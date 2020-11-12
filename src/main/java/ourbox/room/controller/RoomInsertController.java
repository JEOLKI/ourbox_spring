package ourbox.room.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.EnterVO;
import ourbox.common.vo.RoomVO;
import ourbox.room.service.IRoomService;
import ourbox.room.service.RoomServiceImpl;

@WebServlet("/RoomInsertController")
public class RoomInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IRoomService roomService;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/room/insertRoomForm.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String room_name = request.getParameter("room_name");
		String room_content = request.getParameter("room_content");
		String mem_id = request.getParameter("mem_id");
		roomService = RoomServiceImpl.getInstnace();
		
		RoomVO rv = new RoomVO();
		rv.setRoom_name(room_name);
		rv.setRoom_content(room_content);
		
		int cnt = roomService.insertRoom(rv);
		int room_seq = rv.getRoom_seq()+1; // seq 들고오기
		EnterVO ev = new EnterVO();
		ev.setMem_id(mem_id);
		ev.setRoom_seq(room_seq);
		
		roomService.insertEnter(ev);
		request.setAttribute("roomSeq", room_seq);
		
		String msg = "";
		if(cnt >0) {
			msg = "성공";
			request.getRequestDispatcher("view/ourbox/ourboxmember.jsp").forward(request, response);
		}else {
			msg = "실패";
		}
	}
}
