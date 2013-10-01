package net.bitacademy.java41.controls.feed;

import java.util.List;

import javax.servlet.ServletContext;

import net.bitacademy.java41.services.FeedService;
import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.vo.Feed;
import net.bitacademy.java41.vo.Project;
import net.bitacademy.java41.vo.ProjectMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String add(Feed feed) throws Exception {

		feedService.addFeed(feed);
		
		return "redirect:../feed/list.do?projectNo=" + feed.getProjectNo();
	}
	
	@RequestMapping("/delete")
	public String delete(int projectNo, int feedNo) throws Exception {
		
		feedService.deleteFeed(projectNo, feedNo);

		return "redirect:../feed/list.do?projectNo=" + projectNo;
	}
			
	
}
