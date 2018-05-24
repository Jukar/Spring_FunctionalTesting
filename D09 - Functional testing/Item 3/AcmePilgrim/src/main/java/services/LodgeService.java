
package services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import domain.Lodge;
import domain.Booking;
import domain.Innkeeper;
import domain.LodgeAssessment;
import domain.Pilgrim;
import domain.Request;
import domain.Reservation;
import domain.Stage;
import forms.LodgeForm;
import repositories.LodgeRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import org.springframework.util.Assert;

@Service
@Transactional
public class LodgeService {
	
	// ------------------- Managed repository --------------------
	
	@Autowired
	private LodgeRepository lodgeRepository;
	
	// ------------------- Supporting services -------------------
	
	@Autowired
	private InnkeeperService innkeeperService;
	@Autowired
	private RequestService requestService;
	@Autowired
	private StageService stageService;
	@Autowired
	private PilgrimService pilgrimService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private BookingService bookingService;
	
	// ----------------------- Constructor -----------------------
	
	// ------------------- Simple CRUD methods -------------------
	
	public Lodge findOne(int lodgeId) {
		return lodgeRepository.findOne(lodgeId);
	}
	
	public Collection<Lodge> findAll(){
		return lodgeRepository.findAll();
	}
	
	public Lodge create(){
		Lodge lodge = new Lodge();
		lodge.setInnkeeper(getInnkeeper());
		lodge.setBookings(new ArrayList<Booking>());
		lodge.setReservations(new ArrayList<Reservation>());
		lodge.setPublished(false);
		return lodge;
	}
	
	public void save(Lodge lodge){
		
		Assert.notNull(lodge);
		Assert.isTrue(isPrincipal(lodge));
		lodge.setPublished(false);
		lodge = lodgeRepository.save(lodge);
		
		Innkeeper innkeeper = getInnkeeper();
		Collection<Lodge> lodges = innkeeper.getLodges();
		lodges.add(lodge);
		innkeeper.setLodges(lodges);
		innkeeperService.save(innkeeper);
		
		Request request = requestService.findByLodge(lodge.getId());
		if(request!=null){
			request.setStatus("PENDING");
			requestService.save(request);
		}
		
		Stage stage  = stageService.findOne(lodge.getStage().getId());
		Assert.notNull(stage);
		lodges = stage.getLodges();
		lodges.add(lodge);
		stage.setLodges(lodges);
		stageService.saveInnkeeper(stage);
	}
	
	public void saveReservation(Lodge lodge){
		Assert.notNull(lodge);
		Assert.isTrue(isPilgrim());

		lodge = lodgeRepository.save(lodge);
	}
	
	public void savePilgrim(Lodge lodge){
		Assert.notNull(lodge);
		Assert.isTrue(isPilgrim());

		lodgeRepository.save(lodge);
	}
	

	
	public void saveAdmin(Lodge lodge){
		Assert.notNull(lodge);
		Assert.isTrue(isAdmin());
		
		Innkeeper innkeeper = lodge.getInnkeeper();
		Collection<Lodge> lodges = innkeeper.getLodges();
		//ACTUALIZAR NO AÑADIR CAMBIAR UNO POR EL OTRO
		lodges.add(lodge);
		innkeeper.setLodges(lodges);
		innkeeperService.save(innkeeper);
		
		Stage stage  = stageService.findOne(lodge.getStage().getId());
		Assert.notNull(stage);
		lodges = stage.getLodges();
		lodges.add(lodge);
		stage.setLodges(lodges);
		stageService.save(stage);
		lodgeRepository.save(lodge);
	}
	
	public void delete (Lodge lodge){
		Assert.notNull(lodge);
		Assert.isTrue(isPrincipal(lodge));
		
		Lodge lodgeOnDatabase = findOne(lodge.getId());
		Assert.notNull(lodgeOnDatabase);
		
		if(lodgeOnDatabase.getBookings().isEmpty())
			lodgeRepository.delete(lodge);
		else
			Assert.isTrue(false, "No puedes borrar un lodge si ya tienes reservas");
	}
	
	// ----------------- Other business methods ------------------
	
	// REPOSITORIO:
	
	public Collection<Lodge> findAllPublished() {
		Collection<Lodge> result = lodgeRepository.findAllPublished();
		return (result==null) ? new ArrayList<Lodge>() : result;
	}
	
	public Collection<Lodge> lodgesOrderedByBookingsDesc() {
		isKeeperOrAdmin(LoginService.getPrincipal());
		Collection<Lodge> result = lodgeRepository.lodgesOrderedByBookingsDesc();
		return (result==null) ? new ArrayList<Lodge>() : result;
	}
	
