package ourbox.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.EnterVO;
import ourbox.member.service.IMemberService;
import ourbox.member.service.MemberServiceImpl;

@WebServlet("/MemberInviteController")
public class MemberInviteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IMemberService memberService = MemberServiceImpl.getInstance();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String other_mem_id = request.getParameter("other");
		int room_seq = Integer.parseInt(request.getParameter("roomSeq"));
		
		EnterVO ev = new EnterVO();
		ev.setMem_id(other_mem_id);
		ev.setRoom_seq(room_seq);
				
		int cnt = memberService.enterGroup(ev); // enter생성 및 그룹 가입
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/ourbox/ourboxmember.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
