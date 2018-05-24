package services;

import java.util.ArrayList;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.StageRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Route;
import domain.Stage;
import domain.StageOrder;
@Service
@Transactional
public class StageService {
	
	// ------------------- Managed repository --------------------
	
	@Autowired
	private StageRepository stageRepository;
	
	// ------------------- Supporting services -------------------

	@Autowired
	private RouteService routeService;
	@Autowired
	private StageOrderService stageOrderService;
	
	// ----------------------- Constructor -----------------------
	
	// ------------------- Simple CRUD methods -------------------
	
	public Stage create(){
		return new Stage();
	}
	
	public Stage findOne(int stageId){
		return stageRepository.findOne(stageId);
	}
	
	public Collection<Stage> findAll(){
		return stageRepository.findAll();
	}
	
//	Error de getters en los atributos derivados
	public void save(Stage stage) {
		Assert.notNull(stage);
		Assert.isTrue(isAdministrator());
		
		stageRepository.save(stage);
	}
	
	public void delete (Stage stage){
		Assert.notNull(stage);
		Assert.isTrue(isAdministrator());
		Collection<StageOrder> stageOrders = stageOrderService.findAll();
		for(StageOrder aux : stageOrders)
			Assert.isTrue(!aux.getStage().equals(stage));
		stageRepository.delete(stage);
	}
	
	// ----------------- Other business methods ------------------
	
	// REPOSITORIO:
	
	// CASOS DE USO:
	
	public Collection<Stage> findByRouteId(int routeId){
		Route route = routeService.findOne(routeId);
		Collection<Stage> result = new ArrayList<Stage>();
		
		for(StageOrder aux : route.getStageOrders())
			result.add(aux.getStage());
		
		return result;
	}
	
	public Collection<Stage> findNotInRouteByRouteId(int routeId){
		Collection<Stage> result  = findAll();
		result.removeAll(findByRouteId(routeId));
		return result;
	}
	
	// AUXILIARES
	
	Boolean isAdministrator(){
		UserAccount userAccount = LoginService.getPrincipal();
		Boolean result=false;
		Collection<Authority> authorities=userAccount.getAuthorities();
		
		for(Authority a:authorities){
			if(a.getAuthority().equals("ADMIN"))
				result=true;
		}
		return result;
	}
}