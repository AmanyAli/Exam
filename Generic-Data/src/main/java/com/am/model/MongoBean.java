package com.am.model;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.bson.types.ObjectId;

import com.am.util.StringUtil;

/**
 * @author Amany Ali
 * 
 *         Interface to be implemented by any Mongo Entity Model Class. Any Implemented class must not contain
 *         
 *
 */
public interface MongoBean {
	public static final String ID_PROPERTY = "id";

	public ObjectId getId();

	public void setId(ObjectId id);

	/*
	 * This method use java reflection to create query criteria and store it in
	 * Map via checking object get methods and adding criteria for not null
	 * value.
	 */
	public static Map<Object, Object> beanProperties(Object bean) {
		try {
			Map<Object, Object> map = new HashMap<>();
			Arrays.asList(Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors()).stream()
					// filter out properties with setters only
					.filter(pd -> Objects.nonNull(pd.getReadMethod())).forEach(pd -> { // invoke
																						// method
						try {

							Object value = pd.getReadMethod().invoke(bean);

							if (value != null) {
								boolean ignored = false;
								StringBuilder key = new StringBuilder();
								String fieldName = pd.getName();
								key.append(fieldName.substring(0, 1).toLowerCase());
								key.append(fieldName.substring(1));
								PropertyDescriptor mm = null;

								if (value instanceof MongoBean) {

									MongoBean mongoValue = (MongoBean) value;

									// Class type = pd.getName().getType();

									Field field = getField(bean.getClass(), pd.getName());
									Annotation[] annotations = field.getDeclaredAnnotations();

									Map<Object, Object> mapKey = beanProperties(mongoValue);
									boolean embedded = true;
									boolean idOnly = false;

									for (Annotation annotation : annotations) {
										//ignore any transient value criteria
										if (annotation.toString().contains("Transient")) {
											ignored = true;

											break;
										}

										if (annotation.toString()
												.contains("org.mongodb.morphia.annotations.Reference")) {
											// key.append(pd.getName());

											embedded = false;

											if (annotation.toString().contains("idOnly=true")) {
												idOnly = true;
											}

										}

									}
									if (embedded && !ignored) {
										key.append(".");
									}
									if (embedded && !ignored) {
										for (Object key2 : mapKey.keySet()) {
											// key.append(MongoBean.ID_PROPERTY);
											StringBuilder newKey = new StringBuilder();
											newKey.append(key);
											newKey.append(key2);
											map.put(newKey.toString(), mapKey.get(key2));

										}
									} else if (!ignored) {
										if (idOnly) {
											map.put(key.toString(), mongoValue.getId());
										} else {

											map.put(key.toString(), mongoValue);
										}
									}

								} else {

									Field field = getField(bean.getClass(),
											StringUtil.LowerCaseFirstLetter(pd.getName()));
									Annotation[] annotations = field.getDeclaredAnnotations();
									for (Annotation annotation : annotations) {

										if (annotation.toString().contains("Transient")) {
											ignored = true;

											break;
										}

									}
									if (!ignored) {
										map.put(key.toString(), value);
									}
								}
							}
						} catch (Exception e) {
							// e.printStackTrace();
						}
					});
			return map;
		} catch (IntrospectionException e) {
			// and here, too
			return Collections.emptyMap();
		}
	}

	public static Field getField(Class clazz, String fieldName) throws NoSuchFieldException {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			Class superClass = clazz.getSuperclass();
			if (superClass == null) {
				throw e;
			} else {
				return getField(superClass, fieldName);
			}
		}
	}
}
