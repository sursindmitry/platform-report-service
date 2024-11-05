package com.sursindmitry.reportserviceapi.controller;

import static com.sursindmitry.reportserviceapi.constant.ApiConstant.USERS_API;

import com.sursindmitry.reportserviceapi.dto.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер пользователей.
 */
@Tag(
    name = "Контроллер зарегистрированных пользователей",
    description = "Контроллер позволяет получать пользователей"
)
@RequestMapping(USERS_API)
public interface UserController {

    @GetMapping
    UserDto findUserById(@RequestParam UUID id);

}
