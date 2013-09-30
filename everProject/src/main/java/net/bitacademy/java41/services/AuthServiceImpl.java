package net.bitacademy.java41.services;

import java.util.HashMap;
import java.util.List;

import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.dao.MemberImageDao;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired MemberDao memberDao;
	@Autowired MemberImageDao memberImageDao; 


	public Member getUserInfo(String email, String password) throws Exception {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("email", email);
		paramMap.put("password", password);
		
		Member member = memberDao.getMember(paramMap);
		
		if (member != null) {
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
		}
		return member;
	}

}
