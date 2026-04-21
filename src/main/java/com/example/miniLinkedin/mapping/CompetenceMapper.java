package com.example.miniLinkedin.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.miniLinkedin.dtos.CompetenceRequestDto;
import com.example.miniLinkedin.dtos.CompetenceResponseDto;
import com.example.miniLinkedin.entities.CompetenceEntity;

@Mapper(componentModel = "spring")

public interface CompetenceMapper {
	
@Mapping(source = "user.id" , target = "userId")
CompetenceResponseDto toDto(CompetenceEntity entity);

@Mapping(target = "id", ignore = true) 
@Mapping(target = "user", ignore = true)
CompetenceEntity toEntity(CompetenceRequestDto dto);



}
