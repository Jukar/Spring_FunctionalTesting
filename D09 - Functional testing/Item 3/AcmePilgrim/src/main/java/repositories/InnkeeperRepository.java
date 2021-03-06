package repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Innkeeper;

@Repository
public interface InnkeeperRepository extends JpaRepository<Innkeeper,Integer>{
	
	@Query("select l.innkeeper from Lodge l where l.id=?1")
	Innkeeper findByLodgeId(int lodgeId);
	
	@Query("select i from Innkeeper i where i.userAccount.id=?1")
	Innkeeper findByUserAccount(int userAccount);
	
	@Query("select i from Innkeeper i order by i.lodges.size desc")
	Collection<Innkeeper> findAllByLodgeNumberDESC();
	
	@Query("select l.innkeeper from Lodge l where l.bookings.size=(select max(l.bookings.size) from Lodge l) group by l.innkeeper")
	Collection<Innkeeper> findWithMoreBookings();
}