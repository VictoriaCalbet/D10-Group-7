
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Article;
import domain.FollowUp;
import domain.Newspaper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ArticleServiceTest extends AbstractTest {

	// The SUT (Service Under Test) -------------------------------------------

	@Autowired
	private ArticleService		articleService;

	@Autowired
	private NewspaperService	newspaperService;


	// Tests ------------------------------------------------------------------
	/**
	 * Acme-Newspaper : Requirement
	 */
	@Test
	public void testCreateArticleDriver() {

		final Newspaper n1 = this.newspaperService.findOne(this.getEntityId("newspaper1"));

		final Object testingData[][] = {

			/** userPrincipal,private, Date, title,summary,body,exception */
			{	//Crea un artículo para un periódico que es público y no ha sido publicado
				"user2", n1, false, null, "Title of article1", "Summary of article1", "Body of article1", null
			}, {//Crea un artículo para un periódico que es público y ya ha sido publicado
				"user2", n1, false, new Date(System.currentTimeMillis() - 1), "Title of article1", "Summary of article1", "Body of article1", IllegalArgumentException.class
			}, {//Crea un artículo para un periódico que es privado y no ha sido publicado
				"user2", n1, true, null, "Title of article1", "Summary of article1", "Body of article1", null
			}, {//Crea un artículo para un periódico que privado y ya ha sido publicado
				"user2", n1, true, new Date(System.currentTimeMillis() - 1), "Title of article1", "Summary of article1", "Body of article1", IllegalArgumentException.class
			}, {//Crea un artículo para un periódico y no añade título
				"user2", n1, false, new Date(System.currentTimeMillis() - 1), null, "Summary of article1", "Body of article1", IllegalArgumentException.class
			}, {//Crea un artículo para un periódico y no añade resumen
				"user2", n1, false, new Date(System.currentTimeMillis() - 1), "Title of article1", null, "Body of article1", IllegalArgumentException.class
			}, {//Crea un artículo para un periódico y no añade cuerpo
				"user2", n1, false, new Date(System.currentTimeMillis() - 1), "Title of article1", "Summary of article1", null, IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.testCreateArticleTemplate((String) testingData[i][0], (Newspaper) testingData[i][1], (boolean) testingData[i][2], (Date) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(Class<?>) testingData[i][7]);
	}
	protected void testCreateArticleTemplate(final String username, final Newspaper n1, final boolean isPrivate, final Date publicationDate, final String title, final String summary, final String body, final Class<?> expectedException) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(username);

			final Newspaper newspaperToUse = n1;
			newspaperToUse.setIsPrivate(isPrivate);
			newspaperToUse.setPublicationDate(publicationDate);

			this.newspaperService.save(newspaperToUse);
			final Article a = this.articleService.create();
			a.setIsDraft(false);
			a.setNewspaper(newspaperToUse);
			a.setPublicationMoment(publicationDate);
			a.setBody(body);
			a.setSummary(summary);
			a.setTitle(title);
			final Collection<FollowUp> followUps = new ArrayList<FollowUp>();
			a.setFollowUps(followUps);
			final Collection<String> pictures = new ArrayList<String>();
			a.setPictures(pictures);
			a.setPictures(new ArrayList<String>());
			this.articleService.saveFromCreate(a);
			this.unauthenticate();
			this.articleService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}

	@Test
	public void testSaveArticleDriver() {

		final Newspaper n1 = this.newspaperService.findOne(this.getEntityId("newspaper1"));//Publicado
		final Newspaper n4 = this.newspaperService.findOne(this.getEntityId("newspaper4"));//No publicado
		final Article a4 = this.articleService.findOne(this.getEntityId("article4"));
		a4.setIsDraft(true);
		final Article a2 = a4;
		a2.setIsDraft(false);
		final Article a3 = this.articleService.findOne(this.getEntityId("article1"));
		final Object testingData[][] = {

			/** userPrincipal,articulo, periodico, title,summary,body,exception */
			{	//Guarda un artículo draft que pertenece a un periódico no publicado
				"user2", a4, n4, "Title of article1", "Summary of article1", "Body of article1", null
			}, {	//Guarda un artículo de un periódico que ya ha sido publicado
				"user2", a3, n1, "Title of article1", "Summary of article1", "Body of article1", IllegalArgumentException.class
			}, {	//Guarda un artículo de un periódico válido pero que no es suyo
				"user1", a4, n4, "Title of article1", "Summary of article1", "Body of article1", IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.testSaveArticleTemplate((String) testingData[i][0], (Article) testingData[i][1], (Newspaper) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Class<?>) testingData[i][6]);
	}
	protected void testSaveArticleTemplate(final String username, final Article a1, final Newspaper n1, final String title, final String summary, final String body, final Class<?> expectedException) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(username);

			final Newspaper newspaperToUse = n1;

			this.newspaperService.save(newspaperToUse);
			final Article a = a1;
			a.setNewspaper(newspaperToUse);
			a.setBody(body);
			a.setSummary(summary);
			a.setTitle(title);
			this.articleService.saveFromEdit(a);
			this.unauthenticate();
			this.articleService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}

	@Test
	public void testDeleteArticleDriver() {

		final Article a4 = this.articleService.findOne(this.getEntityId("article4"));

		final Object testingData[][] = {

			/** userPrincipal,article,exception */
			{	//El administrador borra un artículo
				"admin", a4, null
			}, {//Un usuario intenta borrar un artículo
				"user2", a4, IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.testDeleteArticleTemplate((String) testingData[i][0], (Article) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	protected void testDeleteArticleTemplate(final String username, final Article a1, final Class<?> expectedException) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(username);
			final Article a = a1;
			this.articleService.delete(a);
			this.unauthenticate();
			this.articleService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			this.unauthenticate();
		}

		this.checkExceptions(expectedException, caught);
	}

	@Test
	public void testFindKeywordArticleDriver() {

		final Object testingData[][] = {

			/** userPrincipal,keyword, encontrados > 0 ,exception */
			{	//El administrador borra un artículo
				"user2", "odkweig3nroimo", false, null
			}, {//Un usuario intenta borrar un artículo
				"user2", "title", true, null
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.testFindKeywordArticleTemplate((String) testingData[i][0], (String) testingData[i][1], (boolean) testingData[i][2], (Class<?>) testingData[i][3]);
	}
	protected void testFindKeywordArticleTemplate(final String username, final String keyword, final boolean encontrados, final Class<?> expectedException) {
		this.authenticate(username);
		Collection<Article> articles = new ArrayList<Article>();
		articles = this.articleService.findArticleByKeyword(keyword);
		Assert.isTrue(encontrados == articles.size() > 0);
		this.unauthenticate();
		this.articleService.flush();

		this.unauthenticate();
	}
}
