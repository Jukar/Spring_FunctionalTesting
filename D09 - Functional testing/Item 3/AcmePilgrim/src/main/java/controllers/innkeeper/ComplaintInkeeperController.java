package controllers.innkeeper;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ComplaintService;
import controllers.AbstractController;
import domain.Complaint;
import forms.ComplaintForm;

@Controller
@RequestMapping("/complaint/inkeeper")
public class ComplaintInkeeperController extends AbstractController{
	@Autowired
	private ComplaintService complaintService;
	
	@RequestMapping("/listRefered")
	public ModelAndView listRefered() {
		ModelAndView result;
		
		Collection<Complaint> complaints;
		complaints=complaintService.findByCustomer();
		
		result = new ModelAndView("complaint/list");
		result.addObject("complaints",complaints);
		String requestURI="complaint/inkeeper/list.do";
		result.addObject("requestURI", requestURI);
		result.addObject("isPilgrim", true);
		return result;
	}
	//	Creation --------------------------------------------------------------

//	Edition ---------------------------------------------------------------
	//	Ancillary methods -----------------------------------------------------
	
	private ModelAndView createEditModelAndView(ComplaintForm complaintForm) {
		ModelAndView result;
		
		result = createEditModelAndView(complaintForm, null);
		
		return result;
	}
	
	
	
	private ModelAndView createEditModelAndView(ComplaintForm complaintForm, String message) {
		ModelAndView result;
		result = new ModelAndView("complaint/pilgrim/edit");
		result.addObject("complaint", complaintForm);
		result.addObject("message", message);
		return result;
	}
}
