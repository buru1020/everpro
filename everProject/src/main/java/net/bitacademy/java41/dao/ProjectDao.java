package net.bitacademy.java41.dao;

import java.util.List;

import net.bitacademy.java41.vo.Project;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectDao {
	@Autowired SqlSessionFactory sqlSessionFactory;
	
	public ProjectDao() {}
	
	
	public List<Project> getProjectList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("net.bitacademy.java41.dao.ProjectMapper.getProjectList");
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {sqlSession.close();} catch (Exception e) {}
		}
	}
	
	public List<Project> getUserProjectList(String email) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("net.bitacademy.java41.dao.ProjectMapper.getUserProjectList", email);
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {sqlSession.close();} catch (Exception e) {}
		}
	}
	
	public Project getProjectInfo(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			Project project = sqlSession.selectOne("net.bitacademy.java41.dao.ProjectMapper.getProjectInfo", no);
		 	sqlSession.commit();
		 	
		 	return project;
		 	
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
			
		} finally {
			try {sqlSession.close();} catch (Exception e) {}
			
		}
	}
	
	public int addProject(Project project) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			sqlSession.insert(
					"net.bitacademy.java41.dao.ProjectMapper.addProject", project);
			sqlSession.commit();
				
			return project.getNo();
			
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
			
		} finally {
			try {sqlSession.close();} catch(Exception e) {}
			
		}
	}

	public int update(Project project) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
				int count = sqlSession.update(
					"net.bitacademy.java41.dao.ProjectMapper.update", project);
				
				sqlSession.commit();
				return count;

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		
		} finally {
			try {sqlSession.close();} catch(Exception e) {}
		}
	}

	public int deleteProject(int projectNo) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.delete(
					"net.bitacademy.java41.dao.ProjectMapper.deleteProject", projectNo);
			
			sqlSession.commit();
			
			return count;
			
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
			
		} finally {
			try {sqlSession.close();} catch (Exception e) {}
			
		}
	}	
}
