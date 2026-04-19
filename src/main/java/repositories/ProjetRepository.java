package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import entities.ProjetEntity;

@Repository
public interface ProjetRepository extends JpaRepository<ProjetEntity, Long> {


    List<ProjetEntity> findByUserId(Long userId);

   
    List<ProjetEntity> findByTitreContainingIgnoreCase(String titre);

   
    List<ProjetEntity> findByUserIdOrderByDateCreationDesc(Long userId);
	
}
