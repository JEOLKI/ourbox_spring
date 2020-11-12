package ourbox.drive.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;

import ourbox.alarm.service.AlarmServiceImpl;
import ourbox.alarm.service.IAlarmService;
import ourbox.common.vo.AlarmVO;
import ourbox.common.vo.DriveVO;
import ourbox.common.vo.MemberVO;
import ourbox.drive.service.DriveServiceImpl;
import ourbox.drive.service.IDriveService;
import ourbox.member.service.IMemberService;
import ourbox.member.service.MemberServiceImpl;
import ourbox.room.service.IRoomService;
import ourbox.room.service.RoomServiceImpl;

@WebServlet(description = "DriveUploadController", urlPatterns = { "/DriveUploadController" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, 
				 maxFileSize = 1024 * 1024 * 5, 
				 maxRequestSize = 1024 * 1024 * 5 * 5)	
public class DriveUploadController extends HttpServlet {

	private static final String VIEW_PAGE = "view/drive/driveList.jsp";
	
	private static final String UPLOAD_DIRECTORY = "upload_files";
	private static final String DEFAULT_FILENAME = "default.file";
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int room_seq = Integer.parseInt(request.getParameter("roomSeq"));
		String uploadPath = getServletContext().getRealPath("") 	// realpath : 실제경로, 상대경로 아닌!
							+ File.separator + UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {	// 경로가 존재하지 않으면
			uploadDir.mkdir();
		}
		
		try {
			String fileName = "";
			int fileSize = 0;
			String fileType = "";
			System.out.println("part 사이즈 : " + request.getParts().size());
			for(Part part : request.getParts()) {
				fileName = getFileName(part);
				part.write(uploadPath + File.separator + fileName);	// 파일 저장
				fileSize += part.getSize();
				fileType = getFileType(part);
			}
			
			DriveVO uploadFile = new DriveVO();
			uploadFile.setDrive_name(fileName);
			uploadFile.setDrive_path(uploadPath+File.separator+fileName);
			uploadFile.setDrive_size(fileSize);
			uploadFile.setRoom_seq(room_seq);
			uploadFile.setDrive_type(fileType);
			IDriveService driveService = DriveServiceImpl.getInstance();
			
			int result = driveService.fileUpload(uploadFile);
			
			// 4. 포스트방식 -> url에 값 못넣어줌 -> redirect
			String redirectUrl = request.getContextPath() + "/DriveListController?roomSeq=" 
					+ URLEncoder.encode(room_seq+"", "utf-8");
			response.sendRedirect(redirectUrl);
			
			
			// 그룹 이름 가져오기
		      IRoomService roomService = RoomServiceImpl.getInstnace();
		      IMemberService memService = MemberServiceImpl.getInstance();
		      
		      String room_name = roomService.getRoomName(room_seq);
		      List<MemberVO> memList =  memService.chatMemProfile(room_seq);
		      
		      
		      // 알람 입력하기
		      IAlarmService alarmService = AlarmServiceImpl.getInstance();
		      
		      AlarmVO alarm = new AlarmVO();
		      
		      alarm.setAlarm_content("그룹 '" + room_name + "' 에서 새로운 파일이 등록되었습니다.");
		      
		      for (int i = 0; i < memList.size(); i++) {
		         
		         alarm.setMem_id(memList.get(i).getMem_id());
		         
		         alarmService.insertAlarm(alarm);
		      }
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	
	
	/**
	 * 파일 이름 추출하기
	 * @param part
	 * @return
	 */
	private String getFileName(Part part) {
		for(String content : part.getHeader("content-disposition").split(";")) {
			if(content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=")+ 2, content.length() - 1);
			}
		}
		return DEFAULT_FILENAME;
	}
	
	/**
	 * 파일 타입 추출하기
	 * @param part
	 * @return
	 */
	private String getFileType(Part part) {
		for(String content : part.getHeader("content-disposition").split(";")) {
			if(content.trim().startsWith("filename")) {
				String name =content.substring(content.indexOf("=")+ 2, content.length() - 1);
				return name.substring(name.lastIndexOf(".")+ 1);
			}
		}
		return null;
	}

}
