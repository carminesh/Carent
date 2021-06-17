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

    public boolean removeRent (int rentCode) throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = ds.getConnection();
            pst = con.prepareStatement("DELETE FROM noleggio WHERE rentCode=?");
            pst.setInt(1,rentCode);
            int ris = pst.executeUpdate();
            return ris>0;
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

    public boolean rentExists (int rentCode) throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            pst = con.prepareStatement("SELECT * FROM noleggio WHERE rentCode=?");
            pst.setInt(1,rentCode);
            rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
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

    public boolean insertRentasCartItem (CartItemBean item, int userCode) throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = ds.getConnection();
            pst = con.prepareStatement("INSERT INTO noleggio(userCode,targa,daData,aData,prezzo) VALUES (?,?,?,?,?)");
            pst.setInt(1, userCode);
            pst.setString(2, item.getAuto().getTarga());
            pst.setString(3, item.getDaData());
            pst.setString(4, item.getaData());
            pst.setDouble(5, item.getPrezzoTotale());
            int ris = pst.executeUpdate();
            return ris > 0;
        } finally {
            try {
                if (pst != null)
                    pst.close();
            } finally {
                if (con != null)
                    con.close();
            }
        }
    }
}