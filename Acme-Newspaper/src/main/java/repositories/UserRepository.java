
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

	@Query("select count(usr)*1.0/(select count(u) from User u where u.newspapers is empty) from User usr where usr.newspapers is not empty")
	public Double ratioOfUsersWhoHaveEverCreatedANewspaper();

	// Acme-Newspaper 1.0 - Requisito 7.3.7

	@Query("select count(usr)*1.0/(select count(u) from User u where u.articles is not empty) from User usr where usr.articles is not empty")
	public Double ratioOfUsersWhoHaveEverWrittenAnArticle();

	// Acme-Newspaper 1.0 - Requisito 17.6.5

	// TODO
	@Query("select count(u) from User u")
	public Double ratioOfUsersWhoHavePostedAbove75PerCentTheAvgNoOfChirpsPerUser();

}
