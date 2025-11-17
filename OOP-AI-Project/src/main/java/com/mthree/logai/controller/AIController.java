package com.mthree.logai.controller;


import com.mthree.logai.dto.*;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @PostMapping("/analyze")
    public ResponseEntity<AnalyseResponse> analyze(@RequestBody LogRequest request) {

        AnalyseResponse response = new AnalyseResponse(
                "placeholder root cause: analysis not implemented yet"
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/summarise-incident")
    public ResponseEntity<SummariseResponse> summarise(@RequestBody LogRequest request) {

        SummariseResponse response = new SummariseResponse(
                "placeholder incident summary"
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/recommend-fix")
    public ResponseEntity<FixResponse> recommendFix(@RequestBody LogRequest request) {

        FixResponse response = new FixResponse(
                "placeholder recommended fix"
        );

        return ResponseEntity.ok(response);
    }
}

