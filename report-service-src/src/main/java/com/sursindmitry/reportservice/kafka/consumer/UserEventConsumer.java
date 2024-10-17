package com.sursindmitry.reportservice.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sursindmitry.commonmodels.kafka.UserEvent;
import com.sursindmitry.reportservice.kafka.handler.UserHandler;
import java.io.IOException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Консьюмер слушающий топик user-events.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class UserEventConsumer implements EventConsumer<ConsumerRecord<String, byte[]>> {

    private final Set<UserHandler<UserEvent>> eventHandlers;

    private final ObjectMapper objectMapper;

    /**
     * Получает евент, десериализует его и идёт по хендлерам {@link UserHandler}.
     *
     * @param event евент кафки из топика user-events
     */
    @Override
    @KafkaListener(
        topics = "${spring.kafka.topic.user-events}",
        groupId = "${spring.kafka.consumer.group-id}"
    )
    public void handle(ConsumerRecord<String, byte[]> event) {

        log.info("Обрабатывается сообщение из топика: {}, оффсет: {}",
            event.topic(),
            event.offset()
        );

        UserEvent userEvent;

        try {
            userEvent = objectMapper.readValue(event.value(), UserEvent.class);
        } catch (IOException ex) {
            log.error("Ошибка десериализации сообщения: {}", ex.getMessage());
            return;
        }

        eventHandlers.stream()
            .filter(handler -> handler.isHandle(userEvent))
            .forEach(handler -> handler.handle(userEvent));
    }
}
