package net.bitacademy.java41.controls.member;

import java.util.Map;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.MemberService;

@Component("/member/updateForm.do")
public class MemberUpdateFormControl implements PageControl {
	MemberService memberService;
	
	public MemberUpdateFormControl setMemberService(MemberService memberService) {
		this.memberService = memberService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = (Map<String, String[]>) model.get("params");
		
		String email = params.get("email")[0];
		model.put("memberInfo", memberService.getMemberInfo(email));
		
		return "/member/MemberUpdateForm.jsp";
	}
	
}
