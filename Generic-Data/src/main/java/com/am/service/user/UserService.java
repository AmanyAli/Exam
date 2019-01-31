package com.am.service.user;

import java.util.List;

import com.am.model.User;
import com.am.service.GenericDaoService;

public interface UserService<T extends User> extends GenericDaoService<T> {

	List<T> getAll(boolean active);

}
