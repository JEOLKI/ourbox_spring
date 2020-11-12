package ourbox.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.ls.LSInput;

import ourbox.common.vo.EnterVO;
import ourbox.common.vo.MemberVO;
import ourbox.member.service.IMemberService;
import ourbox.member.service.MemberServiceImpl;

@WebServlet("/MemberOtherListController")
public class MemberOtherListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IMemberService memberService = MemberServiceImpl.getInstance();
	   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String other_mem_id = request.getParameter("other"); // 다른사용자 
		int room_seq = Integer.parseInt(request.getParameter("roomSeq"));
		String mem_id = request.getParameter("memId");
		
		EnterVO ev = new EnterVO();
		ev.setMem_id(other_mem_id); // 검색할 키워드
		ev.setRoom_seq(room_seq); // 검색할 조건에 추가
		
		List<MemberVO> otherList = memberService.searchMember(ev);
		
		request.setAttribute("otherList", otherList);
		request.setAttribute("memId", mem_id);
		request.setAttribute("roomSeq", room_seq);
		
		request.getRequestDispatcher("view/room/invite.jsp").forward(request, response);
		
	}
}
