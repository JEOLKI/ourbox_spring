package ourbox.chat.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import ourbox.chat.service.ChatServiceImpl;
import ourbox.chat.service.IChatService;
import ourbox.common.vo.ChatVO;
import ourbox.common.vo.MemberVO;


@ServerEndpoint(value = "/webChatServer", configurator = HttpSessionConfigurator.class)
public class WebChatServer extends HttpServlet {
	
	private static Map<Session,ChatVO> users = Collections.synchronizedMap(new HashMap<Session, ChatVO>());
	
	private Map<Session, EndpointConfig> configs = Collections.synchronizedMap(new HashMap<>());
	
	private	IChatService chatService = ChatServiceImpl.getInstance();
	
	
	public String getNickName(HttpSession session) {
		String userName = users.get(session).getMem_id();
		IChatService chatService = ChatServiceImpl.getInstance();
		
		String nickName = chatService.getNickName(userName);
		
		return nickName;
	}
	
	@OnMessage
	public void onMsg(String message, Session session) throws IOException{
		
		EndpointConfig config = configs.get(session);
		
		// HttpSessionConfigurator에서 설정한 session값을 가져온다.
		HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSessionConfigurator.Session);


		//System.out.println("ChatId = > " + httpSession.getAttribute("ChatId"));
		//System.out.println("ChatId = > " + httpSession.getAttribute("ChatId"));

		//String userName = "userName"; //getNickName(httpSession);
		//String userName = "httpSession.getAttribute(\"ChatId\")"; //getNickName(httpSession);
				//users.get(session).getMem_id();

		String userName = (String) httpSession.getAttribute("mem_id");
		
		String nickName = chatService.getNickName(userName);
		
		//httpSession.getAttribute("mem_id")
		System.out.println(nickName + " : " + message);
		
		synchronized (users) {
			Iterator<Session> it = users.keySet().iterator();
			while(it.hasNext()){
				Session currentSession = it.next();
				if(!currentSession.equals(session)){
					currentSession.getBasicRemote().sendText(nickName + " : " + message);
					//httpSession.getAttribute("mem_id")
					
					//---------------------------
					ChatVO vo = new ChatVO();
					//Chat vo = loginMember(chat);         
					
					MemberVO mv = new MemberVO();
					
					mv = (MemberVO) httpSession.getAttribute("vo");
					
					
					httpSession.setAttribute("mem_id", mv.getMem_id()); 	//세션에서 얻어온 id
					httpSession.setAttribute("room_seq", 296);	//세션에서 얻어온 roomNum
					httpSession.setAttribute("chat_content", message);
					//String mem_id = httpSession.getAttribute("ChatId", "id1");
					
					
					
					vo.setMem_id((String)httpSession.getAttribute("mem_id"));
					vo.setRoom_seq((int)httpSession.getAttribute("room_seq"));
					vo.setChat_content((String)httpSession.getAttribute("chat_content"));
					
					//--------------------------
//					httpSession.setAttribute("room_seq", 1);
//					httpSession.setAttribute("chat_content", "zz");
					//--------------------------
					int cnt = chatService.insertChat(vo);
					
					if(cnt==1) {
						System.out.println("db insert success");
					}else {
						System.out.println("fail");
					}
				}
			}
		}
		
	}
	
	@OnOpen
	public void onOpen(Session session, EndpointConfig config){
		
		// EndpointConfig의 클래스를 위 맵에 넣는다.
		if (!configs.containsKey(session)) {
		// userSession 클래스는 connection이 될 때마다 인스턴스 생성되는 값이기 때문에 키로서 사용할 수 있다.
		configs.put(session, config);
		}

		// HttpSessionConfigurator에서 설정한 session값을 가져온다.
		HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSessionConfigurator.Session);
		
		MemberVO mv = new MemberVO();
		
		mv = (MemberVO) httpSession.getAttribute("vo");
		
		httpSession.setAttribute("mem_id", mv.getMem_id() );
		//--------------------------
		//httpSession.setAttribute("room_seq", 1);
		//httpSession.setAttribute("chat_content", "??????");
		//--------------------------
		
		
		
		//httpSession.setAttribute("ChatId", session.getId());
		
		//String nickName = chatService.getNickName("session.getID()");
		
		
		String userName = (String) httpSession.getAttribute("mem_id");
		String nickName = chatService.getNickName(userName);
		
		
		
		
		ChatVO client = new ChatVO();
		//System.out.println(session);
		client.setMem_id(nickName);
		
		System.out.println(session + " connect");
		
		users.put(session, client);
		sendNotice(nickName + "님이 입장하셨습니다.<br> 현재 사용자 " + users.size() + "명");
	}
	
	public void sendNotice(String message){
		String userName = "server";
		System.out.println(userName + " : " + message);
		

		
		synchronized (users) {
			Iterator<Session> it = users.keySet().iterator();
			while(it.hasNext()){
				Session currentSession = it.next();
				try {
		
					currentSession.getBasicRemote().sendText(userName + " : " + message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@OnClose
	public void onClose(Session session) {
		String userName = users.get(session).getMem_id();
		users.remove(session);
		sendNotice(userName + "님이 퇴장하셨습니다. 현재 사용자 " + users.size() + "명");
	}


}
