package net.bitacademy.java41.controls.task;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/task/delete.do")
public class TaskDeleteControl implements PageControl {
	@Autowired TaskService taskService;
	
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = (Map<String, String[]>) model.get("params");
		
		int projectNo = Integer.parseInt(params.get("projectNo")[0]);
		int taskNo = Integer.parseInt(params.get("taskNo")[0]);
		
		HttpServletRequest request = (HttpServletRequest) model.get("request");
		String returnUrl = request.getServletContext().getContextPath() + "/main.do";
		String resutlStatus = "";
		if (taskService.taskDelete(projectNo, taskNo) > 0) {
			returnUrl = request.getServletContext().getContextPath() + "/task/list.do?projectNo=" + projectNo;
			resutlStatus = "DELETE_SUCCESS";
		} else {
			returnUrl = request.getServletContext().getContextPath() + "/task/view.do?projectNo=" + projectNo + "&taskNo=" + taskNo;
			resutlStatus = "DELETE_FAIL";
		}
		
		model.put("returnUrl", returnUrl);
		model.put("resultStatus", resutlStatus);
		return "/task/taskResult.jsp";
	}
	
}