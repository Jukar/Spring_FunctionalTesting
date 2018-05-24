package services;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.CreditCard;
import domain.Pilgrim;
import domain.Register;
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
public class PilgrimServiceTest extends AbstractTest{
	@Autowired
	private PilgrimService pilgrimService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private BookingService bookingService;
	
	//----------------------------------------------------
	// POSITIVE TEST CASES
	//----------------------------------------------------
	//A user who is not authenticated must be able to:
	//Register to the system as a pilgrim.
	//Without any logged user, we want to register a new instance of pilgrim
	@SuppressWarnings("deprecation")
	@Test
	public void testRegister(){
		Pilgrim pilgrim=pilgrimService.create();
		Date fechaProb=new Date();
		fechaProb.setDate(fechaProb.getDate()-7500);
		
		pilgrim.setName("name");
		pilgrim.setSurname("surname");
		pilgrim.setNationality("nationality");
		pilgrim.setBirthDate(fechaProb);
		pilgrim.setContactPhone("954954954");
		pilgrim.setEmailAddress("Correo@gmail.com");
		
		CreditCard credit=new CreditCard();
		credit.setBrandName("brandName");
		credit.setCvvCode(345);
		credit.setExpirationMonth(10);
		credit.setExpirationYear(2015);
		credit.setHolderName("holderName");
		credit.setNumber("5166707375607495");
		
		pilgrim.setCreditCard(credit);

		pilgrimService.save(pilgrim);
	}
	//A user who is authenticated as an pilgrim must be able to:
	//View his or her profile, which consists of the personal information that Acme Pilgrim, Inc. 
	//stores about him or her and the list of routes to which he or she is registered.
	//With an existing pilgrim, we want to check if he can find his own information
	@Test
	public void testViewProfile(){
		authenticate("customer1");
		
		UserAccount user=LoginService.getPrincipal();
		Pilgrim actor=pilgrimService.findByUserAccount(user);
		
		Assert.isTrue(actor.getName().equals("Name4"));
		Set<Route> setAux=new HashSet<Route>();
		for(Register r:actor.registers){
			setAux.add(r.getRoute());
		}
		Assert.isTrue(setAux.size()==2);
		authenticate(null);
	}
	//A user who is authenticated as an pilgrim must be able to:
	//Display a dashboard with the following information:
	//List of pilgrims sorted by descending birth date.
	//With and existing pilgrim, we will check that he can list correctly the pilgrims ordered
	//by birthday.
	@Test
	public void testListByBirthdayPilgrim(){
		authenticate("customer0");
		Collection<Pilgrim> all=pilgrimService.listPilgrimBirthDateDesc();
		List<Pilgrim> pils=new ArrayList<Pilgrim>();
		for(Pilgrim p:all){
			pils.add(p);
		}
		Assert.isTrue(pils.get(0).getName().equals("Name4"));
		authenticate(null);
	}
	
	
	//A user who is authenticated as an innkeeper must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//List of pilgrims who have booked one of his or her lodges, grouped by nationality.
	//With an existing innkeeper we will check that he can find the pilgrims that has booking in at
	//least one of his lodges.
	@Test
	public void testFindPilgrimWithBookingsByInnkeeper(){
		authenticate("keeper1");
		Collection<Pilgrim> all=pilgrimService.findPilgrimWithBookingsByInnkeeper();
		Assert.isTrue(all.size()==3);
		authenticate(null);
	}
	
	//A user who is authenticated as an innkeeper must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//List of pilgrim/s who has/have made more bookings, grouped by nationality if there are more than one.
	//With an existing innkeeper, we want to check that is able to find the pilgrims with more bookings.
	@Test
	public void testPilgrimMoreBookingKeeper(){
		authenticate("keeper1");
		Collection<Pilgrim> all=pilgrimService.findPilgrimMoreBookings();
		Assert.isTrue(all.size()==1);
		authenticate(null);
	}
	
