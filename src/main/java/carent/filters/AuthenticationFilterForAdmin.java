package carent.filters;

import carent.model.UserBean;
import carent.utils.Utility;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns= { "/admin/*"})
public class AuthenticationFilterForAdmin implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Utility.print("Sono entrato nel filtro admin");
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		String uri = hrequest.getRequestURI();
		if (uri.contains("/admin/")) {
			HttpSession session = hrequest.getSession(false);
			if (session!=null) {
				UserBean user = (UserBean) session.getAttribute("utente");
				if (user==null) {
					Utility.print("Non hai eseguito il login");
					hresponse.sendRedirect(hrequest.getContextPath()+"/loginPage.jsp");
					return;
				}
				if (user.getRole().equals("adminrole")) {
					Utility.print("Sei davvero un admin");
					chain.doFilter(request,response);
				} else {
					Utility.print("Non sei un admin...");
					hresponse.sendRedirect(hrequest.getContextPath()+"/loginPage.jsp");
					return;
				}
			} else {
				Utility.print("Non hai eseguito il login...");
				hresponse.sendRedirect(hrequest.getContextPath()+"/loginPage.jsp");
				return;
			}
		}
	}

}
