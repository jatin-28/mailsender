package com.ss.mailshot.data.datamodel;

import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * User: jatin
 * Date: 14-Mar-2010
 * Time: 12:12:01
 */
@Entity
@Table(name = "TEMPLATE_EMAIL_DATA")
public class EMailBean {
    @Id
    @GeneratedValue
    private Long id;

    // if any of the attributes have ${} then they represent a field and the value should be taken from the CSV file.

    // comma separated
    private String toEmailAddress;
    private String ccEmailAddresses;
    private String bccEmailAddresses;

    private String fromEmailAddress;
    private String replytoAddress;
    private String subject;
    private boolean isHTML = true;


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isHTML() {
        return isHTML;
    }

    public void setHTML(boolean HTML) {
        isHTML = HTML;
    }

    public String getCcEmailAddresses() {
        return ccEmailAddresses;
    }

    public void setCcEmailAddresses(String ccEmailAddresses) {
        this.ccEmailAddresses = StringUtils.hasText(ccEmailAddresses) ? ccEmailAddresses :null;
    }

    public String getBccEmailAddresses() {
        return bccEmailAddresses;
    }

    public void setBccEmailAddresses(String bccEmailAddresses) {
        this.bccEmailAddresses = StringUtils.hasText(bccEmailAddresses) ? bccEmailAddresses :null;
    }

    public String getFromEmailAddress() {
        return fromEmailAddress;
    }

    public void setFromEmailAddress(String fromEmailAddress) {
        this.fromEmailAddress = fromEmailAddress;
    }

    public String getReplytoAddress() {
        return replytoAddress;
    }

    public void setReplytoAddress(String replytoAddress) {
        this.replytoAddress = replytoAddress;
    }

    public String getToEmailAddress() {
        return toEmailAddress;
    }

    public void setToEmailAddress(String toEmailAddress) {
        this.toEmailAddress = toEmailAddress;
    }

    @Override
    public String toString() {
        return "EMailBean{" +
                "id=" + id +
                ", toEmailAddress='" + toEmailAddress + '\'' +
                ", ccEmailAddresses='" + ccEmailAddresses + '\'' +
                ", bccEmailAddresses='" + bccEmailAddresses + '\'' +
                ", fromEmailAddress='" + fromEmailAddress + '\'' +
                ", replytoAddress='" + replytoAddress + '\'' +
                ", subject='" + subject + '\'' +
                ", isHTML=" + isHTML +
                '}';
    }
}
