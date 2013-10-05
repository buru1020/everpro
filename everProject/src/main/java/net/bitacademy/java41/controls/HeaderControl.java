package net.bitacademy.java41.controls;

import javax.servlet.http.HttpSession;

import net.bitacademy.java41.vo.Feed;
import net.bitacademy.java41.vo.Member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HeaderControl {
	
	@RequestMapping("/header")
	public String execute(
			HttpSession session, Model model) throws Exception {
		
		model.addAttribute("member", (Member) session.getAttribute("member"));
		return "header";
	}

}
