package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PilgrimService;
import services.RouteService;

import controllers.AbstractController;
import domain.Pilgrim;
import domain.Route;

@Controller
@RequestMapping("/pilgrim/administrator")
public class PilgrimAdministratorController  extends AbstractController{
	//	Services --------------------------------------------------------------
	
	@Autowired
	private PilgrimService pilgrimService ;
	@Autowired
	private RouteService routeService ;
	// Constructors -----------------------------------------------------------
	
	public PilgrimAdministratorController() {
		super();
	}
	
	@RequestMapping("/list")
	public ModelAndView listAll() {
		ModelAndView result;
		
		Collection<Pilgrim> pilgrims;
		pilgrims=pilgrimService.findAll();
		
		result = new ModelAndView("pilgrim/list");
		result.addObject("pilgrims",pilgrims);
		String requestURI="pilgrim/administrator/list.do";
		result.addObject("requestURI", requestURI);
		
		return result;
	}
	
	@RequestMapping(value="/profile",method=RequestMethod.GET)
	public ModelAndView seePilgrimProfile(@RequestParam int pilgrimId) {
		ModelAndView result;
		
		Pilgrim pilgrim;
		pilgrim=pilgrimService.findOne(pilgrimId);
		
		result = new ModelAndView("pilgrim/administrator/profile");
		result.addObject("pilgrim",pilgrim);
		String requestURI="pilgrim/administrator/profile.do";
		result.addObject("requestURI", requestURI);
		Collection<Route> routes= routeService.findByPilgrimId(pilgrimId);
		result.addObject("routes", routes);
		return result;
	}
	
	@RequestMapping(value = "/listByKeyword", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam String keyword){
		ModelAndView result;
		Collection<Pilgrim> pilgrims;
		
		pilgrims = pilgrimService.findPilgrimByKeyword(keyword);
		result = new ModelAndView("pilgrim/list");
		result.addObject("pilgrims", pilgrims);
		result.addObject("requestURI", "pilgrim/administrator/list.do");
		
		return result;
	}
}
