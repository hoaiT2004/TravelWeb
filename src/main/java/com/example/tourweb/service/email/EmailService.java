package com.example.tourweb.service.email;
import com.example.tourweb.model.CodeRequest;
import com.example.tourweb.model.EmailRequest;
import com.example.tourweb.repository.UserRepository;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    private UserRepository userRepository;
        static final String from = "codeptit123@gmail.com";
        static final String password = "wnhpagypqhffhwjc";
        public boolean sendEmail(String to,String title,String content) {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from,password);
                }
            };
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            try{
                msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                msg.setFrom(from);
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
                msg.setSubject(title);
                msg.setSentDate(new Date());
                msg.setContent(content,"text/HTML; charset=UTF-8");
                Transport.send(msg);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        public void changeEmail(EmailRequest emailRequest){
            userRepository.updateEmail(emailRequest.getEmail(), emailRequest.getUsername());
        }
        public String verifyCode(CodeRequest codeRequest){
            String error = "";
            if(codeRequest.getCode().equals(codeRequest.getCodeTrue())){
                int amountOfTime = codeRequest.getEnterCodeDatetime().getMinute() * 60 + codeRequest.getEnterCodeDatetime().getSecond() - (codeRequest.getSendCodeDateTime().getMinute() * 60 + codeRequest.getSendCodeDateTime().getSecond());
                if(codeRequest.getEnterCodeDatetime().getDayOfMonth() == codeRequest.getSendCodeDateTime().getDayOfMonth()
                        && codeRequest.getSendCodeDateTime().getMonth() == codeRequest.getEnterCodeDatetime().getMonth()
                        && codeRequest.getSendCodeDateTime().getYear() == codeRequest.getEnterCodeDatetime().getYear()
                        && codeRequest.getSendCodeDateTime().getHour() == codeRequest.getEnterCodeDatetime().getHour()
                        && (amountOfTime) <= 120
                ){
                    var emailRequest = EmailRequest.builder()
                            .email(codeRequest.getEmail())
                            .username(codeRequest.getUsername())
                            .build();
                    changeEmail(emailRequest);
                    return "success";
                }else{
                    error = "Mã xác thực đã quá thời hạn!";
                    return error;
                }

            }
            error = "Mã xác thực sai!";
            return error;
        }
}

