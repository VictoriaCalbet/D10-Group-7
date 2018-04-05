
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SubscriptionRepository;
import domain.Subscription;

@Service
@Transactional
public class SubscriptionService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private SubscriptionRepository	subscriptionRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public SubscriptionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Subscription save(final Subscription subscription) {
		Assert.notNull(subscription);
		Subscription result;
		result = this.subscriptionRepository.save(subscription);
		return result;
	}

	public Collection<Subscription> findAll() {
		Collection<Subscription> result = null;
		result = this.subscriptionRepository.findAll();
		return result;
	}

	public Subscription findOne(final int subscriptionId) {
		Subscription result = null;
		result = this.subscriptionRepository.findOne(subscriptionId);
		return result;
	}

	// Other business methods -------------------------------------------------
}
