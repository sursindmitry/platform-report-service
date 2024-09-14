package com.sursindmitry.reportservice.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.sursindmitry.reportservice.BaseIntegrationTest;
import com.sursindmitry.reportserviceapi.constant.ApiConstant;
import org.junit.jupiter.api.Test;

class TestControllerImplTest extends BaseIntegrationTest {

    @Test
    void shouldReturnIWork() {
        String message = webTestClient.get()
            .uri(ApiConstant.HELLO_API)
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .returnResult()
            .getResponseBody();

        assertThat(message).isEqualTo("Я работаю");
    }
}