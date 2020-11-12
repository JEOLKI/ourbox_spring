package ourbox.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.MemberVO;
import ourbox.member.service.IMemberService;
import ourbox.member.service.MemberServiceImpl;

@WebServlet("/MemberInsertController")
public class MemberInsertController extends HttpServlet {
	   
    
	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  RequestDispatcher dispatcher = request.getRequestDispatcher("view/member/join.jsp");
		  dispatcher.forward(request, response);
	   }

	   @Override
	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   response.setCharacterEncoding("UTF-8");
		   
	      // 1. 요청파라미터 정보 가져오기
	      String memId1 = request.getParameter("EmailID1"); // form 데이터에서 들고옴
	      String memId2 = request.getParameter("EmailDomain1"); // form 데이터에서 들고옴
	      String memPass1 = request.getParameter("mem_pass1");
	      String memPass2 = request.getParameter("mem_pass2");
	      String memNick = request.getParameter("mem_nickname");
	      String memName = request.getParameter("mem_name");
	      String memBir = request.getParameter("mem_bir");
	      String memTel = request.getParameter("mem_hp");
	      String memZip = request.getParameter("mem_zip");
	      String memAdd1 = request.getParameter("mem_add1");
	      String memAdd2 = request.getParameter("mem_add2");

	      // 2. 서비스 객체 생성하기
	      IMemberService memService = MemberServiceImpl.getInstance();

	      // 3. 회원 정보등록
	      MemberVO mv = new MemberVO();
	      String memId = memId1.trim()+ "@" +memId2.trim();
	      mv.setMem_id(memId);
	      mv.setMem_pass(memPass1.trim());
	      mv.setMem_nickname(memNick.trim());
	      mv.setMem_name(memName.trim());
	      mv.setMem_bir(memBir.trim());
	      mv.setMem_tel(memTel.trim());
	      mv.setMem_zip(memZip.trim());
	      mv.setMem_addr1(memAdd1.trim());
	      mv.setMem_addr2(memAdd2.trim());
	      
	      List<MemberVO> memList = memService.memberList();
	      request.setAttribute("memList", memList); // 모든 회원list

	      int cnt = memService.insertMember(mv); // 회원등록
	      String msg2 = "";
	      if (cnt > 0) { // DB에 회원 등록 성공
	         msg2 = "성공";
			request.getRequestDispatcher("view/main/login.jsp").forward(request, response);
		} else { // DB에 회원 등록 실패
			msg2 = "실패";
			request.getRequestDispatcher("/ourbox/MemberInsertController").forward(request, response);
		}
	}
}
