package carent.filters;

import carent.model.UserBean;
import carent.utils.Utility;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns= { "/user/*"})
public class AuthenticationFilterForUser implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		String uri = hrequest.getRequestURI();
		if (uri.contains("/user/")) {
			HttpSession session = hrequest.getSession(false);
			if (session!=null) {
				UserBean user = (UserBean) session.getAttribute("utente");
				if (user==null) {
					hresponse.sendRedirect(hrequest.getContextPath()+"/loginPage.jsp");
					return;
				}
				if (user.getRole().equals("userrole")) {
					Utility.print("Ok tutto apposto sei un utente");
					chain.doFilter(request,response);
				} else {
					hresponse.sendRedirect(hrequest.getContextPath()+"/loginPage.jsp");
					return;
				}
			} else {
				hresponse.sendRedirect(hrequest.getContextPath()+"/loginPage.jsp");
				return;
			}
		}
	}

}
