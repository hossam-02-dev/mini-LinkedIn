package mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dtos.UserRequestDto;
import dtos.UserResponseDto;
import entities.UserEntity;

@Mapper(componentModel ="spring")
public interface UserMapper {
	@Mapping( target ="password", ignore = true )
	@Mapping(target = "roleName",
    expression = "java(user.getRole().name())")
	
	UserResponseDto toDto (UserEntity user);
	
	@Mapping(target ="id", ignore = true)
	@Mapping(target ="createdAt", ignore = true)
	@Mapping(target ="profile" , ignore = true)
	@Mapping(target = "role", expression = "java(enums.Role.valueOf(dto.getRoleName()))")
	@Mapping(target ="isActive" , ignore = true)
	UserEntity toEntity (UserRequestDto dto);

}
