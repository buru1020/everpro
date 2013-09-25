package net.bitacademy.java41.controls.auth;

import java.util.HashMap;
import java.util.Map;

import net.bitacademy.java41.controls.PageControl;

import org.springframework.stereotype.Component;


@Component("/auth/loginForm.do")
public class LoginFormControl implements PageControl {

	@Override
	public String execute(Map<String, Object> model) {
		HashMap<String, String> cookieMap =  (HashMap<String, String>) model.get("cookies");
		
		String email = "";
		boolean isSaveId = false;
		if (cookieMap.get("email") != null) {
			email = cookieMap.get("email");
			isSaveId = true;
		}
		
		model.put("email", email);
		model.put("isSaveId", isSaveId);
		return "/auth/LoginForm.jsp";
	}
	
	

	


}
