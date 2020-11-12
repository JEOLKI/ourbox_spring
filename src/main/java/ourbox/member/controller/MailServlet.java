package ourbox.member.controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MailServlet")
public class MailServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String Id1 = req.getParameter("EmailID1");
      String Id2 = req.getParameter("EmailDomain1");

      // mail server 설정
      String host = "smtp.naver.com";
      String user = "dongju5439@naver.com"; // 자신의 네이버 계정
      String password = "13dlehdwn13^^";// 자신의 네이버 패스워드

      // 메일 받을 주소
      /* String to_email = m.getEmail(); */
      String to_email = Id1.trim()+"@"+ Id2.trim();

      // SMTP 서버 정보를 설정한다.
      Properties props = new Properties();
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", 465);
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.ssl.enable", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.debug", "true");

      Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password);
         }
      });

      // email 전송
      try {
         MimeMessage msg = new MimeMessage(session);
         msg.setFrom(new InternetAddress(user, "ourbox"));
         msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));

         // 메일 제목
         msg.setSubject("안녕하세요  인증 메일입니다.");
         // 메일 내용
         msg.setText("인증 번호는 :" + 12345);

         Transport.send(msg);
         System.out.println("이메일 전송");

      } catch (Exception e) {
         e.printStackTrace();// TODO: handle exception
      }
      HttpSession saveKey = req.getSession();
      saveKey.setAttribute("AuthenticationKey", 12345);

   }

}
