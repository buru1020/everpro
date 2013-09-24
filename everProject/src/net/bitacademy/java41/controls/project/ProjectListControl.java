package net.bitacademy.java41.controls.project;

import java.util.Map;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.ProjectService;

@Component("/project/list.do")
public class ProjectListControl implements PageControl  {
	ProjectService projectService;
	
	public ProjectListControl setProjectService(ProjectService projectService) {
		this.projectService = projectService;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		model.put("totalProjectList", projectService.getTotalProjectList());
		return "/project/ProjectList.jsp";
	}

}