package com.sursindmitry.reportservice.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sursindmitry.reportservice.BaseUnitTest;
import com.sursindmitry.reportservice.domain.entity.User;
import com.sursindmitry.reportservice.exception.NotFoundException;
import com.sursindmitry.reportservice.repository.UserRepository;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class UserServiceImplTest extends BaseUnitTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("Должен сохранить пользователя")
    void shouldSaveUser() {

        User user = jsonParserUtil.getObjectFromJson(
            "json/service/user-service/User.json",
            User.class
        );

        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.save(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Должен найти пользователя")
    void shouldFindUser() {
        User user = jsonParserUtil.getObjectFromJson(
            "json/service/user-service/User.json",
            User.class
        );

        UUID userId = user.getUserId();

        when(userRepository.findByUserId(any(UUID.class))).thenReturn(Optional.ofNullable(user));

        User findedUser = userService.findById(userId);

        assertEquals(user, findedUser);

        verify(userRepository, times(1)).findByUserId(any(UUID.class));
    }

    @Test
    @DisplayName("Должен не найти пользователя")
    void shouldNotFindUser() {

        UUID userId = UUID.randomUUID();

        when(userRepository.findByUserId(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.findById(userId));

        verify(userRepository, times(1)).findByUserId(any(UUID.class));

    }

    @Test
    @DisplayName("Должен удалить пользователя")
    void shouldDeleteUser() {
        UUID userId = UUID.randomUUID();

        doNothing().when(userRepository).deleteByUserId(userId);

        assertDoesNotThrow(() -> userService.deleteByUserId(userId));

        verify(userRepository, times(1)).deleteByUserId(any(UUID.class));
    }
}