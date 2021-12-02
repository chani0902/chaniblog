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
	
	// �̸��� ���� ����� �޼���
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

	// ������ �̿��� Ű ����
	private boolean lowerCheck;
	private int size;

	public String getKey(boolean lowerCheck, int size) {
		this.lowerCheck = lowerCheck;
		this.size = size;
		return init();
	}

	// ȸ������ �߼� �̸���(����Ű �߼�)
	public void mailSendWithUserKey(String member_email, String member_id, HttpServletRequest request) {
		
		String key = getKey(false, 20);
		dao = sqlSession.getMapper(MemberDAO.class);
		System.out.println("member_id : " + member_id);
		System.out.println("key : " + key);
		dao.GetKey(member_id, key); 
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h1>�ȳ��ϼ���! :D</h1><h2>" + member_id + "��</h2>"
				+ "<h2>My Project Community site�� ���� ���� ȯ���մϴ�.</h2><br>"
				+ "<h3>������ ��ġ�� ȸ�� ������ �Ϸ�˴ϴ�!</h3>"
				+ "<p>�����ϱ� ��ư�� �����ø� �α����� �Ͻ� �� �ֽ��ϴ� : " 
				+ "<a href='http://localhost:8090" + request.getContextPath() + "/key_alter?member_id="+ member_id +"&user_key="+key+"'>�����ϱ�</a></p>"
				+ "<br>(Ȥ�� �߸� ���޵� �����̶�� �� �̸����� �����ϼŵ� �˴ϴ�)";
		try {
			mail.setSubject("[��������] My Project Community site ���� �����Դϴ�", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(member_email));
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
	}
	
//	��й�ȣ ã�� �̸���(���� ���� ��ũ�� ���ִ� ���)
	public void mailSendForFdpw(String member_email, String member_id, HttpServletRequest request) {
		
		dao = sqlSession.getMapper(MemberDAO.class);
		System.out.println("member_id : " + member_id);
		System.out.println("member_email : " + member_email);
		
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h1>�ȳ��ϼ���! :D</h1><h2>" + member_id + "��</h2>"
				+ "<h2>My Project Community site�� �̿����ּż� �����մϴ�.</h2><br>"
				+ "<h3>��й�ȣ�� ���Ӱ� �������ּ���.</h3>"
				+ "<p>��й�ȣ ���� ��ư�� ������ ���� �������� �̵��մϴ� : " 
				+ "<a href='http://localhost:8090" + request.getContextPath() + "/mv_newpw.do?member_id="+ member_id +"'>��й�ȣ ����</a></p>"
				+ "<br>";
		try {
			mail.setSubject("[��й�ȣ ����] My Project Community site ��й�ȣ ���� �����Դϴ�", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(member_email));
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
	}
}
