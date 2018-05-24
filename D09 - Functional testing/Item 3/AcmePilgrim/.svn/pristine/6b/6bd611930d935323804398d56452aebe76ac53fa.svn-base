package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Landmark;
import services.LandmarkService;

@Controller
@RequestMapping("/landmark")
public class LandmarkController extends AbstractController {
	//Services-----------------------
	@Autowired
	private LandmarkService landmarkService;
	
	//Listing------------------------
	@RequestMapping("/list")
	public ModelAndView listAll(@RequestParam Integer stageId) {
		ModelAndView result;
		
		Collection<Landmark> landmarks;
		landmarks=landmarkService.findByStage(stageId);
		String requestURI="landmark/list.do";
			
		result = new ModelAndView("landmark/list");
		result.addObject("landmarks",landmarks);
		result.addObject("requestURI", requestURI);
		return result;
	}
}
