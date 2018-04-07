
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

	// Dashboard queries -------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 24.1.4

	@Query("select news, news.subscriptions.size*1.0/(select count(cust) from Customer cust) from Newspaper news")
	Collection<Double> ratioOfSubscribersPerPrivateNewspaperVsTotalNumberOfCustomers();

}
