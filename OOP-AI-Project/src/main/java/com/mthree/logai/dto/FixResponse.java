package com.mthree.logai.dto;

public class FixResponse {
    private String recommendation;

    public FixResponse(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getRecommendation() {
        return recommendation;
    }
}
