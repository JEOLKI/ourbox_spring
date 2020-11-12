package ourbox.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

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


@WebServlet("/MemberUpdateController")
public class MemberUpdateController extends HttpServlet {
	private IMemberService memberService = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/member/updateForm.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청 파라미터 정보 가져오기
		
		String mem_id = request.getParameter("mem_id");
		String mem_pass = request.getParameter("mem_pass1");
		String mem_name = request.getParameter("mem_name");
		String mem_nickname = request.getParameter("mem_nickname");
		String mem_bir = request.getParameter("mem_bir");
		String mem_tel = request.getParameter("mem_tel");
		String mem_zip = request.getParameter("mem_zip");
		String mem_addr1 = request.getParameter("mem_addr1");
		String mem_addr2 = request.getParameter("mem_addr2");
		String mem_status = request.getParameter("mem_status");
		String mem_profile = request.getParameter("mem_profile");
		// 2. 서비스 객체 생성하기

		// 3. 회원정보 수정
		MemberVO mv = new MemberVO();
	    mv.setMem_id(mem_id);
	    mv.setMem_pass(mem_pass);
	    mv.setMem_name(mem_name);
	    mv.setMem_nickname(mem_nickname);
	    mv.setMem_bir(mem_bir);
	    mv.setMem_tel(mem_tel);
	    mv.setMem_zip(mem_zip);
	    mv.setMem_addr1(mem_addr1);
	    mv.setMem_addr2(mem_addr2);
	    mv.setMem_status(mem_status);
	    mv.setMem_profile(mem_profile);
	    
		int cnt = memberService.updateMember(mv); // 회원정보 수정
		
		MemberVO vo = memberService.detailMember(mem_id); // id값에 해당하는 memberVO 객체
		
		HttpSession httpSession = request.getSession(); // 새로운 변경 vo를 세션에 담는다.
		httpSession.setAttribute("vo", vo);
		String msg = "";
		if (cnt > 0) {
			msg = "성공";
			request.getRequestDispatcher("view/ourbox/ourboxmember.jsp").forward(request, response);
		} else {
			msg = "실패";
		}

	}

}
