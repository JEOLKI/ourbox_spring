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

@WebServlet("/MemberIdCheckController")
public class MemberIdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IMemberService memberService = MemberServiceImpl.getInstance();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id1 = request.getParameter("EmailID1");
		String id2 = request.getParameter("EmailDomain1");
		String id = id1+"@"+id2;
		MemberVO mv = memberService.IdCheck(id);
		String result = "";
		if(mv == null) { // 중복으로 존재한다면
			result = "사용가능";
		}else {
			System.out.println("존재한다!!" + mv.getMem_id());
			result = "중복";
		}
		request.setAttribute("result", result); // if에 따라서result값 바뀜
		 RequestDispatcher dispatcher = request.getRequestDispatcher("view/member/result.jsp");
		  dispatcher.forward(request, response);
	}


}
