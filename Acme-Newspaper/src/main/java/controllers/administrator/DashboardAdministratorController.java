
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import services.ChirpService;
import services.FollowUpService;
import services.NewspaperService;
import services.SubscriptionService;
import services.UserService;
import controllers.AbstractController;
import domain.Newspaper;

@Controller
@RequestMapping("/administrator")
public class DashboardAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private NewspaperService	newspaperService;

	@Autowired
	private ArticleService		articleService;

	@Autowired
	private UserService			userService;

	@Autowired
	private FollowUpService		followUpService;

	@Autowired
	private ChirpService		chirpService;

	@Autowired
	private SubscriptionService	subscriptionService;


	// Constructors ---------------------------------------------------------

	public DashboardAdministratorController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result = null;
		result = new ModelAndView("administrator/dashboard");

		// Acme-Newspaper 1.0

		// Requisito 7.3.1 - OK
		final Double avgNewspaperCreatedPerUser = this.newspaperService.avgNewspaperCreatedPerUser();
		final Double stdNewspaperCreatedPerUser = this.newspaperService.stdNewspapercreatedPerUser();
		result.addObject("avgNewspaperCreatedPerUser", avgNewspaperCreatedPerUser);
		result.addObject("stdNewspaperCreatedPerUser", stdNewspaperCreatedPerUser);

		// Requisito 7.3.2 - OK
		final Double avgArticlesWrittenByWriter = this.articleService.avgArticlesWrittenByWriter();
		final Double stdArticlesWrittenyWriter = this.articleService.stdArticlesWrittenByWriter();
		result.addObject("avgArticlesWrittenByWriter", avgArticlesWrittenByWriter);
		result.addObject("stdArticlesWrittenyWriter", stdArticlesWrittenyWriter);

		// Requisito 7.3.3 - OK
		final Double avgArticlesPerNewspaper = this.articleService.avgArticlesPerNewspaper();
		final Double stdArticlesPerNewspaper = this.articleService.stdArticlesPerNewspaper();
		result.addObject("avgArticlesPerNewspaper", avgArticlesPerNewspaper);
		result.addObject("stdArticlesPerNewspaper", stdArticlesPerNewspaper);

		// Requisito 7.3.4
		final Collection<Newspaper> newspapersThatHaveAtLeast10PerCentMoreArticlesThatTheAvg = this.newspaperService.newspapersThatHaveAtLeast10PerCentMoreArticlesThatTheAvg();
		result.addObject("newspapersThatHaveAtLeast10PerCentMoreArticlesThatTheAvg", newspapersThatHaveAtLeast10PerCentMoreArticlesThatTheAvg);

		// Requisito 7.3.5
		final Collection<Newspaper> newspapersThatHaveAtLeast10PerCentFewerArticlesThatTheAvg = this.newspaperService.newspapersThatHaveAtLeast10PerCentFewerArticlesThatTheAvg();
		result.addObject("newspapersThatHaveAtLeast10PerCentFewerArticlesThatTheAvg", newspapersThatHaveAtLeast10PerCentFewerArticlesThatTheAvg);

		// Requisito 7.3.6
		final Double ratioOfUsersWhoHaveEverCreatedPerNewspaper = this.userService.ratioOfUsersWhoHaveEverCreatedPerNewspaper();
		result.addObject("ratioOfUsersWhoHaveEverCreatedPerNewspaper ", ratioOfUsersWhoHaveEverCreatedPerNewspaper);

		// Requisito 7.3.7
		final Double ratioOfUsersWhoHaveEverWrittenPerNewspaper = this.userService.ratioOfUsersWhoHaveEverWrittenPerNewspaper();
		result.addObject("ratioOfUsersWhoHaveEverWrittenPerNewspaper", ratioOfUsersWhoHaveEverWrittenPerNewspaper);

		// Requisito 17.6.1
		final Double avgFollowUpsPerArticle = this.followUpService.avgFollowUpsPerArticle();
		result.addObject("avgFollowUpsPerArticle", avgFollowUpsPerArticle);

		// Requisito 17.6.2
		final Double avgNoFollowUpsPerArticleUpToOneWeekAfterTheCorrespondingNewspapersBeenPublished = this.followUpService.avgNoFollowUpsPerArticleUpToOneWeekAfterTheCorrespondingNewspapersBeenPublished();
		result.addObject("avgNoFollowUpsPerArticleUpToOneWeekAfterTheCorrespondingNewspapersBeenPublished", avgNoFollowUpsPerArticleUpToOneWeekAfterTheCorrespondingNewspapersBeenPublished);

		// Requisito 17.6.3
		final Double avgNoFollowUpsPerArticleUpToTwoWeeksAfterTheCorrespondingNewspapersBeenPublished = this.followUpService.avgNoFollowUpsPerArticleUpToOneWeeksAfterTheCorrespondingNewspapersBeenPublished();
		result.addObject("avgNoFollowUpsPerArticleUpToTwoWeeksAfterTheCorrespondingNewspapersBeenPublished", avgNoFollowUpsPerArticleUpToTwoWeeksAfterTheCorrespondingNewspapersBeenPublished);

		// Requisito 17.6.4
		final Double avgNoChirpsPerUser = this.chirpService.avgNoChirpsPerUser();
		result.addObject("avgNoChirpsPerUser", avgNoChirpsPerUser);

		// Requisito 17.6.5
		final Double ratioOfUsersWhoHavePostedAbove75PerCentTheAvgNoOfChirpsPerUser = this.userService.ratioOfUsersWhoHavePostedAbove75PerCentTheAvgNoOfChirpsPerUser();
		result.addObject("ratioOfUsersWhoHavePostedAbove75PerCentTheAvgNoOfChirpsPerUser", ratioOfUsersWhoHavePostedAbove75PerCentTheAvgNoOfChirpsPerUser);

		// Requisito 24.1.1
		final Double ratioOfPublicVsPrivateNewspapers = this.newspaperService.ratioOfPublicVsPrivateNewspapers();
		result.addObject("ratioOfPublicVsPrivateNewspapers", ratioOfPublicVsPrivateNewspapers);

		// Requisito 24.1.2
		final Double avgNoArticlesPerPrivateNewspapers = this.articleService.avgNoArticlesPerPrivateNewspapers();
		result.addObject("avgNoArticlesPerPrivateNewspapers", avgNoArticlesPerPrivateNewspapers);

		// Requisito 24.1.3
		final Double avgNoArticlesPerPublicNewspapers = this.articleService.avgNoArticlesPerPublicNewspapers();
		result.addObject("avgNoArticlesPerPublicNewspapers", avgNoArticlesPerPublicNewspapers);

		// Requisito 24.1.4
		final Double ratioOfSubscribersPerPrivateNewspaperVsTotalNumberOfCustomers = this.subscriptionService.ratioOfSubscribersPerPrivateNewspaperVsTotalNumberOfCustomers();
		result.addObject("ratioOfSubscribersPerPrivateNewspaperVsTotalNumberOfCustomers", ratioOfSubscribersPerPrivateNewspaperVsTotalNumberOfCustomers);

		// Requisito 24.1.5
		final Double avgRatioOfPrivateVsPublicNewspaperPerPublisher = this.newspaperService.avgRatioOfPrivateVsPublicNewspaperPerPublisher();
		result.addObject("avgRatioOfPrivateVsPublicNewspaperPerPublisher", avgRatioOfPrivateVsPublicNewspaperPerPublisher);

		result.addObject("requestURI", "administrator/dashboard.do");

		return result;
	}
	// Creation  ------------------------------------------------------------

	// Display --------------------------------------------------------------

	// Edition    -----------------------------------------------------------

	// Other actions --------------------------------------------------------

}
