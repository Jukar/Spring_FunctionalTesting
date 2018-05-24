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
import security.UserAccount;
import domain.Anecdote;
import domain.Complaint;
import domain.CreditCard;
import domain.Folder;
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
		CreditCard creditCard=new CreditCard();
		result.setCreditCard(creditCard);
		
		Folder inbox = new Folder();
		inbox.setName("Inbox");
		Folder outbox = new Folder();
		outbox.setName("Outbox");
		Folder trashbox = new Folder();
		trashbox.setName("Trashbox");
		
		Collection<Folder> folders = new ArrayList<Folder>();
		folders.add(inbox);
		folders.add(outbox);
		folders.add(trashbox);
		result.setFolders(folders);
	
		return result;
	}
	
	public Collection<Pilgrim> findAll(){
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
//		Assert.notNull(keyword);
//		Collection<Pilgrim> result = pilgrimRepository.findPilgrimByKeyword("'%"+keyword+"%'");
//		return result;
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
		Collection<Pilgrim> result = pilgrimRepository.listPilgrimRegistrationDesc();
		return result;
	}
	
	public Collection<Pilgrim> findPilgrimWhithMoreAnecdotes(){
		Collection<Pilgrim> result = pilgrimRepository.findPilgrimWhithMoreAnecdotes();
		return result;
	}
	
	public Collection<Pilgrim> findPilgimMoreComplaint(){
		Collection<Pilgrim> result = pilgrimRepository.findPilgimMoreComplaint();
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
	
}
