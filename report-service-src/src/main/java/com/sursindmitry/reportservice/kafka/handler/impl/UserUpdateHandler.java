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
 * Принимает евент UserEvent, проверяет, является ли статус евента {@link UserEventStatus#UPDATED}
 * и обновляет пользователя в таблице users.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserUpdateHandler implements UserHandler<UserEvent> {

    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Проверяет, является ли статус евента {@link UserEventStatus#UPDATED}.
     *
     * @param userEvent евент пришедший из консьюмера
     * @return true, если статус евента равен {@link UserEventStatus#UPDATED}
     */
    @Override
    public boolean isHandle(UserEvent userEvent) {
        log.debug("Проверка статуса у евента с id: {}", userEvent.id());
        return userEvent.status() == UserEventStatus.UPDATED;
    }

    /**
     * Ищет пользователя в таблице users и обновляет пользователя.
     *
     * @param userEvent евент прошедший проверку на true {@link #isHandle(UserEvent)}
     */
    @Override
    public void handle(UserEvent userEvent) {

        User user = userService.findByUserId(userEvent.userId());

        User updatedUser = userMapper.toUpdateUser(user, userEvent);

        userService.save(updatedUser);
    }
}
