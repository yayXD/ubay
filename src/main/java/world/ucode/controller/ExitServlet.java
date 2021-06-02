package world.ucode.controller;

import org.json.simple.JSONObject;
import world.ucode.model.LotRunner;
import world.ucode.model.RegistrationRunner;
import world.ucode.utilits.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exit")
@MultipartConfig
public class ExitServlet extends HttpServlet {

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
        //int hash = Integer.parseInt(joReq.get("hash").toString());
        RegistrationRunner rr = new RegistrationRunner();
        rr.getSessionFactory();
        rr.updateStatus(login, null, 0, null);
    }
}
