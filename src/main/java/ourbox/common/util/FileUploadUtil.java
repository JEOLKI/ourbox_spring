package ourbox.common.util;

public class FileUploadUtil {
	
	// form-data; name="img"; filename="brown.png"
	// => sally.png
	
	// FileUploadUtilTest
	public static String getFilename(String contentDisposition) {
		
		String[] contents = contentDisposition.split("; ");
		
		for (int i = 0; i < contents.length; i++) {
			
			String[] content = contents[i].split("=");
			
			if(content[0].equals("filename")) return content[1].replace("\"", ""); // 더블 쿼테이션 제거

		}
		
		return "";
	}
	
	// filename : sally.png => png
	public static String getExtension(String filename) {
		
		if(filename == null || filename.indexOf(".") == -1) return ""; // .이 없을때
		else return filename.split("\\.")[1];
		
	}
	
}
