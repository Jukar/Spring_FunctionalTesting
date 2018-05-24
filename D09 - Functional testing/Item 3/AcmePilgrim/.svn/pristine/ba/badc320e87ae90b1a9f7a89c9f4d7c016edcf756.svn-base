package domain;

import java.beans.Transient;
import java.util.Collection;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Route extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String name;
	private String description;
	// private Double ratingL=0.0;
	// private Double ratingD=0.0;
	private Boolean actived;

	// Getters&Setters ----------------------------------------------------

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Transient
	@Range(min = 0, max = 10)
	public Double getRatingL() {
		Double sum = 0.0;
		Double ratingL;
		Integer total = 0;
		if (stageOrders == null) {
			ratingL = 0.0;
		} else {
			total = stageOrders.size();
			if (total == 0) {
				ratingL = 0.0;
			} else {
				for (StageOrder aux : stageOrders) {
					sum = sum + aux.getStage().getRatingL();
				}
				ratingL = sum / total;
			}
		}

		return ratingL;
	}

	public void setRatingL(Double ratingL) {
		// this.ratingL = ratingL;
	}

	@Transient
	@Range(min = 0, max = 10)
	public Double getRatingD() {
		Double sum = 0.0;
		Double ratingD;
		Integer total = 0;
		if (stageOrders == null) {
			ratingD = 0.0;
		} else {
			total = stageOrders.size();
			if (total == 0) {
				ratingD = 0.0;
			} else {
				for (StageOrder aux : stageOrders) {
					sum = sum + aux.getStage().getRatingD();
				}
				ratingD = sum / total;
			}
		}

		return ratingD;
	}

	public void setRatingD(Double ratingD) {
		// this.ratingD = ratingD;
	}

	public Boolean getActived() {
		return actived;
	}

	public void setActived(Boolean actived) {
		this.actived = actived;
	}

	// Relationships ----------------------------------------------------
	private Collection<StageOrder> stageOrders;
	private Administrator administrator;

	@OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
	public Collection<StageOrder> getStageOrders() {
		return stageOrders;
	}

	public void setStageOrders(Collection<StageOrder> stageOrders) {
		this.stageOrders = stageOrders;
	}

	@ManyToOne(optional = false)
	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator =administrator;
	}
}
