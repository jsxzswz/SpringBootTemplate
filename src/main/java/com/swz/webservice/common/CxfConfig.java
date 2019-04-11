package com.swz.webservice.common;

import com.swz.webservice.user.intf.CheckUserService;
import com.swz.webservice.user.intf.impl.CheckUserServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Package: com.swz.webservice.server.common
 * @Description: webservise基础服务类
 * @author: swz
 * @date: 2018/10/11 16:40
 */
@Configuration
public class CxfConfig {

    @Bean
    public ServletRegistrationBean webServiceServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new CXFServlet(), "/webServices/*");
        registrationBean.setName("webServiceServlet");
        return registrationBean;
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public CheckUserService checkUserService() {
        return new CheckUserServiceImpl();
    }

    @Bean
    public EndpointImpl endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), checkUserService());
        endpoint.publish("/checkUserService");
        return endpoint;
    }
}
