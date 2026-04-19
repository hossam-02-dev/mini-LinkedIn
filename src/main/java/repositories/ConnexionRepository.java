package repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import entities.ConnexionEntity;
import enums.StatutConnexion;

@Repository
public interface ConnexionRepository extends JpaRepository<ConnexionEntity, Long> {
	
    boolean existsByDemandeurIdAndDestinataireId(Long demandeurId, Long destinataireId);
    
    Optional<ConnexionEntity> findByDemandeurIdAndDestinataireId(
            Long demandeurId, Long destinataireId);
    
    List<ConnexionEntity> findByDemandeurId(Long demandeurId);
    
    List<ConnexionEntity> findByDestinataireId(Long destinataireId);
    
    List<ConnexionEntity> findByDestinataireIdAndStatut(
            Long destinataireId, StatutConnexion statut);
    
    @Query("SELECT c FROM ConnexionEntity c WHERE " +
            "(c.demandeur.id = :userId OR c.destinataire.id = :userId) " +
            "AND c.statut = 'ACCEPTE'")
    
     List<ConnexionEntity> findConnexionsAccepteesParUserId(@Param("userId") Long userId);
 }
