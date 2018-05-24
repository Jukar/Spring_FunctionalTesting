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
public class Discuss extends DomainEntity{
	
	// Attributes ----------------------------------------------------
	private Date momentDiscuss;
	private String message;
	
	// Getters&Setters ----------------------------------------------------
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMomentDiscuss() {
		return momentDiscuss;
	}
	public void setMomentDiscuss(Date momentDiscuss) {
		this.momentDiscuss = momentDiscuss;
	}
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	// Relationships ----------------------------------------------------
	private Actor actor;
	private Complaint complaint;

	@Valid
	@ManyToOne(optional = false)
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	@Valid
	@ManyToOne(optional = false)
	public Complaint getComplaint() {
		return complaint;
	}
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
}
