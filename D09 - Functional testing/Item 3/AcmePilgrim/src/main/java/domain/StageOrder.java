package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class StageOrder extends DomainEntity{
	// Attributes ----------------------------------------------------
	
	private Integer number;

	// Getters&Setters -----------------------------------------------
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	// Relationships -------------------------------------------------
	
	private Route route;
	private Stage stage;

	@Valid
	@ManyToOne(optional = false)
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	
	@Valid
	@ManyToOne(optional = false)
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}