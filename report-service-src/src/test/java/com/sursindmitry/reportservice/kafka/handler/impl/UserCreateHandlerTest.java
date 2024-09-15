package com.sursindmitry.reportservice.kafka.handler.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.sursindmitry.commonmodels.kafka.UserEvent;
import com.sursindmitry.reportservice.BaseIntegrationTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserCreateHandlerTest extends BaseIntegrationTest {

    @Autowired
    UserCreateHandler userCreateHandler;

    @Test
    @DisplayName("Должен вернуть true, если status == CREATED")
    @SneakyThrows
    void shouldReturnTrueWhenStatusIsCREATED() {

        UserEvent userEvent = jsonParserUtil.getObjectFromJson(
            "json/kafka/handler/user-handler/user-created/UserEventWithStatusCreated.json",
            UserEvent.class
        );

        boolean isHandle = userCreateHandler.isHandle(userEvent);

        assertTrue(isHandle);
    }

    @Test
    @DisplayName("Должен вернуть false, если status == DELETED")
    @SneakyThrows
    void shouldReturnFalseWhenStatusIsDELETED() {

        UserEvent userEvent = jsonParserUtil.getObjectFromJson(
            "json/kafka/handler/user-handler/user-created/UserEventWithStatusDeleted.json",
            UserEvent.class
        );

        boolean isHandle = userCreateHandler.isHandle(userEvent);

        assertFalse(isHandle);
    }

    @Test
    @DisplayName("Должен вернуть false, если status == UPDATED")
    @SneakyThrows
    void shouldReturnFalseWhenStatusIsUPDATED() {

        UserEvent userEvent = jsonParserUtil.getObjectFromJson(
            "json/kafka/handler/user-handler/user-created/UserEventWithStatusUpdated.json",
            UserEvent.class
        );

        boolean isHandle = userCreateHandler.isHandle(userEvent);

        assertFalse(isHandle);
    }

    @Test
    @DisplayName("Должен сохранить пользователя в таблицу users")
    @DataSet(value = "dataset/kafka/handler/user-handler/user-created/EmptyUsers.yml",
        cleanAfter = true,
        cleanBefore = true
    )
    @ExpectedDataSet(value = "dataset/kafka/handler/user-handler/user-created/ExpectedUsers.yml",
        ignoreCols = "created"
    )
    void shouldSaveUser() {

        UserEvent userEvent = jsonParserUtil.getObjectFromJson(
            "json/kafka/handler/user-handler/user-created/UserEventWithStatusCreated.json",
            UserEvent.class
        );

        assertDoesNotThrow(() -> userCreateHandler.handle(userEvent));
    }
}