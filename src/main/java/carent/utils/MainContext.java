package carent.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@WebListener
public class MainContext implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Utility.print("Startup web application");

		ServletContext context = sce.getServletContext();

		DataSource ds = null;

		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/vehicle_rental"); // Nome risorsa in context.xml

			Connection con = null;
			
			try {
				con = ds.getConnection();
				DatabaseMetaData metaData = con.getMetaData();
				Utility.print("DBMS Name: " + metaData.getDatabaseProductName());
				Utility.print("DBMS Version: " + metaData.getDatabaseProductVersion());
			} finally {
				if (con != null)
					con.close();
			}

		} catch (SQLException | NamingException e) {
			Utility.print(e);
		}

		context.setAttribute("DataSource", ds);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		
		context.removeAttribute("DataSource");
		
		Utility.print("Shutdown web application");
	}

}
