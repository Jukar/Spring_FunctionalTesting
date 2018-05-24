package controllers;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Innkeeper;
import domain.Lodge;
import services.InnkeeperService;
import services.LodgeService;

@Controller
@RequestMapping("/lodge")
public class LodgeController extends AbstractController {
	
	// ----------------------- Managed service -----------------------
	
	@Autowired
	private LodgeService lodgeService ;
	@Autowired
	private InnkeeperService innkeeperService ;
	
	
	// ------------------------- Constructor -------------------------
	
	// --------------------------- Listing ---------------------------	

	@RequestMapping("/list")
	public ModelAndView listAll() {
		ModelAndView result;
		
		Collection<Lodge> lodges;
		lodges=lodgeService.findAllPublished();
		Assert.notNull(lodges);
		
		String requestURI="lodge/list.do";
		result = new ModelAndView("lodge/list");
		result.addObject("lodges",lodges);
		result.addObject("requestURI", requestURI);
		return result;
	}
	
//	@RequestMapping("/listByLodge")
//	public ModelAndView listAll(@RequestParam int lodgeId) {
//		ModelAndView result;
//		
//		Innkeeper innkeeper;
//		innkeeper=innkeeperService.findByLodgeId(lodgeId);
//		Assert.notNull(innkeeper);
//		
//		String requestURI="lodge/list.do";
//		result = new ModelAndView("lodge/list");
//		result.addObject("innkeeper",innkeeper);
//		result.addObject("requestURI", requestURI);
//		return result;
//	}
}