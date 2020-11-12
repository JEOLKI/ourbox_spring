package ourbox.drive.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.DriveVO;
import ourbox.drive.service.DriveServiceImpl;
import ourbox.drive.service.IDriveService;

@WebServlet(description = "DrivePasteController", urlPatterns = { "/DrivePasteController" })
public class DrivePasteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String COPY_DIRECTORY = "copied_files";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 붙여넣을 방번호
		int roomSeq = Integer.parseInt(request.getParameter("roomSeq").trim());
		System.out.println("paste roomseq : "+roomSeq);
		//int room_seq = Integer.parseInt(request.getParameter("room_seq"));
		
		// 붙여넣을 원본 파일번호
		int copyDriveSeq = Integer.parseInt(request.getParameter("copyDriveSeq").trim());//복사할 파일/폴더 번호
		System.out.println("paste copyDriveSeq : "+copyDriveSeq);
		
		// 원본 파일의 경로 
		//String copy_path = request.getParameter("copy_path");	// 복사할 드라이브 경로 
		// 원본파일경로 인코딩
		//String copyPath = URLDecoder.decode(copy_path,"utf-8").replace("\"", "");
		//System.out.println("copy_path : "+copyPath);
		
		IDriveService driveService = DriveServiceImpl.getInstance();
		DriveVO sourceDriveVO = driveService.selectDrive(copyDriveSeq);	// 복사할 파일/폴더 객체
		
		// 복사해서 붙여넣을 상위폴더
		String copy_path = getServletContext().getRealPath("") 	// realpath : 실제경로, 상대경로 아닌!
				+ File.separator + COPY_DIRECTORY;
		// 복사할 원본파일의 이름
		String copyDriveName = sourceDriveVO.getDrive_name();
		
		// 복사된 파일 경로
		String copyPath = copy_path + File.separator+copyDriveName;
		
		
		
		File sourceDrive = new File(sourceDriveVO.getDrive_path());
		int result = driveService.pasteDrive(sourceDriveVO, copyPath, roomSeq);
		
		//request.setAttribute("result", result );
		
		//request.getRequestDispatcher("view/common/result.jsp").forward(request, response);
		
		// 4. 포스트방식 -> url에 값 못넣어줌 -> redirect
		String redirectUrl = request.getContextPath() + "/DriveListController?roomSeq=" 
				+ URLEncoder.encode(roomSeq+"", "utf-8");
		response.sendRedirect(redirectUrl);
		
		
	}

}
