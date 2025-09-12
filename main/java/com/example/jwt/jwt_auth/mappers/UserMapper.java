package com.example.jwt.jwt_auth.mappers;

import com.example.jwt.jwt_auth.dtos.DtoUser;
import com.example.jwt.jwt_auth.dtos.DtoUserIU;
import com.example.jwt.jwt_auth.entities.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    DtoUser toDto(User user);

    @Mapping(target = "id", ignore = true)  // Insert'te ID generate edilsin
    User toEntity(DtoUserIU dto);

    @Mapping(target = "id", ignore = true)  // Insert'te ID generate edilsin
    @Mapping(target = "password", ignore = true)  // Password mapping'i service'te yapılıyor
    User toEntity(DtoUser dto);

    @Mapping(target = "password", ignore = true)  // Password DtoUser'da yok
    DtoUserIU toIU(DtoUser dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)  // ID değişmesin
    @Mapping(target = "password", ignore = true)  // Password ayrıca handle ediliyor
    void updateEntityFromIU(DtoUserIU dto, @MappingTarget User user);

    List<DtoUser> toDtoList(List<User> users);
    List<User> toEntityList(List<DtoUser> dtos);
}
