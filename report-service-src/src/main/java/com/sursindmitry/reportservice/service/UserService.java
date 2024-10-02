package com.sursindmitry.reportservice.service;

import com.sursindmitry.reportservice.domain.entity.User;
import java.util.UUID;

/**
 * Сервис для управления сущностью {@link User}.
 */
public interface UserService {
    User save(User user);

    User findByUserId(UUID id);
}
