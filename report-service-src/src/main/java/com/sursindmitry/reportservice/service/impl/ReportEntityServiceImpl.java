package com.sursindmitry.reportservice.service.impl;

import com.sursindmitry.reportservice.domain.entity.ReportEntity;
import com.sursindmitry.reportservice.repository.ReportEntityRepository;
import com.sursindmitry.reportservice.service.ReportEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса общения с сущностью {@link ReportEntity}.
 */
@Service
@RequiredArgsConstructor
public class ReportEntityServiceImpl implements ReportEntityService {

    private final ReportEntityRepository reportEntityRepository;

    /**
     * Сохранение сущности {@link ReportEntity}.
     *
     * @param reportEntity сущность для сохранения
     */
    @Override
    @Transactional
    public void save(ReportEntity reportEntity) {
        reportEntityRepository.save(reportEntity);
    }
}
