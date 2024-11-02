package com.sursindmitry.reportservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger configuration.
 *
 * <p>
 * Класс конфигурации свагера
 * </p>
 */
@Configuration
public class SwaggerConfig {

    /**
     * Настройка OpenAPI.
     *
     * @return {@link OpenAPI}
     */
    @Bean
    public OpenAPI apiInfo() {

        Info apiInfo = new Info()
            .title("Report Service REST API")
            .description("API for report-service. Данный сервис служит для генерации отчётов.")
            .version("0.0.1");
        final Server localServer = new Server().description("Локальное окружение")
            .url("http://localhost:9292");

        return new OpenAPI()
            .info(apiInfo)
            .servers(List.of(localServer));
    }
}
