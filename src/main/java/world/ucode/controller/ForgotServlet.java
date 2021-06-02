package world.ucode.controller;

//import com.google.protobuf.Message;
//import jdk.internal.net.http.websocket.Transport;
import org.json.simple.JSONObject;
import world.ucode.model.BidRunner;
import world.ucode.model.RegistrationRunner;
import world.ucode.utilits.JsonUtils;
import world.ucode.pojo.Registration;

import javax.mail.*;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//import java.net.PasswordAuthentication;

import javax.mail.internet.*;
import java.util.Properties;

@WebServlet("/forgot")
@MultipartConfig
public class ForgotServlet extends HttpServlet {

    private class SMTPAuthenticator extends Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("odemenko18@gmail.com", "test_test");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/ubay_war/jsp/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = JsonUtils.jsonToString(req);
        JSONObject joReq = JsonUtils.jsonToJsonObject(json);
        String login = joReq.get("login").toString();
        RegistrationRunner registRun = new RegistrationRunner();
        registRun.getSessionFactory();
        Registration reg = registRun.getPass(login);
        JSONObject jsonObject = new JSONObject();
        if (reg != null) {
            jsonObject.put("loginExist", "true");
            String email = reg.getEmail();
            String to = email;
            String from = "odemenko18@gmail.com";
            String d_port = "465";
            String host = "smtp.gmail.com";
            String d_email ="odemenko18@gmail.com";
            Properties properties = new Properties(); //System.getProperties();
            properties.put("mail.smtp.user", d_email);
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", d_port);
            properties.put("mail.smtp.starttls.enable","true");
            properties.put("mail.smtp.debug", "true");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.socketFactory.port", d_port);
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.socketFactory.fallback", "false");
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(properties, auth);
            session.setDebug(true);
             try {
                    MimeMessage msg = new MimeMessage(session);
                    msg.setText("login: " + login+", password: " + reg.getPassword());
                    msg.setSubject("Recovery password");
                    msg.setFrom(new InternetAddress(from));
                    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    Transport transport = session.getTransport("smtps");
                    transport.connect(host, 465, "odemenko18", "test_test");
                    transport.send(msg);
                    transport.close();
                    } catch (MessagingException me) {
                    me.printStackTrace();
                    System.out.println(me);
                }
      } else {
            jsonObject.put("loginExist", "Введеного логина нет в базе данных");
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try{
            PrintWriter writer = resp.getWriter();
            String jsonOut = jsonObject.toJSONString();
            writer.write(jsonOut);
            writer.flush();
        } catch(Exception ex){
            System.out.println("Error:: " + ex);
        }
    }
}
