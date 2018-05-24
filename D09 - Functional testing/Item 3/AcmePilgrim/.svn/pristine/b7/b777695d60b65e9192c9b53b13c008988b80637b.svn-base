package domain;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Reservation extends DomainEntity{
	
	// Attributes ----------------------------------------------------
	
	private Date dayBooked;
	private int reservedBeds;

	// Getters&Setters -----------------------------------------------
	
	@Column(unique = true)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDayBooked() {
		return dayBooked;
	}
	public void setDayBooked(Date dayBooked) {
		this.dayBooked = dayBooked;
	}

	@Min(0)
	public int getReservedBeds() {
		return reservedBeds;
	}
	
	public void setReservedBeds(int reservedBeds) {
		this.reservedBeds = reservedBeds;
	}
	
	// Relationships ----------------------------------------------------
	
	private Lodge lodge;

	@Valid
	@ManyToOne(optional=false)
	public Lodge getLodge() {
		return lodge;
	}
	public void setLodge(Lodge lodge) {
		this.lodge = lodge;
	}
}