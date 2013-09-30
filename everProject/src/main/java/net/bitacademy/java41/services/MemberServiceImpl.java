package net.bitacademy.java41.services;

import java.util.HashMap;
import java.util.List;

import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.dao.MemberImageDao;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.dao.ProjectMemberDao;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Photo;
import net.bitacademy.java41.vo.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired MemberDao memberDao;
	@Autowired MemberImageDao memberImageDao;
	@Autowired ProjectDao projectDao;
	@Autowired ProjectMemberDao projectMemberDao;
	@Autowired PlatformTransactionManager txManager;


	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Throwable.class)
	public int signUp(Member member) throws Exception {
		int count = memberDao.addMember(member);
			
		return count;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Throwable.class)
	public int addMember(Member member) throws Exception {
		try {
			int count = memberDao.addMember(member);
			String[] photos = member.getPhotos();
			if (photos != null) {
				addPhotos(member, photos);
			}
			
			return count;
			
		} catch (Exception e) {
			throw e;
			
		}
	}

	public List<Member> getTotalMemberList() throws Exception {
		return memberDao.getMemberList();
	}

	public Member getMemberInfo(String email) throws Exception {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("email", email);
		paramMap.put("password", null);
		
		Member member = memberDao.getMember(paramMap);
		List<Photo> list = memberImageDao.listPhoto(email);
		String[] photos = null;
		if (list.size() > 0) {
			photos = new String[list.size()];
			int index = 0;
			for( Photo photo : list ) {
				photos[index++] = photo.getFilename();
			}
		}
		member.setPhotos(photos);
		
		return member;
	}

	public List<Project> getUserProjectList(String email) throws Exception {
		return projectDao.getUserProjectList(email);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Throwable.class)
	public int isChangePassword(String email, String oldPassword, String newPassword) throws Exception {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("email", email);
		paramMap.put("oldPassword", oldPassword);
		paramMap.put("newPassword", newPassword);	
		
		return memberDao.changePassword(paramMap);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Throwable.class)
	public int updateMemberInfo(Member member) throws Exception {
		try {
			int count = memberDao.updateMember(member);
			memberImageDao.deletePhoto(member.getEmail());
			String[] photos = member.getPhotos();
			
			addPhotos(member, photos);
			
			return count;
			
		} catch (Exception e) {
			throw e;
			
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Throwable.class)
	public int deleteMember(String email) throws Exception {
		try {
			memberImageDao.deletePhoto(email);
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("projectNo", 0);
			paramMap.put("email", email);
			
			projectMemberDao.deleteProjectMember(paramMap);
			
			int count = memberDao.deleteMember(email);
			
			return count;
			
		} catch (Exception e) {
			throw e;
			
		}
	}
	
	private void addPhotos(Member member, String[] photos) throws Exception {
		HashMap<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("email", member.getEmail());
		if (photos != null) {
			for( String path : photos ) {
				paramMap.put("path", path);
				memberImageDao.addPhoto(paramMap);
			}
		}
	}

}
