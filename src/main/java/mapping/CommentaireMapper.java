package mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dtos.CommentaireRequestDto;
import dtos.CommentaireResponseDto;
import entities.CommentaireEntity;

@Mapper(componentModel = "spring") 

public interface CommentaireMapper {
	
	@Mapping(target = "auteur",    ignore = true)
	@Mapping(target = "publication", ignore = true)
	@Mapping(target = "date",     ignore = true)
	@Mapping(target = "id",    ignore = true)
	CommentaireEntity toEntity(CommentaireRequestDto dto);
	
	@Mapping(source = "auteur.lastName", target = "nomAuteur")
	@Mapping(source = "auteur.id", target = "auteurId")
	CommentaireResponseDto toDto(CommentaireEntity entity);

}
