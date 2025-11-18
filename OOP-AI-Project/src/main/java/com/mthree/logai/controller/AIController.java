package com.mthree.logai.controller;

import com.mthree.logai.service.AI.AIService;
import com.mthree.logai.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<AnalyseResponse> analyze(@RequestBody String request) {
        var result = aiService.analyzeLogs(request);
        AnalyseResponse response = new AnalyseResponse(result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/summarise-incident")
    public ResponseEntity<SummariseResponse> summarise(@RequestBody String request) {
        var result = aiService.summarizeIncident(request);
        SummariseResponse response = new SummariseResponse(result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/recommend-fix")
    public ResponseEntity<FixResponse> recommendFix(@RequestBody String request) {
        var result = aiService.recommendFix(request);
        FixResponse response = new FixResponse(result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/detect-anomaly")
    public ResponseEntity<Object> detectAnomaly(@RequestBody String request) {
        var result = aiService.detectAnomaly(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/devops-chat")
    public ResponseEntity<Object> chat(@RequestBody String request) {
        var result = aiService.devOpsChat(request);
        return ResponseEntity.ok(result);
    }
}


