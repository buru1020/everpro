package net.bitacademy.java41.controls.project;

import java.util.Map;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/project/list.do")
public class ProjectListControl implements PageControl  {
	@Autowired ProjectService projectService;
	

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		model.put("totalProjectList", projectService.getTotalProjectList());
		return "/project/ProjectList.jsp";
	}

}