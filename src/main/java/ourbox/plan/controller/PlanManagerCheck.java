package ourbox.plan.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.PlanUseVO;
import ourbox.common.vo.PlanVO;
import ourbox.plan.service.IPlanService;
import ourbox.plan.service.PlanServiceImpl;

/**
 * Servlet implementation class PlanManagerCheck
 */
@WebServlet("/PlanManagerCheck")
public class PlanManagerCheck extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/plan/planmanager.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IPlanService planservice = PlanServiceImpl.getInstance();

		int planseq = Integer.parseInt(request.getParameter("planseq"));

		PlanVO pu = planservice.getPlanManager(planseq);
		request.setAttribute("result", pu);

		RequestDispatcher dispatcher = request.getRequestDispatcher("view/plan/planmanagerchk.jsp");
		
		dispatcher.forward(request, response);
	}

}
