package ourbox.room.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import ourbox.common.vo.EnterVO;
import ourbox.common.vo.RoomVO;

public interface IRoomDao {
	/**
	 * RoomVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param room DB에 insert할 자료가 저장된 RoomVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertRoom(RoomVO room)throws SQLException;

	/**
	 * 접속중인 사용자 mem_id를 이요하여 참여중인 roomList들을 select하는 메서드 
	 * @param 접속중인 사용자 mem_id
	 * @return List<RoomVO>
	 */
	public List<RoomVO> listRoom(String mem_id) throws SQLException;
	
	/**
	 * 선택한 room의 정보를 가져오는 메서드
	 * @param room_seq
	 * @return room_seq에 해당하는 roomVO
	 */
	public RoomVO selectRoom(String room_seq) throws SQLException;
	
	/**
	 * EnterVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param EnterVO ev
	 * @return 작업 성공 : 1, 작업실패 : 0
	 * @throws SQLException
	 */
	public int insertEnter(EnterVO ev)throws SQLException;
	
	/**
	 * group에서 나가는 메서드
	 * @param ev
	 * @return
	 */
	public int deleteRoom(EnterVO ev)throws SQLException;
	
	/**
	 * room_seq로 String 객체 반환
	 * @param room_seq
	 * @return
	 * @throws SQLException
	 */
	public String getRoomName(int room_seq) throws SQLException;
}
