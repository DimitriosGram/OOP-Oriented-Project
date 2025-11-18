package com.mthree.logai.utils;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

   
    public String analysisPrompt(String logs) {
        return ""
            + "Role: You are a DevOps/SRE log intelligence assistant.\n"
            + "Task: Analyze these logs and return a structured, human-readable plain text report (no JSON, no markdown fences). Use the exact section headings below and populate each section. Be concise and list the most important items first.\n\n"
            + "OUTPUT FORMAT (plain text):\n"
            + "Summary:\n"
            + "- <one-sentence summary>\n\n"
            + "Priority: <P1|P2|P3|P4>\n"
            + "Confidence: <LOW|MEDIUM|HIGH>\n\n"
            + "Root Cause:\n"
            + "- Probable: <short phrase or 'unknown'>\n"
            + "- Evidence:\n"
            + "  - <log line 1>\n"
            + "  - <log line 2>\n\n"
            + "Possible Fix:\n"
            + "- <one-line recommended change or command>\n\n"
            + "Action Plan:\n"
            + "1) <immediate step> (commands/examples if applicable)\n"
            + "2) <follow-up step>\n\n"
            + "Insights:\n"
            + "- [LEVEL] <component> — <short message> → recommendation: <short>\n\n"
            + "Services Affected: <comma-separated list or 'none identified'>\n\n"
            + "LOGS:\n"
            + logs;
    }

   
    public String alertPrompt(String logs) {
        return ""
            + "Role: You classify alerts for SRE. Return a structured plain-text alert report using the headings below.\n\n"
            + "OUTPUT FORMAT (plain text):\n"
            + "Severity: <LOW|MEDIUM|HIGH|CRITICAL>\n"
            + "Classification: <short label>\n\n"
            + "Grouped Alerts:\n"
            + "- Signature: <short signature> (count: <n>)\n\n"
            + "Recommended Actions:\n"
            + "- <step 1>\n"
            + "- <step 2>\n\n"
            + "Evidence:\n"
            + "- <relevant log line 1>\n"
            + "- <relevant log line 2>\n\n"
            + "LOGS:\n"
            + logs;
    }


    public String timelinePrompt(String logs) {
        return ""
            + "Role: Produce an incident timeline for on-call. Return structured plain text following the headings below.\n\n"
            + "OUTPUT FORMAT (plain text):\n"
            + "Timeline:\n"
            + "1) <time> — <event summary> — severity: <LOW|MEDIUM|HIGH|CRITICAL>\n"
            + "   Evidence:\n"
            + "   - <log line>\n\n"
            + "Impact: <one-line impact>\n"
            + "Resolved By: <fix or 'not resolved'>\n"
            + "Risk Score: <0-100>\n"
            + "Confidence: <LOW|MEDIUM|HIGH>\n\n"
            + "Verification Steps:\n"
            + "- <how to verify>\n\n"
            + "LOGS:\n"
            + logs;
    }


    public String incidentSummaryPrompt(String logs) {
        return ""
            + "Role: Produce a concise 5–10 line incident summary for engineers and stakeholders. Return plain text with the headings below.\n\n"
            + "OUTPUT FORMAT (plain text):\n"
            + "Summary:\n"
            + "- <line 1>\n"
            + "- <line 2>\n"
            + "- ... (total 5-10 lines)\n\n"
            + "Impact: <short>\n"
            + "Priority: <P1|P2|P3|P4>\n"
            + "Confidence: <LOW|MEDIUM|HIGH>\n\n"
            + "Top Evidence:\n"
            + "- <line 1>\n"
            + "- <line 2>\n"
            + "- <line 3>\n\n"
            + "Suggested Fix:\n"
            + "- <one-line possible fix or 'none identified'>\n\n"
            + "LOGS:\n"
            + logs;
    }

    public String anomalyPrompt(String logs) {
        return ""
            + "Role: Detect anomalous patterns. Return structured plain text listing anomalies as separate entries.\n\n"
            + "OUTPUT FORMAT (plain text):\n"
            + "Anomalies:\n"
            + "1) Type: <Latency spike|CrashLoop|Memory leak|Repeating warnings|Other>\n"
            + "   Description: <short>\n"
            + "   First Seen: <timestamp or 'unknown'>\n"
            + "   Frequency: <e.g. 5/min>\n"
            + "   Risk Level: <LOW|MEDIUM|HIGH|CRITICAL>\n"
            + "   Suggested Immediate Actions:\n"
            + "   - <action 1>\n"
            + "   Evidence:\n"
            + "   - <log line>\n\n"
            + "Overall Confidence: <LOW|MEDIUM|HIGH>\n\n"
            + "LOGS:\n"
            + logs;
    }


    public String fixPrompt(String logs) {
        return ""
            + "Role: Recommend fixes and rollback plans. Return structured plain text using the headings below.\n\n"
            + "OUTPUT FORMAT (plain text):\n"
            + "Probable Cause: <short or 'unknown'>\n\n"
            + "Recommended Fix (ordered):\n"
            + "1) <step description>\n"
            + "   Command/Example: <exact command or config snippet or 'none'>\n"
            + "   Verify: <how to confirm>\n\n"
            + "Rollback Plan:\n"
            + "- <rollback step 1>\n"
            + "- <rollback step 2>\n\n"
            + "Estimated Effort: <minutes|hours>\n"
            + "Confidence: <LOW|MEDIUM|HIGH>\n\n"
            + "Evidence:\n"
            + "- <log line>\n\n"
            + "LOGS:\n"
            + logs;
    }


    public String devOpsChatPrompt(String question) {
        return ""
            + "Role: You are a DevOps troubleshooting assistant speaking to a non-expert. Return structured plain text and be action-oriented and simple.\n\n"
            + "OUTPUT FORMAT (plain text):\n"
            + "TL;DR:\n"
            + "- <one-line summary>\n\n"
            + "Diagnosis:\n"
            + "- <short diagnosis>\n\n"
            + "Immediate Next Steps:\n"
            + "1) <step> (command if applicable)\n\n"
            + "Commands (if any):\n"
            + "- <exact commands>\n\n"
            + "Confidence: <LOW|MEDIUM|HIGH>\n\n"
            + "Evidence:\n"
            + "- <log line or 'none'>\n\nQUESTION:\n"
            + question;
    }

    public String humanReadableAnalysis(String logs) {
        return ""
            + "Role: Produce short categorized lists for quick analysis. Return only Plain text.\n\n"
            + "Errors:\n"
            + "- <one-line per error>\n\n"
            + "Warnings:\n"
            + "- <one-line per warning>\n\n"
            + "Info:\n"
            + "- <one-line per info>\n\n"
            + "Observations:\n"
            + "- <patterns>\n\n"
            + "NextSteps:\n"
            + "- <3 short actions>\n\n"
            + "LOGS:\n"
            + logs;
    }
}
