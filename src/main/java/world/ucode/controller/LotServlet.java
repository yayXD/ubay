package world.ucode.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import world.ucode.model.BidRunner;
import world.ucode.model.LotRunner;
import world.ucode.model.RegistrationRunner;
import world.ucode.pojo.Registration;
import world.ucode.utilits.CheckUser;
import world.ucode.utilits.RandomValue;

@WebServlet("/seller")
@MultipartConfig
public class LotServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/ubay_war/jsp/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        CheckUser cU = new CheckUser();
        String login = req.getParameter("login");
        int hash = Integer.parseInt(req.getParameter("hash"));
        if (cU.checkSignIn(login, cU.getClientIpAddress(req), hash) == true &&
                cU.checkRole(login, "0")) {
            String name = req.getParameter("name");
            int type = Integer.parseInt(req.getParameter("type"));
            String description = req.getParameter("description");
            int price = Integer.parseInt(req.getParameter("price"));
            int increase = Integer.parseInt(req.getParameter("increase"));
            String startTim = req.getParameter("startTime");
            String durat = req.getParameter("duration");
            RegistrationRunner registRun = new RegistrationRunner();
            registRun.getSessionFactory();
            Registration reg = registRun.getPass(login);
            // if(reg.getLogin().equals("0") == true) {
            LotRunner lotRunner = new LotRunner();
            lotRunner.getSessionFactory();
            lotRunner.createLot();
            RandomValue randVal = new RandomValue();
            int id_n = randVal.makeItemNumber();
            String pictureName = null;
            if(req.getParameter("load_image").equals("true") == true) {
                Part part = req.getPart("image");
                InputStream file = part.getInputStream();
                BufferedImage image = ImageIO.read(file);
//                String Name = Paths.get(part.getSubmittedFileName()).getFileName().toString();
//                int r = Name.lastIndexOf(".");
                String format = "jpg"; //Name.substring(r + 1);
                pictureName = Integer.toString(id_n) + "." + format;
                File output = null;
                try {
                    output = new File(pictureName); //File.separator

                    //output.getParentFile().mkdirs();
                    ImageIO.write(image, format, output);
                }
                catch (Exception ex){
//                    System.out.println(System.getProperty("user.dir"));
//                    System.out.println("============================");
                    System.out.println(ex);
                    System.out.println(output.getPath());
                }
            }
            lotRunner.addLot(++id, id_n, name, type, login, description, price, increase,
                    startTim, durat, pictureName, "passive", null, 0, null, 0);
        }
    }
}



