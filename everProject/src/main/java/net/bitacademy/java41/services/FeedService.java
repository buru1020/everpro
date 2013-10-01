package net.bitacademy.java41.services;

import java.util.List;

import net.bitacademy.java41.vo.Feed;


public interface FeedService {

	List<Feed> getFeedList(int projectNo) throws Exception;
//	
//	Task getTask(int projectNo, int taskNo)throws Exception;
//	
//	int updateTask(Task task) throws Exception;
//	
//	int addTask(Task task) throws Exception;
//	
//	int deleteTask(int projectNo, int taskNo) throws Exception;
	
}
