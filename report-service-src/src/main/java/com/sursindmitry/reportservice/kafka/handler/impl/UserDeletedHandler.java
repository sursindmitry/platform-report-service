package com.sursindmitry.reportservice.kafka.handler.impl;

import com.sursindmitry.commonmodels.kafka.UserEvent;
import com.sursindmitry.commonmodels.kafka.enums.UserEventStatus;
import com.sursindmitry.reportservice.kafka.handler.UserHandler;
import com.sursindmitry.reportservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Принимает евент UserEvent, проверяет, является ли статус евента {@link UserEventStatus#DELETED}
 * и удаляет пользователя по id.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserDeletedHandler implements UserHandler<UserEvent> {

    private final UserService userService;

    /**
     * Проверяет, является ли статус евента {@link UserEventStatus#DELETED}.
     *
     * @param userEvent евент пришедший из консьюмера
     * @return true, если статус евента равен {@link UserEventStatus#DELETED}
     */
    @Override
    public boolean isHandle(UserEvent userEvent) {
        log.debug("Проверка статуса у евента с id: {}", userEvent.id());
        return userEvent.status() == UserEventStatus.DELETED;
    }

    /**
     * Удаляет пользователя по id.
     *
     * @param userEvent евент прошедший проверку на true {@link #isHandle(UserEvent)}
     */
    @Override
    public void handle(UserEvent userEvent) {

        userService.findByUserId(userEvent.userId());
        userService.deleteByUserId(userEvent.userId());
    }
}
