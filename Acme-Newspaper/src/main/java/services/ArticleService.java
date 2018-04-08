
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ArticleRepository;
import domain.Article;
import domain.User;

@Service
@Transactional
public class ArticleService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private ArticleRepository	articleRepository;

	@Autowired
	private UserService			userService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public ArticleService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Article create() {
		final Article result = new Article();
		final User principal = this.userService.findByPrincipal();
		result.setWriter(principal);
		result.setIsDraft(false);
		return result;
	}
	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Article save(final Article article) {
		Assert.notNull(article);
		Article result;
		result = this.articleRepository.save(article);
		return result;
	}

	public Article saveFromCreate(final Article article) {
		Assert.notNull(article);
		Article result = article;
		final User writer = this.userService.findByPrincipal();
		result.setWriter(writer);
		final Collection<Article> principalArticles = writer.getArticles();
		principalArticles.add(result);
		writer.setArticles(principalArticles);
		this.userService.saveFromEdit(writer);
		result = this.articleRepository.save(result);

		return result;
	}
	public Article saveFromEdit(final Article article) {
		Assert.notNull(article);
		final Article result = article;

		this.articleRepository.save(article);
		return result;
	}
	public void delete(final Article article) {

		//		7. An actor who is authenticated as an administrator must be able to:
		//		1. Remove an article that he or she thinks is inappropriate.
		//		2. Remove a newspaper that he or she thinks is inappropriate. Removing a newspaper
		//		implies removing all of the articles of which it is composed.
		//Assert Draft false
		Assert.notNull(article);
		final User principal = this.userService.findByPrincipal();

		final Collection<Article> principalArticles = principal.getArticles();

		principalArticles.remove(article);
		this.userService.saveFromEdit(principal);
		this.articleRepository.delete(article);
	}

	public Collection<Article> findAll() {
		Collection<Article> result = null;
		result = this.articleRepository.findAll();
		return result;
	}

	public Article findOne(final int articleId) {
		Article result = null;
		result = this.articleRepository.findOne(articleId);
		return result;
	}

	// Other business methods -------------------------------------------------

	public Collection<Article> findAllPublishedByUserId(final int userId) {
		Collection<Article> result;

		result = this.articleRepository.findAllPublishedByUserId(userId);

		return result;
	}

	// Dashboard services ------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 7.3.2

	public Double avgArticlesWrittenByWriter() {
		Double result = null;
		result = this.articleRepository.avgArticlesWrittenByWriter();
		return result;
	}

	public Double stdArticlesWrittenByWriter() {
		Double result = null;
		result = this.articleRepository.stdArticlesWrittenByWriter();
		return result;
	}

	// Acme-Newspaper 1.0 - Requisito 7.3.3

	public Double avgArticlesPerNewspaper() {
		Double result = null;
		result = this.articleRepository.avgArticlesPerNewspaper();
		return result;
	}

	public Double stdArticlesPerNewspaper() {
		Double result = null;
		result = this.articleRepository.stdArticlesPerNewspaper();
		return result;
	}

	// Acme-Newspaper 1.0 - Requisito 24.1.2
	public Double avgNoArticlesPerPrivateNewspapers() {
		Double result = null;
		result = this.articleRepository.avgNoArticlesPerPrivateNewspapers();
		return result;
	}

	// Acme-Newspaper 1.0 - Requisito 24.1.3
	public Double avgNoArticlesPerPublicNewspapers() {
		Double result = null;
		result = this.articleRepository.avgNoArticlesPerPublicNewspapers();
		return result;
	}
}
