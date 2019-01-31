package com.am.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

import com.am.dao.GenericDao;
import com.am.exception.DeleteReferenceException;
import com.am.model.MongoBean;

public interface GenericDaoService<T extends MongoBean> {

	Datastore getDatastore();

	GenericDao<T> getElementDao();

	void setDataStore(Datastore datastore);

	List<T> getAll();

	List<T> getAll(String... orders);

	void saveOrUpdate(T catalogElement);

	void update(T catalogElement);

	T findById(ObjectId id);

	void deleteAll();

	List<T> findByAndCriteria(T catalogElement);

	List<T> findByOrCriteria(T catalogElement);

	List<T> findBy(String key, Object value);

	List<T> findOrCriteria(String key, Object... values);

	void delete(T catalogElement) throws DeleteReferenceException;

}
