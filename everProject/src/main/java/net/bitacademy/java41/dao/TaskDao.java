package net.bitacademy.java41.dao;

import java.util.HashMap;
import java.util.List;

import net.bitacademy.java41.vo.Task;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskDao {
	@Autowired SqlSessionFactory sqlSessionFactory;
	
	public TaskDao() {}

	
	public Task getTask(int projectNo, int taskNo) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("projectNo", projectNo);
			paramMap.put("taskNo", taskNo);
			
			Task task = sqlSession.selectOne(
					"net.bitacademy.java41.dao.TaskMapper.getTask",
					paramMap);
			sqlSession.commit();
			return task;
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
			
		} finally {
			try {sqlSession.close();} catch (Exception e) {}
			
		}
	}

	
	public List<Task> getTaskList(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			List<Task> list =sqlSession.selectList(
					"net.bitacademy.java41.dao.TaskMapper.getTaskList", no);
		
			sqlSession.commit();
			return list;
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
			
		} finally {
			try {sqlSession.close();} catch (Exception e) {}
			
		}
	}

	public int updateTask(Task task) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			int count =sqlSession.update("net.bitacademy.java41.dao.TaskMapper.update",task);
			sqlSession.commit();
			return count;

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		
		} finally {
			try {sqlSession.close();} catch(Exception e) {}
		}
	}

	public int addTask(Task task) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			int count = sqlSession.insert(
					"net.bitacademy.java41.dao.TaskMapper.addTask", task);
			sqlSession.commit();
			
			return count;
			
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
			
		} finally {
			try {sqlSession.close();} catch(Exception e) {}
		}
	}

	public int deleteTask(int projectNo, int taskNo) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("projectNo", projectNo);
		paramMap.put("taskNo", taskNo);
		try {
			int count = sqlSession.delete(
					"net.bitacademy.java41.dao.TaskMapper.deleteTask", paramMap);
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
