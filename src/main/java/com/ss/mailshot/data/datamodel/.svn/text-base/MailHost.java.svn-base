package com.ss.mailshot.data.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: jatin
 * Date: 14-Mar-2010
 * Time: 12:48:50
 */
@Entity
public class MailHost {
    @Id
    @GeneratedValue
    private Long id;

    private String mailServer;
    private Integer mailServerPort;
    private String username;
    private String password;
    private boolean smtpAuth = true;
    private String name;
    private String description;

     @Column(nullable = false, columnDefinition="default 1")
    private Long messageIntervalInSecs;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailServer() {
        return mailServer;
    }

    public void setMailServer(String mailServer) {
        this.mailServer = mailServer;
    }

    public Integer getMailServerPort() {
        return mailServerPort;
    }

    public void setMailServerPort(Integer mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSmtpAuth() {
        return smtpAuth;
    }

    public void setSmtpAuth(boolean smtpAuth) {
        this.smtpAuth = smtpAuth;
    }

    public Long getMessageIntervalInSecs() {
        return messageIntervalInSecs;
    }

    public void setMessageIntervalInSecs(Long messageIntervalInSecs) {
        this.messageIntervalInSecs = messageIntervalInSecs;
    }

    @Override
    public String toString() {
        return name;
    }

    public String toDebugString() {
        return "MailHost{" +
                "id=" + id +
                ", mailServer='" + mailServer + '\'' +
                ", mailServerPort=" + mailServerPort +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", smtpAuth=" + smtpAuth +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", messageIntervalInSecs=" + messageIntervalInSecs +
                '}';
    }
}
