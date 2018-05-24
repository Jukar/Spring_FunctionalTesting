package services;

import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import domain.Route;
import domain.Stage;
import domain.StageOrder;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class StageOrderServiceTest extends AbstractTest{

	// ------------------- Supporting services -------------------
	
	@Autowired
	private StageOrderService stageOrderService;
	@Autowired
	private RouteService routeService;
	@Autowired
	private StageService stageService;

	// ------------------- Service under test --------------------
	
	@Test
	public void testFindOne() {
		System.out.println("StageOrder: " + stageOrderService.findOne(47));
	}

	@Test
	public void testFindAll(){
		for(StageOrder aux : stageOrderService.findAll())
			System.out.println("StageOrder: " + aux.getId());
	}
	
	@Test
	public void testRegisterStageOnRoute(){
		authenticate("admin1");
		
		Route route = routeService.findOne(38);
		Stage stage = stageService.findOne(36);
		
		System.out.println("Antes");
		Collection<Stage> stages = stageService.findByRouteId(route.getId());
		for(Stage aux : stages)
			System.out.println(aux.getId() +" From " + aux.getOrigin() + 
											" to " + aux.getDestination());
		
		stageOrderService.registerStageOnRoute(route.getId(), stage.getId());
		
		System.out.println("Despues");
		stages = stageService.findByRouteId(route.getId());
		for(Stage aux : stages)
			System.out.println(aux.getId() +" From " + aux.getOrigin() + 
											" to " + aux.getDestination());
		
		authenticate(null);
	}
}