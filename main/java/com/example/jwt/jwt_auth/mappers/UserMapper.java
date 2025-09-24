package com.example.jwt.jwt_auth.mappers;

import com.example.jwt.jwt_auth.dtos.DtoUser;
import com.example.jwt.jwt_auth.dtos.DtoUserIU;
import com.example.jwt.jwt_auth.dtos.auth.DtoAuthRequest;
import com.example.jwt.jwt_auth.entities.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    DtoUser toDto(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(DtoUserIU dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(DtoUser dto);

    @Mapping(target = "password", ignore = true)
    DtoUserIU toIU(DtoUser dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "projects", ignore = true)
    User toEntity(DtoAuthRequest dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "projects", ignore = true)
    DtoUserIU authRequestToUserIU(DtoAuthRequest dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateEntityFromIU(DtoUserIU dto, @MappingTarget User user);

    List<DtoUser> toDtoList(List<User> users);
    List<User> toEntityList(List<DtoUser> dtos);
}
