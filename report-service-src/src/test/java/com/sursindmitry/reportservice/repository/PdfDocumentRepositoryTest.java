package com.sursindmitry.reportservice.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.sursindmitry.reportservice.BaseIntegrationTest;
import com.sursindmitry.reportservice.domain.entity.PdfDocument;
import java.time.LocalDateTime;
import java.util.UUID;
import org.bson.types.Binary;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PdfDocumentRepositoryTest extends BaseIntegrationTest {

    @Autowired
    PdfDocumentRepository pdfDocumentRepository;

    @DisplayName("Тест на сохранение и получение сущности PdfDocument")
    @Test
    void saveAndFindPdfDocument() {
        PdfDocument pdfDocument = new PdfDocument(
            UUID.randomUUID(),
            "Test title",
            ".pdf",
            new Binary(new byte[] {1, 2, 3}),
            LocalDateTime.now()
        );

        pdfDocumentRepository.save(pdfDocument);

        PdfDocument foundPdf = pdfDocumentRepository.findById(pdfDocument.getId())
            .orElse(null);

        assertThat(foundPdf).isEqualTo(pdfDocument);
    }

}
