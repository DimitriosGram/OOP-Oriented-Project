package com.mthree.logai.repository;

import com.mthree.logai.model.ParsedLogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParsedLogEntryRepository extends JpaRepository<ParsedLogEntry, Integer> {
}
