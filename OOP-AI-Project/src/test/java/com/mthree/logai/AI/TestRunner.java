package com.mthree.logai.AI;

import com.mthree.logai.utils.PromptBuilder;
import com.mthree.logai.service.AI.HFConfig;
import com.mthree.logai.service.AI.HFClient;

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
				2025-11-17 10:12:04,001 ERROR [db-conn] Connection timeout to database after 1000ms
                """;
		

        String sampleK8sLogs = """
                2025-11-17T10:20:02.123Z ERROR   kubelet: Failed to connect to database: Connection refused
                2025-11-17T10:20:02.125Z WARN    kubelet: Retrying in 5s...
                2025-11-17T10:20:07.126Z ERROR   app-container: java.net.ConnectException: Connection refused
                2025-11-17T10:20:07.127Z ERROR   kubelet: Liveness probe failed: HTTP probe failed with statuscode: 500
                2025-11-17T10:20:07.128Z WARN    kubelet: Readiness probe failed: Get "http://10.32.0.15:8080/health": dial tcp 10.32.0.15:8080: connect: connection refused
                2025-11-17T10:20:12.200Z ERROR   kubelet: Back-off restarting failed container
                2025-11-17T10:20:30.500Z INFO    k8s.events: Pod my-app-5d8f7c9d4-abcde status CrashLoopBackOff
                """;

		String sampleLongLog="""
				2025-11-18 14:22:46,913 [main] INFO  com.acme.payment.PaymentProcessor - Starting payment processing run...
2025-11-18 14:22:47,102 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'transactionService'
2025-11-18 14:22:47,229 [main] INFO  com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Starting...

2025-11-18 14:22:47,991 [HikariPool-1 connection adder] WARN  com.zaxxer.hikari.pool.PoolBase - HikariPool-1 - Failed to validate connection org.postgresql.jdbc.PgConnection@6fa590ba (This connection has been closed.). Possibly consider using a shorter maxLifetime value.
2025-11-18 14:22:47,992 [HikariPool-1 connection adder] WARN  com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Failed to initialize pool: Connection to localhost:5432 refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.

org.postgresql.util.PSQLException: Connection to localhost:5432 refused.
    at org.postgresql.core.v3.ConnectionFactoryImpl.openConnection(ConnectionFactoryImpl.java:315)
    at org.postgresql.core.ConnectionFactory.openConnection(ConnectionFactory.java:51)
    at org.postgresql.jdbc.PgConnection.<init>(PgConnection.java:223)
    at org.postgresql.Driver.makeConnection(Driver.java:465)
    at org.postgresql.Driver.connect(Driver.java:264)
    at com.zaxxer.hikari.util.DriverDataSource.getConnection(DriverDataSource.java:138)
    at com.zaxxer.hikari.pool.PoolBase.newConnection(PoolBase.java:364)
    at com.zaxxer.hikari.pool.HikariPool.addConnection(HikariPool.java:495)
    ... 10 more
Caused by: java.net.ConnectException: Connection refused: connect
    at java.base/sun.nio.ch.Net.connect0(Native Method)
    at java.base/sun.nio.ch.Net.connect(Net.java:579)
    at java.base/sun.nio.ch.Net.connect(Net.java:568)
    at java.base/sun.nio.ch.NioSocketImpl.connect(NioSocketImpl.java:588)
    at java.base/java.net.SocksSocketImpl.connect(SocksSocketImpl.java:327)
    at java.base/java.net.Socket.connect(Socket.java:633)
    at org.postgresql.core.PGStream.<init>(PGStream.java:79)
    at org.postgresql.core.v3.ConnectionFactoryImpl.tryConnect(ConnectionFactoryImpl.java:138)
    at org.postgresql.core.v3.ConnectionFactoryImpl.openConnection(ConnectionFactoryImpl.java:252)
    ... 17 more


2025-11-18 14:22:48,103 [main] ERROR com.acme.payment.repository.TransactionRepository - Failed to initialize JPA EntityManager
javax.persistence.PersistenceException: Unable to build Hibernate SessionFactory
    at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.persistenceException(EntityManagerFactoryBuilderImpl.java:1336)
    at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1302)
    at org.hibernate.jpa.HibernatePersistenceProvider.createEntityManagerFactory(HibernatePersistenceProvider.java:56)
    at javax.persistence.Persistence.createEntityManagerFactory(Persistence.java:79)
    at javax.persistence.Persistence.createEntityManagerFactory(Persistence.java:54)
    at com.acme.payment.config.DatabaseConfig.entityManagerFactory(DatabaseConfig.java:47)
    ... 14 more
Caused by: org.hibernate.exception.JDBCConnectionException: Unable to open JDBC Connection for DDL execution
    at org.hibernate.exception.internal.SQLStateConversionDelegate.convert(SQLStateConversionDelegate.java:115)
    at org.hibernate.exception.internal.StandardSQLExceptionConverter.convert(StandardSQLExceptionConverter.java:42)
    at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:111)
    at org.hibernate.engine.jdbc.connections.internal.BasicConnectionCreator.createConnection(BasicConnectionCreator.java:62)
    at org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl.buildCreator(DriverManagerConnectionProviderImpl.java:121)
    at org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl.configure(DriverManagerConnectionProviderImpl.java:76)
    at org.hibernate.boot.internal.SessionFactoryBuilderImpl.build(SessionFactoryBuilderImpl.java:459)
    at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1299)
    ... 19 more
Caused by: org.postgresql.util.PSQLException: The connection attempt failed.
    at org.postgresql.core.v3.ConnectionFactoryImpl.openConnection(ConnectionFactoryImpl.java:316)
    at org.postgresql.core.ConnectionFactory.openConnection(ConnectionFactory.java:51)
    ... 21 more
Caused by: java.net.SocketTimeoutException: connect timed out
    at java.base/sun.nio.ch.NioSocketImpl.timedFinishConnect(NioSocketImpl.java:546)
    at java.base/sun.nio.ch.NioSocketImpl.connect(NioSocketImpl.java:595)
    at java.base/java.net.Socket.connect(Socket.java:633)
    ... 25 more


2025-11-18 14:22:48,894 [main] ERROR com.acme.payment.PaymentProcessor - Unhandled exception occurred during payment batch run
com.acme.payment.exception.PaymentProcessingException: Failed to process batc

				""";

		runSummaryTest(client, builder, sampleLongLog);
		runAlertPromptTest(client, builder, sampleLogs);
        runAnalysisTest(client, builder, sampleLogs);
        runAnomalyTest(client, builder, sampleLogs);
        runTimelineTest(client, builder, sampleLogs);
        runFixTest(client, builder, sampleLogs);
        runDevOpsChatTest(client, builder, sampleK8sLogs);
		
    }

	//Test Ai functions
	private static void runAlertPromptTest(HFClient client, PromptBuilder builder, String logs) {
		System.out.println("\n=== TEST: ALERT ANALYSIS ===");
		String prompt = builder.alertPrompt(logs);
		System.out.println(client.ask(prompt));
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

	private static void runDevOpsChatTest(HFClient client, PromptBuilder builder, String logs) {
		System.out.println("\n=== TEST: DEVOPS CHAT ===");
		String prompt = builder.devOpsChatPrompt("My Kubernetes pod keeps restarting. Here are logs:\n" + logs);
		System.out.println(client.ask(prompt));
	}


}
