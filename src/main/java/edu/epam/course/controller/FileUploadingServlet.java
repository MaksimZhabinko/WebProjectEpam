package edu.epam.course.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(urlPatterns = {"/upload/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(FileUploadingServlet.class);
    // fixme нету вывода фотки, фотки с одним и тем же название нельзя созранять( можно по идее добавить UUID на имя)

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getParameter("url");
        response.setContentType("image/jpeg");
        ServletOutputStream out;
        out = response.getOutputStream();
        FileInputStream flinp = new FileInputStream(url);
        BufferedInputStream buffinp = new BufferedInputStream(flinp);
        BufferedOutputStream buffoup = new BufferedOutputStream(out);
        int ch=0;
        while ((ch=buffinp.read()) != -1) {

            buffoup.write(ch);

        }
        buffinp.close();
        flinp.close();
        buffoup.close();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = null;
        try {
            for (Part part : request.getParts()) {
                if (part.getSubmittedFileName() != null) {
                    part.write("/Users/dasik/Desktop/" + part.getSubmittedFileName());
                    path = "/Users/dasik/Desktop/" + part.getSubmittedFileName();
                }
            }
        } catch (IOException e) {
            logger.error("some error save file or file is exist" + e);
        }
        request.setAttribute("photo", path);
        request.getRequestDispatcher("/pages/uploadTest.jsp").forward(request, response);
    }
}
