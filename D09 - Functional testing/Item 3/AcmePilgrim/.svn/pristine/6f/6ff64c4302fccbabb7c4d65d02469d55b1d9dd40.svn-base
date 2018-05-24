package repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Pilgrim;
import domain.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route,Integer>{
	
	@Query("select re.route from Register re group by re.route order by count(re) desc")	// Funciona
	Collection<Route> routesByRegistrationsDesc();
	
	
	@Query("select r from Route r order by r.ratingL+r.ratingD/2 asc")
	Collection<Route> routesByAveRatingAsc();

	// Los " " + " " son para concatenar el String y que sea legible, no afecta a la anotacion @Query
	@Query("select r from Route r join r.stageOrders s where " +
			"(r.name like ?1 or r.description like ?1 or " +
			"s.stage.name like ?1 or s.stage.description like ?1 or " +
			"s.stage.origin.title like ?1 or s.stage.origin.description like ?1 or " +
			"s.stage.destination.title like ?1 or s.stage.destination.description like ?1) and " +
			"r.actived=true")
	Collection<Route> routeByKeyword(String keyword);

//	@Query("select ru from Route ru join r.registers re where re.pilgrim=?1")	// Route no navega a Register
//	@Query("select ru from Route ru, Register re where re.pilgrim=?1 and re.route=ru")	// Usar si falla con +1
	@Query("select ro from Register re join re.route ro where re.pilgrim=?1")	// Funciona
	Collection<Route> findByPilgrim(Pilgrim pilgrim);
	
	// select r from Route r join r.stageOrders s where r.name like '%nice%' or r.description like '%nice%' or s.stage.name like '%nice%' or s.stage.description like '%nice%' or s.stage.origin.title like '%nice%' or s.stage.origin.description like '%nice%' or s.stage.destination.title like '%nice%' or s.stage.destination.description like '%nice%';
	
	@Query("select r from Route r where r.actived is true")
	Collection<Route> findActives();
}