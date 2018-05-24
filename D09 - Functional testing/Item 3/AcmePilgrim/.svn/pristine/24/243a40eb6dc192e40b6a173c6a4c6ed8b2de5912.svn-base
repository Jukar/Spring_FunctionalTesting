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
import domain.Route;
import domain.Stage;
import domain.StageRating;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class StageRatingServiceTest extends AbstractTest{

	// ------------------- Supporting services -------------------
	
	@Autowired
	private StageRatingService stageRatingService;
	@Autowired
	private RegisterService registerService;
	@Autowired
	private StageService stageService;

	//----------------------------------------------------
	// POSITIVE TEST CASES
	//----------------------------------------------------
	//A user who is authenticated as an pilgrim must be able to:
	//Assess a stage that he or she has already completed.
	//With an existing pilgrim, we will create a StageRating for a Stage.
	@Test
	public void testCreate(){
		authenticate("customer1");
		
		StageRating sR=stageRatingService.create();
		Stage s=stageService.findOne(49);
		Assessment a=sR.getAssessment();
		
		a.setCreationMoment(new Date());
		a.setCommentAssessment("commentAssessment");
		a.setRatingDifficulty(5);
		a.setRatingLenght(5);
		
		sR.setAssessment(a);
		sR.setComment("comment");
		sR.setStage(s);
		sR.setRegister(registerService.findOne(79));
		sR.setStartMoment(new Date(System.currentTimeMillis()-3000));
		sR.setEndMoment(new Date(System.currentTimeMillis()-1500));
		stageRatingService.save(sR);
		
		authenticate(null);
	}
	
	
	//----------------------------------------------------
	// NEGATIVE TEST CASES
	//----------------------------------------------------	
	//A user who is authenticated as an pilgrim must be able to:
	//Assess a stage that he or she has already completed.
	//With an existing innkeeper, we will try to create a StageRating and capture the exception
	@Test(expected=IllegalArgumentException.class)
	public void testCreateException(){
		authenticate("keeper1");
		
		StageRating sR=stageRatingService.create();
		Stage s=stageService.findOne(46);
		Assessment a=new Assessment();
		
		a.setCreationMoment(new Date());
		a.setCommentAssessment("commentAssessment");
		a.setRatingDifficulty(5);
		a.setRatingLenght(5);
		
		sR.setAssessment(a);
		sR.setComment("comment");
		sR.setStage(s);
		sR.setRegister(registerService.findOne(75));
	
		stageRatingService.save(sR);
		authenticate(null);
	}
}
