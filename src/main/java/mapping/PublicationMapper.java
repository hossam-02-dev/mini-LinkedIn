package mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dtos.PublicationRequestDto;
import dtos.PublicationResponseDto;
import entities.PublicationEntity;

@Mapper(componentModel = "spring")
public interface PublicationMapper {
	
	@Mapping(source = "auteur.id", target = "auteurId")
PublicationResponseDto  toDto(PublicationEntity publicationEntity);	

@Mapping(target = "auteur" , ignore = true)
@Mapping(target = "datePublication", ignore = true)
@Mapping(target = "dateMaj", ignore = true)
PublicationEntity toEntity (PublicationRequestDto dto);
}
