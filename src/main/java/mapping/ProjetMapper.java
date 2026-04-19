package mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import dtos.ProjetRequestDto;
import dtos.ProjetResponseDto;
import entities.ProjetEntity;
import entities.UserEntity;

@Mapper(componentModel = "spring")
public interface ProjetMapper {
	
	@Mapping(source = "user.id",    target = "auteurId")
	@Mapping(target = "nomAuteur",  source = "user", qualifiedByName = "mapNomAuteur")
	@Mapping(target = "dateMaj",    ignore = true)
	ProjetResponseDto toDto(ProjetEntity projet);
	
	
	@Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)          
    @Mapping(target = "dateCreation", ignore = true)
    @Mapping(target = "dateMaj", ignore = true)
	ProjetEntity toEntity(ProjetRequestDto requestDto);
	
	
	
	
	
	@Named("mapNomAuteur")
	default String mapNomAuteur(UserEntity user) {
	    if (user == null) return null;
	    String prenom = user.getFirstName() != null ? user.getFirstName() : "";
	    String nom    = user.getLastName()  != null ? user.getLastName()  : "";
	    return (prenom + " " + nom).trim();
	}

}
