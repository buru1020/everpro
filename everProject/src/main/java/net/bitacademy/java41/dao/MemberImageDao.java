package net.bitacademy.java41.dao;

import java.util.HashMap;
import java.util.List;

import net.bitacademy.java41.vo.Photo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberImageDao {
	@Autowired SqlSessionFactory sqlSessionFactory;
	
	public MemberImageDao() {}

	
	public int addPhoto(String email, String path) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("email", email);
			paramMap.put("path", path);
			
			int count = sqlSession.insert("net.bitacademy.java41.dao.MemberImageMapper.addPhoto", paramMap);
			sqlSession.commit();
			
			return count;
			
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
			
		} finally {
			try {sqlSession.close();} catch(Exception e) {}
		}
	}

	public List<Photo> listPhoto(String email) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			return sqlSession.selectList("net.bitacademy.java41.dao.MemberImageMapper.listPhoto", email);
			
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			try {sqlSession.close();} catch (Exception e) {}
		}
	}
	
	public int deletePhoto(String email) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			int count =  sqlSession.delete("net.bitacademy.java41.dao.MemberImageMapper.deletePhoto", email);
			
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
