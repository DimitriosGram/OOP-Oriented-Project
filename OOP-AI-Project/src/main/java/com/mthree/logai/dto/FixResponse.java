package com.mthree.logai.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class FixResponse {
    private JsonNode recommendation;

    public FixResponse(JsonNode recommendation) {
        this.recommendation = recommendation;
    }

    public JsonNode getRecommendation() {
        return recommendation;
    }
}

