package ourbox.drive.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.drive.service.DriveServiceImpl;
import ourbox.drive.service.IDriveService;

@WebServlet("/DriveSizeController")
public class DriveSizeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IDriveService driveService = DriveServiceImpl.getInstance();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mem_id = request.getParameter("memId");
		
		int useSize = (int) (driveService.getUseDriveSize(mem_id)/100000); // 사용중인 용량 값 (바이트를 메가바이트 단위로)

		int planSize = driveService.getPlanSize(mem_id)*1000; // 요금제의 용량 값(기가바이트를 메가바이트 단위로) 기본용량 : 15000메가바이트
		
		if(planSize == 0) {
			planSize = 15000;
		}
		
		int percent = (useSize*100/planSize);
		request.setAttribute("useSize", useSize);
		request.setAttribute("planSize", planSize);
		request.setAttribute("percent", percent);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/ourbox/capacity.jsp");
		dispatcher.forward(request, response); // 그래프로 보내주기
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/ourbox/capacity.jsp");
		dispatcher.forward(request, response);
	}

}
