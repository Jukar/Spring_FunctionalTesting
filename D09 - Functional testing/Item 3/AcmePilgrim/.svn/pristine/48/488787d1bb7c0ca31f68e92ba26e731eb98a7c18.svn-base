package controllers.administrator;

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

import services.RequestService;

import controllers.AbstractController;
import domain.Complaint;
import domain.Request;
import domain.Route;

@Controller
@RequestMapping("/request/administrator")
public class RequestAdministratorController extends AbstractController{
	//Services-----------------------
		@Autowired
		private RequestService requestService;
		//Listing------------------------
		@RequestMapping("/list")
		public ModelAndView listAll() {
			ModelAndView result;
			
			Collection<Request> requests;
			requests=requestService.findAll();
			
			result = new ModelAndView("request/administrator/list");
			result.addObject("requests",requests);
			String requestURI="request/administrator/list.do";
			result.addObject("requestURI", requestURI);
			
			return result;
		}
		// Accept -----------------------------------------------------------
		@RequestMapping(value="/accept", method = RequestMethod.GET)
		public ModelAndView accept(@RequestParam int requestId){
			ModelAndView result;
			Request request;
			
			request = requestService.findOne(requestId);
			Assert.notNull(request);
			try{
				requestService.acceptRequest(request);
				result = new ModelAndView("redirect:list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(request,"request.commit.error");
			}
			
			
			return result;
		}
		// Cancel -----------------------------------------------------------
		@RequestMapping(value="/cancel", method = RequestMethod.GET)
		public ModelAndView cancel(@RequestParam int requestId){
			ModelAndView result;
			Request request;
			
			request = requestService.findOne(requestId);
			Assert.notNull(request);
			try{
				requestService.cancelRequest(request);
				result = new ModelAndView("redirect:list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(request,"request.commit.error");
				System.out.println("ERROR");
			}
			
			
			return result;
		}
		//	Save ------------------------------------------------------------
//		@RequestMapping(value="/edit",method=RequestMethod.POST,params="save")		
//		public ModelAndView save(@Valid Request request, BindingResult binding){
//			ModelAndView result;
//			
//			if(binding.hasErrors()){
//				System.out.println("ENTRA EN ERRORES");
//				result=createEditModelAndView(request);
//			} else{
//				try{
//					System.out.println("ENTRA");
//					requestService.saveAdmin(request);
//					System.out.println("SALE");
//					result=new ModelAndView("redirect:list.do");
//				} catch(Throwable oops){
//					result=createEditModelAndView(request,"request.commit.error");
//				}
//			}
//			return result;
//		}
//		//	Edition ---------------------------------------------------------------
//		@RequestMapping(value = "/edit", method = RequestMethod.GET)
//		public ModelAndView edit(@RequestParam int requestId){
//			ModelAndView result;
//			Request request;
//			
//			request = requestService.findOne(requestId);
//			Assert.notNull(request);
//			result = createEditModelAndView(request);
//			
//			return result;
//		}
//
//		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
//		public ModelAndView delete(Request request, BindingResult binding){
//			ModelAndView result;
//			
//			try{
//				requestService.delete(request);
//				result = new ModelAndView("redirect:list.do");
//			}catch(Throwable oops){
//				result = createEditModelAndView(request, "request.commit.error");
//			}
//			
//			return result;
//		}
		// Ancillary methods
		protected ModelAndView createEditModelAndView(Request request){
			ModelAndView result;
			result=createEditModelAndView(request,null);		
			return result;
		}
		
		protected ModelAndView createEditModelAndView(Request requests, String message){
			ModelAndView result;
					
			result=new ModelAndView("request/administrator/list");
			result.addObject("requests", requests);
			
			return result;
		}
}

