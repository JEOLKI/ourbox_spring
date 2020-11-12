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
import ourbox.common.vo.NoticeVO;
import ourbox.notice.service.INoticeService;
import ourbox.notice.service.NoticeServiceImpl;

@WebServlet("/updateAnswerController")
public class UpdateAnswerController extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 파라미터값 받아오기
		int qna_seq = Integer.parseInt(request.getParameter("qna_seq"));
		int ans_seq = Integer.parseInt(request.getParameter("ans_seq"));
		String ans_content = request.getParameter("ans_content");
				
		AnswerVO answer = new AnswerVO();
		
		answer.setAns_content(ans_content);
		answer.setAns_seq(ans_seq);
		answer.setQna_seq(qna_seq);
		

		// 서비스 객체 생성
		IAnswerService answerService = AnswerServiceImpl.getInstance();
		
		// 메소드 호출 하기
		int count = answerService.updateAnswer(answer);
								
								
		// request에 저장
		request.setAttribute("result", count);
								
		// jsp로
		request.getRequestDispatcher("view/common/result.jsp").forward(request, response);	 
			
	
	
	
	
	}


}
