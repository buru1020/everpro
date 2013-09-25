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
	
	public int taskUpdate(Task task) throws Exception {
		int count = 0;
		try {
			count = taskDao.update(task);
			
		} catch (Exception e) {
			throw e;
			
		} finally {
		}
		
		return count;
	}
	public int taskAdd(Task task) throws Exception {
		int count = 0;
		try {
			count = taskDao.add(task);
			
		} catch (Exception e) {
			throw e;
			
		} finally {
		}
		
		return count;
	}
	public int taskDelete(int projectNo, int taskNo) throws Exception {
		int count = 0;
		try {
			count = taskDao.delete(projectNo, taskNo);
			
		} catch (Exception e) {
			throw e;
			
		} finally {
		}
		
		return count;
	}

}
