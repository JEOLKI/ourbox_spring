package ourbox.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ourbox.common.vo.MemberVO;
import ourbox.member.service.IMemberService;
import ourbox.member.service.MemberServiceImpl;

@WebServlet("/MemberSendController")
public class MemberSendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IMemberService memService = MemberServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/member/chk.jsp");
    	
    	dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		String mem_id = request.getParameter("mem_id");
		
	    HttpSession httpSession = request.getSession(); // 새로운 변경 vo를 세션에 담는다.
	    MemberVO vo = memberService.detailMember(mem_id); // id값에 해당하는 memberVO 객체
		httpSession.setAttribute("vo", vo); // 세션담은 vo객체 유지
		
		if(vo !=null) { // id에 해당하는 객체 존재
			response.sendRedirect("view/member/chk.jsp");
		}else {
		}
		
	}

}
