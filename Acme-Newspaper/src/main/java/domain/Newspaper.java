
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Newspaper extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	title;
	private Date	publicationDate;
	private String	description;
	private String	picture;
	private Boolean	isPrivate;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return this.title;
	}
	public void setTitle(final String title) {
		this.title = title;
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

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return this.description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}

	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	public boolean getIsPrivate() {
		return this.isPrivate;
	}

	public void setIsPrivate(final boolean isPrivate) {
		this.isPrivate = isPrivate;
	}


	// Relationships ----------------------------------------------------------
	private User						user;
	private Collection<Article>			articles;
	private Collection<Subscription>	subscriptions;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}
	public void setUser(final User user) {
		this.user = user;
	}

	@Valid
	@NotNull
	@ElementCollection
	@OneToMany(mappedBy = "newspaper", cascade = CascadeType.REMOVE)
	public Collection<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(final Collection<Article> articles) {
		this.articles = articles;
	}

	@Valid
	@NotNull
	@ElementCollection
	@OneToMany(mappedBy = "newspaper", cascade = CascadeType.REMOVE)
	public Collection<Subscription> getSubscriptions() {
		return this.subscriptions;
	}

	public void setSubscriptions(final Collection<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
}
