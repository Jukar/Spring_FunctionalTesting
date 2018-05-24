package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrator;
import domain.Complaint;
import domain.Discuss;
import domain.Pilgrim;
import forms.ComplaintForm;

import repositories.ComplaintRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ComplaintService {
	
	//	Managed repository -----------------------------------------
	@Autowired
	private ComplaintRepository complaintRepository;
	
	//	Supporting services ----------------------------------------
	@Autowired
	private PilgrimService pilgrimService;
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private ActorService actorService;
	//	Constructor ------------------------------------------------

	public ComplaintService() {
		super();
	}
	//	Simple CRUD methods ----------------------------------------
	public Complaint create(){
		UserAccount account=LoginService.getPrincipal();
		checkPrincipal("PILGRIM");
		Pilgrim pilgrim=pilgrimService.findByUserAccount(account);
		
		Complaint result=new Complaint();
		result.setPilgrim(pilgrim);
		result.setStatus("OPEN");
		Collection<Discuss> discusses=new ArrayList<Discuss>();
		result.setDiscusses(discusses);	
		
		return result;
	}
	
	public void save(Complaint complaint){
		checkPrincipalSave(complaint);
		complaintRepository.save(complaint);
	}
	
	public Complaint findOne(int id){
		Complaint result;
		result=complaintRepository.findOne(id);
		return result;
	}
	
	public Collection<Complaint> findAll(){
		checkPrincipal("ADMIN");
		Collection<Complaint> result;
		result=complaintRepository.findAll();
		return result;
	}
	
	//	Other business methods -------------------------------------
	public Complaint reconstruct(ComplaintForm complaintForm){
		Complaint result;
		result=create();
		result.setTitle(complaintForm.getTitle());
		result.setDescription(complaintForm.getDescription());
		result.setResolution(complaintForm.getResolution());
		return result;
	}
	
	public ComplaintForm construct(Complaint complaint){
		ComplaintForm result= new ComplaintForm();
		result.setTitle(complaint.getTitle());
		result.setDescription(complaint.getDescription());
		result.setResolution(complaint.getResolution());
		return result;
	}
	
	public Collection<Complaint> findByPilgrim() {
		UserAccount userAccount= LoginService.getPrincipal();
		Pilgrim pilgrim=pilgrimService.findByUserAccount(userAccount);
		Collection<Complaint> result;
		result=complaintRepository.findByPilgrimId(pilgrim.getId());
		return result;
	}

	public Collection<Complaint> findByAdmin() {
		UserAccount userAccount=LoginService.getPrincipal();
		Administrator administrator=administratorService.findByUserAccount(userAccount);
		Collection<Complaint> result;
		result=complaintRepository.findByAdministratorId(administrator.getId());
		return result;
	}
	public void saveCreate(Complaint complaint){
		checkPrincipalSave(complaint);
		Date moment=new Date(System.currentTimeMillis()-1);
		complaint.setCreationMoment(moment);
		complaintRepository.save(complaint);
	}

	public void handle(Complaint complaint){
		checkPrincipal("ADMIN");
		Assert.isTrue(complaint.getAdministrator()==null);
		UserAccount account=LoginService.getPrincipal();
		Administrator administrator=administratorService.findByUserAccount(account);
		complaint.setAdministrator(administrator);
		complaintRepository.save(complaint);	
	}

	
	public void cancel(Complaint complaint){
		checkPrincipal("PILGRIM");
		checkPrincipalSave(complaint);
		complaint.setStatus("CANCELLED");
		save(complaint);	
	}
	
	public void close(Complaint complaint){
		checkPrincipal("ADMIN");
		checkPrincipalSave(complaint);
		complaint.setStatus("CLOSED");
		save(complaint);
	}
	
	private void checkPrincipalSave(Complaint complaint) {
		UserAccount account=LoginService.getPrincipal();
		Actor principal=actorService.findByUserAccount(account); 
		Boolean res=false;
		if(principal instanceof Pilgrim){
			if(principal.equals(complaint.getPilgrim())){
				res=true;
			}
		}if(principal instanceof Administrator)
			if(principal.equals(complaint.getAdministrator())){
				res=true;
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
	public Double avrComplaint() {
		Double result=complaintRepository.avrComplaint();
		return result;
	}


}
