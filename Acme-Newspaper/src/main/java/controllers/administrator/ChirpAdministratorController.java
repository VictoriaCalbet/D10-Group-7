package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.ChirpService;
import services.NewspaperService;
import controllers.AbstractController;
import domain.Chirp;
import domain.User;

@Controller
@RequestMapping("/chirp/administrator")
public class ChirpAdministratorController extends AbstractController{
	
	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ChirpService		chirpService;


	public ChirpAdministratorController() {
		super();
	}
	
	   //List chirps in the system
	
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;
			Collection<Chirp> chirps = this.chirpService.findAll();

			result = new ModelAndView("chirp/list");
			result.addObject("chirps", chirps);
			result.addObject("requestURI", "chirp/administrator/list.do");

			return result;
		}
		
		//Delete
		@RequestMapping(value = "/delete", method = RequestMethod.GET)
		public ModelAndView delete(@RequestParam final int chirpId) {
			ModelAndView result;
			try {
				Assert.notNull(this.chirpService.findOne(chirpId),"message.error.chirp.null");
				Chirp chirp = this.chirpService.findOne(chirpId);
				this.chirpService.delete(chirp);
				result = new ModelAndView("redirect:/chirp/administrator/list.do");
			} catch (final Throwable oops) {
				String messageError = "chirp.commit.error";
				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				
				result = new ModelAndView("chirp/list");
				Collection<Chirp> chirps = this.chirpService.findAll();
				result.addObject("chirps",chirps);
				result.addObject("message", messageError);
				result.addObject("requestURI","chirp/administrator/list.do");
			}
			return result;
		}	

}
