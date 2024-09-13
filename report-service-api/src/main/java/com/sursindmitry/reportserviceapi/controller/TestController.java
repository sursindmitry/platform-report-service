package com.sursindmitry.reportserviceapi.controller;

import static com.sursindmitry.reportserviceapi.constant.ApiConstant.HELLO_API;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TestController.
 * <p>
 * Контроллер для тестирования
 * </p>
 */
@Tag(name = "Тестовый контроллер", description = "Тестовый контроллер")
@RequestMapping(HELLO_API)
public interface TestController {

    @Operation(summary = "Тестовый ендпоинт", description = "Пример работы")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Возвращает строку Я работаю")
    })
    @GetMapping
    String helloController();
}
