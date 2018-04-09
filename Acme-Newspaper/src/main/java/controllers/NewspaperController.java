
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.NewspaperService;
import domain.Actor;
import domain.Newspaper;

@Controller
@RequestMapping("/newspaper")
public class NewspaperController extends AbstractController {

	@Autowired
	private NewspaperService	newspaperService;

	@Autowired
	private ActorService		actorService;


	public NewspaperController() {
		super();
	}

	//Listing 

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final String message) {
		ModelAndView result;
		Collection<Newspaper> newspapers = new ArrayList<Newspaper>();
		Collection<Newspaper> ns = new ArrayList<Newspaper>();

		try {
			final Actor a = this.actorService.findByPrincipal();
			newspapers = this.newspaperService.findPublicated();
			ns = this.newspaperService.findNewspaperSubscribedOfCustomer(a.getId());
		} catch (final Throwable oops) {
			newspapers = this.newspaperService.findPublicated();
		}

		result = new ModelAndView("newspaper/list");
		result.addObject("newspapers", newspapers);
		result.addObject("ns", ns);
		result.addObject("message", message);
		result.addObject("requestURI", "newspaper/list.do");

		return result;
	}

	// Search by key word

	@RequestMapping(value = "/searchWord", method = RequestMethod.POST)
	public ModelAndView searchByKeyWord(@Valid final String word) {

		ModelAndView result;

		Collection<Newspaper> newspapers;

		newspapers = this.newspaperService.findNewspaperByKeyWord(word);

		result = new ModelAndView("newspaper/list");
		result.addObject("newspapers", newspapers);
		result.addObject("requestURI", "newspaper/list.do");
		return result;

	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView info(@RequestParam final int newspaperId) {
		ModelAndView result = new ModelAndView();

		final Newspaper newspaper = this.newspaperService.findOne(newspaperId);
		result = this.infoModelAndView(newspaper);
		return result;
	}

	// Ancillaty methods
	protected ModelAndView infoModelAndView(final Newspaper newspaper) {
		ModelAndView result;

		result = this.infoModelAndView(newspaper, null);

		return result;
	}

	protected ModelAndView infoModelAndView(final Newspaper newspaper, final String message) {
		ModelAndView result;

		result = new ModelAndView("newspaper/info");

		result.addObject("newspaper", newspaper);
		result.addObject("message", message);

		return result;
	}

}
