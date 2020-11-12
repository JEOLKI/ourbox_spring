package ourbox.drive.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.drive.service.DriveServiceImpl;
import ourbox.drive.service.IDriveService;

@WebServlet(description = "RecycleGarbagesController", urlPatterns = { "/RecycleGarbagesController" })
public class RecycleGarbagesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mem_id = request.getParameter("mem_id");
		
		int drive_seq = Integer.parseInt(request.getParameter("drive_seq"));
		
		IDriveService driveService = DriveServiceImpl.getInstance();
		
		int result = driveService.recycleFile(drive_seq);
		
		request.getRequestDispatcher("GargabegeListController?MemId="+mem_id+"").forward(request, response);
		
		
	}

}
