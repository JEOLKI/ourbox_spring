package ourbox.room.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.EnterVO;
import ourbox.member.service.IMemberService;
import ourbox.member.service.MemberServiceImpl;

@WebServlet("/RoomInviteController")
public class RoomInviteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IMemberService memSerivce = MemberServiceImpl.getInstance();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int room_seq = Integer.parseInt(request.getParameter("roomSeq"));
		String other_id = request.getParameter("otherId"); // 초대할 사람
		String mem_id = request.getParameter("memId"); // 이용자
		
		String msg ="";
		EnterVO ev = new EnterVO();
		ev.setMem_id(other_id);
		ev.setRoom_seq(room_seq);
		int cnt = memSerivce.enterGroup(ev);// 상대방 그룹추가
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
