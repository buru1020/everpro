package net.bitacademy.java41.controls.feed;

import java.util.List;

import javax.servlet.ServletContext;

import net.bitacademy.java41.services.FeedService;
import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.vo.Feed;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Project;
import net.bitacademy.java41.vo.ProjectMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/feed")
public class FeedControl {
	@Autowired ServletContext sc;
	@Autowired FeedService feedService;
	@Autowired ProjectService projectService;
	
	long curTime = 0;
	int count = 0;
	
	@RequestMapping("/list")
	public String list(
			int projectNo, 
			Model model) throws Exception {
		Project project = projectService.getProjectInfo(projectNo);
		List<ProjectMember> projectMemberList = projectService.getProjectMemberList(projectNo);
		List<Feed> feedList =  feedService.getFeedList(projectNo);
		
		model.addAttribute("projectMemberList", projectMemberList);
		model.addAttribute("feedList", feedList);
		model.addAttribute("project", project);
		return "feed/feedForm";
	}
	
	@RequestMapping("/add")
	public String add(
			Feed feed) throws Exception {

		feedService.addFeed(feed);
		
		return "redirect:../feed/list.do?projectNo=" + feed.getProjectNo();
	}
	
	@RequestMapping("/delete")
	public String delete(int projectNo, int feedNo) throws Exception {
		
			feedService.deleteFeed(projectNo, feedNo);

				return "redirect:../feed/list.do?projectNo=" + projectNo;
	}
	
	/*
	@RequestMapping("/view")
	public String view(
			int projectNo, 
			int taskNo, 
			Model model) throws Exception {
		Project project = projectService.getProjectInfo(projectNo);
		List<ProjectMember> projectMemberList = projectService.getProjectMemberList(projectNo);
		Task task = taskService.getTask(projectNo, taskNo);
		
		model.addAttribute("project", project);
		model.addAttribute("projectMemberList", projectMemberList);
		model.addAttribute("task", task);
		
		return "task/taskView";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String updateFrom(
			int projectNo, 
			int taskNo, 
			Model model ) throws Exception {
		Project project = projectService.getProjectInfo(projectNo);
		Task task = taskService.getTask(projectNo, taskNo);

		model.addAttribute("project", project);
		model.addAttribute("task", task);
		
		return "task/taskUpdateForm";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(
			Task task, 
			@RequestParam("uiProto") MultipartFile fileItem, 
			String tmpFilename, 
			Model model) throws Exception {
		String filename = null;
		if (fileItem.getSize() > 0) {
			filename = getNewFileName();
			String path = sc.getAttribute("rootRealPath") + "res/ui/" + filename;
			fileItem.transferTo( new File(path) );
		}
		if (filename != null) {
			task.setUiProtoUrl(filename);
		} else {
			task.setUiProtoUrl(tmpFilename);
		}
		
		String returnUrl = sc.getAttribute("rootPath") + "/main.do";
		String resutlStatus = "";
		int result = taskService.updateTask(task); 
		if (result > 0) {
			returnUrl = sc.getAttribute("rootPath") + "/task/view.do?projectNo=" + task.getProjectNo() + "&taskNo=" + task.getTaskNo();
			resutlStatus = "UPDATE_SUCCESS";
		} else {
			returnUrl = sc.getAttribute("rootPath") + "/task/update.do?projectNo=" + task.getProjectNo() + "&taskNo=" + task.getTaskNo();
			resutlStatus = "UPDATE_FAIL";
		}
		
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("resultStatus", resutlStatus);
		
		return "task/taskResult";
	}
	
*/		
	
}
