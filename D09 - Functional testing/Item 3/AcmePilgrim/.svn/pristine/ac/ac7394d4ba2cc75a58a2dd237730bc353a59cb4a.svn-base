package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.AdministratorService;
import services.PilgrimService;
import services.RouteService;

import domain.Administrator;
import domain.Pilgrim;
import domain.Route;

@Controller
@RequestMapping("")
public class ActorProfileController extends AbstractController{
	@Autowired
	private PilgrimService pilgrimService ;
	@Autowired
	private RouteService routeService ;
	@Autowired
	private AdministratorService administratorService ;
	
	@RequestMapping(value="/pilgrim/profile",method=RequestMethod.GET)
	public ModelAndView seePilgrimProfile() {
		ModelAndView result;
		UserAccount userAccount=LoginService.getPrincipal();
		
		Pilgrim pilgrim;
		pilgrim=pilgrimService.findByUserAccount(userAccount);
		
		result = new ModelAndView("pilgrim/profile");
		result.addObject("pilgrim",pilgrim);
		String requestURI="pilgrim/profile.do";
		result.addObject("requestURI", requestURI);
		Collection<Route> routes= routeService.findByPilgrimId(pilgrim.getId());
		result.addObject("routes", routes);
		return result;
	}
	
	@RequestMapping(value="/administrator/profile",method=RequestMethod.GET)
	public ModelAndView seeAdministratorProfile() {
		ModelAndView result;
		UserAccount userAccount=LoginService.getPrincipal();
		
		Administrator admin;
		admin=administratorService.findByUserAccount(userAccount);
		
		result = new ModelAndView("administrator/profile");
		result.addObject("administrator",admin);
		Collection<Route> routes= routeService.activeRoutes();
		result.addObject("routes", routes);
		String requestURI="administrator/profile.do";
		result.addObject("requestURI", requestURI);
		return result;
	}
}
