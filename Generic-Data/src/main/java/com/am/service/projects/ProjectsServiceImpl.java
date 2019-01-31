package com.am.service.projects;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.am.configuration.DatabaseDaos;
import com.am.dao.GenericDao;
import com.am.exception.DeleteReferenceException;
import com.am.model.Project;
import com.am.service.AbstractGenericService;
import com.am.service.ApplicationService;
import com.am.service.ServiceUtil;

@Service
public class ProjectsServiceImpl extends AbstractGenericService<Project> implements ProjectService {

	final static Logger logger = Logger.getLogger(ProjectsServiceImpl.class);

	private static final String RUNTIMEPROJECT_PREFIX = "run_";

	@Resource(name = DatabaseDaos.PROJECT_DAO)
	private GenericDao<Project> projectDao;

	@Override
	public GenericDao<Project> getElementDao() {

		return projectDao;
	}

	@Override
	public void setDefaultProject(Project project) {
		
		Project searchDefaultProject= new Project();
		searchDefaultProject.setDefaultProject(true);
		List<Project> projects=projectDao.findByAndCriteria(searchDefaultProject);
		for(Project oldDefault: projects){
			oldDefault.setDefaultProject(false);
			projectDao.update(oldDefault);
		}
		
		project.setDefaultProject(true);
		projectDao.update(project);
		
	}
	// delete project require delete its own schema as when create a project we dedicate a schema to it
	@Override
	public void delete(Project project) throws DeleteReferenceException {
		
		ApplicationService modifiedApplicationService=ServiceUtil.getApplicationService();
		
		String currentSchema = getDatastore().getDB().getName();

		modifiedApplicationService.changeDataStore(project.getSchemaName());
		modifiedApplicationService.getProjectService().getDatastore().getDB().dropDatabase();

		modifiedApplicationService.changeDataStore(
				RUNTIMEPROJECT_PREFIX + project.getSchemaName());
		modifiedApplicationService.getProjectService().getDatastore().getDB().dropDatabase();
		modifiedApplicationService.changeDataStore(currentSchema);
		super.delete(project);
	}

}
