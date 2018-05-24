package controllers.innkeeper;


import java.util.Collection;

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
import services.InnkeeperService;
import services.LodgeService;
import services.PilgrimService;
import services.RequestService;

import controllers.AbstractController;
import domain.Innkeeper;
import domain.Lodge;
import domain.Pilgrim;
import domain.Request;
import forms.RequestForm;

@Controller
@RequestMapping("/request/innkeeper")
public class RequestInnkeeperController extends AbstractController{
	//Services-----------------------
	@Autowired
	private RequestService requestService;
	@Autowired
	private LodgeService lodgeService;
	@Autowired
	private InnkeeperService innkeeperService;
	//Listing------------------------
	@RequestMapping("/list")
	public ModelAndView listAll() {
		ModelAndView result;
		
		Collection<Request> requests;
		UserAccount userAccount = LoginService.getPrincipal();
		Innkeeper innkeeper = innkeeperService.findByUserAccount(userAccount);
		requests=requestService.requestByInnkeeper(innkeeper.getId());
		
		result = new ModelAndView("request/innkeeper/list");
		result.addObject("requests",requests);
		String requestURI="request/innkeeper/list.do";
		result.addObject("requestURI", requestURI);
		
		return result;
	}
	// Creation -----------------------------------------------------------
	@RequestMapping(value= "/create", method = RequestMethod.GET )
	public ModelAndView create(){
		ModelAndView result ; 
		RequestForm requestForm = new RequestForm();
		result = createEditModelAndView(requestForm);
		return result;
	}
	//	Save ------------------------------------------------------------
	@RequestMapping(value="/edit",method=RequestMethod.POST,params="save")		
	public ModelAndView save(@Valid RequestForm requestForm, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result=createEditModelAndView(requestForm);
		} else{
			try{
				Request request = requestService.reconstruct(requestForm);
				requestService.save(request);
				result=new ModelAndView("redirect:list.do");
			} catch(Throwable oops){
				System.out.println(oops);
				System.out.println(oops.getMessage());
				result=createEditModelAndView(requestForm,"request.commit.error");
			}
		}
		return result;
	}
	//	Edition ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int requestId){
		ModelAndView result;
		Request request;
		RequestForm requestForm;
		
		request = requestService.findOne(requestId);
		if(request == null){//creamos un request nuevo
			requestForm = new RequestForm();
		}else{//actualizamos el request que hay para un lodge seleccionado
			requestForm = requestService.constructRequestForm(request);
		}
		result = createEditModelAndView(requestForm);
		
		return result;
	}
	// Ancillary methods
	protected ModelAndView createEditModelAndView(RequestForm requestForm){
		ModelAndView result;
		result=createEditModelAndView(requestForm,null);		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(RequestForm requestForm, String message){
		ModelAndView result;
		Collection<Lodge>lodges = lodgeService.lodgesWithoutRequestAssociated();
		if(requestForm.getLodge()!=null){
			lodges.add(requestForm.getLodge());
		}
		
		//actualizo request existentes
		result = new ModelAndView("request/innkeeper/edit");
		result.addObject("lodges",lodges);
		result.addObject("requestForm",requestForm);
		result.addObject("message",message);
		
		return result;
	}
}
