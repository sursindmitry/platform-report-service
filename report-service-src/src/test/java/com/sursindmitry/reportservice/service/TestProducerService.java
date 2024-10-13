package com.sursindmitry.reportservice.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.Semaphore;

@Aspect
@Service
@Getter
@RequiredArgsConstructor
public class TestProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final Semaphore semaphore = new Semaphore(0);

    @SneakyThrows
    public void send(String topic, Object event) {
        kafkaTemplate.send(topic, event);
        semaphore.acquire();
    }

    @SneakyThrows
    @After(value = "execution(public * com.sursindmitry.reportservice.kafka.consumer.EventConsumer.handle(..))")
    public void beforeReceiveMessage() {
        semaphore.release(1);
    }
}
