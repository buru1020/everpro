package net.bitacademy.java41.controls.member;

import java.util.Map;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/member/view.do")
public class MemberViewControl implements PageControl {
	@Autowired MemberService memberService;
	

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