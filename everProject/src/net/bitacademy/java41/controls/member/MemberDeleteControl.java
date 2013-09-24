package net.bitacademy.java41.controls.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.MemberService;

@Component("/member/delete.do")
public class MemberDeleteControl implements PageControl {
	MemberService memberService;
	
	public MemberDeleteControl setMemberService(MemberService memberService) {
		this.memberService = memberService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = (Map<String, String[]>) model.get("params");
		String email = params.get("email")[0];
		
		HttpServletRequest request = (HttpServletRequest) model.get("request");
		String returnUrl = request.getServletContext().getContextPath() + "/member/list.do";
		String status = "";
		if (memberService.deleteMember(email) > 0) {
			status = "DELETE_SUCCESS";
		} else {
			status = "DELETE_FAIL";
		}
		model.put("returnUrl", returnUrl);
		model.put("status", status);
		
		return "/member/MemberResult.jsp";
	}
	
}











