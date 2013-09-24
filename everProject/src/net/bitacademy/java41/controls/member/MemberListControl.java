package net.bitacademy.java41.controls.member;

import java.util.Map;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.MemberService;

@Component("/member/list.do")
public class MemberListControl implements PageControl {
	MemberService memberService;
	
	public MemberListControl setMemberService(MemberService memberService) {
		this.memberService = memberService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		model.put("memberList", memberService.getTotalMemberList());
		return "/member/MemberList.jsp";
	}



	
}