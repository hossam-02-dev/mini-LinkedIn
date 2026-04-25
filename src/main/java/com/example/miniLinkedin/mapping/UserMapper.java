package com.example.miniLinkedin.mapping;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.miniLinkedin.dtos.UserRequestDto;
import com.example.miniLinkedin.dtos.UserResponseDto;
import com.example.miniLinkedin.entities.UserEntity;

@Mapper(componentModel ="spring")
public interface UserMapper {
	@Mapping( target ="password", ignore = true )
	@Mapping(target = "roleName",
    expression = "java(user.getRole().name())")
	
	UserResponseDto toDto (UserEntity user);
	
	List<UserResponseDto> toDtoList(List<UserEntity> users);
	
	@Mapping(target ="id", ignore = true)
	@Mapping(target ="createdAt", ignore = true)
	@Mapping(target ="profile" , ignore = true)
	@Mapping(target = "role", expression = "java(enums.Role.valueOf(dto.getRoleName()))")
	@Mapping(target ="isActive" , ignore = true)
	UserEntity toEntity (UserRequestDto dto);

}
