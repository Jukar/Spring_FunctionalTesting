package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.CreditCard;
import domain.Innkeeper;
import domain.Pilgrim;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class InnkeeperServiceTest extends AbstractTest{
	@Autowired
	private InnkeeperService innkeeperService;
	
	//----------------------------------------------------
	// POSITIVE TEST CASES
	//----------------------------------------------------
	//A user who is not authenticated must be able to:
	//Register to the system as an innkeeper.
	//We will try to register as a innkeeper
	@Test
	public void testRegister(){
		Innkeeper innkeeper=innkeeperService.create();
		Date fechaProb=new Date();
		fechaProb.setDate(fechaProb.getDate()-7500);
		
		innkeeper.setName("name");
		innkeeper.setSurname("surname");
		innkeeper.setNationality("nationality");
		innkeeper.setBirthDate(fechaProb);
		innkeeper.setContactPhone("954954954");
		innkeeper.setEmailAddress("Correo@gmail.com");
		
		CreditCard credit=new CreditCard();
		credit.setBrandName("brandName");
		credit.setCvvCode(345);
		credit.setExpirationMonth(10);
		credit.setExpirationYear(2015);
		credit.setHolderName("holderName");
		credit.setNumber("5166707375607495");
		
		innkeeper.setCreditCard(credit);
		
		innkeeperService.save(innkeeper);
	}
	
	
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//List of innkeepers, in descending order of number of lodges managed.
	//With an existing administrator, we want to check if he can find all the innkeepers.
	@Test
	public void testInnkeepersNumberOfLodges(){
		authenticate("admin1");
		Collection<Innkeeper> all=innkeeperService.findAllByLodgeNumberDESC();
		List<Innkeeper> lista=new ArrayList<Innkeeper>();
		for(Innkeeper i:all){
			lista.add(i);
		}
		Innkeeper inn=lista.get(0);
		Assert.isTrue(inn.getName().equals("Name7"));
		authenticate(null);
	}

	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//The innkeeper/s who has/have managed more bookings.
	//With an existing administrator, we want to check if he can find the innkeeper with more bookings.
	@Test
	public void testInnkeepersMoreBooking(){
		authenticate("admin1");
		Collection<Innkeeper> all=innkeeperService.findWithMoreBookings();
		List<Innkeeper> lista=new ArrayList<Innkeeper>();
		for(Innkeeper i:all){
			lista.add(i);
		}
		Innkeeper inn=lista.get(0);
		Assert.isTrue(inn.getName().equals("Name7"));
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//The innkeeper/s who has/have more unpaid invoices.
	//With an existing administrator, we want to check if he can find the innkeeper with more unpaid invoices.
	@Test
	public void testInnkeepersMoreUnpaidInvoices(){
		authenticate("admin1");
		Collection<Innkeeper> all=innkeeperService.findKeeperMoreUnpaidInvoices();
		Assert.isTrue(all.size()==1);
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//The innkeeper/s who has/have earned more money.
	//With an existing administrator, we want to check if he can find the innkeeper with more earned money.
	@Test
	public void testInnkeepersMoreMoney(){
		authenticate("admin1");
		Collection<Innkeeper> all=innkeeperService.findWithMoreMoneyEarned();
		List<Innkeeper> lista=new ArrayList<Innkeeper>();
		for(Innkeeper i:all){
			lista.add(i);
		}
		Innkeeper inn=lista.get(0);
		Assert.isTrue(inn.getName().equals("Name7"));
		authenticate(null);
	}
	
	
	//----------------------------------------------------
	// NEGATIVE TEST CASES
	//----------------------------------------------------

	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//List of innkeepers, in descending order of number of lodges managed.
	//With an existing customer, we will try to find all the innkeepers and capture the exception.
	@Test(expected=IllegalArgumentException.class)
	public void testInnkeepersNumberOfLodgesException(){
		authenticate("customer0");
		Collection<Innkeeper> all=innkeeperService.findAllByLodgeNumberDESC();
		List<Innkeeper> lista=new ArrayList<Innkeeper>();
		for(Innkeeper i:all){
			lista.add(i);
		}
		Innkeeper inn=lista.get(0);
		Assert.isTrue(inn.getName().equals("Name7"));
		authenticate(null);
	}

	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//The innkeeper/s who has/have managed more bookings.
	//With an existing customer, we will try to find the innkeeper with more bookings.
	@Test(expected=IllegalArgumentException.class)
	public void testInnkeepersMoreBookingException(){
		authenticate("customer0");
		Collection<Innkeeper> all=innkeeperService.findWithMoreBookings();
		List<Innkeeper> lista=new ArrayList<Innkeeper>();
		for(Innkeeper i:all){
			lista.add(i);
		}
		Innkeeper inn=lista.get(0);
		Assert.isTrue(inn.getName().equals("Name7"));
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//The innkeeper/s who has/have more unpaid invoices.
	//With an existing administrator, we want to check if he can find the innkeeper with more unpaid invoices and
	//capture the exception.
	@Test(expected=IllegalArgumentException.class)
	public void testInnkeepersMoreUnpaidInvoicesException(){
		authenticate("customer0");
		Collection<Innkeeper> all=innkeeperService.findKeeperMoreUnpaidInvoices();
		Assert.isTrue(all.size()==1);
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Display a dashboard with the following information:
	//The innkeeper/s who has/have earned more money.
	//With an existing customer, we will try to find the innkeeper with more earned money and
	//capture the exception.
	@Test(expected=IllegalArgumentException.class)
	public void testInnkeepersMoreMoneyException(){
		authenticate("customer0");
		Collection<Innkeeper> all=innkeeperService.findWithMoreMoneyEarned();
		List<Innkeeper> lista=new ArrayList<Innkeeper>();
		for(Innkeeper i:all){
			lista.add(i);
		}
		Innkeeper inn=lista.get(0);
		Assert.isTrue(inn.getName().equals("Name7"));
		authenticate(null);
	}
}
