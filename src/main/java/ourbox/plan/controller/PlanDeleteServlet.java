package ourbox.plan.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.PlanUseVO;
import ourbox.plan.service.IPlanService;
import ourbox.plan.service.PlanServiceImpl;

/**
 * Servlet implementation class PlanDeleteServlet
 */
@WebServlet("/PlanDeleteServlet")
public class PlanDeleteServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청 파라미터 정보 가져오기
		int planseq = Integer.parseInt(request.getParameter("planseq"));
		// 2. 서비스 객체 생성하기
		IPlanService planService = PlanServiceImpl.getInstance();
		// 3. 회원정보 수정
//		PlanUseVO vo = new PlanUseVO();
//		vo.setPlan_seq(planseq);

		int cnt = planService.deletePlan(planseq);

		String msg = "";
		if (cnt > 0) {
			msg = "성공";
//			request.getRequestDispatcher("/PlanManagerList").forward(request, response);
		} else {
//			response.sendRedirect("view/main/main.jsp");
			msg = "실패";
		}

		// 4. 목록 조회 화면으로 이동
		String redirectUrl = request.getContextPath() + "/PlanManagerList?msg=" + URLEncoder.encode(msg, "UTF-8");
		// redirect처리는 resp에서 호출
		response.sendRedirect(redirectUrl);
	}
}
