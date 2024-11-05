package com.sursindmitry.reportservice.service;

import com.sursindmitry.reportservice.domain.entity.User;
import com.sursindmitry.reportserviceapi.dto.UserDto;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для управления сущностью {@link User}.
 */
public interface UserService {
    User save(User user);

    void deleteByUserId(UUID id);

    User findByUserId(UUID id);

    UserDto findUserDtoByUserId(UUID id);

    List<User> findAll();
}
