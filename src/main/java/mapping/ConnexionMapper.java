package mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dtos.ConnexionRequestDto;
import dtos.ConnexionResponseDto;
import entities.ConnexionEntity;

@Mapper(componentModel = "spring")
public interface ConnexionMapper {
	
	@Mapping(source = "demandeur.id" , target = "demandeurId")
	@Mapping(source = "destinataire.id" , target = "destinataireId")
	@Mapping(target = "statutConnexion", expression = "java(entity.getStatut().name())")
	ConnexionResponseDto toDto(ConnexionEntity entity);
	
	
	@Mapping(target = "demandeur" , ignore = true)
	@Mapping(target = "destinataire" , ignore = true)
	@Mapping(target = "statut" , ignore = true)
	@Mapping(target = "dateEnvoi" , ignore = true)
	@Mapping(target = "dateReponse" , ignore = true)
	ConnexionEntity toEntity(ConnexionRequestDto dto);

}
