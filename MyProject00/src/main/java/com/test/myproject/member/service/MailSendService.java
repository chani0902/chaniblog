package com.test.myproject.member.service;

import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.test.myproject.member.model.MemberDAO;

@Service
public class MailSendService {

	@Autowired
	JavaMailSender mailSender;
	@Autowired
	SqlSessionTemplate sqlSession;
	
	MemberDAO dao;
	
	// 이메일 난수 만드는 메서드
	private String init() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;

		do {
			num = ran.nextInt(75) + 48;
			if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				sb.append((char) num);
			} else {
				continue;
			}

		} while (sb.length() < size);
		if (lowerCheck) {
			return sb.toString().toLowerCase();
		}
		return sb.toString();
	}

	// 난수를 이용한 키 생성
	private boolean lowerCheck;
	private int size;

	public String getKey(boolean lowerCheck, int size) {
		this.lowerCheck = lowerCheck;
		this.size = size;
		return init();
	}

	// 회원가입 발송 이메일(인증키 발송)
	public void mailSendWithUserKey(String member_email, String member_id, HttpServletRequest request) {
		
		String key = getKey(false, 20);
		dao = sqlSession.getMapper(MemberDAO.class);
		System.out.println("member_id : " + member_id);
		System.out.println("key : " + key);
		dao.GetKey(member_id, key); 
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h1>안녕하세요! :D</h1><h2>" + member_id + "님</h2>"
				+ "<h2>My Project Community site에 오신 것을 환영합니다.</h2><br>"
				+ "<h3>인증을 마치면 회원 가입이 완료됩니다!</h3>"
				+ "<p>인증하기 버튼을 누르시면 로그인을 하실 수 있습니다 : " 
				+ "<a href='http://localhost:8090" + request.getContextPath() + "/key_alter?member_id="+ member_id +"&user_key="+key+"'>인증하기</a></p>"
				+ "<br>(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
		try {
			mail.setSubject("[본인인증] My Project Community site 인증 메일입니다", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(member_email));
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
	}
	
//	비밀번호 찾기 이메일(정보 수정 링크를 쏴주는 방식)
	public void mailSendForFdpw(String member_email, String member_id, HttpServletRequest request) {
		
		dao = sqlSession.getMapper(MemberDAO.class);
		System.out.println("member_id : " + member_id);
		System.out.println("member_email : " + member_email);
		
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h1>안녕하세요! :D</h1><h2>" + member_id + "님</h2>"
				+ "<h2>My Project Community site를 이용해주셔서 감사합니다.</h2><br>"
				+ "<h3>비밀번호를 새롭게 설정해주세요.</h3>"
				+ "<p>비밀번호 수정 버튼을 누르면 변경 페이지로 이동합니다 : " 
				+ "<a href='http://localhost:8090" + request.getContextPath() + "/mv_newpw.do?member_id="+ member_id +"'>비밀번호 수정</a></p>"
				+ "<br>";
		try {
			mail.setSubject("[비밀번호 변경] My Project Community site 비밀번호 변경 메일입니다", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(member_email));
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
	}
}
