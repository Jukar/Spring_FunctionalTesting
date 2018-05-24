package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Stage;
import domain.StageRating;

@Repository
public interface StageRepository extends JpaRepository<Stage,Integer>{
	@Query("select sr.stage from Register r join r.stageRatings sr where r.pilgrim.id=?1")	// Funciona
	Collection<Stage> findStageRatedByPilgrimId(int pilgrimId);
	
	@Query("select s from Register r join r.route ro join ro.stageOrders sO join sO.stage s where r.pilgrim.id=?1")	// Funciona
	Collection<Stage> findStageByPilgrimId(int pilgrimId);
}
