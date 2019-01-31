package com.am.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.am.configuration.DatabaseConfiguration;
import com.am.exception.DeleteReferenceException;
import com.am.model.ExternalUser;
import com.am.model.Role;
import com.am.service.user.ExternalUserService;
import com.am.service.user.RoleService;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { DatabaseConfiguration.class })
public class ExternalUserServiceTest extends TestCase {
	@Autowired
	private ExternalUserService externalUserService;

	@Autowired
	private RoleService roleService;

	@Before
	@After
	public void setup() {
		clear();
	}

	private void clear() {

		externalUserService.deleteAll();
		roleService.deleteAll();

	}

	@Test
	public void saveOrUpdateUserTest() {

		Role role = new Role();

		role.setRole("superadmin");

		Role role2 = new Role();

		role2.setRole("sadmin");

		roleService.saveOrUpdate(role);
		roleService.saveOrUpdate(role2);

		ExternalUser user1 = new ExternalUser();
		user1.setFirstName("Amany");
		user1.setLastName("Ali");
		user1.setPassword("123456");
		user1.setUsername("Amany Ali");
		user1.setEmail("amanyyyyyy@gmsssail.com");
		user1.setPassword("12345");

		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		roles.add(role2);
		user1.setRoles(roles);
		externalUserService.saveOrUpdate(user1);

		ExternalUser loadUser = externalUserService.findById(user1.getId()); // load
		assertEquals(user1, loadUser);

		// Test update

		ExternalUser updatedUser = loadUser;
		updatedUser.setFirstName("AMANY"); // update
		updatedUser.setLastName("Ali");
		updatedUser.setPassword("123456");
		updatedUser.setUsername("Amany.Ali");
		updatedUser.setEmail("mooony@mioilsss.com");
		externalUserService.saveOrUpdate(updatedUser);

		ExternalUser loaded = externalUserService.findById(user1.getId()); // load
		assertEquals(updatedUser, loaded); // assert

	}

	@Test
	public void addDuplicateUserName() {

		Role role = new Role();
		role.setRole("superadmin");
		roleService.saveOrUpdate(role);

		String username = "linda";

		ExternalUser user1 = new ExternalUser();
		user1.setFirstName("Lina");
		user1.setLastName("Mahmoud");
		user1.setPassword("02020");
		user1.setUsername(username);
		user1.setEmail("linaa@mioilsss.com");
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		user1.setRoles(roles);
		externalUserService.saveOrUpdate(user1); // add

		ExternalUser loaded = externalUserService.findById(user1.getId()); // load
		assertEquals(user1, loaded); // assert

		ExternalUser user2 = new ExternalUser();
		user2.setFirstName("Amany"); // update
		user2.setLastName("Ali");
		user2.setPassword("123456");
		user2.setUsername(username);
		user2.setEmail("Amany.Ali@mioil.com");
		try {
			externalUserService.saveOrUpdate(user2);
			fail();
		} catch (Exception e) {

		}

	}

	@Test
	public void findById() {

		Role role = new Role();
		role.setRole("superadmin");
		roleService.saveOrUpdate(role);

		ExternalUser user1 = new ExternalUser();
		user1.setFirstName("Marwa");
		user1.setLastName("Amin");
		user1.setPassword("02020");
		user1.setUsername("Marwa Amin");
		user1.setEmail("Marwa.s.moneer@mioil.com");
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		user1.setRoles(roles);
		externalUserService.saveOrUpdate(user1); // add
		ExternalUser findUser = externalUserService.findById(user1.getId()); // load
		assertEquals(user1, findUser); // assert

	}

	@Test
	public void findByTest() {

		Role role = new Role();
		role.setRole("superadmin");
		roleService.saveOrUpdate(role);
		String firstName1 = "Marwa";
		String firstName2 = "Ayten";
		String firstName3 = "Hader";

		String userName1 = "Marwa.aa";
		String userName2 = "Ayten.ss";
		String userName3 = "Hader.dd";

		String mail1 = "Marwa.aa@mail.com";
		String mail2 = "Ayten.ss@mail.com";
		String mail3 = "Hader.dd@mail.com";

		ExternalUser user11 = new ExternalUser();
		user11.setFirstName(firstName1);
		user11.setUsername(userName1);
		user11.setEmail(mail1);

		ExternalUser user12 = new ExternalUser();
		user12.setFirstName(firstName2);
		user12.setUsername(userName2);
		user12.setEmail(mail2);

		ExternalUser user13 = new ExternalUser();
		user13.setFirstName(firstName3);
		user13.setUsername(userName3);
		user13.setEmail(mail3);

		List<Role> roles = new ArrayList<Role>();// declare new array list
		roles.add(role);
		user11.setRoles(roles);
		user12.setRoles(roles);
		user13.setRoles(roles);
		externalUserService.saveOrUpdate(user11); // add
		externalUserService.saveOrUpdate(user12); // add
		externalUserService.saveOrUpdate(user13); // add
		List<ExternalUser> searchResult = externalUserService.findBy(ExternalUser.FIRST_NAME_PROPERTY, firstName1); // load
		assertTrue(searchResult.size() == 1); // check if data is uploaded or
												// not

		assertEquals(firstName1, searchResult.get(0).getFirstName());

	}

	// @Test
	public void deleteUserTest() {
		ExternalUser user1 = new ExternalUser();
		user1.setFirstName("Marwa");
		user1.setLastName("Amin");
		user1.setPassword("02020");
		user1.setUsername("Marwa Amin");
		user1.setEmail("Marwa.s.moneer@mioil.com");

		externalUserService.saveOrUpdate(user1); // add
		ExternalUser loadedUser = externalUserService.findById(user1.getId());
		assertNotNull(loadedUser);
		try {
			externalUserService.delete(loadedUser);
		} catch (DeleteReferenceException e) {
			fail();
		}
		ExternalUser deletedUser = externalUserService.findById(user1.getId()); // load
		assertNotNull(deletedUser); // check if user delete or not
		assertTrue(deletedUser.getLocked());
		assertTrue(deletedUser.getDeleted());
	}

