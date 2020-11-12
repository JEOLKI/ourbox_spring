package ourbox.memo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.MemoVO;
import ourbox.memo.service.IMemoService;
import ourbox.memo.service.MemoServiceImpl;

@WebServlet("/MemoListController")
public class MemoListController extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 파라미터값 가져오기
		String mem_id = request.getParameter("memId");
		
		// 서비스 객체 생성
		IMemoService memoService = MemoServiceImpl.getInstance();
		
		// 메소드 호출 하기
		List<MemoVO> memoList =  memoService.memoList(mem_id);
		
		// request에 저장하기
		request.setAttribute("memoList", memoList);
		request.setAttribute("mem_id", mem_id);
				
		// jsp로 전송하기
		request.getRequestDispatcher("view/memo/memoMain.jsp").forward(request, response);
		
	
	}


}
