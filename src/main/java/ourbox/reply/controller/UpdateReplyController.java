package ourbox.reply.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.answer.service.AnswerServiceImpl;
import ourbox.answer.service.IAnswerService;
import ourbox.common.vo.AnswerVO;
import ourbox.common.vo.ReplyVO;
import ourbox.reply.service.IReplyService;
import ourbox.reply.service.ReplyServiceImpl;

@WebServlet("/UpdateReplyController")
public class UpdateReplyController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		// 파라미터값 받아오기
		int board_seq = Integer.parseInt(request.getParameter("board_seq"));
		int reply_seq = Integer.parseInt(request.getParameter("reply_seq"));
		String reply_content = request.getParameter("reply_content");
		
		ReplyVO reply = new ReplyVO();
		
		reply.setBoard_seq(board_seq);
		reply.setReply_seq(reply_seq);
		reply.setReply_content(reply_content);

		// 서비스 객체 생성
		IReplyService replyService = ReplyServiceImpl.getInstance();
		
		// 메소드 호출 하기
		int count = replyService.updateReply(reply);
										
		// request에 저장
		request.setAttribute("result", count);
										
		// jsp로
		request.getRequestDispatcher("view/common/result.jsp").forward(request, response);	 
	
	
	
	
	
	
	
	
	
	
	
	}

}
