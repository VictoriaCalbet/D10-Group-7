
package domain.forms;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

public class NewspaperPublishForm {

	// Attributes -------------------------------------------------------------

	private int		newspaperId;
	private Date	publicationDate;


	public int getNewspaperId() {
		return this.newspaperId;
	}

	public void setNewspaperId(final int newspaperId) {
		this.newspaperId = newspaperId;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getPublicationDate() {
		return this.publicationDate;
	}

	public void setPublicationDate(final Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	// Relationships ----------------------------------------------------------

}
