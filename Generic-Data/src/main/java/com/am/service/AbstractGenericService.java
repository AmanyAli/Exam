package com.am.service;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import org.springframework.transaction.annotation.Transactional;

import com.am.dao.GenericDaoImp;
import com.am.dao.GenericDao;
import com.am.exception.DeleteReferenceException;
import com.am.model.MongoBean;

/**
 * @author Amany Ali
 *
 *         Abstract generic service to be implemented by any service entity to
 *         add its own business related method. The service class interact with
 *         DAO
 * @param <T>
 *            Entity param
 */
public abstract class AbstractGenericService<T extends MongoBean> implements GenericDaoService<T> {

	protected Set<GenericDaoImp<MongoBean>> genericDaos;

	private final Class<T> entityClass;

	public AbstractGenericService() {
		this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public abstract GenericDao<T> getElementDao();

	@Override
	public Datastore getDatastore() {
		return getElementDao().getDatastore();
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	@Transactional(readOnly = false)
	public void saveOrUpdate(T entity) {
		getElementDao().save(entity);
	}

	@Transactional(readOnly = false)
	public void update(T entity) {
		getElementDao().update(entity);

	}

	public T findById(ObjectId id) {
		return getElementDao().findById(id);
	}

	public List<T> findBy(String key, Object value) {
		return getElementDao().findByCriteria((Query<T>) getElementDao().createQuery().filter(key, value));

	}

	public List<T> findOrCriteria(String key, Object... values) {

		Criteria[] criterias = new Criteria[values.length];

		for (int i = 0; i < values.length; i++) {
			criterias[i] = getElementDao().createQuery().criteria(key).equal(values[i]);
		}
		Query<T> query = getElementDao().createQuery();
		query.or(criterias);

		return query.asList();

	}

	@Transactional(readOnly = false)
	public void delete(T entity) throws DeleteReferenceException {
		getElementDao().delete(entity);

	}

	@Transactional(readOnly = false)
	public void deleteAll() {
		getElementDao().deleteAll();

	}

	public List<T> getAll(String... orders) {

		return getElementDao().findByCriteria(orders);

	}

	public List<T> getAll() {

		return getElementDao().findAll();
	}

	public List<T> findByAndCriteria(T entity) {

		return getElementDao().findByAndCriteria(entity);
	}

	public List<T> findByOrCriteria(T entity) {

		return getElementDao().findByOrCriteria(entity);
	}

	public void setDataStore(Datastore datastore) {

		updateBeanDao(this, datastore);

	}

	public void setGenericDaos(GenericDaoImp<MongoBean>... daos) {
		genericDaos.addAll(Arrays.asList(daos));

	}

	public static Set<GenericDaoService> updateBeanService(Object bean, Datastore datastore) {
		Set<GenericDaoService> properties = new HashSet<>();
		try {

			Arrays.asList(Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors()).stream()
					// filter out properties with setters only
					.filter(pd -> Objects.nonNull(pd.getReadMethod())).forEach(pd -> { // invoke
																						// method
						try {

							Object value = pd.getReadMethod().invoke(bean);

							if (value != null && value instanceof GenericDaoService) {
								GenericDaoService genericDaoService = (GenericDaoService) value;
								genericDaoService.setDataStore(datastore);
								properties.add(genericDaoService);

							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					});

		} catch (IntrospectionException e) {
			// TODO and here, too
			e.printStackTrace();

		}

		return properties;
	}

	// this method allow switching between DB schema by updating DAO datastore object
	public static Set<GenericDao> updateBeanDao(Object bean, Datastore datastore) {
		Set<GenericDao> properties = new HashSet<>();
		try {

			Arrays.asList(Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors()).stream()
					// filter out properties with setters only
					.filter(pd -> Objects.nonNull(pd.getReadMethod())).forEach(pd -> { // invoke
																						// method
						try {

							Object value = pd.getReadMethod().invoke(bean);

							if (value != null && value instanceof GenericDao) {
								GenericDao genericDao = (GenericDao) value;
								genericDao.setDatastore(datastore);
								properties.add(genericDao);

							}

						} catch (Exception e) {
							// e.printStackTrace();
						}
					});

		} catch (IntrospectionException e) {
			// TODO and here, too
			e.printStackTrace();

		}

		return properties;
	}

	// public void setElementDao(D elementDao) {
	// this.elementDao = elementDao;
	// }

}
