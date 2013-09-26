package net.bitacademy.java41.services;

import java.util.List;

import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.dao.ProjectMemberDao;
import net.bitacademy.java41.vo.Project;
import net.bitacademy.java41.vo.ProjectMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
public class ProjectService {
	@Autowired TaskService taskService;
	@Autowired ProjectDao projectDao;
	@Autowired ProjectMemberDao projectMemberDao;
	@Autowired MemberDao memberDao;
	@Autowired PlatformTransactionManager txManager;

	
	public List<Project> getMyProjectList(String email) throws Exception {
		return projectDao.getUserProjectList(email);
	}

	public List<Project> getTotalProjectList() throws Exception {
		return projectDao.getProjectList();
	}
	
	public Project getProjectInfo(int no) throws Exception {
		return projectDao.getProjectInfo(no);
	}
	
	public List<ProjectMember> getProjectMemberList(int no) throws Exception {
		return memberDao.getProjectMemberList(no);
	}
	
	public void resisterProject(Project project) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("resisterProjectTx");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus txStatus = txManager.getTransaction(def);
		try {
			projectDao.addProject(project);
			projectMemberDao.addProjectMember(project.getPlEmail(), project.getNo(), 0);
			
			txManager.commit(txStatus);
			
		} catch (Exception e) {
			txManager.rollback(txStatus);
			throw e;
		}
		
	}
	
	public int deleteProject(int projectNo) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("deleteProjectTx");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus txStatus = txManager.getTransaction(def);
		try {
			taskService.deleteTask(projectNo, 0);
			projectMemberDao.deleteProjectMember(projectNo, null);
			int count = projectDao.deleteProject(projectNo);
			
			txManager.commit(txStatus);
			
			return count;
			
		} catch (Exception e) {
			txManager.rollback(txStatus);
			throw e;
		}
	}
	
	public int deleteProjectMember(int projectNo) throws Exception {
		try {
			int count = projectMemberDao.deleteProjectMember(projectNo, null);

			return count;
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public int projectUpdate(Project project) throws Exception {
		return projectDao.update(project);
	}

	

}
