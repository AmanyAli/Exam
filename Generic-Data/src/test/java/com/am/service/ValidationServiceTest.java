package com.am.service;

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
import com.am.model.Validation;
import com.am.model.Validator;
import com.am.service.validation.ValidationService;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { DatabaseConfiguration.class })

public class ValidationServiceTest extends TestCase {

	@Autowired
	ValidationService validationService;

	@Override
	@Before
	public void setUp() {
		clear();
	}

	private void clear() {

		validationService.deleteAll();

	}

	@Override
	@After
	public void tearDown() {
		clear();
	}

	@Test
	public void saveOrUpdateTest() {
		String screenName = "Validation";
		Validator validator = new Validator();
		validator.setFieldName("textFeiled");
		validator.setMaxLength(5);
		validator.setMinLength(3);
		validator.setPattern("ptt");
		validator.setRequired(true);
		Validation validation = new Validation();
		validation.setScreenName(screenName);

		validation.fields.add(validator);
		validationService.saveOrUpdate(validation);

		Validation loadedValidation = validationService.findById(validation.getId());

		assertNotNull(loadedValidation);

		assertEquals(loadedValidation, validation);
	}

	@Test
	public void findByAndCriteriaTest() {
		/// add validation
		String screenName1 = "Validation1";
		Validator validator1 = new Validator();
		validator1.setFieldName("textFeiled1");
		validator1.setMaxLength(5);
		validator1.setMinLength(3);
		validator1.setPattern("ptt");
		validator1.setRequired(true);
		Validation validation1 = new Validation();
		validation1.setScreenName(screenName1);

		validation1.fields.add(validator1);
		validationService.saveOrUpdate(validation1);
		// add another validation
		String screenName2 = "Validation2";
		Validator validator2 = new Validator();
		validator2.setFieldName("textFeiled2");
		validator2.setMaxLength(4);
		validator2.setMinLength(3);
		validator2.setPattern("ptst");
		validator2.setRequired(true);
		Validation validation2 = new Validation();
		validation2.setScreenName(screenName2);

		validation2.fields.add(validator2);
		validationService.saveOrUpdate(validation2);
		// load first validation
		Validation loadedValidation = validationService.findById(validation1.getId());
		assertNotNull(loadedValidation);

		// test FindByAnd
		Validation searchValidation = new Validation(true);
		searchValidation.setScreenName(screenName1);

		List<Validation> searchedValidationList = validationService.findByAndCriteria(searchValidation);

		assertNotNull(searchedValidationList);
		assertEquals(1, searchedValidationList.size());
		for (Validation selectedValidation : searchedValidationList) {
			assertEquals(selectedValidation, validation1);

		}

	}
}
