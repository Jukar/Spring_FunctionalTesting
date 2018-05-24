package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Landmark;

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark,Integer>{
	@Query("select size(s.landmarks) from Stage s")
	Integer getTotal();
	
	@Query("select l from Landmark l where l.stage.id=?1")
	Collection<Landmark> findByStage(int stageId);
	
	@Query("select l.stage.name,avg(l) from Landmark l group by l.stage")
	Collection<Object[]> avgPerRoute();
	
	@Query("select l from Landmark l where l.actor.id=?1")
	Collection<Landmark> findByActor(int actorId);
}
