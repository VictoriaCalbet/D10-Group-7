
package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import utilities.internal.DatabaseUtil;
import domain.Article;

@Service
@Transactional
public class TabooSearchService {

	public static List<Article> search(final String taboo) {
		List<Article> search;
		search = new ArrayList<Article>();
		DatabaseUtil dbUtil;
		dbUtil = new DatabaseUtil();
		try {
			dbUtil.initialise();
		} catch (final Throwable e) {

			e.printStackTrace();
		}
		FullTextEntityManager ftEntityManger;
		ftEntityManger = Search.getFullTextEntityManager(dbUtil.getEntityManager());
		try {
			TabooSearchService.index(dbUtil, ftEntityManger);
		} catch (final Exception e) {

			e.printStackTrace();
		}
		try {
			search.addAll(TabooSearchService.keywordSearch(taboo, ftEntityManger));
		} catch (final Throwable e) {

			e.printStackTrace();
		}
		return search;

	}

	private static void index(final DatabaseUtil du, final FullTextEntityManager fullTextEntityManager) throws Exception {
		try {
			du.getEntityManager().getTransaction().begin();
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (final Exception e) {
			throw e;
		}

	}

	@SuppressWarnings("unchecked")
	private static List<Article> keywordSearch(final String keywordSearch, final FullTextEntityManager fullTextEntityManager) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		final QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Article.class).get();

		final org.apache.lucene.search.Query query = qb.keyword().onFields("body").matching(keywordSearch).createQuery();

		final Query fullSearchQuery = fullTextEntityManager.createFullTextQuery(query, Article.class);

		final List<Article> result = fullSearchQuery.getResultList();

		return result;

	}

}
