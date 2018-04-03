
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class SystemConfiguration extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	tabooWords;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTabooWords() {
		return this.tabooWords;
	}
	public void setTabooWords(final String tabooWords) {
		this.tabooWords = tabooWords;
	}

	// Relationships ----------------------------------------------------------

}
