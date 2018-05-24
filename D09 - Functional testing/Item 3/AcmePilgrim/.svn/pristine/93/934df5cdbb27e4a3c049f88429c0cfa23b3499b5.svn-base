package services;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

import domain.Lodge;
import domain.Request;
import forms.RequestForm;

@Service
@Transactional
public class RequestService {
	// ------------------- Managed repository --------------------
	@Autowired
	private RequestRepository requestRepository;
	// ------------------- Supporting services -------------------
	@Autowired
	private LodgeService lodgeService;
	// ----------------------- Constructor -----------------------
	
	// ------------------- Simple CRUD methods -------------------
	public Request findOne(int requestId){
		return requestRepository.findOne(requestId);
	}
	
	public Collection<Request> findAll(){
		return requestRepository.findAll();
	}
	
	public Request create(){
		UserAccount userAccount=LoginService.getPrincipal();
		isInnkeeper(userAccount);
		Request result = new Request();
		result.setStatus("PENDING");
		result.setCode(getCode());
		return result;
	}
	
	public void save(Request request){
		
		Assert.notNull(request);
		Date today = new Date();
		today = new Date(System.currentTimeMillis()-1);
		request.setCreationMoment(today);
		requestRepository.saveAndFlush(request);
	}
	
	public void saveAdmin(Request request){
		Assert.notNull(request);
		Lodge lodge = lodgeService.findOne(request.getLodge().getId());
		Assert.notNull(lodge);
		lodgeService.saveAdmin(lodge);
		requestRepository.save(request);
	}
	
	public void delete(Request request){
		Assert.notNull(request);
		requestRepository.delete(request);
	}
	// ----------------- Other business methods ------------------

	public Request findByLodge(int lodgeId) {
		Request result = requestRepository.findByLodge(lodgeId);
		return result;
	}

	private void isInnkeeper(UserAccount account){
		Collection<Authority> authorities= account.getAuthorities();
		Boolean res=false;
		for(Authority a:authorities){
			if(a.getAuthority().equals("INNKEEPER")) res=true;
		}
		Assert.isTrue(res);
	}
	
	public Collection<Request> requestByInnkeeper(int innkeeperId){
		Collection<Request> result = requestRepository.requestByInnkeeper(innkeeperId);
		for(Request aux:result){
			System.out.println(aux.getCode());
		}
		return result;
	}
	
	private Boolean isAdmin(){
		UserAccount userAccount = LoginService.getPrincipal();
		Boolean result = userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN");
		
		return result;
	}
	
	public void changeStatus(int requestId){
		Assert.isTrue(isAdmin());
		Request request = findOne(requestId);
		request.setStatus("ACCEPTED");
	}
	
	public void acceptRequest(Request request){
		Assert.isTrue(isAdmin());
		Assert.notNull(request);
		request.setStatus("ACCEPTED");
		saveAdmin(request);
	}
	
	public void cancelRequest(Request request){
		Assert.isTrue(isAdmin());
		Assert.notNull(request);
		request.setStatus("REJECTED");
		saveAdmin(request);
	}
	
	public String getCode(){
		String result="";
		String uuid1=UUID.randomUUID().toString().replaceAll("-", "");
		String uuid2=UUID.randomUUID().toString().replaceAll("-", "");
		if(Math.random()<0.5)
			result=uuid1.concat(uuid2);
		else
			result=uuid1+"-"+uuid2;
		return result;
		
	}
	
	//FORMULARIOS:
	
	public RequestForm constructRequestForm(Request request){
		RequestForm result = new RequestForm();
		
		result.setTitle(request.getTitle());
		result.setDescription(request.getDescription());
		System.out.println(result.getTitle());
		System.out.println(result.getDescription());
		if(request.getComments()!=null){
			result.setComments(request.getComments());
			System.out.println(result.getComments());
		}
		if(request.getLodge()!=null){
			result.setLodge(request.getLodge());
			System.out.println(result.getLodge().getName());
		}
		result.setRequestId(request.getId());
		System.out.println(result.getRequestId());
		return result;
	}
	
	public Request reconstruct(RequestForm requestForm){
		Request result;
		if(requestForm.getRequestId()== null){
			result = create();
		}else{
			result = findOne(requestForm.getRequestId());
			
		}
		result.setStatus("PENDING");
		result.setTitle(requestForm.getTitle());
		result.setDescription(requestForm.getDescription());
		result.setLodge(requestForm.getLodge());
		
		if(requestForm.getComments()!=null){
			result.setComments(requestForm.getComments());
		}
		return result;
	}
}
