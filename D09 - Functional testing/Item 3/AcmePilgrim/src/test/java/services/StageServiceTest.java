package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.mchange.util.DuplicateElementException;

import domain.Complaint;
import domain.GPS;
import domain.Route;
import domain.Stage;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class StageServiceTest extends AbstractTest{

	// ------------------- Supporting services -------------------
	
	@Autowired
	private StageService stageService;
	@Autowired
	private RouteService routeService;

	// ------------------- Service under test --------------------

	
//	- A user who is not authenticated must be able to:
//	List the routes and navigate through their stages.
//	Trying with pilgrim
	@Test
	public void listStageRouteAdmin(){
		authenticate("admin1");
		List<Route> routes= (List<Route>) routeService.activeRoutes();
		Collection<Stage> stages=stageService.findByRouteId(routes.get(0).getId());
		authenticate(null);
	}
//	- A user who is not authenticated must be able to:
//	List the routes and navigate through their stages.
//	Trying with inkeeper
	@Test
	public void listStageRouteInkeeper(){
		authenticate("keeper1");
		List<Route> routes= (List<Route>) routeService.activeRoutes();
		Collection<Stage> stages=stageService.findByRouteId(routes.get(0).getId());
		authenticate(null);
	}
	
//	- A user who is authenticated as an administrator must be able to:
//	Manage routes and stages, which includes creating, listing, modifying, and deleting
//	them. 
	//Trying creating with an Admin
	@Test
	public void testCreateAdmin(){
		authenticate("admin1");
		Stage stage=stageService.create();
		
		GPS origin = new GPS();
		origin.setTitle("asadads");
		origin.setDescription("asadads");
		origin.setLongitude(2.8);
		origin.setLatitude(3.0);
		
		GPS destination= new GPS();
		destination.setTitle("asadadasdas");
		destination.setDescription("asadaadadds");
		destination.setLongitude(2.8);
		destination.setLatitude(4.0);
		destination.setAltitude(1.0);

		stage.setName("aasdasadada");
		stage.setDescription("aasdasadada");
		stage.setOrigin(origin);
		stage.setDestination(destination);
		stage.setLenghtKm(5.0);
		stage.setDifficultyLevel(7);
		stage.setBriefTextDescription("zdfsfasfafafwa");
		
		stageService.save(stage);
		authenticate(null);
	}
	//A user who is authenticated as an administrator must be able to:
	// -Manage stages, which includes creating
	//Trying create with an Pilgrim
	@Test(expected= IllegalArgumentException.class)
	public void testCreatePilgrim(){
		authenticate("customer1");
		Stage stage=stageService.create();
		
		GPS origin = new GPS();
		origin.setTitle("asadads");
		origin.setDescription("asadads");
		origin.setLongitude(2.8);
		origin.setLatitude(3.0);
		
		GPS destination= new GPS();
		destination.setTitle("asadadasdas");
		destination.setDescription("asadaadadds");
		destination.setLongitude(2.8);
		destination.setLatitude(4.0);
		destination.setAltitude(1.0);

		stage.setName("aasdasadada");
		stage.setDescription("aasdasadada");
		stage.setOrigin(origin);
		stage.setDestination(destination);
		stage.setLenghtKm(5.0);
		stage.setDifficultyLevel(7);
		stage.setBriefTextDescription("zdfsfasfafafwa");
		
		stageService.save(stage);
		authenticate(null);
	}
//	- A user who is authenticated as an administrator must be able to:
//	Manage routes and stages, which includes creating, listing, modifying, and deleting
//	them. Listing up.

//	- A user who is authenticated as an administrator must be able to:
//	Manage routes and stages, which includes creating, listing, modifying, and deleting
//	them. 
	//Trying to modifyng with an Admin
	@Test
	public void testModifyingAdmin(){
		authenticate("admin1");
		Stage stage=stageService.findOne(49);
		stage.setName("aasdasadada");		
		stageService.save(stage);
		authenticate(null);
	}
//	- A user who is authenticated as an administrator must be able to:
//	Manage routes and stages, which includes creating, listing, modifying, and deleting
//	them. 
	//Trying to modifyng with a pilgrim
	@Test(expected= IllegalArgumentException.class)
	public void testModifyingPilgrim(){
		authenticate("customer1");
		Stage stage=stageService.findOne(49);
		stage.setName("aasdasadada");		
		stageService.save(stage);
		authenticate(null);
	}
//	- A user who is authenticated as an administrator must be able to:
//	Manage routes and stages, which includes creating, listing, modifying, and deleting
//	them. 
	//Trying delete with an Admin
	@Test(expected= IllegalArgumentException.class)
	public void testDeleteAdminWithRelations(){
		authenticate("admin1");
		Stage stage=stageService.findOne(49);		
		stageService.delete(stage);
		authenticate(null);
	}
//	- A user who is authenticated as an administrator must be able to:
//	Manage routes and stages, which includes creating, listing, modifying, and deleting
//	them. 
	//Trying delete with a pilgrim
	@Test(expected= IllegalArgumentException.class)
	public void testDeletePilgrim(){
		authenticate("customer1");
		Stage stage=stageService.findOne(49);	
		stageService.delete(stage);
		authenticate(null);
	}		
	
// - A user who is authenticated as an administrator must be able to:	
// List of stages, in ascending order of average rating.
//	Trying with an admin
	@Test
	public void listStageAvrRatAdmin(){
		authenticate("admin1");
		Collection<Stage> stages=stageService.getStagesByAVG();
		authenticate(null);
	}
	// - A user who is authenticated as an administrator must be able to:	
	// List of stages, in ascending order of average rating.
	//	Trying with inkeeper
	@Test(expected= IllegalArgumentException.class)
	public void listStageAvrRatInkeeper(){
		authenticate("keeper1");
		Collection<Stage> stages=stageService.getStagesByAVG();
		authenticate(null);
	}
//A user who is authenticated as an pilgrim must be able to:
// -List of pending stages, including the lodges that he or she's booked.
// We considered pending stages the ones that don't have an stageRating
//trying whith pilgrim
	@Test
	public void listPendingStagePilgrim(){
		authenticate("customer1");
		Collection<Stage> stages=stageService.findNotRegisteredByPilgrim();
		authenticate(null);
	}
	//A user who is authenticated as an pilgrim must be able to:
	// -List of pending stages, including the lodges that he or she's booked.
	// We considered pending stages the ones that don't have an stageRating
	//trying whith inkeeper
	@Test(expected= NullPointerException.class)
	public void listPendingStageInkeeper(){
		authenticate("keeper1");
		Collection<Stage> stages=stageService.findNotRegisteredByPilgrim();
		authenticate(null);
	}
}