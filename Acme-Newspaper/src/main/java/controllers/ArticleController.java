
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import services.NewspaperService;
import domain.Article;
import domain.Newspaper;

@Controller
@RequestMapping("/article")
public class ArticleController extends AbstractController {

	//Services

	@Autowired
	private NewspaperService	newsPaperService;

	@Autowired
	private ArticleService		articleService;


	//Constructor

	public ArticleController() {
		super();
	}

	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int newspaperId) {
		final ModelAndView result;
		Collection<Article> articles = new ArrayList<Article>();

		final Newspaper newspaper = this.newsPaperService.findOne(newspaperId);
		articles = newspaper.getArticles();
		result = new ModelAndView("article/list");//tiles
		result.addObject("articles", articles);
		result.addObject("requestURI", "article/list.do");
		return result;

	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int articleId) {
		final ModelAndView result;
		final Article article = this.articleService.findOne(articleId);

		result = new ModelAndView("article/user/display");
		result.addObject("article", article);
		result.addObject("requestURI", "article/user/display.do");
		return result;

	}
	//Ancillary methods

}
