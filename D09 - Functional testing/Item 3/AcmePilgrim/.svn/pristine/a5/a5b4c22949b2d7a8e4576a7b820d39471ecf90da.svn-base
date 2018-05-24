package services;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Pilgrim;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class PilgrimServiceTest extends AbstractTest{
	@Autowired
	private PilgrimService pilgrimService;
	
	@Test
	public void testFindPilgrimByKeyword(){
		Collection<Pilgrim> result = pilgrimService.findPilgrimByKeyword("Name3");
		for(Pilgrim aux:result){
			System.out.println("Search by name : " + aux.getEmailAddress());
		}
		Collection<Pilgrim> result2 = pilgrimService.findPilgrimByKeyword("Surname3");
		for(Pilgrim aux2:result2){
			System.out.println("Search by surname : " + aux2.getEmailAddress());
		}
		Collection<Pilgrim> result3 = pilgrimService.findPilgrimByKeyword("name3@gmail.com");
		for(Pilgrim aux3:result3){
			System.out.println("Search by email : " + aux3.getEmailAddress());
		}
	}
	
	@Test
	public void testListPilgrimRegistrationDesc(){
		Collection<Pilgrim> result = pilgrimService.listPilgrimRegistrationDesc();
		for(Pilgrim aux:result){
			System.out.println(aux.getName());
		}
	}

	@Test
	public void testFindPilgrimWhithMoreAnecdotes(){
		Collection<Pilgrim> result = pilgrimService.findPilgrimWhithMoreAnecdotes();
		for(Pilgrim aux:result){
			System.out.println(aux.getName());
		}
	}
	
	@Test
	public void testFindPilgimMoreComplaint(){
		Collection<Pilgrim> result = pilgrimService.findPilgimMoreComplaint();
		for(Pilgrim aux:result){
			System.out.println(aux.getName());
		}
	}
	
	@Test
	public void testFindPilgimProfile(){
		Pilgrim pilgrim = pilgrimService.findOne(27);
		Collection<String> result = pilgrimService.findPilgrimProfile(pilgrim);
		for(String aux:result){
			System.out.println(aux);
		}
	}
}
