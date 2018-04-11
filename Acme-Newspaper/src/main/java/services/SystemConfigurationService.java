
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SystemConfigurationRepository;
import utilities.internal.DatabaseUtil;
import domain.Article;
import domain.SystemConfiguration;

@Service
@Transactional
public class SystemConfigurationService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private SystemConfigurationRepository	systemConfigurationRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public SystemConfigurationService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	private SystemConfiguration create() {
		SystemConfiguration result;
		result = new SystemConfiguration();
		List<String> tabooWords;
		tabooWords = new ArrayList<String>();
		result.setTabooWords(tabooWords);
		return result;
	}
	public SystemConfiguration saveTabooWord(final String tabooWord) {
		SystemConfiguration sysConfiguration;
		Collection<SystemConfiguration> configuration;
		configuration = this.findAll();
		if (configuration.isEmpty() || configuration == null)
			sysConfiguration = this.create();
		else
			sysConfiguration = configuration.iterator().next();
		String tabooWordTrim;
		tabooWordTrim = tabooWord.trim();
		this.checkTabooWord(tabooWordTrim);
		Assert.isTrue(!sysConfiguration.getTabooWords().contains(tabooWord), "message.error.systemconfiguration.iscreate");
		sysConfiguration.getTabooWords().add(tabooWord);
		SystemConfiguration sysConfigurationInDB;
		sysConfigurationInDB = this.systemConfigurationRepository.save(sysConfiguration);
		return sysConfigurationInDB;
	}
	public SystemConfiguration editTabooWord(final String oldTabooWord, final String tabooWord) {
		SystemConfiguration sysConfiguration;
		Collection<SystemConfiguration> configuration;
		configuration = this.findAll();
		if (configuration.isEmpty() || configuration == null)
			sysConfiguration = this.create();
		else
			sysConfiguration = configuration.iterator().next();
		String tabooWordTrim;
		tabooWordTrim = tabooWord.trim();
		this.checkTabooWord(tabooWordTrim);
		Assert.isTrue(sysConfiguration.getTabooWords().contains(tabooWord), "message.error.systemconfiguration.isnotcreate");
		List<String> tabooWords;
		tabooWords = new ArrayList<String>(sysConfiguration.getTabooWords());
		tabooWords.set(tabooWords.indexOf(oldTabooWord), tabooWord);
		sysConfiguration.setTabooWords(tabooWords);
		SystemConfiguration sysConfigurationInDB;
		sysConfigurationInDB = this.systemConfigurationRepository.save(sysConfiguration);
		return sysConfigurationInDB;
	}
	public SystemConfiguration deleteTabooWord(final String tabooWord) {
		SystemConfiguration result;
		result = null;
		Collection<SystemConfiguration> configuration;
		configuration = this.findAll();
		if (!(configuration.isEmpty() || configuration == null)) {
			SystemConfiguration sysConfigurationInDB;
			sysConfigurationInDB = configuration.iterator().next();
			sysConfigurationInDB.getTabooWords().remove(tabooWord);
			result = this.systemConfigurationRepository.save(sysConfigurationInDB);
		}
		return result;
	}
	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public SystemConfiguration save(final SystemConfiguration systemConfiguration) {
		Assert.notNull(systemConfiguration);
		SystemConfiguration result;
		result = this.systemConfigurationRepository.save(systemConfiguration);
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
	public Collection<?> search() {

		SystemConfiguration systemConfiguration;
		systemConfiguration = this.findMain();

		final Collection<String> tabooWords = systemConfiguration.getTabooWords();

		final DatabaseUtil databaseUtil = new DatabaseUtil();

		try {
			databaseUtil.initialise();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(databaseUtil.getEntityManager());

		databaseUtil.getEntityManager().getTransaction().begin();

		final QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Article.class).get();

		final org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onFields("title", "summary", "body").matching("student").createQuery();
		final javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Article.class);

		final Collection<?> result = jpaQuery.getResultList();

		databaseUtil.getEntityManager().getTransaction().commit();
		databaseUtil.getEntityManager().close();

		return result;
	}
	//Auxiliars methods
	private void checkTabooWord(final String taboo) {
		final String[] words = taboo.split(" ");
		Assert.isTrue(!(words.length > 1), "message.error.systemconfiguration.multiplewords");
	}
}
