package com.mthree.logai.utils;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

    public String analysisPrompt(String logs) {
        return ""
            + "Role: You are a DevOps/SRE log intelligence assistant.\n"
            + "Task: Analyze the logs and return JSON only, matching the SCHEMA exactly. Be concise and list the top findings first.\n\n"
            + "SCHEMA:\n"
            + "{\n"
            + "  \"summary\":\"one-sentence summary\",\n"
            + "  \"priority\":\"P1|P2|P3|P4\",\n"
            + "  \"confidence\":\"LOW|MEDIUM|HIGH\",\n"
            + "  \"rootCause\":{\"probableCause\":\"short phrase or null\",\"confidence\":\"LOW|MEDIUM|HIGH\",\"evidence\":[\"log lines\"]},\n"
            + "  \"actionPlan\":[{\"step\":\"short step\",\"urgency\":\"immediate|short|long\",\"estimatedMinutes\":0}],\n"
            + "  \"insights\":[{\"level\":\"ERROR|WARN|INFO\",\"component\":\"name\",\"message\":\"short\",\"recommendation\":\"short or null\",\"evidence\":[\"log lines\"]}]\n"
            + "}\n\n"
            + "Rules: include evidence arrays for claims; if unknown use null/[]; limit actionPlan to max 5 steps.\n\nLOGS:\n"
            + logs;
    }

    public String alertPrompt(String logs) {
        return ""
            + "Role: Alert classifier for SRE. Return JSON only.\n\n"
            + "SCHEMA:\n"
            + "{\n"
            + "  \"severity\":\"LOW|MEDIUM|HIGH|CRITICAL\",\n"
            + "  \"classification\":\"short label\",\n"
            + "  \"groupedAlerts\":[{\"signature\":\"short signature\",\"count\":0}],\n"
            + "  \"isNoise\":false,\n"
            + "  \"recommendedAction\":[\"step1\",\"step2\"],\n"
            + "  \"evidence\":[\"log lines\"]\n"
            + "}\n\nLOGS:\n"
            + logs;
    }

    public String timelinePrompt(String logs) {
        return ""
            + "Role: Generate an incident timeline for on-call. Return JSON only.\n\n"
            + "SCHEMA:\n"
            + "{\n"
            + "  \"timeline\":[{\"time\":\"ISO or extracted\",\"event\":\"one-line\",\"severity\":\"LOW|MEDIUM|HIGH|CRITICAL\",\"evidence\":[\"log lines\"]}],\n"
            + "  \"impact\":\"one-line impact\",\n"
            + "  \"resolvedBy\":\"fix or null\",\n"
            + "  \"riskScore\":0,\n"
            + "  \"confidence\":\"LOW|MEDIUM|HIGH\"\n"
            + "}\n\nLOGS:\n"
            + logs;
    }

    public String incidentSummaryPrompt(String logs) {
        return ""
            + "Role: Produce a concise 5–10 line incident summary. Return JSON only.\n\n"
            + "SCHEMA:\n"
            + "{\n"
            + "  \"summary\":\"5-10 line summary with \\n separators\",\n"
            + "  \"impact\":\"short\",\n"
            + "  \"priority\":\"P1|P2|P3|P4\",\n"
            + "  \"confidence\":\"LOW|MEDIUM|HIGH\",\n"
            + "  \"topEvidence\":[\"3 most relevant log lines\"]\n"
            + "}\n\nLOGS:\n"
            + logs;
    }

    public String anomalyPrompt(String logs) {
        return ""
            + "Role: Detect anomalies. Return JSON only.\n\n"
            + "SCHEMA:\n"
            + "{\n"
            + "  \"anomalies\":[{\"type\":\"Latency spike|CrashLoop|Memory leak|Repeating warnings|Other\",\"description\":\"short\",\"firstSeen\":\"timestamp\",\"frequency\":\"e.g. 5/min\",\"riskLevel\":\"LOW|MEDIUM|HIGH|CRITICAL\",\"suggestedAction\":[\"steps\"],\"evidence\":[\"log lines\"]}]\n"
            + "}\n\nLOGS:\n"
            + logs;
    }

    public String fixPrompt(String logs) {
        return ""
            + "Role: Recommend fixes and rollback. Return JSON only.\n\n"
            + "SCHEMA:\n"
            + "{\n"
            + "  \"probableCause\":\"short or null\",\n"
            + "  \"recommendedFix\":[{\"step\":\"command or change\",\"impact\":\"low|medium|high\",\"example\":\"snippet or null\"}],\n"
            + "  \"rollbackPlan\":[\"steps\"],\n"
            + "  \"confidence\":\"LOW|MEDIUM|HIGH\",\n"
            + "  \"evidence\":[\"log lines\"]\n"
            + "}\n\nLOGS:\n"
            + logs;
    }

    public String devOpsChatPrompt(String question) {
        return ""
            + "Role: DevOps troubleshooting assistant. Return JSON only and be action-oriented.\n\n"
            + "SCHEMA:\n"
            + "{\n"
            + "  \"answer\":\"short diagnosis\",\n"
            + "  \"nextSteps\":[\"immediate step\",\"follow-up\"],\n"
            + "  \"commands\":[\"exact commands if applicable\"],\n"
            + "  \"confidence\":\"LOW|MEDIUM|HIGH\",\n"
            + "  \"evidence\":[\"log lines or null\"]\n"
            + "}\n\nQUESTION:\n"
            + question;
    }

    public String multiLevelLogPrompt(String logs) {
        return ""
            + "Role: Produce short categorized lists for triage. Return plain text sections (no JSON).\n\n"
            + "Errors:\n- <one-line per error>\n\n"
            + "Warnings:\n- <one-line per warning>\n\n"
            + "Info:\n- <one-line per info>\n\n"
            + "Observations:\n- <patterns>\n\n"
            + "NextSteps:\n- <3 short actions>\n\nLOGS:\n"
            + logs;
    }
}
