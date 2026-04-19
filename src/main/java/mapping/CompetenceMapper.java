package mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dtos.CompetenceRequestDto;
import dtos.CompetenceResponseDto;
import entities.CompetenceEntity;

@Mapper(componentModel = "spring")

public interface CompetenceMapper {
	
@Mapping(source = "user.id" , target = "userId")
CompetenceResponseDto toDto(CompetenceEntity entity);

@Mapping(target = "id", ignore = true) 
@Mapping(target = "user", ignore = true)
CompetenceEntity toEntity(CompetenceRequestDto dto);



}
