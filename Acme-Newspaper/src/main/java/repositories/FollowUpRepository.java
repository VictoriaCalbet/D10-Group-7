
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.FollowUp;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, Integer> {

	// Dashboard queries -------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 17.6.1

	Double avgFollowUpsPerArticle();

	// Acme-Newspaper 1.0 - Requisito 17.6.2

	Double avgNoFollowUpsPerArticleUpToOneWeekAfterTheCorrespondingNewspapersBeenPublished();

	// Acme-Newspaper 1.0 - Requisito 17.6.3

	Double avgNoFollowUpsPerArticleUpToOneWeeksAfterTheCorrespondingNewspapersBeenPublished();

}
