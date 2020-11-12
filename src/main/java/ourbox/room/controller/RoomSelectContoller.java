package ourbox.room.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.RoomVO;
import ourbox.room.service.IRoomService;
import ourbox.room.service.RoomServiceImpl;

@WebServlet("/RoomSelectContoller")
public class RoomSelectContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IRoomService roomService;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String room_seq = request.getParameter("roomSeq");
		
		roomService = RoomServiceImpl.getInstnace();
		roomService.selectRoom(room_seq);
		
		request.setAttribute("roomService", roomService);
		
		request.getRequestDispatcher("content.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/main/main.jsp");
		dispatcher.forward(request, response);
	}

}
