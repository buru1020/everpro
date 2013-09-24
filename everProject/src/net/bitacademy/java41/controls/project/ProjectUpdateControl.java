package net.bitacademy.java41.controls.project;

import java.sql.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.vo.ProjectEx;

@Component("/project/update.do")
public class ProjectUpdateControl implements PageControl {
	ProjectService projectService;
	
	public ProjectUpdateControl setProjectService(ProjectService projectService) {
		this.projectService = projectService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = (Map<String, String[]>) model.get("params");
		
		if (params.get("title") != null) {
			return update(model);
		} else {
			return form(model);
		}
	}
	
	private String form(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = (Map<String, String[]>) model.get("params");
		
		int no = Integer.parseInt( params.get("no")[0] );
		
		ProjectEx project = projectService.getProjectInfo(no);
		model.put("project", project);
		
		return "/project/ProjectUpdateForm.jsp";
	}
	
	private String update(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = (Map<String, String[]>) model.get("params");
		
		int no = Integer.parseInt( params.get("no")[0] );
		String title = params.get("title")[0];
		String content = params.get("content")[0];
		Date startDate = Date.valueOf( params.get("startDate")[0] );
		Date endDate = Date.valueOf( params.get("endDate")[0] );
		String tag = params.get("tag")[0];

		ProjectEx project = projectService.getProjectInfo(no);
		project.setTitle(!"".equals(title) ? title : project.getTitle());
		project.setContent(!"".equals(content) ? content : project.getContent());
		project.setStartDate(!"".equals(startDate) ? startDate : project.getStartDate());
		project.setEndDate(!"".equals(endDate) ? endDate : project.getEndDate());
		project.setTag(!"".equals(tag) ? tag : project.getTag());
		
		HttpServletRequest request = (HttpServletRequest) model.get("request");
		String returnUrl = request.getServletContext().getContextPath() + "/main.do";
		String status = "";
		if (projectService.projectUpdate(project) > 0) {
			returnUrl = request.getServletContext().getContextPath() + "/project/view.do?no=" + project.getNo();
			status = "UPDATE_SUCCESS";
		} else {
			returnUrl = request.getServletContext().getContextPath() + "/project/update.do?no=" + project.getNo();
			status = "UPDATE_FAIL";
		}
		
		model.put("returnUrl", returnUrl);
		model.put("status", status);
		return "/project/ProjectResult.jsp";
	}


}











