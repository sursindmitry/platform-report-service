package com.sursindmitry.reportservice.service.impl;

import com.sursindmitry.reportservice.service.ReportFactory;
import com.sursindmitry.reportservice.service.ReportGenerator;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Реализация фабрики отчётов {@link ReportFactory}.
 */
@Component
@RequiredArgsConstructor
public class ReportFactoryImpl implements ReportFactory {

    // TODO: Добавить генерацию EXCEL
    private final Map<String, ReportGenerator> generators;

    @Override
    public ReportGenerator getReportGenerator(String type) {

        return generators.get(type);
    }
}
