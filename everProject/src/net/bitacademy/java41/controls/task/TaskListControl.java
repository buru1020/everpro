package net.bitacademy.java41.controls.task;

import java.util.List;
import java.util.Map;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.services.TaskService;
import net.bitacademy.java41.vo.Project;
import net.bitacademy.java41.vo.Task;

@Component("/task/list.do")
public class TaskListControl implements PageControl {
	TaskService taskService;
	ProjectService projectService;
	
	public TaskListControl setTaskService(TaskService taskService) {
		this.taskService = taskService;
		return this;
	}
	public TaskListControl setProjectService(ProjectService projectService) {
		this.projectService = projectService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = (Map<String, String[]>) model.get("params");
	
		int projectNo = Integer.parseInt(params.get("projectNo")[0]);
		
		Project project = projectService.getProjectInfo(projectNo);
		List<Task> taskList = taskService.getTaskList(projectNo);
		
		model.put("project", project);
		model.put("taskList", taskList);
		
		return "/task/taskList.jsp";
	}
		
}
