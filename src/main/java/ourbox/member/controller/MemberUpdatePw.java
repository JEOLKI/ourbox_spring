package ourbox.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.MemberVO;
import ourbox.member.service.IMemberService;
import ourbox.member.service.MemberServiceImpl;

@WebServlet("/MemberUpdatePw")
public class MemberUpdatePw extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memId = request.getParameter("email2");
		String newpw = request.getParameter("newpw");

		IMemberService memService = MemberServiceImpl.getInstance();

		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_pass(newpw);

		int cnt = memService.updatePw(mv);

		request.setAttribute("cnt", cnt);
		
		request.getRequestDispatcher("/view/main/test.jsp").forward(request, response);
		
	}

}
