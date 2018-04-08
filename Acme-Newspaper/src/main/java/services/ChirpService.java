
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ChirpRepository;
import domain.Administrator;
import domain.Chirp;
import domain.User;

@Service
@Transactional
public class ChirpService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private ChirpRepository			chirpRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService				userService;

	@Autowired
	private AdministratorService	administratorService;


	// Constructors -----------------------------------------------------------

	public ChirpService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Chirp save(final Chirp chirp) {
		Assert.notNull(chirp,"message.error.chirp.null");
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

	public Chirp create() {

		Chirp result = null;
		result = new Chirp();
		result.setPublicationMoment(new Date(System.currentTimeMillis() - 1));
		return result;
		
	}

	public Chirp saveFromCreate(final Chirp chirp) {

		Assert.notNull(chirp, "message.error.chirp.null");
		Assert.notNull(chirp.getDescription(),"message.error.chirp.description.null");
		Assert.notNull(chirp.getTitle(),"message.error.chirp.title.null");

		final User user = this.userService.findByPrincipal();

		Assert.notNull(user,"message.error.chirp.user");

		chirp.setPublicationMoment(new Date(System.currentTimeMillis() - 1));
		chirp.setUser(user);

		//TODO: check if the title and the description contain taboo words

		final Chirp savedChirp = this.chirpRepository.save(chirp);

		final Collection<Chirp> chirps = user.getChirps();

		chirps.add(savedChirp);

		user.setChirps(chirps);

		this.userService.save(user);

		return savedChirp;

	}

	public void delete(final Chirp c) {

		Assert.notNull(c, "message.error.chirp.null");
		final Administrator admin = this.administratorService.findByPrincipal();
		Assert.notNull(admin, "message.error.chirp.notAnAdmin");

		User user = c.getUser();
		
		Collection<Chirp> chirps = user.getChirps();
		
		chirps.remove(c);
		
		user.setChirps(chirps);
		
		this.userService.save(user);
		
		this.chirpRepository.delete(c);
	}

	// Other business methods -------------------------------------------------

	public Collection<Chirp> listAllChirpsByUser(final int id) {

		final Collection<Chirp> chirps = this.chirpRepository.listAllChirpsByUser(id);

		return chirps;

	}

	public Collection<Chirp> listAllChirpsByFollowedUsers(final int id) {

		final Collection<Chirp> chirps = this.chirpRepository.listAllChirpsByFollowedUsers(id);

		return chirps;

	}

	// Dashboard services ------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 17.6.4

	public Double avgNoChirpsPerUser() {
		Double result = null;
		result = this.chirpRepository.avgNoChirpsPerUser();
		return result;
	}
}
