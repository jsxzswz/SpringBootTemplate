package com.swz.utils;

import com.swz.config.MailConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

/**
 * @Package: com.swz.utils
 * @Description: mail工具类
 * @author: swz
 * @date: 2018/7/24 9:55
 */
public class MailUtil {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    // private final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private String smtpServer; // SMTP服务器地址
    // private String port; // 端口
    private String username; // 登录SMTP服务器的用户名
    private String password; // 登录SMTP服务器的密码
    private String[] recipientsTO = { "baoshun.sun@newtouch.cn",
            "wenzheng.sun@newtouch.cn", "haoyang.li@newtouch.cn" }; // 收件人地址集合
    private String[] recipientsCC = { "huabing.mei@newtouch.cn",
            "1553225871@qq.com" }; // 抄送人地址集合
    private String subject; // 邮件主题
    private String content; // 邮件正文
    private List<String> attachmentNames = new ArrayList<String>(); // 附件路径信息集合

    public MailUtil() {
        try {
//            smtpServer = fig.getSmtpServer();
//            // port = fig.getResourceValue("mail_port");
//            username = fig.getUserName();
//            password = fig.getPassWord();
            content = "祝工作愉快，事事顺心！";
            this.setRecipientsTO(recipientsTO);
            this.setRecipientsCC(recipientsCC);
            this.setContent(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    // public String getPort() {
    // return port;
    // }
    //
    // public void setPort(String port) {
    // this.port = port;
    // }

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

    public String[] getRecipientsTO() {
        return recipientsTO;
    }

    public void setRecipientsTO(String[] recipientsTO) {
        this.recipientsTO = recipientsTO;
    }

    public String[] getRecipientsCC() {
        return recipientsCC;
    }

    public void setRecipientsCC(String[] recipientsCC) {
        this.recipientsCC = recipientsCC;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAttachmentNames() {
        return attachmentNames;
    }

    public void setAttachmentNames(List<String> attachmentNames) {
        this.attachmentNames = attachmentNames;
    }

    /**
     * 进行base64加密，防止中文乱码
     * */
    public String changeEncode(String str) {
        try {
            str = MimeUtility.encodeText(new String(str.getBytes(), "utf-8"),
                    "utf-8", "B"); // "B"代表Base64
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 正式发邮件
     * */
    public boolean sendMail() {
//		String proxyHosts = "";
//		String proxyPorts = "";
//		try {
//			SystemConfig fig = new SystemConfig();
//			proxyHosts = fig.getResourceValue("proxyHosts");
//			proxyPorts = fig.getResourceValue("proxyPorts");
//		} catch (GeneralException e) {
//			e.printStackTrace();
//		}
        Properties props = System.getProperties();
        // 设置代理服务器
//		 props.setProperty("proxySet", "true");
//		 props.setProperty("proxyHost", proxyHosts);
//		 props.setProperty("proxyPort", proxyPorts);
        // 设置不需要通过代理服务器访问的主机，可以使用*通配符，多个地址用|分隔
        // props.setProperty("nonProxyHosts", "localhost|192.168.0.*|10.1.*");
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.auth", "true");
        // props.put("mail.smtp.socketFactory.class", SSL_FACTORY); //
        // 使用JSSE的SSL
        // props.put("mail.smtp.socketFactory.fallback", "false"); //
        // 只处理SSL的连接,对于非SSL的连接不做处理
        // props.put("mail.smtp.port", port);
        // props.put("mail.smtp.socketFactory.port", port);
        Session session = Session.getInstance(props);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        try {
            // 发件人
            Address address = new InternetAddress(username);
            message.setFrom(address);
            // 收件人
            Address[] tos = null;
            if (recipientsTO != null) {
                // 为每个邮件接收者创建一个地址
                tos = new InternetAddress[recipientsTO.length + 1];
                for (int i = 0; i < recipientsTO.length; i++) {
                    tos[i] = new InternetAddress(recipientsTO[i]);
                }
                tos[3] = tos[tos.length - 1];
                tos = Arrays.copyOf(tos, tos.length - 1);
            }
            message.setRecipients(MimeMessage.RecipientType.TO, tos); // 设置收件人,并设置其接收类型为TO
            // 抄送人
            Address[] ccs = null;
            if (recipientsCC != null) {
                // 为每个邮件抄送者创建一个地址
                ccs = new InternetAddress[recipientsCC.length + 1];
                for (int i = 0; i < recipientsCC.length; i++) {
                    ccs[i] = new InternetAddress(recipientsCC[i]);
                }
                ccs[2] = ccs[ccs.length - 1];
                ccs = Arrays.copyOf(ccs, ccs.length - 1);
            }
            message.setRecipients(MimeMessage.RecipientType.CC, ccs); // 设置收件人,并设置其接收类型为CC
            // 主题
            message.setSubject(changeEncode(subject));
            // 时间
            message.setSentDate(new Date());
            Multipart multipart = new MimeMultipart();
            // 添加文本
            BodyPart text = new MimeBodyPart();
            text.setText(content);
            multipart.addBodyPart(text);
            // 添加附件
            for (String fileName : attachmentNames) {
                BodyPart adjunct = new MimeBodyPart();
                FileDataSource fileDataSource = new FileDataSource(fileName);
                adjunct.setDataHandler(new DataHandler(fileDataSource));
                adjunct.setFileName(changeEncode(fileDataSource.getName()));
                multipart.addBodyPart(adjunct);
            }
            // 清空收件/抄送人集合，附件集合
            recipientsTO = null;
            recipientsCC = null;
            attachmentNames.clear();
            message.setContent(multipart);
            message.saveChanges();
            log.info("邮件发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("邮件发送异常"+e);
            return false;
        }
        try {
            Transport transport = session.getTransport("smtp");
            transport.connect(smtpServer, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("邮件发送异常"+e);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        MailConfig mailConfig = new MailConfig();
        String subject = "这封邮件是为了测试SMTP的SSL加密传输";
        List<String> attachmentNames = new ArrayList<String>();
        attachmentNames.add("E:\\ccicall\\data\\excel\\2018071217.zip");
        MailUtil mailUtils = new MailUtil();
        mailUtils.setSmtpServer(mailConfig.getSmtpServer());
        mailUtils.setUsername(mailConfig.getUserName());
        mailUtils.setPassword(mailConfig.getPassWord());
        mailUtils.setSubject(subject);
        mailUtils.setAttachmentNames(attachmentNames);
        boolean b = mailUtils.sendMail();
    }

}
