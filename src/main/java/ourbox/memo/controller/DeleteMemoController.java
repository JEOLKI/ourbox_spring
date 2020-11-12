package ourbox.memo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.memo.service.IMemoService;
import ourbox.memo.service.MemoServiceImpl;

@WebServlet("/DeleteMemoController")
public class DeleteMemoController extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 파라미터 받아오기
		String mem_id = request.getParameter("memId");
		int memo_seq = Integer.parseInt(request.getParameter("memoSeq"));
				
		// service 객체 생성하기
		IMemoService memoService = MemoServiceImpl.getInstance();
				
		// 메소드 호출 하기
		int count =  memoService.deleteMemo(memo_seq);
								
		// request에 저장
		request.setAttribute("result", count);
										
		// jsp로
		request.getRequestDispatcher("view/common/result.jsp").forward(request, response);	 	
	
	
	}


}
