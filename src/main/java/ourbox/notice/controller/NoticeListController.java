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

@WebServlet("/noticeList")
public class NoticeListController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 서비스 객체 생성
		INoticeService noticeService = NoticeServiceImpl.getInstance();
		
		// 메소드 호출 하기
		List<NoticeVO> noticeList =  noticeService.noticeList();
	
		// request에 저장하기
		request.setAttribute("noticeList", noticeList);
		
		// jsp로 전송하기
		request.getRequestDispatcher("view/ourbox/start.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
