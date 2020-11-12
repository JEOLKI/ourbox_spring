package ourbox.drive.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;

import ourbox.common.vo.DriveVO;

public interface IDriveDao {
	/**
	 * 드라이브에 들어갔을 때 전체목력을 출력하는 메서드
	 * @return
	 * @throws SQLException
	 */
	public List<DriveVO> driveList(int roomSeq) throws SQLException;
	
	/**
	 * 경로와 이름을 받아 폴더를 생성하는 메서드 
	 * @param newFolder 새폴더 객체
	 * @return 작업성공 : 1, 작업실패 0
	 * @throws SQLException
	 */
	public int createFolder(DriveVO newFolder) throws SQLException;
	
	
	/**
	 * 파일객체와 경로를 받아 파일을 업로드하는 메서드
	 * @param file 업로드할 파일  
	 * @return 작업성공 : 1, 작업실패 0
	 * @throws SQLException
	 */
	public int fileUpload(DriveVO file) throws SQLException;

	/**
	 * 파일/폴더 객체 정보를 가져오는 메서드
	 * @param drive_seq 파일/폴더 번호
	 * @return 파일/폴더 번호에 해당하는 객체
	 * @throws SQLException
	 */
	public DriveVO selectDrive(int drive_seq) throws SQLException;
	
	/**
	 * 파일을 삭제하여 휴지통에 넣는 메서드
	 * @param drive_seq 삭제할 파일 번호
	 * @return 작업성공 : 1, 작업실패 0
	 * @throws SQLException
	 */
	public int changeFileStatus(int drive_seq) throws SQLException;
	

	/**
	 * 드라이브 검색결과 출력 메서드
	 * @param keyword 검색할 키워드를
	 * @return 검색 결과  drive 객체를 담은 리스트
	 * @throws SQLException
	 */
	public List<DriveVO> searchDriveList(String keyword) throws SQLException;  
	
	/**
	 * 이름변경된 파일의 정보를 db에 반영하는 메서드
	 * @param renamedFile 이름변경된 파일
	 * @return 작업성공 : 1, 작업실패 0
	 * @throws SQLException
	 */
	public int renameFile(DriveVO renamedFile) throws SQLException;
	
	/**
	 * 파일의 위치를 변경하고 db에 변경사항 반영하는 메서드
	 * @param movedFile 이동된 파일객체
	 * @return 작업성공 : 1, 작업실패 0
	 * @throws IOException
	 */
	public int moveFile(DriveVO movedFile) throws SQLException;
	
	/**
	 * 휴지통의 모든 파일을 출력하는 메서드
	 * @return 휴지통에 있는 모든 파일
	 * @throws SQLException
	 */
	public List<DriveVO> garbageList(String mem_id) throws SQLException;
	
	/**
	 * 휴지통에 있는 모든 파일을 삭제하는 메서드
	 * @return 작업성공 : 1, 작업실패 0
	 * @throws SQLException
	 */
	public int deleteGarbages(String mem_id) throws SQLException;
	

	/**
	 * 휴지통의 파일을 복원하는 메서드
	 * 휴지통에 넣기 전 경로로 복원
	 * @param sorceFile 복원하려는 파일
	 * @return 작업성공 : 1, 작업실패 0
	 * @throws IOException
	 */
	public int recycleFile(int drive_seq) throws SQLException;
	
	
	/**
	 * 폴더경로 하위 항목리스트를 추출하는 메서드
	 * @param dirPath
	 * @return
	 * @throws SQLException
	 */
	public List<DriveVO> subDirList(String dirPath) throws SQLException;
	
	/**
	 * 사용자가 사용하고 있는 파일들의 사이즈
	 * @throws SQLException
	 */
	public int getUseDriveSize(String mem_id) throws SQLException;
	
	/**
	 * 사용자가 사용하고 있는 요금제의  사이즈
	 * @throws SQLException
	 */
	public int getPlanSize(String mem_id) throws SQLException;
	
	
	
}
