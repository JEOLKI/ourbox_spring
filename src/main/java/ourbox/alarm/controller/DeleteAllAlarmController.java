package ourbox.alarm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourbox.alarm.service.AlarmServiceImpl;
import ourbox.alarm.service.IAlarmService;

@WebServlet("/DeleteAllAlarmController")
public class DeleteAllAlarmController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 파라미터 받아오기
		String mem_id = request.getParameter("mem_id");
				
		// service객체 생성하기
		IAlarmService alarmService = AlarmServiceImpl.getInstance();
			
		// 메소드 호출하기
		int cnt = alarmService.deleteAllAlarm(mem_id);
		
		// request에 저장
		request.setAttribute("result", cnt);
		
		// jsp로
		request.getRequestDispatcher("view/common/result.jsp").forward(request, response);
	
	}


}
