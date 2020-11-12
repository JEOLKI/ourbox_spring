package ourbox.plan.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.MemberVO;
import ourbox.common.vo.PlanUseVO;
import ourbox.plan.service.IPlanService;
import ourbox.plan.service.PlanServiceImpl;

/**
 * Servlet implementation class PlanEndServlet
 */
@WebServlet("/PlanEndServlet")
public class PlanEndServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/plan/planchk.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 요청 파라미터 정보 가져오기
//		int plan_seq = Integer.parseInt(request.getParameter("plan_seq"));
//		int plan_price = Integer.parseInt(request.getParameter("plan_price"));
//		String plan_name = request.getParameter("plan_name");
//		String plan_content = request.getParameter("plan_content");
//		int use_seq = Integer.parseInt(request.getParameter("use_seq"));
//		String use_status = request.getParameter("use_status");
//		int plan_sto_cap = Integer.parseInt(request.getParameter("plan_sto_cap"));
//		String use_end = request.getParameter("use_end");
		String mem_id = request.getParameter("mem_id");
		// 2. 서비스 객체 생성하기
		IPlanService planService = PlanServiceImpl.getInstance();
		// 3. 회원정보 수정
		PlanUseVO vo = new PlanUseVO();
		vo.setMem_id(mem_id);
//		vo.setPlan_seq(plan_seq);
//		vo.setPlan_name(plan_name);
//		vo.setPlan_content(plan_content);
//		vo.setPlan_price(plan_price);
//		vo.setUse_status(use_status);
//		vo.setPlan_sto_cap(plan_sto_cap);
//		vo.setUse_end(use_end);
//		vo.setUse_seq(use_seq);

		int cnt = planService.endPlan(mem_id);

		String msg = "";
		if (cnt > 0) {
			msg = "성공";
			request.getRequestDispatcher("/PlanSelectServlet").forward(request, response);
		} else {
			response.sendRedirect("view/main/main.jsp");
			msg = "실패";
		}
	}

}
