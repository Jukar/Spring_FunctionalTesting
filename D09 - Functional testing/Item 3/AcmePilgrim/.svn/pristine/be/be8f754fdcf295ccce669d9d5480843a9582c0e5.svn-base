package controllers.innkeeper;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.LodgeService;
import services.PilgrimService;

import controllers.AbstractController;
import domain.Lodge;
import domain.Pilgrim;
@Controller
@RequestMapping("/innkeeper")
public class DashboardInnkeeperController extends AbstractController{
	//Services-----------------------
	@Autowired
	private PilgrimService pilgrimService;
	@Autowired
	private LodgeService lodgeService;
	
	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	public ModelAndView dashboard(){
		ModelAndView result;
		
		Collection<Pilgrim> pilgrimsWithBookings=pilgrimService.findPilgrimWithBookingsByInnkeeper();
		Collection<Pilgrim> pilgrimWithMoreBookings=pilgrimService.findPilgrimMoreBookings();
		Collection<Pilgrim> pilgrimByBirthdayDate=pilgrimService.listPilgrimBirthDateDesc();
		Collection<Lodge> lodgesByBooking=lodgeService.lodgesOrderedByBookingsDesc();
		Collection<Lodge> lodgesByRating=lodgeService.lodgesOrderedByRatingAsc();
		Collection<Lodge> lodgesByPrice=lodgeService.lodgesOrderedByPriceDesc();
		Double occupancyRate=lodgeService.getOccupancyRateNextMonth();
		
		result=new ModelAndView("innkeeper/dashboard");
		
		result.addObject("pilgrimsWithBookings",pilgrimsWithBookings);
		result.addObject("pilgrimWithMoreBookings",pilgrimWithMoreBookings);
		result.addObject("pilgrimByBirthdayDate",pilgrimByBirthdayDate);
		result.addObject("lodgesByBooking",lodgesByBooking);
		result.addObject("lodgesByRating",lodgesByRating);
		result.addObject("lodgesByPrice",lodgesByPrice);
		result.addObject("occupancyRate",occupancyRate);
		
		return result;
	}
}
