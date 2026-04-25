package com.example.miniLinkedin.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.miniLinkedin.dtos.ProfilRequestDto;
import com.example.miniLinkedin.dtos.ProfilResponseDto;
import com.example.miniLinkedin.entities.ProfilEntity;
import com.example.miniLinkedin.entities.UserEntity;
import com.example.miniLinkedin.exceptions.ResourceNotFoundException;
import com.example.miniLinkedin.mapping.ProfilMapper;
import com.example.miniLinkedin.repositories.ProfilRepository;
import com.example.miniLinkedin.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfilService {
	
private final ProfilRepository profilRepository;
private final ProfilMapper profilMapper;
private final UserRepository userRepository;

@Transactional(readOnly = true)
public ProfilResponseDto getProfilByUserId(Long userId) {
	
ProfilEntity profil = profilRepository.findByUserId(userId)
	.orElseThrow(() -> new ResourceNotFoundException("Profil not found with user id: " + userId));
	
	return  profilMapper.toDto(profil);
}
@Transactional
public ProfilResponseDto createProfil (Long userId , ProfilRequestDto dto) {
	
	UserEntity user = userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
	
	ProfilEntity profil = new ProfilEntity();
	
	profil.setEtablissement(dto.getEtablissement());
	profil.setBio(dto.getBio());
	profil.setUser(user);
	profil.setVille(dto.getVille());
	profil.setSiteWeb(dto.getSiteWeb());
	profil.setPhotoUrl(dto.getPhotoUrl());
	profil.setDateNaissance(dto.getDateNaissance());
	profil.setName(dto.getName());
	
	profilRepository.save(profil);
	return profilMapper.toDto(profil);
}

@Transactional
public ProfilResponseDto updateProfil (Long profilId , ProfilRequestDto dto) {
	
ProfilEntity profil = profilRepository.findByUserId(profilId)
	.orElseThrow(() -> new ResourceNotFoundException("Profil not found with user id: " + profilId));



profil.setEtablissement(dto.getEtablissement());
profil.setBio(dto.getBio());
profil.setVille(dto.getVille());
profil.setSiteWeb(dto.getSiteWeb());
profil.setPhotoUrl(dto.getPhotoUrl());
profil.setDateNaissance(dto.getDateNaissance());
profil.setName(dto.getName());

profilRepository.save(profil);
return profilMapper.toDto(profil);

}
@Transactional
public ProfilResponseDto uploadPhoto (Long profilId , String photoUrl) {
	
ProfilEntity profil = profilRepository.findByUserId(profilId)
	.orElseThrow(() -> new ResourceNotFoundException("Profil not found with user id: " + profilId));	

profil.setPhotoUrl(photoUrl);
profilRepository.save(profil);

return profilMapper.toDto(profil);
}
}
