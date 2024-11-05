package com.sursindmitry.reportservice.controller;

import com.sursindmitry.reportservice.service.UserService;
import com.sursindmitry.reportserviceapi.controller.UserController;
import com.sursindmitry.reportserviceapi.dto.UserDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * Реализация {@link UserController}.
 */
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    /**
     * Ищет пользователя по id.
     *
     * @param id пользователя
     * @return {@link UserDto}
     */
    @Override
    public UserDto findUserById(UUID id) {
        return userService.findUserDtoByUserId(id);
    }
}
