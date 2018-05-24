package controllers.pilgrim;

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
import services.AnecdoteService;
import services.PilgrimService;
import services.RouteService;

import controllers.AbstractController;
import domain.Anecdote;
import domain.Pilgrim;
import domain.Route;
import forms.AnecdoteForm;

@Controller
@RequestMapping("/anecdote/pilgrim")
public class AnecdotePilgrimController extends AbstractController{
	//	Services --------------------------------------------------------------
	@Autowired
	private AnecdoteService anecdoteService;
	@Autowired
	private PilgrimService pilgrimService;
	@Autowired
	private RouteService routeService;
	
	// Constructors -----------------------------------------------------------
	public AnecdotePilgrimController(){
		super();
	}
	
	// Listing -----------------------------------------------------------
	@RequestMapping(value= "/list", method = RequestMethod.GET)
	public ModelAndView listAllByPilgrim(){
		ModelAndView result;
		Collection<Anecdote> anecdotes;
		UserAccount userAccount = LoginService.getPrincipal();
		Pilgrim pilgrim=pilgrimService.findByUserAccount(userAccount);
		
		anecdotes = anecdoteService.findByPilgrimId(pilgrim.getId());
		
		result = new ModelAndView("anecdote/list");
		result.addObject("anecdotes", anecdotes);
		result.addObject("requestURI", "anecdote/pilgrim/list.do");
		
		
		return result;
	}
	
	// Creation -----------------------------------------------------------
	@RequestMapping(value= "/create", method = RequestMethod.GET )
	public ModelAndView create(){
		ModelAndView result; 
		AnecdoteForm anecdoteForm = new AnecdoteForm();
		result = createEditModelAndView(anecdoteForm);
		result.addObject("create", true);
		Collection<Route> routes = routeService.findAll();
		System.out.println(routes);
		result.addObject("routes",routes);
		return result;
	}
	
	@RequestMapping(value= "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid AnecdoteForm anecdoteForm, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(anecdoteForm);
		}else{
			try{
				Anecdote anecdote = anecdoteService.reconstruct(anecdoteForm);
				anecdoteService.save(anecdote);
				result = new ModelAndView("redirect:../list.do");
			}catch (Throwable oops){
				result = createEditModelAndView(anecdoteForm, "anecdote.edit.save.error");
			}
		}
		
		return result;
	}
	
	// Edition -----------------------------------------------------------
	@RequestMapping(value= "/editUpdate", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int anecdoteId){
		ModelAndView result;
		Anecdote anecdote;
		
		anecdote = anecdoteService.findOne(anecdoteId);
		Assert.notNull(anecdote);
		
		result = createEditModelAndView(anecdote);
		
		return result;
	}
	
	@RequestMapping(value= "/editUpdate", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Anecdote anecdote, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(anecdote);
		}else{
			try{
				System.out.println("sdsdfs");
				anecdoteService.save(anecdote);
				System.out.println("sdsdfs");
				result = new ModelAndView("redirect:../list.do");
			}catch (Throwable oops){
				result = createEditModelAndView(anecdote, "anecdote.edit.save.error");
			}
		}
		
		return result;
	}
	
	@RequestMapping(value= "/editUpdate", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Anecdote anecdote, BindingResult binding){
		ModelAndView result;
		
		try{
			anecdoteService.delete(anecdote);
			result = new ModelAndView("redirect:list.do");
		}catch (Throwable oops){
			result = createEditModelAndView(anecdote, "anecdote.edit.delete.error");
		}
		return result;
	}
	
	// Ancillary methods -----------------------------------------------------------
	private ModelAndView createEditModelAndView(AnecdoteForm anecdoteForm){
		ModelAndView result;
		result = createEditModelAndView(anecdoteForm, null);
		return result;
	}
	
	private ModelAndView createEditModelAndView(AnecdoteForm anecdoteForm, String message){
		ModelAndView result;
		
		result = new ModelAndView("anecdote/pilgrim/edit");
		result.addObject("anecdoteForm", anecdoteForm);
		result.addObject("message", message);
		
		return result;
	}
	
	private ModelAndView createEditModelAndView(Anecdote anecdote){
		ModelAndView result;
		result = createEditModelAndView(anecdote, null);
		return result;
	}
	
	private ModelAndView createEditModelAndView(Anecdote anecdote, String message){
		ModelAndView result;
		
		result = new ModelAndView("anecdote/pilgrim/editUpdate");
		result.addObject("anecdote",anecdote);
		result.addObject("message",message);
	
		return result;
	}
}
