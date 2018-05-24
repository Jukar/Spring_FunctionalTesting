package services;

import java.util.Collection;

import javax.annotation.processing.Messager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import security.LoginService;
import security.UserAccount;
import utilities.AbstractTest;
import domain.Actor;
import domain.Folder;
import domain.Message;
import domain.Pilgrim;
import domain.Register;
import domain.StageRating;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class MessageServiceTest extends AbstractTest{
	
	// ------------------- Supporting services -------------------
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private MessageService messageService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private FolderService folderService;
	// ------------------- Service under test --------------------
//	- A user who is authenticated must be able to:
//		o List the messages in his or her message folders.
// 	Trying with a pilgrim
	@Test
	public void testListMessageFromPilgrim() {
		authenticate("customer0");
		Collection<Message> iMessage=messageService.getFromInbox();
		Assert.isTrue(iMessage.size()==1);
		Collection<Message> oMessage=messageService.getFromOutbox();
		Assert.isTrue(oMessage.size()==0);
		Collection<Message> tMessage=messageService.getFromTrashBox();
		Assert.isTrue(tMessage.size()==0);
		authenticate(null);
	}
//	- A user who is authenticated must be able to:
//	o List the messages in his or her message folders.
//  Trying with an unregistered user
	@Test(expected=IllegalArgumentException.class)
	public void testListMessageUnregistered() {
		authenticate(null);
		Collection<Message> iMessage=messageService.getFromInbox();
		Assert.isTrue(iMessage.size()==1);
		Collection<Message> oMessage=messageService.getFromOutbox();
		Assert.isTrue(oMessage.size()==0);
		Collection<Message> tMessage=messageService.getFromTrashBox();
		Assert.isTrue(tMessage.size()==0);
	}
//	- A user who is authenticated must be able to:
//		o Create and send a new message or reply to a message.
// 	Trying to create with a pilgrim
	@Test
	public void testCreateMessageFromPilgrim() {
		authenticate("customer0");
		Message message=messageService.create();
		Actor recipient=actorService.findOne(45);
		message.setRecipient(recipient);
		message.setSubject("aaaaa");
		message.setBody("aaaaa");
		messageService.save(message);
		messageService.sendMessage(message);
		
		Collection<Message> iMessage=messageService.getFromInbox();
		Assert.isTrue(iMessage.size()==1);
		Folder inboxRec=folderService.findOne(33);
		Assert.isTrue(inboxRec.getMessages().size()==1);
		Collection<Message> oMessage=messageService.getFromOutbox();
		Assert.isTrue(oMessage.size()==1);
		Collection<Message> tMessage=messageService.getFromTrashBox();
		Assert.isTrue(tMessage.size()==0);
		authenticate(null);
	}
//	- A user who is authenticated must be able to:
//	o Create and send a new message or reply to a message.
//	Trying to reply with a pilgrim
	public void testReplyMessageFromPilgrim() {
		authenticate("customer0");
		Message message=messageService.prepareReply(messageService.findOne(29));
		message.setSubject("aaaaa");
		message.setBody("aaaaa");
		messageService.save(message);
		messageService.sendMessage(message);
	
		Collection<Message> iMessage=messageService.getFromInbox();
		Assert.isTrue(iMessage.size()==1);
		Folder inboxRec=folderService.findOne(13);
		Assert.isTrue(inboxRec.getMessages().size()==2);
		Collection<Message> oMessage=messageService.getFromOutbox();
		Assert.isTrue(oMessage.size()==1);
		Collection<Message> tMessage=messageService.getFromTrashBox();
		Assert.isTrue(tMessage.size()==0);
		authenticate(null);
	}
//	- A user who is authenticated must be able to:
//		o Create and send a new message or reply to a message.
// 	Trying to create with an unregistered user
	@Test(expected=IllegalArgumentException.class)
	public void testCreateMessageUnregistered() {
		authenticate(null);
		Message message=messageService.create();
		Actor recipient=actorService.findOne(45);
		message.setRecipient(recipient);
		message.setSubject("aaaaa");
		message.setBody("aaaaa");
		messageService.save(message);
		messageService.sendMessage(message);
		
		Collection<Message> iMessage=messageService.getFromInbox();
		Assert.isTrue(iMessage.size()==1);
		Folder inboxRec=folderService.findOne(33);
		Assert.isTrue(inboxRec.getMessages().size()==1);
		Collection<Message> oMessage=messageService.getFromOutbox();
		Assert.isTrue(oMessage.size()==1);
		Collection<Message> tMessage=messageService.getFromTrashBox();
		Assert.isTrue(tMessage.size()==0);
		authenticate(null);
	}
//	- A user who is authenticated must be able to:
//	o Create and send a new message or reply to a message.
// 	Trying to reply with an unregistered user
	@Test(expected=IllegalArgumentException.class)
	public void testReplyMessageFromUnregistered() {
		authenticate(null);
		Message message=messageService.prepareReply(messageService.findOne(29));
		message.setSubject("aaaaa");
		message.setBody("aaaaa");
		messageService.save(message);
		messageService.sendMessage(message);
	
		Collection<Message> iMessage=messageService.getFromInbox();
		Assert.isTrue(iMessage.size()==1);
		Folder inboxRec=folderService.findOne(13);
		Assert.isTrue(inboxRec.getMessages().size()==2);
		Collection<Message> oMessage=messageService.getFromOutbox();
		Assert.isTrue(oMessage.size()==1);
		Collection<Message> tMessage=messageService.getFromTrashBox();
		Assert.isTrue(tMessage.size()==0);
		authenticate(null);
	}
//	- A user who is authenticated must be able to:
//		o Delete one of his or her messages. The messages that 
//		are deleted from the inbox or the outbox are moved to the 
//		trashbox. 
//		The messages that are deleted from the trashbox are 
//		deleted permanently.	
// 	Trying to deleted with a pilgrim
	@Test
	public void testDeleteMessageFromPilgrim() {
		authenticate("customer0");
//		o Delete one of his or her messages. The messages that 
//		are deleted from the inbox or the outbox are moved to the 
//		trashbox. 
		Message message=messageService.findOne(29);
		Folder trashbox = folderService.getTrashboxByActorId();
		messageService.moveMessageToFolder(message, trashbox);
		messageRepository.flush();
		Collection<Message> iMessage=messageService.getFromInbox();
		Assert.isTrue(iMessage.size()==0);
		Collection<Message> oMessage=messageService.getFromOutbox();
		Assert.isTrue(oMessage.size()==0);
		Collection<Message> tMessage=messageService.getFromTrashBox();
		Assert.isTrue(tMessage.size()==1);
//		The messages that are deleted from the trashbox are 
//		deleted permanently.			
		messageService.delete(message);
		messageRepository.flush();
		iMessage=messageService.getFromInbox();
		Assert.isTrue(iMessage.size()==0);
		oMessage=messageService.getFromOutbox();
		Assert.isTrue(oMessage.size()==0);
		tMessage=messageService.getFromTrashBox();
		Assert.isTrue(tMessage.size()==0);
		
		
		authenticate(null);
	}	
//	- A user who is authenticated must be able to:
//	o Delete one of his or her messages. The messages that 
//	are deleted from the inbox or the outbox are moved to the 
//	trashbox. 
//	The messages that are deleted from the trashbox are 
//	deleted permanently.	
//	Trying to deleted with a pilgrim
	@Test(expected=IllegalArgumentException.class)
	public void testDeleteMessageUnregistered() {
		authenticate(null);
//		o Delete one of his or her messages. The messages that 
//		are deleted from the inbox or the outbox are moved to the 
//		trashbox. 
		Message message=messageService.findOne(29);
		Folder trashbox = folderService.getTrashboxByActorId();
		messageService.moveMessageToFolder(message, trashbox);
		messageRepository.flush();
		Collection<Message> iMessage=messageService.getFromInbox();
		Assert.isTrue(iMessage.size()==0);
		Collection<Message> oMessage=messageService.getFromOutbox();
		Assert.isTrue(oMessage.size()==0);
		Collection<Message> tMessage=messageService.getFromTrashBox();
		Assert.isTrue(tMessage.size()==1);
//		The messages that are deleted from the trashbox are 
//		deleted permanently.			
		messageService.delete(message);
		messageRepository.flush();
		Assert.isTrue(iMessage.size()==0);
		Assert.isTrue(oMessage.size()==0);
		Assert.isTrue(tMessage.size()==0);
	
	
		authenticate(null);
	}	
}
