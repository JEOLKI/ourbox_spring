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
 * Servlet implementation class PlanListServlet
 */
@WebServlet("/PlanListServlet")
public class PlanListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlanListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IPlanService planservice = PlanServiceImpl.getInstance();
		String memid = request.getParameter("mem_id");
		
		List<PlanVO> planList = planservice.planList();
		
		request.setAttribute("planlist", planList);
		request.setAttribute("memid", memid);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/plan/plan.jsp");
	
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	
	}


}
