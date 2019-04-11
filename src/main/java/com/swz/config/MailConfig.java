package com.swz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Package: com.swz.config
 * @Description: MailConfig
 * @author: swz
 * @date: 2018/7/24 12:01
 */
@Configuration
@PropertySource("classpath:config/system.properties") // 指定文件地址
@ConfigurationProperties(prefix = "mail")
public class MailConfig {

    private String smtpServer;
    private String userName;
    private String passWord;

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
