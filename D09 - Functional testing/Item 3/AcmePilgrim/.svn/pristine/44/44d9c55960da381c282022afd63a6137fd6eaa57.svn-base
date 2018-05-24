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
@TransactionConfiguration(defaultRollback = false)
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
	
	// ------------------- Service under test --------------------
	
//	@Test
//	public void testFindOne() {
//		Register register = registerService.findOne(59);
//		System.out.println("Registro: " + register.getId() + " A ruta: " + register.getRoute().getName());
//	}
//	
//	@Test
//	public void testFindAll() {
//		Collection<Register> all = registerService.findAll();
//		for (Register aux : all) {
//			System.out.println("Registro: " + aux.getId() + " A ruta: " + aux.getRoute().getName());
//		}
//	}

//	@Test
//	public void testRegisterOnRoute() {
//		authenticate("customer0");
//		UserAccount useAco = LoginService.getPrincipal();
//		Pilgrim pilgrim = pilgrimService.findByUserAccount(useAco);
//		
//		System.out.println("Antes -> Cantidad: " + routeService.findByPilgrim().size());
//		System.out.println("Nombre: " + pilgrim.getName());
//		System.out.println("Rutas: " + routeService.findByPilgrim());
//		
//		Route route = routeService.findOne(39);
//		registerService.registerOnRoute(route);
//		
//		System.out.println("Despues -> Cantidad: " + routeService.findByPilgrim().size());
//		System.out.println("Nombre: " + pilgrim.getName());
//		System.out.println("Rutas: " + routeService.findByPilgrim());
//		
//		authenticate(null);
//	}
	
	@Test
	public void testRegisterStageRating() {
		authenticate("customer6");
		UserAccount useAco = LoginService.getPrincipal();
		Pilgrim pilgrim = pilgrimService.findByUserAccount(useAco);
		
		System.out.println("Antes");
		System.out.println("Nombre: " + pilgrim.getName());
		for(Register aux : pilgrim.getRegisters()){
			System.out.println("Registro a ruta: " + aux.getRoute().getName());
			System.out.println("StageRatings: " + aux.getStageRatings());
		}
		
		StageRating stageRating = stageRatingService.create();
		stageRating.setRegister(registerService.findOne(59));
		stageRating.setStage(stageService.findOne(35));
		
		registerService.registerStageRating(stageRating);
		
		//hacer un find by pilgrim o argo
		System.out.println("Despues");
		System.out.println("Nombre: " + pilgrim.getName());
		for(Register aux : pilgrim.getRegisters()){
			System.out.println("Registro a ruta: " + aux.getRoute().getName());
			System.out.println("StageRatings: " + aux.getStageRatings());
		}
		
		authenticate(null);
	}

}
