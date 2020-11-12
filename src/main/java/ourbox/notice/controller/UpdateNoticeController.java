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

@WebServlet("/updateNotice")
public class UpdateNoticeController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 파라미터값 받아오기
		int notice_seq = Integer.parseInt(request.getParameter("notice_seq"));
		String updateTitle = request.getParameter("updateTitle");
		String updateContent = request.getParameter("updateContent");
				
		NoticeVO notice = new NoticeVO();
		
		notice.setNotice_seq(notice_seq);
		notice.setNotice_title(updateTitle);
		notice.setNotice_content(updateContent);
				
		// 서비스 객체 생성
		INoticeService noticeService = NoticeServiceImpl.getInstance();
						
		// 메소드 호출 하기
		int count =  noticeService.updateNotice(notice);
						
		// request에 저장
		request.setAttribute("result", count);
						
		//5 jsp로
		request.getRequestDispatcher("view/common/result.jsp").forward(request, response);	 
	
	
	}

	

}
