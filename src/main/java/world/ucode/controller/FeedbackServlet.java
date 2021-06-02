package world.ucode.controller;

import org.json.simple.JSONObject;
import world.ucode.utilits.JsonUtils;
import world.ucode.pojo.Lot;
import world.ucode.model.LotRunner;
import world.ucode.utilits.CheckUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/feedback")
@MultipartConfig
public class FeedbackServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/ubay_war/jsp/customer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        String json = JsonUtils.jsonToString(req);
        JSONObject joReq = JsonUtils.jsonToJsonObject(json);
        int itemLot = Integer.parseInt(joReq.get("lotItem").toString());
        String login = joReq.get("login").toString();
        int hash = Integer.parseInt(joReq.get("hash").toString());
        String role = joReq.get("user").toString();
        String feedback = joReq.get("feedback").toString();
        int mark = Integer.parseInt(joReq.get("mark").toString());
        CheckUser cU = new CheckUser();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bidCreated", "false");
        if (cU.checkSignIn(login, cU.getClientIpAddress(req), hash) == true &&
                cU.checkRole(login, role)) {
            LotRunner lotRunner = new LotRunner();
            lotRunner.getSessionFactory();
            Lot lot = lotRunner.getItemNumber(itemLot);
            if (lot != null) {
                if(role.equals("1") == true) {
                    if (lot.getWinner().equals(login) == true) {
                        lotRunner.updateBiderFeedback(itemLot, mark, feedback);
                        jsonObject.put("bidCreated", "true");
                    } else
                        jsonObject.put("trouble", "Вы не являетесь покупцом этого лота.Вы не можете сделать ставку");
                }
            }
            else jsonObject.put("trouble", "Вы ввели не правильный номер лота");
        }
        else
            jsonObject.put("bidCreated", "У вас нет прав для этого действия");

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
