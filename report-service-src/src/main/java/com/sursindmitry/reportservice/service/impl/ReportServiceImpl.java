package com.sursindmitry.reportservice.service.impl;

import com.sursindmitry.reportservice.domain.entity.ReportEntity;
import com.sursindmitry.reportservice.service.ReportEntityService;
import com.sursindmitry.reportservice.service.ReportFactory;
import com.sursindmitry.reportservice.service.ReportGenerator;
import com.sursindmitry.reportservice.service.ReportService;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.bson.types.Binary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса генерации отчётов {@link ReportService}.
 */
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportFactory reportFactory;
    private final ReportEntityService reportEntityService;

    @Override
    public ResponseEntity<byte[]> generateReport(String type) {

        ReportGenerator generator = reportFactory.getReportGenerator(type.toUpperCase());

        byte[] reportContent = generator.generateReport();

        String title = "user_report" + LocalDateTime.now();

        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setId(UUID.randomUUID());
        reportEntity.setTitle(title);
        reportEntity.setFile(new Binary(reportContent));
        reportEntity.setType(type);

        reportEntityService.save(reportEntity);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", title);

        return new ResponseEntity<>(reportContent, headers, HttpStatus.OK);
    }
}
