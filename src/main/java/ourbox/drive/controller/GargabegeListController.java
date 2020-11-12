package ourbox.drive.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.stylesheets.LinkStyle;

import ourbox.common.vo.DriveVO;
import ourbox.drive.service.DriveServiceImpl;
import ourbox.drive.service.IDriveService;

@WebServlet(description = "GargabegeListController", urlPatterns = { "/GargabegeListController" })
public class GargabegeListController extends HttpServlet {
	
	private static final String VIEW_PAGE = "view/drive/garbageList.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// URL 변수 가져오기
		String mem_id = request.getParameter("memId");
		System.out.println("memId : "+mem_id);
		
		IDriveService driveService = DriveServiceImpl.getInstance();
		
		List<DriveVO> garbageList = driveService.garbageList(mem_id);
		
		request.setAttribute("garbageList", garbageList);
		request.setAttribute("mem_id", mem_id);
		
		request.getRequestDispatcher(VIEW_PAGE).forward(request, response);
	}

}
