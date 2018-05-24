package repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer>{
	
	@Query("select b from Booking b where b.lodge.innkeeper.id = ?1 and b.pilgrim.id =?2")
	Collection<Booking> findByBookingAndInnkeeperIdAndPilgrimId(int innKeeperId, int pilgrimId);
	
	@Query("select b from Booking b where b.lodge.innkeeper.id = ?1")
	Collection<Booking> findByBookingAndInnkeeperId(int innKeeperId);
	
	@Query("select b from Booking b where b.pilgrim.id = ?1")
	Collection<Booking> findByBookingAndPilgrimId(int pilgrimId);
	
	@Query("select b from Booking b where b.pilgrim.id = ?1 order by b.arrivalDate asc")
	Collection<Booking> findByInnkeeperIdAndOrderedByArrivalDate(int innkeeperId);
	
	@Query("select b from Booking b where b.lodge.innkeeper.id = ?1 group by b.pilgrim")
	Collection<Booking> findBookingByInnkeeper(int innkeeperId);

	@Query("select b from Booking b where b.lodge.id = ?1")
	Collection<Booking> findByLodge(int lodgeId);
	@Query("select b from Booking b where b.pilgrim.id = ?1 order by b.creationBookMoment asc")
	Collection<Booking> findByPilgrimIdOrdered(int id);
}
