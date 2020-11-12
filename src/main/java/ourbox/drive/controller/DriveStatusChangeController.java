package ourbox.drive.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.DriveVO;
import ourbox.drive.service.DriveServiceImpl;
import ourbox.drive.service.IDriveService;

@WebServlet(description = "DriveStatusChangeController", urlPatterns = { "/DriveStatusChangeController" })
public class DriveStatusChangeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int drive_seq = Integer.parseInt(request.getParameter("drive_seq"));
		
		IDriveService driveSevice = DriveServiceImpl.getInstance();
		
		DriveVO toGarbageDrive = driveSevice.selectDrive(drive_seq);
		int room_seq = toGarbageDrive.getRoom_seq();
		
		int result = driveSevice.changeDriveStatus(drive_seq);
		
		request.setAttribute("result", result);
		request.setAttribute("room_seq", room_seq);
		
		request.getRequestDispatcher("view/drive/getRoomSeq.jsp").forward(request, response);
		
	}

}
