package com.example.miniLinkedin.mapping;

import org.springframework.stereotype.Component;
import com.example.miniLinkedin.dtos.LikeRequestDto;
import com.example.miniLinkedin.dtos.LikeResponseDto;
import com.example.miniLinkedin.entities.LikeEntity;

@Component
public class LikeMapperImpl implements LikeMapper {

    @Override
    public LikeResponseDto toDto(LikeEntity entity) {
        if (entity == null) return null;
        LikeResponseDto dto = new LikeResponseDto();
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreatedAt());
        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getId());
        }
        if (entity.getPublication() != null) {
            dto.setPublicationId(entity.getPublication().getId());
        }
        return dto;
    }

    @Override
    public LikeEntity toEntity(LikeRequestDto dto) {
        // La création d'un like se fait via le service, cette méthode n'est pas utilisée
        return null;
    }
}