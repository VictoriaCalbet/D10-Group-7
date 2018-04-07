
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.userAccount.id = ?1")
	public User findByUserAccountId(int id);

	// Dashboard queries -------------------------------------------------------

	// Acme-Newspaper 1.0 - Requisito 7.3.6

	public Double ratioOfUsersWhoHaveEverCreatedPerNewspaper();

	// Acme-Newspaper 1.0 - Requisito 7.3.7

	public Double ratioOfUsersWhoHaveEverWrittenPerNewspaper();

	// Acme-Newspaper 1.0 - Requisito 17.6.5

	public Double ratioOfUsersWhoHavePostedAbove75PerCentTheAvgNoOfChirpsPerUser();

}
