package world.ucode.controller;

import org.json.simple.JSONObject;
import world.ucode.model.BidRunner;
import world.ucode.model.RegistrationRunner;
import world.ucode.utilits.JsonUtils;
import world.ucode.pojo.Registration;
import world.ucode.utilits.CheckUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/change")
@MultipartConfig
public class ChangePasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/ubay_war/jsp/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = JsonUtils.jsonToString(req);
        JSONObject joReq = JsonUtils.jsonToJsonObject(json);
        String login = joReq.get("login").toString();
        String password_old = joReq.get("password_old").toString();
        String password_new = joReq.get("password_new").toString();
        int hash = Integer.parseInt(joReq.get("hash").toString());
        String role =joReq.get("role").toString();

        CheckUser cU = new CheckUser();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("changeCreated", "false");
        if (cU.checkSignIn(login, cU.getClientIpAddress(req), hash) == true &&
                cU.checkRole(login, role)) {
            RegistrationRunner registRun = new RegistrationRunner();
            registRun.getSessionFactory();
            Registration reg = registRun.getPass(login);
            if(reg.getPassword().equals(password_old) == true) {
                registRun.changePassword(login, password_new);
                jsonObject.put("changeCreated", "true");

            } else
                jsonObject.put("trouble", "Вы неправильно ввели старый пароль");

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

