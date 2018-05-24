package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Landmark extends DomainEntity{
	
	// Attributes ----------------------------------------------------
	
	private String name;
	private GPS location;
	private String lndDescription;
	private String url;

	// Getters&Setters -----------------------------------------------
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Valid
	public GPS getLocation() {
		return location;
	}
	public void setLocation(GPS location) {
		this.location = location;
	}
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getLndDescription() {
		return lndDescription;
	}
	public void setLndDescription(String lndDescription) {
		this.lndDescription = lndDescription;
	}
	
	@URL
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	// Relationships ----------------------------------------------------
	
	private Stage stage;
	private Actor actor;

	@Valid
	@ManyToOne(optional=false)
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@Valid
	@ManyToOne(optional=false)
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
}