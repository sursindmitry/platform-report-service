package com.sursindmitry.reportservice.mapper;

import com.sursindmitry.commonmodels.kafka.UserEvent;
import com.sursindmitry.reportservice.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Маппер сущности {@link User}.
 */
@Mapper
public interface UserMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "archived", constant = "false")
    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updated", ignore = true)
    User toUser(UserEvent userEvent);
}
