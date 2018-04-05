
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FollowUpRepository;
import domain.FollowUp;

@Service
@Transactional
public class FollowUpService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private FollowUpRepository	followUpRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public FollowUpService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public FollowUp save(final FollowUp followUp) {
		Assert.notNull(followUp);
		FollowUp result;
		result = this.followUpRepository.save(followUp);
		return result;
	}

	public Collection<FollowUp> findAll() {
		Collection<FollowUp> result = null;
		result = this.followUpRepository.findAll();
		return result;
	}

	public FollowUp findOne(final int followUpId) {
		FollowUp result = null;
		result = this.followUpRepository.findOne(followUpId);
		return result;
	}

	// Other business methods -------------------------------------------------
}
