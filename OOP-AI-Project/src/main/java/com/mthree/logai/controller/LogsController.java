package com.mthree.logai.controller;

import com.mthree.logai.dto.UploadLogRequest;
import com.mthree.logai.dto.LogResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/logs")
public class LogsController {

    @PostMapping("/upload")
    public ResponseEntity<LogResponse> upload(@RequestBody UploadLogRequest request) {
        if (request.getLogContent() == null || request.getLogContent().isBlank()) {
            return ResponseEntity.badRequest().body(new LogResponse("No logs provided"));
        }

        // Just return the raw logs
        return ResponseEntity.ok(new LogResponse(request.getLogContent()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable String id) {
        return ResponseEntity.ok("Log storage disabled. Retrieval not supported.");
    }
}

