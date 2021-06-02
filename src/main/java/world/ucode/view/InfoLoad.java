package world.ucode.view;

//import org.json.simple.JSONArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import world.ucode.pojo.Lot;
import world.ucode.model.LotRunner;
import world.ucode.utilits.JsonUtils;

import javax.servlet.ServletException;
        import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
        import java.util.List;

@WebServlet("/infoload")
@MultipartConfig

public class InfoLoad extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = JsonUtils.jsonToString(req);
        JSONObject joReq = JsonUtils.jsonToJsonObject(json);
        JSONObject jsonObject = new JSONObject();
        int type  = Integer.parseInt(joReq.get("type").toString());
        String text  = joReq.get("text").toString();
        int N  = Integer.parseInt(joReq.get("N").toString());
        jsonObject.put("size", 0);
            List<Lot> lst = null;
            LotRunner lr = new LotRunner();
            lr.getSessionFactory();
            if( type == 99) {
                lst = lr.getLastN(N);
            }
//            if (type == 0) {
//                lst = lr.getName(text);
//            }
//            if (type == 1) {
//                lst = lr.getStartTime(text);
//            }      if( type == 99)
//            lst = lr.getLastN(N);
        if (type == 0)
            lst = lr.getName(text);
//        if (type == 1)
//            lst = lr.getItemNumber2(text1);
        if(type == 1)
            lst = lr.getLoginSeller(text);
        if(type == 2)
            lst = lr.getStatus(text);
//        if(type == 4)
//            lst = lr.getPrice(text1);
        if(type ==3)
            lst = lr.getStartTime(text);
                if (lst != null) {
                    int n = lst.size();
                    jsonObject.put("size", Integer.toString(n));
                    ArrayList<String> lotName = new ArrayList<String>();
                    ArrayList<String> imageName = new ArrayList<String>();
                    ArrayList<String> lotId = new ArrayList<String>();
                    for (int a = 0; a < n; a++) {
                        Lot l = lst.get(a);
                        lotName.add(l.getName());
                        imageName.add(l.getPictuteName());
                        lotId.add(Integer.toString(l.getItemNumder()));
                    }
                    JSONArray lotNameJ = new JSONArray();
                    lotNameJ.add(lotName);
                    JSONArray imageNameJ = new JSONArray();
                    imageNameJ.add(imageName);
                    JSONArray lotIdJ = new JSONArray();
                    lotIdJ.add(lotId);
                    jsonObject.put("lotName", lotNameJ);
                    jsonObject.put("imageName", imageNameJ);
                    jsonObject.put("lotId", lotIdJ);
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

