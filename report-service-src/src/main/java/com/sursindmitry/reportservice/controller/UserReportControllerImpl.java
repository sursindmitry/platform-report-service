package com.sursindmitry.reportservice.controller;

import com.sursindmitry.reportservice.service.ReportService;
import com.sursindmitry.reportserviceapi.controller.UserReportController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Реализация контроллера генерации отчётов по пользователям {@link UserReportController}.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserReportControllerImpl implements UserReportController {

    private final ReportService reportService;

    @Override
    public ResponseEntity<byte[]> generatePdf(String type) {

        return reportService.generateReport(type);
    }
}
