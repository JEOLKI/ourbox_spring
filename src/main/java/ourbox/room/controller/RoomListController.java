package ourbox.room.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.RoomVO;
import ourbox.room.service.IRoomService;
import ourbox.room.service.RoomServiceImpl;

@WebServlet("/RoomListController")
public class RoomListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static IRoomService roomService;   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String mem_id = request.getParameter("memId"); // ourboxmember.jsp에서 넘겨준 로그인회원의 아이디 받아오기
		roomService = RoomServiceImpl.getInstnace();
		
		List<RoomVO> roomList = new ArrayList<RoomVO>();
		roomList = roomService.listRoom(mem_id); // mem_id로 참여중인 room의 정보list받아오기
		
		request.setAttribute("roomList", roomList);
		String msg = "";
		
		if(roomList == null) {
			msg = "실패";
			request.getRequestDispatcher("view/room/listRoom.jsp").forward(request, response);
		}else {
			msg = "성공";
			RequestDispatcher dispatcher = request.getRequestDispatcher("view/room/listRoom.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/room/listRoom.jsp");
		dispatcher.forward(request, response);
		
	}

}
