package com.sursindmitry.reportservice.service.impl;

import com.sursindmitry.reportservice.domain.entity.User;
import com.sursindmitry.reportservice.repository.UserRepository;
import com.sursindmitry.reportservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация интерфейса {@link UserService}.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Сохраняет в БД пользователя.
     *
     * @param user пользователь которого нужно сохранить
     */
    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
}
