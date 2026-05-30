package com.example.miniLinkedin.mapping;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.example.miniLinkedin.dtos.ProjetRequestDto;
import com.example.miniLinkedin.dtos.ProjetResponseDto;
import com.example.miniLinkedin.entities.ProjetEntity;
import com.example.miniLinkedin.entities.UserEntity;

@Component
public class ProjetMapperImpl implements ProjetMapper {

    @Override
    public ProjetResponseDto toDto(ProjetEntity projet) {
        if (projet == null) return null;
        ProjetResponseDto dto = new ProjetResponseDto();
        dto.setId(projet.getId());
        dto.setTitre(projet.getTitre());
        dto.setDescription(projet.getDescription());
        dto.setTechnologies(projet.getTechnologies());
        dto.setLienGithub(projet.getLienGithub());
        dto.setLienDemo(projet.getLienDemo());
        dto.setImageUrl(projet.getImageUrl());
        // Conversion supprimée : on garde LocalDateTime
        dto.setDateCreation(projet.getDateCreation());
        dto.setDateMaj(projet.getDateMaj());
        if (projet.getUser() != null) {
            dto.setAuteurId(projet.getUser().getId());
            dto.setNomAuteur(mapNomAuteur(projet.getUser()));
        }
        return dto;
    }

    @Override
    public ProjetEntity toEntity(ProjetRequestDto requestDto) {
        if (requestDto == null) return null;
        ProjetEntity entity = new ProjetEntity();
        entity.setTitre(requestDto.getTitre());
        entity.setDescription(requestDto.getDescription());
        entity.setTechnologies(requestDto.getTechnologies());
        entity.setLienGithub(requestDto.getLienGithub());
        entity.setLienDemo(requestDto.getLienDemo());
        entity.setImageUrl(requestDto.getImageUrl());
        return entity;
    }

    @Override
    public List<ProjetResponseDto> toDtoList(List<ProjetEntity> entities) {
        if (entities == null) return null;
        List<ProjetResponseDto> list = new ArrayList<>(entities.size());
        for (ProjetEntity entity : entities) {
            list.add(toDto(entity));
        }
        return list;
    }

    public String mapNomAuteur(UserEntity user) {
        if (user == null) return null;
        String prenom = user.getFirstName() != null ? user.getFirstName() : "";
        String nom = user.getLastName() != null ? user.getLastName() : "";
        return (prenom + " " + nom).trim();
    }
}