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

@WebServlet("/registration")
@MultipartConfig
public class RegistrationServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/ubay_war/jsp/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        String json = JsonUtils.jsonToString(req);
        JSONObject joReq = JsonUtils.jsonToJsonObject(json);
        String login = joReq.get("login").toString();
        String password = joReq.get("password").toString();
        String role = joReq.get("role").toString();
        String email = joReq.get("email").toString();
        RegistrationRunner registRun = new RegistrationRunner();
        registRun.getSessionFactory();
        registRun.createUser();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userCreated", "Возникла ошибка при регистрации. Попробуйде еще раз.");
        if (registRun.addUser(id++, login, password, role, null, null, email, 0) == false) {
            jsonObject.put("userCreated", "Введенный логин уже существует");
        } else {
            jsonObject.put("userCreated", "true");
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try {
            PrintWriter writer = resp.getWriter();
            String jsonOut = jsonObject.toJSONString();
            writer.write(jsonOut);
            writer.flush();
        } catch (Exception ex) {
            System.out.println("Error:: " + ex);
        }
    }
}
