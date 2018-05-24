package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import domain.GPS;
import domain.Money;
import domain.Stage;

@Embeddable
@Access(AccessType.PROPERTY)
public class LodgeForm {
	
	// Attributes ----------------------------------------------------
	
	private Integer lodgeId;
	
	private String name;
	private String address;
	private GPS coordinates;
	private String webSite;
	private String contactPhone;
	private String lodgeDescription;
	private int numberBeds;
	private Money pricePerNight;
	
	// Getters&Setters ----------------------------------------------------
	
	public Integer getLodgeId() {
		return lodgeId;
	}
	public void setLodgeId(Integer lodgeId) {
		this.lodgeId = lodgeId;
	}
	
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
	
	// Relationships ----------------------------------------------------
	
	private Stage stage;
	
	@Valid
	@ManyToOne(optional = false)
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}

}