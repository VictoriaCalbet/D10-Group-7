
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private AdministratorRepository	administratorRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public AdministratorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Administrator save(final Administrator administrator) {
		Assert.notNull(administrator);
		Administrator result;
		result = this.administratorRepository.save(administrator);
		return result;
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> result = null;
		result = this.administratorRepository.findAll();
		return result;
	}

	public Administrator findOne(final int administratorId) {
		Administrator result = null;
		result = this.administratorRepository.findOne(administratorId);
		return result;
	}

	// Other business methods -------------------------------------------------

	public Administrator findByPrincipal() {
		Administrator result = null;
		UserAccount userAccount = null;

		userAccount = LoginService.getPrincipal();
		result = this.administratorRepository.findByUserAccountId(userAccount.getId());

		Assert.notNull(result);

		return result;
	}
}
