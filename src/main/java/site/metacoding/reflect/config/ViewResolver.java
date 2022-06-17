package site.metacoding.reflect.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// View를 찾아주고, request 유지하는 전략
public class ViewResolver {

	public static void resolve(String path, HttpServletRequest request, HttpServletResponse response) {	
		try {
			RequestDispatcher dis = request.getRequestDispatcher(path);
			dis.forward(request, response);
		} catch (Exception e) {
			writeError(response);
		} 
	}
	
	private static void writeError(HttpServletResponse response) {
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<h1>파일을 찾을 수 없습니다</h1>");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
