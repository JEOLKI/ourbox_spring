package ourbox.qna.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import ourbox.common.vo.AnswerVO;
import ourbox.common.vo.QnaVO;
import ourbox.qna.dao.IQnaDao;
import ourbox.qna.dao.QnaDaoImpl;

public class QnaServiceImpl implements IQnaService {

	private IQnaDao qnaDao;
	
	private static IQnaService qnaService;
	
	private QnaServiceImpl() {
		qnaDao = QnaDaoImpl.getInstance();
	}
	
	public static IQnaService getInstance() {
		if (qnaService == null) qnaService = new QnaServiceImpl();
		
		return qnaService;
	}
	
	
	@Override
	public List<QnaVO> selectPage(Map<String, Integer> map) {
		List<QnaVO> qnaList = null;
		
		try {
			qnaList = qnaDao.selectPage(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return qnaList;
	}

	@Override
	public int getTotalCount() {
		int count = 0;
		
		try {
			count = qnaDao.getTotalCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public QnaVO detailQna(int qna_seq) {
		QnaVO qna = null;
		
		try {
			qna = qnaDao.detailQna(qna_seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return qna;
	}
	
	@Override
	public List<QnaVO> searchQna(QnaVO qna) {
		
		List<QnaVO> qnaList = null;
		
		try {
			qnaList = qnaDao.searchQna(qna);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return qnaList;
	}


	@Override
	public List<QnaVO> myQnaList(String mem_id) {
		
		List<QnaVO> myQnaList = null;
		
		try {
			myQnaList = qnaDao.myQnaList(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myQnaList;
	}
	

	@Override
	public int insertQna(QnaVO qna) {
		int count = 0;
		
		try {
			count = qnaDao.insertQna(qna);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public int deleteQna(int qna_seq) {
		int count = 0;
		
		try {
			count = qnaDao.deleteQna(qna_seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
		
	}
	
	
	@Override
	public int updateQna(QnaVO qna) {
		int count = 0;
		
		try {
			count = qnaDao.updateQna(qna);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}








}
