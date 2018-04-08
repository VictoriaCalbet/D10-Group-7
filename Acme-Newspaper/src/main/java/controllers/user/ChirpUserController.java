package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ChirpService;
import services.UserService;
import services.forms.ChirpFormService;

import controllers.AbstractController;
import domain.Chirp;
import domain.User;
import domain.forms.ChirpForm;

@Controller
@RequestMapping("/chirp/user")
public class ChirpUserController extends AbstractController{

	@Autowired
	private UserService					userService;
	
	@Autowired
	private ChirpFormService					chirpFormService;
	
	@Autowired
	private ChirpService					chirpService;
	
	public ChirpUserController(){
		
		super();
	}
	
	///List own chirps
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Chirp> chirps = new ArrayList<Chirp>();
		final User u = this.userService.findByPrincipal();
		chirps = u.getChirps();

		result = new ModelAndView("chirp/list");
		result.addObject("chirps", chirps);
		result.addObject("requestURI", "chirp/user/list.do");

		return result;
	}
	
	//List followed's chirps
	
	@RequestMapping(value = "/listFollowedChirps", method = RequestMethod.GET)
	public ModelAndView listFollowerChirps() {
		ModelAndView result;
		Collection<Chirp> chirps = new ArrayList<Chirp>();
		final User u = this.userService.findByPrincipal();
		chirps = this.chirpService.listAllChirpsByFollowedUsers(u.getId());

		result = new ModelAndView("chirp/list");
		result.addObject("chirps", chirps);
		result.addObject("requestURI", "chirp/user/listFollowerChirps.do");

		return result;
	}
	
	//List followed users
	
			@RequestMapping(value = "/listFollowedUsers", method = RequestMethod.GET)
			public ModelAndView listFollowedUsers() {
				ModelAndView result;
				Collection<User> users = new ArrayList<User>();
				final User u = this.userService.findByPrincipal();
				users = u.getFollowed();

				result = new ModelAndView("user/list");
				result.addObject("users", users);
				result.addObject("requestURI", "user/listFollowedUsers.do");

				return result;
			}
			
			//List followed users
			
				@RequestMapping(value = "/listFollowers", method = RequestMethod.GET)
				public ModelAndView listFollowers() {
					ModelAndView result;
					Collection<User> users = new ArrayList<User>();
					final User u = this.userService.findByPrincipal();
					users = u.getFollowers();

					result = new ModelAndView("user/list");
					result.addObject("users", users);
					result.addObject("requestURI", "user/listFollowers.do");

					return result;
				}	

	
	// Creation ----------------------------------------------------------------

		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {
			final ModelAndView result;
			ChirpForm chirpForm;

			chirpForm = this.chirpFormService.create();
			result = this.createModelAndView(chirpForm);
			return result;

		}
		
		@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
		public ModelAndView create(@Valid final ChirpForm chirpForm, final BindingResult binding) {
			//		this.rendezvousFormValidator.validate(rendezvousForm, binding);

			ModelAndView result;

			if (binding.hasErrors())
				result = this.createModelAndView(chirpForm);
			else
				try {
					this.chirpFormService.saveFromCreate(chirpForm);
					result = new ModelAndView("redirect:/chirp/user/list.do");
				} catch (final Throwable oops) {
					String messageError = "chirp.commit.error";
					if (oops.getMessage().contains("message.error"))
						messageError = oops.getMessage();
					result = this.createModelAndView(chirpForm, messageError);
				}

			return result;
		}	
		
		// Ancillaty methods
		protected ModelAndView createModelAndView(final ChirpForm chirpForm) {
			ModelAndView result;

			result = this.createModelAndView(chirpForm, null);

			return result;
		}

		protected ModelAndView createModelAndView(final ChirpForm chirpForm, final String message) {
			ModelAndView result;

			result = new ModelAndView("chirp/edit");
			result.addObject("chirpForm", chirpForm);
			result.addObject("message", message);

			return result;
		}
	
}
