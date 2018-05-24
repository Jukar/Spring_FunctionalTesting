package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Actor;
import domain.Complaint;
import domain.Discuss;

import repositories.DiscussRepository;
import security.LoginService;
import security.UserAccount;

@Transactional
@Service
public class DiscussService {
//	Managed repository -----------------------------------------	
	@Autowired
	private DiscussRepository discussRepository;
//	Supporting services ----------------------------------------
	@Autowired
	private ActorService actorService;
	@Autowired
	private ComplaintService complaintService;
//	Constructor ------------------------------------------------
//	Simple CRUD methods ----------------------------------------
	public Discuss create(Complaint complaint){
		Discuss result=new Discuss();
		UserAccount userAccount=LoginService.getPrincipal();
		Actor principal=actorService.findByUserAccount(userAccount);
		result.setActor(principal);
		result.setComplaint(complaint);

		Date moment=new Date(System.currentTimeMillis()-1);
		result.setMomentDiscuss(moment);
		return result;
	}
	public Discuss findOne(int discussId){
		Discuss result;
		result=discussRepository.findOne(discussId);
		return result;
	}
//	public Collection<Discuss> findAll(){
//		Collection<Discuss> result;
//		return result;
//	}
	public void save(Discuss discuss){
		Date moment=new Date(System.currentTimeMillis()-1);
		discuss.setMomentDiscuss(moment);

		Complaint complaint=discuss.getComplaint();
		Collection<Discuss> discusses=complaint.getDiscusses();
		discusses.add(discuss);
		complaint.setDiscusses(discusses);
		complaintService.save(complaint);
	}
//	public void delete(Discuss discuss){
//		
//	}
//	Other business methods -------------------------------------
	public Collection<Discuss> findByComplaintId(int complaintId){
		Collection<Discuss> result;
		result=discussRepository.findByComplaintId(complaintId);
		return result;
	}
}
