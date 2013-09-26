package net.bitacademy.java41.controls.project;

import java.sql.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/project/add.do")
public class ProjectAddControl implements PageControl {
	@Autowired ProjectService projectService;
	
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = (Map<String, String[]>) model.get("params");
		HttpSession session = (HttpSession) model.get("session");
		Member member = (Member) session.getAttribute("member");
		projectService.resisterProject( new Project()
													.setTitle(params.get("title")[0])
													.setContent(params.get("content")[0])
													.setStartDate( Date.valueOf(params.get("startDate")[0]) )
													.setEndDate( Date.valueOf(params.get("endDate")[0]) )
													.setTag(params.get("tag")[0])
													.setPlEmail(member.getEmail())
													.setPlName(member.getName())
													.setPlTel(member.getTel())
												);
		
		return "redirect:list.do";
	}

}
