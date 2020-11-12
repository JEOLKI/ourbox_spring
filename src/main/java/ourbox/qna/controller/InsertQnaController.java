package ourbox.qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.QnaVO;
import ourbox.qna.service.IQnaService;
import ourbox.qna.service.QnaServiceImpl;


@WebServlet("/InsertQnaController")
public class InsertQnaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 파라미터값 받아오기
		String mem_id = request.getParameter("mem_id");
		String insertTitle = request.getParameter("insertTitle");
		String insertContent = request.getParameter("insertContent");
				
		QnaVO qna = new QnaVO();
		
		qna.setMem_id(mem_id);
		qna.setQna_title(insertTitle);
		qna.setQna_content(insertContent);
		
		// 서비스 객체 생성
		IQnaService qnaService = QnaServiceImpl.getInstance();
		
		// 메소드 호출 하기
		int count =  qnaService.insertQna(qna);
						
		// request에 저장
		request.setAttribute("result", count);
					
		// jsp로
		request.getRequestDispatcher("view/common/result.jsp").forward(request, response);	 
	
	
	
	
	
	}

	

}
