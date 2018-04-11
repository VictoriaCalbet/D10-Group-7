
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SystemConfigurationRepository;
import domain.Administrator;
import domain.SystemConfiguration;

@Service
@Transactional
public class SystemConfigurationService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private SystemConfigurationRepository	systemConfigurationRepository;

	@Autowired
	private AdministratorService			administratorService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public SystemConfigurationService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public SystemConfiguration create() {
		SystemConfiguration result;

		result = new SystemConfiguration();
		result.setTabooWords(new HashSet<String>());

		return result;
	}

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public SystemConfiguration save(final SystemConfiguration systemConfiguration) {
		Assert.notNull(systemConfiguration);
		SystemConfiguration result;
		result = this.systemConfigurationRepository.save(systemConfiguration);
		return result;
	}

	public SystemConfiguration saveFromCreate(final SystemConfiguration systemConfiguration) {
		Assert.notNull(systemConfiguration);

		final Administrator principal = this.administratorService.findByPrincipal();
		Assert.notNull(principal);

		SystemConfiguration result;

		result = this.save(systemConfiguration);

		return result;
	}

	public SystemConfiguration saveFromEdit(final SystemConfiguration systemConfiguration) {
		Assert.notNull(systemConfiguration);

		final Administrator principal = this.administratorService.findByPrincipal();
		Assert.notNull(principal);

		SystemConfiguration result;

		result = this.save(systemConfiguration);

		return result;
	}

	public Collection<SystemConfiguration> findAll() {
		Collection<SystemConfiguration> result = null;
		result = this.systemConfigurationRepository.findAll();
		return result;
	}

	public SystemConfiguration findOne(final int systemConfigurationId) {
		SystemConfiguration result = null;
		result = this.systemConfigurationRepository.findOne(systemConfigurationId);
		return result;
	}

	public SystemConfiguration findMain() {
		SystemConfiguration result;

		result = this.findAll().iterator().next();

		return result;
	}

	// Other business methods -------------------------------------------------
}
