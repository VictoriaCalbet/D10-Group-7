
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
}
