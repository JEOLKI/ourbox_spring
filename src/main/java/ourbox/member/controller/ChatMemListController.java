package ourbox.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.MemberVO;
import ourbox.common.vo.PlanVO;
import ourbox.member.service.IMemberService;
import ourbox.member.service.MemberServiceImpl;
import ourbox.plan.service.IPlanService;
import ourbox.plan.service.PlanServiceImpl;

@WebServlet("/ChatMemListController")
public class ChatMemListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청 파라미터 정보 가져오기
		int roomseq = Integer.parseInt(request.getParameter("roomSeq"));
		// 2. 서비스 객체 생성하기
		IMemberService memberService = MemberServiceImpl.getInstance();

		List<MemberVO> list = memberService.chatMemProfile(roomseq);

		request.setAttribute("list", list);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/chat/chatprofile.jsp");

		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
