package com.sursindmitry.reportservice.repository;

import com.sursindmitry.reportservice.domain.entity.ReportEntity;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью {@link ReportEntity}.
 */
@Repository
public interface ReportEntityRepository extends MongoRepository<ReportEntity, UUID> {

}
