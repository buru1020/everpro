package net.bitacademy.java41.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectMemberDao {
	@Autowired SqlSessionFactory sqlSessionFactory;
	
	public ProjectMemberDao() {}
	
	
	public int addProjectMember(String email, int projectNo, int projectMemberLevel) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("email", email);
			paramMap.put("projectNo", projectNo);
			paramMap.put("projectMemberLevel", projectMemberLevel);
			
			int count = sqlSession.insert(
					"net.bitacademy.java41.dao.ProjectMemberMapper.addProjectMember", paramMap);
			
			sqlSession.commit();
			
			return count;
			
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
			
		} finally {
			try {sqlSession.close();} catch(Exception e) {}
			
		}
	}

	public int deleteProjectMember(int projectNo, String email) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("projectNo", projectNo);
			paramMap.put("email", email);
			
			int count = sqlSession.delete(
					"net.bitacademy.java41.dao.ProjectMemberMapper.deleteProjectMember", paramMap);
			
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
