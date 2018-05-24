package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import domain.Pilgrim;
import domain.Route;
import domain.StageOrder;
import repositories.RouteRepository;
import security.LoginService;
import security.UserAccount;
import org.springframework.util.Assert;

@Service
@Transactional
public class RouteService {
	
	// ------------------- Managed repository --------------------
	
	@Autowired
	private RouteRepository routeRepository;
	
	// ------------------- Supporting services -------------------
	
	@Autowired
	private PilgrimService pilgrimService;
//	@Autowired
//	private AdministratorService administratorService;
	
	// ----------------------- Constructor -----------------------
	
	// ------------------- Simple CRUD methods -------------------
	
	public Route findOne(int routeId) {
		return routeRepository.findOne(routeId);
	}
	
	public Collection<Route> findAll(){
		return routeRepository.findAll();
	}
	
	public Route create(){
		Route route = new Route();
		route.setActived(true);
		route.setStageOrders(new ArrayList<StageOrder>());
		return route;
	}
	
	public void save(Route route){
		
		Assert.notNull(route);
		Assert.isTrue(isAdministrator());
		
//		System.out.println(routeRepository.findAll());
		routeRepository.save(route);
//		System.out.println(routeRepository.findAll());
	}
	
	public void delete (Route route){
		Assert.notNull(route);
		Assert.isTrue(isAdministrator());
		route.setActived(false);
		routeRepository.saveAndFlush(route);
	}
	
	// ----------------- Other business methods ------------------
	
	// REPOSITORIO:
	
	public Collection<Route> routesByRegistrationsDesc() {
		return routeRepository.routesByRegistrationsDesc();
	}
	
	public Collection<Route> activeRoutes() {
		return routeRepository.findActives();
	}
	
	public Collection<Route> routesByAveRatingAsc() {
		return routeRepository.routesByAveRatingAsc();
	}
	
	//	Error de getters en los atributos derivados
	public Collection<Route> routesByKeyword(String keyword){
		Assert.notNull(keyword);
		
		Collection<Route> result = new HashSet<Route>();	//	No admite duplicados
		result.addAll(routeRepository.routeByKeyword("%"+keyword+"%"));
		return result;
	}
	
	// CASOS DE USO:
	
	public Collection<Route> findByPilgrim(){
		UserAccount userAccount = LoginService.getPrincipal();
		Pilgrim pilgrim = getPilgrim(userAccount);
		Collection<Route> result = routeRepository.findByPilgrim(pilgrim);
		return (result==null) ? new ArrayList<Route>() : result;
	}
	
	public Collection<Route> findByPilgrimId(int pilgrimId) {
		Pilgrim pilgrim=pilgrimService.findOne(pilgrimId);
		Collection<Route> routes=new ArrayList<Route>(); 
		routes=routeRepository.findByPilgrim(pilgrim);
		return routes;
	}
	
	// AUXILIARES
	
	private Boolean isAdministrator(){
		Boolean result=false;
		UserAccount userAccount = LoginService.getPrincipal();

		result = userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN");
		
		return result;
	}
	
	private Pilgrim getPilgrim(UserAccount userAccount){
		Pilgrim result = pilgrimService.findByUserAccount(userAccount);
		return result;
	}



//	private Boolean isPrincipal(Route route, UserAccount userAccount) {
//		Boolean result = false;
//		Assert.isTrue(isPilgrim(userAccount));
//	
//		Pilgrim pilgrim = getPilgrim(userAccount);
//		Collection<Route> routes = pilgrim.getRoutes();
//	
//		for(Route aux : routes){
//			if(aux.getId() == route.getId())
//				result = true;
//		}
//		return result;
//	}

//	public Boolean isPilgrim(UserAccount userAccount){
//		Boolean result=false;
//		Collection<Authority> authorities=userAccount.getAuthorities();
//	
//		for(Authority a:authorities){
//			if(a.getAuthority().equals("PILGRIM"))
//				result=true;
//		}
//		return result;
//	}
	
//	private Administrator getAdministrator(UserAccount userAccount){
//		Administrator result = administratorService.findByUserAccount(userAccount);
//		return result;
//	}
	
}