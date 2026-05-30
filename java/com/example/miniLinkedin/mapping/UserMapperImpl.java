package com.example.miniLinkedin.mapping;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.example.miniLinkedin.dtos.UserRequestDto;
import com.example.miniLinkedin.dtos.UserResponseDto;
import com.example.miniLinkedin.entities.UserEntity;
import com.example.miniLinkedin.enums.Role;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto toDto(UserEntity user) {
        if (user == null) return null;
        return UserResponseDto.builder()
                .id(user.getId())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .roleName(user.getRole().name())
                .createdAt(user.getCreatedAt())
                .build();
    }

    @Override
    public List<UserResponseDto> toDtoList(List<UserEntity> users) {
        if (users == null) return null;
        List<UserResponseDto> list = new ArrayList<>();
        for (UserEntity u : users) list.add(toDto(u));
        return list;
    }

    @Override
    public UserEntity toEntity(UserRequestDto dto) {
        if (dto == null) return null;
        return UserEntity.builder()
                .lastName(dto.getLastName())
                .firstName(dto.getFirstName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.valueOf(dto.getRoleName()))
                .build();
    }
}