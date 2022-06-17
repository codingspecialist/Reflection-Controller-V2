package site.metacoding.reflect.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import site.metacoding.reflect.domain.Member;

public class MessageConverter {

	public static void resolve(Object data, HttpServletResponse response) {
		try {
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			String responseBody = gson.toJson(data);
			out.println(responseBody);
			out.flush();
		} catch (Exception e) {
			writeError(response);
		}

	}

	private static void writeError(HttpServletResponse response) {
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<h1>JSON 변환에 실패하였습니다.</h1>");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
