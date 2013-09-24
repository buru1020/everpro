package net.bitacademy.java41.listeners;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.bitacademy.java41.annotation.Component;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.reflections.Reflections;

public class ContextLoaderListener implements ServletContextListener {
	ServletContext ctx;
	Hashtable<String, Object> objTable = new Hashtable<String, Object>();
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ctx = event.getServletContext();
		
		objTable.put("rootPath", ctx.getContextPath());
		objTable.put("rootRealPath", ctx.getRealPath("/"));
		
		try {
//			loadProperties( ctx.getRealPath("/WEB-INF/db.properties") );
			prepareMybatis();
			prepareObjects( new File(ctx.getRealPath("/WEB-INF/classes")) );
			
			prepareDependency();
			saveToContext();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void loadProperties(String propPath) throws Exception {
		Properties props = new Properties();
		props.load( new FileReader(propPath) );
		
		Enumeration enums = props.keys();
		String key = null;
		while (enums.hasMoreElements()) {
			key = (String) enums.nextElement();
			objTable.put( key, ((String) props.get(key)).trim() );
		}
	}
	
	private void prepareMybatis() throws Exception {
		String mybatisConfig = "net/bitacademy/java41/dao/mybatis-config.xml";
		InputStream in =  Resources.getResourceAsStream(mybatisConfig);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		objTable.put("sqlSessionFactory", sqlSessionFactory);
	}
	
	private void saveToContext() {
		Enumeration<String> keyList = objTable.keys();
		String key = null;
		while (keyList.hasMoreElements()) {
			key = keyList.nextElement();
			ctx.setAttribute(key, objTable.get(key));
		}
	}

	private void prepareDependency() throws Exception {
		Collection<Object> objectList = objTable.values();
		for( Object obj : objectList ) {
			if (obj.getClass() != java.lang.String.class) {
				injectDependency(obj);
			}
		}
	}

	private void injectDependency(Object obj) throws Exception {
		System.out.println(obj.getClass().getName() + "-----------");
		Method[] methodList = obj.getClass().getMethods();
		for( Method m : methodList ) {
			callSetter(obj, m);
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void callSetter(Object instance, Method method) throws Exception {
		Class paramClass = null;
		Object paramObject = null;
		if (method.getName().startsWith("set")) {
			paramClass = method.getParameterTypes()[0];
			if (paramClass == java.lang.String.class) {
				String propertyName = extractPropertyName(method.getName());
				method.invoke(instance, objTable.get(propertyName));
			} else {
				paramObject = findInstanceByClass(paramClass);
				method.invoke(instance, paramObject);
			}
			System.out.println("......" + method.getName());
		}
	}

	private String extractPropertyName(String methodName) {
		return methodName.substring(3, 4).toLowerCase()	// set뒤 첫알바벳 소문자로 변경
				+ methodName.substring(4);	// set를 잘라냄
	}

	@SuppressWarnings("rawtypes")
	private Object findInstanceByClass(Class paramClass) {
		Collection<Object> instanceList = objTable.values();
		for( Object obj : instanceList ) {
			if (paramClass.isInstance(obj)) {
				return obj;
			}
		}
		return null;
	}
	
	/**
	 * 1) classpath를 뒤져서 net.bitacademy.java41 패키지를 찾는다. 
	 */
	@SuppressWarnings("rawtypes")
	private void prepareObjects(File file) throws Exception {
		Reflections reflector = new Reflections("net.bitacademy.java41");
		
		Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class);
		String key = null;
		for( Class clazz : list ) {
			key = getKeyFromClass(clazz);
			objTable.put(key, clazz.newInstance());
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String getKeyFromClass(Class clazz) throws Exception {
		Component compAnno =  (Component) clazz.getAnnotation(Component.class);
		if (compAnno != null) {
			String value = compAnno.value();
			if (value.equals("")) {
				String className = clazz.getSimpleName();	//getName()은 패키지 포함된 이름  getSimpleName()은 페키지 포함 안된 이름
				// ex) ProjectService => proejctService
				return className.substring(0, 1).toLowerCase() 
						+ className.substring(1); 
			} else {
				return value;
			}
		} else {
			return null;
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
}
