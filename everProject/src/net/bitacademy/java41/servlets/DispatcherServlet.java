package net.bitacademy.java41.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bitacademy.java41.controls.PageControl;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap<String, Object> model = new HashMap<String, Object>();
		
		try {
			HashMap<String, String> cookieMap = createCookieMap(request);
			model.put("cookies", cookieMap);
			if (ServletFileUpload.isMultipartContent(request)) {
				model.put("params", getMultipartParameterMap(request));
			} else {
				model.put("params", request.getParameterMap());
			}
			model.put("session", request.getSession());
			model.put("request", request);
			model.put("response", response);
		
			String viewUrl = null;
			PageControl control = (PageControl) request.getServletContext().getAttribute(request.getServletPath());
			
			if (control != null) {
				viewUrl = control.execute(model);
				transferControlDataFromRequest(request, model);
				processResult(request, response, viewUrl);
			} else {
				throw new Exception("[" + request.getServletPath() + "]해당 요청을 처리 할수 없습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			processResult(request, response, "/Error.jsp");
		}
		
	}

	private Map<String, Object> getMultipartParameterMap(HttpServletRequest request) throws Exception {
		HashMap<String, Object> params = new HashMap<String, Object>();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload uploadHandler = new ServletFileUpload(factory);
		List<FileItem> partList = uploadHandler.parseRequest(request);
		for( FileItem item : partList ) {
			if (item.isFormField()) {
				params.put(item.getFieldName(), item.getString("UTF-8"));
			} else {
				params.put(item.getFieldName(), item);
			}
		}
		
		return params;
	}

	private void processResult(HttpServletRequest request,
			HttpServletResponse response, String viewUrl) throws IOException, ServletException {
		if (viewUrl.startsWith("redirect:")) {
			response.sendRedirect(viewUrl.substring(9));
		} else {
			RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
			rd.forward(request, response);
		}
	}

	private void transferControlDataFromRequest(HttpServletRequest request, 
			HashMap<String, Object> model) {
		Set<String> keys = model.keySet();
		for( String key : keys ) {
			if (!key.equals("cookies") && !key.equals("params")) {
				request.setAttribute(key, model.get(key));
			}
		}
	}
	
	private HashMap<String, String> createCookieMap(HttpServletRequest request) {
		HashMap<String, String> cookieMap = new HashMap<String, String>();
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for( Cookie c : cookies ) {
				cookieMap.put(c.getName(), c.getValue());
			}
		}
		return cookieMap;
	}
	
}
