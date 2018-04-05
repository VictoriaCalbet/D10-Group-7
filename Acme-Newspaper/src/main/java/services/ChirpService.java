
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ChirpRepository;
import domain.Chirp;

@Service
@Transactional
public class ChirpService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private ChirpRepository	chirpRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public ChirpService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Chirp save(final Chirp chirp) {
		Assert.notNull(chirp);
		Chirp result;
		result = this.chirpRepository.save(chirp);
		return result;
	}

	public Collection<Chirp> findAll() {
		Collection<Chirp> result = null;
		result = this.chirpRepository.findAll();
		return result;
	}

	public Chirp findOne(final int chirpId) {
		Chirp result = null;
		result = this.chirpRepository.findOne(chirpId);
		return result;
	}

	// Other business methods -------------------------------------------------
}
