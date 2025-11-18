package com.mthree.logai.AI;

import com.mthree.logai.utils.PromptBuilder;
import com.mthree.logai.service.AI.HFConfig;
import com.mthree.logai.service.AI.HFClient;

public class TestRunner {

    
    public static void main(String[] args) {

        HFConfig config = new HFConfig();
        config.apiKey = System.getenv("HUGGINGFACE_API_KEY");  // or set via Spring in a real app
        config.model = "mistralai/Mistral-7B-Instruct-v0.2:featherless-ai";   
        config.apiUrl = "https://router.huggingface.co/v1"; 

        HFClient client = new HFClient(config);
        PromptBuilder builder = new PromptBuilder();

        String sampleLogs = """
                2025-11-17 10:12:04,001 ERROR [db-conn] Connection timeout to database after 5000ms
                2025-11-17 10:12:04,003 WARN  [cache] Redis set failed, retrying
                2025-11-17 10:12:05,100 ERROR [parser] Failed to parse input file
                """;

        runAnalysisTest(client, builder, sampleLogs);
        runSummaryTest(client, builder, sampleLogs);
        runAnomalyTest(client, builder, sampleLogs);
        runTimelineTest(client, builder, sampleLogs);
        runFixTest(client, builder, sampleLogs);
        runDevOpsChatTest(client, builder);
    }

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

	private static void runDevOpsChatTest(HFClient client, PromptBuilder builder) {
		System.out.println("\n=== TEST: DEVOPS CHAT ===");
		String prompt = builder.devOpsChatPrompt("My Kubernetes pod keeps restarting. Here are logs...");
		System.out.println(client.ask(prompt));
	}
}
