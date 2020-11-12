package ourbox.qna.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.common.vo.QnaVO;
import ourbox.qna.service.IQnaService;
import ourbox.qna.service.QnaServiceImpl;

@WebServlet("/searchQna")
public class SearchQnaController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// parameter 가져오기
    	String searchOption = request.getParameter("searchOption");
    	String searchKeyWord = request.getParameter("searchKeyWord");
    	String mem_id = request.getParameter("mem_id");
    	
    	// service 객체 가져오기
    	IQnaService qnaService = QnaServiceImpl.getInstance();
    	
    	// qna 객체에 정보 저장하기
    	QnaVO qna = new QnaVO();
    			
    	qna.setMem_id(mem_id);
    	
    	if (searchOption.equals("글제목만")) {
    		qna.setQna_title(searchKeyWord);
    	}else if (searchOption.equals("글내용만")) {
    		qna.setQna_content(searchKeyWord);
    	}else if (searchOption.equals("글제목 + 내용")) {
    		qna.setQna_title(searchKeyWord);
    		qna.setQna_content(searchKeyWord);
    	}else if (searchOption.equals("작성자만")) {
    		qna.setMem_id(searchKeyWord);
    	}
    	
    	
    	// service 메소드 사용하기
    	List<QnaVO> qnaList = qnaService.searchQna(qna);
    	
    	// request에 저장하기
    	request.setAttribute("qnaList", qnaList);
    	
    	// jsp로 forward한다.
    	request.getRequestDispatcher("view/qna/qnaList.jsp").forward(request, response);	
	
	
	
	
	
	}

}
