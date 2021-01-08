package com.example.spring03.service.email;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.spring03.model.email.EmailDTO;

@Service
public class EmailServiceImpl implements EmailService {

	@Inject
	JavaMailSender mailSender; // 메일 발송 객체, root-context.xml에 설정한 bean
	@Override
	public void sendEmail(EmailDTO dto) {
		
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			msg.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiveMail())); 
			msg.addFrom(new InternetAddress[] { // 이메일 발신자
					new InternetAddress(dto.getSenderMail(),dto.getSenderName())
					});
			msg.setSubject(dto.getSubject(),"utf-8"); //이메일 제목
			msg.setText(dto.getMessage(),"utf-8"); // 이메일 본문
			mailSender.send(msg); //전송
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
