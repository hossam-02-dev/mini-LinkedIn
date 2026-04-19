package mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dtos.NotificationResponseDto;
import entities.NotificationEntity;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
	
@Mapping(source = "destinataire.id", target = "destinataireId")
@Mapping(source = "declencheur.id" , target = "declencheurId")
NotificationResponseDto toDto (NotificationEntity notification);


}
