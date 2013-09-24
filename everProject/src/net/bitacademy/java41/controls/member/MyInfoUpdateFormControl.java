package net.bitacademy.java41.controls.member;

import java.util.Map;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.controls.PageControl;

@Component("/member/myInfoUpdateForm.do")
public class MyInfoUpdateFormControl implements PageControl {
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		return "/member/myInfoUpdate.jsp";
	}

}
