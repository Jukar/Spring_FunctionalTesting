package controllers.pilgrim;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BookingService;
import services.LodgeService;
import services.PilgrimService;
import services.RegisterService;

import controllers.AbstractController;
import domain.Booking;
import domain.Pilgrim;

@Controller
@RequestMapping("/pilgrim")
public class DashboardPilgrimController extends AbstractController{
	//Services-----------------------
	@Autowired
	private PilgrimService pilgrimService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private RegisterService registerService;
	@Autowired
	private LodgeService lodgeService;
	
	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	public ModelAndView dashboard(){
		ModelAndView result;
		
		Collection<Booking> bookingHistory=bookingService.findByPilgrim();
		Collection<Pilgrim> pilgrimByBirthdayDate=pilgrimService.listPilgrimBirthDateDesc();
		List<Object[]> routesStages=registerService.findByRoutesStageByPilgrim();
		Collection<Object[]> findLodgesBookedAndStagesNotRated=lodgeService.findLodgesBookedAndStagesNotRated();
		
		result=new ModelAndView("pilgrim/dashboard");
		
		result.addObject("bookingHistory",bookingHistory);
		result.addObject("routesStages",routesStages);
		result.addObject("findLodgesBookedAndStagesNotRated",findLodgesBookedAndStagesNotRated);
		result.addObject("pilgrimByBirthdayDate",pilgrimByBirthdayDate);

		result.addObject("requestURI","pilgrim/dashboard");
		
		return result;
	}
}
