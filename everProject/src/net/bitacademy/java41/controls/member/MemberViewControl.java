package net.bitacademy.java41.controls.member;

import java.util.Map;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.MemberService;

@Component("/member/view.do")
public class MemberViewControl implements PageControl {
	MemberService memberService;
	
	public MemberViewControl setMemberService(MemberService memberService) {
		this.memberService = memberService;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = (Map<String, String[]>) model.get("params");
		String email = params.get("email")[0];
		model.put("memberInfo", memberService.getMemberInfo(email));
		model.put("projectList", memberService.getUserProjectList(email));
		
		return "/member/MemberView.jsp";
	}




}