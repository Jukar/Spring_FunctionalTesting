package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import domain.Pilgrim;
import domain.Register;
import domain.StageRating;
import repositories.StageRatingRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import org.springframework.util.Assert;

@Service
@Transactional
public class StageRatingService {
	
	// ------------------- Managed repository --------------------
	
	@Autowired
	private StageRatingRepository stageRatingRepository;
	
	// ------------------- Supporting services -------------------
	
	@Autowired
	private PilgrimService pilgrimService;
	@Autowired
	private RegisterService registerService;
	
	// ----------------------- Constructor -----------------------
	
	// ------------------- Simple CRUD methods -------------------
	
	public StageRating findOne(int stageRatingId) {
		return stageRatingRepository.findOne(stageRatingId);
	}
	
	public Collection<StageRating> findAll(){
		return stageRatingRepository.findAll();
	}
	
	public StageRating create(){
		StageRating stageRating = new StageRating();
		stageRating.setStartMoment(new Date());
		stageRating.setEndMoment(new Date());
		return stageRating;
	}
	
	public void save(StageRating stageRating){
		Assert.notNull(stageRating);
		UserAccount userAccount=LoginService.getPrincipal();
		Assert.isTrue(isPilgrim(userAccount));
		
		Pilgrim pilgrim = getPilgrim(userAccount);
		Assert.isTrue(stageRating.getRegister().getPilgrim()==pilgrim);
		
		stageRatingRepository.save(stageRating);
	}
	
//	public void delete (StageRating stageRating){
//	}
	
	// ----------------- Other business methods ------------------
	
	// REPOSITORIO:
	
	// CASOS DE USO:
	
	public Collection<StageRating> findByRouteId(int routeId){
		Collection<Register> register = registerService.findByRouteId(routeId);
		
		UserAccount userAccount=LoginService.getPrincipal();
		Pilgrim pilgrim = getPilgrim(userAccount);

		List<StageRating> result=new ArrayList<StageRating>();
		for(Register r:register){
			if(r.getPilgrim().getId()==pilgrim.getId()){
				for(StageRating sR:r.getStageRatings()){
					result.add(sR);
				}
			}
		}
		
		return result;
	}
	
	public Collection<StageRating> findByPilgrimId(){
		UserAccount userAccount=LoginService.getPrincipal();
		Pilgrim pilgrim = getPilgrim(userAccount);
		Collection<StageRating> result = stageRatingRepository.findByPilgrimId(pilgrim.getId());
		return result;
	}
	
	// AUXILIARES
	
	public Boolean isPilgrim(UserAccount userAccount){
		Boolean result=false;
		Collection<Authority> authorities=userAccount.getAuthorities();
		
		for(Authority a:authorities){
			if(a.getAuthority().equals("PILGRIM"))
				result=true;
		}
		return result;
	}
	
	private Pilgrim getPilgrim(UserAccount userAccount){
		Pilgrim result = pilgrimService.findByUserAccount(userAccount);
		return result;
	}
	
}