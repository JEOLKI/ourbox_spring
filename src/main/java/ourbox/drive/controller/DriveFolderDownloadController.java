package ourbox.drive.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ourbox.common.vo.DriveVO;
import ourbox.drive.service.DriveServiceImpl;
import ourbox.drive.service.IDriveService;

@WebServlet(description = "DriveFolderDownloadController", urlPatterns = { "/DriveFolderDownloadController" })
public class DriveFolderDownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//	private static final String DOWNLOAD_DIR = "upload_files\\";
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int drive_seq = Integer.parseInt(request.getParameter("drive_seq"));
		
		IDriveService driveService = DriveServiceImpl.getInstance();
		
		String downloadFolderName = driveService.zipDownloadDir(drive_seq);
		

		response.setContentType("application/zip");
		response.addHeader("Content-Disposition", "attachment; filename=" + downloadFolderName + ".zip");

	}
}