package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.PilgrimRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Anecdote;
import domain.Booking;
import domain.Complaint;
import domain.CreditCard;
import domain.Folder;
import domain.Innkeeper;
import domain.Invoice;
import domain.Pilgrim;
import domain.Register;
import forms.PilgrimForm;

@Transactional
@Service
public class PilgrimService {
	
	//	Managed repository -----------------------------------------	
	@Autowired
	private PilgrimRepository pilgrimRepository;
	//	Supporting services ----------------------------------------
	@Autowired
	private BookingService bookingService;
	@Autowired
	private InnkeeperService innkeeperService;
	@Autowired
	private InvoiceService invoiceService;
	//	Constructor ------------------------------------------------
	//	Simple CRUD methods ----------------------------------------
	public Pilgrim create(){
		Pilgrim result=new Pilgrim();
		
		UserAccount userAccount=new UserAccount();
		List<Authority> authorities=new ArrayList<Authority>();
		Authority a=new Authority();
		a.setAuthority(Authority.PILGRIM);
		authorities.add(a);
		userAccount.setAuthorities(authorities);
		
		result.setUserAccount(userAccount);
		
		Collection<Complaint>complaints = new ArrayList<Complaint>();
		result.setComplaints(complaints);
		Collection<Register>registers = new ArrayList<Register>();
		result.setRegisters(registers);
		Collection<Anecdote>anecdotes = new ArrayList<Anecdote>();
		result.setAnecdotes(anecdotes);
		Collection<Booking>bookings = new ArrayList<Booking>();
		result.setBookings(bookings);
		CreditCard creditCard=new CreditCard();
		result.setCreditCard(creditCard);
		
		Folder inbox = new Folder();
		inbox.setName("inbox");
		Folder outbox = new Folder();
		outbox.setName("outbox");
		Folder trashbox = new Folder();
		trashbox.setName("trashbox");
		
		Collection<Folder> folders = new ArrayList<Folder>();
		folders.add(inbox);
		folders.add(outbox);
		folders.add(trashbox);
		result.setFolders(folders);
	
		return result;
	}
	
	public Collection<Pilgrim> findAll(){
		checkPrincipal("ADMIN");
		Collection<Pilgrim>result = pilgrimRepository.findAll();
		return result;
	}
	
	public Pilgrim findOne(int pilgrimId){
		Pilgrim result = pilgrimRepository.findOne(pilgrimId); 
		return result;
	}

	public void save(Pilgrim pilgrim) {
		Assert.notNull(pilgrim);
		pilgrimRepository.saveAndFlush(pilgrim);
	}
	
	//	Other business methods -------------------------------------
	public Pilgrim findByUserAccount(UserAccount userAccount){
		Assert.notNull(userAccount);
		Pilgrim result = pilgrimRepository.findByUserAccount(userAccount);
		return result;
	}
	
	public Collection<Pilgrim> findPilgrimByKeyword(String keyword){
		checkPrincipal("ADMIN");
		Assert.notNull(keyword);
		Collection<Pilgrim> result = new ArrayList<Pilgrim>();
		Collection<Pilgrim> all = pilgrimRepository.findAll();
		for(Pilgrim item : all){
			if(item.getName().contains(keyword) || item.getSurname().contains(keyword) || item.getEmailAddress().contains(keyword)){
				result.add(item);
			}
		}
		
		return result;
	}
	
	public Collection<Pilgrim> listPilgrimRegistrationDesc(){
		checkPrincipal("ADMIN");
		Collection<Pilgrim> result = pilgrimRepository.listPilgrimRegistrationDesc();
		return result;
	}
	
	public Collection<Pilgrim> findPilgrimWhithMoreAnecdotes(){
		checkPrincipal("ADMIN");
		Collection<Pilgrim> result = pilgrimRepository.findPilgrimWhithMoreAnecdotes();
		return result;
	}
	
	public Collection<Pilgrim> findPilgimMoreComplaint(){
		checkPrincipal("ADMIN");
		Collection<Pilgrim> result = pilgrimRepository.findPilgimMoreComplaint();
		return result;
	}
	
	public Collection<Pilgrim> findPilgrimMoreUnpaidInvoices(){
		isAdmin(LoginService.getPrincipal());
		Integer total=0;
		List<Pilgrim> result=new ArrayList<Pilgrim>();
		
		List<Pilgrim> all=pilgrimRepository.findAll();
		for(Pilgrim p:all){
			if(invoiceService.findByPilgrimNotPaid(p.getId())!=null){
				if(invoiceService.findByPilgrimNotPaid(p.getId()).size()>total){
					result.add(p);
				}
			}
		}
		return result;
	}
	
