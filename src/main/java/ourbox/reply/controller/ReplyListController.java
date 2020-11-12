package ourbox.reply.controller;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/ReplyListController")
public class ReplyListController extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");	
		
		// 파라미터 정보 가져오기
		int board_seq = Integer.parseInt(request.getParameter("board_seq"));
		
		// 서비스 객체 생성
		IReplyService replyService = ReplyServiceImpl.getInstance();
		
		// 메소드 호출 하기
		List<ReplyVO> replyList = replyService.replyList(board_seq);
			
		// request에 저장하기
		request.setAttribute("replyList", replyList);
				
		// jsp로 전송하기
		request.getRequestDispatcher("view/reply/replyList.jsp").forward(request, response);
	
	
	
	
	
	}


}
