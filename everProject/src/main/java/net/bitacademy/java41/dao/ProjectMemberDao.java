package net.bitacademy.java41.dao;

import java.util.Map;

public interface ProjectMemberDao {
	
	int addProjectMember(Map<String, Object> paramMap) throws Exception;

	int deleteProjectMember(Map<String, Object> paramMap) throws Exception;
	
}
