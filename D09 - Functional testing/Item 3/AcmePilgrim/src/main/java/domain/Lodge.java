package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Lodge extends DomainEntity{
	
	// Attributes ----------------------------------------------------
	
	private String name;
	private String address;
	private GPS coordinates;
	private String webSite;
	private String contactPhone;
	private String lodgeDescription;
	private int numberBeds;
	private Money pricePerNight;
	private Boolean published;
	
	// Getters&Setters ----------------------------------------------------
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Valid
	public GPS getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(GPS coordinates) {
		this.coordinates = coordinates;
	}
	
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getLodgeDescription() {
		return lodgeDescription;
	}
	public void setLodgeDescription(String lodgeDescription) {
		this.lodgeDescription = lodgeDescription;
	}
	
	@Min(1)
	public int getNumberBeds() {
		return numberBeds;
	}
	public void setNumberBeds(int numberBeds) {
		this.numberBeds = numberBeds;
	}
	
	@Valid
	public Money getPricePerNight() {
		return pricePerNight;
	}
	public void setPricePerNight(Money pricePerNight) {
		this.pricePerNight = pricePerNight;
	}
	
	public Boolean getPublished() {
		return published;
	}
	public void setPublished(Boolean published) {
		this.published = published;
	}
	
	// Relationships ----------------------------------------------------
	
	private Innkeeper innkeeper;
	private Collection<Booking> bookings;
	private Stage stage;
//	private Request request;
	private Collection<Reservation> reservations;
	
	@Valid
	@ManyToOne(optional = false)
	public Innkeeper getInnkeeper() {
		return innkeeper;
	}
	public void setInnkeeper(Innkeeper innkeeper) {
		this.innkeeper = innkeeper;
	}
	
	@Valid
	@OneToMany(mappedBy = "lodge")
	public Collection<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(Collection<Booking> bookings) {
		this.bookings = bookings;
	}
	
	@Valid
	@ManyToOne(optional = false)
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
//	@Valid
//	@OneToOne(optional = true, mappedBy="lodge")
//	public Request getRequest() {
//		return request;
//	}
//	public void setRequest(Request request) {
//		this.request = request;
//	}
	
	@Valid
	@OneToMany(mappedBy = "lodge")
	public Collection<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(Collection<Reservation> reservations) {
		this.reservations = reservations;
	}
}