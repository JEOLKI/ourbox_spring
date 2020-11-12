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

@WebServlet("/InsertMemoController")
public class InsertMemoController extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파라미터 받아오기
		String mem_id = request.getParameter("mem_id");
		String memoContent = request.getParameter("memoContent");
		String memoTitle = request.getParameter("memoTitle");
		
		MemoVO memo = new MemoVO();
		
		memo.setMem_id(mem_id);
		memo.setMemo_content(memoContent);
		memo.setMemo_title(memoTitle);
		
		// service 객체 생성하기
		IMemoService memoService = MemoServiceImpl.getInstance();
		
		// 메소드 호출 하기
		int count =  memoService.insertMemo(memo);
						
		// request에 저장
		request.setAttribute("result", count);
						
		// jsp로
		request.getRequestDispatcher("view/common/result.jsp").forward(request, response);	 
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
