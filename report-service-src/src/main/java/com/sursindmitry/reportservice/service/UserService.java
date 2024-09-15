package com.sursindmitry.reportservice.service;

import com.sursindmitry.reportservice.domain.entity.User;

/**
 * Сервис для управления сущностью {@link User}.
 */
public interface UserService {
    void save(User user);
}
