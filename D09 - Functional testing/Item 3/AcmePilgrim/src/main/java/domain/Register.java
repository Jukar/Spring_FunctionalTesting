package domain;

import java.util.Collection;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Register extends DomainEntity{
	
	// Attributes -------------------------------------------------------------
	
	// Getters&Setters ----------------------------------------------------
	
	// Relationships ----------------------------------------------------
	
	private Pilgrim pilgrim;
	private Route route;
	private Collection<StageRating> stageRatings;
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Pilgrim getPilgrim() {
		return pilgrim;
	}
	public void setPilgrim(Pilgrim pilgrim) {
		this.pilgrim = pilgrim;
	}
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	@Valid
	@OneToMany(mappedBy="register")
	public Collection<StageRating> getStageRatings() {
		return stageRatings;
	}
	public void setStageRatings(Collection<StageRating> stageRatings) {
		this.stageRatings = stageRatings;
	}
}