package com.mthree.logai.repository;

import com.mthree.logai.model.ParsedLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParsedLogRepository extends JpaRepository<ParsedLog, Integer> {
}
