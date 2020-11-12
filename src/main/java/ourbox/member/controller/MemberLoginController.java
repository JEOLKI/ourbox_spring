package ourbox.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ourbox.common.vo.MemberVO;
import ourbox.member.service.IMemberService;
import ourbox.member.service.MemberServiceImpl;

@WebServlet("/MemberLoginController")
public class MemberLoginController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      RequestDispatcher dispatcher = request.getRequestDispatcher("view/main/login.jsp");
      dispatcher.forward(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      String Id = request.getParameter("id"); // form 데이터에서 들고옴
      String pw = request.getParameter("pass"); // form 데이터에서 들고옴

      IMemberService memService = MemberServiceImpl.getInstance();

      if(Id.equals("ourbox")&&pw.equals("ourbox")||Id.equals("ㅐㅕㄱㅠㅐㅌ")&&pw.equals("ㅐㅕㄱㅠㅐㅌ")) { // 관리자 일 경우 1반환 
    	  response.sendRedirect("view/ourbox/ourboxmanager.jsp");
      }else {
			MemberVO mv = new MemberVO();
			mv.setMem_id(Id);
			mv.setMem_pass(pw);

			MemberVO vo = memService.loginMember(mv);

			HttpSession httpSession = request.getSession(); // session에 로그인값을 담는다.
			httpSession.setAttribute("vo", vo); // 아이디

			String msg = "";
			// 해당하는 사용자의 아이디와 비밀번호가 일치한다면 null이아닌 객체를 받아옴

			if (vo != null && vo.getMem_pass().equals(pw)) { // 올바르게 로그인
				response.sendRedirect("view/ourbox/ourboxmember.jsp");
			} else if (vo == null) { // 아이디 또는 비밀번호 틀림
				msg = "아이디와 패스워드를 확인해주세요.";
				request.setAttribute("msg", msg);
				RequestDispatcher dispatcher = request.getRequestDispatcher("view/main/login.jsp");
				dispatcher.forward(request, response);
			} else {
				msg = "탈퇴한 회원입니다.";
				request.setAttribute("msg", msg);
				RequestDispatcher dispatcher = request.getRequestDispatcher("view/main/login.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}