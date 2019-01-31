package com.am.service;

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
import com.am.model.Role;
import com.am.service.user.RoleService;
import com.mongodb.DuplicateKeyException;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { DatabaseConfiguration.class })

public class RoleServiceTest extends TestCase {

	@Autowired
	private RoleService roleService;

	@Before
	public void setup() {

		clear();
	}

	@After
	public void tearDown() {

		clear();
	}

	private void clear() {
		roleService.deleteAll();

	}

	@Test
	public void saveDeleteTest() {
		Role role = new Role();
		String roleString = "admin";
		//String type = "ex";

		role.setRole(roleString);
		//role.setType(type);

		roleService.saveOrUpdate(role);

		Role loadedRole = roleService.findById(role.getId());
		assertNotNull(loadedRole);

		assertEquals(loadedRole, role);
		
		
		try {
			roleService.delete(loadedRole);
		} catch (DeleteReferenceException e) {
			fail();
		}
		
		loadedRole = roleService.findById(role.getId());
		assertNull(loadedRole);
	}
	
	
	
	@Test
	public void saveDuplicateRoleTest() {
		Role role = new Role();
		String roleString = "admin";
		

		role.setRole(roleString);
		role.setType(Role.INTERNAL_USER_ROLE_TYPE);

		roleService.saveOrUpdate(role);
		
		
		Role role2 = new Role();
		role2.setRole(roleString);
		role2.setType(Role.EXTERNAL_USER_ROLE_TYPE);
		
		
		
		
		try {
			roleService.saveOrUpdate(role2);
			fail();
		} catch (DuplicateKeyException e) {
			
		}
		
	
	}

}
