package com.sursindmitry.reportservice.service.impl;

import com.sursindmitry.reportservice.domain.entity.User;
import com.sursindmitry.reportservice.exception.NotFoundException;
import com.sursindmitry.reportservice.repository.UserRepository;
import com.sursindmitry.reportservice.service.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервис общения с {@link UserRepository}.
 *
 * Реализация интерфейса {@link UserService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Сохраняет в БД пользователя.
     *
     * @param user пользователь которого нужно сохранить
     * @return {@link User}
     */
    @Override
    @Transactional
    public User save(User user) {
        log.info("Сохранение пользователя с id: {}", user.getUserId());
        return userRepository.save(user);
    }

    /**
     * Ищет в БД пользователя и возвращает его.
     *
     * @param id идентификатор пользователя
     * @return {@link User}
     */
    @Override
    @Transactional
    public User findByUserId(UUID id) {
        log.info("Поиск пользователя с id: {}", id);
        return userRepository.findByUserId(id)
            .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
    }

    /**
     * Удаляет пользователя по id.
     *
     * @param id пользователя
     */
    @Override
    @Transactional
    public void deleteByUserId(UUID id) {
        log.info("Удаление пользователя с id: {}", id);
        userRepository.deleteByUserId(id);
    }
}
