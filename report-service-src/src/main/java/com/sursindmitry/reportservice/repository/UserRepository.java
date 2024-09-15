package com.sursindmitry.reportservice.repository;

import com.sursindmitry.reportservice.domain.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностями {@link User}.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
