package controllers.pilgrim;

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
@RequestMapping("/complaint/pilgrim")
public class ComplaintPilgrimController extends AbstractController{
	@Autowired
	private ComplaintService complaintService;
	@RequestMapping("/list")
	public ModelAndView listAll() {
		ModelAndView result;
		
		Collection<Complaint> complaints;
		complaints=complaintService.findByPilgrim();
		
		result = new ModelAndView("complaint/list");
		result.addObject("complaints",complaints);
		String requestURI="complaint/pilgrim/list.do";
		result.addObject("requestURI", requestURI);
		result.addObject("isPilgrim", true);
		return result;
	}
	
	//	Creation --------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		ComplaintForm complaintForm= new ComplaintForm();
		result = createEditModelAndView(complaintForm);
		result.addObject("create", true);
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ComplaintForm complaintForm, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors())
			result = createEditModelAndView(complaintForm);
		else{
			try{
				Complaint complaint=complaintService.reconstruct(complaintForm);
				complaintService.saveCreate(complaint);
				result = new ModelAndView("redirect:list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(complaintForm, "complaint.commit.error");
			}
		}
		
		return result;
	}
//	Edition ---------------------------------------------------------------
	
	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam int complaintId){
		ModelAndView result;
		Complaint complaint=complaintService.findOne(complaintId);
		try{
			complaintService.cancel(complaint);
			result= new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result= new ModelAndView("redirect:list.do");
		}
		return result;
	}


	
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
