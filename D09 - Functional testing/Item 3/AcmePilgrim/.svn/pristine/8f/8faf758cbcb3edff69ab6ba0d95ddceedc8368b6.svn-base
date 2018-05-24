package controllers;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import domain.Route;
import services.RouteService;

@Controller
@RequestMapping("/route")
public class RouteController extends AbstractController {
	//	Services --------------------------------------------------------------
	
	@Autowired
	private RouteService routeService ;
	
	
	// Constructors -----------------------------------------------------------
	
	public RouteController() {
		super();
	}
		
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView listAll() {
		ModelAndView result;
		
		Collection<Route> routes;
		routes=routeService.activeRoutes();
		String requestURI="route/list.do";
		
		result = new ModelAndView("route/list");
		result.addObject("routes",routes);
		result.addObject("requestURI", requestURI);
		return result;
	}
	
	@RequestMapping(value= "/listByKeyword", method = RequestMethod.GET)
	public ModelAndView listByKeyword(@RequestParam String keyword) {
		ModelAndView result;
		
		Collection<Route> routes = routeService.routesByKeyword(keyword);
		String requestURI="route/listByKeyword.do";
		
		result = new ModelAndView("route/list");
		result.addObject("routes",routes);
		result.addObject("requestURI", requestURI);
		return result;
	}
}