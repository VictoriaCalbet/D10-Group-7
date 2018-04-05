
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Customer;

@Service
@Transactional
public class CustomerService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private CustomerRepository	customerRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public CustomerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Customer save(final Customer customer) {
		Assert.notNull(customer);
		Customer result;
		result = this.customerRepository.save(customer);
		return result;
	}

	public Collection<Customer> findAll() {
		Collection<Customer> result = null;
		result = this.customerRepository.findAll();
		return result;
	}

	public Customer findOne(final int customerId) {
		Customer result = null;
		result = this.customerRepository.findOne(customerId);
		return result;
	}

	// Other business methods -------------------------------------------------

	public Customer findByPrincipal() {
		Customer result = null;
		UserAccount userAccount = null;

		userAccount = LoginService.getPrincipal();
		result = this.customerRepository.findByUserAccountId(userAccount.getId());

		Assert.notNull(result);

		return result;
	}
}
