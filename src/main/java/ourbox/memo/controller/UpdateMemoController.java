package ourbox.memo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.MemoVO;
import ourbox.memo.service.IMemoService;
import ourbox.memo.service.MemoServiceImpl;

@WebServlet("/UpdateMemoController")
public class UpdateMemoController extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 변수 받아오기
		int memo_seq = Integer.parseInt(request.getParameter("memo_seq"));
		String mem_id = request.getParameter("mem_id");
		String memo_title = request.getParameter("memoTitle");
		String memo_content = request.getParameter("memoContent");
		
		MemoVO memo = new MemoVO();
		
		memo.setMem_id(mem_id);
		memo.setMemo_content(memo_content);
		memo.setMemo_title(memo_title);
		memo.setMemo_seq(memo_seq);
		
		// 서비스 객체 불러오기
		IMemoService memoService = MemoServiceImpl.getInstance();
		
		// 메소드 사용하기
		int count = memoService.updateMemo(memo);
		
		// request에 저장
		request.setAttribute("result", count);
								
		// jsp로
		request.getRequestDispatcher("view/common/result.jsp").forward(request, response);	 
	}

}
