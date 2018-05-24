package services;

import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.StageOrderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Route;
import domain.Stage;
import domain.StageOrder;

@Service
@Transactional
public class StageOrderService {
	
	// ------------------- Managed repository --------------------
	
	@Autowired
	private StageOrderRepository stageOrderRepository;
	
	// ------------------- Supporting services -------------------

	@Autowired
	private RouteService routeService;
	@Autowired
	private StageService stageService;
	
	// ----------------------- Constructor -----------------------
	
	// ------------------- Simple CRUD methods -------------------
	
	public StageOrder create(){
		return new StageOrder();
	}
	
	public StageOrder findOne(int stageOrderId){
		return stageOrderRepository.findOne(stageOrderId);
	}
	
	public Collection<StageOrder> findAll(){
		return stageOrderRepository.findAll();
	}
	
	public void save(StageOrder stageOrder) {
		Assert.notNull(stageOrder);
		Assert.isTrue(isAdministrator());
		
		stageOrderRepository.save(stageOrder);
	}
	
	public void delete (StageOrder stageOrder){
		Assert.notNull(stageOrder);
		Assert.isTrue(isAdministrator());
		
		
		
		stageOrderRepository.delete(stageOrder);
	}
	
	// ----------------- Other business methods ------------------
	
	// REPOSITORIO:
	
	// CASOS DE USO:
	
	public void registerStageOnRoute(int routeId, int stageId){
		Route route = routeService.findOne(routeId);
		Stage stage = stageService.findOne(stageId);
		
		Collection<StageOrder> stageOrders = route.getStageOrders();
		StageOrder stageOrder = create();
		stageOrder.setNumber(stageOrders.size()+1);
		stageOrder.setRoute(route);
		stageOrder.setStage(stage);
		
		stageOrders.add(stageOrder);
		route.setStageOrders(stageOrders);
		
//		routeService.save(route);
		save(stageOrder);
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