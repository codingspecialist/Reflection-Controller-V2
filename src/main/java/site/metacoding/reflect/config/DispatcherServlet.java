package site.metacoding.reflect.config;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			UtilsLog.getInstance().info(TAG, method.getName());
			String idf = identifier.replace("/", "");
			if(idf.equals(method.getName())) {
				UtilsLog.getInstance().info(TAG, idf+" 메서드를 실행합니다");
				try {
					method.invoke(memberController, req, resp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
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
