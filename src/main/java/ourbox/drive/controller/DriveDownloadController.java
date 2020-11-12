package ourbox.drive.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ourbox.common.vo.DriveVO;
import ourbox.drive.service.DriveServiceImpl;
import ourbox.drive.service.IDriveService;
import ourbox.drive.util.CompressZip;

@WebServlet(description = "DriveDownloadController", urlPatterns = { "/DriveDownloadController" })
public class DriveDownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//	private static final String DOWNLOAD_DIR = "upload_files\\";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String fileName = "test_utf-8.txt";
		/*
		// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename =\"" + "test.txt" + "\"");
		
		FileInputStream fis = new FileInputStream(DOWNLOAD_DIR + fileName);
		ServletOutputStream out = response.getOutputStream();
		
		int c;
		while((c = fis.read()) != -1) {
			out.write(c);
		}
		fis.close();
		*/
		
		
		
		
		
		
		
		
		
		
		// 파라미터값 받아오기
		int drive_seq = Integer.parseInt(request.getParameter("drive_seq"));
		
		
		IDriveService driveService = DriveServiceImpl.getInstance();
		
		// 다운받을 원본 객체 (파일/폴더)
		DriveVO sourceDriveVO = driveService.selectDrive(drive_seq);
		
		// 다운받을 원본 객체 위치경로
		String sourceDrivePath = sourceDriveVO.getDrive_path();
		
		
		// 다운받을 드라이브(파일/폴더 )이름
		String downloadDriveName = sourceDriveVO.getDrive_name();
		
		// 다운받을 드라이브(파일/폴더)의 위치경로
		String downloadPath = sourceDriveVO.getDrive_path();
		// 이름 인코딩
		downloadDriveName = URLEncoder.encode(downloadDriveName, "utf-8");
		downloadDriveName = downloadDriveName.replaceAll("\\+", "%20");

		
		// 다운받을 원본경로를 넣은 파일객체
		File downloadDrive = new File(sourceDrivePath);
		
	
		if(downloadDrive.getAbsolutePath().lastIndexOf(".")>0) {	// 해당 위치를 넣은 원본객체가 파일일때	// isFile() 메소드가 잘 안먹힘... 둘다 false 나올때 많음
			File downloadFile = new File(downloadPath);
			
			// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename =\"" + downloadDriveName + "\"");
			
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(downloadFile));
			ServletOutputStream out = response.getOutputStream();
			
			int c;
			while((c = bis.read()) != -1) {
				out.write(c);
			}
			bis.close();
			out.close();
		
		}
		
		if(downloadDrive.getAbsolutePath().lastIndexOf(".")<0) {	// 해당위치를 넣은 원본객체가 폴더일 때 
			CompressZip compressZip = new CompressZip(); 
			// 압축 하기 
			
			String zipPath = sourceDriveVO.getDrive_path();
			String zipFolderName = sourceDriveVO.getDrive_name();
			try { 
				if (!compressZip.compress(zipPath, zipPath, zipFolderName)) {
					System.out.println("압축 실패");
				} else {
				
				}
			} catch (Throwable e) { 
				e.printStackTrace();
			}
			
			File downloadFolder = new File(downloadPath);
			
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment; filename=" + zipFolderName + ".zip");
			
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(downloadFolder+File.separator+zipFolderName));
			ServletOutputStream out = response.getOutputStream();
			
			int c;
			while((c = bis.read()) != -1) {
				out.write(c);
			}
			bis.close();
			out.close();
		}
		
		
		
	}

}
