package ourbox.member.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import ourbox.common.vo.MemberVO;
import ourbox.member.service.IMemberService;
import ourbox.member.service.MemberServiceImpl;

@WebServlet(description = "MemberProfile", urlPatterns = { "/MemberProfile" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, 
				 maxFileSize = 1024 * 1024 * 5, 
				 maxRequestSize = 1024 * 1024 * 5 * 5)
public class MemberProfile extends HttpServlet {
	
	private static final String DEFAULT_FILENAME = "default.file";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mem_id = request.getParameter("mem_id");
		
		String uploadPath = "D:\\A_TeachingMaterial\\4.MiddleProject\\workspace\\"
				+ "ourbox\\WebContent\\images" + File.separator;
		
		
		
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {	// 경로가 존재하지 않으면
			uploadDir.mkdir();
		}
		
		try {
			String fileName = "";
			int fileSize = 0;
			String fileType = "";
			for(Part part : request.getParts()) {
				fileName = getFileName(part);
				part.write(uploadPath + File.separator + fileName);	// 파일 저장
			}
			
			MemberVO mv = new MemberVO();
			mv.setMem_id(mem_id);
			mv.setMem_profile(fileName);
			IMemberService memberService = MemberServiceImpl.getInstance();
			
			int result = memberService.profile(mv);
			
			request.setAttribute("result", result);
			
			
			request.getRequestDispatcher("view/member/profile.jsp").forward(request, response);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 파일 이름 추출하기
	 * @param part
	 * @return
	 */
	private String getFileName(Part part) {
		for(String content : part.getHeader("content-disposition").split(";")) {
			if(content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=")+ 2, content.length() - 1);
			}
		}
		return DEFAULT_FILENAME;
	}
	

}
