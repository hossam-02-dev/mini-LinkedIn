package com.example.miniLinkedin.mapping;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.example.miniLinkedin.dtos.NotificationResponseDto;
import com.example.miniLinkedin.entities.NotificationEntity;

@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public List<NotificationResponseDto> toDtoList(List<NotificationEntity> entities) {
        if (entities == null) return null;
        List<NotificationResponseDto> list = new ArrayList<>();
        for (NotificationEntity entity : entities) {
            list.add(toDto(entity));
        }
        return list;
    }

    @Override
    public NotificationResponseDto toDto(NotificationEntity notification) {
        if (notification == null) return null;
        NotificationResponseDto dto = new NotificationResponseDto();
        dto.setId(notification.getId());
        dto.setContenu(notification.getContenu());
        dto.setLu(notification.getLu());
        dto.setType(notification.getType());
        dto.setDate(notification.getDate());
        if (notification.getDestinataire() != null) {
            dto.setDestinataireId(notification.getDestinataire().getId());
        }
        if (notification.getDeclencheur() != null) {
            dto.setDeclencheurId(notification.getDeclencheur().getId());
        }
        return dto;
    }
}