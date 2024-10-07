package com.sursindmitry.reportservice.kafka.handler.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.sursindmitry.commonmodels.kafka.UserEvent;
import com.sursindmitry.reportservice.BaseIntegrationTest;
import com.sursindmitry.reportservice.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserDeletedHandlerTest extends BaseIntegrationTest {

    @Autowired
    UserDeletedHandler userDeletedHandler;

    @Test
    @DisplayName("Должен вернуть true, если status == DELETED")
    void shouldReturnTrueWhenStatusIsCREATED() {

        UserEvent userEvent = jsonParserUtil.getObjectFromJson(
            "json/kafka/handler/user-handler/user-deleted/UserEventWithStatusDeleted.json",
            UserEvent.class
        );

        boolean isHandle = userDeletedHandler.isHandle(userEvent);

        assertTrue(isHandle);
    }

    @Test
    @DisplayName("Должен вернуть false, если status == CREATED")
    void shouldReturnFalseWhenStatusIsDELETED() {

        UserEvent userEvent = jsonParserUtil.getObjectFromJson(
            "json/kafka/handler/user-handler/user-deleted/UserEventWithStatusCreated.json",
            UserEvent.class
        );

        boolean isHandle = userDeletedHandler.isHandle(userEvent);

        assertFalse(isHandle);
    }

    @Test
    @DisplayName("Должен вернуть false, если status == UPDATED")
    void shouldReturnFalseWhenStatusIsUPDATED() {

        UserEvent userEvent = jsonParserUtil.getObjectFromJson(
            "json/kafka/handler/user-handler/user-deleted/UserEventWithStatusUpdated.json",
            UserEvent.class
        );

        boolean isHandle = userDeletedHandler.isHandle(userEvent);

        assertFalse(isHandle);
    }

    @Test
    @DisplayName("Должен удалить пользователя и таблицы users")
    @DataSet(value = "dataset/kafka/handler/user-handler/user-deleted/DeletedUsers.yml",
        cleanAfter = true,
        cleanBefore = true
    )
    @ExpectedDataSet(value = "dataset/kafka/handler/user-handler/user-deleted/EmptyUsers.yml")
    void shouldDeleteUser() {

        UserEvent userEvent = jsonParserUtil.getObjectFromJson(
            "json/kafka/handler/user-handler/user-deleted/UserEventWithStatusDeleted.json",
            UserEvent.class
        );

        assertDoesNotThrow(() -> userDeletedHandler.handle(userEvent));
    }

    @Test
    @DisplayName("Должен не найти пользователя в таблице users")
    @DataSet(value = "dataset/kafka/handler/user-handler/user-deleted/EmptyUsers.yml",
        cleanAfter = true,
        cleanBefore = true
    )
    @ExpectedDataSet(value = "dataset/kafka/handler/user-handler/user-deleted/EmptyUsers.yml")
    void shouldNotFoundUser() {

        UserEvent userEvent = jsonParserUtil.getObjectFromJson(
            "json/kafka/handler/user-handler/user-deleted/UserEventWithStatusDeleted.json",
            UserEvent.class
        );

        assertThrows(NotFoundException.class, () -> userDeletedHandler.handle(userEvent));
    }
}