package world.ucode.controller;

import org.json.simple.JSONObject;
import world.ucode.model.BidRunner;
import world.ucode.model.LotRunner;
import world.ucode.pojo.Bid;
import world.ucode.pojo.Lot;
import world.ucode.utilits.CheckUser;
import world.ucode.utilits.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet("/winner")
@MultipartConfig
public class WinnerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/ubay_war/jsp/seller.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = JsonUtils.jsonToString(req);
        JSONObject joReq = JsonUtils.jsonToJsonObject(json);
        String login = joReq.get("login").toString();
        int hash = Integer.parseInt(joReq.get("hash").toString());
        int itemLot = Integer.parseInt(joReq.get("lot").toString());
        CheckUser cU = new CheckUser();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("winnerCreated", "false");
        if (cU.checkSignIn(login, cU.getClientIpAddress(req), hash) == true &&
                cU.checkRole(login, "0")) {
            LotRunner lotRunner = new LotRunner();
            lotRunner.getSessionFactory();
            Lot lot = lotRunner.getItemNumber(itemLot);
            if (lot != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
                Date allTime  = null;
                String time = lot.getStartTime() + " " + lot.getDuration();
                try {
                    allTime = sdf.parse(time);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Date date = new Date();
                if(date.getTime() > allTime.getTime()) {
                    BidRunner bidR =new BidRunner();
                    bidR.getSessionFactory();
                    List<Bid> bid = bidR.getBid(itemLot);
                    if(bid != null) {
                        String winner = null;
                        int max = 0;
                        for(Bid f: bid) {
                            if (max < f.getBid()) {
                                winner = f.getCustomerLogin();
                                max = f.getBid();
                            }
                        }
                        jsonObject.put("winnerCreated", "true");
                        jsonObject.put("winnerName", winner);
                        lotRunner.updateStatus(itemLot, "deprecated");
                        lotRunner.updateWinner(itemLot, winner);
                        lotRunner.updateFinalprice(itemLot, max);
                    }
                    else
                        jsonObject.put("trouble", "По Вашему лоту не было сделано ставок");
                }
                else {
                    jsonObject.put("trouble", "Ваш лот еще не закрыт по нему производятся ставки");
                }
            }
            else
                jsonObject.put("trouble", "Вы ввели не правильный номер лота");
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
