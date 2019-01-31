package com.am.service.projects;

import com.am.model.Project;
import com.am.service.GenericDaoService;

public interface ProjectService extends GenericDaoService<Project> {

	public void setDefaultProject(Project project);

}
