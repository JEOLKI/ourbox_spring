package ourbox.plan.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.UseVO;
import ourbox.plan.service.IPlanService;
import ourbox.plan.service.PlanServiceImpl;

/**
 * Servlet implementation class PlanPayMemberServlet
 */
@WebServlet("/PlanPayMemberServlet")
public class PlanPayMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlanPayMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int seq = Integer.parseInt(request.getParameter("planseq"));
    	String memid = request.getParameter("memid");
    	
    	IPlanService planService = PlanServiceImpl.getInstance();
    	
    	UseVO uv = new UseVO();
    	uv.setPlan_seq(seq);
    	uv.setMem_id(memid);
    	
    	int cnt = planService.payPlan(uv);
    	
    	request.setAttribute("cnt", cnt);
    	
    	request.getRequestDispatcher("view/plan/paychk.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
