package com.sursindmitry.reportservice.repository;

import com.sursindmitry.reportservice.domain.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью {@link User}.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Поиск пользователя по {@link User#userId}.
     *
     * @param id идентификатор пользователя
     * @return {@link User}
     */
    Optional<User> findByUserId(UUID id);

    /**
     * Удаление пользователя по {@link User#userId}.
     *
     * @param id пользователя
     */
    void deleteByUserId(UUID id);
}
