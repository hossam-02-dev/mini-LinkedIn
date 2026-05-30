package com.example.miniLinkedin.mapping;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.example.miniLinkedin.dtos.PublicationRequestDto;
import com.example.miniLinkedin.dtos.PublicationResponseDto;
import com.example.miniLinkedin.entities.PublicationEntity;

@Component
public class PublicationMapperImpl implements PublicationMapper {

    @Override
    public PublicationResponseDto toDto(PublicationEntity entity) {
        if (entity == null) return null;
        PublicationResponseDto dto = new PublicationResponseDto();
        dto.setId(entity.getId());
        dto.setContenu(entity.getContenu());
        dto.setImageUrl(entity.getImageUrl());
        dto.setDatePublication(entity.getDatePublication());
        dto.setDateMaj(entity.getDateMaj());
        if (entity.getAuteur() != null) {
            dto.setAuteurId(entity.getAuteur().getId());
            dto.setNomAuteur(entity.getAuteur().getFirstName() + " " + entity.getAuteur().getLastName());
            if (entity.getAuteur().getProfile() != null) {
                dto.setPhotoProfil(entity.getAuteur().getProfile().getPhotoUrl());
            }
        }
        return dto;
    }

    @Override
    public List<PublicationResponseDto> toDtoList(List<PublicationEntity> entities) {
        if (entities == null) return null;
        List<PublicationResponseDto> list = new ArrayList<>(entities.size());
        for (PublicationEntity e : entities) {
            list.add(toDto(e));
        }
        return list;
    }

    @Override
    public PublicationEntity toEntity(PublicationRequestDto dto) {
        if (dto == null) return null;
        PublicationEntity entity = new PublicationEntity();
        entity.setContenu(dto.getContenu());
        entity.setImageUrl(dto.getImageUrl());
        return entity;
    }
}