package mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dtos.FormationRequestDto;
import dtos.FormationResponseDto;
import entities.FormationEntity;

@Mapper(componentModel = "spring")
public interface FormationMapper {
	
	@Mapping(source ="profil.id" , target = "profilId")
	FormationResponseDto toDto (FormationEntity formation);
	
	@Mapping(target = "profil" , ignore = true)
	FormationEntity toEntity (FormationRequestDto dto);

}
