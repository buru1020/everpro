package net.bitacademy.java41.services;

import net.bitacademy.java41.vo.LoginInfo;
import net.bitacademy.java41.vo.Member;

public interface AuthService {

	Member getUserInfo(String email, String password) throws Exception;

	LoginInfo getLoginInfo(String email, String password) throws Exception;
	
	LoginInfo getLoginInfo(String email) throws Exception;

	String getCurPassword(String email) throws Exception;

	

}
