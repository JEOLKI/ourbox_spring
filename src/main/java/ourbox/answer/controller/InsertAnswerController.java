package ourbox.answer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.answer.service.AnswerServiceImpl;
import ourbox.answer.service.IAnswerService;
import ourbox.common.vo.AnswerVO;

@WebServlet("/insertAnswerController")
public class InsertAnswerController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 파라미터값 받아오기
		int qna_seq = Integer.parseInt(request.getParameter("qna_seq"));
		String ans_content = request.getParameter("answerContent");
		
		// 정보 저장하기
		AnswerVO answer = new AnswerVO();
		
		answer.setQna_seq(qna_seq);
		answer.setAns_content(ans_content);

		// 서비스 객체 생성
		IAnswerService answerService = AnswerServiceImpl.getInstance();
		
		// 메소드 호출 하기
		int count = answerService.insertAnswer(answer);
		
		
		//4 request에 저장
		request.setAttribute("result", count);
						
		//5 jsp로
		request.getRequestDispatcher("view/common/result.jsp").forward(request, response);
	
	
	
	
	}

}
