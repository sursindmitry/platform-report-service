package com.sursindmitry.reportservice.controller;

import static com.sursindmitry.reportserviceapi.constant.ApiConstant.PDF_REPORT_API;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sursindmitry.reportservice.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

class UserReportControllerImplTest extends BaseIntegrationTest {

    @Test
    void shouldGenerateAndSaveReport() {
        webTestClient.get()
            .uri(uriBuilder -> uriBuilder.path(PDF_REPORT_API)
                .queryParam("type", "pdf")
                .build()
            )
            .exchange()
            .expectStatus().isOk()
            .expectHeader().value(HttpHeaders.CONTENT_DISPOSITION, value -> {
                assertTrue(value.startsWith("form-data; name=\"attachment\"; filename=\"user_report"));
            })
            .expectBody(byte[].class)
            .consumeWith(response -> {
                byte[] reportContent = response.getResponseBody();

                assertNotNull(reportContent);
                assertTrue(reportContent.length > 0);
                assertTrue(new String(reportContent).startsWith("%PDF"));
            });
    }

}