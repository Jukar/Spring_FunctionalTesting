package controllers.pilgrim;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import controllers.AbstractController;
import domain.Route;
import services.RegisterService;
import services.RouteService;

@Controller
@RequestMapping("/route/pilgrim")
public class RoutePilgrimController extends AbstractController {
	
	//	Services --------------------------------------------------------------
	
	@Autowired
	private RouteService routeService ;
	@Autowired
	private RegisterService registerService ;
	
	// Constructors -----------------------------------------------------------
	
	public RoutePilgrimController() {
		super();
	}
		
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView listAll() {
		ModelAndView result;
		
		Collection<Route> routes;
		routes=routeService.findByPilgrim();
		String requestURI="route/pilgrim/list.do";
		
		result = new ModelAndView("route/list");
		result.addObject("routes",routes);
		result.addObject("requestURI", requestURI);
		return result;
	}
	
	@RequestMapping(value = "/registerOnRoute", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam int routeId){
		ModelAndView result;
		
		Route route = routeService.findOne(routeId);
		
		try{
			registerService.registerOnRoute(route);
			result= new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result= new ModelAndView("redirect:list.do");
		}
		return result;
	}
}