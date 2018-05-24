package controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.ComplaintService;
import services.DiscussService;
import domain.Actor;
import domain.Complaint;
import domain.Discuss;

@Controller
@RequestMapping("/discuss")
public class DiscussController {
	//	Services --------------------------------------------------------------
	@Autowired
	private DiscussService discussService;
	@Autowired
	private ComplaintService complaintService;
	
	//Create ----------------------------------------
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer complaintId){
		ModelAndView result;
		Discuss discuss;
		Complaint complaint=complaintService.findOne(complaintId);		
		discuss=discussService.create(complaint);
		Assert.notNull(discuss);
		result=createEditModelAndView(discuss);
				
		return result;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST,params="save")		
	public ModelAndView save(@Valid Discuss discuss, BindingResult binding){
		ModelAndView result;
		UserAccount uA=LoginService.getPrincipal();
		
		if(binding.hasErrors()){
			result=createEditModelAndView(discuss);
		} else{
			try{
				discussService.save(discuss);
				if(uA.getAuthorities().iterator().next().getAuthority().equals("ADMIN"))
					result=new ModelAndView("redirect:../complaint/administrator/list.do");
				else
					result=new ModelAndView("redirect:../complaint/pilgrim/list.do");
			} catch(Throwable oops){
				result=createEditModelAndView(discuss,"invoice.commit.error");
			}
		}
		return result;
	}
	
	
	
	// Ancillary methods
	protected ModelAndView createEditModelAndView(Discuss discuss){
		ModelAndView result;
		result=createEditModelAndView(discuss,null);		
		return result;
	}
			
	protected ModelAndView createEditModelAndView(Discuss discuss, String message){
		ModelAndView result;
		Date momentDiscuss;
		Actor actor;
		Complaint complaint;
		
		actor=discuss.getActor();
		complaint=discuss.getComplaint();
		momentDiscuss=discuss.getMomentDiscuss();
						
		discuss.setActor(actor);
		discuss.setComplaint(complaint);
		discuss.setMomentDiscuss(momentDiscuss);
				
		result=new ModelAndView("discuss/edit");
		result.addObject("discuss", discuss);
		result.addObject("complaint",complaint);
		result.addObject("actor",actor);
		
		return result;
	}
}
