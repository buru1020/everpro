package net.bitacademy.java41.controls.member;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.MemberService;
import net.bitacademy.java41.vo.Member;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/member/myInfoUpdate.do")
public class MyInfoUpdateControl implements PageControl {
	@Autowired MemberService memberService;

	String rootRealPath;
	long curTime = 0;
	int count = 0;
	
	public void setRootRealPath(String rootRealPath) {
		this. rootRealPath = rootRealPath;
	}
	

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>) model.get("params");
		
		HttpSession session = (HttpSession) model.get("session");
		Member sessionMember = (Member) session.getAttribute("member");
		Member member = null;
		int count = 0;
		if (params.get("password").equals(sessionMember.getPassword())) {
			FileItem photo = (FileItem) params.get("photo");
			String[] photos = null;
			if (photo.getSize() > 0) {
				String filename = this.getNewFileName();
				String path = rootRealPath + "res/photo/" + filename;
				photo.write(new File(path));
				photos = new String[]{ filename };
			}
			
			String email = (String) params.get("email");
			String password = (String) params.get("password");
			String name = (String) params.get("name");
			String tel = (String) params.get("tel");
			String blog = (String) params.get("blog");
			String tag = (String) params.get("tag");
			int level = Integer.parseInt( (String) params.get("level") );
			
			member = memberService.getMemberInfo(email);
			member.setPassword(!"".equals(password) ? password : member.getPassword());
			member.setName(!"".equals(name) ? name : member.getName());
			member.setTel(!"".equals(tel) ? tel : member.getTel());
			member.setBlog(!"".equals(blog) ? blog : member.getBlog());
			member.setTag(!"".equals(tag) ? tag : member.getTag());
			member.setLevel(level != 0 ? level : member.getLevel()); 
			member.setPhotos( photos );
			
			count = memberService.updateMemberInfo(member);
		}
		if (count > 0) {
			session.setAttribute("member", member);
			return "/member/myInfoUpdateSuccess.jsp";
		} else {
			return "/member/myInfoUpdateFail.jsp";
		}
	}
	
	synchronized
	private String getNewFileName() {
		long millis = System.currentTimeMillis();
		if (curTime != millis) {
			curTime = millis;
			count = 0;
		}
		return "uiproto_" + millis + "_" + (++count);
	}

}
