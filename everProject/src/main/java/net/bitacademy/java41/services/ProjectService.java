package net.bitacademy.java41.services;

import java.util.List;

import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Project;
import net.bitacademy.java41.vo.ProjectMember;

public interface ProjectService {
	
	List<Project> getMyProjectList(String email) throws Exception;

	List<Project> getTotalProjectList() throws Exception;
	
	Project getProjectInfo(int no) throws Exception;
	
	List<ProjectMember> getProjectMemberList(int no) throws Exception;
	
	void resisterProject(Project project) throws Exception;
	
	int deleteProject(int projectNo) throws Exception;
	
	int deleteProjectMember(int projectNo, int feedNo, String emai) throws Exception;
	
	int projectUpdate(Project project) throws Exception;

	boolean isProjectMember(int projectNo, Member sessionMember) throws Exception;
	

}
