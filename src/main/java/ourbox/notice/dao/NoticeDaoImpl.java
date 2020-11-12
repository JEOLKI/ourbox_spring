package ourbox.notice.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourbox.common.util.SqlMapClientFactory;
import ourbox.common.vo.NoticeVO;

public class NoticeDaoImpl implements INoticeDao {

	private SqlMapClient smc;
	
	private static INoticeDao noticeDao;
	
	private NoticeDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static INoticeDao getInstance() {
		
		if(noticeDao == null) {
			noticeDao = new NoticeDaoImpl();
		}
		
		return noticeDao;
	}
	
	
	@Override
	public List<NoticeVO> noticeList() throws SQLException {
		return smc.queryForList("notice.noticeList");
	}

	@Override
	public int insertNotice(NoticeVO notice) throws SQLException {
		return (Integer) smc.insert("notice.insertNotice",notice);
	}

	@Override
	public int updateNotice(NoticeVO notice) throws SQLException {
		return smc.update("notice.updateNotice", notice);
	}

	@Override
	public int deleteNotice(int notice_seq) throws SQLException {
		return smc.delete("notice.deleteNotice", notice_seq);
	}

	@Override
	public NoticeVO detailNotice(int notice_seq) throws SQLException {
		return (NoticeVO) smc.queryForObject("notice.detailNotice", notice_seq);
	}

	@Override
	public List<NoticeVO> searchNotice(NoticeVO notice) throws SQLException {
		return smc.queryForList("notice.searchNotice", notice);
	}

	@Override
	public List<NoticeVO> selectPage(Map<String, Integer> map) throws SQLException {
		return smc.queryForList("notice.selectPage", map);
	}

	@Override
	public int getTotalCount() throws SQLException {
		return (Integer) smc.queryForObject("notice.getTotalCount");
	}

}
