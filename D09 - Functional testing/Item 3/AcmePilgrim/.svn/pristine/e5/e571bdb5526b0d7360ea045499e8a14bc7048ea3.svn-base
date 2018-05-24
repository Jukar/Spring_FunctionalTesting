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
public class Anecdote extends DomainEntity{

	// Attributes -------------------------------------------------------------
	private String title;
	private String description;
	private Date creationMoment;
	private Date eventMoment;
	
	// Getters&Setters ----------------------------------------------------
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationMoment() {
		return creationMoment;
	}
	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEventMoment() {
		return eventMoment;
	}
	public void setEventMoment(Date eventMoment) {
		this.eventMoment = eventMoment;
	}
	
	// Relationships ----------------------------------------------------
	private Pilgrim pilgrim;
	private Route route;

	@Valid
	@ManyToOne(optional = false)
	public Pilgrim getPilgrim() {
		return pilgrim;
	}
	public void setPilgrim(Pilgrim pilgrim) {
		this.pilgrim = pilgrim;
	}
	@Valid
	@ManyToOne(optional = true)
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
}
