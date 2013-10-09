package net.bitacademy.java41.controls;

import java.util.List;

import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("member")
public class HeaderControl {
	@Autowired ProjectService projectService;
	
	@RequestMapping("/header")
	public String header (
				Member member,
				Model model ) throws Exception {
		
		List<Project> myProjectList = projectService.getMyProjectList(member.getEmail());
		
		model.addAttribute("myProjectList", myProjectList);
		return "header";
	}

}
