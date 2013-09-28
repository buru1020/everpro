package net.bitacademy.java41.controls.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.services.AuthService;
import net.bitacademy.java41.vo.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
public class AuthControl {
	@Autowired AuthService authService;

	@RequestMapping("/loginForm")
	public String loginForm(
			boolean isSaveId,
			@CookieValue(value="email", required=false) String email,
			Model model ) {
		
		if (email != null) {
			isSaveId = true;
		}
		
		model.addAttribute("email", email);
		model.addAttribute("isSaveId", isSaveId);
		return "/auth/loginForm.jsp";
	}
	
	@RequestMapping("/login")
	public String login(
				String email,
				String password,
				String saveId, 
				HttpSession session,
				HttpServletResponse response) throws Exception {
		
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
			session.setAttribute("member", member);
			return "redirect:../main.do";
			
		} else {
			session.invalidate();
			return "/auth/loginFail.jsp";
		}
	
	}
	
	@RequestMapping("/logout")
	public String execute(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:loginForm.do";
	}

	
	

	


}
