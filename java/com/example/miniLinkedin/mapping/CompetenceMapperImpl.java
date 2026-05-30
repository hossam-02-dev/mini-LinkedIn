package com.example.miniLinkedin.mapping;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.example.miniLinkedin.dtos.CompetenceRequestDto;
import com.example.miniLinkedin.dtos.CompetenceResponseDto;
import com.example.miniLinkedin.entities.CompetenceEntity;

@Component
public class CompetenceMapperImpl implements CompetenceMapper {

    @Override
    public CompetenceResponseDto toDto(CompetenceEntity entity) {
        if (entity == null) return null;
        CompetenceResponseDto dto = CompetenceResponseDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .niveau(entity.getNiveau() != null ? entity.getNiveau().name() : null)
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .build();
        return dto;
    }

    @Override
    public List<CompetenceResponseDto> toDtoList(List<CompetenceEntity> entities) {
        if (entities == null) return null;
        List<CompetenceResponseDto> list = new ArrayList<>();
        for (CompetenceEntity e : entities) list.add(toDto(e));
        return list;
    }

    @Override
    public CompetenceEntity toEntity(CompetenceRequestDto dto) {
        if (dto == null) return null;
        CompetenceEntity entity = new CompetenceEntity();
        entity.setNom(dto.getNom());
        entity.setNiveau(com.example.miniLinkedin.enums.Niveau.valueOf(dto.getNiveau()));
        // user, id seront ignorés
        return entity;
    }
}