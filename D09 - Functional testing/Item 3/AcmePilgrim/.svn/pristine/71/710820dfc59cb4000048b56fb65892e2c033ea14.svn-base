package domain;

import java.util.Collection;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Pilgrim extends Customer{

	// Attributes -------------------------------------------------------
	
	// Getters and Setters ----------------------------------------------------
	
	// Relationships ----------------------------------------------------
	
	private Collection<Anecdote> anecdotes;
	public Collection<Register> registers;
	public Collection<Booking> bookings;

	@Valid
	@OneToMany(mappedBy = "pilgrim")
	public Collection<Anecdote> getAnecdotes() {
		return anecdotes;
	}
	public void setAnecdotes(Collection<Anecdote> anecdotes) {
		this.anecdotes = anecdotes;
	}
	@Valid
	@OneToMany(mappedBy = "pilgrim")
	public Collection<Register> getRegisters() {
		return registers;
	}
	public void setRegisters(Collection<Register> registers) {
		this.registers = registers;
	}
	
	@Valid
	@OneToMany(mappedBy = "pilgrim")
	public Collection<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(Collection<Booking> bookings) {
		this.bookings = bookings;
	}
}
