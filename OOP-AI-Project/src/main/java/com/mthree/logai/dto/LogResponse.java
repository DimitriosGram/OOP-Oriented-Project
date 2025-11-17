package com.mthree.logai.dto;


public class LogResponse {
    private String id;
    private String content;

    public LogResponse(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}

