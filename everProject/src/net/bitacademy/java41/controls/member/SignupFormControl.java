package net.bitacademy.java41.controls.member;

import java.util.Map;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.controls.PageControl;

@Component("/member/signupForm.do")
public class SignupFormControl implements PageControl {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		return "/member/SignupForm.jsp";
	}

}
