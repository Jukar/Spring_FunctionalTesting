package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AnecdoteService;
import services.ComplaintService;
import services.PilgrimService;
import services.RouteService;

import controllers.AbstractController;
import domain.Pilgrim;
import domain.Route;

@Controller
@RequestMapping("/administrator")
public class DashboardAdministratorController extends AbstractController{
	
	//Services-----------------------
	@Autowired
	private RouteService routeService;
	@Autowired
	private PilgrimService pilgrimService;
	@Autowired
	private ComplaintService complaintService;
	@Autowired
	private AnecdoteService anecdoteService;
	
	//Listing------------------------
	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	public ModelAndView dashboard(){
		ModelAndView result;
		
		//C
		Collection<Route> routesByRegister;
		routesByRegister=routeService.routesByRegistrationsDesc();
		Collection<Pilgrim> pilgrimsByRegisterDesc;
		pilgrimsByRegisterDesc=pilgrimService.listPilgrimRegistrationDesc();
		Collection<Route> routesByVotes;
		routesByVotes=routeService.routesByAveRatingAsc();
		//B
		Integer totalNumber;
		totalNumber = anecdoteService.countAnecdotes();
		Double averageNumber;
		averageNumber = anecdoteService.avrAnecdotes();
		Collection<Pilgrim> pilgrimsMoreAnecdotes;
		pilgrimsMoreAnecdotes = pilgrimService.findPilgrimWhithMoreAnecdotes();
		//A
		Double rate=complaintService.avrComplaint();
		Collection<Pilgrim> pligrimsMoreComplaints;
		pligrimsMoreComplaints=pilgrimService.findPilgimMoreComplaint();
		
		
		result=new ModelAndView("administrator/dashboard");
		
		//C
		result.addObject("routesByRegister",routesByRegister);
		result.addObject("pilgrimsByRegisterDesc",pilgrimsByRegisterDesc);
		result.addObject("routesByVotes",routesByVotes);
		//B
		result.addObject("totalNumber", totalNumber);
		result.addObject("averageNumber",averageNumber);
		result.addObject("pilgrimsMoreAnecdotes", pilgrimsMoreAnecdotes);
		//A
		result.addObject("rate",rate);
		result.addObject("pligrimsMoreComplaints",pligrimsMoreComplaints);
		
		return result;
	}
}
