package com.mthree.logai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "log_file")
public class LogFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false)
    private String filename;

    @Column(name = "uploaded_at", nullable = false)
    private LocalDateTime uploadedAt;

    @Column(name = "detected_source_type", length = 20)
    private String detectedSourceType;

    @Column(name = "status", nullable = false, length = 20)
    private String status = "UPLOADED";

    @OneToMany(mappedBy = "logFile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LogEntry> entries;

    @OneToMany(mappedBy = "logFile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ParsedLog> parsedLogs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LogFile logFile = (LogFile) o;
        return Objects.equals(id, logFile.id)
                && Objects.equals(filename, logFile.filename)
                && Objects.equals(uploadedAt, logFile.uploadedAt)
                && Objects.equals(detectedSourceType, logFile.detectedSourceType)
                && Objects.equals(status, logFile.status)
                && Objects.equals(entries, logFile.entries)
                && Objects.equals(parsedLogs, logFile.parsedLogs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filename, uploadedAt, detectedSourceType, status, entries, parsedLogs);
    }
}
