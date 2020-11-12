package ourbox.plan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import ourbox.common.vo.PlanUseVO;
import ourbox.plan.service.IPlanService;
import ourbox.plan.service.PlanServiceImpl;

/**
 * Servlet implementation class PlanSelectServlet
 */
@WebServlet("/PlanSelectServlet")
public class PlanSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlanSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memid = request.getParameter("mem_id");
		IPlanService planservice = PlanServiceImpl.getInstance();
		
		List<PlanUseVO> list = planservice.selectPlan(memid);
		
		request.setAttribute("result", list);
		
		request.getRequestDispatcher("view/plan/selectplan.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
