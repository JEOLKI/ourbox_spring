package ourbox.answer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.answer.dao.AnswerDaoImpl;
import ourbox.answer.service.AnswerServiceImpl;
import ourbox.answer.service.IAnswerService;
import ourbox.common.vo.AnswerVO;
import ourbox.qna.service.IQnaService;
import ourbox.qna.service.QnaServiceImpl;


@WebServlet("/answerList")
public class AnswerListController extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파라미터 정보 가져오기
		int qna_seq = Integer.parseInt(request.getParameter("qna_seq"));
		
		// 서비스 객체 생성
		IAnswerService answerService = AnswerServiceImpl.getInstance();
		
		// 메소드 호출 하기
		List<AnswerVO> answerList =  answerService.answerList(qna_seq);
			
		// request에 저장하기
		request.setAttribute("answerList", answerList);
				
		// jsp로 전송하기
		request.getRequestDispatcher("view/answer/answerList.jsp").forward(request, response);
		
	
	}


}
