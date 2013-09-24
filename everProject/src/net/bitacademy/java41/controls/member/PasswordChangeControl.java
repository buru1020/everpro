package net.bitacademy.java41.controls.member;

import java.util.Map;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.MemberService;

@Component("/member/passwordChange.do")
public class PasswordChangeControl implements PageControl{
	MemberService memberService;
	
	public PasswordChangeControl setMemberService(MemberService memberService) {
		this.memberService = memberService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = (Map<String, String[]>) model.get("params");
		
		if (params.get("password") != null) {
			return changePassword(model);
		} else {
			return form();
		}
	}
	
	protected String form() {
		return "/member/passwordForm.jsp";
	}
	
	protected String changePassword(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = (Map<String, String[]>) model.get("params");
		
		String email = params.get("email")[0];
		String oldPassword = params.get("password")[0];
		String newPassword = params.get("newPassword")[0];
		String newPassword2 = params.get("newPassword2")[0];
		
		if (newPassword.equals(newPassword2)) {
			if (memberService.isChangePassword(email, oldPassword, newPassword) > 0) {
				model.put("status", "SUCCESS");
			} else {
				model.put("status", "OLD_PASSWORD_ERROR");
			}
		} else {
			model.put("status", "NEW_PASSWORD_ERROR");
		}
		
		return "/member/passwordChangeResult.jsp";
			
	}

	
}
