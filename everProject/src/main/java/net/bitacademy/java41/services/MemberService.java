package net.bitacademy.java41.services;

import java.util.List;

import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Project;

public interface MemberService {

	int signUp(Member member) throws Exception;

	int addMember(Member member) throws Exception;
	
	List<Member> getTotalMemberList() throws Exception;

	Member getMemberInfo(String email) throws Exception;

	List<Project> getUserProjectList(String email) throws Exception;
	
	int isChangePassword(String email, String oldPassword, String newPassword) throws Exception;
	
	int updateMemberInfo(Member member) throws Exception;
	
	int deleteMember(String email) throws Exception;

}
