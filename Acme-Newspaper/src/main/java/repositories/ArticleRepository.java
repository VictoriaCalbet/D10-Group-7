
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

	// Dashboard queries -------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 7.3.2

	@Query("select avg(usr.articles.size) from User usr")
	Double avgArticlesWrittenByWriter();

	@Query("select sqrt(sum(usr.articles.size * usr.articles.size) / count(usr.articles.size) - (avg(usr.articles.size) * avg(usr.articles.size))) from User usr")
	Double stdArticlesWrittenByWriter();

	// Acme-Newspaper 1.0 - Requisito 7.3.3
	@Query("select avg(news.articles.size) from Newspaper news")
	Double avgArticlesPerNewspaper();

	@Query("select sqrt(sum(news.articles.size * news.articles.size) / count(news.articles.size) - (avg(news.articles.size) * avg(news.articles.size))) from Newspaper news")
	Double stdArticlesPerNewspaper();

	// Acme-Newspaper 1.0 - Requisito 24.1.2

	// TODO
	@Query("select count(a) from Article a")
	Double avgNoArticlesPerPrivateNewspapers();

	// Acme-Newspaper 1.0 - Requisito 24.1.3

	// TODO
	@Query("select count(a) from Article a")
	Double avgNoArticlesPerPublicNewspapers();

}
