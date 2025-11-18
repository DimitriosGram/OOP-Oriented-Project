package com.mthree.logai.service.AI;

import com.mthree.logai.utils.PromptBuilder;

public class TestRunner {

    public static void main(String[] args) {

        HFConfig config = new HFConfig();
        config.apiKey = System.getenv("HUGGINGFACE_API_KEY");  
        config.model = "mistralai/Mistral-7B-Instruct-v0.2:featherless-ai";   
        config.apiUrl = "https://router.huggingface.co/v1"; 

        HFClient client = new HFClient(config);
        PromptBuilder builder = new PromptBuilder();

        String sampleLogs = """
                2025-11-17 10:12:04,001 ERROR [db-conn] Connection timeout to database after 5000ms
                2025-11-17 10:12:04,003 WARN  [cache] Redis set failed, retrying
                2025-11-17 10:12:05,100 ERROR [parser] Failed to parse input file
                """;

        // Kubernetes logs for the devops chat test
        String sampleK8sLogs = """
                2025-11-17T10:20:02.123Z ERROR   kubelet: Failed to connect to database: Connection refused
                2025-11-17T10:20:02.125Z WARN    kubelet: Retrying in 5s...
                2025-11-17T10:20:07.126Z ERROR   app-container: java.net.ConnectException: Connection refused
                2025-11-17T10:20:07.127Z ERROR   kubelet: Liveness probe failed: HTTP probe failed with statuscode: 500
                2025-11-17T10:20:07.128Z WARN    kubelet: Readiness probe failed: Get "http://10.32.0.15:8080/health": dial tcp 10.32.0.15:8080: connect: connection refused
                2025-11-17T10:20:12.200Z ERROR   kubelet: Back-off restarting failed container
                2025-11-17T10:20:30.500Z INFO    k8s.events: Pod my-app-5d8f7c9d4-abcde status CrashLoopBackOff
                """;

        runAnalysisTest(client, builder, sampleLogs);
        runSummaryTest(client, builder, sampleLogs);
        runAnomalyTest(client, builder, sampleLogs);
        runTimelineTest(client, builder, sampleLogs);
        runFixTest(client, builder, sampleLogs);
        runDevOpsChatTest(client, builder, sampleK8sLogs);
    }

    //Test Ai functions
    private static void runAnalysisTest(HFClient client, PromptBuilder builder, String logs) {
        System.out.println("\n=== TEST: LOG ANALYSIS ===");
        String prompt = builder.analysisPrompt(logs);
        System.out.println(client.ask(prompt));
    }

    private static void runSummaryTest(HFClient client, PromptBuilder builder, String logs) {
        System.out.println("\n=== TEST: INCIDENT SUMMARY ===");
        String prompt = builder.incidentSummaryPrompt(logs);
        System.out.println(client.ask(prompt));
    }

    private static void runAnomalyTest(HFClient client, PromptBuilder builder, String logs) {
        System.out.println("\n=== TEST: ANOMALY DETECTION ===");
        String prompt = builder.anomalyPrompt(logs);
        System.out.println(client.ask(prompt));
    }

    private static void runTimelineTest(HFClient client, PromptBuilder builder, String logs) {
        System.out.println("\n=== TEST: TIMELINE GENERATOR ===");
        String prompt = builder.timelinePrompt(logs);
        System.out.println(client.ask(prompt));
    }

    private static void runFixTest(HFClient client, PromptBuilder builder, String logs) {
        System.out.println("\n=== TEST: FIX RECOMMENDATION ===");
        String prompt = builder.fixPrompt(logs);
        System.out.println(client.ask(prompt));
    }

    private static void runDevOpsChatTest(HFClient client, PromptBuilder builder, String logs) {
        System.out.println("\n=== TEST: DEVOPS CHAT ===");
        String prompt = builder.devOpsChatPrompt("My Kubernetes pod keeps restarting. Here are logs:\n" + logs);
        System.out.println(client.ask(prompt));
    }
}
