package net.bitacademy.java41.controls.member;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.MemberService;
import net.bitacademy.java41.vo.Member;

@Component("/member/signup.do")
public class SignupControl implements PageControl {
	String rootRealPath;
	MemberService memberService;
	int count = 0;
	long curTime = 0;
	

	public SignupControl setRootRealPath(String rootRealPath) {
		this.rootRealPath = rootRealPath;
		return this;
	}
	public SignupControl setMemberService(MemberService memberService) {
		this.memberService = memberService;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>) model.get("params");

		FileItem photo = (FileItem) params.get("photo");
		String filename = this.getNewFileName();
		String path = rootRealPath + "res/photo/" + filename;
		photo.write( new File(path) );
		
		Member member = new Member()
										.setEmail( (String) params.get("email") )
										.setName( (String) params.get("name") )
										.setPassword( (String) params.get("password") )
										.setTel( (String) params.get("tel") )
										.setPhotos( new String[] {filename} );
										
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
