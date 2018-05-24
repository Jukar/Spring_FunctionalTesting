package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity{

	// Attributes -------------------------------------------------------------
	private Date moment;
	private String subject;
	private String body;
	private String urlAttachment;
	
	// Getters&Setters ----------------------------------------------------
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getUrlAttachment() {
		return urlAttachment;
	}
	public void setUrlAttachment(String urlAttachment) {
		this.urlAttachment = urlAttachment;
	}
	
	// Relationships ----------------------------------------------------------
	private Actor sender;
	private Actor recipient;
	private Folder folder;
	
	@Valid
	@ManyToOne(optional = false)
	public Actor getSender() {
		return sender;
	}
	
	public void setSender(Actor sender) {
		this.sender = sender;
	}
	
	@Valid
	@ManyToOne(optional = false)
	public Actor getRecipient() {
		return recipient;
	}
	
	public void setRecipient(Actor recipient) {
		this.recipient = recipient;
	}
	
	@Valid
	@ManyToOne(optional = false)
	public Folder getFolder() {
		return folder;
	}
	
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
}
