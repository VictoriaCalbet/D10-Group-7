
package controllers.customer;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.NewspaperService;
import controllers.AbstractController;
import domain.Newspaper;

@Controller
@RequestMapping("/newspaper/customer")
public class NewspaperCustomerController extends AbstractController {

	@Autowired
	private NewspaperService	newspaperService;


	public NewspaperCustomerController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final String message) {
		ModelAndView result;
		Collection<Newspaper> newspapers = new ArrayList<Newspaper>();
		newspapers = this.newspaperService.findNewspaperSubscribedOfCustomer();

		result = new ModelAndView("newspaper/list");
		result.addObject("newspapers", newspapers);
		result.addObject("message", message);
		result.addObject("requestURI", "newspaper/user/list.do");

		return result;
	}

}
