package ourbox.alarm.service;

import java.sql.SQLException;
import java.util.List;

import ourbox.alarm.dao.AlarmDaoImpl;
import ourbox.alarm.dao.IAlarmDao;
import ourbox.common.vo.AlarmVO;

public class AlarmServiceImpl implements IAlarmService {

private static IAlarmService alarmService;
	
	private IAlarmDao alarmDao;
	
	private AlarmServiceImpl() {
		alarmDao = AlarmDaoImpl.getInstance();
	}
	
	public static IAlarmService getInstance() {
		
		if(alarmService == null) {
			alarmService = new AlarmServiceImpl();
		}
		return alarmService;
	}

	@Override
	public List<AlarmVO> alarmList(String mem_id) {
		List<AlarmVO> alarmList = null;
		
		try {
			alarmList = alarmDao.alarmList(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alarmList;
	}

	@Override
	public int insertAlarm(AlarmVO alarm) {
		int cnt = 0;
		try {
			cnt = alarmDao.insertAlarm(alarm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	

	@Override
	public int deleteAlarm(int alarm_seq) {
		int cnt = 0;
		try {
			cnt = alarmDao.deleteAlarm(alarm_seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteAllAlarm(String mem_id) {
		int cnt = 0;
		try {
			cnt = alarmDao.deleteAllAlarm(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
}
