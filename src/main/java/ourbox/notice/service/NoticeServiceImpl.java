package ourbox.notice.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import ourbox.common.vo.NoticeVO;
import ourbox.notice.dao.INoticeDao;
import ourbox.notice.dao.NoticeDaoImpl;

public class NoticeServiceImpl implements INoticeService {

	private static INoticeService noticeService;
	
	private INoticeDao noticeDao;
	
	private NoticeServiceImpl() {
		noticeDao = NoticeDaoImpl.getInstance();
	}
	
	public static INoticeService getInstance() {
		
		if(noticeService == null) {
			noticeService = new NoticeServiceImpl();
		}
		return noticeService;
	}
	
	@Override
	public List<NoticeVO> noticeList() {
		List<NoticeVO> noticeList = null;
		
		try {
			noticeList = noticeDao.noticeList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return noticeList;
	}

	@Override
	public int insertNotice(NoticeVO notice) {
		int count = 0;
		
		try {
			count = noticeDao.insertNotice(notice);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public int updateNotice(NoticeVO notice) {
		int count = 0;
		
		try {
			count = noticeDao.updateNotice(notice);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public int deleteNotice(int notice_seq) {
		int count = 0;
		
		try {
			count = noticeDao.deleteNotice(notice_seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public NoticeVO detailNotice(int notice_seq) {
		NoticeVO detailNotice = null;
		
		try {
			detailNotice = noticeDao.detailNotice(notice_seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return detailNotice;
	}

	@Override
	public List<NoticeVO> searchNotice(NoticeVO notice) {
		List<NoticeVO> noticeList = null;
		
		try {
			noticeList = noticeDao.searchNotice(notice);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return noticeList;
	}

	@Override
	public List<NoticeVO> selectPage(Map<String, Integer> map) {

		List<NoticeVO> selectPage = null;
		
		try {
			selectPage = noticeDao.selectPage(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return selectPage;
	}

	@Override
	public int getTotalCount() {
		int count = 0;
		
		try {
			count = noticeDao.getTotalCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

}