	public Collection<String> findPilgrimProfile(Pilgrim pilgrim){
		Collection<String> result = new ArrayList<String>();
		result.add(pilgrim.getName());
		result.add(pilgrim.getSurname());
		result.add(pilgrim.getEmailAddress());
		result.add(pilgrim.getContactPhone());
		result.add(pilgrim.getBirthDate().toString());
		result.add(pilgrim.getNationality());
		return result;
	}

	public Pilgrim reconstruct(PilgrimForm pilgrimForm) {
		Assert.isTrue(pilgrimForm.getPassword().equals(pilgrimForm.getConfirmPassword()));
		Assert.isTrue(pilgrimForm.isAccepConditions());
		Pilgrim result=create();
		UserAccount userAccount=result.getUserAccount();
		userAccount.setUsername(pilgrimForm.getUserName());

		
		Md5PasswordEncoder encoder;		
		encoder= new Md5PasswordEncoder();
		String password=encoder.encodePassword(pilgrimForm.getPassword(), null);
		userAccount.setPassword(password);
		
		result.setUserAccount(userAccount);
		
		result.setName(pilgrimForm.getName());
		result.setSurname(pilgrimForm.getSurname());
		result.setEmailAddress(pilgrimForm.getEmailAddress());
		result.setContactPhone(pilgrimForm.getContactPhone());
		result.setUrl(pilgrimForm.getUrl());
		result.setBirthDate(pilgrimForm.getBirthDate());
		result.setNationality(pilgrimForm.getNationality());
		result.setCreditCard(pilgrimForm.getCreditCard());
		
		return result;
	}
	
	public Collection<Pilgrim> listPilgrimBirthDateDesc(){
		LoginService.getPrincipal();
		Collection<Pilgrim> result=pilgrimRepository.listPilgrimBirthDateDesc();
		return result;
	}
	
	public Collection<Pilgrim> findPilgrimMoreBookings(){
		isKeeperOrAdmin(LoginService.getPrincipal());
		Collection<Pilgrim> result=pilgrimRepository.findPilgrimMoreBookings();
		return result;
	}
	
	public Collection<Pilgrim> findWithMoreMoneySpent(){
		checkPrincipal("ADMIN");
		Collection<Pilgrim> all=pilgrimRepository.findAll();
		List<Pilgrim> result=new ArrayList<Pilgrim>();
		Double maxSpent=0.0;
		for(Pilgrim p:all){
			Double aux=0.0;
			
			Collection<Invoice> invoices=invoiceService.findByPilgrim(p.getId());
			for(Invoice in:invoices){
				if(in.getPaidMoment()!=null){
					aux+=in.getTotalCost().getValue();
				}
			}

			if(aux+1==maxSpent+1){
				result.add(p);
			}
			if(aux>maxSpent){
				result=new ArrayList<Pilgrim>();
				result.add(p);
				maxSpent=aux;
			}
		}
		return result;
	}
	
	public Collection<Pilgrim> findPilgrimWithBookingsByInnkeeper(){
		isCustomer(LoginService.getPrincipal());
		List<Pilgrim> result=new ArrayList<Pilgrim>();
		UserAccount user=LoginService.getPrincipal();
		Innkeeper currentKeeper=innkeeperService.findByUserAccount(user);
		
		Collection<Pilgrim> all=pilgrimRepository.findAll();
		for(Pilgrim p:all){
			Boolean wanted=false;
			for(Booking b:p.getBookings()){
				if(b.getLodge().getInnkeeper().getId()==currentKeeper.getId()){
					wanted=true;
				}
			}
			if(wanted==true){
				result.add(p);
			}
		}
		
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
	private void isAdmin(UserAccount account) {
		Collection<Authority> authorities= account.getAuthorities();
		Boolean res=false;
		for(Authority a:authorities){
			if(a.getAuthority().equals("ADMIN")) res=true;
		}
		Assert.isTrue(res);
	}
	private void isKeeperOrAdmin(UserAccount account) {
		Collection<Authority> authorities= account.getAuthorities();
		Boolean res=false;
		for(Authority a:authorities){
			if(a.getAuthority().equals("INNKEEPER")) res=true;
			if(a.getAuthority().equals("ADMIN")) res=true;
		}
		Assert.isTrue(res);
	}
	private void isCustomer(UserAccount account) {
		Collection<Authority> authorities= account.getAuthorities();
		Boolean res=false;
		for(Authority a:authorities){
			if(a.getAuthority().equals("INNKEEPER")) res=true;
			if(a.getAuthority().equals("PILGRIM")) res=true;
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
}
