
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String				name;
	private String				surname;
	private Collection<String>	postalAddresses;
	private Collection<String>	phoneNumbers;
	private Collection<String>	emailAddresses;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@Valid
	@NotNull
	@ElementCollection
	public Collection<String> getpostalAddresses() {
		return this.postalAddresses;
	}

	public void setPostalAddresses(final Collection<String> postalAddresses) {
		this.postalAddresses = postalAddresses;
	}

	@Valid
	@NotNull
	@ElementCollection
	public Collection<String> getPhoneNumbers() {
		return this.phoneNumbers;
	}

	public void setPhoneNumbers(final Collection<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	@Valid
	@NotEmpty
	@ElementCollection
	public Collection<String> getEmailAddresses() {
		return this.emailAddresses;
	}

	public void setEmailAddresses(final Collection<String> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}


	// Relationships ----------------------------------------------------------

	private UserAccount	userAccount;


	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
