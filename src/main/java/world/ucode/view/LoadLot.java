package world.ucode.view;

import org.json.simple.JSONObject;
import world.ucode.pojo.Lot;
import world.ucode.model.LotRunner;
import world.ucode.utilits.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loadlot")
public class LoadLot extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = JsonUtils.jsonToString(req);
        JSONObject joReq = JsonUtils.jsonToJsonObject(json);
        JSONObject jsonObject = new JSONObject();

        int lotId  = Integer.parseInt(joReq.get("lotID").toString());
        LotRunner lr = new LotRunner();
        lr.getSessionFactory();
        Lot t = lr.getItemNumber(lotId);
        if(t != null) {
            jsonObject.put("imageName", t.getPictuteName());
            jsonObject.put("lotPrice", t.getFinalprice());
            jsonObject.put("lotId", t.getItemNumder());
            jsonObject.put("lotType", t.getType());
            jsonObject.put("lotStatus", t.getStatus());
            jsonObject.put("lotDescription", t.getDescription());
            jsonObject.put("lotWinner", t.getWinner());
            jsonObject.put("lotFeedback", t.getFeedbackCustomer());

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
}