	//A user who is authenticated as an innkeeper must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//List of pilgrims sorted by descending birth date.
	//With and existing innkeeper, we will check that he can list correctly the pilgrims ordered
	//by birthday.
	@Test
	public void testListByBirthdayKeeper(){
		authenticate("keeper1");
		Collection<Pilgrim> all=pilgrimService.listPilgrimBirthDateDesc();
		List<Pilgrim> pils=new ArrayList<Pilgrim>();
		for(Pilgrim p:all){
			pils.add(p);
		}
		Assert.isTrue(pils.get(0).getName().equals("Name4"));
		authenticate(null);
	}
	//A user who is authenticated as an administrator must be able to:
	//Search for the pilgrims of the system using a single keyword that must be contained in their 
	//names, surnames, or email addresses.
	//With an existing administrator, we want to check if he can find by a keyword
	@Test
	public void testFindByKeyword(){
		authenticate("admin1");
		Collection<Pilgrim> all=pilgrimService.findPilgrimByKeyword("name");
		Assert.isTrue(all.size()==4);
		authenticate(null);
	}
	//A user who is authenticated as an administrator must be able to:
	//List the pilgrims of the system and navigate to their profiles.
	//With an existing administrator we will check that he can find all pilgrims
	@Test
	public void testFindAllPilgrim(){
		authenticate("admin1");
		Collection<Pilgrim> all=pilgrimService.findAll();
		Assert.isTrue(all.size()==4);
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//The list of pilgrims in descending order of registrations.
	//With an existing administrator we will check if he can find the pilgrims in the correct order
	@Test
	public void testPilgrimsOrderedByRegistration(){
		authenticate("admin1");
		Collection<Pilgrim> all=pilgrimService.listPilgrimRegistrationDesc();
		List<Pilgrim> lis=new ArrayList<Pilgrim>();
		for(Pilgrim p:all){
			lis.add(p);
		}
		Assert.isTrue(lis.get(0).getName().equals("Name4"));
		authenticate(null);
	}
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//The pilgrim/s who has/have published more anecdotes.
	//With an existing administrator we will check that he can find the pilgrim with more anecdotes
	@Test
	public void testPilgrimMoreAnecdotes(){
		authenticate("admin1");
		Collection<Pilgrim> all=pilgrimService.findPilgrimWhithMoreAnecdotes();
		Assert.isTrue(all.size()==1);
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//List the pilgrim/s who have created more complaints.
	//With an existing administrator we will check if he can find the pilgrims in the correct order.
	@Test
	public void testFindPilgrimMoreComplaints(){
		authenticate("admin1");
		Collection<Pilgrim> all=pilgrimService.findPilgimMoreComplaint();
		Assert.isTrue(all.size()==1);
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//List of pilgrims sorted by descending birth date.
	//With and existing innkeeper, we will check that he can list correctly the pilgrims ordered
	//by birthday.
	@Test
	public void testListByBirthdayAdmin(){
		authenticate("admin1");
		Collection<Pilgrim> all=pilgrimService.listPilgrimBirthDateDesc();
		List<Pilgrim> pils=new ArrayList<Pilgrim>();
		for(Pilgrim p:all){
			pils.add(p);
		}
		Assert.isTrue(pils.get(0).getName().equals("Name4"));
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//The pilgrim/s who has/have made more bookings.
	////With an existing administrator, we will try to find the pilgrim with more bookings.
	@Test
	public void testPilgrimMoreBookingAdmin(){
		authenticate("admin1");
		Collection<Pilgrim> all=pilgrimService.findPilgrimMoreBookings();
		Assert.isTrue(all.size()==1);
		authenticate(null);
	}
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//The pilgrim/s who has/have more unpaid invoices.
	//With an existing administrator, we will try to find all pilgrims with unpaid invoices and
	//capture the exception.
	@Test
	public void testPilgrimsMoreUnpaidInvoices(){
		authenticate("admin1");
		Collection<Pilgrim> all=pilgrimService.findPilgrimMoreUnpaidInvoices();
		Assert.isTrue(all.size()==1);
		authenticate(null);
	}
	
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//The pilgrim/s who has/have spent more money in lodges.
	//With an existing administrator we will check if he can find the pilgrim with more earned money
	@Test
	public void testPilgrimMoreMoneySpent(){
		authenticate("admin1");
		Collection<Pilgrim> all=pilgrimService.findWithMoreMoneySpent();
		Assert.isTrue(all.size()==2);
		authenticate(null);
	}


	
	//----------------------------------------------------
	// NEGATIVE TEST CASES
	//----------------------------------------------------
	//A user who is not authenticated must be able to:
	//Register to the system as a pilgrim.
	//Without any logged user, we will try to register a new instance of pilgrim without a name
	@Test(expected=ConstraintViolationException.class)
	public void testRegisterExpected(){
		Pilgrim pilgrim=pilgrimService.create();
		Date fechaProb=new Date();
		fechaProb.setDate(fechaProb.getDate()-7500);
		
		pilgrim.setSurname("surname");
		pilgrim.setNationality("nationality");
		pilgrim.setBirthDate(fechaProb);
		pilgrim.setContactPhone("954954954");
		pilgrim.setEmailAddress("Correo@gmail.com");
		
		CreditCard credit=new CreditCard();
		credit.setBrandName("brandName");
		credit.setCvvCode(345);
		credit.setExpirationMonth(10);
		credit.setExpirationYear(2015);
		credit.setHolderName("holderName");
		credit.setNumber("5166707375607495");
		
		pilgrim.setCreditCard(credit);

		pilgrimService.save(pilgrim);
	}
	//A user who is authenticated as an pilgrim must be able to:
	//View his or her profile, which consists of the personal information that Acme Pilgrim, Inc. 
	//stores about him or her and the list of routes to which he or she is registered.
	//With an existing pilgrim, we will try to obtain a different pilgrim and catch the exception
	@Test(expected=IllegalArgumentException.class)
	public void testViewProfileException(){
		authenticate("customer2");
		
		UserAccount user=LoginService.getPrincipal();
		Pilgrim actor=pilgrimService.findByUserAccount(user);
		
		Assert.isTrue(actor.getName().equals("Name4"));
		Set<Route> setAux=new HashSet<Route>();
		for(Register r:actor.registers){
			setAux.add(r.getRoute());
		}
		Assert.isTrue(setAux.size()==2);
		authenticate(null);
	}
	//A user who is authenticated as an pilgrim must be able to:
	//Display a dashboard with the following information:
	//List of pilgrims sorted by descending birth date.
	//With and existing pilgrim, we will try look for a wrong order and
	//catch the exception
	@Test(expected=IllegalArgumentException.class)
	public void testListByBirthdayPilgrimException(){
		authenticate("customer0");
		Collection<Pilgrim> all=pilgrimService.listPilgrimBirthDateDesc();
		List<Pilgrim> pils=new ArrayList<Pilgrim>();
		for(Pilgrim p:all){
			pils.add(p);
		}
		Assert.isTrue(pils.get(0).getName().equals("Name3"));
		authenticate(null);
	}
	
	
	//A user who is authenticated as an innkeeper must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//List of pilgrims who have booked one of his or her lodges, grouped by nationality.
	//With an existing admin we will try to find the pilgrims that has booking in at
	//least one of his lodges.
	@Test(expected=IllegalArgumentException.class)
	public void testFindPilgrimWithBookingsByInnkeeperException(){
		authenticate("admin1");
		Collection<Pilgrim> all=pilgrimService.findPilgrimWithBookingsByInnkeeper();
		Assert.isTrue(all.size()==3);
		authenticate(null);
	}
	
	//A user who is authenticated as an innkeeper must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//List of pilgrim/s who has/have made more bookings, grouped by nationality if there are more than one.
	//With an existing pilgrim, we will try to find the pilgrims with more bookings and catch
	//the exception.
	@Test(expected=IllegalArgumentException.class)
	public void testPilgrimMoreBookingKeeperException(){
		authenticate("customer0");
		Collection<Pilgrim> all=pilgrimService.findPilgrimMoreBookings();
		Assert.isTrue(all.size()==1);
		authenticate(null);
	}
	
	//A user who is authenticated as an innkeeper must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//List of pilgrims sorted by descending birth date.
	//With and existing innkeeper, we will try look for a wrong order and
	//catch the exception
	@Test(expected=IllegalArgumentException.class)
	public void testListByBirthdayKeeperException(){
		authenticate("keeper1");
		Collection<Pilgrim> all=pilgrimService.listPilgrimBirthDateDesc();
		List<Pilgrim> pils=new ArrayList<Pilgrim>();
		for(Pilgrim p:all){
			pils.add(p);
		}
		Assert.isTrue(pils.get(0).getName().equals("Name3"));
		authenticate(null);
	}
	//A user who is authenticated as an administrator must be able to:
	//Search for the pilgrims of the system using a single keyword that must be contained in their 
	//names, surnames, or email addresses.
	//With an existing pilgrim, we will try to find by a keyword and capture the exception.
	@Test(expected=IllegalArgumentException.class)
	public void testFindByKeywordException(){
		authenticate("customer0");
		Collection<Pilgrim> all=pilgrimService.findPilgrimByKeyword("name");
		Assert.isTrue(all.size()==4);
		authenticate(null);
	}
	//A user who is authenticated as an administrator must be able to:
	//List the pilgrims of the system and navigate to their profiles.
	//With an existing customer we will try to find all pilgrims and 
	//capture the exception.
	@Test(expected=IllegalArgumentException.class)
	public void testFindAllPilgrimException(){
		authenticate("customer0");
		Collection<Pilgrim> all=pilgrimService.findAll();
		Assert.isTrue(all.size()==4);
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//The list of pilgrims in descending order of registrations.
	//With an existing pilgrim we will try to find the pilgrims in the correct order
	//and capture the exception
	@Test(expected=IllegalArgumentException.class)
	public void testPilgrimsOrderedByRegistrationException(){
		authenticate("customer0");
		Collection<Pilgrim> all=pilgrimService.listPilgrimRegistrationDesc();
		List<Pilgrim> lis=new ArrayList<Pilgrim>();
		for(Pilgrim p:all){
			lis.add(p);
		}
		Assert.isTrue(lis.get(0).getName().equals("Name4"));
		authenticate(null);
	}
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//The pilgrim/s who has/have published more anecdotes.
	//With an existing pilgrim we will try to find the pilgrim with more anecdotes
	//and capture the exception
	@Test(expected=IllegalArgumentException.class)
	public void testPilgrimMoreAnecdotesException(){
		authenticate("customer0");
		Collection<Pilgrim> all=pilgrimService.findPilgrimWhithMoreAnecdotes();
		Assert.isTrue(all.size()==1);
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//List the pilgrim/s who have created more complaints.
	//With an existing pilgrim we will try to find the pilgrims in the correct order
	//and capture the exception.
	@Test(expected=IllegalArgumentException.class)
	public void testFindPilgrimMoreComplaintsException(){
		authenticate("customer0");
		Collection<Pilgrim> all=pilgrimService.findPilgimMoreComplaint();
		Assert.isTrue(all.size()==1);
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//List of pilgrims sorted by descending birth date.
	//With and existing innkeeper, , we will try look for a wrong order and
	//capture the exception
	@Test(expected=IllegalArgumentException.class)
	public void testListByBirthdayAdminException(){
		authenticate("admin1");
		Collection<Pilgrim> all=pilgrimService.listPilgrimBirthDateDesc();
		List<Pilgrim> pils=new ArrayList<Pilgrim>();
		for(Pilgrim p:all){
			pils.add(p);
		}
		Assert.isTrue(pils.get(0).getName().equals("Name3"));
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//The pilgrim/s who has/have made more bookings.
	//With an existing pilgrim, we will try to find the pilgrim with more bookings and 
	//capture the exception.
	@Test(expected=IllegalArgumentException.class)
	public void testPilgrimMoreBookingAdminException2(){
		authenticate("customer0");
		Collection<Pilgrim> all=pilgrimService.findPilgrimMoreBookings();
		Assert.isTrue(all.size()==2);
		authenticate(null);
	}
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information regarding their own lodges:
	//The pilgrim/s who has/have more unpaid invoices.
	//With an existing pilgrim, we will try to find all pilgrims with unpaid invoices and
	//capture the exception.
	@Test(expected=IllegalArgumentException.class)
	public void testPilgrimsMoreUnpaidInvoicesException(){
		authenticate("customer0");
		Collection<Pilgrim> all=pilgrimService.findPilgrimMoreUnpaidInvoices();
		Assert.isTrue(all.size()==1);
		authenticate(null);
	}
	
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//The pilgrim/s who has/have spent more money in lodges.
	//With an existing pilgrim we will try to find the pilgrim with more earned money
	//and capture the exception
	@Test(expected=IllegalArgumentException.class)
	public void testPilgrimMoreMoneySpentException(){
		authenticate("customer0");
		Collection<Pilgrim> all=pilgrimService.findWithMoreMoneySpent();
		Assert.isTrue(all.size()==2);
		authenticate(null);
	}
}
