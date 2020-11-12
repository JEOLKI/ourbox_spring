package ourbox.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.notice.service.INoticeService;
import ourbox.notice.service.NoticeServiceImpl;

@WebServlet("/deleteNotice")
public class DeleteNoticeController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// parameter 받아오기
		int notice_seq = Integer.parseInt(request.getParameter("notice_seq"));
		
		// 서비스 객체 가져오기
		INoticeService noticeService = NoticeServiceImpl.getInstance();
		
		// 메소드 호출
		int count = noticeService.deleteNotice(notice_seq);
		
		// request에 결과 저장하기
		request.setAttribute("result", count);
		
		// jsp로 전송하기
		request.getRequestDispatcher("view/common/result.jsp").forward(request, response);
		
	}

}
