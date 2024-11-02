package com.sursindmitry.reportservice.service;

/**
 * Фабрика отчётов.
 */
public interface ReportFactory {
    ReportGenerator getReportGenerator(String type);
}
