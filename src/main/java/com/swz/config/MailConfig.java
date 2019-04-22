package com.swz.config;

import lombok.Data;
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
@Data
public class MailConfig {

    private String smtpServer;
    private String userName;
    private String passWord;

}
