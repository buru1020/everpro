package net.bitacademy.java41.controls.member;

import java.util.Map;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/member/list.do")
public class MemberListControl implements PageControl {
	@Autowired MemberService memberService;
	
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		model.put("memberList", memberService.getTotalMemberList());
		return "/member/MemberList.jsp";
	}



	
}