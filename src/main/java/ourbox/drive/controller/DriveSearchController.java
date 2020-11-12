package ourbox.drive.controller;

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

@WebServlet(description = "DriveSearchController", urlPatterns = { "/DriveSearchController" })
public class DriveSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String keyword = request.getParameter("keyword");
		
		IDriveService driveService = DriveServiceImpl.getInstance();
		
		List<DriveVO> searchList = driveService.searchDriveList(keyword);
		
		request.setAttribute("searchList", searchList);
		
		request.getRequestDispatcher("view/drive/searchList.jsp").forward(request, response);
				
	}

}
