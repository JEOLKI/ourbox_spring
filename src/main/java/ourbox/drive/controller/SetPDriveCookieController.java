package ourbox.drive.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SetPDriveCookieController")
public class SetPDriveCookieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setPCookie(req,resp);
		int result = 0;
		if((req.getParameter("drive_seq") != null) && (req.getParameter("drive_path") != null)) result = 1;
		
			
		req.setAttribute("result", result);
		req.getRequestDispatcher("view/common/result.jsp").forward(req, resp);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	
	
	/**
	 * 쿠키 생성용 메서드
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void setPCookie(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		/*
		 쿠키 정보를 설정하는 방법 
		 
		 1. 쿠키 객체를 생성한다. 사용불가문자 (공백, []()=,"/?@:; )
		 	Cookie cookie = new Cookie("키값", "value값");
		 							// 키캆 = 쿠키이름
		 
		 2. 쿠키 최대 지속시간을 설정한다. (초단위) => 지정하지 않으면 웹브라우저를 종료할 때 쿠키를 함께 삭제한다.
		 	cookie.setMaxAge(60*60*24);		// 24시간
		 
		 3. 응답 헤더에 쿠키 객체를 추가한다.
		 	response.addCookie(cookie);
		 	
		=> 출력버퍼가 플러시된 이후에는 쿠키를 추가할 수가 없다. (응답헤더를 통해서 웹브라우저에 전달하기 때문에..)
		
		 */
		
		// 쿠키 생성하기
		Cookie drive_seq = new Cookie("drive_seq", req.getParameter("drive_seq"));
		Cookie drive_path = new Cookie("drive_path", req.getParameter("drive_path"));

		// 쿠키 소멸시간 설정(초단위)
		drive_seq.setMaxAge(60*60*24);	// 1일
		drive_path.setMaxAge(60*60*24);	// 1일
		
		// 응답헤더에 쿠키추가하기
		resp.addCookie(drive_seq);
		resp.addCookie(drive_path);
		
		// 응답헤터데 인토딩 및 content type 설정
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		
		
	}
}
