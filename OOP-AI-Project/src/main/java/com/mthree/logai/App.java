package com.mthree.logai;

import com.mthree.logai.model.LogFile;
import com.mthree.logai.repository.LogFileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    @Bean
    CommandLineRunner runner(LogFileRepository logFileRepository) {
        return args -> {
            LogFile logFile = new LogFile();
            logFile.setFilename("test.log");
            logFile.setUploadedAt(LocalDateTime.now());
            logFileRepository.save(logFile);

            System.out.println("Saved Logfile: " + logFileRepository.findAll());
        };
    }
}
