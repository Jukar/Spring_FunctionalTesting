package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.LodgeRepository;
import repositories.StageRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Lodge;
import domain.Pilgrim;
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
	@Autowired
	private PilgrimService pilgrimService;
	
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
		checkPrincipal("ADMIN");
		
		stageRepository.save(stage);
	}
	
	public void saveInnkeeper(Stage stage) {
		Assert.notNull(stage);
		Assert.isTrue(isInnkeeper());
		
		Stage stageDB = findOne(stage.getId());
		Assert.notNull(stageDB);
		
		Assert.isTrue(stageDB.getName() == stage.getName() && 
						stageDB.getDescription() == stage.getDescription() && 
						stageDB.getOrigin() == stage.getOrigin() && 
						stageDB.getDestination() == stage.getDestination() && 
						stageDB.getLenghtKm() == stage.getLenghtKm() && 
						stageDB.getDifficultyLevel() == stage.getDifficultyLevel() && 
						stageDB.getBriefTextDescription() == stage.getBriefTextDescription() && 
						stageDB.getLandmarks() == stage.getLandmarks() && 
						stageDB.getStageRatings() == stage.getStageRatings() &&
						
						(stageDB.getLodges().size()+1 == stage.getLodges().size() ||
						stageDB.getLodges().size() == stage.getLodges().size()));
		
		stageRepository.save(stage);
	}
	
	public void delete (Stage stage){
		Assert.notNull(stage);
		checkPrincipal("ADMIN");
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
	
	public Collection<Stage> findNotRegisteredByPilgrim(){
		UserAccount userAccount = LoginService.getPrincipal();
		Pilgrim pilgrim=pilgrimService.findByUserAccount(userAccount);
		Collection<Stage> allS=stageRepository.findStageByPilgrimId(pilgrim.getId());
		Collection<Stage> allRat=stageRepository.findStageRatedByPilgrimId(pilgrim.getId());
		Collection<Stage> result = new ArrayList<Stage>();
		for (Stage s:allS){
			if(!allRat.contains(s)) 
				result.add(s);
		}
		return result;
	}

	// AUXILIARES

	public Collection<Stage> getStagesByAVG() {
		checkPrincipal("ADMIN");
		Collection<Stage> all=stageRepository.findAll();
		Map<Stage,Double> allWithRat=new HashMap<Stage,Double>();
		for(Stage s:all){
			Double aux=(s.getRatingD()+s.getRatingL())/2;
			allWithRat.put(s, aux);
		}
		Map<Stage,Double> orderedByRat=sortByComparator(allWithRat);
		Collection<Stage> result=orderedByRat.keySet();
		return result;
	}
	
	//	Method used to order a map
	private static Map<Stage, Double> sortByComparator(Map<Stage, Double> unsortMap) {
		 
		// Convert Map to List
		List<Map.Entry<Stage, Double>> list = 
			new LinkedList<Map.Entry<Stage, Double>>(unsortMap.entrySet());
 
		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<Stage, Double>>() {
			public int compare(Map.Entry<Stage, Double> o1,
                                           Map.Entry<Stage, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
 
		// Convert sorted map back to a Map
		Map<Stage, Double> sortedMap = new LinkedHashMap<Stage, Double>();
		for (Iterator<Map.Entry<Stage, Double>> it = list.iterator(); it.hasNext();) {
			Entry<Stage, Double> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	private Boolean isInnkeeper(){
		UserAccount userAccount = LoginService.getPrincipal();
		Boolean result = userAccount.getAuthorities().iterator().next().getAuthority().equals("INNKEEPER");
		return result;
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