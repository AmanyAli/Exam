package com.am.configuration;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.am.dao.GenericDao;
import com.am.dao.GenericDaoImp;
import com.am.model.ExternalUser;
import com.am.model.InternalUser;
import com.am.model.LanguageSupportConfig;
import com.am.model.Project;
import com.am.model.Role;
import com.am.model.Validation;

/**
 * @author Amnay Ali
 * 
 *         DAO Configuration Beans
 *
 */
@Configuration

@ComponentScan(value = { "com.am" })

public class DatabaseDaos {

	public static final String EXTERNAL_USER_DAO = "externalUserDao";
	public static final String INTERNAL_USER_DAO = "internalUserDao";
	public static final String ROLE_DAO = "roleDao";
	public static final String SYSTEM_CONFIG_DAO = "systemConfigDao";
	public static final String VALIDATION_DAO = "validationDao";
	public static final String LANGUAGE_SUPPORT_CONFIG_DAO = "languageSupportConfigDao";

	public static final String PROJECT_DAO = "projectDao";

	@Bean(name = PROJECT_DAO)
	public GenericDao<Project> projectDao() throws UnknownHostException {
		GenericDao<Project> projectDao = new GenericDaoImp<Project>(Project.class);
		return projectDao;
	}

	@Bean(name = EXTERNAL_USER_DAO)
	public GenericDao<ExternalUser> externalUserDao() throws UnknownHostException {
		GenericDao<ExternalUser> userProfileDao = new GenericDaoImp<ExternalUser>(ExternalUser.class);
		return userProfileDao;
	}

	@Bean(name = INTERNAL_USER_DAO)
	public GenericDao<InternalUser> internalUserDao() throws UnknownHostException {
		GenericDao<InternalUser> internalUserDao = new GenericDaoImp<InternalUser>(InternalUser.class);
		return internalUserDao;
	}

	@Bean(name = ROLE_DAO)
	public GenericDao<Role> roleDao() throws UnknownHostException {
		GenericDao<Role> roleDao = new GenericDaoImp<Role>(Role.class);
		return roleDao;
	}
	
	

	@Bean(name = VALIDATION_DAO)
	public GenericDao<Validation> validationDao() throws UnknownHostException {
		GenericDao<Validation> validationDao = new GenericDaoImp<Validation>(Validation.class);
		return validationDao;
	}

	@Bean(name = LANGUAGE_SUPPORT_CONFIG_DAO)
	public GenericDao<LanguageSupportConfig> languageSupportConfigDao() throws UnknownHostException {
		GenericDao<LanguageSupportConfig> languageSupportConfigDao = new GenericDaoImp<LanguageSupportConfig>(
				LanguageSupportConfig.class);
		return languageSupportConfigDao;
	}

}
