package com.am.service.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.am.exception.DeleteReferenceException;
import com.am.model.User;
import com.am.service.AbstractGenericService;

public abstract class UserServiceImpl<T extends User> extends AbstractGenericService<T>
		implements UserService<T> {

	final static Logger logger = Logger.getLogger(UserServiceImpl.class);
	// private final String DELETE_ERROR_MESSAGE = "User Profile cannot be
	// deleted as it has child of Role.";
	// private final String DELETE_USER_ERROR_MESSAGE = "User Profile cannot be
	// deleted as it has child of Event.";

	

	@Override
	public List<T> getAll(boolean actived) {
		logger.debug("Getting all users...");
		T searchUser = null;
		try {
			searchUser = getEntityClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		searchUser.setDeleted(!actived);
		searchUser.setLocked(!actived);
		searchUser.setRoles(null);
		return getElementDao().findByAndCriteria(searchUser);
	}
	/*No physical deletion for user*/
	@Override
	public void delete(T user) throws DeleteReferenceException {
		
		user.setDeleted(true);
		user.setLocked(true);
		saveOrUpdate(user);
	}

}
