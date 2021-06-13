package carent.model;

import carent.utils.Utility;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartModelDS {
	private DataSource ds;
	
	public CartModelDS (DataSource datasource) {
		this.ds=datasource;
	}
	
	public void addToCart (CartItemBean item) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("INSERT INTO carrello(userCode,targa,daData,aData) VALUES (?, ?, ?, ?)");
			pst.setInt(1, item.getUserCode());
			pst.setString(2, item.getTarga());
			pst.setString(3, item.getDaData());
			pst.setString(4, item.getaData());
			
			Utility.print("Sto per eseguire: "+pst.toString());
			
			pst.executeUpdate();
			
		} finally {
			try {
				if (pst!=null)
					pst.close();
			} finally {
				if (con!=null)
					con.close();
			}
		}
	}
	
	public CartBean doRetrieveCartForUser (int userCode) throws SQLException {
		CartBean daRestituire = new CartBean();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("SELECT * FROM carrello WHERE userCode=?");
			pst.setInt(1, userCode);
			rs = pst.executeQuery();
			CartItemBean item;
			while (rs.next()) {
				item = new CartItemBean();
				item.setCodiceItem(rs.getInt("codiceItem"));
				item.setUserCode(rs.getInt("userCode"));
				item.setTarga(rs.getString("targa"));
				item.setDaData(rs.getString("daData"));
				item.setaData(rs.getString("aData"));
				daRestituire.inserisci(item);
			}
			
			return daRestituire;
		} finally {
			try {
				if (pst!=null)
					pst.close();
			} finally {
				if (con!=null)
					con.close();
			}
		}
	}
	
	public void emptyCart (int userCode) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("DELETE * FROM carrello WHERE userCode=?");
			pst.setInt(1, userCode);
			pst.executeUpdate();
		} finally {
			try {
				if (pst!=null)
					pst.close();
			} finally {
				if (con!=null)
					con.close();
			}
		}
	}
	
	public void removeFromCart (int code) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("DELETE FROM carrello WHERE codiceItem=?");
			pst.setInt(1, code);
			Utility.print(pst.toString());
			pst.executeUpdate();
		} finally {
			try {
				if (pst!=null)
					pst.close();
			} finally {
				if (con!=null)
					con.close();
			}
		}
	}
}
