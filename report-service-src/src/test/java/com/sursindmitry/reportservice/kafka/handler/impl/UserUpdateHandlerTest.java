package com.sursindmitry.reportservice.kafka.handler.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.sursindmitry.commonmodels.kafka.UserEvent;
import com.sursindmitry.reportservice.BaseIntegrationTest;
import com.sursindmitry.reportservice.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserUpdateHandlerTest extends BaseIntegrationTest {

    @Autowired
    UserUpdateHandler userUpdateHandler;


    @Test
    @DisplayName("Должен вернуть true, если status == UPDATED")
    void shouldReturnTrueWhenStatusIsUpdated() {

        UserEvent userEvent = jsonParserUtil.getObjectFromJson(
            "json/kafka/handler/user-handler/user-updated/UserEventWithStatusUpdated.json",
            UserEvent.class
        );

        boolean isHandle = userUpdateHandler.isHandle(userEvent);

        assertTrue(isHandle);
    }

    @Test
    @DisplayName("Должен вернуть false, если status == CREATED")
    void shouldReturnTrueWhenStatusCreated() {

        UserEvent userEvent = jsonParserUtil.getObjectFromJson(
            "json/kafka/handler/user-handler/user-updated/UserEventWithStatusCreated.json",
            UserEvent.class
        );

        boolean isHandle = userUpdateHandler.isHandle(userEvent);

        assertFalse(isHandle);
    }

    @Test
    @DisplayName("Должен вернуть false, если status == DELETED")
    void shouldReturnTrueWhenStatusDeleted() {

        UserEvent userEvent = jsonParserUtil.getObjectFromJson(
            "json/kafka/handler/user-handler/user-updated/UserEventWithStatusDeleted.json",
            UserEvent.class
        );

        boolean isHandle = userUpdateHandler.isHandle(userEvent);

        assertFalse(isHandle);
    }

    @Test
    @DisplayName("Должен обновить пользователя в таблице users")
    @DataSet(value = "dataset/kafka/handler/user-handler/user-updated/UpdatedUsers.yml",
        cleanBefore = true,
        cleanAfter = true
    )
    @ExpectedDataSet(value = "dataset/kafka/handler/user-handler/user-updated/ExpectedUsers.yml",
        ignoreCols = "updated"
    )
    void shouldUpdatedUser() {

        UserEvent userEvent = jsonParserUtil.getObjectFromJson(
            "json/kafka/handler/user-handler/user-updated/UserEventWithStatusUpdated.json",
            UserEvent.class
        );

        assertDoesNotThrow(() -> userUpdateHandler.handle(userEvent));
    }

    @Test
    @DisplayName("Должен не найти пользователя в таблице users и выкинуть NotFoundException")
    @DataSet(value = "dataset/kafka/handler/user-handler/user-updated/EmptyUsers.yml",
        cleanBefore = true,
        cleanAfter = true
    )
    @ExpectedDataSet(value = "dataset/kafka/handler/user-handler/user-updated/EmptyUsers.yml")
    void shouldThrowNotFoundException() {

        UserEvent userEvent = jsonParserUtil.getObjectFromJson(
            "json/kafka/handler/user-handler/user-updated/UserEventWithStatusUpdated.json",
            UserEvent.class
        );

        assertThrows(NotFoundException.class, () -> userUpdateHandler.handle(userEvent));
    }

}