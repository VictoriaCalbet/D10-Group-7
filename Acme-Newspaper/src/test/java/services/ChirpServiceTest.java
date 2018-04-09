package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Chirp;
import domain.User;

import utilities.AbstractTest;

@ContextConfiguration(locations = {
		"classpath:spring/junit.xml"
	})
	@RunWith(SpringJUnit4ClassRunner.class)
	@Transactional
public class ChirpServiceTest extends AbstractTest{
	
	// System under test ------------------------------------------------------
		@Autowired
		private ChirpService	chirpService;
		
		//Supporting services
		
		@Autowired
		private UserService	userService;

		/**
		 * Acme-Newspaper Requirement 16.1
		 * An actor who is authenticated as a user must be able to:
		 * Post a chirp. Chirps may not be changed or deleted once they are posted.
		 * 
		 */
		@Test
		public void testSaveFromCreateChirp() {
			// Chirp: title, description, publicationMoment, user, expected exception.
			User user = this.userService.findOne(getEntityId("user1"));
			final Object[][] testingData = {
				{
					"Title", "Description", user, null
				},
				{
					null, "Description", user, IllegalArgumentException.class
				},
				{
					"Title", null, user, IllegalArgumentException.class
				},
				{
					"Title", "Description", user, null
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.testSaveFromCreateChirpTemplate((String) testingData[i][0], (String) testingData[i][1], (User) testingData[i][2], (Class<?>) testingData[i][3]);

		}

		protected void testSaveFromCreateChirpTemplate(final String title, final String description, final User user, final Class<?> expectedException) {

			Class<?> caught = null;

			try {
				
				this.authenticate("user1");
				
				Chirp result = this.chirpService.create();

				result.setTitle(title);
				result.setDescription(description);
				result.setUser(user);

				this.chirpService.saveFromCreate(result);
				this.chirpService.flush();

			} catch (final Throwable oops) {
				caught = oops.getClass();
			} finally {
				this.unauthenticate();
			}

			this.checkExceptions(expectedException, caught);

		}
		
		/**
		 * Acme-Newspaper Requirement 16.2
		 * An actor who is authenticated as a user must be able to:
		 * 
		 * Follow or unfollow another user. This test will be dedicated to following users functionality.
		 * 
		 */
		
		@Test
		public void testFollowUser() {
			// Chirp: User, expected exception.
			User user = this.userService.findOne(getEntityId("user1"));
			User user2 = this.userService.findOne(getEntityId("user2"));
			final Object[][] testingData = {
				{
					user, null
				},
				{
					user2, IllegalArgumentException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.testFollowUser((User) testingData[i][0], (Class<?>) testingData[i][1]);

		}

		protected void testFollowUser(final User user, final Class<?> expectedException) {

			Class<?> caught = null;

			try {
				
				this.authenticate("user2");
				
				this.chirpService.followUser(user.getId());
				this.chirpService.flush();
				
				Assert.isTrue(this.userService.findByPrincipal().getFollowed().contains(user));

			} catch (final Throwable oops) {
				caught = oops.getClass();
			} finally {
				this.unauthenticate();
			}

			this.checkExceptions(expectedException, caught);

		}
		
		/**
		 * Acme-Newspaper Requirement 16.2
		 * An actor who is authenticated as a user must be able to:
		 * 
		 * Follow or unfollow another user. This test will be dedicated to unfollowing users functionality.
		 * 
		 */
		
		@Test
		public void testUnfollowUser() {
			// Chirp: User, expected exception.
			User user = this.userService.findOne(getEntityId("user1"));
			User user2 = this.userService.findOne(getEntityId("user2"));
			final Object[][] testingData = {
				{
					user2, null
				},
				{
					user, null
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.testUnfollowUser((User) testingData[i][0], (Class<?>) testingData[i][1]);

		}

		protected void testUnfollowUser(final User user, final Class<?> expectedException) {

			Class<?> caught = null;

			try {
				
				this.authenticate("user1");
				
				this.chirpService.unfollowUser(user.getId());
				this.chirpService.flush();
				Assert.isTrue(!this.userService.findByPrincipal().getFollowed().contains(user));

			} catch (final Throwable oops) {
				caught = oops.getClass();
			} finally {
				this.unauthenticate();
			}

			this.checkExceptions(expectedException, caught);

		}
		
		/**
		 * Acme-Newspaper Requirement 17.5
		 * An actor who is authenticated as an administrator must be able to:
		 * 
		 * Remove a chirp that he or she thinks is inappropriate.
		 * 
		 */
		
		@Test
		public void testDeleteChirp() {
			// Chirp: Chirp, expected exception.
			Chirp chirp = this.chirpService.findOne(this.getEntityId("chirp1"));
			final Object[][] testingData = {
				{
					chirp, null
				},
				{
					null, IllegalArgumentException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.testDeleteChirp((Chirp) testingData[i][0], (Class<?>) testingData[i][1]);

		}

		protected void testDeleteChirp(final Chirp chirp, final Class<?> expectedException) {

			Class<?> caught = null;

			try {
				
				this.authenticate("admin");
				
				this.chirpService.delete(chirp);
				this.chirpService.flush();

			} catch (final Throwable oops) {
				caught = oops.getClass();
			} finally {
				this.unauthenticate();
			}

			this.checkExceptions(expectedException, caught);

		}
		
}
