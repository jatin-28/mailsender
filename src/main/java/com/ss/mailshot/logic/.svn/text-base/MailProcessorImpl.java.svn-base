package com.ss.mailshot.logic;

import com.ss.mailshot.FreemarkerRenderer;
import com.ss.mailshot.Mailer;
import com.ss.mailshot.data.datamodel.EMailBean;
import com.ss.mailshot.data.datamodel.MailHost;
import com.ss.mailshot.data.datamodel.TemplateMetaData;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: jatin
 * Date: 27-Mar-2010
 * Time: 15:30:39
 */
public class MailProcessorImpl implements MailProcessor {
    private static final Logger logger = Logger.getLogger(MailProcessorImpl.class);

    private FreemarkerRenderer renderer;
    private Mailer mailer;

    @Autowired(required = true)
    public void setRenderer(FreemarkerRenderer renderer) {
        this.renderer = renderer;
    }

    @Autowired(required = true)
    public void setMailer(Mailer mailer) {
        this.mailer = mailer;
    }

    @Override
    public boolean processor(TemplateMetaData template, List<Map<String, String>> dataList, MailHost mailHost) {
        boolean success = true;

        logger.info("Starting mailing process..... to mail: " + dataList.size());

        // create mailsender
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(mailHost.getMailServer());
        sender.setPort(mailHost.getMailServerPort());
        sender.setUsername(mailHost.getUsername());
        sender.setPassword(mailHost.getPassword());

        if ( mailHost.isSmtpAuth()) {
            Properties props = new Properties();
            props.put("mail.smtp.auth","true");
            sender.setJavaMailProperties(props);
        }
        mailer.setMailSender(sender);

        EMailBean emailBean = template.getEmailBean();
        // TODO introduce macro processing for all this including toEmail Address and subject.
        mailer.setBccEmailAddresses( split(emailBean.getBccEmailAddresses()));
        mailer.setCcEmailAddresses(split(emailBean.getCcEmailAddresses()));
        mailer.setFromEmailAddress(emailBean.getFromEmailAddress());
        mailer.setReplytoAddress(emailBean.getReplytoAddress());

        // loop through, render and send the data
        try {
            StringBuilder emailAddressesSuccessful = new StringBuilder("");
            for (Map<String, String> root : dataList) {
                String macroNameForEmailAddress = emailBean.getToEmailAddress();
                String emailAddress = root.get(macroNameForEmailAddress);
                if ( macroNameForEmailAddress.indexOf("@") > -1) emailAddress = macroNameForEmailAddress;

                String subject = emailBean.getSubject();

                if (!root.isEmpty() && StringUtils.hasText(emailAddress)) {
                    // render the template
                    String text = renderer.convert(template.getTemplateName(), template.getTemplateText(), root);
                    if (StringUtils.hasText(text)) {
                        mailer.sendMail(subject, null, null, text, emailAddress);
                        logger.debug(text + "\n===========================\n");
                        emailAddressesSuccessful.append(emailAddress).append(",");
                    }
                }
                try {
                    Thread.sleep(mailHost.getMessageIntervalInSecs() * 1000);
                } catch (InterruptedException e) { //
                }
            }

            logger.info("FINISHED. Emailed to : " + emailAddressesSuccessful);

        } catch (IOException e) {
            success = false;
            logger.error("FAILED to email: ", e);
        } catch (TemplateException e) {
            success = false;
            logger.error("FAILED to email: ", e);
        } catch (MessagingException e) {
            success = false;
            logger.error("FAILED to email: ", e);
        }

        return success;
    }

    private String[] split(String emailAddress) {
        String[] returnVal = null;
        if ( StringUtils.hasText(emailAddress)) {
            returnVal = emailAddress.split(",");
        }
        return returnVal;
    }
}
