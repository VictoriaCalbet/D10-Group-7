
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.LoginService;
import security.UserAccount;
import domain.User;

@Service
@Transactional
public class UserService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private UserRepository	userRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public UserService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public User save(final User user) {
		Assert.notNull(user);
		User result;
		result = this.userRepository.save(user);
		return result;
	}

	public Collection<User> findAll() {
		Collection<User> result = null;
		result = this.userRepository.findAll();
		return result;
	}

	public User findOne(final int userId) {
		User result = null;
		result = this.userRepository.findOne(userId);
		return result;
	}

	// Other business methods -------------------------------------------------

	public User findByPrincipal() {
		User result = null;
		UserAccount userAccount = null;

		userAccount = LoginService.getPrincipal();
		result = this.userRepository.findByUserAccountId(userAccount.getId());

		Assert.notNull(result);

		return result;
	}
}
