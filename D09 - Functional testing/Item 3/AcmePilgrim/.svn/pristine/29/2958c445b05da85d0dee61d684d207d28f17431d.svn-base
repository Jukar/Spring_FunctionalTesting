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
import org.springframework.util.Assert;

import domain.Route;
import domain.Stage;
import domain.StageOrder;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class RouteServiceTest extends AbstractTest{
	
	// ------------------- Supporting services -------------------
	
	@Autowired
	private RouteService routeService;
	@Autowired
	private StageService stageService;
	@Autowired
	private RegisterService registerService;
	
	//----------------------------------------------------
	// POSITIVE TEST CASES
	//----------------------------------------------------
	
	//A user who is not authenticated must be able to:
	//List the routes and navigate through their stages.
	//With no user, we will try to find all routes in the system, and check the total of the stages.
	@Test
	public void testFindAllRoutes(){
		Collection<Route> all=routeService.findAll();
		Assert.isTrue(all.size()==3);
		Integer numberStages=0;
		for(Route r:all){
			numberStages+=r.getStageOrders().size();
		}
		Assert.isTrue(numberStages==7);
	}
	
	//A user, independently from whether he or she is authenticated must be able to:
	//Search for routes using a single key word that must appear in its name or its 
	//description, the name or the description of its stages, or the title or the 
	//description of the origin and destination location of these stages.
	//Without actors logged, we will try to find a route by a keyword
	@Test
	public void testFindByKeyword(){
		Collection<Route> all=routeService.routesByKeyword("Pontevedra");
		Assert.isTrue(all.size()==2);
	}
	
	
	
	//A user who is authenticated as an pilgrim must be able to:
	//List the routes and navigate through their stages.
	//With an existing pilgrim, we will try to find all routes in the system, and check the total of the stages.
	@Test
	public void testFindAllRoutesPilgrim(){
		authenticate("customer0");
		Collection<Route> all=routeService.findAll();
		Assert.isTrue(all.size()==3);
		Integer numberStages=0;
		for(Route r:all){
			numberStages+=r.getStageOrders().size();
		}
		Assert.isTrue(numberStages==7);
		authenticate(null);
	}
	
	//A user, independently from whether he or she is authenticated must be able to:
	//Search for routes using a single key word that must appear in its name or its 
	//description, the name or the description of its stages, or the title or the 
	//description of the origin and destination location of these stages.
	//With an existing pilgrim, we will try to find a route by a keyword
	@Test
	public void testFindByKeywordPilgrim(){
		authenticate("customer0");
		Collection<Route> all=routeService.routesByKeyword("Pontevedra");
		Assert.isTrue(all.size()==2);
		authenticate(null);
	}
	
	//A user who is authenticated as an pilgrim must be able to:
	//Display a dashboard with the following information:
	//Route and stage history.
	//With an existing pilgrim we will try to find this information
	@Test
	public void testRouteStageHistory(){
		authenticate("customer1");
		List<Object[]> all=registerService.findByRoutesStageByPilgrim();
		Assert.isTrue(all.size()==2);
		authenticate(null);
	}
	
	
	//A user, independently from whether he or she is authenticated must be able to:
	//Search for routes using a single key word that must appear in its name or its 
	//description, the name or the description of its stages, or the title or the 
	//description of the origin and destination location of these stages.
	//With an existing innkeeper, we will try to find a route by a keyword
	@Test
	public void testFindByKeywordKeeper(){
		authenticate("keeper1");
		Collection<Route> all=routeService.routesByKeyword("Pontevedra");
		Assert.isTrue(all.size()==2);
		authenticate(null);
	}
	
	
	//A user who is authenticated as an administrator must be able to:
		//List the routes and navigate through their stages.
		//With an existing administrator, we will try to find all routes in the system, and check the total of the stages.
		@Test
		public void testFindAllRoutesAdmin(){
			authenticate("admin1");
			Collection<Route> all=routeService.findAll();
			Assert.isTrue(all.size()==3);
			Integer numberStages=0;
			for(Route r:all){
				numberStages+=r.getStageOrders().size();
			}
			Assert.isTrue(numberStages==7);
			authenticate(null);
		}
	
	//A user who is authenticated as an administrator must be able to:
	//Create his or her routes.
	//With an existing administrator, we want to check if he can create and save a new Route correctly
	//made with existing stages.
	@Test
	public void testCreateAndSaveAdmin(){
		authenticate("admin1");
				
		Route route=routeService.create();
		route.setDescription("description");
		route.setName("name");
		
		routeService.save(route);

		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//List his or her routes.
	//With an existing administrator, we want to check if he can find all his routes.
	@Test
	public void testFindByAdmin(){
		authenticate("admin1");
		Collection<Route> all=routeService.findByAdministrator();
		Assert.isTrue(all.size()==2);
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Delete his or her routes.
	//With an existing administrator, we want to check if he can delete(cancel) one of his routes.
	@Test
	public void testDeleteAdmin(){
		authenticate("admin1");
		Route route=routeService.findOne(55);
		routeService.delete(route);
		Assert.isTrue(routeService.findAll().size()==3);
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//The list of routes in descending order of registrations.
	//With an existing administrator, we want to check if the first of the routes is the expected.
	@Test 
	public void testOrderByRegistrations(){
		authenticate("admin1");
		Collection<Route> all=routeService.routesByRegistrationsDesc();
		List<Route> allOrdered=new ArrayList<Route>();
		for(Route r:all){
			allOrdered.add(r);
		}
		Route route=allOrdered.get(0);
		Assert.isTrue(route.getId()==61);
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//The list of routes, in ascending order of average rating, with an 
	//indication regarding whether they have been deleted.
	//With an existing administrator, we want to check if the first of the routes is the expected.
	@Test 
	public void testOrderByAvg(){
		authenticate("admin1");
		Collection<Route> all=routeService.routesByAveRatingAsc();
		List<Route> allOrdered=new ArrayList<Route>();
		for(Route r:all){
			allOrdered.add(r);
		}
		Route route=allOrdered.get(0);
		Assert.isTrue(route.getId()==55);
		authenticate(null);
	}
	
	//A user, independently from whether he or she is authenticated must be able to:
	//Search for routes using a single key word that must appear in its name or its 
	//description, the name or the description of its stages, or the title or the 
	//description of the origin and destination location of these stages.
	//With an existing administrator, we will try to find a route by a keyword
	@Test
	public void testFindByKeywordAdmin(){
		authenticate("admin1");
		Collection<Route> all=routeService.routesByKeyword("Pontevedra");
		Assert.isTrue(all.size()==2);
		authenticate(null);
	}

	//----------------------------------------------------
	// NEGATIVE TEST CASES
	//----------------------------------------------------
	//A user who is not authenticated must be able to:
	//List the routes and navigate through their stages.
	//With no user, we will try to find all routes, expected a worng number and capture 
	//the exception.
	@Test(expected=IllegalArgumentException.class)
	public void testFindAllRoutesException(){
		Collection<Route> all=routeService.findAll();
		Assert.isTrue(all.size()==6);
		Integer numberStages=0;
		for(Route r:all){
			numberStages+=r.getStageOrders().size();
		}
		Assert.isTrue(numberStages==7);
	}
	
	//A user, independently from whether he or she is authenticated must be able to:
	//Search for routes using a single key word that must appear in its name or its 
	//description, the name or the description of its stages, or the title or the 
	//description of the origin and destination location of these stages.
	//Without actors logged, we will try to find a route by a wrong keyword
	//and capture the exception
	@Test(expected=IllegalArgumentException.class)
	public void testFindByKeywordExpected(){
		Collection<Route> all=routeService.routesByKeyword("FaLsO");
		Assert.isTrue(all.size()==2);
	}
	
	
	
	//A user who is authenticated as an pilgrim must be able to:
	//List the routes and navigate through their stages.
	//With an existing pilgrim, we will try to find all routes, expected a worng number and capture 
	//the exception.
	@Test(expected=IllegalArgumentException.class)
	public void testFindAllRoutesPilgrimExpected(){
		authenticate("customer0");
		Collection<Route> all=routeService.findAll();
		Assert.isTrue(all.size()==4);
		Integer numberStages=0;
		for(Route r:all){
			numberStages+=r.getStageOrders().size();
		}
		Assert.isTrue(numberStages==7);
		authenticate(null);
	}
	
	//A user, independently from whether he or she is authenticated must be able to:
	//Search for routes using a single key word that must appear in its name or its 
	//description, the name or the description of its stages, or the title or the 
	//description of the origin and destination location of these stages.
	//With an existing pilgrim, we will try to find a route by a wrong keyword
	//and capture the exception
	@Test(expected=IllegalArgumentException.class)
	public void testFindByKeywordPilgrimException(){
		authenticate("customer0");
		Collection<Route> all=routeService.routesByKeyword("FaLsO");
		Assert.isTrue(all.size()==2);
		authenticate(null);
	}
	
	//A user who is authenticated as an pilgrim must be able to:
	//Display a dashboard with the following information:
	//Route and stage history.
	//With an existing innkeeper we will try to find this information and capture the exception.
	@Test(expected=NullPointerException.class)
	public void testRouteStageHistoryException(){
		authenticate("keeper1");
		List<Object[]> all=registerService.findByRoutesStageByPilgrim();
		Assert.isTrue(all.size()==2);
		authenticate(null);
	}
	
	
	//A user, independently from whether he or she is authenticated must be able to:
	//Search for routes using a single key word that must appear in its name or its 
	//description, the name or the description of its stages, or the title or the 
	//description of the origin and destination location of these stages.
	//With an existing innkeeper, we will try to find a route by a wrong keyword
	//and capture the exception
	@Test(expected=IllegalArgumentException.class)
	public void testFindByKeywordKeeperException(){
		authenticate("keeper1");
		Collection<Route> all=routeService.routesByKeyword("FaLsO");
		Assert.isTrue(all.size()==2);
		authenticate(null);
	}
	
	
	//A user who is authenticated as an administrator must be able to:
	//List the routes and navigate through their stages.
	//With an existing administrator, we will try to find all routes, expected a worng number and capture 
	//the exception.
		@Test(expected=IllegalArgumentException.class)
		public void testFindAllRoutesAdminExpected(){
			authenticate("admin1");
			Collection<Route> all=routeService.findAll();
			Assert.isTrue(all.size()==6);
			Integer numberStages=0;
			for(Route r:all){
				numberStages+=r.getStageOrders().size();
			}
			Assert.isTrue(numberStages==2);
			authenticate(null);
		}
	
	//A user who is authenticated as an administrator must be able to:
	//Create his or her routes.
	//With an existing pilgrim, we want to check if he can create and save a new Route correctly
	//made with existing stages.
	@Test(expected=IllegalArgumentException.class)
	public void testCreateAndSaveAdminException(){
		authenticate("customer0");
				
		Route route=routeService.create();
		route.setDescription("description");
		route.setName("name");
		
		routeService.save(route);

		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//List his or her routes.
	//With an existing pilgrim, we will try to find all his routes and capture the exception
	@Test(expected=IllegalArgumentException.class)
	public void testFindByAdminException(){
		authenticate("customer1");
		Collection<Route> all=routeService.findByAdministrator();
		Assert.isTrue(all.size()==2);
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Delete his or her routes.
	//With an existing innkeeper, we want to check if he can delete(cancel) 
	//one of his routes.
	@Test(expected=IllegalArgumentException.class)
	public void testDeleteAdminException(){
		authenticate("keeper1");
		Route route=routeService.findOne(55);
		routeService.delete(route);
		Assert.isTrue(routeService.findAll().size()==3);
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//The list of routes in descending order of registrations.
	//With an existing innkeeper, we want to check if the first of the routes is the expected.
	@Test(expected=IllegalArgumentException.class)
	public void testOrderByRegistrationsException(){
		authenticate("keeper1");
		Collection<Route> all=routeService.routesByRegistrationsDesc();
		List<Route> allOrdered=new ArrayList<Route>();
		for(Route r:all){
			allOrdered.add(r);
		}
		Route route=allOrdered.get(0);
		Assert.isTrue(route.getId()==61);
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//The list of routes, in ascending order of average rating, with an 
	//indication regarding whether they have been deleted.
	//With an existing pilgrim, we want to check if the first of the routes is the expected.
	@Test(expected=IllegalArgumentException.class)
	public void testOrderByAvgException(){
		authenticate("keeper1");
		Collection<Route> all=routeService.routesByAveRatingAsc();
		List<Route> allOrdered=new ArrayList<Route>();
		for(Route r:all){
			allOrdered.add(r);
		}
		Route route=allOrdered.get(0);
		Assert.isTrue(route.getId()==55);
		authenticate(null);
	}
	
	//A user, independently from whether he or she is authenticated must be able to:
	//Search for routes using a single key word that must appear in its name or its 
	//description, the name or the description of its stages, or the title or the 
	//description of the origin and destination location of these stages.
	//With an existing administrator, we will try to find a route by a wrong keyword and
	//capture the exception
	@Test(expected=IllegalArgumentException.class)
	public void testFindByKeywordAdminExpected(){
		authenticate("admin1");
		Collection<Route> all=routeService.routesByKeyword("FaLsO");
		Assert.isTrue(all.size()==2);
		authenticate(null);
	}
}
