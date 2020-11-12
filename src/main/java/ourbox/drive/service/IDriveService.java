package ourbox.drive.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;

import org.apache.commons.fileupload.FileItem;

import ourbox.common.vo.DriveVO;

public interface IDriveService {
	
	/**
	 * 드라이브에 들어갔을 때 전체목력을 출력하는 메서드
	 * @return
	 */
	public List<DriveVO> driveList(int roomSeq);
	
	/**
	 * 경로와 이름을 받아 폴더를 생성하는 메서드 
	 * @param dir 폴더 생성위치
	 * @param driveName 폴더 이름
	 * @return 작업성공 : 1, 작업실패 0
	 */
	public int createFolder(DriveVO newFolder);
	

	/**
	 * 멀티파트 형식으로  전송된 파일 아이템
	 * @param item 
	 * @return 작업성공 : 1, 작업실패 0
	 */
	public int fileUpload(DriveVO uploadFile);

	/**
	 * 폴더를 압축하는 메서드(다운로드용)
	 * @param drive_seq 다운로드 할 폴더의 번호
	 * @return 압축한 폴더 이름
	 */
	public String zipDownloadDir(int drive_seq);
	
	/**
	 * 드라이브객체를 가져오는 메서드
	 * @param drive_seq 소스파일 번호  
	 * @return 파일 번호에 해당하는 드라이브 객체
	 */
	public DriveVO selectDrive(int drive_seq);
	
	/**
	 * 파일/폴더를 삭제하여 휴지통에 넣는 메서드
	 * @param drive_seq 삭제할 파일/폴더경로
	 * @return 작업성공 : 1, 작업실패 0
	 */
	public int changeDriveStatus(int drive_seq);
	

	/**
	 * 드라이브 검색결과 출력 메서드
	 * @param vo 검색할  property와 키워드를 담은 drive객체
	 * @return 검색 결과  drive 객체를 담은 리스트
	 */
	public List<DriveVO> searchDriveList(String keyword); 
	
	
	/**
	 * 파일/드라이브를 복사하여 임시저장하는 메서드
	 * @param sourceFile 복사하고자 하는 파일
	 * @return 작업성공 : 1, 작업실패 0
	 */
	public int copyDrive(File sourceFile, File targetFile);
	
	/**
	 * 복사하여 임시저장된 파일/폴더를 붙여넣는 메서드
	 * @param targetDir 복사할 소스파일
	 * @param targetPath 파일을 붙여넣고자 하는 경로
	 * @param targetPath 파일을 붙여넣고자 하는 그룹번호
	 * @return 작업성공 : 1, 작업실패 0
	 */
	public int pasteDrive(DriveVO sourceDrive, String targetPath, int pasteRoomSeq);
	
	/**
	 * 휴지통 버리기,복원 시  파일/폴더를 원래 위치에서 잘라내는 메서드 
	 * @param path
	 * @return
	 */
	public int deleteDrive(String path);

	/**
	 * 폴더의 이름을 변경하는 메서드
	 * @param orginDirName 원본 폴더 이름
	 * @param newDirName 새로운 폴더 이름
	 * @return 작업성공 : 1, 작업실패 0
	 */
	public int renameDir(DriveVO sourceFile, String newDirName);
	
	/**
	 * 파일의 이름을 변경하는 메서드
	 * @param sourceFile 원본 파일 
	 * @param newFileName newFileName
	 * @return 작업성공 : 1, 작업실패 0
	 */
	public int renameFile(DriveVO sourceFile, String newFileName);
	
	
	
	/**
	 * 파일의 위치를 변경하는 메서드
	 * 복사하여 붙여넣고 원본경로의 파일 삭제
	 * @param sourceFile 원본 파일 경로
	 * @param targetFile 변경 대상 경로
	 * @return 작업성공 : 1, 작업실패 0
	 */
	public int moveFile(DriveVO sourceFile, String targetPath);
	
	/**
	 * 휴지통의 모든 파일을 출력하는 메서드
	 * @param mem_id
	 * @return 휴지통에 있는 모든 파일
	 */
	public List<DriveVO> garbageList(String mem_id);
	
	/**
	 * 휴지통에 있는 모든 파일을 삭제하는 메서드
	 * @param garbagePath 휴지통 경로
	 * @return 작업성공 : 1, 작업실패 0
	 */
	public int deleteGarbages(String garbagePath, String mem_id);
	
	/**
	 * 휴지통의 폴더를 복원하는 메서드
	 * 휴지통에 넣기 전 경로로 복원
	 * @param sorceDir 복원하려는 폴더
	 * @return 작업성공 : 1, 작업실패 0
	 */
	public int recycleDir(DriveVO sorceDir);
	
	/**
	 * 휴지통의 파일을 복원하는 메서드
	 * 휴지통에 넣기 전 경로로 복원
	 * @param drive_seq 복원하려는 파일 번호
	 * @return 작업성공 : 1, 작업실패 0
	 */
	public int recycleFile(int drive_seq);
	
	/**
	 * 사용자가 사용하고 있는 파일들의 사이즈
	 * @throws SQLException
	 */
	public int getUseDriveSize(String mem_id);
	
	/**
	 * 사용자가 사용하고 있는 요금제의  사이즈
	 * @throws SQLException
	 */
	public int getPlanSize(String mem_id);
	
	

	
	
	
	
	
	
		
}
