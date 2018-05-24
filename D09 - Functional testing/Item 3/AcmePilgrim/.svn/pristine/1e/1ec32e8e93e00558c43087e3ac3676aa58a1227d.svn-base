package services;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Assessment;
import domain.Booking;
import domain.LodgeAssessment;
import domain.StageRating;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class StageAssessmentServiceTest extends AbstractTest{
		
		// ------------------- Supporting services -------------------
		@Autowired
		private StageRatingService stageRatingService;

		//----------------------------------------------------
		// POSITIVE TEST CASES
		//----------------------------------------------------

		//A user who is authenticated as a pilgrim must be able to:
		//Register that he or she is completed a stage in a route.
		//We have to attach an assessment for a stage rating.
		@Test 
		public void testAddAssessmentStageRating(){
			authenticate("customer1");
			
			Assessment assess = new Assessment();
			assess.setCreationMoment(new Date());
			assess.setCommentAssessment("soy un comentario");
			assess.setRatingDifficulty(3);
			assess.setRatingLenght(5);
			
			StageRating result = stageRatingService.findOne(83);
			result.setAssessment(assess);
			stageRatingService.save(result);
			authenticate(null);
			
		}
		
		//----------------------------------------------------
		// NEGATIVE TEST CASES
		//----------------------------------------------------
		//A user who is authenticated as a pilgrim must be able to:
		//Register that he or she is completed a stage in a route.
		//We have to attach an assessment for a stage rating with a wrong user.
		@Test(expected=IllegalArgumentException.class)
		public void testAddAssessmentStageRatingException(){
			authenticate("keeper1");
			
			Assessment assess = new Assessment();
			assess.setCreationMoment(new Date());
			assess.setCommentAssessment("soy un comentario");
			assess.setRatingDifficulty(3);
			assess.setRatingLenght(5);
			
			StageRating result = stageRatingService.findOne(83);
			result.setAssessment(assess);
			stageRatingService.save(result);
			authenticate(null);
			
		}
}
