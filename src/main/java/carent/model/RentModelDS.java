package carent.model;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class RentModelDS {
    private DataSource ds;

    public RentModelDS (DataSource datasource) {
        this.ds=datasource;
    }

    public Collection<RentBean> fetchRentsFromUser (String email, int limit) throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Collection<RentBean> lista = new LinkedList<RentBean>();
        try {
            con = ds.getConnection();
            if (limit<0) {
                pst = con.prepareStatement("SELECT n.* FROM noleggio n NATURAL JOIN utente u WHERE u.email=?");
                pst.setString(1, email);
            } else {
                pst = con.prepareStatement("SELECT n.* FROM noleggio n NATURAL JOIN utente u WHERE u.email=? LIMIT ?");
                pst.setString(1,email);
                pst.setInt(2,limit);
            }

            rs = pst.executeQuery();

            while (rs.next()) {
                RentBean bean = new RentBean();
                bean.setRentCode(rs.getInt("rentCode"));
                bean.setUserCode(rs.getInt("userCode"));
                bean.setTarga(rs.getString("targa"));
                bean.setDaData(rs.getString("daData"));
                bean.setaData(rs.getString("aData"));
                bean.setPrezzo(rs.getDouble("prezzo"));
                lista.add(bean);
            }
            return lista;
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

    public Collection<RentBean> fetchAllRents () throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Collection<RentBean> lista = new LinkedList<RentBean>();
        try {
            con = ds.getConnection();
            pst = con.prepareStatement("SELECT * FROM noleggio");
            rs = pst.executeQuery();

            while (rs.next()) {
                RentBean bean = new RentBean();
                bean.setRentCode(rs.getInt("rentCode"));
                bean.setUserCode(rs.getInt("userCode"));
                bean.setTarga(rs.getString("targa"));
                bean.setDaData(rs.getString("daData"));
                bean.setaData(rs.getString("aData"));
                bean.setPrezzo(rs.getDouble("prezzo"));
                lista.add(bean);
            }
            return lista;
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