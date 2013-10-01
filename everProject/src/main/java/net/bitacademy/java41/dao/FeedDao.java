package net.bitacademy.java41.dao;

import java.util.List;

import net.bitacademy.java41.vo.Feed;


public interface FeedDao {

	List<Feed> getFeedList(int projectNo) throws Exception;
	
//	Task getTask(Map<String, Object> paramMap) throws Exception;
//
//	int updateTask(Task task) throws Exception;
//	
//	int addTask(Task task) throws Exception;
//
//	int deleteTask(Map<String, Object> paramMap) throws Exception;
	
	
}
