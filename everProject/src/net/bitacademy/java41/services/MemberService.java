package net.bitacademy.java41.services;

import java.util.List;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Photo;
import net.bitacademy.java41.vo.ProjectEx;

@Component
public class MemberService {
	MemberDao memberDao;
	ProjectDao projectDao;

	public MemberService setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	public MemberService setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}

	public int signUp(Member member) throws Exception {
		int count = 0;
			count = memberDao.addMember(member);
//			String[] photos = member.getPhotos();
//			if (photos != null) {
//				for( String path : photos ) {
//					memberDao.addPhoto(member.getEmail(), path);
//				}
//			}
			
		return count;
	}

	public int addMember(Member member) throws Exception {
		int count = memberDao.addMember(member);
		
		return count;
	}

	public List<Member> getTotalMemberList() throws Exception {
		return memberDao.getMemberList();
	}

	public Member getMemberInfo(String email) throws Exception {
		Member member = memberDao.getMember(email, null);
		List<Photo> list = memberDao.listPhoto(email);
		String[] photos = null;
		if (list.size() > 0) {
			photos = new String[list.size()];
			int index = 0;
			for( Photo photo : list ) {
				photos[index] = photo.getFilename();
			}
		}
		member.setPhotos(photos);
		
		return member;
	}

	public List<ProjectEx> getUserProjectList(String email) throws Exception {
		return projectDao.getUserProjectList(email);
	}
	
	public int deleteMember(String email) throws Exception {
		memberDao.deleteProjectMember(email);
		int count = memberDao.deleteMember(email);
		
		return count;
	}
	
	public int isChangePassword(String email, String oldPassword, String newPassword) throws Exception {
		return memberDao.changePassword(email, oldPassword, newPassword);
	}
	
	public int updateMemberInfo(Member member) throws Exception {
		int count = memberDao.updateMember(member);
		String[] photos = member.getPhotos();
		if (photos != null) {
			for( String path : photos ) {
				memberDao.addPhoto(member.getEmail(), path);
			}
		}
		
		return count;
		
	}
	

}
