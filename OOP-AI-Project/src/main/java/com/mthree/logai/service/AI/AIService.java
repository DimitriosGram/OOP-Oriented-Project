package com.mthree.logai.service.AI;
import com.mthree.logai.utils.PromptBuilder;

import org.springframework.stereotype.Service;
import com.mthree.logai.exception.GlobalExceptionHandler;

@Service
public class AIService {

    private final HFClient client;
    private final PromptBuilder promptBuilder;

    public AIService(HFClient client, PromptBuilder promptBuilder) {
        this.client = client;
        this.promptBuilder = promptBuilder;
    }

    private String askModel(String prompt, String operation) {
        // try {
            String result = client.ask(prompt);
            return result;
        // } catch (Exception e) {
        //     throw new GlobalExceptionHandler("Failed to get output from model for operation: " + operation, e);
        // }
    }      

    public String chat(String message) {
        return client.ask(message);
    }

    public String analyzeLogs(String logs) {
        return askModel(promptBuilder.analysisPrompt(logs), "analyzeLogs");
    }

    public String analyzeAlerts(String logs) {
        return askModel(promptBuilder.alertPrompt(logs), "alert");
    }

    public String generateIncidentTimeline(String logs) {
        return askModel(promptBuilder.timelinePrompt(logs), "timeline");
    }

    public String summarizeIncident(String logs) {
        return askModel(promptBuilder.incidentSummaryPrompt(logs), "incident summary");
    }

    public String detectAnomaly(String logs) {
        return askModel(promptBuilder.anomalyPrompt(logs), "anomaly");
    }

    public String recommendFix(String logs) {
        return askModel(promptBuilder.fixPrompt(logs), "fix");
    }

    public String devOpsChat(String question) {
        return askModel(promptBuilder.devOpsChatPrompt(question), "devops chat");
    }
}
