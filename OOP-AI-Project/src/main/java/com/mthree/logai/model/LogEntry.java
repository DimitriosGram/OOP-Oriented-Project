package com.mthree.logai.model;

public class LogEntry {

    private String id;
    private String timestamp;
    private String source;
    private String level;
    private String message;
    private String raw;

    public LogEntry() {}

    public LogEntry(String id, String timestamp, String source, String level, String message, String raw) {
        this.id = id;
        this.timestamp = timestamp;
        this.source = source;
        this.level = level;
        this.message = message;
        this.raw = raw;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getRaw() { return raw; }
    public void setRaw(String raw) { this.raw = raw; }


}
