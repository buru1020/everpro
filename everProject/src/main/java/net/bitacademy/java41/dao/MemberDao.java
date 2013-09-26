package net.bitacademy.java41.dao;

import java.util.HashMap;
import java.util.List;

import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.ProjectMember;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberDao {
	@Autowired SqlSessionFactory sqlSessionFactory;
	
	public MemberDao() {	}


	public Member getMember(String email, String password) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("email", email);
			paramMap.put("password", password);
			
			return sqlSession.selectOne("net.bitacademy.java41.dao.MemberMapper.getMember", paramMap);

		} catch (Exception e) {
			throw e;
			
		} finally {
			try {sqlSession.close();} catch (Exception e) {}
		}
	}

	public int addMember(Member member) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("net.bitacademy.java41.dao.MemberMapper.addMember", member); 
			sqlSession.commit();
			return count;
			
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
			
		} finally {
			try {sqlSession.close();} catch(Exception e) {}
		}
	}
	
	public List<ProjectMember> getProjectMemberList(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
		List<ProjectMember> list = sqlSession.selectList(
				"net.bitacademy.java41.dao.MemberMapper.getProjectMemberList", no);
		sqlSession.commit();
		return list;
		
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			try {sqlSession.close();} catch (Exception e) {}
		}
	}
	
	public List<Member> getMemberList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();		
		try {
			return sqlSession.selectList("net.bitacademy.java41.dao.MemberMapper.getMemberList");
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {sqlSession.close();} catch (Exception e) {}
		}
	}
	
	public int updateMember(Member member) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.update("net.bitacademy.java41.dao.MemberMapper.updateMember", member); 
			sqlSession.commit();
			
			return count;
			
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
			
		} finally {
			try {sqlSession.close();} catch(Exception e) {}
		}
	}

	public int deleteMember(String email) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.delete("net.bitacademy.java41.dao.MemberMapper.deleteMember", email); 
			sqlSession.commit();
			
			return count;
			
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
			
		} finally {
			try {sqlSession.close();} catch(Exception e) {}
		}
	}	
	
	public int changePassword(String email, String oldPassword, String newPassword) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("email", email);
			paramMap.put("oldPassword", oldPassword);
			paramMap.put("newPassword", newPassword);
			int count = sqlSession.update("net.bitacademy.java41.dao.MemberMapper.changePassword", paramMap); 
			sqlSession.commit();
			
			return count;
			
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
			
		} finally {
			try {sqlSession.close();} catch(Exception e) {}
		}
	}
	

}
