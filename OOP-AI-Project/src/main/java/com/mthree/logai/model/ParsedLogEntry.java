package com.mthree.logai.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "parsed_log_entry",
    indexes = {@Index(name = "fk_parsed_log_entry_parsed_log_idx", columnList = "parsed_log_id")})
public class ParsedLogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "parsed_log_id", nullable = false)
    private ParsedLog parsedLog;

    @Column(name = "line_number", nullable = false)
    private Integer lineNumber;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "level", length = 25)
    private String level;

    @Column(name = "message", length = 200)
    private String message;

    @Lob
    @Column(name = "raw_text", nullable = false)
    private String rawText;

    @Column(name = "source", nullable = false)
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ParsedLog getParsedLog() {
        return parsedLog;
    }

    public void setParsedLog(ParsedLog parsedLog) {
        this.parsedLog = parsedLog;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        ParsedLogEntry that = (ParsedLogEntry) o;
        return Objects.equals(id, that.id)
                && Objects.equals(parsedLog, that.parsedLog)
                && Objects.equals(lineNumber, that.lineNumber)
                && Objects.equals(timestamp, that.timestamp)
                && Objects.equals(level, that.level)
                && Objects.equals(message, that.message)
                && Objects.equals(rawText, that.rawText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parsedLog, lineNumber, timestamp, level, message, rawText);
    }
}
