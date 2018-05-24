package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Innkeeper extends Customer{
	
	// Attributes -------------------------------------------------------
	
	// Getters&Setters --------------------------------------------------
	
	// Relationships ----------------------------------------------------
	
	private Collection<Lodge> lodges;

	@Valid
	@OneToMany(mappedBy = "innkeeper")
	public Collection<Lodge> getLodges() {
		return lodges;
	}
	public void setLodges(Collection<Lodge> lodges) {
		this.lodges = lodges;
	}
	
	
}
