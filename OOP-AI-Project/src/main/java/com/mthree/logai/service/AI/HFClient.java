package com.mthree.logai.service.AI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class HFClient {

    private final HFConfig config;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    public HFClient(HFConfig config) {
        this.config = config;
        if (config.apiKey == null || config.apiKey.isBlank()) {
            throw new RuntimeException("HuggingFace API key not set (hf.api.key or HUGGINGFACE_API_KEY).");
        }
    }

    public String ask(String prompt) {
        try {
            // Build router chat completions URL: {apiUrl}/chat/completions
            String url = config.apiUrl;
            if (url.endsWith("/")) url = url.substring(0, url.length() - 1);
            url += "/chat/completions";

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(config.apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Build OpenAI-style chat payload: { model, messages: [{role:"user",content:prompt}], temperature, max_tokens }
            ObjectNode body = mapper.createObjectNode();
            body.put("model", config.model);
            ArrayNode messages = mapper.createArrayNode();
            ObjectNode userMsg = mapper.createObjectNode();
            userMsg.put("role", "user");
            userMsg.put("content", prompt);
            messages.add(userMsg);
            body.set("messages", messages);
            body.put("temperature", 0.2);
            body.put("max_tokens", 1024);

            HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(body), headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("HF Router API error: " + response.getStatusCode() + " " + response.getBody());
            }

            String respBody = response.getBody();
            if (respBody == null) return "";

            JsonNode root = mapper.readTree(respBody);

            // 1) Router/OpenAI-style: choices[0].message.content
            JsonNode msgContent = root.path("choices").path(0).path("message").path("content");
            if (!msgContent.isMissingNode() && !msgContent.isNull()) {
                return msgContent.asText().trim();
            }

            // 2) Fallback OpenAI-style: choices[0].text
            JsonNode choiceText = root.path("choices").path(0).path("text");
            if (!choiceText.isMissingNode() && !choiceText.isNull()) {
                return choiceText.asText().trim();
            }

            // 3) Fallback: top-level generated_text / array responses
            JsonNode genTop = root.path("generated_text");
            if (!genTop.isMissingNode() && !genTop.isNull()) {
                return genTop.asText().trim();
            }
            if (root.isArray() && root.size() > 0) {
                JsonNode first = root.get(0).path("generated_text");
                if (!first.isMissingNode() && !first.isNull()) {
                    return first.asText().trim();
                }
            }

            // Fallback: return raw response body
            return respBody.trim();

        } catch (HttpClientErrorException.NotFound nf) {
            throw new RuntimeException("HF Router returned 404 Not Found. Ensure hf.api.url points to the router base (e.g. https://router.huggingface.co/v1) and you're POSTing to /chat/completions; also verify model name and access.", nf);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to call HuggingFace Router API", ex);
        }
    }
}
