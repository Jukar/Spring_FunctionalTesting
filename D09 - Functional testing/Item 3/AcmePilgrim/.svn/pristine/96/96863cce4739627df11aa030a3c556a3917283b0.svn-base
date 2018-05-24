package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import domain.Pilgrim;
import domain.Register;
import domain.Route;
import domain.Stage;
import domain.StageRating;
import repositories.RegisterRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import org.springframework.util.Assert;

@Service
@Transactional
public class RegisterService {
	
	// ------------------- Managed repository --------------------
	
	@Autowired
	private RegisterRepository registerRepository;
	
	// ------------------- Supporting services -------------------
	
	@Autowired
	private PilgrimService pilgrimService;
	@Autowired
	private StageService stageService;
	@Autowired
	private StageRatingService stageRatingService;
	
	// ----------------------- Constructor -----------------------
	
	// ------------------- Simple CRUD methods -------------------
	
	public Register findOne(int registerId){
		return registerRepository.findOne(registerId);
	}
	
	public Collection<Register> findAll(){
		return registerRepository.findAll();
	}
	
	public Register create(){
		Register register = new Register();
		UserAccount userAccount=LoginService.getPrincipal();
		register.setPilgrim(getPilgrim(userAccount));
		return register;
	}
	
	public void save(Register register){
		UserAccount userAccount=LoginService.getPrincipal();
		Assert.isTrue(isPilgrim(userAccount));
		Assert.notNull(register);
		
		Pilgrim pilgrim = getPilgrim(userAccount);
		Collection<Register> registers = pilgrim.getRegisters();
		
		registers.add(register);
		pilgrim.setRegisters(registers);
		pilgrimService.save(pilgrim);
		registerRepository.save(register);
	}
	
	// ----------------- Other business methods ------------------
	
	// REPOSITORIO:
	
	// CASOS DE USO:
	
	public void registerOnRoute(Route route){
		UserAccount userAccount=LoginService.getPrincipal();
		Pilgrim pilgrim = getPilgrim(userAccount);
		Assert.notNull(route);
		Assert.notNull(pilgrim);
		
		Register register = create();
		register.setRoute(route);
		
		save(register);
	}
	
	public void registerStageRating(StageRating stageRating){
		UserAccount userAccount=LoginService.getPrincipal();
		Assert.notNull(stageRating);
		Assert.isTrue(isPilgrim(userAccount));
		
		Stage stage = stageRating.getStage();
		Collection<StageRating> stageRatingsStage = stage.getStageRatings();
		stageRatingsStage.add(stageRating);
		stage.setStageRatings(stageRatingsStage);
		Register register = stageRating.getRegister();
		Assert.isTrue(isPrincipal(register, userAccount));
		Collection<StageRating> stageRatingsRegister = register.getStageRatings();
		stageRatingsRegister.add(stageRating);
		register.setStageRatings(stageRatingsRegister);
		stageRatingService.save(stageRating);
		stageService.save(stage);
		save(register);
	}
	
	public Collection<Register> findByRouteId(int routeId) {
		return registerRepository.findByRouteId(routeId);
	}
	
	// AUXILIARES
	
	private Boolean isPrincipal(Register register, UserAccount userAccount) {
		Boolean result = false;
		Assert.isTrue(isPilgrim(userAccount));
		
		Pilgrim pilgrim = getPilgrim(userAccount);
		Collection<Register> registers = pilgrim.getRegisters();
		
		for(Register aux : registers){
			if(aux.getId() == register.getId())
				result = true;
		}
		return result;
	}
	
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

	public Collection<Register> findByRoutePilgrimId(int routeId) {
		UserAccount usA=LoginService.getPrincipal();
		Pilgrim p=pilgrimService.findByUserAccount(usA);
		
		Collection<Register> regsPilgrim=p.getRegisters();
		Collection<Register> regsRoute=registerRepository.findByRouteId(routeId);
		List<Register> result=new ArrayList<Register>();
		
		for(Register r:regsPilgrim){
			Boolean ex=false;
			for(Register ro:regsRoute){
				if(r.equals(ro)) ex=true;
			}
			if(ex) result.add(r);
		}
		return result;
	}
}