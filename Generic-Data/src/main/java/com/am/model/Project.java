package com.am.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Transient;

import com.am.util.DateUtil;
/**
 * @author Amnay Ali
 * 
 * Project Entity
 *
 */
@Entity(value = "project", noClassnameStored = true)
@Indexes({ @Index(fields = { @Field("name") }, options = @IndexOptions(unique = true)),
		@Index(fields = { @Field("schemaName") }, options = @IndexOptions(unique = true)) })

public class Project implements MongoBean {

	@Id
	private ObjectId id;

	private String name;

	private String schemaName;

	private Boolean defaultProject;

	private String runtimeProject;

	private String projectCode;
	private String projectShortName;
	private String projectOfficialName;
	private Date projectStartDate;
	@Transient
	private String startDate;

	private Date projectDeliveryDate;
	private Date projectCreationDate;

	@Transient
	public static Project currentProject;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((projectCode == null) ? 0 : projectCode.hashCode());
		result = prime * result + ((projectCreationDate == null) ? 0 : projectCreationDate.hashCode());
		result = prime * result + ((projectDeliveryDate == null) ? 0 : projectDeliveryDate.hashCode());
		result = prime * result + ((projectOfficialName == null) ? 0 : projectOfficialName.hashCode());

		result = prime * result + ((projectShortName == null) ? 0 : projectShortName.hashCode());
		result = prime * result + ((projectStartDate == null) ? 0 : projectStartDate.hashCode());
		result = prime * result + ((runtimeProject == null) ? 0 : runtimeProject.hashCode());
		result = prime * result + ((schemaName == null) ? 0 : schemaName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (projectCode == null) {
			if (other.projectCode != null)
				return false;
		} else if (!projectCode.equals(other.projectCode))
			return false;
		if (projectCreationDate == null) {
			if (other.projectCreationDate != null)
				return false;
		} else if (!projectCreationDate.equals(other.projectCreationDate))
			return false;

		if (projectDeliveryDate == null) {
			if (other.projectDeliveryDate != null)
				return false;
		} else if (!projectDeliveryDate.equals(other.projectDeliveryDate))
			return false;

		if (projectOfficialName == null) {
			if (other.projectOfficialName != null)
				return false;
		} else if (!projectOfficialName.equals(other.projectOfficialName))
			return false;

		if (projectShortName == null) {
			if (other.projectShortName != null)
				return false;
		} else if (!projectShortName.equals(other.projectShortName))
			return false;
		if (projectStartDate == null) {
			if (other.projectStartDate != null)
				return false;
		} else if (!projectStartDate.equals(other.projectStartDate))
			return false;
		if (runtimeProject == null) {
			if (other.runtimeProject != null)
				return false;
		} else if (!runtimeProject.equals(other.runtimeProject))
			return false;
		if (schemaName == null) {
			if (other.schemaName != null)
				return false;
		} else if (!schemaName.equals(other.schemaName))
			return false;
		return true;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public Boolean isDefaultProject() {
		if (defaultProject == null) {
			defaultProject = false;
		}
		return defaultProject;
	}

	public void setDefaultProject(Boolean defaultProject) {
		this.defaultProject = defaultProject;
	}

	public boolean getDefaultProject() {
		return this.defaultProject;
	}

	public String getRuntimeProject() {
		return runtimeProject;
	}

	public void setRuntimeProject(String runtimeProject) {
		this.runtimeProject = runtimeProject;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectShortName() {
		return projectShortName;
	}

	public void setProjectShortName(String projectShortName) {
		this.projectShortName = projectShortName;
	}

	public String getProjectOfficialName() {
		return projectOfficialName;
	}

	public void setProjectOfficialName(String projectOfficialName) {
		this.projectOfficialName = projectOfficialName;
	}

	public Date getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;

	}

	public String getStartDate() {
		if (projectStartDate != null) {
			startDate = DateUtil.formatDateTime(projectStartDate);
		}
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Date getProjectDeliveryDate() {
		return projectDeliveryDate;
	}

	public void setProjectDeliveryDate(Date projectDeliveryDate) {
		this.projectDeliveryDate = projectDeliveryDate;
	}

	public Date getProjectCreationDate() {
		return projectCreationDate;
	}

	public void setProjectCreationDate(Date projectCreationDate) {
		this.projectCreationDate = projectCreationDate;
	}

}
