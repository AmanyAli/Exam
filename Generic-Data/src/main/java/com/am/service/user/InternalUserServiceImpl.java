package com.am.service.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.am.configuration.DatabaseDaos;
import com.am.dao.GenericDao;
import com.am.model.InternalUser;

@Service
public class InternalUserServiceImpl extends UserServiceImpl<InternalUser> implements InternalUserService {
	@Resource(name = DatabaseDaos.INTERNAL_USER_DAO)
	private GenericDao<InternalUser> elementDao;

	@Override
	public GenericDao<InternalUser> getElementDao() {

		return elementDao;
	}
}
