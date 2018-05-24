package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.BookingRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Anecdote;
import domain.Booking;
import domain.Innkeeper;
import domain.Invoice;
import domain.Lodge;
import domain.LodgeAssessment;
import domain.Money;
import domain.Pilgrim;
import forms.BookingForm;
import forms.LodgeAssessmentForm;

@Service
@Transactional
public class BookingService {
	// ------------------- Managed repository --------------------
	
	@Autowired
	private BookingRepository bookingRepository;
	
	// ------------------- Supporting services -------------------
	
	@Autowired
	private PilgrimService pilgrimService;
	@Autowired
	private InnkeeperService innkeeperService;
	@Autowired
	private LodgeService lodgeService;
	@Autowired
	private InvoiceService invoiceService;
	
	// ----------------------- Constructor -----------------------
	
	// ------------------- Simple CRUD methods -------------------
	
	public Booking findOne(int bookingId){
		return bookingRepository.findOne(bookingId);
	}
	
	public Collection<Booking> findAll(){
		return bookingRepository.findAll();
	}
	
	public Booking create(){
		checkPrincipal("PILGRIM");
		Pilgrim pilgrim = getPilgrim();
		Booking result = new Booking();
		result.setCreationBookMoment(new Date());
		result.setPilgrim(pilgrim);
	
		return result;
	}
	
	public void save(Booking booking){
		///kjhakjfhjf
		Assert.notNull(booking);
		checkPrincipal("PILGRIM");
		checkPrincipal(booking);
		Assert.isTrue(booking.getArrivalDate().compareTo(new Date())>0);
			
		bookingRepository.saveAndFlush(booking);

	}
	
	public void delete(Booking booking){
		Assert.notNull(booking);
		System.out.println(booking.toString());
		checkPrincipal(booking);
		bookingRepository.delete(booking);
	}
	
	// ----------------- Other business methods ------------------

	// REPOSITORIO:
	public Collection<Booking> findByPilgrim(){
		UserAccount userAccount=LoginService.getPrincipal();
		Pilgrim pilgrim=pilgrimService.findByUserAccount(userAccount);
		Collection<Booking> result=bookingRepository.findByPilgrimIdOrdered(pilgrim.getId());
		return result;
	}
	
	public Collection<Booking> findByBookingAndInnkeeperId(int innKeeperId){
		Collection<Booking> result=bookingRepository.findByBookingAndInnkeeperId(innKeeperId);
		return result;
	}
	
	public Collection<Booking> findByBookingAndPilgrimId(int pilgrimId){
		Collection<Booking> result=bookingRepository.findByBookingAndPilgrimId(pilgrimId);
		return result;
	}
	
	public Collection<Booking> findBookingByInnkeeperPilgrim(int innKeeperId, int pilgrimId){
		Collection<Booking> result=bookingRepository.findByBookingAndInnkeeperIdAndPilgrimId(innKeeperId,pilgrimId);
		return result;
	}
	public Collection<Booking> findBookingByInnkeeper(int innkeeperId){
		Collection<Booking> result = bookingRepository.findBookingByInnkeeper(innkeeperId);
		return result;
	}
	
	// CASOS DE USO:
	
	public Collection<Booking> dayToDayBookings(Date date){
		Innkeeper innkeeper = getInnkeeper();
		Collection<Booking> bookingsDB = bookingRepository.findByInnkeeperIdAndOrderedByArrivalDate(innkeeper.getId());
		Collection<Booking> result = new ArrayList<Booking>();
		
		for(Booking aux : bookingsDB){
			Integer numberOfNights= aux.getNumberOfNights();
			Date arrivalDate = aux.getArrivalDate();
			
			while(numberOfNights>0){		// esto comprueba si un Booking pertenece a ese d�a
				if(date.compareTo(arrivalDate)==0)
					result.add(aux);
				else{
					arrivalDate = new Date(arrivalDate.getTime() + 86400000);		// Fecha + 1 D�a en ms
					numberOfNights--;
				}
			}
		}
		
		return result;
	}
	
	// FORMULARIOS:
	
	public BookingForm constructBookingForm(Booking booking){
		BookingForm result= new BookingForm();
		
		result.setArrivalDate(booking.getArrivalDate());
		result.setNumberOfNights(booking.getNumberOfNights());
		result.setNumberOfBeds(booking.getNumberOfBeds());
		result.setPricePerNight(booking.getPricePerNight());
		
		if(booking.getBookComments()!=null)
			result.setBookComments(booking.getBookComments());
		
		result.setBookingId(booking.getId());
		return result;
	}
	
	public LodgeAssessmentForm constructLodgeAssessmentForm(Booking booking){
		LodgeAssessmentForm result= new LodgeAssessmentForm();
		
		LodgeAssessment lodgeAssessment = booking.getLodgeAssessment();
		if(lodgeAssessment!=null){
			if(lodgeAssessment.getComments()!=null)
				result.setComments(lodgeAssessment.getComments());
			if(lodgeAssessment.getLocationRate()!=null)
				result.setLocationRate(lodgeAssessment.getLocationRate());
			if(lodgeAssessment.getPriceRate()!=null)
				result.setPriceRate(lodgeAssessment.getPriceRate());
			if(lodgeAssessment.getRoomsRate()!=null)
				result.setRoomsRate(lodgeAssessment.getRoomsRate());
			if(lodgeAssessment.getServiceRate()!=null)
				result.setServiceRate(lodgeAssessment.getServiceRate());
		}
		
		result.setBookingId(booking.getId());
		return result;
	}
	
