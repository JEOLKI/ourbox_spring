package ourbox.qna.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.QnaVO;
import ourbox.qna.service.IQnaService;
import ourbox.qna.service.QnaServiceImpl;


@WebServlet("/MemberQnaPageListController")
public class MemberQnaPageListController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// URL변수 받아오기 
		String mem_id = request.getParameter("memId");
		
		// service 객체 받아오기
		IQnaService qnaService = QnaServiceImpl.getInstance();
		
		// 메소드 호출하기
		List<QnaVO> myQnaList = qnaService.myQnaList(mem_id);
		
		// request에 저장하기
		request.setAttribute("myQnaList", myQnaList);
		request.setAttribute("mem_id", mem_id);
		
		// jsp로 전송하기
		request.getRequestDispatcher("view/qna/memberQnaPageList.jsp").forward(request, response);
		
		
	}
	

}
