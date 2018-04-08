
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NewspaperRepository;
import domain.Actor;
import domain.Article;
import domain.Customer;
import domain.Newspaper;
import domain.Subscription;
import domain.User;

@Service
@Transactional
public class NewspaperService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private NewspaperRepository	newspaperRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService			userService;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private CustomerService		customerService;


	// Constructors -----------------------------------------------------------

	public NewspaperService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Newspaper create() {

		final Newspaper result = new Newspaper();
		final User u = this.userService.findByPrincipal();

		final Collection<Article> articles = new ArrayList<Article>();
		final Collection<Subscription> subscriptions = new ArrayList<Subscription>();

		result.setArticles(articles);
		result.setPublisher(u);
		result.setSubscriptions(subscriptions);

		return result;
	}

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Newspaper save(final Newspaper newspaper) {
		Assert.notNull(newspaper);
		Newspaper result;
		result = this.newspaperRepository.save(newspaper);
		return result;
	}

	public Collection<Newspaper> findAll() {
		Collection<Newspaper> result = null;
		result = this.newspaperRepository.findAll();
		return result;
	}

	public Newspaper findOne(final int newspaperId) {
		Newspaper result = null;
		result = this.newspaperRepository.findOne(newspaperId);
		return result;
	}

	public void deleteAdmin(final int newspaperId) {
		final Newspaper n = this.newspaperRepository.findOne(newspaperId);
		final Actor actor = this.actorService.findByPrincipal();
		Assert.isTrue(this.actorService.checkAuthority(actor, "ADMIN"));
		Assert.notNull(n, "message.error.newspaper.null");
		final User publisher = n.getPublisher();
		publisher.getNewspapers().remove(n);
		this.userService.save(publisher);

		this.newspaperRepository.delete(n);

	}

	// Other business methods -------------------------------------------------

	public Collection<Newspaper> findAvailableNewspapersByCustomerId(final int customerId) {
		Collection<Newspaper> result = null;
		result = this.newspaperRepository.findAvailableNewspapersByCustomerId(customerId);
		return result;
	}

	public Collection<Newspaper> findPublicated() {
		return this.newspaperRepository.findPublicated();
	}

	public Collection<Newspaper> findNewspaperByKeyWord(final String keyWord) {
		return this.newspaperRepository.findNewspaperByKeyWord(keyWord);
	}

	public Integer numArticlesFinalOfNewspaper(final int newspaperId) {
		return this.newspaperRepository.numArticlesFinalOfNewspaper(newspaperId);

	}

	public Collection<Newspaper> findPublicatedAll() {
		return this.newspaperRepository.findPublicatedAll();
	}

	public Collection<Newspaper> findNewspaperSubscribedOfCustomer() {

		final Customer c = this.customerService.findByPrincipal();
		return this.newspaperRepository.findNewspaperSubscribedOfCustomer(c.getId());

	}

	public Collection<Newspaper> findNewspaperSubscribedOfCustomer(final int customerId) {

		return this.newspaperRepository.findNewspaperSubscribedOfCustomer(customerId);

	}

	// Dashboard services ------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 7.3.1

	public Double avgNewspaperCreatedPerUser() {
		Double result = null;
		result = this.newspaperRepository.avgNewspaperCreatedPerUser();
		return result;
	}

	public Double stdNewspapercreatedPerUser() {
		Double result = null;
		result = this.newspaperRepository.stdNewspapercreatedPerUser();
		return result;
	}

	// Acme-Newspaper 1.0 - Requisito 7.3.4

	public Collection<Newspaper> newspapersThatHaveAtLeast10PerCentMoreArticlesThatTheAvg() {
		Collection<Newspaper> result = null;
		result = this.newspaperRepository.newspapersThatHaveAtLeast10PerCentMoreArticlesThatTheAvg();
		return result;
	}

	// Acme-Newspaper 1.0 - Requisito 7.3.5

	public Collection<Newspaper> newspapersThatHaveAtLeast10PerCentFewerArticlesThatTheAvg() {
		Collection<Newspaper> result = null;
		result = this.newspaperRepository.newspapersThatHaveAtLeast10PerCentFewerArticlesThatTheAvg();
		return result;
	}

	// Acme-Newspaper 1.0 - Requisito 24.1.1

	public Double ratioOfPublicVsPrivateNewspapers() {
		Double result = null;
		result = this.newspaperRepository.ratioOfPublicVsPrivateNewspapers();
		return result;
	}

	// Acme-Newspaper 1.0 - Requisito 24.1.5

	public Double avgRatioOfPrivateVsPublicNewspaperPerPublisher() {
		Double result = null;
		result = this.newspaperRepository.avgRatioOfPrivateVsPublicNewspaperPerPublisher();
		return result;
	}
}
