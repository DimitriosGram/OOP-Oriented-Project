package com.mthree.logai.model;

public class IncidentTimeline {

    private String timestamp;
    private String level;
    private String description;

    private String impact;
    private String fixHint;

    public IncidentTimeline() {}

    public IncidentTimeline(String timestamp, String level, String description) {
        this.timestamp = timestamp;
        this.level = level;
        this.description = description;
    }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImpact() { return impact; }
    public void setImpact(String impact) { this.impact = impact; }

    public String getFixHint() { return fixHint; }
    public void setFixHint(String fixHint) { this.fixHint = fixHint; }


}
