package ourbox.qna.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourbox.common.util.SqlMapClientFactory;
import ourbox.common.vo.QnaVO;

public class QnaDaoImpl implements IQnaDao {

	private SqlMapClient smc;
	private static IQnaDao qnaDao;
	
	private QnaDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IQnaDao getInstance() {
		if(qnaDao == null) qnaDao = new QnaDaoImpl();
		
		return qnaDao;
	}

	@Override
	public List<QnaVO> selectPage(Map<String, Integer> map) throws SQLException {
		return smc.queryForList("qna.selectPage", map);
	}

	@Override
	public int getTotalCount() throws SQLException {
		return (Integer) smc.queryForObject("qna.getTotalCount");
	}

	@Override
	public QnaVO detailQna(int qna_seq) throws SQLException {
		return (QnaVO) smc.queryForObject("qna.detailQna", qna_seq);
	}

	@Override
	public List<QnaVO> searchQna(QnaVO qna) throws SQLException {
		return smc.queryForList("qna.searchQna", qna);
	}

	@Override
	public List<QnaVO> myQnaList(String mem_id) throws SQLException {
		return smc.queryForList("qna.myQnaList", mem_id);
		
	}

	@Override
	public int insertQna(QnaVO qna) throws SQLException {
		return (Integer) smc.insert("qna.insertQna",qna);
	}

	@Override
	public int deleteQna(int qna_seq) throws SQLException {
		return (Integer) smc.delete("qna.deleteQna", qna_seq);
	}

	@Override
	public int updateQna(QnaVO qna) throws SQLException {
		return smc.update("qna.updateQna", qna);
	}
	
	
	
	
	
}
