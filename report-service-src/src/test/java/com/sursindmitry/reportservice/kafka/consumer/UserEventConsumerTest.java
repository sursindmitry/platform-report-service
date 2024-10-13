package com.sursindmitry.reportservice.kafka.consumer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.sursindmitry.commonmodels.kafka.UserEvent;
import com.sursindmitry.reportservice.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

class UserEventConsumerTest extends BaseIntegrationTest {

    @Value(value = "${spring.kafka.topic.user-events}")
    private String topic;

    /**
     * Тесты на создание пользователя
     */
    @Test
    @DisplayName("Отправляет в топик \"user-event\" данные, консьюмер ловит и сохраняет пользователя")
    @DataSet(value = "dataset/kafka/handler/user-handler/user-created/EmptyUsers.yml")
    @ExpectedDataSet(value = "dataset/kafka/handler/user-handler/user-created/ExpectedUsers.yml",
        ignoreCols = "created"
    )
    void shouldConsumeUserEventAndSaveUser() {

        UserEvent event = jsonParserUtil.getObjectFromJson(
            "json/kafka/handler/user-handler/user-created/UserEventWithStatusCreated.json",
            UserEvent.class
        );

        assertDoesNotThrow(() -> testProducerService.send(topic, event));
    }


    /**
     * Тесты на удаление пользователя
     */

    @Test
    @DisplayName("Отправляет в топик \"user-event\" данные, консьюмер ловит и удаляет пользователя")
    @DataSet(value = "dataset/kafka/handler/user-handler/user-deleted/DeletedUsers.yml")
    @ExpectedDataSet(value = "dataset/kafka/handler/user-handler/user-deleted/EmptyUsers.yml")
    void shouldConsumeUserEventAndDeleteUser() {

        UserEvent event = jsonParserUtil.getObjectFromJson(
            "json/kafka/handler/user-handler/user-deleted/UserEventWithStatusDeleted.json",
            UserEvent.class
        );
        assertDoesNotThrow(() -> testProducerService.send(topic, event));
    }

    /**
     * Тесты на обновление пользователя
     */
    @Test
    @DisplayName("Отправляет в топик \"user-event\" данные, консьюмер ловит и обновляет пользователя")
    @DataSet(value = "dataset/kafka/handler/user-handler/user-updated/UpdatedUsers.yml")
    @ExpectedDataSet(value = "dataset/kafka/handler/user-handler/user-updated/ExpectedUsers.yml",
        ignoreCols = "updated"
    )
    void shouldConsumeUserEventAndUpdatedUser() {

        UserEvent event = jsonParserUtil.getObjectFromJson(
            "json/kafka/handler/user-handler/user-updated/UserEventWithStatusUpdated.json",
            UserEvent.class
        );

        assertDoesNotThrow(() -> testProducerService.send(topic, event));
    }
}