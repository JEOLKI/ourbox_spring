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



@WebServlet(description = "DriveRenameController", urlPatterns = { "/DriveRenameController" })
public class DriveRenameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.setCharacterEncoding("utf-8");  // 안해도 한글 안깨짐
		
		// 파라미터 가져오기
		int drive_seq = Integer.parseInt(request.getParameter("drive_seq"));
		String newName = request.getParameter("newName");
		
		
		IDriveService driveService = DriveServiceImpl.getInstance();
		
		// 파일번호로 객체 가져오기
		DriveVO orignDriveVO = driveService.selectDrive(drive_seq);
		// 파일번호로 room_seq 가져오기
		int room_seq = orignDriveVO.getRoom_seq();
		
		// 객체 경로 얻기
		String orignPath = orignDriveVO.getDrive_path();
		
		
		File chkFile = new File(orignPath);
		
		int result = 0;
		
		if(chkFile.getAbsolutePath().lastIndexOf(".") >0) { // 파일이면
			result = driveService.renameFile(orignDriveVO, newName);
		}
		
		if(chkFile.getAbsolutePath().lastIndexOf(".")<0) {	// 폴더면
			result = driveService.renameDir(orignDriveVO, newName);
		}
		
		// isFile(), isDirectory() 안먹힘. 둘다 false 나옴
//		boolean isFile = chkFile.isFile();
//		if(chkFile.isFile()) {
//			result = driveService.renameFile(orignDriveVO, newName);
//		}
//		boolean isDir = chkFile.isDirectory();
//		if(chkFile.isDirectory()) {
//			result = driveService.renameDir(orignDriveVO, newName);
//		}
//		
		request.setAttribute("result",result);
		request.setAttribute("room_seq", room_seq);
		
		request.getRequestDispatcher("view/drive/getRoomSeq.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