	public Collection<Lodge> findByInnkeeperId(int innkeeperId) {
		Collection<Lodge> result = lodgeRepository.findByInnkeeperId(innkeeperId);
		return (result==null) ? new ArrayList<Lodge>() : result;
	}
	
	public Collection<Lodge> findByPilgrimId(int pilgrimId){
		Collection<Lodge> result = lodgeRepository.findByPilgrimId(pilgrimId);
		return (result==null) ? new ArrayList<Lodge>() : result;
	}
	
	public Collection<Lodge> findByPilgrim(){
		Pilgrim pilgrim = getPilgrim();
		return findByPilgrimId(pilgrim.getId());
	}
	
	public Collection<Lodge> findByInnkeeper(){
		Innkeeper innkeeper = getInnkeeper();
		return findByInnkeeperId(innkeeper.getId());
	}
	
	public Collection<Lodge> lodgesOrderedByRatingAsc(){
		Collection<Lodge> result;
		isKeeper(LoginService.getPrincipal());
		UserAccount usAc=LoginService.getPrincipal();
		Innkeeper innkeeper=innkeeperService.findByUserAccount(usAc);
		Collection<Lodge> all=lodgeRepository.findByInnkeeperId(innkeeper.getId());
		System.out.println("LODGES BY INNKEEPER: " + all);
		Map<Lodge,Double> allWithRating=new HashMap<Lodge,Double>();
		System.out.println("SI");
		for(Lodge l:all){
			Double rating=0.0;
			System.out.println("Recorriendo: " + l.getId());
			Double ratingFin=0.0;
			for(Booking b:l.getBookings()){
				LodgeAssessment lAs=b.getLodgeAssessment();
				
				if(lAs != null){
					Double count = 0.0;
					
					if(lAs.getPriceRate()!=null){
						rating+=lAs.getPriceRate();
						count++;
					}if(lAs.getLocationRate()!=null){
						rating+=lAs.getLocationRate();
						count++;
					}if(lAs.getRoomsRate()!=null){
						rating+=lAs.getRoomsRate();
						count++;
					}if(lAs.getServiceRate()!=null){
						rating+=lAs.getServiceRate();
						count++;
					}
					System.out.println("Dentro es: " + rating/count);
					ratingFin+=rating/count/l.getBookings().size();
				}
			}
			System.out.println("El total es: " + ratingFin);
			allWithRating.put(l, ratingFin);
		}
		System.out.println("SI");
		Map<Lodge,Double> orderedByRat=sortByComparatorDou(allWithRating);
		System.out.println("SI");
		result=orderedByRat.keySet();
		System.out.println("SI");
		return result;
	}

