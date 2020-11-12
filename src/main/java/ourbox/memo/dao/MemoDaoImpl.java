package ourbox.memo.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourbox.common.util.SqlMapClientFactory;
import ourbox.common.vo.MemoVO;

public class MemoDaoImpl implements IMemoDao {

	private SqlMapClient smc;
	
	private static IMemoDao memoDao;
	
	private MemoDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IMemoDao getInstance() {
		
		if(memoDao == null) {
			memoDao = new MemoDaoImpl();
		}
		
		return memoDao;
	}
	
	
	@Override
	public List<MemoVO> memoList(String mem_id)  throws SQLException{
		return smc.queryForList("memo.memoList", mem_id);
	}

	@Override
	public int insertMemo(MemoVO memo) throws SQLException {
		return (Integer) smc.insert("memo.insertMemo", memo);
	}

	@Override
	public int deleteMemo(int memo_seq) throws SQLException {
		return smc.delete("memo.deleteMemo", memo_seq);
	}

	@Override
	public int updateMemo(MemoVO memo) throws SQLException {
		return smc.update("memo.updateMemo", memo);
	}

	@Override
	public List<MemoVO> searchMemo(MemoVO memo) throws SQLException {
		return smc.queryForList("memo.searchMemo", memo);
	}



}
