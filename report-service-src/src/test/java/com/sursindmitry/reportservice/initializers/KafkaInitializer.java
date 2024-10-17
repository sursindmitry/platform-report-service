package com.sursindmitry.reportservice.initializers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.ConfluentKafkaContainer;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class KafkaInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Container
    public static final ConfluentKafkaContainer CONTAINER = new ConfluentKafkaContainer(
        DockerImageName.parse("confluentinc/cp-kafka:7.4.0"))
        .withExposedPorts(9092)
        .withReuse(false);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        CONTAINER.start();
        System.setProperty("spring.kafka.bootstrap-servers", CONTAINER.getBootstrapServers());
    }
}
