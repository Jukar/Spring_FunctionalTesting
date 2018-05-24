package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ComplaintService;

import controllers.AbstractController;
import domain.Complaint;

@Controller
@RequestMapping("/complaint/administrator")
public class ComplaintAdministratorController extends AbstractController{

	//Services --------------------------------------------------------------
	@Autowired
	private ComplaintService complaintService;
	
	@RequestMapping("/list")
	public ModelAndView listAll() {
		ModelAndView result;
		
		Collection<Complaint> complaints;
		complaints=complaintService.findByAdmin();
		
		result = new ModelAndView("complaint/list");
		result.addObject("complaints",complaints);
		String requestURI="complaint/administrator/list.do";
		result.addObject("requestURI", requestURI);
		
		return result;
	}
	@RequestMapping("/close")
	public ModelAndView close(@RequestParam int complaintId) {
		ModelAndView result;
		Complaint complaint=complaintService.findOne(complaintId);
		try{
			complaintService.close(complaint);
			result= new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result= new ModelAndView("redirect:list.do");
		}
		return result;
	}
	@RequestMapping("/handle")
	public ModelAndView handle(@RequestParam int complaintId) {
		ModelAndView result;
		Complaint complaint=complaintService.findOne(complaintId);
		try{
			complaintService.handle(complaint);
			result= new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result= new ModelAndView("redirect:list.do");
		}
		return result;
	}
	
}
