package ourbox.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.MemberVO;
import ourbox.member.service.IMemberService;
import ourbox.member.service.MemberServiceImpl;

@WebServlet("/MemberSelectServlet")
public class MemberSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberSelectServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memId = request.getParameter("memId");
		IMemberService memberservice = MemberServiceImpl.getInstance();
		
		MemberVO mv = memberservice.detailMember(memId);
		
		request.setAttribute("mv", mv);

		RequestDispatcher dispatcher = request.getRequestDispatcher("view/main/select.jsp");

		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
