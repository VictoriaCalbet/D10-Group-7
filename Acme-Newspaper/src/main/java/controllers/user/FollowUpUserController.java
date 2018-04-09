
package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import services.FollowUpService;
import services.SubscriptionService;
import services.UserService;
import controllers.AbstractController;
import domain.Article;
import domain.FollowUp;
import domain.User;

@Controller
@RequestMapping("/follow-up/user")
public class FollowUpUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private FollowUpService		followUpService;

	@Autowired
	private UserService			userService;

	@Autowired
	private ArticleService		articleService;

	@Autowired
	private SubscriptionService	subscriptionService;


	// Constructors ---------------------------------------------------------

	public FollowUpUserController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int articleId) {
		ModelAndView result = null;
		Collection<FollowUp> followUps = null;
		Article article = null;
		String requestURI = null;
		String displayURI = null;
		User user = null;

		user = this.userService.findByPrincipal();

		Assert.notNull(user);

		article = this.articleService.findOne(articleId);
		followUps = article.getFollowUps();

		if (article.getNewspaper().getIsPrivate() && article.getNewspaper().getPublicationDate() != null)
			Assert.isTrue(this.subscriptionService.thisCustomerCanSeeThisNewspaper(user.getId(), article.getNewspaper().getId()));

		requestURI = "follow-up/user/list.do";
		displayURI = "follow-up/user/display.do?followUpId=";

		result = new ModelAndView("follow-up/list");
		result.addObject("follow-ups", followUps);
		result.addObject("requestURI", requestURI);
		result.addObject("displayURI", displayURI);

		return result;
	}

	// Creation  ------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result = null;
		FollowUp followUp = null;

		followUp = this.followUpService.create();
		result = this.createEditModelAndView(followUp);

		return result;
	}

	// Display --------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int followUpId) {
		ModelAndView result = null;
		FollowUp followUp = null;

		followUp = this.followUpService.findOne(followUpId);

		Assert.notNull(followUp);

		result = new ModelAndView("follow-up/display");
		result.addObject("followup", followUp);
		result.addObject("editURI", "/follow-up/user/edit.do?followUpId=" + followUpId);
		result.addObject("cancelURI", "/follow-up/user/list.do?articleId=" + followUp.getArticle().getId());

		return result;
	}
	// Edition    -----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int followUpId) {
		ModelAndView result = null;
		FollowUp followUp = null;
		User user = null;

		followUp = this.followUpService.findOne(followUpId);
		user = this.userService.findByPrincipal();

		Assert.isTrue(followUp.getUser().equals(user));

		result = this.createEditModelAndView(followUp);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final FollowUp followUp, final BindingResult bindingResult) {
		ModelAndView result = null;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(followUp);
		else
			try {
				if (followUp.getId() == 0)
					this.followUpService.saveFromCreate(followUp);
				else
					this.followUpService.saveFromEdit(followUp);

				result = new ModelAndView("redirect:/follow-up/user/list.do");

			} catch (final Throwable oops) {
				String messageError = "follow-up.commit.error";
				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				result = this.createEditModelAndView(followUp, messageError);
			}

		return result;
	}

	// Other actions --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final FollowUp followUp) {
		ModelAndView result = null;

		result = this.createEditModelAndView(followUp, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final FollowUp followUp, final String message) {
		ModelAndView result = null;
		String actionURI = null;
		Collection<Article> availableArticles = null;
		User user = null;

		actionURI = "follow-up/user/edit.do";

		user = this.userService.findByPrincipal();

		availableArticles = this.articleService.findAvailableArticlesToCreateFollowUps();

		if (followUp.getId() == 0)
			result = new ModelAndView("follow-up/create");
		else
			result = new ModelAndView("follow-up/edit");

		result.addObject("user", user);
		result.addObject("followUp", followUp);
		result.addObject("actionURI", actionURI);
		result.addObject("availableArticles", availableArticles);
		result.addObject("message", message);

		return result;
	}
}
