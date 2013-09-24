package net.bitacademy.java41.services;

import java.sql.Connection;
import java.util.List;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Photo;
import net.bitacademy.java41.vo.ProjectEx;

@Component
public class MemberService {
	DBConnectionPool conPool;
	MemberDao memberDao;
	ProjectDao projectDao;

	public MemberService setConPool(DBConnectionPool conPool) {
		this.conPool = conPool;
		return this;
	}
	public MemberService setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	public MemberService setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}

	public int signUp(Member member) throws Exception {
		Connection con = conPool.getConnection();
		con.setAutoCommit(false);
		int count = 0;
		try {
			count = memberDao.addMember(member);
			String[] photos = member.getPhotos();
			if (photos != null) {
				for( String path : photos ) {
					memberDao.addPhoto(member.getEmail(), path);
				}
			}
			con.commit();
			
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			conPool.returnConnection(con);
		}
		
		return count;
	}

	public int addMember(Member member) throws Exception {
		Connection con = conPool.getConnection();
		con.setAutoCommit(false);
		int count = 0;
		try {
			count = memberDao.add(member);
			con.commit();
			
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			conPool.returnConnection(con);
		}
		
		return count;
	}

	public List<Member> getTotalMemberList() throws Exception {
		return memberDao.getMemberList();
	}

	public Member getMemberInfo(String email) throws Exception {
		Member member = memberDao.getMember(email);
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
		Connection con = conPool.getConnection();
		con.setAutoCommit(false);
		int count = 0;
		try {
			count = memberDao.delete(email);
			con.commit();
			
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			conPool.returnConnection(con);
		}
		
		return count;
	}
	
	public int isChangePassword(String email, String oldPassword
			, String newPassword) throws Exception {
		Connection con = conPool.getConnection();
		con.setAutoCommit(false);
		int count = 0;
		try {
			count = memberDao.changePassword(email, oldPassword, newPassword);
			con.commit();
			
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			conPool.returnConnection(con);
		}
		
		return count;
	}
	
	public int updateMemberInfo(Member member) throws Exception {
		Connection con = conPool.getConnection();
		con.setAutoCommit(false);
		int count = 0;
		try {
			count = memberDao.update(member);
			String[] photos = member.getPhotos();
			if (photos != null) {
				for( String path : photos ) {
					memberDao.addPhoto(member.getEmail(), path);
				}
			}
			con.commit();
			
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			conPool.returnConnection(con);
		}
		
		return count;
		
	}
	
	public int myInfoChange(Member member) throws Exception {
		Connection con = conPool.getConnection();
		con.setAutoCommit(false);
		int count = 0;
		try {
			count = memberDao.myInfoChange(member);
			String[] photos = member.getPhotos();
			if (photos != null) {
				for( String path : photos ) {
					memberDao.addPhoto(member.getEmail(), path);
				}
			}
			con.commit();
			
		} catch (Exception e) {
			con.rollback();
			throw e;
			
		} finally {
			con.setAutoCommit(true);
			conPool.returnConnection(con);
		}
		
		return count;
	}


}
