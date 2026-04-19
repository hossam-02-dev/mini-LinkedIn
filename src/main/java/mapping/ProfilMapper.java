package mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import dtos.ProfilRequestDto;
import dtos.ProfilResponseDto;
import entities.ProfilEntity;
import entities.UserEntity;

@Mapper(componentModel = "spring")
public interface ProfilMapper {
	
@Mapping(source = "user.id" , target = "userId")
@Mapping(target = "nomComplet", source = "user", qualifiedByName = "mapNomComplet")
ProfilResponseDto toDto (ProfilEntity profil);

@Mapping(target = "id" , ignore = true)
@Mapping(target = "user",  ignore = true)
@Mapping(target = "formations", ignore = true)
ProfilEntity toEntity (ProfilRequestDto dto);

@Named("mapNomComplet")

default String mapNomComplet(UserEntity user) {
	
    if (user == null) return null;
    String prenom = (user.getFirstName() != null) ? user.getFirstName() : "";
    String nom = (user.getLastName() != null) ? user.getLastName() : "";
    return (prenom + " " + nom).trim();
}

}
