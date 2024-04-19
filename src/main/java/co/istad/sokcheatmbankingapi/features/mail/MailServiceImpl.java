package co.istad.sokcheatmbankingapi.features.mail;

import co.istad.sokcheatmbankingapi.features.mail.dto.MailRequest;
import co.istad.sokcheatmbankingapi.features.mail.dto.MailResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{

    private  final JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;
    @Override
    public MailResponse send(MailRequest mailRequest) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        Context context = new Context();
        context.setVariable("name","CSTAD");

        String htmlTemplate = templateEngine.process("mail/index",context);

        try{
            mimeMessageHelper.setTo(mailRequest.to());
            mimeMessageHelper.setSubject(mailRequest.subject());
            mimeMessageHelper.setText(htmlTemplate,true);
        }catch (MessagingException e){
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        javaMailSender.send(mimeMessage);

        return new MailResponse("Mail has been sent successfully.");
    }
}
