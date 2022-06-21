package site.metacoding.reflect.config;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import site.metacoding.reflect.config.web.RequestMapping;
import site.metacoding.reflect.domain.Member;
import site.metacoding.reflect.util.UtilsLog;
import site.metacoding.reflect.web.MemberController;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String TAG = "DispatcherServlet : ";

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UtilsLog.getInstance().info(TAG, "doDelete()");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberController memberController = new MemberController();
		
		UtilsLog.getInstance().info(TAG, "doGet()");
		UtilsLog.getInstance().info(TAG, req.getRequestURI());

		String identifier = req.getRequestURI();

		// 리플렉션 발동 /login
		Method[] methods = memberController.getClass().getDeclaredMethods();
		for(Method method : methods) {
			Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class);
			RequestMapping requestMapping = (RequestMapping) annotation;
			
			if(identifier.equals(requestMapping.value())) {
				
				try {
					Parameter[] params = method.getParameters();
					Object[] queue = new Object[params.length];
					
					for(int i=0; i< params.length; i++) {
						
						Class<?> cls = params[i].getType();

						if(cls == HttpServletRequest.class) {
							System.out.println("Request 찾음");
							queue[i] = req;
						}else if(cls == HttpServletResponse.class) {
							System.out.println("Response 찾음");
							queue[i] = resp;
						}else {
							Constructor<?> constructor = cls.getConstructor();
							queue[i] = constructor.newInstance();
							
							for (Method m : queue[i].getClass().getDeclaredMethods()) {
								System.out.println(req.getParameter("username"));
								System.out.println(req.getParameter("password"));
								System.out.println(m.getName());
							}
						}
						
						System.out.println("size : "+queue.length);
					}	
					
					method.invoke(memberController, queue);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			
		}
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UtilsLog.getInstance().info(TAG, "doPost()");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UtilsLog.getInstance().info(TAG, "doPut()");
	}

}
