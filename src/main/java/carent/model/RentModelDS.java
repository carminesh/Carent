package carent.model;

import carent.utils.Utility;

import javax.sql.DataSource;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Collection;
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
                bean.setCheckoutData(rs.getString("checkoutData"));
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
                bean.setCheckoutData(rs.getString("checkoutData"));
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
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            con = ds.getConnection();
            pst = con.prepareStatement("INSERT INTO noleggio(userCode,targa,daData,aData,checkoutData,prezzo) VALUES (?,?,?,?,?,?)");
            pst.setInt(1, userCode);
            pst.setString(2, item.getAuto().getTarga());
            pst.setString(3, item.getDaData());
            pst.setString(4, item.getaData());
            pst.setString(5, formatter.format(new Date()));
            pst.setDouble(6, item.getPrezzoTotale());
            Utility.print(pst.toString());
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

    public Collection<RentBean> fetchEarlyRentsFromUser (String email) throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Collection<RentBean> lista = new LinkedList<RentBean>();
        RentBean bean;
        try {
            con = ds.getConnection();
            pst = con.prepareStatement("SELECT n.* FROM noleggio n NATURAL JOIN utente u WHERE u.email=? ORDER BY n.checkoutData DESC LIMIT 3");
            pst.setString(1,email);
            rs = pst.executeQuery();
            while (rs.next()) {
                bean = new RentBean();
                bean.setRentCode(rs.getInt("rentCode"));
                bean.setUserCode(rs.getInt("userCode"));
                bean.setTarga(rs.getString("targa"));
                bean.setDaData(rs.getString("daData"));
                bean.setaData(rs.getString("aData"));
                bean.setCheckoutData(rs.getString("checkoutData"));
                bean.setPrezzo(rs.getDouble("prezzo"));
                lista.add(bean);
            }
            return lista;
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