	public Booking reconstruct(LodgeAssessmentForm lodgeAssessmentForm){
		Booking booking = findOne(lodgeAssessmentForm.getBookingId());
		Assert.isNull(booking, "Nunca debe ser null");
		
		Assert.isTrue(new Date(booking.getArrivalDate().getTime() + booking.getNumberOfNights()*86400000)
						.compareTo(new Date()) <=0, "No has completado la instancia, ergo, nada de LodgeAssessment");
		
		LodgeAssessment lodgeAssessment = booking.getLodgeAssessment();
		
		if(lodgeAssessmentForm.getComments()!=null)
			lodgeAssessment.setComments(lodgeAssessmentForm.getComments());
		if(lodgeAssessmentForm.getLocationRate()!=null)
			lodgeAssessment.setLocationRate(lodgeAssessmentForm.getLocationRate());
		if(lodgeAssessmentForm.getPriceRate()!=null)
			lodgeAssessment.setPriceRate(lodgeAssessmentForm.getPriceRate());
		if(lodgeAssessmentForm.getRoomsRate()!=null)
			lodgeAssessment.setRoomsRate(lodgeAssessmentForm.getRoomsRate());
		if(lodgeAssessmentForm.getServiceRate()!=null)
			lodgeAssessment.setServiceRate(lodgeAssessmentForm.getServiceRate());
		
		booking.setLodgeAssessment(lodgeAssessment);
		
		return booking;
	}
	
	public Booking reconstruct(BookingForm bookingForm){
		Booking result;
		
		if(bookingForm.getBookingId()==null || bookingForm.getBookingId()==0)
			result = create();
		else
			result = findOne(bookingForm.getBookingId());
		
		result.setArrivalDate(bookingForm.getArrivalDate());
		result.setNumberOfNights(bookingForm.getNumberOfNights());
		result.setNumberOfBeds(bookingForm.getNumberOfBeds());
		result.setPricePerNight(bookingForm.getPricePerNight());
		result.setLodge(bookingForm.getLodge());

		if(bookingForm.getBookComments()!=null)
			result.setBookComments(bookingForm.getBookComments());		
		return result;
	}
	
	// AUXILIARES:
	
	public Pilgrim getPilgrim(){
		UserAccount userAccount = LoginService.getPrincipal();
		Pilgrim result = pilgrimService.findByUserAccount(userAccount);
		Assert.notNull(result);
		return result;
	}
	
	public Innkeeper getInnkeeper(){
		UserAccount userAccount = LoginService.getPrincipal();
		Innkeeper result = innkeeperService.findByUserAccount(userAccount);
		Assert.notNull(result);
		return result;
	}
	
	private Boolean isPilgrim(){
		UserAccount userAccount = LoginService.getPrincipal();
		Boolean result = userAccount.getAuthorities().iterator().next().getAuthority().equals("PILGRIM");
		return result;
	}
	
	private void isKeeper(UserAccount account){
		Collection<Authority> authorities= account.getAuthorities();
		Boolean res=false;
		for(Authority a:authorities){
			if(a.getAuthority().equals("INNKEEPER")) res=true;
		}
		Assert.isTrue(res);
	}
	
	private void checkPrincipal(String authority) {
		UserAccount account=LoginService.getPrincipal();
		Collection<Authority> authorities= account.getAuthorities();
		Boolean res=false;
		for(Authority a:authorities){
			if(a.getAuthority().equals(authority)) res=true;			
		}
		Assert.isTrue(res);
	}
	
	private void checkPrincipal(Booking booking){
		UserAccount userAccount=LoginService.getPrincipal();
		Pilgrim pilgrim=pilgrimService.findByUserAccount(userAccount);
		Assert.notNull(pilgrim);
		Assert.isTrue(pilgrim.getId()==booking.getPilgrim().getId());
	}

	public Collection<Booking> findByLodge(int lodgeId) {
		Collection<Booking> result = bookingRepository.findByLodge(lodgeId);
		return result;
	}

	public Collection<Booking> findAllWithoutInvoice() {
		isKeeper(LoginService.getPrincipal());
		UserAccount user=LoginService.getPrincipal();
		Innkeeper keeper=innkeeperService.findByUserAccount(user);
		
		Collection<Booking> allBook=bookingRepository.findByBookingAndInnkeeperId(keeper.getId());
		Collection<Invoice> allInvo=invoiceService.findByKeeper(keeper.getId());
		
		List<Booking> result=new ArrayList<Booking>();
		for(Booking b:allBook){
			Boolean withInvoice=false;
			for(Invoice i:allInvo){
				if(i.getBooking().getId()==b.getId()) withInvoice=true;
			}
			if(withInvoice==false) result.add(b);
		}
		return result;
	}
}
