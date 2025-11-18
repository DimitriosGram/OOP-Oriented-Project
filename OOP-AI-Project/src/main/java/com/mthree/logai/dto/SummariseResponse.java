package com.mthree.logai.dto;


import com.fasterxml.jackson.databind.JsonNode;

public class SummariseResponse {
    private JsonNode summary;

    public SummariseResponse(JsonNode summary) {
        this.summary = summary;
    }

    public JsonNode getSummary() {
        return summary;
    }
}


