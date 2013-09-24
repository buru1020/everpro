package net.bitacademy.java41.controls.task;

import java.io.File;
import java.sql.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.bitacademy.java41.annotation.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.TaskService;
import net.bitacademy.java41.vo.Task;

import org.apache.commons.fileupload.FileItem;

@Component("/task/update.do")
public class TaskUpdateControl implements PageControl {
	String rootRealPath;
	TaskService taskService;
	int count;
	long curTime;
	
	public void setRootRealPath(String rootRealPath) {
		this. rootRealPath = rootRealPath;
	}
	public TaskUpdateControl setTaskService(TaskService taskService) {
		this.taskService = taskService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>) model.get("params");
		
		FileItem fileItem = (FileItem) params.get("uiProto");
		String filename = null;
		if (fileItem.getSize() > 0) {
			filename = getNewFileName();
			String path = rootRealPath + "res/ui/" + filename;
			fileItem.write( new File(path) );
		}
		
		int projectNo = Integer.parseInt( (String) params.get("projectNo"));
		int taskNo = Integer.parseInt( (String) params.get("taskNo"));
		String title = (String) params.get("title");
		String content = (String) params.get("content");
		Date startDate = Date.valueOf( (String) params.get("startDate") );
		Date endDate = Date.valueOf( (String) params.get("endDate") );
		int status = Integer.parseInt( (String)params.get("status") );

		Task task = taskService.getTask(projectNo, taskNo);
		task.setTitle( !"".equals(title) ? title : task.getTitle() );
		task.setUiProtoUrl( filename != null ? filename : task.getUiProtoUrl() );
		task.setContent( !"".equals(content) ? content : task.getContent() );
		task.setStartDate( !"".equals(startDate) ? startDate : task.getStartDate() );
		task.setEndDate( !"".equals(endDate) ? endDate : task.getEndDate() );
		task.setStatus( !"".equals(status) ? status : task.getStatus() );
		
		HttpServletRequest request = (HttpServletRequest) model.get("request");
		String returnUrl = request.getServletContext().getContextPath() + "/main.do";
		String resutlStatus = "";
		if (taskService.taskUpdate(task) > 0) {
			returnUrl = request.getServletContext().getContextPath() + "/task/view.do?projectNo=" + projectNo + "&taskNo=" + taskNo;
			resutlStatus = "UPDATE_SUCCESS";
		} else {
			returnUrl = request.getServletContext().getContextPath() + "/task/update.do?projectNo=" + projectNo + "&taskNo=" + taskNo;
			resutlStatus = "UPDATE_FAIL";
		}
		
		model.put("returnUrl", returnUrl);
		model.put("resultStatus", resutlStatus);
		return "/task/taskResult.jsp";
	}
	
	synchronized
	private String getNewFileName() {
		long millis = System.currentTimeMillis();
		if (curTime != millis) {
			curTime = millis;
			count = 0;
		}
		return "uiproto_" + millis + "_" + (++count);
	}
	
}