
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.FollowUp;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, Integer> {

	// Dashboard queries -------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 17.6.1

	// TODO
	@Query("select avg(art.followUps.size) from Article art")
	Double avgFollowUpsPerArticle();

	// Acme-Newspaper 1.0 - Requisito 17.6.2

	// TODO
	@Query("select count(f) from FollowUp f")
	Double avgNoFollowUpsPerArticleUpToOneWeekAfterTheCorrespondingNewspapersBeenPublished();

	// Acme-Newspaper 1.0 - Requisito 17.6.3

	// TODO
	@Query("select count(f) from FollowUp f")
	Double avgNoFollowUpsPerArticleUpToOneWeeksAfterTheCorrespondingNewspapersBeenPublished();

}
