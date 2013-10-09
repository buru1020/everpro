package net.bitacademy.java41.controls.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import net.bitacademy.java41.services.AuthService;
import net.bitacademy.java41.vo.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


@Controller
@SessionAttributes("member")
@RequestMapping("/auth")
public class AuthControl {
	@Autowired AuthService authService;

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm(
			boolean isSaveId,
			@CookieValue(value="email", required=false) String email,
			Model model ) {
		
		if (email != null) {
			isSaveId = true;
		}
		
		model.addAttribute("email", email);
		model.addAttribute("isSaveId", isSaveId);
		return "auth/loginForm";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(
				String email,
				String password,
				String saveId, 
				HttpServletResponse response, 
				Model model, 
				SessionStatus status ) throws Exception {
		
		Member member = authService.getUserInfo(email, password);
		if(saveId != null) {
			Cookie cookie = new Cookie("email", email);
			cookie.setMaxAge(60 * 60 * 24); // 하루
			// 컴퓨터를 껐다가 켜도 해당 시간 동안은 유효하다. 
			// 유효하다는 의미는 서버에 해당 쿠키정보를 보낸다는 의미이다.
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("email", null);
			cookie.setMaxAge(0); // 브라우저에 더이상 email 쿠키를 보내지 말 것을 요청.
			// 쿠키의 생존 시간을 지정하지 않으면, 웹브라우저가 실행되는 동안만 유효하다.
			response.addCookie(cookie);
		}
			
		if (member != null) {
			model.addAttribute("member", member);
			return "redirect:../main.do";
			
		} else {
			status.setComplete();
			return "auth/loginFail";
		}
	
	}
	
	@RequestMapping("/logout")
	public String logout(SessionStatus status) throws Exception {
		status.setComplete();
		return "redirect:login.do";
	}

	
	

	


}
