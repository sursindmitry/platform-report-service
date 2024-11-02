package com.sursindmitry.reportserviceapi.controller;

import static com.sursindmitry.reportserviceapi.constant.ApiConstant.PDF_REPORT_API;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер генерации отчётов пользователей.
 */
@Tag(
    name = "Контроллер PDF документов зарегистрированных пользователей",
    description = "Контроллер позволяет получать PDF файлы"
)
@RequestMapping(PDF_REPORT_API)
public interface UserReportController {

    @GetMapping
    ResponseEntity<byte[]> generatePdf(@RequestParam String type);

}
