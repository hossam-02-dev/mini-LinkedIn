package com.example.miniLinkedin.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.miniLinkedin.dtos.NotificationResponseDto;
import com.example.miniLinkedin.entities.NotificationEntity;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
	
@Mapping(source = "destinataire.id", target = "destinataireId")
@Mapping(source = "declencheur.id" , target = "declencheurId")
NotificationResponseDto toDto (NotificationEntity notification);


}
