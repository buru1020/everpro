package net.bitacademy.java41.services;

import java.util.List;

import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.dao.MemberImageDao;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.dao.ProjectMemberDao;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Photo;
import net.bitacademy.java41.vo.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
public class MemberService {
	@Autowired MemberDao memberDao;
	@Autowired MemberImageDao memberImageDao;
	@Autowired ProjectDao projectDao;
	@Autowired ProjectMemberDao projectMemberDao;
	@Autowired PlatformTransactionManager txManager;


	public int signUp(Member member) throws Exception {
		int count = 0;
		count = memberDao.addMember(member);
			
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
	
	public int isChangePassword(String email, String oldPassword, String newPassword) throws Exception {
		return memberDao.changePassword(email, oldPassword, newPassword);
	}
	
	public int updateMemberInfo(Member member) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("getMemberInfoTx");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus txStatus = txManager.getTransaction(def);
		try {
			int count = memberDao.updateMember(member);
			String[] photos = member.getPhotos();
			if (photos != null) {
				for( String path : photos ) {
					memberImageDao.addPhoto(member.getEmail(), path);
				}
			}
			
			txManager.commit(txStatus);
			
			return count;
			
		} catch (Exception e) {
			txManager.rollback(txStatus);
			throw e;
			
		}
	}
	
	public int deleteMember(String email) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("getMemberInfoTx");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus txStatus = txManager.getTransaction(def);
		try {
			memberImageDao.deletePhoto(email);
			projectMemberDao.deleteProjectMember(0, email);
			int count = memberDao.deleteMember(email);
			
			txManager.commit(txStatus);
			
			return count;
			
		} catch (Exception e) {
			txManager.rollback(txStatus);
			throw e;
			
		}
	}
	

}
