package ourbox.answer.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourbox.common.util.SqlMapClientFactory;
import ourbox.common.vo.AnswerVO;

public class AnswerDaoImpl implements IAnswerDao{
	
	private SqlMapClient smc;
	
	private static IAnswerDao answerDao;
	
	private AnswerDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IAnswerDao getInstance() {
		
		if(answerDao == null) {
			answerDao = new AnswerDaoImpl();
		}
		
		return answerDao;
	}

	@Override
	public List<AnswerVO> answerList(int qna_seq) throws SQLException {
		return smc.queryForList("answer.answerList", qna_seq);
	}

	@Override
	public int insertAnswer(AnswerVO answer) throws SQLException {
		return (Integer) smc.insert("answer.insertAnswer", answer);
	}

	@Override
	public int deleteAnswer(int ans_seq) throws SQLException {
		return smc.delete("answer.deleteAnswer", ans_seq);
	}

	@Override
	public int updateAnswer(AnswerVO answer) throws SQLException {
		return smc.update("answer.updateAnswer", answer);
	}
	
	
}
