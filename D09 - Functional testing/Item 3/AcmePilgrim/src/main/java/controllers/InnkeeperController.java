package controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import domain.Innkeeper;
import forms.InnkeeperForm;
import services.InnkeeperService;

@Controller
@RequestMapping("/innkeeper")
public class InnkeeperController extends AbstractController{
	
	// ----------------------- Managed service -----------------------
	
	@Autowired 
	private InnkeeperService innkeeperService ;
	
	// ------------------------- Constructor -------------------------
	
	// --------------------------- Profile ---------------------------

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView getProfile(@RequestParam int lodgeId) {
		ModelAndView result;
		
		Innkeeper innkeeper;
		innkeeper=innkeeperService.findByLodgeId(lodgeId);
		Assert.notNull(innkeeper);
		String requestURI="innkeeper/profile.do";
		
		result = new ModelAndView("innkeeper/profile");
		result.addObject("innkeeper",innkeeper);

		result.addObject("requestURI", requestURI);
		return result;
	}
	
	// --------------------------- Register --------------------------
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		
		InnkeeperForm innkeeperForm = new InnkeeperForm();
		result= createEditModelAndView(innkeeperForm);
		
		return result;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST,params="save")
	public ModelAndView save(@Valid InnkeeperForm innkeeperForm, BindingResult binding) {
		
		ModelAndView result;
		Innkeeper innkeeper;
		if(binding.hasErrors()){
			result=createEditModelAndView(innkeeperForm);
		}else{
			try{
				innkeeper=innkeeperService.reconstruct(innkeeperForm);
				System.out.println("SI");
				innkeeperService.save(innkeeper);
				System.out.println("NO");
				result=new ModelAndView("redirect:../security/login.do");
			}catch (Throwable oops) {
				System.out.println(oops.toString());
				result= createEditModelAndView(innkeeperForm,"pilgrim.commit.error");
			}
		}
		
		return result;
	}
	
	// ---------------------- Ancillary methods ----------------------
	
	protected ModelAndView createEditModelAndView(InnkeeperForm innkeeperForm){
		ModelAndView result;
		result=createEditModelAndView(innkeeperForm,null);
		return result;
	}
	
	protected ModelAndView createEditModelAndView(InnkeeperForm innkeeperForm, String message){
		ModelAndView result;
		
		result=new ModelAndView("innkeeper/register");
		result.addObject("innkeeperForm",innkeeperForm);
		result.addObject("message",message);
		return result;
	}
}
