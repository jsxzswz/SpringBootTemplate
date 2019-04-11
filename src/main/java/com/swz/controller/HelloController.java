package com.swz.controller;

import com.swz.config.MailConfig;
import com.swz.config.PropertyConfig;
import com.swz.rabbitmq.fanout.FanoutSender;
import com.swz.rabbitmq.topic.TopicSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class HelloController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PropertyConfig propertyConfig;
    @Autowired
    private MailConfig mailConfig;
    @Autowired
    private FanoutSender fanoutSender;
    @Autowired
    private TopicSender topicSender;

    @GetMapping("property")
    @ResponseBody
    public Object property() {
        return propertyConfig.toString();
    }

    @GetMapping("config")
    @ResponseBody
    public String config() {
        return mailConfig.getUserName();
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/login")
    public String login() throws Exception {
        return "login";
    }

    @RequestMapping("/fanoutSend")
    @ResponseBody
    public String fanoutSend() {
        String content = "hi, i am fanout message";
        fanoutSender.sendFanoutMsg(content);
        return "fanout消息发送成功！";
    }

    @RequestMapping("/sendTopic")
    @ResponseBody
    public String sendTopic() {
        String content = "hi, i am topic message!!!";
        topicSender.sendTopic(content);
        return "topic消息发送成功！";
    }

    @RequestMapping("/sendTopic1")
    @ResponseBody
    public String sendTopic1() {
        String content = "hi, i am topic message 1";
        topicSender.sendTopic1(content);
        return "topic消息1发送成功！";
    }

    @RequestMapping("/sendTopic2")
    @ResponseBody
    public String sendTopic2() {
        String content = "hi, i am topic message 2";
        topicSender.sendTopic2(content);
        return "topic消息2发送成功！";
    }

    //servlet方式
    @RequestMapping("/uploadFile")
    public String uploadFile() {
        return "upload";
    }

    @RequestMapping("/downloadFile")
    public String listFile() {
        return "download";
    }

    //springMVC方式
    @PostMapping("/upload1")
    @ResponseBody
    public String upload1(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("[文件类型] - [{}]", file.getContentType());
        log.info("[文件名称] - [{}]", file.getOriginalFilename());
        log.info("[文件大小] - [{}]", file.getSize());
        file.transferTo(new File(propertyConfig.getImagePath() + file.getOriginalFilename()));
        return "上传成功！";
    }

    //springMVC方式
    @PostMapping("/upload2")
    @ResponseBody
    public String upload2(@RequestParam("file") MultipartFile[] files) throws IOException {
        if (files == null || files.length == 0) {
            return null;
        }
        for (MultipartFile file : files) {
            file.transferTo(new File(propertyConfig.getImagePath() + file.getOriginalFilename()));
        }
        return "上传成功！";
    }
}
