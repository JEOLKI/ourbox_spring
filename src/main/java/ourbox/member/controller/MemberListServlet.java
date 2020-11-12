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

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/MemberListServlet")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IMemberService memberService = MemberServiceImpl.getInstance();
		IPlanService planservice = PlanServiceImpl.getInstance();
		
		List<MemberVO> memList = memberService.memberList();
		List<PlanVO> planList = planservice.planList();
		
		request.setAttribute("planlist", planList);
		request.setAttribute("memList", memList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/member/memberlist.jsp");

		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
