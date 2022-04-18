package com.medicensoya.loginproject.services;

import com.medicensoya.loginproject.utils.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final static String MAIL_ERROR_MSG = "Failed to send Email";

    private final JavaMailSender javaMailSender;


    /**
     * Service that sends a confirmation email for the registration process
     *
     * @param toEmail sender email
     * @param email   from email
     */
    @Override
    @Async
    public void send(String toEmail, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(toEmail);
            helper.setSubject("Confirm Your email");
            helper.setFrom("sollaarg@gmail.com");
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            LOGGER.error(MAIL_ERROR_MSG, e);
            throw new IllegalStateException(MAIL_ERROR_MSG);

        }

    }
}
