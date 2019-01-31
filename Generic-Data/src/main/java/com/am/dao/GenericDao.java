package com.am.dao;

import java.io.Serializable;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.am.model.MongoBean;
import com.mongodb.DBObject;


/**
 * @author Amany Ali
 * 
 * Interface to provide crud DAO methods
 *
 * @param <E> entity class type
 */


public interface GenericDao<E extends MongoBean> {
	/**
	 * 
	 * @param entity:
	 *            entity to save
	 * @return Identifier of saved entity
	 */
	Serializable save(E entity);
	
	void setDatastore(Datastore datastore);

	Datastore getDatastore();

	/**
	 * 
	 * @param entity:
	 *            entity to save or update
	 */
	public void saveOrUpdate(E entity);

	/**
	 * 
	 * @param entity:
	 *            entity to update
	 */

	public void update(E entity);

	/**
	 * 
	 * @param entity:
	 *            entity to delete
	 */
	void delete(E entity);

	/**
	 * Delete all records
	 */
	void deleteAll();

	/**
	 * Find all records
	 * 
	 * @return
	 */
	List<E> findAll();

	Query<E> createCrieria(E entity);

	/**
	 * Find by criteria
	 * 
	 * @return
	 */
	List<E> findByCriteria(Query<E> query);

	List<E> findByCriteria(Query<E> query, int maxResult);

	List<E> findByCriteria(int maxResults, String... order);

	List<E> findByCriteria(int maxResults,Query<E> query, String... orders);
	
	List<E> findByCriteria(Query<E> query, String... orders);
	
	/**
	 * Find all records matching provided entity
	 * 
	 * @param entity:
	 *            entity object used for search
	 * @return
	 */

	/**
	 * Find by primary key
	 * 
	 * @param id
	 * @return unique entity
	 */
	E findById(Serializable id);

	Query<E> createQuery();

	long count();

	List<E> findByAndCriteria(E entity);

	List<E> findByOrCriteria(E entity);
	List<E> findByCriteria( String... orders);

	List<DBObject> findIndexed(E entity);



	

}
