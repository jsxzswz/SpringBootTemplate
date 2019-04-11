package com.swz;

import com.swz.servlet.DownLoadServlet;
import com.swz.servlet.UploadHandleServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean getservletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new UploadHandleServlet());
        bean.addUrlMappings("/upload");
        return bean;
    }

    @Bean
    public ServletRegistrationBean getservletRegistrationBean2() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new DownLoadServlet());
        bean.addUrlMappings("/download");
        return bean;
    }
}
