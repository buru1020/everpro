package net.bitacademy.java41.services;

import java.util.List;

import net.bitacademy.java41.dao.TaskDao;
import net.bitacademy.java41.vo.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskService {
	@Autowired TaskDao taskDao;
	

	public List<Task> getTaskList(int no) throws Exception {
		return taskDao.getTaskList(no);
	}
	
	public Task getTask(int projectNo, int taskNo)throws Exception{
		return taskDao.getTask(projectNo, taskNo);
	}
	
	public int updateTask(Task task) throws Exception {
		int count = 0;
		try {
			count = taskDao.updateTask(task);
			
		} catch (Exception e) {
			throw e;
			
		} finally {
		}
		
		return count;
	}
	public int addTask(Task task) throws Exception {
		int count = 0;
		try {
			count = taskDao.addTask(task);
			
		} catch (Exception e) {
			throw e;
			
		} finally {
		}
		
		return count;
	}
	
	public int deleteTask(int projectNo, int taskNo) throws Exception {
		int count = 0;
		try {
			count = taskDao.deleteTask(projectNo, taskNo);
			
		} catch (Exception e) {
			throw e;
		}
		
		return count;
	}

}
