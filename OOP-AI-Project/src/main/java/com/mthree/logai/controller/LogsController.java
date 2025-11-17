package com.mthree.logai.controller;

import com.mthree.logai.dto.UploadLogRequest;
import com.mthree.logai.dto.LogResponse;

//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logs")
public class LogsController {

    @PostMapping("/upload")
    public ResponseEntity<LogResponse> uploadLog(@RequestBody UploadLogRequest request) {

        // Placeholder until Member 3 implements real storage logic
        String generatedId = "log-123";
        LogResponse response = new LogResponse(generatedId, request.getLogContent());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogResponse> getLogById(@PathVariable String id) {

        // Placeholder until Member 3 implements retrieval logic
        LogResponse response = new LogResponse(id, "placeholder log content from storage");

        return ResponseEntity.ok(response);
    }
}

