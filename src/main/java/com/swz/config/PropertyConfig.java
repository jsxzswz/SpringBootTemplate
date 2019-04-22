package com.swz.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

@Configuration // 声明当前类是一个配置类，相当于Spring配置的XML文件
@PropertySource("classpath:config/system.properties") // 指定文件地址
@Data
public class PropertyConfig {
    @Value("注入普通字符串") // 注入普通字符串
    private String normal;

    @Value("#{systemProperties['os.name']}") // 注入操作系统属性
    private String osName;

    @Value("#{T(java.lang.Math).random() * 100.0 }") // 注入表达式结果
    private double randomNumber;

    @Value("classpath:config/test.txt") // 注入文件资源
    private Resource testFile;

    @Value("${book.author}") // 注入配置文件【注意是$符号】
    private String bookAuthor;

    @Value("${mail.smtpServer}") // 注入配置文件【注意是$符号】
    private String smtpServer;
    @Value("${mail.userName}") // 注入配置文件【注意是$符号】
    private String userName;
    @Value("${mail.passWord}") // 注入配置文件【注意是$符号】
    private String passWord;

    @Value("${upload.imagePath}") // 注入配置文件【注意是$符号】
    private String imagePath;

    @Autowired // Properties可以从Environment获得
    private Environment environment;

}