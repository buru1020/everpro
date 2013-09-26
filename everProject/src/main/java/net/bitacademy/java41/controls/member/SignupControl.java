package net.bitacademy.java41.controls.member;

import java.util.Map;

import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.MemberService;
import net.bitacademy.java41.vo.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/member/signup.do")
public class SignupControl implements PageControl {
	@Autowired MemberService memberService;

	int count = 0;
	long curTime = 0;


	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = (Map<String, String[]>) model.get("params");

//		FileItem photo = (FileItem) params.get("photo");
//		String filename = null;
//		if (photo != null) {
//			filename = this.getNewFileName();
//			String path = model.get("rootRealPath") + "res/photo/" + filename;
//			photo.write( new File(path) );
//		}
		
		Member member = new Member()
										.setEmail( (String) params.get("email")[0] )
										.setName( (String) params.get("name")[0] )
										.setPassword( (String) params.get("password")[0] )
										.setTel( (String) params.get("tel")[0] );
//										.setPhotos( new String[] {filename} );
										
		int count = memberService.signUp(member);
		HttpSession session = (HttpSession) model.get("session");
		if (count > 0) {
			session.setAttribute("member", member);
			return "/member/SignupSuccess.jsp";
		} else {
			session.invalidate();
			return "/member/SignupFail.jsp";
		}
		
	}

	synchronized
	private String getNewFileName() {
		long millis = System.currentTimeMillis();
		if (curTime != millis) {
			curTime = millis;
			count = 0;
		}
		return "member_" + millis + "_" + (++count);
	}


}
