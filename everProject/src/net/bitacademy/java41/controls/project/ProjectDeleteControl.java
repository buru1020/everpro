package net.bitacademy.java41.controls.project;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.ProjectService;

@Component("/project/delete.do")
public class ProjectDeleteControl implements PageControl {
	ProjectService projectService;
	
	public ProjectDeleteControl setProjectService(ProjectService projectService) {
		this.projectService = projectService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = (Map<String, String[]>) model.get("params");
		
		int no = Integer.parseInt( params.get("no")[0] );
		
		HttpServletRequest request = (HttpServletRequest) model.get("request");
		String returnUrl = request.getServletContext().getContextPath() + "/project/list.do";
		String status = "";
		if (projectService.deleteProject(no) > 0) {
			status = "DELETE_SUCCESS";
		} else {
			status = "DELETE_FAIL";
		}
		
		model.put("returnUrl", returnUrl);
		model.put("status", status);
		return "/project/ProjectResult.jsp";
	}

	
	
}











