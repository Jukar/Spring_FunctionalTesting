/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import domain.Stage;
import services.StageService;

@Controller
@RequestMapping("/stage")
public class StageController extends AbstractController {
	
	//	Services --------------------------------------------------------------
	
	@Autowired
	private StageService stageService ;
	
	
	// Constructors -----------------------------------------------------------
	
	public StageController() {
		super();
	}
		
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping(value= "/listByRoute", method = RequestMethod.GET)
	public ModelAndView listByRoute(@RequestParam int routeId) {
		ModelAndView result;
		
		Collection<Stage> stages = stageService.findByRouteId(routeId);
		String requestURI="stage/listByRoute.do";
		
		result = new ModelAndView("stage/list");
		result.addObject("stages", stages);
		result.addObject("requestURI", requestURI);
		return result;
	}
	
	@RequestMapping("/list")
	public ModelAndView listAll() {
		ModelAndView result;
		
		Collection<Stage> stages=stageService.findAll();
		String requestURI="stage/list.do";
		
		result = new ModelAndView("stage/list");
		result.addObject("stages",stages);
		result.addObject("requestURI", requestURI);
		return result;
	}
}