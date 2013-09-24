package net.bitacademy.java41.controls.task;

import java.util.Map;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.vo.Project;

@Component("/task/addForm.do")
public class TaskAddFormControl implements PageControl {
	ProjectService projectService;
	
	public TaskAddFormControl setProjectService(ProjectService projectService) {
		this.projectService = projectService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = (Map<String, String[]>) model.get("params");
		
		int projectNo = Integer.parseInt(params.get("projectNo")[0]);
		
		Project project = projectService.getProjectInfo(projectNo);
		model.put("project", project);
		
		return "/task/taskAddForm.jsp";
	}
	
	
}