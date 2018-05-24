package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import security.LoginService;
import security.UserAccount;
import utilities.AbstractTest;
import domain.Pilgrim;
import domain.Register;
import domain.StageRating;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class RegisterServiceTest extends AbstractTest{
	
	// ------------------- Supporting services -------------------
	
	@Autowired
	private RegisterService registerService;
	@Autowired
	private PilgrimService pilgrimService;
	@Autowired
	private StageRatingService stageRatingService;
	@Autowired
	private StageService stageService;
	@Autowired
	private RouteService routeService;
	
	//----------------------------------------------------
	// POSITIVE TEST CASES
	//----------------------------------------------------
	//A user who is authenticated as an pilgrim must be able to:
	//Register to a route, which implies that he or she registers to every 
	//individual stage. Note that it is not allowed to register to an individual stage.
	//With an existing pilgrim, we will try to register him to a route
	@Test
	public void testRegisterToRoute(){
		authenticate("customer0");
		
		Register regist=registerService.create();
		regist.setRoute(routeService.findOne(55));
		registerService.save(regist);
		
		authenticate(null);
	}
	
	//----------------------------------------------------
	// NEGATIVE TEST CASES
	//----------------------------------------------------
	//A user who is authenticated as an pilgrim must be able to:
	//Register to a route, which implies that he or she registers to every 
	//individual stage. Note that it is not allowed to register to an individual stage.
	//With an existing inkeeper, we will try to register him to a route and capture the 
	//exception
	@Test(expected=IllegalArgumentException.class)
	public void testRegisterToRouteException(){
		authenticate("keeper1");
		
		Register regist=registerService.create();
		regist.setRoute(routeService.findOne(55));
		registerService.save(regist);
		
		authenticate(null);
	}
}
