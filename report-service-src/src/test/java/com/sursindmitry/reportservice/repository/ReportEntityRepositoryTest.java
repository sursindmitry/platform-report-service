package com.sursindmitry.reportservice.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.sursindmitry.reportservice.BaseIntegrationTest;
import com.sursindmitry.reportservice.domain.entity.ReportEntity;
import java.time.LocalDateTime;
import java.util.UUID;
import org.bson.types.Binary;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ReportEntityRepositoryTest extends BaseIntegrationTest {

    @Autowired
    ReportEntityRepository reportEntityRepository;

    @DisplayName("Тест на сохранение и получение сущности PdfDocument")
    @Test
    void saveAndFindPdfDocument() {
        ReportEntity reportEntity = new ReportEntity(
            UUID.randomUUID(),
            "Test title",
            ".pdf",
            new Binary(new byte[] {1, 2, 3}),
            LocalDateTime.now()
        );

        reportEntityRepository.save(reportEntity);

        ReportEntity foundPdf = reportEntityRepository.findById(reportEntity.getId())
            .orElse(null);

        assertThat(foundPdf).isEqualTo(reportEntity);
    }

}
