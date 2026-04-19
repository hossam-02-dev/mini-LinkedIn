package mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dtos.MessageRequestDto;
import dtos.MessageResponseDto;
import entities.MessageEntity;

@Mapper(componentModel = "spring")

public interface MessageMapper {
	
	@Mapping(source = "expediteur.id" , target = "expediteurId")
	@Mapping(source = "destinataire.id" , target = "destinataireId")
MessageResponseDto toDto (MessageEntity message);
	
	@Mapping(target = "expediteur" , ignore = true)
	@Mapping(target = "destinataire" , ignore = true)
	@Mapping(target = "lu" , ignore = true)
	@Mapping(target = "dateEnvoi" , ignore = true)
	MessageEntity toEntity (MessageRequestDto dto);

}
