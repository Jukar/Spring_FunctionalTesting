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
import javax.validation.constraints.Pattern;
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
	
	public static final String OPEN = "OPEN";
	public static final String CANCELLED = "CANCELLED";
	public static final String CLOSED = "CLOSED";
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
	@Pattern(regexp = "^" + OPEN + "|" + CANCELLED + "|" + "CLOSED" + "$")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	// Relationships ----------------------------------------------------
	private Administrator administrator;
	private Customer author;
	private Customer reffered;
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
	public Customer getAuthor() {
		return author;
	}
	public void setAuthor(Customer customer) {
		this.author = customer;
	}
	@Valid
	@ManyToOne(optional = true)
	public Customer getReffered() {
		return reffered;
	}
	public void setReffered(Customer customer) {
		this.reffered = customer;
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
