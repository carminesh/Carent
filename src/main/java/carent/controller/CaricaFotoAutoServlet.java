 
package carent.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "/CaricaFotoAutoServlet", urlPatterns = { "/admin/CaricaFotoAutoServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB after which the file will be
														// temporarily stored on disk
		maxFileSize = 1024 * 1024 * 10, // 10MB maximum size allowed for uploaded files
		maxRequestSize = 1024 * 1024 * 50) // 50MB overall size of all uploaded files
public class CaricaFotoAutoServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String SAVE_DIR = "";

	public void init() {
		// Get the file location where it would be stored
		SAVE_DIR = "imgs"+File.separator+"cars";
	}

	public CaricaFotoAutoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		

		out.write("Error: GET method is used but POST method is required");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String savePath = (String) request.getServletContext().getAttribute("carImagesPath");
		String fileName = request.getParameter("targaPerFoto");

		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		String message = "upload =\n";
		if (request.getParts() != null && request.getParts().size() > 0) {
			for (Part part : request.getParts()) {
				if (fileName != null && !fileName.equals("")) {
					part.write(savePath + File.separator +fileName+".jpg");
					System.out.println("Ho salvato la foto in :"+savePath + File.separator + fileName);
					message = message + fileName + "\n";
				} else {
					request.setAttribute("error", "Errore: Bisogna selezionare almeno un file");

				}
			}
		}

		request.setAttribute("message", message);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PhotoManager.jsp");
		dispatcher.forward(request, response);
	}
}
