package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.persistence.AccessType;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Complaint extends DomainEntity{
	// Attributes ----------------------------------------------------
	private String title;
	private Date creationMoment;
	private String description;
	private String resolution;
	private String status;
	
	// Getters&Setters ----------------------------------------------------
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationMoment() {
		return creationMoment;
	}
	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	// Relationships ----------------------------------------------------
	private Administrator administrator;
	private Pilgrim pilgrim;
	private Collection<Discuss> discusses;

	@Valid
	@ManyToOne(optional = true)
	public Administrator getAdministrator() {
		return administrator;
	}
	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
	@Valid
	@ManyToOne(optional = false)
	public Pilgrim getPilgrim() {
		return pilgrim;
	}
	public void setPilgrim(Pilgrim pilgrim) {
		this.pilgrim = pilgrim;
	}
	@Valid
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "complaint")
	public Collection<Discuss> getDiscusses() {
		return discusses;
	}
	public void setDiscusses(Collection<Discuss> discusses) {
		this.discusses = discusses;
	}
	
}
