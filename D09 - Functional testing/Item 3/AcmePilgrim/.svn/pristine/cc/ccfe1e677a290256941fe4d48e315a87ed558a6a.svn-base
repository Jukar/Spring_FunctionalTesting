/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.administrator;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import controllers.AbstractController;
import domain.Stage;
import services.StageService;

@Controller
@RequestMapping("/stage/administrator")
public class StageAdministratorController extends AbstractController {
	
	//	Services --------------------------------------------------------------
	
	@Autowired
	private StageService stageService ;
	
	
	// Constructors -----------------------------------------------------------
	
	public StageAdministratorController() {
		super();
	}
		
	// Creation --------------------------------------------------------------
	
	@RequestMapping(value= "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		
		Stage stage = stageService.create();
		
		result = createEditModelAndView(stage);
		result.addObject("stage",stage);
		return result;
	}
	
	// Edition ---------------------------------------------------------------
	
	@RequestMapping(value= "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int stageId) {
		ModelAndView result;
		
		Stage stage = stageService.findOne(stageId);
		Assert.notNull(stage);
		
		result = createEditModelAndView(stage);
		result.addObject("stage",stage);
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Stage stage, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors())
			result = createEditModelAndView(stage);
		else{
			try{

				stageService.save(stage);
				result = new ModelAndView("redirect:../list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(stage, "stage.commit.error");
			}
		}
		
		return result;
	}

	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView cancel(Stage stage, BindingResult binding){
		ModelAndView result;
		
		try{
			stageService.delete(stage);
			result = new ModelAndView("redirect:../list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(stage, "stage.commit.error");
		}
		
		return result;
	}
	
	// Ancillary methods -----------------------------------------------------
	
	private ModelAndView createEditModelAndView(Stage stage) {
		ModelAndView result;
		result = createEditModelAndView(stage, null);
		return result;
	}
	private ModelAndView createEditModelAndView(Stage stage, String message) {
		ModelAndView result;
		result = new ModelAndView("stage/administrator/edit");
		result.addObject("command", stage);
		result.addObject("message", message);
		return result;
	}
}