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

@WebServlet("/SearchMemoController")
public class SearchMemoController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		// parameter 가져오기
		String mem_id = request.getParameter("mem_id");
    	String searchKeyWord = request.getParameter("searchKeyWord");
    	
    	// service 객체 가져오기
    	IMemoService memoService = MemoServiceImpl.getInstance();
    	
    	MemoVO memo = new MemoVO();
    	
    	memo.setMem_id(mem_id);
    	memo.setMemo_title(searchKeyWord);
    	memo.setMemo_title(searchKeyWord);
    			
    	// service 메소드 사용하기
    	List<MemoVO> memoList = memoService.searchMemo(memo);
    		
    	// request에 저장하기
    	request.setAttribute("memoList", memoList);
    	request.setAttribute("mem_id", mem_id);
    	
    	// jsp로 forward한다.
    	request.getRequestDispatcher("view/memo/searchMain.jsp").forward(request, response);
		
		
		
		
		
	}


}
