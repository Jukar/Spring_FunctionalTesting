package services;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.GPS;
import domain.Innkeeper;
import domain.Lodge;
import domain.Money;
import domain.Request;
import domain.Stage;

import repositories.RequestRepository;
import security.LoginService;
import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class RequestServiceTest extends AbstractTest{
	@Autowired
	private RequestService requestService;
	@Autowired
	private LodgeService lodgeService;
	@Autowired
	private InnkeeperService innkeeperService;
	@Autowired
	private StageService stageService;
	
	//----------------------------------------------------
	// POSITIVE TEST CASES
	//----------------------------------------------------
	//A user who is authenticated as an innkeeper must be able to:
	//Create a request to publish a lodge.
	//With a user logged as an innkeeper, we wnat to create a new request for a lodge
	// which isn't 'ACCEPTED'
	@Test
	public void testCreateRequest(){
		authenticate("keeper1");
		GPS gps = new GPS();
		gps.setTitle("Soy una coordenada GPS");
		gps.setDescription("Soy una description GPS");
		gps.setLatitude(20.0);
		gps.setLongitude(15.0);
		gps.setAltitude(5.0);
		
		GPS gps2 = new GPS();
		gps2.setTitle("Soy una coordenada GPS");
		gps2.setDescription("Soy una description GPS");
		gps2.setLatitude(20.0);
		gps2.setLongitude(15.0);
		gps2.setAltitude(5.0);
		
		GPS gps3 = new GPS();
		gps3.setTitle("Soy una coordenada GPS");
		gps3.setDescription("Soy una description GPS");
		gps3.setLatitude(20.0);
		gps3.setLongitude(15.0);
		gps3.setAltitude(5.0);
		
		Money money = new Money();
		money.setValue(10.0);
		money.setCurrency("Eur");
		
		Lodge lodge = lodgeService.findOne(88);
		
		Request req = requestService.create();
		req.setTitle("pruebareq");
		req.setDescription("soy una description");
		req.setComments("soy un comentario");
		req.setLodge(lodge);
//		req.setStatus("PENDING");
		
		requestService.save(req);
		authenticate(null);
	}
	//A user who is authenticated as an innkeeper must be able to:
	//Create a request to publish a lodge.
	//With a user logged as an innkeeper, we wnat to create a new request for a lodge
	// which isn't 'ACCEPTED'
	@Test(expected=DataIntegrityViolationException.class)
	public void testCreateRequestDuplicate(){
		authenticate("keeper1");
		GPS gps = new GPS();
		gps.setTitle("Soy una coordenada GPS");
		gps.setDescription("Soy una description GPS");
		gps.setLatitude(20.0);
		gps.setLongitude(15.0);
		gps.setAltitude(5.0);
		
		GPS gps2 = new GPS();
		gps2.setTitle("Soy una coordenada GPS");
		gps2.setDescription("Soy una description GPS");
		gps2.setLatitude(20.0);
		gps2.setLongitude(15.0);
		gps2.setAltitude(5.0);
		
		GPS gps3 = new GPS();
		gps3.setTitle("Soy una coordenada GPS");
		gps3.setDescription("Soy una description GPS");
		gps3.setLatitude(20.0);
		gps3.setLongitude(15.0);
		gps3.setAltitude(5.0);
		
		Money money = new Money();
		money.setValue(10.0);
		money.setCurrency("Eur");
		
		Lodge lodge = lodgeService.findOne(87);
		
		Request req = requestService.create();
		req.setTitle("pruebareq");
		req.setDescription("soy una description");
		req.setComments("soy un comentario");
		req.setLodge(lodge);
//		req.setStatus("PENDING");
		
		requestService.save(req);
		authenticate(null);
	}	
	//A user who is authenticated as an administrator must be able to:
	//Manage a request to publish a lodge.
	//Cancel a request
	@Test
	public void testManageCancelRequestAdmin(){
		authenticate("admin1");
		Request request=requestService.findOne(93);
		requestService.cancelRequest(request);
		authenticate(null);
	}
	//A user who is authenticated as an administrator must be able to:
	//Manage a request to publish a lodge.
	//Cancel a request
	@Test
	public void testAceptRequestAdmin(){
		authenticate("admin1");
		Request request=requestService.findOne(93);
		requestService.acceptRequest(request);
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Manage a request to publish a lodge.
	//Cancel a request whith a pilgrim
	@Test(expected=IllegalArgumentException.class)
	public void testManageCancelRequestPilgrim(){
		authenticate("customer0");
		Request request=requestService.findOne(93);
		requestService.cancelRequest(request);
		authenticate(null);
	}
	//A user who is authenticated as an administrator must be able to:
	//Manage a request to publish a lodge.
	//Cancel a request
	@Test(expected=IllegalArgumentException.class)
	public void testAceptRequestPilgrim(){
		authenticate("customer0");
		Request request=requestService.findOne(93);
		requestService.acceptRequest(request);
		authenticate(null);
	}
}
