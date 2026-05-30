package com.example.miniLinkedin.mapping;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.example.miniLinkedin.dtos.FormationRequestDto;
import com.example.miniLinkedin.dtos.FormationResponseDto;
import com.example.miniLinkedin.entities.FormationEntity;

@Component
public class FormationMapperImpl implements FormationMapper {

    @Override
    public FormationResponseDto toDto(FormationEntity formation) {
        if (formation == null) return null;
        FormationResponseDto dto = new FormationResponseDto();
        dto.setId(formation.getId());                       // indispensable
        dto.setDiplome(formation.getDiplome());
        dto.setEtablissement(formation.getEtablissement());
        dto.setDomaine(formation.getDomaine());
        dto.setEnCours(formation.getEnCours());
        dto.setDateDebut(formation.getDateDebut());
        dto.setDateFin(formation.getDateFin());
        if (formation.getProfil() != null) {
            dto.setProfilId(formation.getProfil().getId());
        }
        return dto;
    }

    @Override
    public List<FormationResponseDto> toDtoList(List<FormationEntity> entities) {
        if (entities == null) return null;
        List<FormationResponseDto> list = new ArrayList<>();
        for (FormationEntity e : entities) {
            list.add(toDto(e));
        }
        return list;
    }

    @Override
    public FormationEntity toEntity(FormationRequestDto dto) {
        if (dto == null) return null;
        FormationEntity entity = new FormationEntity();
        entity.setDiplome(dto.getDiplome());
        entity.setEtablissement(dto.getEtablissement());
        entity.setDomaine(dto.getDomaine());
        entity.setEnCours(dto.getEnCours());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        return entity;
    }
}