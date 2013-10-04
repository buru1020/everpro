package net.bitacademy.java41.controls;

import javax.servlet.http.HttpSession;

import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.vo.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SidebarControl {
	@Autowired ProjectService projectService;
	
	@RequestMapping("/sidebar")
	public String execute(
			HttpSession session, Model model) throws Exception {
		Member member = (Member) session.getAttribute("member");
		model.addAttribute("projectList", 
				projectService.getMyProjectList(member.getEmail()));
		return "sidebar";
	}
}







