package ourbox.drive.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.DriveVO;
import ourbox.drive.service.DriveServiceImpl;
import ourbox.drive.service.IDriveService;
@WebServlet(description = "DeletegarbagesController", urlPatterns = { "/DeletegarbagesController" })
public class DeleteGarbagesController extends HttpServlet {
	
	private static final String VIEW_PAGE = "view/drive/garbageList.jsp";
	private static final String UPLOAD_DIRECTORY = "upload_files";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mem_id = request.getParameter("mem_id");
		
		String uploadPath = getServletContext().getRealPath("") 	// realpath : 실제경로, 상대경로 아닌!
				+ File.separator + UPLOAD_DIRECTORY;
		IDriveService driveService = DriveServiceImpl.getInstance();
		
		int cnt = driveService.deleteGarbages(uploadPath, mem_id);
		
		request.setAttribute("result", cnt);
		
		request.getRequestDispatcher("GargabegeListController?MemId="+mem_id+"").forward(request, response);
		
	}

}
