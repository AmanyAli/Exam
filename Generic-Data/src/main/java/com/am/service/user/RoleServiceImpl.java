package com.am.service.user;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.am.configuration.DatabaseDaos;
import com.am.dao.GenericDao;
import com.am.model.Role;
import com.am.service.AbstractGenericService;

@Service

public class RoleServiceImpl extends AbstractGenericService<Role> implements RoleService {

	final static Logger logger = Logger.getLogger(RoleServiceImpl.class);

	@Resource( name=DatabaseDaos.ROLE_DAO)
	GenericDao<Role> elementDao;

	
	@Override
	public GenericDao<Role> getElementDao() {
		
		return elementDao;
	}

	
	
}
