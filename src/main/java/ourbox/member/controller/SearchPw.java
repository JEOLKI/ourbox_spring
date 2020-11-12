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

@WebServlet("/SearchPw")
public class SearchPw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String mememail = request.getParameter("email");
		String memname = request.getParameter("name");
		String memtel = request.getParameter("tel");
		
		IMemberService memService = MemberServiceImpl.getInstance();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(mememail);
		mv.setMem_name(memname);
		mv.setMem_tel(memtel);

		MemberVO vo = memService.findPw(mv);
		
		if(vo==null){

			request.setAttribute("message", "회원정보가 존재하지 않습니다.");
			request.getRequestDispatcher("/view/main/idchk.jsp").forward(request, response);

		} else {

			request.setAttribute("vo", vo);
			request.setAttribute("message1", "비밀번호는	");
			request.setAttribute("message2", "입니다.");

			request.getRequestDispatcher("/view/main/findPw.jsp").forward(request, response);
		}
	}

}
