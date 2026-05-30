package com.example.miniLinkedin.mapping;

import org.springframework.stereotype.Component;
import com.example.miniLinkedin.dtos.ProfilRequestDto;
import com.example.miniLinkedin.dtos.ProfilResponseDto;
import com.example.miniLinkedin.entities.ProfilEntity;
import com.example.miniLinkedin.entities.UserEntity;

@Component
public class ProfilMapperImpl implements ProfilMapper {

    @Override
    public ProfilResponseDto toDto(ProfilEntity profil) {
        if (profil == null) return null;
        ProfilResponseDto dto = new ProfilResponseDto();
        dto.setId(profil.getId());
        dto.setName(profil.getName());
        dto.setVille(profil.getVille());
        dto.setEtablissement(profil.getEtablissement());
        dto.setBio(profil.getBio());
        dto.setSiteWeb(profil.getSiteWeb());
        dto.setPhotoUrl(profil.getPhotoUrl());
        dto.setDateNaissance(profil.getDateNaissance());
        if (profil.getUser() != null) {
            dto.setUserId(profil.getUser().getId());
            dto.setNomComplet(mapNomComplet(profil.getUser()));
        }
        return dto;
    }

    @Override
    public ProfilEntity toEntity(ProfilRequestDto dto) {
        if (dto == null) return null;
        ProfilEntity entity = new ProfilEntity();
        entity.setName(dto.getName());
        entity.setVille(dto.getVille());
        entity.setEtablissement(dto.getEtablissement());
        entity.setBio(dto.getBio());
        entity.setSiteWeb(dto.getSiteWeb());
        entity.setPhotoUrl(dto.getPhotoUrl());
        entity.setDateNaissance(dto.getDateNaissance());
        return entity;
    }

    // Méthode utilitaire pour le nom complet (identique à celle de l'interface)
    public String mapNomComplet(UserEntity user) {
        if (user == null) return null;
        String prenom = user.getFirstName() != null ? user.getFirstName() : "";
        String nom = user.getLastName() != null ? user.getLastName() : "";
        return (prenom + " " + nom).trim();
    }
}