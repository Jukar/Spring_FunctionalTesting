package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
@Access(AccessType.PROPERTY)
public class Assessment{

	// Attributes -------------------------------------------------------------
	private Date creationMoment;
	private Integer ratingLenght;
	private Integer ratingDifficulty;
	private String commentAssessment;
	
	// Getters&Setters ----------------------------------------------------
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationMoment() {
		return creationMoment;
	}
	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}
	@Range(min = 0, max = 10)
	public Integer getRatingLenght() {
		return ratingLenght;
	}
	public void setRatingLenght(Integer ratingLenght) {
		this.ratingLenght = ratingLenght;
	}
	@Range(min = 0, max = 10)
	public Integer getRatingDifficulty() {
		return ratingDifficulty;
	}
	public void setRatingDifficulty(Integer ratingDifficulty) {
		this.ratingDifficulty = ratingDifficulty;
	}
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getCommentAssessment() {
		return commentAssessment;
	}
	public void setCommentAssessment(String commentAssessment) {
		this.commentAssessment = commentAssessment;
	}
}
