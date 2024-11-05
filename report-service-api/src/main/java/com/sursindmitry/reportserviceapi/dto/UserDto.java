package com.sursindmitry.reportserviceapi.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO пользователя.
 *
 * @param id айди пользователя
 * @param name имя пользователя
 * @param lastName фамилия пользователя
 * @param email почта пользователя
 * @param isArchived архивирован ли пользователь
 * @param created дата регистрации
 * @param updated дата обновления
 */
public record UserDto(UUID id,
                      String name,
                      String lastName,
                      String email,
                      Boolean isArchived,
                      LocalDateTime created,
                      LocalDateTime updated
) implements Serializable {
}
