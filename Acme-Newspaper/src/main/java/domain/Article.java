
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Article extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	title;
	private Date	publicationMoment;
	private String	summary;
	private String	body;
	private String	pictures;
	private Boolean	isDraft;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return this.title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}

	@Valid
	public Date getPublicationMoment() {
		return this.publicationMoment;
	}
	public void setPublicationMoment(final Date publicationMoment) {
		this.publicationMoment = publicationMoment;
	}
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSummary() {
		return this.summary;
	}
	public void setSummary(final String summary) {
		this.summary = summary;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getBody() {
		return this.body;
	}
	public void setBody(final String body) {
		this.body = body;
	}

	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPictures() {
		return this.pictures;
	}

	public void setPictures(final String pictures) {
		this.pictures = pictures;
	}

	public boolean getIsDraft() {
		return this.isDraft;
	}

	public void setIsDraft(final boolean isDraft) {
		this.isDraft = isDraft;
	}


	// Relationships ----------------------------------------------------------
	private User					user;
	private Collection<FollowUp>	followUps;
	private Newspaper				newspaper;


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
	@OneToMany(mappedBy = "article")
	public Collection<FollowUp> getFollowUps() {
		return this.followUps;
	}

	public void setFollowUps(final Collection<FollowUp> followUps) {
		this.followUps = followUps;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Newspaper getNewspaper() {
		return this.newspaper;
	}
	public void setNewspaper(final Newspaper newspaper) {
		this.newspaper = newspaper;
	}
}
