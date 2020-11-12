package ourbox.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.NoticeVO;
import ourbox.notice.service.INoticeService;
import ourbox.notice.service.NoticeServiceImpl;

@WebServlet("/insertNotice")
public class InsertNoticeController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파라미터값 받아오기
		String insertTitle = request.getParameter("insertTitle");
		String insertContent = request.getParameter("insertContent");
		
		NoticeVO notice = new NoticeVO();
		
		notice.setNotice_title(insertTitle);
		notice.setNotice_content(insertContent);
		
		// 서비스 객체 생성
		INoticeService noticeService = NoticeServiceImpl.getInstance();
				
		// 메소드 호출 하기
		int count =  noticeService.insertNotice(notice);
				
		// request에 저장
		request.setAttribute("result", count);
				
		// jsp로
		request.getRequestDispatcher("view/common/result.jsp").forward(request, response);	 
			
	
	}

}