	public Collection<Lodge> lodgesOrderedByPriceDesc(){
		isKeeper(LoginService.getPrincipal());
		UserAccount usAc=LoginService.getPrincipal();
		Innkeeper innkeeper=innkeeperService.findByUserAccount(usAc);
		
		Collection<Lodge> all=lodgeRepository.lodgesOrderedByPricesDesc(innkeeper.getId());
		Map<Lodge,Integer> allWithStage=new HashMap<Lodge,Integer>();
		for(Lodge l:all){
			allWithStage.put(l, l.getStage().getId());
		}
		Map<Lodge,Integer> orderedByStag=sortByComparatorInt(allWithStage);
		Collection<Lodge> result=orderedByStag.keySet();
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public Double getOccupancyRateNextMonth(){
		UserAccount user=LoginService.getPrincipal();
		Innkeeper keeper=innkeeperService.findByUserAccount(user);
		Double result=0.0;
		Collection<Lodge> all=lodgeRepository.findByInnkeeperId(keeper.getId());
		Collection<Booking> allBookings=bookingService.findBookingByInnkeeper(keeper.getId());
		Integer totalBeds=0;
		
		for(Lodge l:all){
			totalBeds+=l.getNumberBeds();
		}
		for(Booking b:allBookings){
			Date today=new Date();
			Date thatDay=new Date();
			thatDay.setDate(thatDay.getDate() + 31);
			if(b.getArrivalDate().after(today) && b.getArrivalDate().before(thatDay)){
				result=result+1.0*b.getNumberOfBeds();
			}
		}
		result=(result*100)/totalBeds;
		return result;
	}
	
	public Collection<Lodge> lodgesWithoutRequestAssociated(){

		Collection<Request> allRequest = requestService.findAll();
		Collection<Lodge> all = findAll();
		List<Lodge> allWithRequest = new ArrayList<Lodge>() ;
		List<Lodge> result = new ArrayList<Lodge>();
		for(Request r:allRequest){
			if(r.getLodge()!=null){
				allWithRequest.add(r.getLodge());
			}
		}
		
		for(Lodge l : all){
			if(!(allWithRequest.contains(l))){
				result.add(l);
			}
		}
		return result;
	}
	// CASOS DE USO:
	
	public void crearReserva(Booking booking){
		Date arrivalDate = booking.getArrivalDate();
		int numberOfNights = booking.getNumberOfNights();
		int numberOfBeds = booking.getNumberOfBeds();
		Lodge lodge = booking.getLodge();

		Assert.isTrue(lodge.getNumberBeds() >= numberOfBeds, "El hotel no tiene esa capacidad");
		System.out.println("SI");
		while(numberOfNights>0){		// Creo o modifico tantas reservas como días quiero reservar
			Reservation reservation = reservationService.findByDateAndLodgeId(arrivalDate, lodge.getId());
									
			if(reservation==null){		// Si no existe la creo con las camas que quiero para el día X
				System.out.println("2");
				reservation = reservationService.create();
				reservation.setDayBooked(arrivalDate);
				reservation.setReservedBeds(numberOfBeds);
				reservation.setLodge(lodge);
			}else{						// Si existe compruebo que tengo las camas disponibles para día X
				System.out.println("2");
				Assert.isTrue(lodge.getNumberBeds() >= reservation.getReservedBeds() + numberOfBeds, "it's full mate");
				System.out.println("2");
				reservation.setReservedBeds(reservation.getReservedBeds() + numberOfBeds);
			}
			System.out.println("SI: " + numberOfNights);
			reservationService.save(reservation);
			System.out.println("SI: " + numberOfNights);
			arrivalDate = new Date(arrivalDate.getTime() + 86400000);		// Fecha + 1 Día en ms
			numberOfNights--;
		}
	}
	
	public void cancelarReserva(Booking booking){
		Date arrivalDate = booking.getArrivalDate();
		int numberOfNights = booking.getNumberOfNights();
		int numberOfBeds = booking.getNumberOfBeds();
		Lodge lodge = booking.getLodge();
		
		Assert.isTrue(arrivalDate.compareTo(new Date(new Date().getTime()-86400000)) > 0, "less than 24h for arrival");
		
		while(numberOfNights>0){		// borro o modifico tantas reservas como días quiero cancelar
			Reservation reservation = reservationService.findByDateAndLodgeId(arrivalDate, lodge.getId());
			Assert.isNull(reservation, "Si estas borrando es porque existe");
			
			if(reservation.getReservedBeds()==numberOfBeds){	// Si el booking es unico para el día X, borro
				reservationService.delete(reservation);
			}else{						// Si hay más de una reserva, solo borro las camas que tenía reservadas
				Assert.isTrue(lodge.getNumberBeds() >= reservation.getReservedBeds() + numberOfBeds, "it's full mate");

				reservation.setReservedBeds(reservation.getReservedBeds() - numberOfBeds);
				reservationService.save(reservation);
			}
			
			arrivalDate = new Date(arrivalDate.getTime() + 86400000);		// Fecha + 1 Día en ms
			numberOfNights--;
		}
	}
	
	public void publishLodge(int lodgeId){
		Lodge lodge = findOne(lodgeId);
		Request request = requestService.findByLodge(lodge.getId());
		System.out.println(request);
		Assert.notNull(lodge);
		Assert.notNull(request);
		Assert.isTrue(isPrincipal(lodge));
		Assert.isTrue(request.getStatus().equals("ACCEPTED"));
		lodge.setPublished(true);
		lodgeRepository.save(lodge);
}
	
	public Lodge reconstruct(LodgeForm lodgeForm){
		Lodge result;
		
		if(lodgeForm.getLodgeId()==0||lodgeForm.getLodgeId()==null)
			result = create();
		else{
			result = lodgeRepository.findOne(lodgeForm.getLodgeId());
			Assert.notNull(result, "Nunca debe ser null");
			
			if(!result.getBookings().isEmpty() && lodgeForm.getStage()!=result.getStage())
				Assert.isTrue(false, "No cambiar de Stage si existen Bookings");
			else
				result.setStage(lodgeForm.getStage());
		}
		
		result.setName(lodgeForm.getName());
		result.setAddress(lodgeForm.getAddress());
		result.setCoordinates(lodgeForm.getCoordinates());
		result.setWebSite(lodgeForm.getWebSite());
		result.setContactPhone(lodgeForm.getContactPhone());
		result.setLodgeDescription(lodgeForm.getLodgeDescription());
		result.setNumberBeds(lodgeForm.getNumberBeds());
		result.setPricePerNight(lodgeForm.getPricePerNight());
		result.setStage(lodgeForm.getStage());
		
		return result;
	}
	public Collection<Object[]> findLodgesBookedAndStagesNotRated(){
		Collection<Stage> stages=stageService.findNotRegisteredByPilgrim();
		Collection<Object[]> result = new ArrayList<Object[]>();
		for(Stage s:stages){
			Object[] object=new Object[2];
			object[0]=s;
			object[1]=lodgesBookingByStage(s);
			result.add(object);
		}
		return result;
	}
	public LodgeForm construct(Lodge lodge){
		LodgeForm result= new LodgeForm();
		
		result.setName(lodge.getName());
		result.setAddress(lodge.getAddress());
		result.setCoordinates(lodge.getCoordinates());
		result.setWebSite(lodge.getWebSite());
		result.setContactPhone(lodge.getContactPhone());
		result.setLodgeDescription(lodge.getLodgeDescription());
		result.setNumberBeds(lodge.getNumberBeds());
		result.setPricePerNight(lodge.getPricePerNight());
		result.setStage(lodge.getStage());
		
		result.setLodgeId(lodge.getId());
		return result;
	}
	
	// AUXILIARES:
	
	private Boolean isInnkeeper(){
		UserAccount userAccount = LoginService.getPrincipal();
		Boolean result = userAccount.getAuthorities().iterator().next().getAuthority().equals("INNKEEPER");
		
		return result;
	}
	
	private Boolean isPilgrim(){
		UserAccount userAccount = LoginService.getPrincipal();
		Boolean result = userAccount.getAuthorities().iterator().next().getAuthority().equals("PILGRIM");
		
		return result;
	}
	
	private Boolean isAdmin(){
		UserAccount userAccount = LoginService.getPrincipal();
		Boolean result = userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN");
		return result;
	}
	
	private Pilgrim getPilgrim(){
		UserAccount userAccount = LoginService.getPrincipal();
		Pilgrim result = pilgrimService.findByUserAccount(userAccount);
		Assert.notNull(result);
		return result;
	}
	
	private void isKeeper(UserAccount account) {
		Collection<Authority> authorities= account.getAuthorities();
		Boolean res=false;
		for(Authority a:authorities){
			if(a.getAuthority().equals("INNKEEPER")) res=true;
		}
		Assert.isTrue(res);
	}
	public void isKeeperOrAdmin(UserAccount account){
		Collection<Authority> authorities= account.getAuthorities();
		Boolean res=false;
		for(Authority a:authorities){
			if(a.getAuthority().equals("INNKEEPER")) res=true;
			if(a.getAuthority().equals("ADMIN")) res=true;
		}
		Assert.isTrue(res);
	}

	private Boolean isPrincipal(Lodge lodge){
		Boolean result = false;
		Innkeeper innkeeper = getInnkeeper();
		result = innkeeper.getId() == lodge.getInnkeeper().getId();
		return result;
	}
	
	private Innkeeper getInnkeeper(){
		UserAccount userAccount = LoginService.getPrincipal();
		Innkeeper result = innkeeperService.findByUserAccount(userAccount);
		Assert.notNull(result);
		return result;
	}
	
	private Collection<Lodge> lodgesBookingByStage(Stage stage){
		UserAccount userAccount = LoginService.getPrincipal();
		Pilgrim pilgrim=pilgrimService.findByUserAccount(userAccount);
		Collection<Lodge> result = lodgeRepository.lodgesBookingByStage(stage.getId(),pilgrim.getId());
		return result;
	}
	
	//	Method used to order a map<Lodge,Double>
	private static Map<Lodge, Double> sortByComparatorDou(Map<Lodge, Double> unsortMap) {
		 
		// Convert Map to List
		List<Map.Entry<Lodge, Double>> list = 
			new LinkedList<Map.Entry<Lodge, Double>>(unsortMap.entrySet());
 
		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<Lodge, Double>>() {
			public int compare(Map.Entry<Lodge, Double> o1,
                                           Map.Entry<Lodge, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
 
		// Convert sorted map back to a Map
		Map<Lodge, Double> sortedMap = new LinkedHashMap<Lodge, Double>();
		for (Iterator<Map.Entry<Lodge, Double>> it = list.iterator(); it.hasNext();) {
			Entry<Lodge, Double> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	//	Method used to order a map<Lodge,Integer>
	private static Map<Lodge, Integer> sortByComparatorInt(Map<Lodge, Integer> unsortMap) {
		 
		// Convert Map to List
		List<Map.Entry<Lodge, Integer>> list = new LinkedList<Map.Entry<Lodge, Integer>>(unsortMap.entrySet());
 
		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<Lodge, Integer>>() {
			public int compare(Map.Entry<Lodge, Integer> o1,
                                           Map.Entry<Lodge, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
 
		// Convert sorted map back to a Map
		Map<Lodge, Integer> sortedMap = new LinkedHashMap<Lodge, Integer>();
		for (Iterator<Map.Entry<Lodge, Integer>> it = list.iterator(); it.hasNext();) {
			Entry<Lodge, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}