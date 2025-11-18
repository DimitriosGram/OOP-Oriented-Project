package com.mthree.logai.dto;


import com.fasterxml.jackson.databind.JsonNode;

public class AnalyseResponse {
    private JsonNode analysis;

    public AnalyseResponse(JsonNode analysis) {
        this.analysis = analysis;
    }

    public JsonNode getAnalysis() {
        return analysis;
    }
}


