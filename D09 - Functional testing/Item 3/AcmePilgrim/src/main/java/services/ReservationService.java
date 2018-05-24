package services;

import java.util.Collection;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ReservationRepository;
import security.LoginService;
import security.UserAccount;
import domain.Lodge;
import domain.Reservation;

@Service
@Transactional
public class ReservationService {
	
	// ------------------- Managed repository --------------------
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	// ------------------- Supporting services -------------------
	
	@Autowired
	private LodgeService lodgeService;

	// ----------------------- Constructor -----------------------
	
	// ------------------- Simple CRUD methods -------------------
	
	public Reservation findOne(int reservationId) {
		return reservationRepository.findOne(reservationId);
	}
	
	public Collection<Reservation> findAll(){
		return reservationRepository.findAll();
	}
	
	public Reservation create(){
		return new Reservation();
	}
	
	public void save(Reservation reservation){
		Assert.notNull(reservation);
		Lodge lodge = lodgeService.findOne(reservation.getLodge().getId());
		System.out.println(lodge.getNumberBeds() + " vs " + reservation.getReservedBeds());
		Assert.isTrue(lodge.getNumberBeds()>=reservation.getReservedBeds());
		
		System.out.println(lodge.getNumberBeds()>=reservation.getReservedBeds());
		Collection<Reservation> reservations = lodge.getReservations();
		System.out.println(reservations.size());
		reservations.add(reservation);
		System.out.println(reservations.size());
		lodge.setReservations(reservations);
		lodgeService.saveReservation(lodge);
		
		reservationRepository.save(reservation);
	}
	
	public void delete (Reservation reservation){
		Assert.notNull(reservation);
		Assert.isTrue(isPilgrim());
		
		reservationRepository.delete(reservation);
	}
	
	// ----------------- Other business methods ------------------
	
	// REPOSITORIO:
	
	public Reservation findByDateAndLodgeId(Date date, int lodgeId) {
		Reservation result = reservationRepository.findByDateAndLodgeId(date, lodgeId);
		return result;
	}
	
	// CASOS DE USO:
	
	// AUXILIARES:
	
	private Boolean isPilgrim(){
		UserAccount userAccount = LoginService.getPrincipal();
		Boolean result = userAccount.getAuthorities().iterator().next().getAuthority().equals("PILGRIM");
		return result;
	}
	
}