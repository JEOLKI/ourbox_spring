package ourbox.room.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourbox.common.util.SqlMapClientFactory;
import ourbox.common.vo.EnterVO;
import ourbox.common.vo.RoomVO;

public class RoomDaoImpl implements IRoomDao {
	private SqlMapClient smc; 
	
	private static IRoomDao roomDao;
	
	private RoomDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IRoomDao getInstance() {
		if(roomDao==null) {
			roomDao = new RoomDaoImpl();
		}
		return roomDao;
	}
	
	/**
	 * RoomVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param room DB에 insert할 자료가 저장된 RoomVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	@Override
	public int insertRoom(RoomVO room) throws SQLException {
		Object obj = smc.insert("room.insertRoom",room);
		int cnt = 0;
		if(obj==null) {
			cnt = 1;
		}
		return cnt;
	}

	/**
	 * 접속중인 사용자 mem_id를 이요하여 참여중인 roomList들을 select하는 메서드 
	 * @param 접속중인 사용자 mem_id
	 * @return List<RoomVO>
	 */
	@Override
	public List<RoomVO> listRoom(String mem_id) throws SQLException {
		List<RoomVO> roomList = null;
		roomList = smc.queryForList("room.listRoom",mem_id);
		return roomList;
	}

	/**
	 * 선택한 room의 정보를 가져오는 메서드
	 * @param room_seq
	 * @return room_seq에 해당하는 roomVO
	 */
	@Override
	public RoomVO selectRoom(String room_seq) throws SQLException {
		RoomVO rv = null;
		rv = (RoomVO) smc.queryForObject("room.selectRoom",room_seq);
		return rv;
	}
	
	/**
	 * EnterVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param EnterVO ev
	 * @return 작업 성공 : 1, 작업실패 : 0
	 * @throws SQLException
	 */
	@Override
	public int insertEnter(EnterVO ev) throws SQLException {
		Object obj = smc.insert("room.insertEnter",ev);
		int cnt = 0;
		if(obj == null) {
			cnt = 1;
		}
		return cnt;
	}

	@Override
	public int deleteRoom(EnterVO ev) throws SQLException {
		return smc.update("room.deleteRoom",ev);
	}

	@Override
	public String getRoomName(int room_seq) throws SQLException {
		String room_name= "";
		room_name = (String)smc.queryForObject("room.getRoomName",room_seq);
		return room_name;
	}

}
