package ourbox.plan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.PlanVO;
import ourbox.plan.service.IPlanService;
import ourbox.plan.service.PlanServiceImpl;

/**
 * Servlet implementation class PlanManagerList
 */
@WebServlet("/PlanManagerList")
public class PlanManagerList extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IPlanService planservice = PlanServiceImpl.getInstance();

		List<PlanVO> planList = planservice.planList();

		request.setAttribute("planlist", planList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("view/plan/planmanager.jsp");

		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
