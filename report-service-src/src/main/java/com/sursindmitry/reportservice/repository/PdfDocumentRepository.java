package com.sursindmitry.reportservice.repository;

import com.sursindmitry.reportservice.domain.entity.PdfDocument;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью {@link PdfDocument}.
 */
@Repository
public interface PdfDocumentRepository extends MongoRepository<PdfDocument, UUID> {

}
