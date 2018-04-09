
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.FollowUpService;
import domain.FollowUp;

@Controller
@RequestMapping("/follow-up")
public class FollowUpController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private FollowUpService	followUpService;

	@Autowired
	private ActorService	actorService;


	// Constructors ---------------------------------------------------------

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result = null;
		Collection<FollowUp> followUps = null;
		String requestURI = null;
		String displayURI = null;

		followUps = this.followUpService.findPublicFollowUps();
		requestURI = "follow-up/list.do";
		displayURI = "follow-up/display.do?followUpId=";

		result = new ModelAndView("follow-up/list");
		result.addObject("follow-ups", followUps);
		result.addObject("requestURI", requestURI);
		result.addObject("displayURI", displayURI);

		return result;
	}

	// Creation  ------------------------------------------------------------

	// Display --------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int followUpId) {
		ModelAndView result = null;
		FollowUp followUp = null;

		// Soy un customer, y quiero visualizar un follow-up: solo podré si el periódico es publico
		// o si es privado y estoy subscrito al periódico

		followUp = this.followUpService.findOne(followUpId);

		Assert.notNull(followUp);

		//		try {
		//			actor = this.actorService.findByPrincipal();
		//		} catch (final Throwable oops) {
		//			actor = null;
		//		}
		//
		//		if (actor != null && actor instanceof Customer) {
		//			final int newspaperId = followUp.getArticle().getNewspaper().getId();
		//			final int userId = actor.getId();
		//			Assert.isTrue(this.followUpService.canISeeDisplayThisFollowUp(newspaperId, actor.getId()));
		//		}

		Assert.isTrue(!followUp.getArticle().getNewspaper().getIsPrivate());

		result = new ModelAndView("follow-up/display");
		result.addObject("followup", followUp);
		result.addObject("editURI", "/follow-up/edit.do?followUpId=" + followUpId);
		result.addObject("cancelURI", "/follow-up/list.do");

		return result;
	}

	// Edition    -----------------------------------------------------------

	// Other actions --------------------------------------------------------
}
