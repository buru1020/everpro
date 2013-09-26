package net.bitacademy.java41.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ContextLoaderListener implements ServletContextListener {
	ServletContext servletContext;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		servletContext = event.getServletContext();
		
		servletContext.setAttribute("rootPath", servletContext.getContextPath());
		servletContext.setAttribute("rootRealPath", servletContext.getRealPath("/"));
		
		try {
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("net/bitacademy/java41/conf/application-context.xml");
			
			String[] beanNameList = applicationContext.getBeanDefinitionNames();
			for( String beanName : beanNameList ) {
				servletContext.setAttribute(beanName, applicationContext.getBean(beanName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
}
