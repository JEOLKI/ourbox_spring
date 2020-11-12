package ourbox.reply.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.answer.service.AnswerServiceImpl;
import ourbox.answer.service.IAnswerService;
import ourbox.reply.service.IReplyService;
import ourbox.reply.service.ReplyServiceImpl;

@WebServlet("/DeleteReplyController")
public class DeleteReplyController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// parameter 받아오기
		int reply_seq = Integer.parseInt(request.getParameter("reply_seq"));
						
		// 서비스 객체 가져오기
		IReplyService replyService = ReplyServiceImpl.getInstance();
		
		// 메소드 호출
		int count = replyService.deleteReply(reply_seq);
						
		// request에 결과 저장하기
		request.setAttribute("result", count);
						
		// jsp로 전송하기
		request.getRequestDispatcher("view/common/result.jsp").forward(request, response);
	
	
	
	
	
	}



}
