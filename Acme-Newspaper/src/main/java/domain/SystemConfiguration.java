
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class SystemConfiguration extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private Collection<String>	tabooWords;


	@Valid
	@ElementCollection
	public Collection<String> getTabooWords() {
		return this.tabooWords;
	}
	public void setTabooWords(final Collection<String> tabooWords) {
		this.tabooWords = tabooWords;
	}

	// Relationships ----------------------------------------------------------

}
