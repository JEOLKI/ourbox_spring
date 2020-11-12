package ourbox.drive.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.DriveVO;
import ourbox.drive.dao.DriveDaoImpl;
import ourbox.drive.service.DriveServiceImpl;
import ourbox.drive.service.IDriveService;

@WebServlet(description = "DriveListController", urlPatterns = { "/DriveListController" })
public class DriveListController extends HttpServlet {
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// URL 변수 가져오기
		int room_seq = Integer.parseInt(request.getParameter("roomSeq"));
		System.out.println("Cotroller room_seq : " + room_seq);
				
		// 서비스 객체 생성하기
		IDriveService driveService = DriveServiceImpl.getInstance();
		
		// 드라이브 리스트 가져오기
		List<DriveVO> driveList = driveService.driveList(room_seq);
	
		// request에 저장하기
		request.setAttribute("driveList", driveList);
		request.setAttribute("roomSeq", room_seq);
		
		request.getRequestDispatcher("view/drive/driveList.jsp").forward(request, response);
	}

}
