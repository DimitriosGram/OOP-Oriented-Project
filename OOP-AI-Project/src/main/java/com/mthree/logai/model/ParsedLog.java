package com.mthree.logai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "parsed_log",
    indexes = {@Index(name = "fk_parsed_log_log_file_idx", columnList = "log_file_id")})
public class ParsedLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "log_file_id", nullable = false)
    private LogFile logFile;

    @Column(name = "source_type", nullable = false, length = 20)
    private String sourceType;

    @Column(name = "total_entries")
    private Integer totalEntries;

    @OneToMany(mappedBy = "parsedLog", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ParsedLogEntry> parsedEntries;

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

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getTotalEntries() {
        return totalEntries;
    }

    public void setTotalEntries(Integer totalEntries) {
        this.totalEntries = totalEntries;
    }

    public List<ParsedLogEntry> getParsedEntries() {
        return parsedEntries;
    }

    public void setParsedEntries(List<ParsedLogEntry> parsedEntries) {
        this.parsedEntries = parsedEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ParsedLog parsedLog = (ParsedLog) o;
        return Objects.equals(id, parsedLog.id)
                && Objects.equals(logFile, parsedLog.logFile)
                && Objects.equals(sourceType, parsedLog.sourceType)
                && Objects.equals(totalEntries, parsedLog.totalEntries)
                && Objects.equals(parsedEntries, parsedLog.parsedEntries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, logFile, sourceType, totalEntries, parsedEntries);
    }
}
