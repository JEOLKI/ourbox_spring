package ourbox.qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.QnaVO;
import ourbox.qna.service.IQnaService;
import ourbox.qna.service.QnaServiceImpl;

@WebServlet("/detailQna")
public class DetailQnaController extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// parameter 받아오기
		int qna_seq = Integer.parseInt(request.getParameter("qna_seq"));

		// service 객체 생성하기
		IQnaService qnaService = QnaServiceImpl.getInstance();
		
		// 메서드 사용하기
		QnaVO detailQna = qnaService.detailQna(qna_seq);
		
		// request에 담기
		request.setAttribute("detailQna", detailQna);
						
		// jsp로 forward한다.
		request.getRequestDispatcher("view/qna/detailQna.jsp").forward(request, response);
	
	
	}


}
