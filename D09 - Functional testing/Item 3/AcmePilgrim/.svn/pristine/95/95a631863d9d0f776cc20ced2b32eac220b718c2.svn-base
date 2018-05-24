package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AnecdoteRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Anecdote;
import domain.Pilgrim;
import forms.AnecdoteForm;

@Service
@Transactional
public class AnecdoteService {
//	Managed repository -----------------------------------------
	@Autowired
	private AnecdoteRepository anecdoteRepository;
//	Supporting services ----------------------------------------
	@Autowired
	private PilgrimService pilgrimService;
//	Constructor ------------------------------------------------
//	Simple CRUD methods ----------------------------------------
	public Anecdote create(){
		UserAccount userAccount=LoginService.getPrincipal();
		checkPrincipal("PILGRIM");
		Pilgrim pilgrim=pilgrimService.findByUserAccount(userAccount);
		Anecdote result=new Anecdote();
		
		result.setCreationMoment(new Date());
		result.setPilgrim(pilgrim);
		
		return result;
	}
	public Anecdote findOne(int anecdoteId){
		Assert.notNull(anecdoteId);
		Anecdote result=anecdoteRepository.findOne(anecdoteId);
		return result;
	}
	public Collection<Anecdote> findAll(){
		Collection<Anecdote> result=anecdoteRepository.findAll();
		return result;
	}
	public void save(Anecdote anecdote){
		Assert.notNull(anecdote);
		System.out.println("1");
		checkPrincipal(anecdote);
		System.out.println("2");
		anecdoteRepository.save(anecdote);
		System.out.println("3");
	}
	public void delete(Anecdote anecdote){
		Assert.notNull(anecdote);
		checkPrincipal(anecdote);
		anecdoteRepository.delete(anecdote);
	}
//	Other business methods -------------------------------------
	public Anecdote reconstruct(AnecdoteForm anecdoteForm){
		Anecdote result;
		result=create();
		result.setTitle(anecdoteForm.getTitle());
		result.setDescription(anecdoteForm.getDescription());
		return result;
	}
	
	public AnecdoteForm construct(Anecdote anecdote){
		AnecdoteForm result= new AnecdoteForm();
		result.setTitle(anecdote.getTitle());
		result.setDescription(anecdote.getDescription());
		return result;
	}
	
	public Collection<Anecdote> findByPilgrimId(int pilgrimId){
		Collection<Anecdote> result=anecdoteRepository.findByPilgrimId(pilgrimId);
		return result;
	}
	
	public Integer countAnecdotes(){
		Integer result=anecdoteRepository.countAnecdotes();
		return result;
	}
	
	public Double avrAnecdotes(){
		Double result=anecdoteRepository.avrAnecdotes();
		return result;
	}
	
	
	
	private void checkPrincipal(Anecdote anecdote){
		System.out.println("test" + anecdote.getPilgrim());
		UserAccount userAccount=LoginService.getPrincipal();
		Pilgrim pilgrim=pilgrimService.findByUserAccount(userAccount);
		System.out.println("pilgrim " + pilgrim.getId());
		System.out.println("pilgrim " + anecdote.getPilgrim().getId());
		System.out.println("4");
		boolean res = false;
		System.out.println("dasfas" + res);
		if(pilgrim.getId()==anecdote.getPilgrim().getId()){
			res = true;
		}
		System.out.println(res);
//		Assert.isTrue(pilgrim.getId()==anecdote.getPilgrim().getId());
		System.out.println("5");
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
