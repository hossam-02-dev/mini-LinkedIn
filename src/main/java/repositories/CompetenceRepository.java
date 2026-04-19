package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.CompetenceEntity;

@Repository
public interface CompetenceRepository extends JpaRepository<CompetenceEntity, Long> {
	
	 List<CompetenceEntity> findByUserId(Long userId);
	 
	   boolean existsByNomAndUserId(String nom, Long userId);
	   
	   void deleteByUserId(Long userId);
}
