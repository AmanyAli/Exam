package com.am.service.language;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.am.configuration.DatabaseDaos;
import com.am.dao.GenericDao;
import com.am.model.LanguageSupportConfig;
import com.am.service.AbstractGenericService;

@Component
public class LanguageSupportConfigServiceImpl extends AbstractGenericService<LanguageSupportConfig> implements LanguageSupportConfigService {

	final static Logger logger = Logger.getLogger(LanguageSupportConfig.class);
	
	
	@Resource(name = DatabaseDaos.LANGUAGE_SUPPORT_CONFIG_DAO)
	private GenericDao<LanguageSupportConfig> languageSupportConfigDao;

	@Override
	public GenericDao<LanguageSupportConfig> getElementDao() {

		return languageSupportConfigDao;
	}

	
}
