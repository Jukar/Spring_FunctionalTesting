/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.pilgrim;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RegisterService;
import services.StageRatingService;
import services.StageService;
import controllers.AbstractController;
import domain.Assessment;
import domain.Register;
import domain.Stage;
import domain.StageRating;

@Controller
@RequestMapping("/stageRating/pilgrim")
public class StageRatingPilgrimController extends AbstractController {
	
	//	Services --------------------------------------------------------------
	
	@Autowired
	private StageRatingService stageRatingService ;
	@Autowired
	private StageService stageService;
	@Autowired
	private RegisterService registerService;
	
	
	// Constructors -----------------------------------------------------------
	
	public StageRatingPilgrimController() {
		super();
	}
		
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping(value= "/listByRoute", method = RequestMethod.GET)
	public ModelAndView listByStageRating(@RequestParam int routeId) {
		ModelAndView result;
		
		Collection<StageRating> stageRatings = stageRatingService.findByRouteId(routeId);
		String requestURI="stageRating/pilgrim/listByRoute.do";
		
		result = new ModelAndView("stageRating/list");
		result.addObject("stageRatings", stageRatings);
		result.addObject("requestURI", requestURI);
		return result;
	}
	
	@RequestMapping("/list")
	public ModelAndView listAll() {
		ModelAndView result;
		
		Collection<StageRating> stageRatings=stageRatingService.findByPilgrimId();
		String requestURI="stageRatingRating/pilgrim/list.do";
		
		result = new ModelAndView("stageRating/list");
		result.addObject("stageRatings",stageRatings);
		result.addObject("requestURI", requestURI);
		return result;
	}
	
	@RequestMapping("/edit")
	public ModelAndView create(@RequestParam int routeId){
		ModelAndView result;

		Date creat=new Date();
		stageService.findByRouteId(routeId);
		Collection<Register> registers = registerService.findByRoutePilgrimId(routeId);
		Collection<Stage> stages = stageService.findByRouteId(routeId);
		
		StageRating stageRating = new StageRating();
		Assessment assessment=new Assessment();
		assessment.setCreationMoment(creat);
		stageRating.setAssessment(assessment);
		
		result=new ModelAndView("stageRating/pilgrim/edit");
		result.addObject("command",stageRating);
		result.addObject("registers",registers);
		result.addObject("stages",stages);
		return result;
	}
	
	//Create ----------------------------------------
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public ModelAndView create(){
		System.out.println("Jelou?");
		ModelAndView result;
		StageRating stagRat;
		
		stagRat=stageRatingService.create();
		Assert.notNull(stagRat);
		result=createEditModelAndView(stagRat);
		
		return result;
	}
	
	
	
	// Save--------------------------------------
	
	@RequestMapping(value= "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid StageRating stagRat, BindingResult binding){
		ModelAndView result;
		System.out.println("hola?");
		if(binding.hasErrors()){
			result = createEditModelAndView(stagRat);
		}else{
			try{
				stageRatingService.save(stagRat);
				result = new ModelAndView("redirect:/route/list.do");
			}catch (Throwable oops){
				result = createEditModelAndView(stagRat, "anecdote.edit.save.error");
			}
		}
		
		return result;
	}
	
	private ModelAndView createEditModelAndView(StageRating stagRat){
		ModelAndView result;
		result = createEditModelAndView(stagRat, null);
		return result;
	}
	
	private ModelAndView createEditModelAndView(StageRating stagRat, String message){
		ModelAndView result;
		
		result = new ModelAndView("stageRating/pilgrim/edit");
		result.addObject("command",stagRat);
		result.addObject("message",message);
	
		return result;
	}
	
}