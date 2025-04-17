package com.movy.shared.logging.repository;

import com.movy.shared.logging.model.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SystemLogRepository extends JpaRepository<SystemLog, UUID> {
}
