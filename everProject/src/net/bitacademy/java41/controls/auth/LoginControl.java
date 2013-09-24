package net.bitacademy.java41.controls.auth;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.AuthService;
import net.bitacademy.java41.vo.Member;

@Component("/auth/login.do")
public class LoginControl implements PageControl {
	AuthService authService;

	public LoginControl setAuthService(AuthService authService) {
		this.authService = authService;
		return this;
	}


	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		HashMap<String, String[]> params =  (HashMap<String, String[]>) model.get("params");
		String email = params.get("email")[0];
		String password = params.get("password")[0];
		
		Member member = authService.getUserInfo(email, password);
		HttpSession session = (HttpSession) model.get("session");
		HttpServletRequest request = (HttpServletRequest) model.get("request");
		HttpServletResponse response = (HttpServletResponse) model.get("response");
		if(request.getParameter("saveId") != null) {
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
			return "/auth/LoginFail.jsp";
		}
	
	}
	
	
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		// 만약 쿠키 정보에 email의 값이 있다면, 기본적으로 그 값을 입력 상자에 출력한다.
//		Cookie[] cookies = request.getCookies();
//		String email = "";
//		boolean isSaveId = false;
//		if ( cookies != null) {
//			for(Cookie c : cookies) {
//				if (c.getName().equals("email")) {
//					email = c.getValue();
//					isSaveId = true;
//					break;
//				}
//			}
//		}
//		
//		request.setAttribute("email", email);
//		request.setAttribute("isSaveId", new Boolean(isSaveId));
//		
//		RequestDispatcher rd = request.getRequestDispatcher("/auth/LoginForm.jsp");
//		rd.forward(request, response);
//	}
//	
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		String email = request.getParameter("email");
//		String password = request.getParameter("password");
//		
//		MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
//		try {
//			Member member = memberDao.getMember(email, password);
//			HttpSession session = request.getSession();
//			
//			if(request.getParameter("saveId") != null) {
//				Cookie cookie = new Cookie("email", email);
//				cookie.setMaxAge(60 * 60 * 24); // 하루
//				// 컴퓨터를 껐다가 켜도 해당 시간 동안은 유효하다. 
//				// 유효하다는 의미는 서버에 해당 쿠키정보를 보낸다는 의미이다.
//				response.addCookie(cookie);
//			} else {
//				Cookie cookie = new Cookie("email", null);
//				cookie.setMaxAge(0); // 브라우저에 더이상 email 쿠키를 보내지 말 것을 요청.
//				// 쿠키의 생존 시간을 지정하지 않으면, 웹브라우저가 실행되는 동안만 유효하다.
//				response.addCookie(cookie);
//			}
//			
//			if (member != null) {
//				session.setAttribute("member", member);
//				response.sendRedirect("../main");
//				
//			} else {
//				session.invalidate();
//				RequestDispatcher rd = request.getRequestDispatcher("/auth/LoginFail.jsp");
//				rd.forward(request, response);
//			}
//	
//		} catch (Exception e) {
//			e.printStackTrace();
//			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
//			rd.forward(request, response);
//		}
//		
//	}


	


}
