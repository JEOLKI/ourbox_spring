package ourbox.alarm.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourbox.common.util.SqlMapClientFactory;
import ourbox.common.vo.AlarmVO;

public class AlarmDaoImpl implements IAlarmDao {
	
	private static IAlarmDao alarmDao;
	
	private SqlMapClient smc;
	
	private AlarmDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IAlarmDao getInstance() {
		
		if(alarmDao == null) {
			alarmDao = new AlarmDaoImpl();
		}
		
		return alarmDao;
	}
	

	@Override
	public List<AlarmVO> alarmList(String mem_id) throws SQLException {
		return smc.queryForList("alarm.alarmList", mem_id);
	}

	@Override
	public int insertAlarm(AlarmVO alarm) throws SQLException {
		return (Integer) smc.insert("alarm.insertAlarm", alarm);
	}
	
	@Override
	public int deleteAlarm(int alarm_seq) throws SQLException {
		return smc.delete("alarm.deleteAlarm", alarm_seq);
	}

	@Override
	public int deleteAllAlarm(String mem_id) throws SQLException {
		return smc.delete("alarm.deleteAllAlarm", mem_id);
	}
	
}
