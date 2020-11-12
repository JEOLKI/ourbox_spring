package ourbox.room.service;

import java.io.DataOutput;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ourbox.common.vo.EnterVO;
import ourbox.common.vo.RoomVO;
import ourbox.room.dao.IRoomDao;
import ourbox.room.dao.RoomDaoImpl;

public class RoomServiceImpl implements IRoomService {

	private static IRoomService roomService;
	
	private IRoomDao roomDao;
	
	private RoomServiceImpl() {
		roomDao = RoomDaoImpl.getInstance();
	}
	
	public static IRoomService getInstnace() {
		if(roomService == null) {
			roomService = new RoomServiceImpl();
		}
		return roomService;
	}
	
	/**
	 * RoomVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param room DB에 insert할 자료가 저장된 RoomVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	@Override
	public int insertRoom(RoomVO room) {
		int cnt = 0;
		try {
			cnt = roomDao.insertRoom(room);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	/**
	 * 접속중인 사용자 mem_id를 이요하여 참여중인 roomList들을 select하는 메서드 
	 * @param 접속중인 사용자 mem_id
	 * @return List<RoomVO>
	 */
	@Override
	public List<RoomVO> listRoom(String mem_id) {
		List<RoomVO> roomList = new ArrayList<RoomVO>();
		try {
			roomList = roomDao.listRoom(mem_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roomList;
	}

	/**
	 * 그룹 하나를 선택하여 값 반환
	 */
	@Override
	public RoomVO selectRoom(String room_seq) {
		RoomVO rv = null;
		try {
			rv = roomDao.selectRoom(room_seq);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rv;
	}
	
	/**
	 * EnterVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param EnterVO ev
	 * @return 작업 성공 : 1, 작업실패 : 0
	 * @throws SQLException
	 */
	@Override
	public int insertEnter(EnterVO ev) {
		int cnt = 0;
		try {
			cnt = roomDao.insertEnter(ev);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteRoom(EnterVO ev) {
		int cnt = 0;
		try {
			cnt = roomDao.deleteRoom(ev);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public String getRoomName(int room_seq) {
		String room_name = "";
		try {
			room_name = roomDao.getRoomName(room_seq);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return room_name;
	}

}
