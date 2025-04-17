package com.movy.shared.logging.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movy.shared.logging.model.SystemLog;
import com.movy.shared.logging.repository.SystemLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class LogService {

    private final SystemLogRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void log(String action, String className, String methodName, String username, String message, Object payload) {
        try {
            String payloadJson = payload != null ? objectMapper.writeValueAsString(payload) : null;

            repository.save(SystemLog.builder()
                    .action(action)
                    .className(className)
                    .methodName(methodName)
                    .username(username != null ? username : "ADMIN")
                    .message(message)
                    .timestamp(Instant.now())
                    .payload(payloadJson)
                    .build());

        } catch (Exception e) {
            System.err.println("Erro ao salvar log: " + e.getMessage());
        }
    }
}
