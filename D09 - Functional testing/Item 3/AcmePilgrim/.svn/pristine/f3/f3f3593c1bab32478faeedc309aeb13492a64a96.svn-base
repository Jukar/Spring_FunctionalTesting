package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.persistence.CascadeType;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity{

	// Attributes ---------------------------------------------------------
	
	private String name;
	private String surname;
	private String emailAddress;
	private String contactPhone;
	private String url;
	
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	@Email
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	@URL
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	// Relationships ----------------------------------------------------------
	
	private UserAccount userAccount;
	private Collection<Folder> folders;
	private Collection<Landmark> landmarks;

	@Valid
	@OneToOne(optional = false,cascade={CascadeType.ALL})
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	@Valid
	@OneToMany(cascade = CascadeType.ALL )
	//(mappedBy = "actor")
	public Collection<Folder> getFolders() {
		return folders;
	}
	public void setFolders(Collection<Folder> folders) {
		this.folders = folders;
	}
	
	@Valid
	@OneToMany(mappedBy = "actor")
	public Collection<Landmark> getLandmarks() {
		return landmarks;
	}
	public void setLandmarks(Collection<Landmark> landmarks) {
		this.landmarks = landmarks;
	}
}