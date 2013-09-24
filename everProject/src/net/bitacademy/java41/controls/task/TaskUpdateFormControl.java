package net.bitacademy.java41.controls.task;

import java.util.Map;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.services.TaskService;
import net.bitacademy.java41.vo.Project;
import net.bitacademy.java41.vo.Task;

@Component("/task/updateForm.do")
public class TaskUpdateFormControl implements PageControl {
	TaskService taskService;
	ProjectService projectService;
	
	public TaskUpdateFormControl setTaskService(TaskService taskService) {
		this.taskService = taskService;
		return this;
	}
	public TaskUpdateFormControl setProjectService(ProjectService projectService) {
		this.projectService = projectService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = (Map<String, String[]>) model.get("params");
		
		int projectNo = Integer.parseInt(params.get("projectNo")[0]);
		int taskNo = Integer.parseInt(params.get("taskNo")[0]);
		
		Project project = projectService.getProjectInfo(projectNo);
		Task task = taskService.getTask(projectNo, taskNo);

		model.put("project", project);
		model.put("task", task);
		
		return "/task/taskUpdateForm.jsp";
	}
	
}