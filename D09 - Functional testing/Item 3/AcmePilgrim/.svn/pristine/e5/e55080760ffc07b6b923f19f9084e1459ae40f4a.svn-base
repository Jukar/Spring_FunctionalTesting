package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register,Integer>{

	@Query("select r from Register r where r.route.id=?1")
	Collection<Register> findByRouteId(int routeId);

}
