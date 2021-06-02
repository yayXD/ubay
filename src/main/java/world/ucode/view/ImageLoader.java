package world.ucode.view;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@WebServlet("/loadimage")
@MultipartConfig

public class ImageLoader extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String imageName = req.getParameter("imageName");
        int r = imageName.lastIndexOf(".");
        String format = imageName.substring(r + 1);
        File output = new File(imageName);
        BufferedImage image = ImageIO.read(output);
        ServletOutputStream os = resp.getOutputStream();
        ImageIO.write(image, format, os);
    }
}
