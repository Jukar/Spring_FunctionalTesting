package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
@Access(AccessType.PROPERTY)
public class LodgeAssessment{

	// Attributes ---------------------------------------------------------
	
	private Date creationMoment;
	private Integer locationRate;
	private Integer roomsRate;
	private Integer serviceRate;
	private Integer priceRate;
	private String comments;
	
	// Getters&Setters ----------------------------------------------------
	
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationMoment() {
		return creationMoment;
	}
	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}
	
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Range(min = 0, max = 10)
	public Integer getLocationRate() {
		return locationRate;
	}
	public void setLocationRate(Integer locationRate) {
		this.locationRate = locationRate;
	}
	
	@Range(min = 0, max = 10)
	public Integer getRoomsRate() {
		return roomsRate;
	}
	public void setRoomsRate(Integer roomsRate) {
		this.roomsRate = roomsRate;
	}
	
	@Range(min = 0, max = 10)
	public Integer getServiceRate() {
		return serviceRate;
	}
	public void setServiceRate(Integer serviceRate) {
		this.serviceRate = serviceRate;
	}
	
	@Range(min = 0, max = 10)
	public Integer getPriceRate() {
		return priceRate;
	}
	public void setPriceRate(Integer priceRate) {
		this.priceRate = priceRate;
	}
}