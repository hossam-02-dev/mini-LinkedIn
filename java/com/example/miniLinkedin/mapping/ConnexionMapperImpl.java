package com.example.miniLinkedin.mapping;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.example.miniLinkedin.dtos.ConnexionRequestDto;
import com.example.miniLinkedin.dtos.ConnexionResponseDto;
import com.example.miniLinkedin.entities.ConnexionEntity;

@Component
public class ConnexionMapperImpl implements ConnexionMapper {

    @Override
    public ConnexionResponseDto toDto(ConnexionEntity entity) {
        if (entity == null) return null;
        ConnexionResponseDto dto = ConnexionResponseDto.builder()
                .id(entity.getId())
                .dateEnvoi(entity.getDateEnvoi())
                .dateReponse(entity.getDateReponse())
                .build();
        if (entity.getDemandeur() != null) {
            dto.setDemandeurId(entity.getDemandeur().getId());
        }
        if (entity.getDestinataire() != null) {
            dto.setDestinataireId(entity.getDestinataire().getId());
        }
        if (entity.getStatut() != null) {
            dto.setStatutConnexion(entity.getStatut().name());
        }
        return dto;
    }

    @Override
    public List<ConnexionResponseDto> toDtoList(List<ConnexionEntity> entities) {
        if (entities == null) return null;
        List<ConnexionResponseDto> list = new ArrayList<>();
        for (ConnexionEntity e : entities) {
            list.add(toDto(e));
        }
        return list;
    }

    @Override
    public ConnexionEntity toEntity(ConnexionRequestDto dto) {
        // Non utilisé car la création se fait via le service
        return null;
    }
}