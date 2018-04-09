
package services.forms;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import services.ArticleService;
import services.UserService;
import domain.Article;
import domain.User;
import domain.forms.ArticleForm;

@Service
@Transactional
public class ArticleFormService {

	// Managed repository -----------------------------------------------------

	// Supporting services ----------------------------------------------------

	@Autowired
	private ArticleService	articleService;
	@Autowired
	private UserService		userService;


	// Constructors -----------------------------------------------------------

	public ArticleFormService() {
		super();
	}

	public ArticleForm create() {
		ArticleForm result;

		result = new ArticleForm();
		result.setIsDraft(true);
		final User principal = this.userService.findByPrincipal();
		result.setWriter(principal);
		return result;
	}

	public ArticleForm create(final int articleId) {
		final Article a = this.articleService.findOne(articleId);

		final ArticleForm articleForm = new ArticleForm();
		articleForm.setBody(a.getBody());
		articleForm.setPictures(a.getPictures());
		articleForm.setTitle(a.getTitle());
		articleForm.setSummary(a.getSummary());
		articleForm.setFollowUps(a.getFollowUps());
		articleForm.setWriter(a.getWriter());
		articleForm.setPublicationMoment(a.getPublicationMoment());
		articleForm.setId(a.getId());
		articleForm.setIsDraft(a.getIsDraft());

		return articleForm;
	}
	public Article saveFromEdit(final ArticleForm articleForm) {
		Assert.notNull(articleForm);

		final Article a = this.articleService.findOne(articleForm.getId());
		a.setTitle(articleForm.getTitle());
		a.setBody(articleForm.getBody());
		a.setFollowUps(articleForm.getFollowUps());
		a.setId(articleForm.getId());
		a.setIsDraft(articleForm.getIsDraft());
		a.setNewspaper(articleForm.getNewspaper());
		a.setPictures(articleForm.getPictures());
		a.setPublicationMoment(articleForm.getPublicationMoment());
		a.setSummary(articleForm.getSummary());
		a.setWriter(articleForm.getWriter());
		this.articleService.saveFromEdit(a);

		return a;

	}
	public Article saveFromCreate(final ArticleForm articleForm) {

		final Article a = this.articleService.create();

		a.setTitle(articleForm.getTitle());
		a.setPublicationMoment(articleForm.getPublicationMoment());
		a.setId(articleForm.getId());
		a.setSummary(articleForm.getSummary());
		a.setBody(articleForm.getBody());
		a.setPictures(articleForm.getPictures());
		a.setWriter(this.userService.findByPrincipal());
		a.setNewspaper(articleForm.getNewspaper());
		a.setIsDraft(articleForm.getIsDraft());
		a.setFollowUps(articleForm.getFollowUps());
		final Article articleSave = this.articleService.saveFromCreate(a);

		return articleSave;

	}

}
