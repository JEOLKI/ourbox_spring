package ourbox.drive.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.fileupload.FileItem;
import org.apache.naming.resources.DirContextURLConnection;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import ourbox.common.util.FileUploadRequestWrapper;
import ourbox.common.vo.DriveVO;
import ourbox.drive.dao.DriveDaoImpl;
import ourbox.drive.dao.IDriveDao;
import ourbox.drive.util.CompressZip;

public class DriveServiceImpl implements IDriveService {

	private static IDriveService driveService;
		
	private IDriveDao driveDao;
	
	private DriveServiceImpl() {
		driveDao = DriveDaoImpl.getInstance();
	}
	
	public static IDriveService getInstance() {
		if(driveService == null) driveService = new DriveServiceImpl();
		return driveService;
	}
	
	
	// 드라이브 리스트 출력
	@Override
	public List<DriveVO> driveList(int roomSeq) {
		List<DriveVO> driveList = null;
		
		try {
			driveList = driveDao.driveList(roomSeq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return driveList;
	}
	
	// 폴더 생성
	@Override
	public int createFolder(DriveVO newFolder) {
		
		int cnt = 0;	// 성공시 1, 실패 0
		File Folder = new File(newFolder.getDrive_path());
		if(Folder.exists()) {
			return cnt;
		}
		if (!Folder.exists()) {
			 Folder.mkdirs(); //폴더 생성합니다.
					try {
						cnt = driveDao.createFolder(newFolder);
					} catch (SQLException e) {
						e.printStackTrace();
					}
			
		}
		
		return cnt;
	}


	@Override
	public int fileUpload(DriveVO sourceFile) {
		int result = 0;
		File uploadFile = new File(sourceFile.getDrive_path());
		try {
			result = driveDao.fileUpload(sourceFile);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String zipDownloadDir(int drive_seq) {
		String downloadFolderName = "";
		
		// 압축 파일 위치와 압축된 파일
		// String zipPath = "G:/ZIP_TEST/"; 
		// String zipFile = "jsmpeg-player-master.zip"; 
		// 압축을 해제할 위치, 압축할 파일이름 
		//String unZipPath = "G:/ZIP_TEST/TEST/"; 
		//String unZipFile = "jsmpeg-player"; 
		// System.out.println("--------- 압축 해제 ---------"); 
		// UnZip unZip = new UnZip(); 
		// // 압축 해제
		// if (!unZip.unZip(zipPath, zipFile, unZipPath)) { 
			// System.out.println("압축 해제 실패"); 
		// } 
		
		DriveVO downloadFolder = new DriveVO();
		try {
			downloadFolder = driveDao.selectDrive(drive_seq);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String zipPath = downloadFolder.getDrive_path();
		String zipFolder = downloadFolder.getDrive_name();
			
		System.out.println("--------- 압축 하기 ---------"); 
		CompressZip compressZip = new CompressZip(); 
		// 압축 하기 
		try { 
			if (!compressZip.compress(zipPath+zipFolder, zipPath, zipFolder)) {
				System.out.println("압축 실패");
			} else {
				downloadFolderName = zipFolder;
			}
		} catch (Throwable e) { 
			e.printStackTrace();
		}
		return downloadFolderName+".zip";
	}

	
	@Override
	public DriveVO selectDrive(int drive_seq){
		DriveVO downloadFile = new DriveVO();
		try {
			downloadFile = driveDao.selectDrive(drive_seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return downloadFile;
	}

	@Override
	public int changeDriveStatus(int drive_seq) {	// 파일 휴지통에 버리기
		int result = 0;

			try {
				result = driveDao.changeFileStatus(drive_seq);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		return result;
	}


	@Override
	public List<DriveVO> searchDriveList(String keyword) {
		List<DriveVO> searchDriveList = new ArrayList<>();
		try {
			searchDriveList = driveDao.searchDriveList(keyword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchDriveList;
	}


	@Override
	public int copyDrive(File sourceFile, File targetFile) {
		File[] target_file = sourceFile.listFiles();
		if(sourceFile.listFiles() != null) {
			
			for (File file : target_file) {
				File temp = new File(targetFile.getAbsolutePath() + File.separator + file.getName());
				if(file.isDirectory()){
					temp.mkdir();
					copyDrive(file, temp);
				} else {
					FileInputStream fis = null;
					FileOutputStream fos = null;
					try {
						fis = new FileInputStream(file);
						fos = new FileOutputStream(temp) ;
						byte[] b = new byte[4096];
						int cnt = 0;
						while((cnt=fis.read(b)) != -1){
							fos.write(b, 0, cnt);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally{
						try {
							fis.close();
							fos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}	
					}
				}
			}			
		}
		return 0;
	}


	@Override
	public int pasteDrive(DriveVO sourceDrive, String targetPath, int pasteRoomSeq) {
		int result = 0;
		System.out.println("붙여넣기  파라미터패스 : "+targetPath);
		File sourceFile = new File(sourceDrive.getDrive_path());
		File targetFile = new File(targetPath);
		copyDrive(sourceFile, targetFile);
		String copiedFilePath = targetFile.getAbsolutePath(); 
		File copiedFile = new File(copiedFilePath);
		File[] fileList = copiedFile.listFiles(); 
		
		
		
		
		int insertCount = uploadDBCopyFiles(sourceDrive,copiedFilePath, pasteRoomSeq);	// 두번째 파라미터 룸넘
		
		return insertCount;
	}
	
	
	@Override
	public int deleteDrive(String path) {
		int result = 0;
		File folder = new File(path);
		try {
			if(folder.exists()){
			    File[] folder_list = folder.listFiles();
					
			    for (int i = 0; i < folder_list.length; i++) {
				if(folder_list[i].isFile()) {
					folder_list[i].delete();
				}else {
					deleteDrive(folder_list[i].getPath());
				}
				folder_list[i].delete();
			    }
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	    
		return result;
	}

	
	/**
	 * 복사된 폴더의 각각을 db에 insert하는 메서드
	 * @param copiedFilePath
	 * @return
	 */
	public int uploadDBCopyFiles(DriveVO sourceDrive, String copiedFilePath, int pasteRoomSeq) {
		// 디비에 날릴때 필요한 정보
		// 이름, 룸넘, 경로
		// 이름 : 경로.substring(경로.lastIndexOf("//")+1)
		// 룸넘 : 경로.substring(경로.lastIndexOf("//"))에서 마지막 글자인 숫자 2
		//		(경로.substring(경로.lastIndexOf("//"))).length-1 substring
		System.out.println("copiedFilePath : "+copiedFilePath);
		
		String copiedDriveName = copiedFilePath.substring(copiedFilePath.lastIndexOf("\\")+1);
		String leftPath = copiedFilePath.substring(0,copiedFilePath.lastIndexOf("\\"));
		System.out.println("leftPath : "+leftPath);
		System.out.println("copiedDriveName : "+copiedDriveName);
				
		DriveVO copiedDrive = new DriveVO();
		copiedDrive.setDrive_name(copiedDriveName);
		copiedDrive.setDrive_path(copiedFilePath);
		copiedDrive.setRoom_seq(pasteRoomSeq);
		copiedDrive.setDrive_type(sourceDrive.getDrive_type());
		copiedDrive.setDrive_size(sourceDrive.getDrive_size());
		
		System.out.println("파일 ? "+ copiedFilePath.lastIndexOf("."));
		int count = 0;
		
//		File copiedFile = new File(copiedFilePath);
//		//List<Integer> insertCount = new ArrayList<>();
//		if(copiedFilePath.lastIndexOf(".")<0) {
//			System.out.println("대상 있니? " + copiedFile.exists());
//			File[] fileList = copiedFile.listFiles(); 
//			System.out.println("하위폴더 개수 :  "+fileList.length);
//			try{
//				for(int i = 0 ; i < fileList.length ; i++){
//					File file = fileList[i];
//				
//					if(file.isFile()){
//						// 파일이 있다면 파일을 db에 insert
//						try {
//							count = driveDao.fileUpload(copiedDrive);
//							//insertCount.add(count);
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//					}else if(file.isDirectory()){
//						try {
//							count = driveDao.fileUpload(copiedDrive);
//							//insertCount.add(count);
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//						System.out.println("디렉토리 이름 = " + file.getName());
//						uploadDBCopyFiles(,file.getCanonicalPath().toString(), pasteRoomSeq);		// 두번째 파라미터 룸넘 
//					}
//				}
//			}catch(IOException e){
//			}
//		} else 
//			
			
			if(copiedFilePath.lastIndexOf(".") > 0) {
			try {
				count = driveDao.fileUpload(copiedDrive);
				//insertCount.add(count);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}


	@Override
	public int renameDir(DriveVO sourceDir, String newDirName) {
		int result = 0;
		
		String orignPath = sourceDir.getDrive_path();
		
		String presetPath = orignPath.substring(0, orignPath.lastIndexOf("\\")+1);
		String newPath = presetPath+ newDirName;
		
		File srcFile = new File(orignPath);
		boolean success = srcFile.renameTo(new File(newPath));
		if (success) {
			try {
				List<DriveVO> subDirList = driveDao.subDirList(orignPath); // 폴더인경우 하위폴더의 경로도 바꿔주기 위해 하위폴더리스트 추출
				for(int i =1; i < subDirList.size(); i++) {
					if(subDirList.get(i).getDrive_path().equals(newPath)) { // 최상위폴더 자신이라면
						subDirList.get(i).setDrive_path(newPath);
						result = driveDao.renameFile(subDirList.get(i));
					} else {
						DriveVO subDir = subDirList.get(i);
						// 하위파일 이름 얻기
						String subDirName = subDir.getDrive_name();
						
						// 경로값 최상위폴더경로 + 본래 이름
						subDir.setDrive_path(newPath+File.separator+subDirName);
						result = driveDao.renameFile(subDir);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@Override
	public int renameFile(DriveVO sourceFile, String newFileName) {
		int result = 0;
		// 이름.타입 붙여주기위해
		String driveType = sourceFile.getDrive_type();
		System.out.println();
		System.out.println();
		
		
		String presetPath = sourceFile.getDrive_path().substring(0, sourceFile.getDrive_path().lastIndexOf("\\")+1);
		
		File srcFile = new File(sourceFile.getDrive_path());
		boolean success = srcFile.renameTo(new File(presetPath + newFileName+"."+driveType));
		if (success) {
			try {
				DriveVO renamedFile = new DriveVO();
				renamedFile.setDrive_seq(sourceFile.getDrive_seq());
				renamedFile.setDrive_path(presetPath + newFileName+"."+driveType);
				renamedFile.setDrive_name(newFileName+"."+driveType);
				result = driveDao.renameFile(renamedFile);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   
		}
		return 0;
	}


	@Override
	public int moveFile(DriveVO sourceFile, String targetPath) {
		int result = 0;
		try {
		    Path sourceFilePath = Paths.get(sourceFile.getDrive_path());
		    Path filePathMoveTo = Paths.get(targetPath+ sourceFile.getDrive_name());
		    Files.move(sourceFilePath, filePathMoveTo);
		    
		    DriveVO movedFile = new DriveVO();
		    movedFile.setDrive_path(targetPath + sourceFile.getDrive_name());
		    movedFile.setDrive_seq(sourceFile.getDrive_seq());
		    
		    try {
				result = driveDao.moveFile(movedFile);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return result;
	}

	/**
	 * 하위폴더 탐색 메서드
	 * @param source
	 */
	public void subDirList(String source){
		File dir = new File(source); 
		File[] fileList = dir.listFiles(); 
		try{
			for(int i = 0 ; i < fileList.length ; i++){
				File file = fileList[i]; 
				if(file.isFile()){
					// 파일이 있다면 파일 이름 출력
					System.out.println("\t 파일 이름 = " + file.getName());
				}else if(file.isDirectory()){
					// 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색
					subDirList(file.getCanonicalPath().toString()); 
				}
			}
		}catch(IOException e){
		}
	}


	
/////////////////////////////////////// 이하 휴지통 ////////////////////////////////////////	
	
	@Override
	public List<DriveVO> garbageList(String mem_id) {
		List<DriveVO> garbageList = new ArrayList<DriveVO>();
		try {
			garbageList= driveDao.garbageList(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return garbageList;
	}



	
	@Override
	public int deleteGarbages(String garbagePath,String mem_id) { // db에서 상태값 0인거 전부 삭제
		int result = 0;
		
		List<DriveVO> garbageList = garbageList(mem_id);
		System.out.println(garbageList.size());
		for(int i=0; i<garbageList.size(); i++) {
			
			File garbageFile = new File(garbageList.get(i).getDrive_path());
			garbageFile.delete();
		}
		
		try {
			result = driveDao.deleteGarbages(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}


	@Override
	public int recycleFile(int drive_seq) {
		int cnt = 0;
		try {
			cnt = driveDao.recycleFile(drive_seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}


	@Override
	public int recycleDir(DriveVO toRecycleDrive) {	// 휴지통 복원
		int result = 0;
		String path = toRecycleDrive.getDrive_path();
		
		List<DriveVO> toRecycleList = null;
		try {
			toRecycleList = driveDao.subDirList(path);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(int i =0; i<toRecycleList.size(); i++) {
			try {
				result = driveDao.recycleFile(toRecycleList.get(i).getDrive_seq());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public int getUseDriveSize(String mem_id) { // 사용중인 파일 용량 가져오기
		int cnt = 0;
		try {
			cnt = driveDao.getUseDriveSize(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int getPlanSize(String mem_id){
		int cnt = 0;
		try {
			cnt = driveDao.getPlanSize(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	
	
}
