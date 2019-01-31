package com.am.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.CriteriaContainerImpl;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.am.model.MongoBean;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * @author Amany Ali
 * 
 *         Generic DAO Implementation Class
 *
 * @param <E>
 *            Entity class
 */
@SuppressWarnings("unchecked")
public class GenericDaoImp<E extends MongoBean> implements GenericDao<E> {

	private final Class<E> entityClass;
	@Autowired
	private Datastore datastore;

	public GenericDaoImp(final Class<E> type) {

		this.entityClass = type;
	}

	public GenericDaoImp() {

		this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Override
	public E findById(final Serializable id) {
		return (E) getDatastore().get(this.entityClass, id);
	}

	@Override
	public Serializable save(E entity) {

		return getDatastore().save(entity);
	}

	@Override
	public void saveOrUpdate(E entity) {
		getDatastore().save(entity);

	}

	@Override
	public void update(E entity) {

		getDatastore().save(entity);

	}

	@Override
	public void delete(E entity) {
		getDatastore().delete(entity);
	}

	@Override
	public void deleteAll() {
		List<E> entities = findAll();
		for (E entity : entities) {
			getDatastore().delete(entity);
		}
	}

	@Override
	public List<E> findAll() {

		List<E> list = getDatastore().find(this.entityClass).asList();
		return list;
	}

	@Override
	public List<E> findByCriteria(Query<E> query) {

		return query.asList();

	}

	@Override
	public List<E> findByCriteria(Query<E> query, int maxResults) {
		// return
		// getDatastore().createQuery(hql).setMaxResults(maxResults).list();
		return query.limit(maxResults).asList();

	}

	@Override
	public List<E> findByCriteria(int maxResults, String... orders) {

		Query<E> query = createQuery().limit(maxResults);
		for (String order : orders) {
			query.order(order);
		}
		return query.asList();

	}

	@Override
	public List<E> findByCriteria(String... orders) {

		Query<E> query = createQuery();
		for (String order : orders) {
			query.order(order);
		}
		return query.asList();

	}

	@Override
	public List<E> findByCriteria(Query<E> query, String... orders) {

		for (String order : orders) {
			query.order(order);
		}
		return query.asList();

	}

	@Override
	public List<E> findByCriteria(int maxResults, Query<E> query, String... orders) {

		query.limit(maxResults);
		for (String order : orders) {
			query.order(order);
		}
		return query.asList();

	}

	@Override
	public List<E> findByAndCriteria(E entity) {

		Query<E> query = createCrieria(entity);

		return query.asList();
	}

	public Query<E> createCrieria(E entity) {

		Query<E> query = getDatastore().createQuery(this.entityClass);
		Map<Object, Object> map = MongoBean.beanProperties(entity);
		CriteriaContainerImpl[] object = new CriteriaContainerImpl[map.size()];

		int i = 0;

		for (Entry<Object, Object> entry : map.entrySet()) {
			Object value = entry.getValue();

			if (value != null) {

				object[i] = query.criteria(entry.getKey().toString()).equal(value);

			}
			i++;
		}
		query.and(object);

		return query;

	}

	@Override
	public List<E> findByOrCriteria(E entity) {
		Query<E> query = getDatastore().createQuery(this.entityClass);

		Map<Object, Object> map = MongoBean.beanProperties(entity);
		CriteriaContainerImpl[] object = new CriteriaContainerImpl[map.size()];

		int i = 0;
		for (Entry<Object, Object> entry : map.entrySet()) {
			Object value = entry.getValue();
			if (value != null) {

				object[i] = query.criteria(entry.getKey().toString()).equal(value);
			}
			i++;
		}

		query.or(object);
		return query.asList();
	}

	public Datastore getDatastore() {
		return datastore;
	}

	@Override
	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}

	@Override
	public Query<E> createQuery() {

		return getDatastore().find(this.entityClass);
	}

	@Override
	public long count() {
		return getDatastore().getCount(this.entityClass);
	}

	@Override
	public List<DBObject> findIndexed(E entity) {
		// TODO Auto-generated method stub
		DBCollection userCollection = getDatastore().getDB().getCollection(this.entityClass.getName());
		List<DBObject> indexes = userCollection.getIndexInfo();
		return indexes;
	}

}
