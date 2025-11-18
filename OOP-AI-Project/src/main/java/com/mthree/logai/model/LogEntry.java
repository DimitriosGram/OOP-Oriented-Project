package com.mthree.logai.model;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "log_entry",
    indexes = {@Index(name = "fk_log_entry_log_file_idx", columnList = "log_file_id")})
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "log_file_id", nullable = false)
    private LogFile logFile;

    @Column(name = "line_number", nullable = false)
    private Integer lineNumber;

    @Lob
    @Column(name = "raw_text")
    private String rawText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LogFile getLogFile() {
        return logFile;
    }

    public void setLogFile(LogFile logFile) {
        this.logFile = logFile;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LogEntry logEntry = (LogEntry) o;
        return Objects.equals(id, logEntry.id) && Objects.equals(logFile, logEntry.logFile) && Objects.equals(lineNumber, logEntry.lineNumber) && Objects.equals(rawText, logEntry.rawText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, logFile, lineNumber, rawText);
    }
}
