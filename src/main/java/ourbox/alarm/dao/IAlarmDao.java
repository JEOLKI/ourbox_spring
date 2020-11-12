package ourbox.alarm.dao;

import java.sql.SQLException;
import java.util.List;

import ourbox.common.vo.AlarmVO;

public interface IAlarmDao {
	/**
	 * 회원 id가 mem_id인 회원의 알람 리스트를 출력하는 메소드
	 * @param mem_id
	 * @return mem_id회원의 AlarmVO객체를 담은 List객체
	 */
	public List<AlarmVO> alarmList(String mem_id) throws SQLException;
	
	
	/**
	 * AlarmVO에 담겨진 자료를 DB에 insert하는 메소드
	 * @param alarm DB에 insert 할 자료가 들어있는 AlarmVO 객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int insertAlarm(AlarmVO alarm) throws SQLException;
	
	
	/**
	 * 알람 번호를 Param으로 받아서 해당 알람을 삭제하는 메서드
	 * @param alarm_seq
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int deleteAlarm(int alarm_seq) throws SQLException;
	
	/**
	 * memId회원이 갖고있는 알람을 전체 삭제하는 메소드
	 * @param mem_id
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int deleteAllAlarm(String mem_id) throws SQLException;
	
}
