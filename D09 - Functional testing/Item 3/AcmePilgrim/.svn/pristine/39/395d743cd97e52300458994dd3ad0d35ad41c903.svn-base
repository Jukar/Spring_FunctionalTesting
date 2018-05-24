package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Folder;
import domain.Message;
@Service
@Transactional
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	//	Supporting services ----------------------------------------

	@Autowired
	private FolderService folderService;
	@Autowired
	private ActorService actorService;

	//	Constructor ------------------------------------------------
	
	//	Simple CRUD methods ----------------------------------------
	
	public Message create(){
		Message result = new Message();
		UserAccount senderAccount=LoginService.getPrincipal();
		Actor sender=actorService.findByUserAccount(senderAccount);
		
		Date moment = new Date(System.currentTimeMillis()-1);

		Folder folder = folderService.getOutboxByActorId();
		
		result.setMoment(moment);
		result.setSender(sender);
		result.setFolder(folder);
		return result;
	}


	public Message findOne(int messageId) {
		Message result=messageRepository.findOne(messageId);
		return result;
	}

	public void save(Message message) {
		Date moment=new Date(System.currentTimeMillis()-1);
		message.setMoment(moment);
		messageRepository.save(message);
	}	
	
	public void delete(Message message){
		Assert.notNull(message);
		checkPrincipal(message);
		
		Folder trashbox = message.getFolder();
		
		Assert.isTrue(trashbox.getName().equals("trashbox"));
		messageRepository.delete(message);
		
//		UserAccount userAccount=LoginService.getPrincipal();
//		//Tenemos el pilgrim actual
//		Pilgrim pilgrim = pilgrimService.findByUserAccount(userAccount);
//		Folder trashbox = folderService.getTrashboxByActorId();
//		Collection<Message>messagesTrashbox = trashbox.getMessages();
//		for(Message m : messagesTrashbox){
//			if(m.getId()==message.getId()){
//				System.out.println("Elimina "+ m.getSubject());
//				//AQUI ESTA EL ERROR
//				messagesTrashbox.remove(message);
//				//AQUI TERMINA EL ERROR
//				trashbox.setMessages(messagesTrashbox);
//				folderService.save(trashbox);
//				for(Message me:trashbox.getMessages()){
//					System.out.println("mensajes : ");
//					System.out.println(me.getBody());
//				}
//				//Falta modificar el trashbox del pilgrim
//				messageRepository.delete(message);
//			}
//		}
		
	}
	
	//	Other business methods -------------------------------------
	public Collection<Message> messagesByActor(){
		UserAccount userAccount=LoginService.getPrincipal();
		Actor actor=actorService.findByUserAccount(userAccount);
		Collection<Message> result = messageRepository.messagesByActorId(actor.getId());
		return result;
	}
	
	public Message prepareReply(Message message) {
		Actor recipient=message.getSender();
		Actor sender=message.getRecipient();
		String subject=message.getSubject();

		Message result=create();
		result.setSubject("Re: "+subject);
		result.setSender(sender);
		result.setRecipient(recipient);
		
		Folder folder = folderService.getOutboxByActorId();
		result.setFolder(folder);
		
		return result;
	}
	
	public void sendMessage(Message message){
		Actor recipient=message.getRecipient();
		Message result=create();
		
		result.setBody(message.getBody());
		result.setMoment(message.getMoment());
		result.setSender(message.getSender());
		result.setSubject(message.getSubject());
		result.setRecipient(message.getRecipient());
		result.setUrlAttachment(message.getUrlAttachment());
		
		Folder folder=folderService.getInboxByActorId(recipient.getId());
		result.setFolder(folder);
		
		save(result);
	}
	public void moveMessageToFolder(Message m, Folder to){
		checkPrincipal(m);
		m.setFolder(to);
		to.getMessages().add(m);
		
		messageRepository.save(m);
		folderService.save(to);
	}

	private void checkPrincipal(Message message) {
		UserAccount userAccount=LoginService.getPrincipal();
		Actor principal=actorService.findByUserAccount(userAccount);
		Actor sender=message.getSender();
		Actor recipient=message.getRecipient();
		Assert.isTrue(principal.getId()==sender.getId()||principal.getId()==recipient.getId());
	}
	
	public boolean isAdmin(UserAccount userAccount){
		boolean result=false; 
		Collection<Authority> authorities=userAccount.getAuthorities();
		for(Authority a:authorities){
			if(a.getAuthority().equals("ADMIN")) result=true;
		}
		return result;
	}


	public Collection<Message> getFromInbox() {
		Collection<Message> mA=messagesByActor();
		UserAccount uA=LoginService.getPrincipal();
		Actor aact=actorService.findByUserAccount(uA);
		
		List<Message> result=new ArrayList<Message>();
		for(Message m:mA){
			if(m.getFolder().getName().equals("inbox") && m.getRecipient()==aact){
				result.add(m);
			}
		}
		return result;
	}


	public Collection<Message> getFromOutbox() {
		Collection<Message> mA=messagesByActor();
		UserAccount uA=LoginService.getPrincipal();
		Actor aact=actorService.findByUserAccount(uA);
		
		List<Message> result=new ArrayList<Message>();
		for(Message m:mA){
			if(m.getFolder().getName().equals("outbox") && m.getSender()==aact){
				result.add(m);
			}
		}
		return result;
	}


	public Collection<Message> getFromTrashBox() {
		Collection<Message> mA=messagesByActor();
		List<Message> result=new ArrayList<Message>();
		for(Message m:mA){
			if(m.getFolder().getName().equals("trashbox")){
				result.add(m);
			}
		}
		return result;
	}


}
