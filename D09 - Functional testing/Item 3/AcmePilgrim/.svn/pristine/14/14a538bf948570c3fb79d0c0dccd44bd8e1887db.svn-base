package repositories;

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer>{

	@Query("select r from Reservation r where r.dayBooked=?1 and r.lodge.id=?2")
	Reservation findByDateAndLodgeId(Date date, int lodgeId);
}