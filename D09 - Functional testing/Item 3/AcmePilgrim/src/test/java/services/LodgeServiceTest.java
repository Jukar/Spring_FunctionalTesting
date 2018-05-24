package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.RollbackException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Booking;
import domain.GPS;
import domain.Innkeeper;
import domain.Lodge;
import domain.Money;
import domain.Pilgrim;
import domain.Request;
import domain.Stage;
import forms.LodgeForm;
import repositories.StageRepository;
import security.LoginService;
import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@SuppressWarnings("unused")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LodgeServiceTest extends AbstractTest{
	
	// ------------------- Supporting services -------------------
	
	@Autowired
	private LodgeService lodgeService;
	@Autowired
	private PilgrimService pilgrimService;
	@Autowired
	private InnkeeperService innkeeperService;
	@Autowired
	private StageService stageService;
	
	//----------------------------------------------------
	// POSITIVE TEST CASES
	//----------------------------------------------------
	//A user who is not authenticated must be able to:
	//List lodges and navigate to their innkeepers.
	//Without a logged actor, we will try to list all lodges
	@Test
	public void testListAll(){
		Collection<Lodge> all=lodgeService.findAll();
		Assert.isTrue(all.size()==5);
	}
	
	//A user who is authenticated as an innkeeper must be able to:
	//Create
	//With an existing innkeeper we will try to create a lodge
	@Test
	public void testCreateAndSave(){
		authenticate("keeper1");
		
		Lodge lodge = lodgeService.create();
		
		GPS coord = new GPS();
		coord.setLatitude(22.1);
		coord.setLongitude(22.1);
		coord.setAltitude(22.1);
		coord.setDescription("testing");
		coord.setTitle("testing");
		
		Money money = new Money();
		money.setCurrency("testing");
		money.setValue(22.1);
		
		lodge.setName("testing");
		lodge.setAddress("testing");
		lodge.setCoordinates(coord);
		lodge.setWebSite("testing.test");
		lodge.setContactPhone("667667667");
		lodge.setLodgeDescription("testing");
		lodge.setNumberBeds(17);
		lodge.setPricePerNight(money);
		
		Stage stage = stageService.findOne(52);
		lodge.setStage(stage);
		
		lodgeService.save(lodge);
		
		authenticate(null);
	}
	//A user who is authenticated as an innkeeper must be able to:
	//Edit a lodge
	//With an existing innkeeper we will try to edit a lodge
	@Test
	public void testEdit(){
		authenticate("keeper1");
		Lodge lodge=lodgeService.findOne(89);
		lodge.setName("novo");
		lodgeService.save(lodge);
		authenticate(null);
	}
	//A user who is authenticated as an innkeeper must be able to:
	//Delete a lodge
	//With an existing innkeeper we will try to delete a lodge
	@Test
	public void testDelete(){
		authenticate("keeper3");
		Lodge lodge=lodgeService.findOne(88);
		lodgeService.delete(lodge);
		authenticate(null);
	}	
	//A user who is authenticated as an innkeeper must be able to:
	//Listing his lodge
	//With an existing innkeeper we will try to list his lodges
	@Test
	public void testList(){
		authenticate("keeper3");
		Collection<Lodge> lodges=lodgeService.findByInnkeeper();
		Assert.isTrue(lodges.size()==1);
		authenticate(null);
	}
	
	//A user who is authenticated as an innkeeper must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//List of lodges sorted by descending number of bookings.
	@Test
	public void testLodgesByBookingDesc(){
		authenticate("keeper1");
		Collection<Lodge> all=lodgeService.lodgesOrderedByBookingsDesc();
		authenticate(null);
	}
	
	//A user who is authenticated as an innkeeper must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//List of lodges sorted by increasing average rating.
	@Test
	public void testSortedByAvgAsc(){
		authenticate("keeper1");
		Collection<Lodge> all=lodgeService.lodgesOrderedByRatingAsc();
		List<Lodge> li=new ArrayList<Lodge>();
		authenticate(null);
	}
	
	//A user who is authenticated as an innkeeper must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//List of lodges sorted by descending price, grouped by stage.
	@Test
	public void testSortedByPriceDesc(){
		authenticate("keeper1");
		Collection<Lodge> all=lodgeService.lodgesOrderedByPriceDesc();
		List<Lodge> li=new ArrayList<Lodge>();

		authenticate(null);
	}
	//A user who is authenticated as an innkeeper must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//Occupancy rate of their lodges regarding the next month.
	//With an existing innkeeper we will try to obtain the value
	@Test
	public void testOccupacyRate(){
		authenticate("keeper1");
		Double rating=lodgeService.getOccupancyRateNextMonth();
		authenticate(null);
	}
	
	
	//A user who is authenticated as an innkeeper must be able to:
	//Publish a lodge
	//With an existing innkeeper we will try to create a lodge
	@Test
	public void testPublish(){
		authenticate("keeper1");
		lodgeService.publishLodge(86);
		authenticate(null);
	}
	
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//List of lodges sorted by descending number of bookings.
	//With an existing administrator we will try to obtain the list ordered
	@Test
	public void testSortedByNumBookDesc(){
		authenticate("admin1");
		Collection<Lodge> all=lodgeService.lodgesOrderedByBookingsDesc();
		List<Lodge> li=new ArrayList<Lodge>();
		for(Lodge l:all){
			li.add(l);
		}
		Assert.isTrue(li.get(0).getId()==85);
		authenticate(null);
	}

	
	//----------------------------------------------------
	// NEGATIVE TEST CASES
	//----------------------------------------------------
	//A user who is not authenticated must be able to:
	//List lodges and navigate to their innkeepers.
	//Without a logged actor, we will try to list a wrong number of lodges
	@Test(expected=IllegalArgumentException.class)
	public void testListAllException(){
		Collection<Lodge> all=lodgeService.findAll();
		Assert.isTrue(all.size()==8);
	}
	
	//A user who is authenticated as an innkeeper must be able to:
	//Create a lodge
	//With an existing pilgrim we will try to create a lodge and capture the exception
	@Test(expected=IllegalArgumentException.class)
	public void testCreateAndSaveException(){
		authenticate("customer0");
		
		Lodge lodge = lodgeService.create();
		
		GPS coord = new GPS();
		coord.setLatitude(22.1);
		coord.setLongitude(22.1);
		coord.setAltitude(22.1);
		coord.setDescription("testing");
		coord.setTitle("testing");
		
		Money money = new Money();
		money.setCurrency("testing");
		money.setValue(22.1);
		
		lodge.setName("testing");
		lodge.setAddress("testing");
		lodge.setCoordinates(coord);
		lodge.setWebSite("testing.test");
		lodge.setContactPhone("667667667");
		lodge.setLodgeDescription("testing");
		lodge.setNumberBeds(17);
		lodge.setPricePerNight(money);
		
		Stage stage = stageService.findOne(52);
		lodge.setStage(stage);
		
		lodgeService.save(lodge);
		
		authenticate(null);
	}
	//A user who is authenticated as an innkeeper must be able to:
	//Edit a lodge
	//With an existing pilgrim we will try to edit a lodge
	@Test(expected=IllegalArgumentException.class)
	public void testEditException(){
		authenticate("customer0");
		Lodge lodge=lodgeService.findOne(89);
		lodge.setName("novo");
		lodgeService.save(lodge);
		authenticate(null);
	}
	//A user who is authenticated as an innkeeper must be able to:
	//Delete a lodge
	//With an existing pilgrim we will try to delete a lodge
	@Test(expected=IllegalArgumentException.class)
	public void testDeleteException(){
		authenticate("customer0");
		Lodge lodge=lodgeService.findOne(88);
		lodgeService.delete(lodge);
		authenticate(null);
	}	
	//A user who is authenticated as an innkeeper must be able to:
	//Listing his lodge
	//With an existing pilgrim we will try to list his lodges
	@Test(expected=IllegalArgumentException.class)
	public void testListException(){
		authenticate("customer0");
		Collection<Lodge> lodges=lodgeService.findByInnkeeper();
		Assert.isTrue(lodges.size()==1);
		authenticate(null);
	}
	
	//A user who is authenticated as an innkeeper must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//List of lodges sorted by descending number of bookings.
	//With an existing pilgrim we will try to list the lodges
	@Test(expected=IllegalArgumentException.class)
	public void testLodgesByBookingDescException(){
		authenticate("customer0");
		Collection<Lodge> all=lodgeService.lodgesOrderedByBookingsDesc();
		authenticate(null);
	}
	
	//A user who is authenticated as an innkeeper must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//List of lodges sorted by increasing average rating.
	//With an existing pilgrim we will try to list the lodges
	@Test(expected=IllegalArgumentException.class)
	public void testSortedByAvgAscException(){
		authenticate("customer0");
		Collection<Lodge> all=lodgeService.lodgesOrderedByRatingAsc();
		List<Lodge> li=new ArrayList<Lodge>();
		authenticate(null);
	}
	
	//A user who is authenticated as an innkeeper must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//List of lodges sorted by descending price, grouped by stage.
	//With an existing pilgrim we will try to list the lodges
	@Test(expected=IllegalArgumentException.class)
	public void testSortedByPriceDescException(){
		authenticate("customer0");
		Collection<Lodge> all=lodgeService.lodgesOrderedByPriceDesc();
		List<Lodge> li=new ArrayList<Lodge>();

		authenticate(null);
	}
	//A user who is authenticated as an innkeeper must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//Occupancy rate of their lodges regarding the next month.
	//With an existing pilgrim we will try to list the lodges
	@Test(expected=NullPointerException.class)
	public void testOccupacyRateException(){
		authenticate("customer0");
		Double rating=lodgeService.getOccupancyRateNextMonth();
		authenticate(null);
	}
	
	
	//A user who is authenticated as an innkeeper must be able to:
	//Publish a lodge
	//With an existing pilgrim we will try to list the lodges
	@Test(expected=IllegalArgumentException.class)
	public void testPublishException(){
		authenticate("customer0");
		lodgeService.publishLodge(86);
		authenticate(null);
	}
	
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//List of lodges sorted by descending number of bookings.
	//With an existing pilgrim we will try to list the lodges
	@Test(expected=IllegalArgumentException.class)
	public void testSortedByNumBookDescException(){
		authenticate("customer0");
		Collection<Lodge> all=lodgeService.lodgesOrderedByBookingsDesc();
		List<Lodge> li=new ArrayList<Lodge>();
		for(Lodge l:all){
			li.add(l);
		}
		Assert.isTrue(li.get(0).getId()==85);
		authenticate(null);
	}
}
