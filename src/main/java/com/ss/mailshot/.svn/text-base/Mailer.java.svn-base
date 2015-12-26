package com.ss.mailshot;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.MessageFormat;

/*
*  User: jshah
*
*/

public class Mailer {
    private static final Logger logger = Logger.getLogger(Mailer.class);
    private JavaMailSender mailSender;
    private String[] ccEmailAddresses;
    private String[] bccEmailAddresses;
    private String fromEmailAddress;
    private String replytoAddress;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * @param subject   - the subject
     * @param file      - file attachments - can be null
     * @param plainText - body text in plain
     * @param htmlText  - body text in html
     * @throws MessagingException
     */
    public void sendMail(final String subject, final File file, final String plainText, final String htmlText, final String toEmailAddress)
            throws MessagingException {

        String message = "Sending email with subject {0}, text {1}, html {2}, attachment {3}";
        logger.debug(MessageFormat.format(message, subject, plainText, htmlText, file));

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

                if (subject != null) {
                    mimeMessageHelper.setSubject(subject);
                }


                if (toEmailAddress != null) {
                    mimeMessageHelper.setTo(new String[]{toEmailAddress});
                }

                if (ccEmailAddresses != null && ccEmailAddresses.length > 0) {
                    mimeMessageHelper.setCc(ccEmailAddresses);
                }

                if (bccEmailAddresses != null && bccEmailAddresses.length > 0) {
                    mimeMessageHelper.setBcc(bccEmailAddresses);
                }

                mimeMessageHelper.setFrom(fromEmailAddress);
                mimeMessageHelper.setReplyTo(replytoAddress);

                if (file != null) {
                    mimeMessageHelper.addAttachment(file.getName(), file);
                }

                if (plainText != null && htmlText != null) {
                    mimeMessageHelper.setText(plainText, htmlText);
                } else if (plainText != null) {
                    mimeMessageHelper.setText(plainText, false);
                } else if (htmlText != null) {
                    mimeMessageHelper.setText(htmlText, true);
                }
            }
        };

        logger.debug("Sending email : " + preparator);
        mailSender.send(preparator);
        logger.info("Email sent.");
    }

    public void setCcEmailAddresses(String[] ccEmailAddresses) {
        this.ccEmailAddresses = ccEmailAddresses;
    }

    public void setFromEmailAddress(String fromEmailAddress) {
        this.fromEmailAddress = fromEmailAddress;
    }

    public void setReplytoAddress(String replytoAddress) {
        this.replytoAddress = replytoAddress;
    }

    public void setBccEmailAddresses(String[] bccEmailAddresses) {
        this.bccEmailAddresses = bccEmailAddresses;
    }
}