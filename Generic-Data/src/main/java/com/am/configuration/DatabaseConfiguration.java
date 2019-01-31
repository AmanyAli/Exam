package com.am.configuration;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

/**
 * @author Amnay Ali
 * 
 *         Configuration DB Class
 *
 */
@Configuration

@ComponentScan(value = { "com.am" })
@PropertySource(value = { "classpath:application.properties" })
public class DatabaseConfiguration {

	public static final String host = "mongoHost";
	public static final String port = "mongoPort";
	public static final String dbname = "mongoDbname";

	public static final String USER_PROFILE_DAO = "userProfileDao";

	@Autowired
	private Environment env;

	@Bean
	public MongoClient mongoClient() throws UnknownHostException {

		StringBuilder mongoProp = new StringBuilder();
		mongoProp.append(env.getRequiredProperty(host));
		mongoProp.append(":");
		mongoProp.append(env.getRequiredProperty(port));

		// List<MongoCredential> credentials = new ArrayList<MongoCredential>();
		// credentials.add(MongoCredential.createMongoCRCredential("admin",
		// "admin",
		// "OPENSHIFT_MONGODB_DB_PASSWORD".toCharArray()));
		// credentials.add(MongoCredential.createMongoCRCredential("OPENSHIFT_MONGODB_DB_USER",
		// "admin",
		// "OPENSHIFT_MONGODB_DB_PASSWORD".toCharArray()));

		// return new MongoClient(serverAddress, credentials);
		return (new MongoClient(mongoProp.toString()));
	}

	@Bean
	public Datastore datastore() throws UnknownHostException {
		Morphia morphia = morphia();
		Datastore datastore = morphia.createDatastore(mongoClient(), env.getRequiredProperty(dbname));
		datastore.ensureIndexes();
		return datastore;
	}

	@Bean
	public Morphia morphia() throws UnknownHostException {
		Morphia morphia = new Morphia();
		morphia.mapPackage("com.am.model");

		return morphia;
	}

	@Bean
	public MongoDbFactory mongoDbFactory() throws UnknownHostException {
		return new SimpleMongoDbFactory(mongoClient(), env.getRequiredProperty(dbname));
	}

	@Bean
	public MongoTemplate mongoTemplate() throws UnknownHostException {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}
}
