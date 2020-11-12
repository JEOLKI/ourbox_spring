package ourbox.room.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.EnterVO;
import ourbox.room.service.IRoomService;
import ourbox.room.service.RoomServiceImpl;

@WebServlet("/RoomDeleteController")
public class RoomDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IRoomService roomService = RoomServiceImpl.getInstnace();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int room_seq = Integer.parseInt(request.getParameter("roomSeq"));
		String mem_id = request.getParameter("memId");
		
		EnterVO ev = new EnterVO();
		
		ev.setRoom_seq(room_seq);
		ev.setMem_id(mem_id);
		
		roomService.deleteRoom(ev);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
