package com.example.miniLinkedin.mapping;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.example.miniLinkedin.dtos.CommentaireRequestDto;
import com.example.miniLinkedin.dtos.CommentaireResponseDto;
import com.example.miniLinkedin.entities.CommentaireEntity;
import com.example.miniLinkedin.entities.UserEntity;

@Component
public class CommentaireMapperImpl implements CommentaireMapper {

    @Override
    public CommentaireEntity toEntity(CommentaireRequestDto dto) {
        if (dto == null) return null;
        CommentaireEntity entity = new CommentaireEntity();
        entity.setTexte(dto.getTexte());
        // Les autres champs (auteur, publication, date, id) seront ignorés comme demandé
        return entity;
    }

    @Override
    public CommentaireResponseDto toDto(CommentaireEntity entity) {
        if (entity == null) return null;
        CommentaireResponseDto dto = CommentaireResponseDto.builder()
                .id(entity.getId())
                .texte(entity.getTexte())
                .date(entity.getDate())
                .build();
        if (entity.getAuteur() != null) {
            dto.setNomAuteur(entity.getAuteur().getLastName());
            dto.setAuteurId(entity.getAuteur().getId());
        }
        return dto;
    }

    @Override
    public List<CommentaireResponseDto> toDtoList(List<CommentaireEntity> entities) {
        if (entities == null) return null;
        List<CommentaireResponseDto> list = new ArrayList<>();
        for (CommentaireEntity e : entities) list.add(toDto(e));
        return list;
    }
}