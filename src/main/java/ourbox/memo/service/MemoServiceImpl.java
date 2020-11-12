package ourbox.memo.service;

import java.sql.SQLException;
import java.util.List;

import ourbox.common.vo.MemoVO;
import ourbox.memo.dao.IMemoDao;
import ourbox.memo.dao.MemoDaoImpl;
import ourbox.notice.dao.INoticeDao;
import ourbox.notice.dao.NoticeDaoImpl;
import ourbox.notice.service.INoticeService;
import ourbox.notice.service.NoticeServiceImpl;

public class MemoServiceImpl implements IMemoService {

	private static IMemoService memoService;

	private IMemoDao memoDao;
	
	private MemoServiceImpl() {
		memoDao = MemoDaoImpl.getInstance();
	}
	
	public static IMemoService getInstance() {
		
		if(memoService == null) {
			memoService = new MemoServiceImpl();
		}
		return memoService;
	}
	
	
	@Override
	public List<MemoVO> memoList(String mem_id) {
		List<MemoVO> memoList = null;
		
		try {
			memoList = memoDao.memoList(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return memoList;
	}

	@Override
	public int insertMemo(MemoVO memo) {
		int count = 0;
		
		try {
			count = memoDao.insertMemo(memo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	@Override
	public int deleteMemo(int memo_seq) {
		int count = 0;
		
		try {
			count = memoDao.deleteMemo(memo_seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	

	@Override
	public int updateMemo(MemoVO memo) {
		int count = 0;
		
		try {
			count = memoDao.updateMemo(memo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}


	@Override
	public List<MemoVO> searchMemo(MemoVO memo) {
		List<MemoVO> searchMemo = null;
		
		try {
			searchMemo = memoDao.searchMemo(memo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return searchMemo;
	}


	
}
