package repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.StageRating;

@Repository
public interface StageRatingRepository extends JpaRepository<StageRating,Integer>{
	
	@Query("select sr from StageRating sr  join sr.register re where re.pilgrim.id=?1")	// Funciona
	Collection<StageRating> findByPilgrimId(int pilgrimId);
}
