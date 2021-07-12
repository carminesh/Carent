package carent.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.io.InputStream;
import java.io.File;
import java.nio.file.Files;



@WebServlet("/imgupload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB after which the file will be
        // temporarily stored on disk
        maxFileSize = 1024 * 1024 * 10, // 10MB maximum size allowed for uploaded files
        maxRequestSize = 1024 * 1024 * 50) // 50MB overall size of all uploaded files
public class ImageUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        out.write("Error: GET method is used but POST method is required");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Part filePart = request.getPart("car-image");
            String fileName = request.getParameter("targaPerFoto")+".svg";
            InputStream fileContent = filePart.getInputStream();
            File filePath = new File(getServletContext().getRealPath("/immagini"));
            File file = new File(filePath, fileName);

            filePath.mkdir();

            if(!file.exists())
                Files.copy(fileContent, file.toPath());
            else {
                request.setAttribute("error", "Impossibile processare la richiesta.");
                request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
                response.setStatus(400);
                return;
            }
        } catch(IOException e) {
            e.printStackTrace();

            request.setAttribute("error", "Non &egrave; stato possibile aggiungere la foto del veicolo.");
            request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
            response.setStatus(400);

            return;
        }

        request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
        return;
    }
}
