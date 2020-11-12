package ourbox.common.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

// daoimpl에있던거 공유해서 쓰기위해
public class SqlMapClientFactory {
	
	private static SqlMapClient smc;
	
	static Reader rd;
	
	// new 생성전에 실행
	static {
		try {
			// xml문서 읽어오기
			Charset charset = Charset.forName("UTF-8"); // 설정파일의 인코딩 설정
			Resources.setCharset(charset);
			rd = Resources.getResourceAsReader("ourbox/common/config/SqlMapConfig.xml");
			
			// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); // Reader 객체 닫기
			
		} catch (IOException e) {
			System.out.println("SqlMapClient객체 생성 실패!!");
			e.printStackTrace();
		}
	}
	
	
	public static SqlMapClient getInstance() {
		return smc;
	}
	
	
	/*
	private static SqlMapClient smc; // SqlMapClient객체변수 선언
	
	private SqlMapClientFactory() {}; // 객체를 얻어쓸때에는 싱글톤으로 변경하는것이 좋다
	
	public static SqlMapClient getInstance() {
		if(smc == null) {
			Reader rd;
			
			try {
				// 1-1. xml문서 읽어오기
				Charset charset = Charset.forName("UTF-8"); // 설정파일의 인코딩 설정
				Resources.setCharset(charset);
				rd = Resources.getResourceAsReader("ourbox/common/config/SqlMapConfig.xml");
				
				// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				
				rd.close(); // Reader 객체 닫기
				
			} catch (IOException e) {
				System.out.println("SqlMapClient객체 생성 실패!!");
				e.printStackTrace();
			}
		}
		
		return smc;
	}*/
	
}
