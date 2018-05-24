package controllers.pilgrim;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Pilgrim;
import forms.PilgrimForm;

import services.PilgrimService;


@Controller
@RequestMapping("/pilgrim")
public class PilgrimRegisterController extends AbstractController{
	//	Services --------------------------------------------------------------
	@Autowired 
	private PilgrimService pilgrimService ;
	// Constructors -----------------------------------------------------------
	public PilgrimRegisterController(){
		super();
	}
	// Action-1 ---------------------------------------------------------------	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		PilgrimForm pilgrimForm;
		pilgrimForm=new PilgrimForm();
		
		result= createEditModelAndView(pilgrimForm);
		
		return result;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST,params="save")
	public ModelAndView save(@Valid PilgrimForm pilgrimForm, BindingResult binding) {
		
		ModelAndView result;
		Pilgrim pilgrim;
		if(binding.hasErrors()){
			result=createEditModelAndView(pilgrimForm);
		}else{
			try{
				pilgrim=pilgrimService.reconstruct(pilgrimForm);
				pilgrimService.save(pilgrim);
				result=new ModelAndView("redirect:../security/login.do");
			}catch (Throwable oops) {
				result= createEditModelAndView(pilgrimForm,"pilgrim.commit.error");
			}
		}
		
		return result;
		
	}
	
	// Ancillary methods
	protected ModelAndView createEditModelAndView(PilgrimForm pilgrimForm){
		ModelAndView result;
		result=createEditModelAndView(pilgrimForm,null);
		return result;
	}
	protected ModelAndView createEditModelAndView(PilgrimForm pilgrimForm, String message){
		ModelAndView result;
		
		result=new ModelAndView("pilgrim/register");
		result.addObject("command",pilgrimForm);
		result.addObject("message",message);
		return result;
	}
}
