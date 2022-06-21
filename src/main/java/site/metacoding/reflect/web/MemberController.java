package site.metacoding.reflect.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import site.metacoding.reflect.config.MessageConverter;
import site.metacoding.reflect.config.ViewResolver;
import site.metacoding.reflect.config.web.RequestMapping;
import site.metacoding.reflect.domain.Member;
import site.metacoding.reflect.util.UtilsLog;

// API
public class MemberController {
	
	private static final String TAG = "MemberController : ";
	
	/*   GET     /join         */
	@RequestMapping("/join")
	public void join(Member member, HttpServletRequest request, HttpServletResponse response) {
		UtilsLog.getInstance().info(TAG, "join()");
		UtilsLog.getInstance().info(TAG, "Service가 호출되어 회원가입 완료되었습니다.");
		UtilsLog.getInstance().info(TAG, member.getUsername() +", "+member.getPassword());
		request.setAttribute("username", member.getUsername());
		ViewResolver.resolve("main.jsp", request, response);
	}
	
	/*   GET    /login         */	
	@RequestMapping(value = "/login")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		
		UtilsLog.getInstance().info(TAG, "login()");
		UtilsLog.getInstance().info(TAG, "Service가 호출되어 로그인이 완료되었습니다.");
		HttpSession session = request.getSession();
		session.setAttribute("principal", new Member(1, "ssar", "1234"));
		ViewResolver.resolve("main.jsp", request, response);
	}
	
	/*   GET    /findById         */
	@RequestMapping(value = "/findById")
	public void findById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UtilsLog.getInstance().info(TAG, "findById()");
		UtilsLog.getInstance().info(TAG, "Service가 호출되어 Member를 찾았습니다.");
		Member memberEntity = new Member(1, "ssar", "1234");
		MessageConverter.resolve(memberEntity, response);
	}
	
	
}
