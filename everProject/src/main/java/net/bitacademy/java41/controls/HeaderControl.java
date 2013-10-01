package net.bitacademy.java41.controls;

import javax.servlet.http.HttpSession;

import net.bitacademy.java41.vo.Member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/header")
public class HeaderControl {
	
	@RequestMapping
	public String header(
			HttpSession session, 
			Model model ) throws Exception {
		Member member = (Member) session.getAttribute("member");
		model.addAttribute("member", member);
		
		return "header";
	}

}
