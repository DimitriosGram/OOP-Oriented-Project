package com.mthree.logai.service.AI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mthree.logai.utils.PromptBuilder;

import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final HFClient client;
    private final PromptBuilder promptBuilder;
    private final ObjectMapper mapper = new ObjectMapper();

    public AIService(HFClient client, PromptBuilder promptBuilder) {
        this.client = client;
        this.promptBuilder = promptBuilder;
    }

    private JsonNode askModel(String prompt, String operation) {
        try {
            String result = client.ask(prompt);
            return mapper.readTree(result);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse " + operation + " JSON", e);
        }
    }

    public String chat(String message) {
        return client.ask(message);
    }

    public JsonNode analyzeLogs(String logs) {
        return askModel(promptBuilder.analysisPrompt(logs), "analyzeLogs");
    }

    public JsonNode analyzeAlerts(String logs) {
        return askModel(promptBuilder.alertPrompt(logs), "alert");
    }

    public JsonNode generateIncidentTimeline(String logs) {
        return askModel(promptBuilder.timelinePrompt(logs), "timeline");
    }

    public JsonNode summarizeIncident(String logs) {
        return askModel(promptBuilder.incidentSummaryPrompt(logs), "incident summary");
    }

    public JsonNode detectAnomaly(String logs) {
        return askModel(promptBuilder.anomalyPrompt(logs), "anomaly");
    }

    public JsonNode recommendFix(String logs) {
        return askModel(promptBuilder.fixPrompt(logs), "fix");
    }

    public JsonNode devOpsChat(String question) {
        return askModel(promptBuilder.devOpsChatPrompt(question), "devops chat");
    }
}
