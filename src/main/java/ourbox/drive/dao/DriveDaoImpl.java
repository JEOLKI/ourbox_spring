package ourbox.drive.dao;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import ourbox.common.util.SqlMapClientFactory;
import ourbox.common.vo.DriveVO;

public class DriveDaoImpl implements IDriveDao {

	private SqlMapClient smc;
	
	private static IDriveDao driveDao;
	
	private DriveDaoImpl() {
			smc = SqlMapClientFactory.getInstance();
	}
	
	public static IDriveDao getInstance() {
		if(driveDao==null) driveDao = new DriveDaoImpl();
		return driveDao;
	}
	
	//////////////////////////// 이하 메서드 ////////////////////////////////
	@Override
	public List<DriveVO> driveList(int roomSeq) throws SQLException {
		return smc.queryForList("drive.driveList",roomSeq);
	}

	@Override
	public int createFolder(DriveVO newFolder) throws SQLException{
		return (int) smc.insert("drive.createDirectory",newFolder);
	}


	@Override
	public int fileUpload(DriveVO uploadFile) throws SQLException {
		return (int)smc.insert("drive.fileUpload", uploadFile);
	}
	
	@Override
	public DriveVO selectDrive(int drive_seq) throws SQLException{
		return (DriveVO)smc.queryForObject("drive.selectDownloadDrive", drive_seq);
	}


	@Override
	public int changeFileStatus(int drive_seq) throws SQLException {// 파일/폴더 휴지통으로
		return smc.update("drive.changeFileStatus", drive_seq);
	}



	@Override
	public List<DriveVO> searchDriveList(String keyword) throws SQLException {
		List<DriveVO> seachDriveList = new ArrayList<>();
		seachDriveList = smc.queryForList("drive.searchDriveList", keyword); 
		return seachDriveList;
	}


	@Override
	public int renameFile(DriveVO renamedFile) throws SQLException {
		return smc.update("drive.renameFile", renamedFile);
	}

	@Override
	public int moveFile(DriveVO movedFile) throws SQLException {
		return smc.update("drive.moveFile", movedFile);
	}

	@Override
	public List<DriveVO> garbageList(String mem_id) throws SQLException {
		return smc.queryForList("drive.garbageList", mem_id);
	}

	@Override
	public int deleteGarbages(String mem_id) throws SQLException {
		return  smc.delete("drive.deleteGarbages", mem_id);
	}


	@Override
	public int recycleFile(int drive_seq) throws SQLException {
		return smc.update("drive.recycleFile", drive_seq);
	}


	@Override
	public List<DriveVO> subDirList(String drive_path) throws SQLException {
		
		return smc.queryForList("drive.subDirList", drive_path);
	}

	@Override
	public int getUseDriveSize(String mem_id) throws SQLException {
		int size = 0;
		if(smc.queryForObject("drive.getUseDriveSize",mem_id)==null) {
			size = 0;
		}else {
			size = (int) smc.queryForObject("drive.getUseDriveSize",mem_id);
		}
		return size; 
	}

	@Override
	public int getPlanSize(String mem_id) throws SQLException {
		if(smc.queryForObject("drive.getPlanSize",mem_id)==null) {
			return 0; 
		}else {
			return (int) smc.queryForObject("drive.getPlanSize",mem_id);
		}
		
	}

	
	

}