	@Test
	public void deleteAll() {
		Role role = new Role();
		role.setRole("superadmin");
		roleService.saveOrUpdate(role);

		String firstName1 = "Marwa";
		String firstName2 = "Ayten";
		String firstName3 = "Hader";

		String userName1 = "Marwa.aa";
		String userName2 = "Ayten.ss";
		String userName3 = "Hader.dd";

		String mail1 = "Marwa.aa@mail.com";
		String mail2 = "Ayten.ss@mail.com";
		String mail3 = "Hader.dd@mail.com";

		ExternalUser user11 = new ExternalUser();
		user11.setFirstName(firstName1);
		user11.setUsername(userName1);
		user11.setEmail(mail1);

		ExternalUser user12 = new ExternalUser();
		user12.setFirstName(firstName2);
		user12.setUsername(userName2);
		user12.setEmail(mail2);

		ExternalUser user13 = new ExternalUser();
		user13.setFirstName(firstName3);
		user13.setUsername(userName3);
		user13.setEmail(mail3);

		List<Role> roles = new ArrayList<Role>();// declare new array list
		roles.add(role);

		user11.setRoles(roles);
		user12.setRoles(roles);
		user13.setRoles(roles);

		externalUserService.saveOrUpdate(user11); // add
		externalUserService.saveOrUpdate(user12); // add
		externalUserService.saveOrUpdate(user13); // add
		List<ExternalUser> searchResult = externalUserService.getAll(); // load
		assertEquals(3, searchResult.size());
		externalUserService.deleteAll();
		searchResult = externalUserService.getAll(); // load
		assertEquals(0, searchResult.size());// check if users deleteAll or not
	}

	@Test
	public void findByAndCriteria() {

		Role role = new Role();
		role.setRole("superadmin");
		roleService.saveOrUpdate(role);

		String firstName1 = "manal";
		String firstName2 = "sara";

		String lastName1 = "Ali";
		String lastName2 = "Amgad";

		String userName1 = "Marwa.aa";
		String userName2 = "Ayten.ss";

		String mail1 = "Marwa.aa@mail.com";
		String mail2 = "Ayten.ss@mail.com";

		ExternalUser user11 = new ExternalUser();
		user11.setFirstName(firstName1);
		user11.setLastName(lastName1);
		user11.setUsername(userName1);
		user11.setEmail(mail1);

		ExternalUser user12 = new ExternalUser();
		user12.setFirstName(firstName2);
		user12.setLastName(lastName2);
		user12.setUsername(userName2);
		user12.setEmail(mail2);

		List<Role> roles = new ArrayList<Role>();// declare new array list
		roles.add(role);

		user11.setRoles(roles);
		user12.setRoles(roles);

		externalUserService.saveOrUpdate(user11); // add
		externalUserService.saveOrUpdate(user12); // add

		ExternalUser searchUser = new ExternalUser();
		searchUser.setFirstName(firstName1);
		searchUser.setLastName(lastName1);

		List<ExternalUser> searchResults = externalUserService.findByAndCriteria(searchUser); // load
		assertEquals(1, searchResults.size());

		ExternalUser searchedUser = searchResults.get(0);

		assertEquals(searchedUser.getFirstName(), user11.getFirstName());
		assertEquals(searchedUser.getLastName(), user11.getLastName());

		searchUser.setLastName(lastName2);
		searchResults = externalUserService.findByAndCriteria(searchUser); // load
		assertEquals(0, searchResults.size());

	}

	@Test
	public void findByOrCriteria() {

		Role role = new Role();
		role.setRole("superadmin");
		roleService.saveOrUpdate(role);

		String firstName1 = "manal";
		String firstName2 = "sara";

		String lastName1 = "Ali";
		String lastName2 = "Amgad";

		String userName1 = "Marwa.aa";
		String userName2 = "Ayten.ss";

		String mail1 = "Marwa.aa@mail.com";
		String mail2 = "Ayten.ss@mail.com";

		ExternalUser user11 = new ExternalUser();
		user11.setFirstName(firstName1);
		user11.setLastName(lastName1);
		user11.setUsername(userName1);
		user11.setEmail(mail1);

		ExternalUser user12 = new ExternalUser();
		user12.setFirstName(firstName2);
		user12.setLastName(lastName2);
		user12.setUsername(userName2);
		user12.setEmail(mail2);

		List<Role> roles = new ArrayList<Role>();// declare new array list
		roles.add(role);

		user11.setRoles(roles);
		user12.setRoles(roles);

		externalUserService.saveOrUpdate(user11); // add
		externalUserService.saveOrUpdate(user12); // add

		ExternalUser searchUser = new ExternalUser();
		searchUser.setFirstName(firstName1);
		searchUser.setLastName(lastName2);

		List<ExternalUser> searchResults = externalUserService.findByOrCriteria(searchUser); // load
		assertEquals(2, searchResults.size());

		for (ExternalUser user : searchResults) {
			assertTrue(searchUser.getFirstName().equals(user.getFirstName())
					|| searchUser.getLastName().equals(user.getLastName()));

		}

		searchUser.setLastName(lastName1);
		searchResults = externalUserService.findByOrCriteria(searchUser); // load
		assertEquals(1, searchResults.size());

		for (ExternalUser user : searchResults) {
			assertTrue(searchUser.getFirstName().equals(user.getFirstName())
					|| searchUser.getLastName().equals(user.getLastName()));

		}

	}


}
