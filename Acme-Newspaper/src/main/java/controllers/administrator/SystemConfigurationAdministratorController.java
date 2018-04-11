
package controllers.administrator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SystemConfigurationService;
import controllers.AbstractController;
import domain.SystemConfiguration;
import domain.forms.TabooWordForm;

@Controller
@RequestMapping("/system-configuration/administrator")
public class SystemConfigurationAdministratorController extends AbstractController {

	//Service
	@Autowired
	SystemConfigurationService	sysConfService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listTabooWords() {
		final ModelAndView result;
		List<String> tabooWords;
		tabooWords = new ArrayList<String>();
		SystemConfiguration sysConf;
		sysConf = this.sysConfService.findMain();
		if (sysConf != null)
			tabooWords.addAll(sysConf.getTabooWords());
		result = new ModelAndView("systemConfiguration/list");
		List<TabooWordForm> listOfTabooForms;
		listOfTabooForms = new ArrayList<TabooWordForm>();
		for (final String word : tabooWords) {
			TabooWordForm tbf;
			tbf = new TabooWordForm();
			tbf.setTabooWord(word);
			listOfTabooForms.add(tbf);
		}
		result.addObject("tabooWords", listOfTabooForms);
		result.addObject("requestURI", "system-configuration/administrator/list.do");

		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createTabooWord() {
		final ModelAndView result;
		TabooWordForm tabooWordForm;
		tabooWordForm = new TabooWordForm();
		tabooWordForm.setTabooWord(" ");
		tabooWordForm.setOldTabooWord("none");
		result = this.createEditModelAndViewForm(tabooWordForm, "system-configuration/administrator/create.do");
		return result;

	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteTabooWord(@RequestParam final String tabooWord) {
		final ModelAndView result;
		result = new ModelAndView("systemConfiguration/list");
		try {
			this.sysConfService.deleteTabooWord(tabooWord);

		} catch (final Throwable oops) {
			String messageError = "systemconfiguration.commit.error";
			if (oops.getMessage().contains("message.error"))
				messageError = oops.getMessage();
			result.addObject("message", messageError);
		}
		List<String> tabooWords;
		tabooWords = new ArrayList<String>();
		SystemConfiguration sysConf;
		sysConf = this.sysConfService.findMain();
		if (sysConf != null)
			tabooWords.addAll(sysConf.getTabooWords());
		List<TabooWordForm> listOfTabooForms;
		listOfTabooForms = new ArrayList<TabooWordForm>();
		for (final String word : tabooWords) {
			TabooWordForm tbf;
			tbf = new TabooWordForm();
			tbf.setTabooWord(word);
			listOfTabooForms.add(tbf);
		}
		result.addObject("tabooWords", listOfTabooForms);
		result.addObject("requestURI", "system-configuration/administrator/list.do");
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editTabooWord(@RequestParam final String tabooWord) {
		final ModelAndView result;
		TabooWordForm tabooWordForm;
		tabooWordForm = new TabooWordForm();
		tabooWordForm.setOldTabooWord(tabooWord);
		tabooWordForm.setTabooWord(tabooWord);
		result = this.createEditModelAndViewForm(tabooWordForm, "system-configuration/administrator/edit.do");
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveFromEdit(@Valid final TabooWordForm tabooWordForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndViewForm(tabooWordForm, "system-configuration/administrator/edit.do");
		else
			try {
				this.sysConfService.editTabooWord(tabooWordForm.getOldTabooWord(), tabooWordForm.getTabooWord());
				result = new ModelAndView("redirect:/system-configuration/administrator/list.do");
			} catch (final Throwable oops) {
				String messageError = "systemconfiguration.commit.error";
				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				result = this.createEditModelAndViewForm(tabooWordForm, "system-configuration/administrator/edit.do", messageError);
			}
		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveFromCreate(@Valid final TabooWordForm tabooWordForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndViewForm(tabooWordForm, "system-configuration/administrator/create.do");
		else
			try {
				this.sysConfService.saveTabooWord(tabooWordForm.getTabooWord());
				result = new ModelAndView("redirect:/system-configuration/administrator/list.do");
			} catch (final Throwable oops) {
				String messageError = "systemconfiguration.commit.error";
				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				result = this.createEditModelAndViewForm(tabooWordForm, "system-configuration/administrator/create.do", messageError);
			}
		return result;
	}

	// Ancillary methods

	public ModelAndView createEditModelAndViewForm(final TabooWordForm tabooWordForm, final String requestURI) {
		ModelAndView result;

		result = this.createEditModelAndViewForm(tabooWordForm, requestURI, null);

		return result;
	}

	public ModelAndView createEditModelAndViewForm(final TabooWordForm tabooWordForm, final String requestURI, final String message) {
		ModelAndView result;

		result = new ModelAndView("systemConfiguration/edit");
		result.addObject("tabooWordForm", tabooWordForm);
		result.addObject("message", message);
		result.addObject("requestURI", requestURI);

		return result;
	}

}