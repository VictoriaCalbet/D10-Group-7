
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
}
