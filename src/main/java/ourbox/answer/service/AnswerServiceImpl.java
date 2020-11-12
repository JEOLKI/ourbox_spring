package ourbox.answer.service;

import java.sql.SQLException;
import java.util.List;

import ourbox.answer.dao.AnswerDaoImpl;
import ourbox.answer.dao.IAnswerDao;
import ourbox.common.vo.AnswerVO;

public class AnswerServiceImpl implements IAnswerService{

	private IAnswerDao answerDao;
	
	private static IAnswerService answerService;
	
	private AnswerServiceImpl() {
		answerDao = AnswerDaoImpl.getInstance();
	}
	
	public static IAnswerService getInstance() {
		if (answerService == null) answerService = new AnswerServiceImpl();
		
		return answerService;
	}

	@Override
	public List<AnswerVO> answerList(int qna_seq) {
		List<AnswerVO> answerList = null;
		
		try {
			answerList = answerDao.answerList(qna_seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return answerList;
	}

	@Override
	public int insertAnswer(AnswerVO answer) {
		int count = 0;
		
		try {
			count = answerDao.insertAnswer(answer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public int deleteAnswer(int ans_seq) {
		int count = 0;
		
		try {
			count = answerDao.deleteAnswer(ans_seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public int updateAnswer(AnswerVO answer) {
		int count = 0;
		
		try {
			count = answerDao.updateAnswer(answer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	
	
}
