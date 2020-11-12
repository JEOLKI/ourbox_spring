package ourbox.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.NoticeVO;
import ourbox.notice.service.INoticeService;
import ourbox.notice.service.NoticeServiceImpl;

@WebServlet("/detailNotice")
public class DetailNoticeController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// parameter 받아오기
		int notice_seq = Integer.parseInt(request.getParameter("notice_seq"));

		// service 객체 생성하기
		INoticeService noticeService = NoticeServiceImpl.getInstance();
		
		// 메서드 사용하기
		NoticeVO detailNotice = noticeService.detailNotice(notice_seq);
		
		// request에 담기
		request.setAttribute("detailNotice", detailNotice);
						
		// jsp로 forward한다.
		request.getRequestDispatcher("view/notice/detailNotice.jsp").forward(request, response);
	
	
	
	}
	
	
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
