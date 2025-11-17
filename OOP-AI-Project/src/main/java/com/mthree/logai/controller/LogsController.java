package com.mthree.logai.controller;

import com.mthree.logai.dto.UploadLogRequest;
import com.mthree.logai.dto.LogResponse;

import com.mthree.logai.model.LogFile;
import com.mthree.logai.service.storage.LogParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/logs")
public class LogsController {

    @Autowired
    LogParser logParser;

    // todo switch this to two separate upload endpoints for json and file
    // currently doesn't work - need to handle each case in separate endpoint.
    /**
     * Handles both JSON upload {"logContent": [content]} and file upload through HTTP multipart file upload.
     * this endpoint identifies which method is used and persists either.
     * The HTTP request must contain one of either JSON or multipart file body.
     * @param jsonRequest optional request given in JSON format with "logContent" key.
     * @param file optional multipart file given in HTTP request body.
     * @return new id generated from persisting the log if upload succeeds,
     * otherwise error response or bad request response.
     * @throws IOException if error occurs in persisting the log.
     */
    @PostMapping("/upload")
    public ResponseEntity<LogResponse> uploadLog(
            @RequestPart(value = "json", required = false) UploadLogRequest jsonRequest,
            @RequestPart(value = "file", required = false) MultipartFile file)
                throws IOException {

        String generatedId = null;
        String contentPreview = null;

        if (file != null) {
            // handle file upload
            try (Reader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)) {
                LogFile logFile = logParser.persistLog(reader, file.getOriginalFilename());
                generatedId = logFile.getId().toString();
                contentPreview = logFile.getFilename();
            }

        } else if (jsonRequest != null) {
            // handle JSON upload
            String logs = jsonRequest.getLogContent();
            try (Reader reader = new StringReader(logs)) {
                LogFile logFile = logParser.persistLog(reader);
                generatedId = logFile.getId().toString();
                contentPreview = logs.length() > 50 ? logs.substring(0, 50) + "..." : logs;
            }
        } else {
            return ResponseEntity.badRequest().body(new LogResponse(null, "no log provided"));
        }

        return ResponseEntity.ok(new LogResponse(generatedId, contentPreview));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogResponse> getLogById(@PathVariable String id) {

        // Placeholder until Member 3 implements retrieval logic
        LogResponse response = new LogResponse(id, "placeholder log content from storage");

        return ResponseEntity.ok(response);
    }
}

