package carent.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/checkout")
public class CheckoutPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("fromServlet", "enabled");
        RequestDispatcher dispatcher;
        ServletContext context = this.getServletContext();
        HttpSession session = request.getSession(false);
        dispatcher = this.getServletContext().getRequestDispatcher("/checkoutPage.jsp");
        dispatcher.forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
