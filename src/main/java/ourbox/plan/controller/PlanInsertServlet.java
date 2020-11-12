package ourbox.plan.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.SetCharacterEncodingFilter;

import ourbox.common.vo.PlanVO;
import ourbox.plan.service.IPlanService;
import ourbox.plan.service.PlanServiceImpl;

/**
 * Servlet implementation class PlanInsertServlet
 */
@WebServlet("/PlanInsertServlet")
public class PlanInsertServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int planprice = Integer.parseInt(request.getParameter("planprice"));
		String planContent = request.getParameter("planContent");
		int planstocap = Integer.parseInt(request.getParameter("planstocap"));
		String planname = request.getParameter("planname");
		
		IPlanService planservice = PlanServiceImpl.getInstance();
		PlanVO pv = new PlanVO();
		pv.setPlan_price(planprice);
		pv.setPlan_name(planname);
		pv.setPlan_Content(planContent);
		pv.setPlan_sto_cap(planstocap);
		
		int cnt = planservice.insertPlan(pv);
		List<PlanVO> list = planservice.planList();
		request.setAttribute("list", list);

		RequestDispatcher dispatcher = request.getRequestDispatcher("view/plan/planmanager2.jsp");

		dispatcher.forward(request, response);

	}

}
