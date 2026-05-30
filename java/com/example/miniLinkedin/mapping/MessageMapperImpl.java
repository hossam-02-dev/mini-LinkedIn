package com.example.miniLinkedin.mapping;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.example.miniLinkedin.dtos.MessageRequestDto;
import com.example.miniLinkedin.dtos.MessageResponseDto;
import com.example.miniLinkedin.entities.MessageEntity;

@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageResponseDto toDto(MessageEntity message) {
        if (message == null) return null;
        MessageResponseDto dto = new MessageResponseDto();
        dto.setId(message.getId());
        dto.setContenu(message.getContenu());
        dto.setLu(message.getLu());
        dto.setDateEnvoi(message.getDateEnvoi());
        if (message.getExpediteur() != null) {
            dto.setExpediteurId(message.getExpediteur().getId());
        }
        if (message.getDestinataire() != null) {
            dto.setDestinataireId(message.getDestinataire().getId());
        }
        return dto;
    }

    @Override
    public MessageEntity toEntity(MessageRequestDto dto) {
        if (dto == null) return null;
        MessageEntity entity = new MessageEntity();
        entity.setContenu(dto.getContenu());
        return entity;
    }

    @Override
    public List<MessageResponseDto> toDtoList(List<MessageEntity> messages) {
        if (messages == null) return null;
        List<MessageResponseDto> list = new ArrayList<>();
        for (MessageEntity m : messages) {
            list.add(toDto(m));
        }
        return list;
    }
}