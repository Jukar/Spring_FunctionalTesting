package forms;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;
import domain.Invoice;
import domain.Lodge;
import domain.Money;
import javax.persistence.Column;

@Embeddable
@Access(AccessType.PROPERTY)
public class BookingForm {
	
	// Attributes ----------------------------------------------------------

	private Integer bookingId;
	
	private Date arrivalDate;
	private int numberOfNights;
	private int numberOfBeds;
	private Money pricePerNight;
	private String bookComments;
	private Lodge lodge;
//	private Invoice invoice;
	
	// Getters&Setters ----------------------------------------------------

	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	@Min(1)
	public int getNumberOfNights() {
		return numberOfNights;
	}
	public void setNumberOfNights(Integer numberOfNights) {
		this.numberOfNights = numberOfNights;
	}
	
	@Min(1)
	public int getNumberOfBeds() {
		return numberOfBeds;
	}
	public void setNumberOfBeds(Integer numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}
	
	@Valid
	@AttributeOverrides({		//Creo que no hace falta en formulario
		@AttributeOverride(name="currency",
			column=@Column(name="curr")),
		@AttributeOverride(name="value",
			column=@Column(name="amount"))
	})
	public Money getPricePerNight() {
		return pricePerNight;
	}
	public void setPricePerNight(Money pricePerNight) {
		this.pricePerNight = pricePerNight;
	}
	
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getBookComments() {
		return bookComments;
	}
	public void setBookComments(String bookComments) {
		this.bookComments = bookComments;
	}
	public Lodge getLodge() {
		return lodge;
	}
	public void setLodge(Lodge lodge) {
		this.lodge = lodge;
	}
	
//	@Valid
//	public Invoice getInvoice(){
//		return invoice;
//	}
//	public void setInvoice(Invoice invoice){
//		this.invoice = invoice;
//	}
	
	
}