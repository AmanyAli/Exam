package com.am.service.validation;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.am.configuration.DatabaseDaos;
import com.am.dao.GenericDao;
import com.am.model.Validation;
import com.am.service.AbstractGenericService;

@Component
public class ValidationServiceImpl extends AbstractGenericService<Validation> implements ValidationService {

	final static Logger logger = Logger.getLogger(ValidationServiceImpl.class);

	@Resource(name = DatabaseDaos.VALIDATION_DAO)
	private GenericDao<Validation> validationlDao;

	@Override
	public GenericDao<Validation> getElementDao() {

		return validationlDao;
	}
}
