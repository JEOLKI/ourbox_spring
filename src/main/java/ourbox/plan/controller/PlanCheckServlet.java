package ourbox.plan.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ourbox.common.vo.PlanUseVO;
import ourbox.plan.service.IPlanService;
import ourbox.plan.service.PlanServiceImpl;

/**
 * Servlet implementation class PlanCheckServlet
 */
@WebServlet("/PlanCheckServlet")
public class PlanCheckServlet extends HttpServlet {

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

		IPlanService planservice = PlanServiceImpl.getInstance();

		String mem_id = request.getParameter("mem_id");

		PlanUseVO pu = planservice.getPlan(mem_id); // id값에 해당하는 memberVO 객체
		request.setAttribute("result", pu);

		RequestDispatcher dispatcher = request.getRequestDispatcher("view/plan/planchk.jsp");
		
		dispatcher.forward(request, response);
	}

}
