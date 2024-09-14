package com.sursindmitry.reportservice.initializers;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class PostgresInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Container
    public static final PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>("postgres:15.1")
        .withDatabaseName("test")
        .withPassword("test")
        .withPassword("test")
        .withReuse(false);
    public static final String DATASOURCE_URL = "spring.datasource.url=";
    public static final String DATASOURCE_USERNAME = "spring.datasource.username=";
    public static final String DATASOURCE_PASSWORD = "spring.datasource.password=";


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        CONTAINER.start();
        TestPropertyValues.of(
            DATASOURCE_URL + CONTAINER.getJdbcUrl(),
            DATASOURCE_USERNAME + CONTAINER.getUsername(),
            DATASOURCE_PASSWORD + CONTAINER.getPassword()
        ).applyTo(applicationContext.getEnvironment());
    }
}
