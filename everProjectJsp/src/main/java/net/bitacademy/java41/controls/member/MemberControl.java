package net.bitacademy.java41.controls.member;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.services.AuthService;
import net.bitacademy.java41.services.MemberService;
import net.bitacademy.java41.vo.LoginInfo;
import net.bitacademy.java41.vo.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/member")
public class MemberControl {
	@Autowired ServletContext sc;
	@Autowired MemberService memberService;
	@Autowired AuthService authService;
	
	long curTime = 0;
	int count = 0;
	
	@RequestMapping("/list")
	public String list(Model model) throws Exception {
		List<Member> memberList = memberService.getTotalMemberList();
		model.addAttribute("memberList", memberList);
		
		return "member/memberList";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addForm() {
		return "member/memberAddForm";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(
			Member member, 
			MultipartFile photo ) throws Exception {
		String[] photos = null;
		if (photo.getSize() > 0) {
			String filename = this.getNewFileName();
			String path = sc.getAttribute("rootRealPath") + "res/photo/" + filename;
			photo.transferTo( new File(path) );
			photos = new String[]{ filename };
			
			member.setPhotos(photos);
		}
		memberService.addMember(member);
		return "redirect:list.do";
	}
	
	@RequestMapping("/view")
	public String view(String email, Model model) throws Exception {
		model.addAttribute("memberInfo", memberService.getMemberInfo(email));
		model.addAttribute("projectList", memberService.getUserProjectList(email));
		
		return "member/memberView";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String updateForm(
			String email, 
			Model model ) throws Exception {
		Member memberInfo = memberService.getMemberInfo(email);
		model.addAttribute("memberInfo", memberInfo);
		
		return "member/memberUpdateForm";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(
			@ModelAttribute("memberInfo") Member memberInfo,
			MultipartFile photo,
			String photoName,
			Model model ) throws Exception {
		
		if ( photo != null && photo.getSize() > 0 ) {
			String filename = this.getNewFileName();
			String path = sc.getAttribute("rootRealPath") + "res/photo/" + filename;
			photo.transferTo(new File(path));
			memberInfo.setPhotos(new String[]{filename});
		} else {
			memberInfo.setPhotos(new String[]{photoName});
		}
		
		String returnUrl = sc.getAttribute("rootPath") + "/main.do";
		String status = "";
		int result = memberService.updateMemberInfo(memberInfo);
		if (result > 0) {
			returnUrl = sc.getAttribute("rootPath") + "/member/view.do?email=" + memberInfo.getEmail();
			status = "UPDATE_SUCCESS";
		} else {
			returnUrl = sc.getAttribute("rootPath") + "/member/updateForm.do?email=" + memberInfo.getEmail();
			status = "UPDATE_FAIL";
		}
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("status", status);
		
		return "member/memberResult";
	}
	
	@RequestMapping("/delete")
	public String delete(String email, Model model) throws Exception {
		String returnUrl = sc.getAttribute("rootPath") + "/member/list.do";
		String status = "";
		int result = memberService.deleteMember(email);
		if (result > 0) {
			status = "DELETE_SUCCESS";
		} else {
			status = "DELETE_FAIL";
		}
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("status", status);
		
		return "member/memberResult";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signUpForm() throws Exception {
		return "member/signupForm";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signUp(
				Member member,
				HttpSession session, 
				SessionStatus status
				) throws Exception {
		
		LoginInfo loginInfo = memberService.signUp(member);
			
		if (loginInfo != null) {
			session.setAttribute("loginInfo", loginInfo);
			return "member/signupSuccess";
		} else {
			status.setComplete();
			return "member/signupFail";
		}
	}
	
	@RequestMapping(value="/updateMyInfo", method=RequestMethod.GET)
	public String updateMyInfoForm() throws Exception {
		return "member/myInfoUpdate";
	}
	
	@RequestMapping(value="/updateMyInfo", method=RequestMethod.POST)
	public String updateMyInfo(
			Member member,
			MultipartFile photo,
			HttpSession session,
			Model model ) throws Exception {
		
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
		String returnUrl = null;
		String status = null;
		if (member.getPassword().equals(authService.getCurPassword(loginInfo.getEmail()))) {
			String[] photos = null;
			if (photo.getSize() > 0) {
				String filename = this.getNewFileName();
				String path = sc.getAttribute("rootRealPath") + "res/photo/" + filename;
				photo.transferTo(new File(path));
				photos = new String[]{ filename };
			}
			if (photos != null) {
				member.setPhotos(photos);
			} else {
				member.setPhotos( new String[] {loginInfo.getPhoto()} );
			}
			
			loginInfo = memberService.updateMyInfo(member); 
			if (loginInfo != null) {
				session.setAttribute("loginInfo", loginInfo);
				returnUrl = sc.getAttribute("rootPath") + "/main.do";
				status = "UPDATE_SUCCESS";
			} else {
				returnUrl = sc.getAttribute("rootPath") + "/main.do";
				status = "UPDATE_FAIL";
			}
		} else {
			returnUrl = sc.getAttribute("rootPath") + "/member/updateMyInfo.do";
			status = "PASSWORD_WRONG";
		}
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("status", status);
		
		return "member/memberResult";
	}
	
	@RequestMapping(value="/passwordChange", method=RequestMethod.GET)
	protected String form() {
		return "member/passwordForm";
	}
	
	@RequestMapping(value="/passwordChange", method=RequestMethod.POST)
	public String changePassword(
			String email, 
			@RequestParam("password") String oldPassword,
			String newPassword,
			String newPassword2,
			HttpSession session,
			Model model ) throws Exception {
		if (newPassword.equals(newPassword2)) {
			int result = memberService.isChangePassword(email, oldPassword, newPassword);
			if (result > 0) {
				session.setAttribute("loginInfo", authService.getLoginInfo(email));
				model.addAttribute("status", "SUCCESS");
			} else {
				model.addAttribute("status", "OLD_PASSWORD_ERROR");
			}
		} else {
			model.addAttribute("status", "NEW_PASSWORD_ERROR");
		}
		
		return "member/passwordChangeResult";
			
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