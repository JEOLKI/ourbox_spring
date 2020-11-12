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

@WebServlet("/UpdateQnaController")
public class UpdateQnaController extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파라미터값 받아오기
		int qna_seq = Integer.parseInt(request.getParameter("qna_seq"));
		String updateTitle = request.getParameter("updateTitle");
		String updateContent = request.getParameter("updateContent");
				
		QnaVO qna = new QnaVO();
		
		qna.setQna_seq(qna_seq);
		qna.setQna_title(updateTitle);
		qna.setQna_content(updateContent);
		
		// 서비스 객체 생성
		IQnaService qnaService = QnaServiceImpl.getInstance();
		
		// 메소드 호출 하기
		int count =  qnaService.updateQna(qna);
								
		// request에 저장
		request.setAttribute("result", count);
								
		// jsp로 전송하기
		request.getRequestDispatcher("view/common/result.jsp").forward(request, response);	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
