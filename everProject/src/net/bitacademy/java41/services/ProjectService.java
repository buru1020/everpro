package net.bitacademy.java41.services;

import java.sql.Connection;
import java.util.List;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Project;
import net.bitacademy.java41.vo.ProjectEx;
import net.bitacademy.java41.vo.ProjectMember;

@Component
public class ProjectService {
	DBConnectionPool conPool;
	ProjectDao projectDao;
	MemberDao memberDao;

	public ProjectService setConPool(DBConnectionPool conPool) {
		this.conPool = conPool;
		return this;
	}
	public ProjectService setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}
	public ProjectService setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	
	public List<ProjectEx> getMyProjects(String email) throws Exception {
		return projectDao.getUserProjectList(email);
	}

	public List<ProjectEx> getTotalProjectList() throws Exception {
		return projectDao.getProjectList();
	}
	
	public ProjectEx getProjectInfo(int no) throws Exception {
		return projectDao.getProjectInfol(no);
	}
	
	public List<ProjectMember> getProjectMemberList(int no) throws Exception {
		return memberDao.getProjectMemberList(no);
	}
	
	public void resisterProject(Project project) throws Exception {
		Connection con = conPool.getConnection();
		con.setAutoCommit(false);
		
		try {
			projectDao.add(project);
			con.commit();
			
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			conPool.returnConnection(con);
		}
	}
	
	public int deleteProject(int no) throws Exception {
		Connection con = conPool.getConnection();
		con.setAutoCommit(false);
		int count = 0;
		try {
			count = projectDao.delete(no);
			con.commit();
			
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			conPool.returnConnection(con);
		}
		
		return count;
	}
	
	public int projectUpdate(ProjectEx project) throws Exception {
		Connection con = conPool.getConnection();
		con.setAutoCommit(false);
		int count = 0;
		try {
			count = projectDao.update(project);
			con.commit();
			
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			conPool.returnConnection(con);
		}
		
		return count;
	}

	

}
