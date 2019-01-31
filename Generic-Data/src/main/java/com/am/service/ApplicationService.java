package com.am.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.am.service.language.LanguageSupportConfigService;
import com.am.service.projects.ProjectService;
import com.am.service.user.ExternalUserService;
import com.am.service.validation.ValidationService;
import com.mongodb.MongoClient;

@Component
public class ApplicationService {

	final static Logger logger = Logger.getLogger(ApplicationService.class);

	@Autowired
	private Morphia morphia;

	@Autowired
	private MongoClient mongoClient;

	@Autowired
	private LanguageSupportConfigService languageSupportConfigService;
	
	@Autowired
	private ExternalUserService externalUserService;

	@Autowired
	private ValidationService validationService;
	
	@Autowired
	private ProjectService projectService;


	private static Map<String, Datastore> dataStoreMap = new HashMap<>();

	// switch schema
	public Datastore changeDataStore(String dbname) {

		Datastore datastore = createDataStore(dbname);

		AbstractGenericService.updateBeanService(this, datastore);
		return datastore;
	}

	public Datastore createDataStore(String dbname) {
		Datastore currentDataStore = projectService.getDatastore();
		dataStoreMap.put(currentDataStore.getDB().getName(), currentDataStore);

		if (dbname == null) {
			return currentDataStore;
		}
		Datastore datastore = dataStoreMap.get(dbname);
		if (datastore == null) {
			datastore = morphia.createDatastore(mongoClient, dbname);
			dataStoreMap.put(dbname, datastore);
			try {
				datastore.ensureIndexes();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return datastore;
	}

	public Morphia getMorphia() {
		return morphia;
	}

	public void setMorphia(Morphia morphia) {
		this.morphia = morphia;
	}


	

	public ExternalUserService getExternalUserService() {
		return externalUserService;
	}

	public void setExternalUserService(ExternalUserService externalUserService) {
		this.externalUserService = externalUserService;
	}

	public LanguageSupportConfigService getLanguageSupportConfigService() {
		return languageSupportConfigService;
	}

	public void setLanguageSupportConfigService(LanguageSupportConfigService languageSupportConfigService) {
		this.languageSupportConfigService = languageSupportConfigService;
	}

	



	


	


	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectsService(ProjectService projectService) {
		this.projectService = projectService;
	}

	

	

	

	
	public ValidationService getValidationService() {
		return validationService;
	}

	public void setValidationService(ValidationService validationService) {
		this.validationService = validationService;
	}

	

	
}
