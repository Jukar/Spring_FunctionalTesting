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
import domain.Route;
import services.RouteService;

@Controller
@RequestMapping("/route/administrator")
public class RouteAdministratorController extends AbstractController {
	
	//	Services --------------------------------------------------------------
	
	@Autowired
	private RouteService routeService ;
	
	// Constructors -----------------------------------------------------------
	
	public RouteAdministratorController() {
		super();
	}
	
	// Creation --------------------------------------------------------------
	
	@RequestMapping(value= "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		
		Route route = routeService.create();
		
		result = createEditModelAndView(route);
		result.addObject("route",route);
		return result;
	}
	
	// Edition ---------------------------------------------------------------
	
	@RequestMapping(value= "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int routeId) {
		ModelAndView result;
		
		Route route = routeService.findOne(routeId);
		Assert.notNull(route);
		
		result = createEditModelAndView(route);
		result.addObject("route",route);
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Route route, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors())
			result = createEditModelAndView(route);
		else{
			try{

				routeService.save(route);
				result = new ModelAndView("redirect:../list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(route, "route.commit.error");
			}
		}
		
		return result;
	}

	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView cancel(Route route, BindingResult binding){
		ModelAndView result;
		
		try{
			routeService.delete(route);
			result = new ModelAndView("redirect:../list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(route, "route.commit.error");
		}
		
		return result;
	}
	
	// Ancillary methods -----------------------------------------------------
	
	private ModelAndView createEditModelAndView(Route route) {
		ModelAndView result;
		result = createEditModelAndView(route, null);
		return result;
	}
	private ModelAndView createEditModelAndView(Route route, String message) {
		ModelAndView result;
		result = new ModelAndView("route/administrator/edit");
		result.addObject("command", route);
		result.addObject("message", message);
		return result;
	}
}