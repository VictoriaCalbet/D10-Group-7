
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Newspaper;

@Repository
public interface NewspaperRepository extends JpaRepository<Newspaper, Integer> {

	@Query("select news from Newspaper news where news.isPrivate is true and news.publicationDate is not null and news.id not in (select subs.newspaper.id from Subscription subs where subs.customer.id = ?1)")
	Collection<Newspaper> findAvailableNewspapersByCustomerId(int customerId);

	// Dashboard queries -------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 7.3.1

	@Query("select n from Newspaper n where n.publicationDate < CURRENT_TIMESTAMP")
	Collection<Newspaper> findPublishedNewspapers();

	@Query("select avg(usr.newspapers.size) from User usr")
	Double avgNewspaperCreatedPerUser();

	@Query("select sqrt(sum(usr.newspapers.size * usr.newspapers.size) / count(usr.newspapers.size) - (avg(usr.newspapers.size) * avg(usr.newspapers.size))) from User usr")
	Double stdNewspapercreatedPerUser();

	// Acme-Newspaper 1.0 - Requisito 7.3.4

	// TODO
	@Query("select n from Newspaper n")
	Collection<Newspaper> newspapersThatHaveAtLeast10PerCentMoreArticlesThatTheAvg();

	// Acme-Newspaper 1.0 - Requisito 7.3.5

	// TODO
	@Query("select n from Newspaper n")
	Collection<Newspaper> newspapersThatHaveAtLeast10PerCentFewerArticlesThatTheAvg();

	// Acme-Newspaper 1.0 - Requisito 24.1.1

	// TODO
	@Query("select count(n) from Newspaper n")
	Double ratioOfPublicVsPrivateNewspapers();

	// Acme-Newspaper 1.0 - Requisito 24.1.5

	// TODO
	@Query("select count(n) from Newspaper n")
	Double avgRatioOfPrivateVsPublicNewspaperPerPublisher();

}
