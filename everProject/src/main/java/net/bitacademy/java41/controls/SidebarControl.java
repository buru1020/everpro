package net.bitacademy.java41.controls;

import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.vo.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("member")
@RequestMapping("/sidebar")
public class SidebarControl {
	@Autowired ProjectService projectService;
	
	@RequestMapping
	public String sidebar(
			@ModelAttribute("member") Member member,
			Model model) throws Exception {
		model.addAttribute("projectList", projectService.getMyProjectList(member.getEmail()) );
		
		return "sidebar";
	}

}
