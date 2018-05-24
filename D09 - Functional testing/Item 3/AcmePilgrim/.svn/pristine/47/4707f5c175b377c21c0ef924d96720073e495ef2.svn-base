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
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class RouteServiceTest extends AbstractTest{
	
	// ------------------- Supporting services -------------------
	
	@Autowired
	private RouteService routeService;
	
	// ------------------- Service under test --------------------
	
	@Test
	public void testFindOne() {
		System.out.println("Ruta: " + routeService.findOne(39).getName());
	}
	
	@Test
	public void testFindAll(){
		for(Route aux : routeService.findAll())
			System.out.println("Ruta: " + aux.getName());
	}
	
	@Test
	public void testFindByPilgrim(){
		authenticate("customer6");
		
		for(Route aux : routeService.findByPilgrim())
			System.out.println("Ruta: " + aux.getName());
		
		authenticate(null);
	}
	
	@Test
	public void testRoutesByRegistrationsDesc() {
		for(Route aux : routeService.routesByRegistrationsDesc())
			System.out.println("Ruta: " + aux.getName());
	}
	
	@Test
	public void testActiveRoutes() {
		for(Route aux : routeService.activeRoutes())
			System.out.println("Ruta: " + aux.getName());
	}
	
	@Test
	public void testRoutesByAveRatingAsc() {
		for(Route aux : routeService.routesByAveRatingAsc())
			System.out.println("Ruta: " + aux.getName());
	}
	
//	Error de getters en los atributos derivados
	@Test
	public void testRoutesByKeyword(){
		String keyword = "route";
		Collection<Route> est = routeService.routesByKeyword(keyword);
		System.out.println(est.size());
		for(Route aux : est)
			System.out.println("Ruta: " + aux.getName());
	}
	
	@Test
	public void testCreateAndSave(){
		authenticate("admin1");
		System.out.println(routeService.findAll().size());
		
		Route route = routeService.create();
		route.setName("testing");
		route.setDescription("testing");
		routeService.save(route);
		
		System.out.println(routeService.findAll().size());
		
		authenticate(null);
	}
}
