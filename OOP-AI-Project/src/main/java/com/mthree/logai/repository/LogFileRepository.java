package com.mthree.logai.repository;


import com.mthree.logai.model.LogFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogFileRepository extends JpaRepository<LogFile, Integer> {
}

