package forms;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

import domain.Route;

@Embeddable
@Access(AccessType.PROPERTY)
public class AnecdoteForm {
	
	@Override
	public String toString() {
		return "AnecdoteForm [anecdoteId=" + anecdoteId + ", title=" + title
				+ ", description=" + description + ", eventMoment="
				+ eventMoment + ", route=" + route + "]";
	}
	private Integer anecdoteId;

	private String title;
	private String description;
	private Date eventMoment;
	private Route route;
	
	public Integer getAnecdoteId() {
		return anecdoteId;
	}
	public void setAnecdoteId(Integer anecdoteId) {
		this.anecdoteId = anecdoteId;
	}
	
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
	
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
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
}