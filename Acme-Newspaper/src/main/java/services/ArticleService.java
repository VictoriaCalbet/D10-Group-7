
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ArticleRepository;
import domain.Article;

@Service
@Transactional
public class ArticleService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private ArticleRepository	articleRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public ArticleService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// DO NOT MODIFY. ANY OTHER SAVE METHOD MUST BE NAMED DIFFERENT.
	public Article save(final Article article) {
		Assert.notNull(article);
		Article result;
		result = this.articleRepository.save(article);
		return result;
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
