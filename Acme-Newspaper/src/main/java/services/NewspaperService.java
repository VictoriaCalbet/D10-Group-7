
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NewspaperRepository;
import domain.Newspaper;

@Service
@Transactional
public class NewspaperService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private NewspaperRepository	newspaperRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public NewspaperService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

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

	// Other business methods -------------------------------------------------

	public Collection<Newspaper> findAvailableNewspapersByCustomerId(final int customerId) {
		Collection<Newspaper> result = null;
		result = this.newspaperRepository.findAvailableNewspapersByCustomerId(customerId);
		return result;
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
