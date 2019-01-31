package com.am.service;

import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.am.configuration.AppConfiguration;
import com.mongodb.MongoClient;

/**
 * @author Amany Ali
 * 
 * Service Util class to manage Spring initiation. 
 *
 */
public class ServiceUtil {

	
	private static ConfigurableApplicationContext context;

	private static ApplicationService app;

	

	public static void initContext(String[] args) {
		
		context = SpringApplication.run(AppConfiguration.class, args);
		app = context.getBean(ApplicationService.class, args);
	}

	public static ApplicationService getApplicationService() {

		return app;
	}

	

	

	public static void createSchema(String dbName) {
		MongoClient client = null;
		try {
			client = new MongoClient();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.getDB(dbName);
	}

}
