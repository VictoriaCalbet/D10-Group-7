
package domain.forms;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
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
import org.springframework.format.annotation.DateTimeFormat;

import cz.jirutka.validator.collection.constraints.EachURL;
import domain.FollowUp;
import domain.Newspaper;
import domain.User;

public class ArticleForm {

	// Attributes -------------------------------------------------------------

	private int						id;
	private String					title;
	private Date					publicationMoment;
	private String					summary;
	private String					body;
	private Collection<String>		pictures;
	private boolean					isDraft;
	private Newspaper				newspaper;
	private User					writer;
	private Collection<FollowUp>	followUps;


	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

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
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
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

	@Valid
	@NotNull
	@ElementCollection
	@EachURL
	public Collection<String> getPictures() {
		return this.pictures;
	}

	public void setPictures(final Collection<String> pictures) {
		this.pictures = pictures;
	}

	public boolean getIsDraft() {
		return this.isDraft;
	}

	public void setIsDraft(final boolean isDraft) {
		this.isDraft = isDraft;
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

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getWriter() {
		return this.writer;
	}
	public void setWriter(final User writer) {
		this.writer = writer;
	}
	@Valid
	@NotNull
	@OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
	public Collection<FollowUp> getFollowUps() {
		return this.followUps;
	}

	public void setFollowUps(final Collection<FollowUp> followUps) {
		this.followUps = followUps;
	}
}
