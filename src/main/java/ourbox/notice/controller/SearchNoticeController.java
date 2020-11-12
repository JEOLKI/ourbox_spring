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

@WebServlet("/searchNotice")
public class SearchNoticeController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		// parameter 가져오기
    	String searchOption = request.getParameter("searchOption");
    	String searchKeyWord = request.getParameter("searchKeyWord");
    	
    	// service 객체 가져오기
    	INoticeService noticeService = NoticeServiceImpl.getInstance();
    	
    	// notice객체에 정보 저장하기
    	NoticeVO notice = new NoticeVO();
    			
    	if (searchOption.equals("글제목만")) {
    		notice.setNotice_title(searchKeyWord);
    	}else if (searchOption.equals("글내용만")) {
    		notice.setNotice_content(searchKeyWord);
    	}else if (searchOption.equals("글제목 + 내용")) {
    		notice.setNotice_title(searchKeyWord);
    		notice.setNotice_content(searchKeyWord);
    	}
    			
    	// service 메소드 사용하기
    	List<NoticeVO> noticeList = noticeService.searchNotice(notice);
    	
    	// request에 저장하기
    	request.setAttribute("noticeList", noticeList);
    	
    	// jsp로 forward한다.
    	request.getRequestDispatcher("view/notice/noticeList.jsp").forward(request, response);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
}
