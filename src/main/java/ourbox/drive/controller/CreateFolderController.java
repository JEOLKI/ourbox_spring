package ourbox.drive.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.DriveVO;
import ourbox.drive.service.DriveServiceImpl;
import ourbox.drive.service.IDriveService;

@WebServlet(description = "CreateFolderController", urlPatterns = { "/CreateFolderController" })
public class CreateFolderController extends HttpServlet {
		
	private static final String UPLOAD_DIRECTORY = "upload_files";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String drive_name = request.getParameter("drive_name");
		int room_seq = Integer.parseInt(request.getParameter("room_seq"));
		String drive_path = getServletContext().getRealPath("") 	// realpath : 실제경로, 상대경로 아닌!
				+ File.separator + UPLOAD_DIRECTORY;
		
		System.out.println(drive_name);
		System.out.println(room_seq);
		
		DriveVO newFolder = new DriveVO();
		newFolder.setDrive_name(drive_name);
		newFolder.setRoom_seq(room_seq);
		newFolder.setDrive_path(drive_path+File.separator+drive_name);
		IDriveService driveService = DriveServiceImpl.getInstance();
		
		int cnt = driveService.createFolder(newFolder);
		
		request.setAttribute("result", cnt);
		
		request.getRequestDispatcher("view/common/result.jsp").forward(request, response);
		
		
	}

}
