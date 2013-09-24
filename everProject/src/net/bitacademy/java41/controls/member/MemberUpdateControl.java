package net.bitacademy.java41.controls.member;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.MemberService;
import net.bitacademy.java41.vo.Member;

@Component("/member/update.do")
public class MemberUpdateControl implements PageControl {
	String rootRealPath;
	MemberService memberService;
	long curTime = 0;
	int count = 0;
	
	public void setRootRealPath(String rootRealPath) {
		this. rootRealPath = rootRealPath;
	}
	public MemberUpdateControl setMemberService(MemberService memberService) {
		this.memberService = memberService;
		return this;
	}
	
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>) model.get("params");
		
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
		
		Member memberInfo = memberService.getMemberInfo(email);
		memberInfo.setPassword(!"".equals(password) ? password : memberInfo.getPassword());
		memberInfo.setName(!"".equals(name) ? name : memberInfo.getName());
		memberInfo.setTel(!"".equals(tel) ? tel : memberInfo.getTel());
		memberInfo.setBlog(!"".equals(blog) ? blog : memberInfo.getBlog());
		memberInfo.setTag(!"".equals(tag) ? tag : memberInfo.getTag());
		memberInfo.setLevel(level != 0 ? level : memberInfo.getLevel()); 
		memberInfo.setPhotos( photos != null ? photos : memberInfo.getPhotos() );
		
		
		HttpServletRequest request = (HttpServletRequest) model.get("request");
		String returnUrl = request.getServletContext().getContextPath() + "/main";
		String status = "";
		if (memberService.updateMemberInfo(memberInfo) > 0) {
			returnUrl = request.getServletContext().getContextPath() + "/member/view.do?email=" + memberInfo.getEmail();
			status = "UPDATE_SUCCESS";
		} else {
			returnUrl = request.getServletContext().getContextPath() + "/member/updateForm.do?email=" + memberInfo.getEmail();
			status = "UPDATE_FAIL";
		}
		model.put("returnUrl", returnUrl);
		model.put("status", status);
		return "/member/MemberResult.jsp";
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











