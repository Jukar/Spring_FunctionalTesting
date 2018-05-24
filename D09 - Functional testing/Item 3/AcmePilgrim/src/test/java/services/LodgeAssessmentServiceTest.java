package services;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Booking;
import domain.LodgeAssessment;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LodgeAssessmentServiceTest extends AbstractTest{
	
	// ------------------- Supporting services -------------------
	@Autowired
	private BookingService bookingService;

	//----------------------------------------------------
	// POSITIVE TEST CASES
	//----------------------------------------------------

	//A user who is authenticated as a pilgrim must be able to:
	//After staying in a lodge, he or she can assess it.
	//A user who is authenticated as a pilgrim must be able to:
	//After staying in a lodge, he or she can assess it.
	@Test 
	public void testAddAssessmentLodge(){
		authenticate("customer3");
		
		LodgeAssessment assess = new LodgeAssessment();
		assess.setCreationMoment(new Date());
		assess.setComments("soy un comentario");
		assess.setLocationRate(5);
		assess.setRoomsRate(7);
		assess.setServiceRate(7);
		assess.setPriceRate(6);
		
		Booking result = bookingService.findOne(97);
		result.setLodgeAssessment(assess);
		bookingService.save(result);
		authenticate(null);
		
	}
	
	//----------------------------------------------------
	// NEGATIVE TEST CASES
	//----------------------------------------------------

	//A user who is authenticated as a pilgrim must be able to:
	//After staying in a lodge, he or she can assess it.
	//A user who is authenticated as a pilgrim must be able to:
	//We are going to login as an innkeeper and try to assess the booking. 
	@Test(expected=IllegalArgumentException.class) 
	public void testAddAssessmentLodgeException(){
		authenticate("keeper1");
		
		LodgeAssessment assess = new LodgeAssessment();
		assess.setCreationMoment(new Date());
		assess.setComments("soy un comentario");
		assess.setLocationRate(5);
		assess.setRoomsRate(7);
		assess.setServiceRate(7);
		assess.setPriceRate(6);
		
		Booking result = bookingService.findOne(97);
		result.setLodgeAssessment(assess);
		bookingService.save(result);
		authenticate(null);
		
	}
}
