
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {

	// Attributes -------------------------------------------------------------

	// Relationships ----------------------------------------------------------
	private Collection<Newspaper>	newspapers;
	private Collection<FollowUp>	followUps;
	private Collection<Article>		articles;
	private Collection<Chirp>		chirps;


	@Valid
	@NotNull
	@OneToMany(mappedBy = "user")
	public Collection<Newspaper> getNewspapers() {
		return this.newspapers;
	}

	public void setNewspapers(final Collection<Newspaper> newspapers) {
		this.newspapers = newspapers;
	}
	@Valid
	@NotNull
	@OneToMany(mappedBy = "user")
	public Collection<FollowUp> getFollowUps() {
		return this.followUps;
	}

	public void setFollowUps(final Collection<FollowUp> followUps) {
		this.followUps = followUps;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "user")
	public Collection<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(final Collection<Article> articles) {
		this.articles = articles;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "user")
	public Collection<Chirp> getChirps() {
		return this.chirps;
	}

	public void setChirps(final Collection<Chirp> chirps) {
		this.chirps = chirps;
	}
}
