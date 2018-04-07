
package services.forms;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import services.NewspaperService;
import services.UserService;
import domain.Newspaper;
import domain.User;
import domain.forms.NewspaperPublishForm;

@Service
@Transactional
public class NewspaperPublishFormService {

	// Managed repository -----------------------------------------------------

	// Supporting services ----------------------------------------------------

	@Autowired
	private NewspaperService	newspaperService;

	@Autowired
	private UserService			userService;


	// Constructors -----------------------------------------------------------

	public NewspaperPublishFormService() {
		super();
	}
	public NewspaperPublishForm create(final int newspaperId) {
		final Newspaper n = this.newspaperService.findOne(newspaperId);
		final User u = this.userService.findByPrincipal();
		Assert.notNull(n, "message.error.newspaper.null");
		Assert.isTrue(n.getArticles().size() == this.newspaperService.numArticlesFinalOfNewspaper(n.getId()));
		Assert.isTrue(n.getPublisher().equals(u), "message.error.newspaper.user");
		final NewspaperPublishForm nP = new NewspaperPublishForm();
		nP.setNewspaperId(newspaperId);

		return nP;

	}

	public void publishTo(final NewspaperPublishForm nP) {

		Assert.notNull(nP, "message.error.newspaper.null");
		final Newspaper n = this.newspaperService.findOne(nP.getNewspaperId());
		n.setPublicationDate(nP.getPublicationDate());

		Assert.isTrue(n.getArticles().size() == this.newspaperService.numArticlesFinalOfNewspaper(n.getId()));

		this.newspaperService.save(n);

	}

}
