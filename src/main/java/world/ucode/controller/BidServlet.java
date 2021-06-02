package world.ucode.controller;

import org.json.simple.JSONObject;
import world.ucode.model.BidRunner;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet("/bid")
@MultipartConfig
public class BidServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/ubay_war/jsp/customer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        String json = JsonUtils.jsonToString(req);
        JSONObject joReq = JsonUtils.jsonToJsonObject(json);

        int itemLot = Integer.parseInt(joReq.get("itemNumber").toString());
        String customerLogin = joReq.get("loginBuyer").toString();
        int bid = Integer.parseInt(joReq.get("bid").toString());
        int hash = Integer.parseInt(joReq.get("hash").toString());
        CheckUser cU = new CheckUser();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bidCreated", "false");
        if (cU.checkSignIn(customerLogin, cU.getClientIpAddress(req), hash) == true &&
                cU.checkRole(customerLogin, "1")) {
            LotRunner lotRunner = new LotRunner();
            lotRunner.getSessionFactory();
            Lot lot = lotRunner.getItemNumber(itemLot);
            if (lot != null) {
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date start = null;
                 try {
                start = sdf2.parse(lot.getStartTime());
                 } catch (Exception e) {
                    e.printStackTrace();
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
                Date allTime  = null;
                String time = lot.getStartTime() + " " + lot.getDuration();
                try {
                    allTime = sdf.parse(time);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                 Date date = new Date();
                if((start.getTime() < date.getTime()) && (date.getTime() < allTime.getTime())) {
                    if(bid > (lot.getPrice() + lot.getIncrease())) {
                        BidRunner bidRun = new BidRunner();
                        bidRun.getSessionFactory();
                        bidRun.createBid();
                        bidRun.addBid(++id, itemLot, customerLogin, bid);
                        lotRunner.updateStatus(itemLot, "activ");
                        jsonObject.put("bidCreated", "true");
                    }
                    else
                        jsonObject.put("trouble", "Ваша ставка слишком мала");
                }
                else {
                    lotRunner.updateStatus(itemLot, "passive");
                    jsonObject.put("trouble", "Лот закрыт.Вы не можете сделать ставку");
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
