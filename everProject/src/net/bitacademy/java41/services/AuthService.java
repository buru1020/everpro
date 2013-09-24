package net.bitacademy.java41.services;

import java.util.List;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Photo;

@Component
public class AuthService {
	MemberDao memberDao;

	public AuthService setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	public Member getUserInfo(String email, String password) throws Exception {
		Member member = memberDao.getMember(email, password);
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

}
