package com.sursindmitry.reportservice.initializers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class MongoInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Container
    public static final MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer(
        DockerImageName.parse("mongo:latest")
    );

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        MONGO_DB_CONTAINER.start();
        System.setProperty("spring.data.mongodb.uri", MONGO_DB_CONTAINER.getReplicaSetUrl());
    }
}
