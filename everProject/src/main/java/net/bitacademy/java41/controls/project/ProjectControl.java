package net.bitacademy.java41.controls.project;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.vo.JsonResult;
import net.bitacademy.java41.vo.LoginInfo;
import net.bitacademy.java41.vo.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/project")
public class ProjectControl {
	@Autowired ServletContext sc;
	@Autowired ProjectService projectService;
	
	
	@RequestMapping("/myProjectList")
	@ResponseBody
	public Object list(HttpSession session) throws Exception {
		JsonResult jsonResult = new JsonResult();
		try {
			LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
			
			jsonResult.setData(projectService.getMyProjectList(loginInfo.getEmail()));
			jsonResult.setStatus("success");
		} catch (Throwable e) {
			StringWriter out = new StringWriter();
			e.printStackTrace(new PrintWriter(out));
			
			jsonResult.setData(out.toString());
			jsonResult.setStatus("fail");
		}
		
		return jsonResult;
	}

	@RequestMapping("/list")
	@ResponseBody
	public Object list() throws Exception {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setData( projectService.getTotalProjectList() );
			jsonResult.setStatus("success");
			
		} catch (Throwable e) {
			StringWriter out = new StringWriter();
			e.printStackTrace(new PrintWriter(out));
			
			jsonResult.setData(out.toString());
			jsonResult.setStatus("fail");
		}
		
		return jsonResult;
	}
	
//	@RequestMapping(value="/add", method=RequestMethod.GET)
//	public String addForm() throws Exception {
//		return "project/projectAddForm";
//	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(
			Project project,
			HttpSession session ) throws Exception {
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
		project.setPlEmail(loginInfo.getEmail())
				.setPlName(loginInfo.getName())
				.setPlTel(loginInfo.getTel());
		projectService.resisterProject(project);
		
		return "redirect:list.do";
	}

	@RequestMapping("/view")
	@ResponseBody
	public Object view( int projectNo ) throws Exception {
		JsonResult jsonResult = new JsonResult();
		try {
//			Project project = projectService.getProjectInfo(projectNo);
//			List<ProjectMember> projectMemberList = projectService.getProjectMemberList(projectNo);
			
			jsonResult.setData( projectService.getProjectInfo(projectNo) );
			jsonResult.setStatus("success");
			
		} catch (Throwable e) {
			StringWriter out = new StringWriter();
			e.printStackTrace(new PrintWriter(out));
			
			jsonResult.setData(out.toString());
			jsonResult.setStatus("fail");
		}
		
		return jsonResult;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String updateForm(
			@RequestParam("no") int projectNo,
			Model model ) throws Exception {
		Project project = projectService.getProjectInfo(projectNo);
		model.addAttribute("project", project);
		
		return "project/projectUpdateForm";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(
			Project project,
			Model model ) throws Exception {
		String returnUrl = sc.getAttribute("rootPath") + "/main.do";
		String status = "";
		int result = projectService.projectUpdate(project);
		if (result > 0) {
			returnUrl = sc.getAttribute("rootPath") + "/project/view.do?no=" + project.getNo();
			status = "UPDATE_SUCCESS";
		} else {
			returnUrl = sc.getAttribute("rootPath") + "/project/update.do?no=" + project.getNo();
			status = "UPDATE_FAIL";
		}
		
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("status", status);
		
		return "project/projectResult";
	}
	
	
	@RequestMapping("/delete")
	public String delete(
			@RequestParam("no") int projectNo, 
			Model model ) throws Exception {
		String returnUrl = sc.getAttribute("rootPath") + "/project/list.do";
		String status = "";
		int result = projectService.deleteProject(projectNo);
		if (result > 0) {
			status = "DELETE_SUCCESS";
		} else {
			status = "DELETE_FAIL";
		}
		
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("status", status);
		
		return "project/projectResult";
	}
}