package com.finp.moic.voc.model.service;

import com.finp.moic.user.model.entity.User;
import com.finp.moic.user.model.repository.UserRepository;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.MailSendException;
import com.finp.moic.util.exception.list.NotFoundException;
import com.finp.moic.voc.model.dto.request.VOCRequestDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class VOCServiceImpl implements VOCService{

    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    @Autowired
    public VOCServiceImpl(UserRepository userRepository, JavaMailSender javaMailSender){
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendVOC(String id, VOCRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.USER_NOT_FOUND));

        String adminMail = "moicssafy@gamil.com";
        String subject = "[" + id + "] 님이 전달한 문의사항";
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessageHelper.setTo(adminMail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom("moicsecurity@moic.com");
            mimeMessageHelper.setText(dto.getContent(),true);

            javaMailSender.send(mimeMessage);
        }catch (MessagingException e) {
            throw new MailSendException(ExceptionEnum.MAIL_SEND_ERROR);
        }
    }
}
