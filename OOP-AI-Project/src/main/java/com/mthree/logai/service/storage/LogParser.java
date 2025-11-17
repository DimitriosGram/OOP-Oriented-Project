package com.mthree.logai.service.storage;

import com.mthree.logai.model.LogEntry;
import com.mthree.logai.model.LogFile;
import com.mthree.logai.repository.LogEntryRepository;
import com.mthree.logai.repository.LogFileRepository;
import com.mthree.logai.repository.ParsedLogEntryRepository;
import com.mthree.logai.repository.ParsedLogRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LogParser {

    @Autowired
    LogEntryRepository logEntryRepository;

    @Autowired
    LogFileRepository logFileRepository;

    @Autowired
    ParsedLogRepository parsedLogRepository;

    @Autowired
    ParsedLogEntryRepository parsedLogEntryRepository;

    @Transactional
    public LogFile persistLog(Reader logContent) throws IOException {
        return persistLog(logContent, "FROM_JSON");
    }

    // returns LogFile with generated ID
    @Transactional
    public LogFile persistLog(Reader logContent, String fileName) throws IOException {

        try {
            LogFile logFile = saveLogFile(logContent, fileName); // persist main file
            List<LogEntry> entries = parseLogEntries(logContent, logFile); // create entries for file
            logEntryRepository.saveAll(entries); // persist entries
            return logFile;
        } catch (Exception e) {
            throw new IOException("Error uploading log file", e);
        }
    }


    private LogFile saveLogFile(Reader logContent, String fileName) {
        LogFile logFile = new LogFile();
        logFile.setFilename(fileName);
        logFile.setUploadedAt(LocalDateTime.now());
        return logFileRepository.save(logFile); // return logFile with generated ID
    }

    private List<LogEntry> parseLogEntries(Reader logContent, LogFile logFile) {
        BufferedReader reader = new BufferedReader(logContent);
        AtomicInteger lineNumber = new AtomicInteger(1);
        return reader.lines().map(line -> {
            LogEntry entry = new LogEntry();
            entry.setLogFile(logFile);
            entry.setRawText(line);
            entry.setLineNumber(lineNumber.getAndIncrement());
            return entry;
        }).toList();
    }


}
