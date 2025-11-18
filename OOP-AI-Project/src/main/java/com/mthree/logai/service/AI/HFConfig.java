package com.mthree.logai.service.AI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HFConfig {

    @Value("${hf.api.key:#{null}}")
    public String apiKey;

    // Any text-generation model that HF Router supports
    @Value("${hf.model:mistralai/Mistral-7B-Instruct-v0.2:featherless-ai}")
    public String model;

    @Value("${hf.api.url:https://router.huggingface.co/v1}")
    public String apiUrl;
}
