package net.bitacademy.java41.dao;

import java.util.List;
import java.util.Map;

import net.bitacademy.java41.vo.Photo;

public interface MemberImageDao {
	
	int addPhoto(Map<String, String> paramMap) throws Exception;

	List<Photo> listPhoto(String email) throws Exception;
	
	int deletePhoto(String email) throws Exception;


}
