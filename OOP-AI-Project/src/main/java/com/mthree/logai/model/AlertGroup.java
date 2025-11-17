package com.mthree.logai.model;

import java.util.List;

public class AlertGroup {

    private String key;
    private List<LogEntry> logs;

    public AlertGroup() {}

    public AlertGroup(String key, List<LogEntry> logs) {
        this.key = key;
        this.logs = logs;
    }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public List<LogEntry> getLogs() { return logs; }
    public void setLogs(List<LogEntry> logs) { this.logs = logs; }

}
