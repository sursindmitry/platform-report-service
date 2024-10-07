package com.sursindmitry.reportservice.kafka.handler.impl;

import com.sursindmitry.commonmodels.kafka.UserEvent;
import com.sursindmitry.commonmodels.kafka.enums.UserEventStatus;
import com.sursindmitry.reportservice.domain.entity.User;
import com.sursindmitry.reportservice.kafka.handler.UserHandler;
import com.sursindmitry.reportservice.mapper.UserMapper;
import com.sursindmitry.reportservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Принимает евент UserEvent, проверяет, является ли статус евента {@link UserEventStatus#CREATED}
 * и сохраняет его в таблице users.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserCreateHandler implements UserHandler<UserEvent> {

    private final UserMapper userMapper;
    private final UserService userService;

    /**
     * Проверяет, является ли статус евента {@link UserEventStatus#CREATED}.
     *
     * @param userEvent евент пришедший из консьюмера
     * @return true, если статус евента равен {@link UserEventStatus#CREATED}
     */
    @Override
    public boolean isHandle(UserEvent userEvent) {
        log.debug("Проверка статуса у евента с id: {}", userEvent.id());
        return userEvent.status() == UserEventStatus.CREATED;
    }

    /**
     * Сохраняет евент в таблицу users.
     *
     * @param userEvent евент прошедший проверку на true {@link #isHandle(UserEvent)}
     */
    @Override
    public void handle(UserEvent userEvent) {

        User user = userMapper.toUser(userEvent);

        userService.save(user);
    }
}
