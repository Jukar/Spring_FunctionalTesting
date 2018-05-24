package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.GPS;
import domain.Stage;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class StageServiceTest extends AbstractTest{

	// ------------------- Supporting services -------------------
	
	@Autowired
	private StageService stageService;

	// ------------------- Service under test --------------------
	
	@Test
	public void testFindOne() {
		System.out.println("Stage: " + stageService.findOne(32).getName());
	}

	@Test
	public void testFindAll(){
		for( Stage aux : stageService.findAll())
			System.out.println("Stage: " + aux.getName() + " Con tamaño: " + aux.getLenghtKm());;
	}
	
//		Error de getters en los atributos derivados
	@Test
	public void testSaveAndDelete(){
		authenticate("admin1");
		
		System.out.println("Antes");
		System.out.println(stageService.findAll().size());
		for( Stage aux : stageService.findAll())
			System.out.println("Stage: " + aux.getName() + " Con tamaño: " + aux.getLenghtKm());
			
		GPS oriAndDest = new GPS();
		oriAndDest.setTitle("testing");
		oriAndDest.setLatitude(10.0);
		oriAndDest.setLongitude(10.0);
		Stage stage = stageService.create();
		stage.setName("testing");
		stage.setDescription("testing");
		stage.setOrigin(oriAndDest);
		stage.setDestination(oriAndDest);
		stage.setLenghtKm(10.0);
		stage.setDifficultyLevel(9);
		stage.setBriefTextDescription("testing");
		
		stageService.save(stage);
		
		System.out.println("Despues de Crear");
		System.out.println(stageService.findAll().size());
		for( Stage aux : stageService.findAll())
			System.out.println("Stage: " + aux.getName() + " Con tamaño: " + aux.getLenghtKm());
		
		List <Stage> stages = new ArrayList<Stage>(stageService.findAll());
		stageService.delete(stages.get(stages.size()-1));
		
		System.out.println("Despues de Borrar");
		System.out.println(stageService.findAll().size());
		for( Stage aux : stageService.findAll())
			System.out.println("Stage: " + aux.getName() + " Con tamaño: " + aux.getLenghtKm());
		
		authenticate(null);
	}
	
	@Test
	public void findByRouteId(){
		Collection<Stage> stages = stageService.findByRouteId(43);
		for(Stage aux : stages)
			System.out.println("Name: " + aux.getName() + " Descripción: " + aux.getDescription());
	}

	@Test
	public void findNotInRouteByRouteId(){
		Collection<Stage> stages = stageService.findNotInRouteByRouteId(43);
		for(Stage aux : stages)
			System.out.println("Name: " + aux.getName() + " Descripción: " + aux.getDescription());
	}
}