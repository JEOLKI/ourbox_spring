package ourbox.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CustomEncodingFilter implements Filter{

	private String encoding; // 인코딩 정보
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		
		// 인코딩 설정
		req.setCharacterEncoding(encoding); 
		resp.setCharacterEncoding(encoding);
		
		fc.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
		if (config.getInitParameter("encoding") ==  null) {
			encoding = "utf-8";
		} else {
			encoding = config.getInitParameter("encoding");
		}
	}
}
