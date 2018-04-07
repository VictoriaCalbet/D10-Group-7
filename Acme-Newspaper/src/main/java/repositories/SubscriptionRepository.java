
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

	// Dashboard queries -------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 24.1.4

	// TODO
	@Query("select count(s) from Subscription s")
	Double ratioOfSubscribersPerPrivateNewspaperVsTotalNumberOfCustomers();

}
