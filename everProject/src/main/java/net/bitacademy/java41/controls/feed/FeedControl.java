package net.bitacademy.java41.controls.feed;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

@Controller
@SessionAttributes("member")
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
			@ModelAttribute("member") Member sessionMember, 
			Model model) throws Exception {
		Project project = projectService.getProjectInfo(projectNo);
		List<ProjectMember> projectMemberList = projectService.getProjectMemberList(projectNo);
		boolean isProjectMember = projectService.isProjectMember(projectNo, sessionMember);
		List<Feed> feedList =  feedService.getFeedList(projectNo);
		String tmpContent = null;
		for( Feed feed : feedList ) {
			tmpContent = feed.getContent().replace("%nl&%", "<br>");
			feed.setContent(tmpContent);
		}
		model.addAttribute("project", project);
		model.addAttribute("projectMemberList", projectMemberList);
		model.addAttribute("isProjectMember", isProjectMember);
		model.addAttribute("feedList", feedList);
		
		return "feed/feedForm";
	}
	
	@RequestMapping("/listAll")
	public String getAllFeedList(Model model) throws Exception {
		List<Feed> feedList =  feedService.getFeedAllList();
		String tmpContent = null;
		for( Feed feed : feedList ) {
			tmpContent = feed.getContent().replace("%nl&%", "<br>");
			feed.setContent(tmpContent);
		}
		model.addAttribute("feedList", feedList);
		
		return "feed/feedForm";
	}
	
	@RequestMapping("/add")
	public String add(Feed feed,
					   @RequestParam("feedPhoto") MultipartFile feedUrl) throws Exception {
		
		String filename = null;
		if(feedUrl.getSize() > 0 && feedUrl != null){
			filename = this.getNewFileNames();
			String path = sc.getAttribute("rootRealPath") + "res/feed/" + filename;
			feedUrl.transferTo( new File (path) );
		}
		feed.setFeedUrl(filename);
		
		String content = feed.getContent().replace("\n", "%nl&%");
		System.out.println(content);
		feed.setContent(content);
		feedService.addFeed(feed);
		
		return "redirect:../feed/list.do?projectNo=" + feed.getProjectNo();
	}
	
	synchronized private String getNewFileNames() {
		long mills = System.currentTimeMillis();
		if(curTime != mills){
			curTime = mills;
			count = 0;
		}
		return "feed_" + mills + "_" + (++count);
	}

	@RequestMapping("/delete")
	public String delete(int projectNo, int feedNo) throws Exception {
		
		feedService.deleteFeed(projectNo, feedNo);

		return "redirect:../feed/list.do?projectNo=" + projectNo;
	}
			
	
}
