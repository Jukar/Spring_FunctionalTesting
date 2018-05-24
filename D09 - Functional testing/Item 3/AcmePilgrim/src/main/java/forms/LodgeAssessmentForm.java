package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Embeddable
@Access(AccessType.PROPERTY)
public class LodgeAssessmentForm{

	// Attributes ---------------------------------------------------------

	private Integer bookingId;
	
	private Integer locationRate;
	private Integer roomsRate;
	private Integer serviceRate;
	private Integer priceRate;
	private String comments;
	
	// Getters&Setters ----------------------------------------------------
	
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
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