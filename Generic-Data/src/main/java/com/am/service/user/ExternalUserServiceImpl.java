package com.am.service.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.am.configuration.DatabaseDaos;
import com.am.dao.GenericDao;
import com.am.model.ExternalUser;

@Service
public class ExternalUserServiceImpl extends UserServiceImpl<ExternalUser> implements ExternalUserService {
	@Resource(name = DatabaseDaos.EXTERNAL_USER_DAO)
	private GenericDao<ExternalUser> elementDao;

	@Override
	public GenericDao<ExternalUser> getElementDao() {

		return elementDao;
	}
}
