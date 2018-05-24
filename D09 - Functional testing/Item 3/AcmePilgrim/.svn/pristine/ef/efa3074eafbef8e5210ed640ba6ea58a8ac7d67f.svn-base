package services;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Anecdote;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class AnecdoteServiceTest extends AbstractTest{
	@Autowired
	private AnecdoteService anecdoteService;
	
	@Test
	public void testFindByPilgrimId(){
		
		Collection<Anecdote> result = anecdoteService.findByPilgrimId(28);
		System.out.println("Anecdotes of one pilgrim : " + result.size());
		for(Anecdote aux:result){
			System.out.println(aux.getTitle());
		}
	}
	
	@Test
	public void testCountAnecdotes(){
		Integer result = anecdoteService.countAnecdotes();
		System.out.println("Number of anecdotes stored in the system :" + result);
	}
	
	@Test
	public void testAvrAnecdotes(){
		Double result = anecdoteService.avrAnecdotes();
		System.out.println("Average of anecdotes per pilgrim :" + result);
	}

}
