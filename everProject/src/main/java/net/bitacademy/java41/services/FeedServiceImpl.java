package net.bitacademy.java41.services;

import java.util.List;

import net.bitacademy.java41.dao.FeedDao;
import net.bitacademy.java41.vo.Feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedServiceImpl implements FeedService {
	@Autowired FeedDao feedDao;
	

	public List<Feed> getFeedList(int projectNo) throws Exception {
		return feedDao.getFeedList(projectNo);
	}
	/*
	public Task getTask(int projectNo, int taskNo)throws Exception{
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("projectNo", projectNo);
		paramMap.put("taskNo", taskNo);
		
		return taskDao.getTask(paramMap);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Throwable.class)
	public int updateTask(Task task) throws Exception {
		try {
			return taskDao.updateTask(task);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Throwable.class)
	public int addTask(Task task) throws Exception {
		try {
			return taskDao.addTask(task);
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Throwable.class)
	public int deleteTask(int projectNo, int taskNo) throws Exception {
		try {
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("projectNo", projectNo);
			paramMap.put("taskNo", taskNo);
			
			return taskDao.deleteTask(paramMap);
			
		} catch (Exception e) {
			throw e;
		}
	}
	*/
}
