package controllers;

import java.util.Collection;
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

import services.ActorService;
import services.FolderService;
import services.MessageService;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {
//	Services --------------------------------------------------------------
	@Autowired
	private MessageService messageService;
	@Autowired
	private FolderService folderService;
	@Autowired
	private ActorService actorService;
	
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		
		Collection<Message> messagesInInbox;
		messagesInInbox=messageService.getFromInbox();
		Collection<Message> messagesInOutbox;
		messagesInOutbox=messageService.getFromOutbox();
		Collection<Message> messagesInTrashbox;
		messagesInTrashbox=messageService.getFromTrashBox();
		
		result = new ModelAndView("message/list");
		result.addObject("messagesInInbox",messagesInInbox);
		result.addObject("messagesInOutbox",messagesInOutbox);
		result.addObject("messagesInTrashbox",messagesInTrashbox);
		
		String requestURI="message/list.do";
		result.addObject("requestURI", requestURI);
		
		return result;
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int messageId){
		ModelAndView result=null;
		
		Message message = messageService.findOne(messageId);
//		Folder inbox = folderService.getInboxByActorId();
//		Folder outbox = folderService.getOutboxByActorId();
		Folder trashbox = folderService.getTrashboxByActorId();

		try{
			messageService.moveMessageToFolder(message, trashbox);
			result= new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result= new ModelAndView("redirect:list.do");
		}
		
		
		
		
		return result;
	}
	
	@RequestMapping(value="/deleteOutbox", method = RequestMethod.GET)
	public ModelAndView editOutbox(@RequestParam int messageId){
		ModelAndView result=null;
	

		Message message = messageService.findOne(messageId);
//		Folder outbox = folderService.getOutboxByActorId();
		Folder trashbox = folderService.getTrashboxByActorId();

		
		try{
			messageService.moveMessageToFolder(message, trashbox);
			result= new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result= new ModelAndView("redirect:list.do");
		}
		
		return result;
	}
	
	@RequestMapping(value="/deleteTrashbox", method = RequestMethod.GET)
	public ModelAndView editTrashbox(@RequestParam int messageId){
		ModelAndView result=null;
	
		Message message = messageService.findOne(messageId);
		
		try{
			messageService.delete(message);
			result= new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result= new ModelAndView("redirect:list.do");
		}
		return result;
	}
	
//	@RequestMapping(value="/finalDelete", method = RequestMethod.GET)
//	public ModelAndView delete(@RequestParam int messageId){
//		ModelAndView result=null;
//		return result;
//	}
	
	//Edit ----------------------------------------
	
	@RequestMapping(value="/reply",method=RequestMethod.GET)
	public ModelAndView replyMessage(@RequestParam int messageId){
		
		ModelAndView result;
		Message message;
		
		Message m=messageService.findOne(messageId);
		message=messageService.prepareReply(m);
		
		Assert.notNull(message);
		result=createEditModelAndView(message);
		result.addObject("isReply",true);

		return result;
	}
	
	//Create ----------------------------------------
		@RequestMapping(value="/create",method=RequestMethod.GET)
		public ModelAndView create(){
			ModelAndView result;
			Message message;
			
			message=messageService.create();
			Assert.notNull(message);
			result=createEditModelAndView(message);
			result.addObject("isReply",false);
			
			return result;
		}
		
		// Save--------------------------------------
		
		@RequestMapping(value="/edit",method=RequestMethod.POST,params="save")		
		public ModelAndView save(@Valid Message message, BindingResult binding){
			ModelAndView result;
			
			if(binding.hasErrors()){
				result=createEditModelAndView(message);
			} else{
				try{
					messageService.save(message);
					messageService.sendMessage(message);
					result=new ModelAndView("redirect:list.do");
				} catch(Throwable oops){
					result=createEditModelAndView(message,"invoice.commit.error");
				}
			}
			return result;
		}
		
		// Ancillary methods
		protected ModelAndView createEditModelAndView(Message message){
			ModelAndView result;
			result=createEditModelAndView(message,null);		
			return result;
		}
		
		protected ModelAndView createEditModelAndView(Message messa, String message){
			ModelAndView result;
			Actor sender;
			Folder folder;
			Date moment;
					
			Collection<Actor> recipients=actorService.findAll();
			if(messa.getSender()==null){
				sender=null;
				System.out.println("El sender es nulo");
			}
			else{
				sender=messa.getSender();
				System.out.println("El sender existe, es "+sender.getName());
			}
			
			if(messa.getFolder()==null){
				folder=null;
				System.out.println("El folder es nulo");
			}
			else{
				folder=messa.getFolder();
				System.out.println("El folder existe, es "+folder.getName());
			}
			if(messa.getMoment()==null){
				moment=null;
				System.out.println("El momento es nulo");
			}
			else{
				moment=messa.getMoment();
				System.out.println("El momento existe, es "+moment);
			}
			
			messa.setFolder(folder);
			messa.setMoment(moment);
			messa.setSender(sender);
			
			result=new ModelAndView("message/edit");
			result.addObject("message", messa);
			result.addObject("recipients",recipients);		
			return result;
		}
	
}
