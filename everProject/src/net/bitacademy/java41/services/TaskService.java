package net.bitacademy.java41.services;

import java.sql.Connection;
import java.util.List;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.dao.TaskDao;
import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Task;

@Component
public class TaskService {
	TaskDao taskDao;
	DBConnectionPool conPool;
	
	public TaskService setTaskDao(TaskDao taskDao){
		this.taskDao = taskDao;
		return this;
	}
	public TaskService setDBConnectionPool(DBConnectionPool conPool) {
		this.conPool = conPool;
		return this;
	}

	public List<Task> getTaskList(int no) throws Exception {
		return taskDao.getTaskList(no);
	}
	
	public Task getTask(int projectNo, int taskNo)throws Exception{
		return taskDao.getTask(projectNo, taskNo);
	}
	
	public int taskUpdate(Task task) throws Exception {
		Connection con = conPool.getConnection();
		con.setAutoCommit(false);
		int count = 0;
		try {
			count = taskDao.update(task);
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
	public int taskAdd(Task task) throws Exception {
		Connection con = conPool.getConnection();
		con.setAutoCommit(false);
		int count = 0;
		try {
			count = taskDao.add(task);
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
	public int taskDelete(int projectNo, int taskNo) throws Exception {
		Connection con = conPool.getConnection();
		con.setAutoCommit(false);
		int count = 0;
		try {
			count = taskDao.delete(projectNo, taskNo);
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
