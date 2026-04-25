package com.example.miniLinkedin.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.miniLinkedin.dtos.CommentaireRequestDto;
import com.example.miniLinkedin.dtos.CommentaireResponseDto;
import com.example.miniLinkedin.entities.CommentaireEntity;
import com.example.miniLinkedin.entities.PublicationEntity;
import com.example.miniLinkedin.entities.UserEntity;
import com.example.miniLinkedin.exceptions.ResourceNotFoundException;
import com.example.miniLinkedin.exceptions.UnauthorizedException;
import com.example.miniLinkedin.mapping.CommentaireMapper;
import com.example.miniLinkedin.repositories.CommentaireRepository;
import com.example.miniLinkedin.repositories.PublicationRepository;
import com.example.miniLinkedin.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentaireService {
	
private final CommentaireRepository commentaireRepository;
private final CommentaireMapper commentaireMapper;
private final UserRepository userRepository;
private final PublicationRepository publicationRepository;

@Transactional(readOnly = true)
public List< CommentaireResponseDto> getCommentairesByAuteur(Long id , Long auteurId) {
	
	UserEntity auteur = userRepository.findById(auteurId)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + auteurId));
	
	
List<CommentaireEntity>	  commentaire = commentaireRepository.findByAuteurId(auteurId);
			
	
	return commentaireMapper.toDtoList(commentaire);
		
}
@Transactional(readOnly = true)
public List<CommentaireResponseDto> getCommentaireByPublication(Long publicationId) {
    
  
    if (!publicationRepository.existsById(publicationId)) {
        throw new ResourceNotFoundException("Publication not found with id: " + publicationId);
    }
    
  
    List<CommentaireEntity> commentaires = commentaireRepository.findByPublicationId(publicationId);
    
   
    return commentaireMapper.toDtoList(commentaires);
}

@Transactional
public CommentaireResponseDto createCommentaire (Long publicationId , Long auteurId , String texte , CommentaireRequestDto dto) {
	
	UserEntity auteur = userRepository.findById(auteurId)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + auteurId));
	
	PublicationEntity publication = publicationRepository.findById(publicationId)
			.orElseThrow(() -> new ResourceNotFoundException("Publication not found with id: " + publicationId));
	
	CommentaireEntity commentaire = new CommentaireEntity();
	
	commentaire.setTexte(dto.getTexte());
	commentaire.setAuteur(auteur);
	commentaire.setPublication(publication);
	commentaire.setDate(LocalDateTime.now());
	
	CommentaireEntity savedCommentaire = commentaireRepository.save(commentaire);
	return commentaireMapper.toDto(savedCommentaire);
	
}
@Transactional
public CommentaireResponseDto updateCommentaire (Long competenceId , Long userId, String texte , CommentaireRequestDto dto) {
	
	UserEntity auteur = userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
	CommentaireEntity commentaire = commentaireRepository.findById(competenceId)
			.orElseThrow(() -> new ResourceNotFoundException("Commentaire not found with id: " + competenceId));
	
	if (!commentaire.getAuteur().getId().equals(userId)) {
	    throw new UnauthorizedException("Vous n'êtes pas autorisé à modifier ce commentaire");
	}
	
	
	commentaire.setTexte(dto.getTexte());
	commentaire.setPublication(commentaire.getPublication());
	
	
	
	CommentaireEntity updatedCommentaire = commentaireRepository.save(commentaire);
	return commentaireMapper.toDto(updatedCommentaire);
	
}
@Transactional
public void deleteCommentaire (Long id , Long userId) {
	
	 userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
	
	CommentaireEntity commentaire = commentaireRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Commentaire not found with id: " + id));
	
	if (!commentaire.getAuteur().getId().equals(userId)) {
	    throw new UnauthorizedException("Vous n'êtes pas autorisé à supprimer ce commentaire");
	}
	
	commentaireRepository.delete(commentaire);

}
}
