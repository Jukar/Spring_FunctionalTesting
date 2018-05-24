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
import domain.Route;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AnecdoteServiceTest extends AbstractTest{
	@Autowired
	private AnecdoteService anecdoteService;
	@Autowired
	private RouteService routeService;
//	All users must be able to:
//	List the anecdotes. Whithout register into the system
	@Test
	public void testFindAllNotRegistered(){
		Collection<Anecdote> result = anecdoteService.findAll();
		Assert.isTrue(result.size()==3);
	}
//	List the anecdotes.Registered as an inkeeper
	@Test
	public void testFindAllInkeeper(){
		authenticate("keeper1");
		Collection<Anecdote> result = anecdoteService.findAll();
		Assert.isTrue(result.size()==3);
		authenticate(null);
	}
//	List the anecdotes.Registered as an pilgrim
	@Test
	public void testFindAllPilgrim(){
		authenticate("customer0");
		Collection<Anecdote> result = anecdoteService.findAll();
		Assert.isTrue(result.size()==3);
		authenticate(null);
	}
//	List the anecdotes.Registered as an admin
	@Test
	public void testFindAllAdmin(){
		authenticate("admin1");
		Collection<Anecdote> result = anecdoteService.findAll();
		Assert.isTrue(result.size()==3);
		authenticate(null);
	}
//	- A user who is authenticated as a pilgrim must be able to:
//		o Manage his anecdotes, which includes 
	//creating as a pilgrim
	@Test
	public void testCreateByPilgrim(){
		authenticate("customer1");
		Anecdote result = anecdoteService.create();
		result.setTitle("asafsaffasffa");
		result.setDescription("afasfasgfasgfweafeafeafaef");
		Date evMoment=new Date();
		evMoment.setYear(2016);
		List<Route> routes=(List<Route>) routeService.findAll();
		
		result.setEventMoment(evMoment);
		result.setRoute(routes.get(0));
		authenticate(null);
	}
//	- A user who is authenticated as a pilgrim must be able to:
//	o Manage his anecdotes, which includes 
//creating as a pilgrim	
	//trying to create as an admin, it must fail
	@Test(expected= IllegalArgumentException.class)
	public void testCreateByAdmin(){
		authenticate("admin1");
		Anecdote result = anecdoteService.create();
		result.setTitle("asafsaffasffa");
		result.setDescription("afasfasgfasgfweafeafeafaef");
		Date evMoment=new Date();
		evMoment.setYear(2016);
		List<Route> routes=(List<Route>) routeService.findAll();
		
		result.setEventMoment(evMoment);
		result.setRoute(routes.get(0));
		authenticate(null);
	}	
//	- A user who is authenticated as a pilgrim must be able to:
//	o Manage his anecdotes, which includes 
//  listing he or she's anecdotes
	@Test
	public void testFindByPilgrim(){
		authenticate("customer1");
		Collection<Anecdote> result = anecdoteService.findByPilgrim();
		Assert.isTrue(result.size()==2);
		authenticate(null);
	}
//	- A user who is authenticated as a pilgrim must be able to:
//	o Manage his anecdotes, which includes 
//  listing he or she's anecdotes
	//listing as an admin, it must fail
	@Test(expected= NullPointerException.class)
	public void testFindByAdmin(){
		authenticate("admin1");
		Collection<Anecdote> result = anecdoteService.findByPilgrim();
		Assert.isTrue(result.size()==2);
		authenticate(null);
	}
//	- A user who is authenticated as a pilgrim must be able to:
//	o Manage his anecdotes, which includes 
	//modifying it
	@Test
	public void testEditByPilgrim(){
		authenticate("customer1");
		Anecdote result = anecdoteService.findOne(75);
		result.setDescription("asdasdasdasdasd");
		anecdoteService.save(result);
		authenticate(null);
	}
//	- A user who is authenticated as a pilgrim must be able to:
//	o Manage his anecdotes, which includes 
	//modifying it but not the pilgrim who creates, it must fail
	@Test(expected= IllegalArgumentException.class)
	public void testEditByPilgrimNotCreated(){
		authenticate("customer0");
		Anecdote result = anecdoteService.findOne(77);
		result.setDescription("asdasdasdasdasd");
		anecdoteService.save(result);
		authenticate(null);
	}
	
	
//	- A user who is authenticated as a pilgrim must be able to:
//	o Manage his anecdotes, which includes 
	// deleting it
	@Test
	public void testDeletingByPilgrim(){
		authenticate("customer1");
		Anecdote result = anecdoteService.findOne(75);
		anecdoteService.delete(result);
		authenticate(null);
	}
//	- A user who is authenticated as a pilgrim must be able to:
//	o Manage his anecdotes, which includes 
	// deleting it as a pilgrim, but not the pilgrim who creates,
	// it must fail
	@Test(expected= IllegalArgumentException.class)
	public void testDeleteByPilgrimNotCreated(){
		authenticate("customer0");
		Anecdote result = anecdoteService.findOne(77);
		anecdoteService.delete(result);
		authenticate(null);
	}
//	- A user who is authenticated as an administrator must be able to:
//	o Display a dashboard with the following information:
//	 - The total number of anecdotes. Registered as an admin.
	@Test
	public void testCountAnecdotes(){
		authenticate("admin1");
		Integer result = anecdoteService.countAnecdotes();
		Assert.isTrue(result==3);
		authenticate(null);
	}
//	- A user who is authenticated as an administrator must be able to:
//	o Display a dashboard with the following information:
//	 - The total number of anecdotes. Registered as a pilgrim, it must fail.
	@Test(expected= IllegalArgumentException.class)
	public void testCountAnecdotesPilgrim(){
		authenticate("customer0");
		Integer result = anecdoteService.countAnecdotes();
		Assert.isTrue(result==3);
		authenticate(null);
	}
//	- A user who is authenticated as an administrator must be able to:
//	o Display a dashboard with the following information:
//	 - The average number of anecdotes per pilgrim.	Registered as an admin
	@Test
	public void testAvrAnecdotes(){
		authenticate("admin1");
		Double result = anecdoteService.avrAnecdotes();
		authenticate(null);
	}
//	- A user who is authenticated as an administrator must be able to:
//	o Display a dashboard with the following information:
//	 - The average number of anecdotes per pilgrim.	Registered as a pilgrim, it must fail
	@Test(expected= IllegalArgumentException.class)
	public void testAvrAnecdotesPilgrim(){
		authenticate("customer0");
		Double result = anecdoteService.avrAnecdotes();
		authenticate(null);
	}
}
