
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	name;
	private String	surname;
	private String	postalAddresses;
	private String	phoneNumbers;
	private String	emailAddresses;


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

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getpostalAddresses() {
		return this.postalAddresses;
	}

	public void setPostalAddresses(final String postalAddresses) {
		this.postalAddresses = postalAddresses;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getphoneNumbers() {
		return this.phoneNumbers;
	}

	public void setphoneNumbers(final String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	@NotBlank
	@Email
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getEmailAddresses() {
		return this.emailAddresses;
	}

	public void setEmailAddresses(final String emailAddresses) {
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
