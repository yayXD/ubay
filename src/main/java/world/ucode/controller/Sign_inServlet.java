package world.ucode.controller;

import org.json.simple.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import world.ucode.model.BidRunner;
import world.ucode.model.RegistrationRunner;
import world.ucode.utilits.JsonUtils;
import world.ucode.pojo.Registration;
import world.ucode.utilits.CheckUser;
import world.ucode.utilits.RandomValue;

@WebServlet("/sign_in")
@MultipartConfig
public class Sign_inServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/ubay_war/jsp/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = JsonUtils.jsonToString(req);
        JSONObject joReq = JsonUtils.jsonToJsonObject(json);
        String login = joReq.get("login").toString();
        String passwod = joReq.get("password").toString();
        RegistrationRunner registRun = new RegistrationRunner();
        registRun.getSessionFactory();
        Registration reg = registRun.getPass(login);
        JSONObject jsonObject = new JSONObject();
        if (reg != null) {
            jsonObject.put("loginExist", "true");
            if (passwod.equals(reg.getPassword()) == false)
                jsonObject.put("checkPass", "Введен не верный пароль");
            else if (passwod.equals(reg.getPassword()) == true) {
                RandomValue randVal = new RandomValue();
                int hash = randVal.makeItemNumber();
                CheckUser cU = new CheckUser();
                registRun.updateStatus(login, cU.getClientIpAddress(req), hash, "activ");
                jsonObject.put("checkPass", "true");
                jsonObject.put("login", reg.getLogin());
                jsonObject.put("role",reg.getRole());
                jsonObject.put("hash",hash);
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

