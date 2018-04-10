
package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import utilities.internal.DatabaseUtil;
import domain.Article;

@Service
@Transactional
public class TabooSearchService {

	public static List<Article> search(final List<String> taboos) {
		List<Article> search;
		search = new ArrayList<Article>();
		if (!taboos.isEmpty()) {
			DatabaseUtil dbUtil;
			dbUtil = new DatabaseUtil();
			try {
				dbUtil.initialise();
			} catch (final Throwable e) {

				System.out.println("1");
			}
			FullTextEntityManager ftEntityManger;
			ftEntityManger = Search.getFullTextEntityManager(dbUtil.getEntityManager());
			try {
				TabooSearchService.index(dbUtil, ftEntityManger);
			} catch (final Exception e) {

				System.out.println("2");
			}
			try {
				search.addAll(TabooSearchService.keywordSearch(taboos, ftEntityManger));
			} catch (final Throwable i) {

				System.out.println("3");
				System.out.println(i.getMessage());
			}
		}
		return search;

	}
	private static void index(final DatabaseUtil du, final FullTextEntityManager fullTextEntityManager) throws Exception {
		try {
			du.getEntityManager().getTransaction().begin();
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (final Exception e) {
			System.out.println("4");
		}

	}

	@SuppressWarnings("unchecked")
	private static List<Article> keywordSearch(final List<String> keywordsSearch, final FullTextEntityManager fullTextEntityManager) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Article> result;
		result = new ArrayList<Article>();
		final QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Article.class).get();

		@SuppressWarnings("rawtypes")
		BooleanJunction booleanJuntion = qb.bool().should(qb.keyword().onFields("title", "summary", "body").matching(keywordsSearch.get(0)).createQuery());
		for (int i = 1; i < keywordsSearch.size(); i++)
			booleanJuntion = booleanJuntion.should(qb.keyword().onFields("title", "summary", "body").matching(keywordsSearch.get(i)).createQuery());
		final org.apache.lucene.search.Query query = booleanJuntion.createQuery();

		final Query fullSearchQuery = fullTextEntityManager.createFullTextQuery(query, Article.class);

		result = fullSearchQuery.getResultList();

		return result;

	}

}
