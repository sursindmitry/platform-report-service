package com.sursindmitry.reportservice.service;

import com.sursindmitry.reportservice.domain.entity.ReportEntity;

/**
 * Сервис для управления сущностью {@link ReportEntity}.
 */
public interface ReportEntityService {
    void save(ReportEntity reportEntity);
}
