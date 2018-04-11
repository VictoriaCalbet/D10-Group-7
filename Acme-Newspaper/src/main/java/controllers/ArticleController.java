
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
import services.ArticleService;
import services.NewspaperService;
import domain.Actor;
import domain.Article;
import domain.Newspaper;
import domain.User;

@Controller
@RequestMapping("/article")
public class ArticleController extends AbstractController {

	//Services

	@Autowired
	private NewspaperService	newsPaperService;

	@Autowired
	private ArticleService		articleService;

	@Autowired
	private ActorService		actorService;


	//Constructor

	public ArticleController() {
		super();
	}

	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int newspaperId) {
		final ModelAndView result;
		Collection<Article> articles = new ArrayList<Article>();
		Collection<Article> principalArticles = new ArrayList<Article>();
		Actor actor = null;
		User principal = null;
		if (this.actorService.checkLogin() == true) {
			actor = this.actorService.findByPrincipal();
			if (this.actorService.checkAuthority(actor, "USER")) {
				principal = (User) actor;
				principalArticles = principal.getArticles();
			}
		}
		final Newspaper newspaper = this.newsPaperService.findOne(newspaperId);
		articles = newspaper.getArticles();
		result = new ModelAndView("article/list");
		result.addObject("articles", articles);
		result.addObject("principalArticles", principalArticles);
		result.addObject("requestURI", "article/list.do");
		return result;

	}
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int articleId) {
		final ModelAndView result;
		final Article article = this.articleService.findOne(articleId);

		result = new ModelAndView("article/user/display");
		result.addObject("article", article);
		result.addObject("requestURI", "article/display.do");
		return result;

	}
	//Ancillary methods

	@RequestMapping(value = "/searchArticleByKeyword", method = RequestMethod.POST)
	public ModelAndView searchByKeyWord(@Valid final String word) {

		ModelAndView result;

		Collection<Article> articles;

		articles = this.articleService.findArticleByKeyword(word);

		result = new ModelAndView("article/list");
		result.addObject("articles", articles);
		result.addObject("requestURI", "article/searchArticleByKeyword.do");
		return result;

	}
}
