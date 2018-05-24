package services;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Anecdote;
import domain.Booking;
import domain.Innkeeper;
import domain.Money;
import domain.Route;

import security.LoginService;
import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class BookingServiceTest extends AbstractTest{
	@Autowired
	private BookingService bookingService;
	@Autowired
	private LodgeService lodgeService;
	@Autowired
	private InnkeeperService innkeeperService;	
//	- A user who is authenticated as an pilgrim must be able to:
//	Manage his or her bookings. Bookings can be cancelled or 
//  changed until the day before.
	//List bookings by a pilgrim
	@Test
	public void testListByPilgrim(){
		authenticate("customer0");
		Collection<Booking> result = bookingService.findByPilgrim();
		authenticate(null);
	}	
//	- A user who is authenticated as an pilgrim must be able to:
//	Manage his or her bookings. Bookings can be cancelled or 
//  changed until the day before.
	//List bookings by an admin
	@Test(expected=NullPointerException.class)
	public void testListByAdmin(){
		authenticate("admin1");
		Collection<Booking> result = bookingService.findByPilgrim();
		authenticate(null);
	}	
//	- A user who is authenticated as an pilgrim must be able to:
//	Manage his or her bookings. Bookings can be cancelled or 
//  changed until the day before.
	//Create a booking as a pilgrim
	@Test
	public void testCreateByPilgrim(){
		authenticate("customer1");
		Booking result = bookingService.create();
		result.setArrivalDate(new Date(System.currentTimeMillis()+35000));
		result.setNumberOfNights(7);
		result.setNumberOfBeds(7);
		Money pricePerNight=new Money();
		pricePerNight.setCurrency("$");
		pricePerNight.setValue(23.0);
		result.setPricePerNight(pricePerNight);
		result.setBookComments("aasdasdadas");
		result.setLodge(lodgeService.findOne(85));
		bookingService.save(result);
		authenticate(null);
	}
//	- A user who is authenticated as an pilgrim must be able to:
//	Manage his or her bookings. Bookings can be cancelled or 
//  changed until the day before.
	//Create a booking as an admin, it must fail
	@Test(expected=IllegalArgumentException.class)
	public void testCreateByAdmin(){
		authenticate("admin1");
		Booking result = bookingService.create();
		result.setArrivalDate(new Date(System.currentTimeMillis()+7735000));
		result.setNumberOfNights(7);
		result.setNumberOfBeds(7);
		Money pricePerNight=new Money();
		pricePerNight.setCurrency("$");
		pricePerNight.setValue(23.0);
		result.setPricePerNight(pricePerNight);
		result.setBookComments("aasdasdadas");
		result.setLodge(lodgeService.findOne(85));
		bookingService.save(result);
		authenticate(null);
	}
	
//	- A user who is authenticated as an pilgrim must be able to:
//	Manage his or her bookings. Bookings can be cancelled or 
//  changed until the day before.
	//Modify a booking as a pilgrim
	@Test
	public void testModifyByPilgrim(){
		authenticate("customer0");
		Booking result = bookingService.findOne(94);
		result.setArrivalDate(new Date(System.currentTimeMillis()+7735000));
		result.setBookComments("aasdasdadas");
		bookingService.save(result);
		authenticate(null);
	}
//	- A user who is authenticated as an pilgrim must be able to:
//	Manage his or her bookings. Bookings can be cancelled or 
//  changed until the day before.
	//Modify a booking as an admin, it must fail
	@Test(expected=IllegalArgumentException.class)
	public void testModifyByAdmin(){
		authenticate("admin1");
		Booking result = bookingService.findOne(94);
		result.setBookComments("aasdasdadas");
		result.setLodge(lodgeService.findOne(85));
		bookingService.save(result);
		authenticate(null);
	}
//	- A user who is authenticated as an pilgrim must be able to:
//	Manage his or her bookings. Bookings can be cancelled or 
//  changed until the day before.
	//Modify a booking as a pilgrim
	@Test
	public void testDeleteByPilgrim(){
		authenticate("customer0");
		Booking result = bookingService.findOne(94);
		bookingService.delete(result);
		authenticate(null);
	}
//	- A user who is authenticated as an pilgrim must be able to:
//	Manage his or her bookings. Bookings can be cancelled or 
//  changed until the day before.
	//Modify a booking as an admin, it must fail
	@Test(expected=IllegalArgumentException.class)
	public void testDeleteByAdmin(){
		authenticate("admin1");
		Booking result = bookingService.findOne(94);
		bookingService.delete(result);
		authenticate(null);
	}
//	- A user who is authenticated as an pilgrim must be able to:
//	Display a dashboard with the following information:
//		Booking history, including his or her assessments.
	//Trying as a pilgrim
	@Test
	public void testBookingHistoryByPilgrim(){
		authenticate("customer0");
		bookingService.findByPilgrim();
		authenticate(null);
	}	
//	- A user who is authenticated as an pilgrim must be able to:
//	Display a dashboard with the following information:
//		Booking history, including his or her assessments.
	//Trying as an admin, it must fail
	@Test(expected=NullPointerException.class)
	public void testBookingHistoryByAdmin(){
		authenticate("admin1");
		bookingService.findByPilgrim();
		authenticate(null);
	}	
//	- A user who is authenticated as an innkeeper must be able to:
//	List the bookings that the pilgrims have made to their lodges,
//	on a per-day basis.
	//Trying as an inkeeper
	@Test
	public void testBookingToLodgesByInkeeper(){
		authenticate("keeper1");
		UserAccount account=LoginService.getPrincipal();
		Innkeeper innkeeper=innkeeperService.findByUserAccount(account);
		bookingService.findBookingByInnkeeper(innkeeper.getId());
		authenticate(null);
	}
//	- A user who is authenticated as an innkeeper must be able to:
//	List the bookings that the pilgrims have made to their lodges,
//	on a per-day basis.
	//Trying as an pilgrim, it must fail
	@Test(expected=NullPointerException.class)
	public void testBookingToLodgesByPilgrim(){
		authenticate("customer0");
		UserAccount account=LoginService.getPrincipal();
		Innkeeper innkeeper=innkeeperService.findByUserAccount(account);
		bookingService.findBookingByInnkeeper(innkeeper.getId());
		authenticate(null);
	}
}
