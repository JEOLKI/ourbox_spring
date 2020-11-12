package ourbox.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.MemberVO;
import ourbox.member.service.IMemberService;
import ourbox.member.service.MemberServiceImpl;

@WebServlet("/MemProfileController")
public class MemProfileController extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Url 변수 가져오기
		String mem_id = request.getParameter("memId");
		
		// 서비스 객체 가져오기
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		// 메소드 호출하기
		MemberVO member = memberService.detailMember(mem_id);
		
		// request에 저장하기
		request.setAttribute("member", member);
		
		// jsp로 전송하기
		request.getRequestDispatcher("view/ourbox/profile.jsp").forward(request, response);
		
	
	
	}


}
