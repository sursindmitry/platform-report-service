package com.sursindmitry.reportservice.service;

import org.springframework.http.ResponseEntity;

/**
 * Сервис генерации отчётов.
 */
public interface ReportService {
    ResponseEntity<byte[]> generateReport(String type);

}
