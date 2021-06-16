package carent.model;

import carent.utils.Utility;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class CarModelDS implements CarModel<CarBean> {

	private DataSource ds;
	private static final String TABLE_NAME = "veicolo";
	
	public CarModelDS (DataSource datasource) {
		this.ds=datasource;
	}
	
	@Override
	public CarBean doRetrieveByKey(String code) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		CarBean bean = null;
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("SELECT * FROM ? WHERE targa = ?");
			pst.setString(1, TABLE_NAME);
			pst.setString(2, code);
			Utility.print("doRetrieveByKey: "+pst.toString());
			rs = pst.executeQuery();
			if (rs.next()) {
				bean = new CarBean();
				bean.setTarga(rs.getString("targa"));
				bean.setMarca(rs.getString("marca"));
				bean.setModello(rs.getString("modello"));
				bean.setChilometraggio(rs.getInt("chilometraggio"));
				bean.setAnnoImmatricolazione(rs.getInt("anno_imm"));
				bean.setPotenza(rs.getInt("potenza"));
				bean.setAlimentazione(rs.getString("alimentazione"));
				bean.setPrezzo_gg(rs.getInt("prezzo_gg"));
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
		return bean;
	}

	@Override
	public Collection<CarBean> doRetrieveAll(String order) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Collection<CarBean> lista = new LinkedList<CarBean>();
		
		String ricerca = "SELECT * FROM "+TABLE_NAME;
		if (order!=null) {
			ricerca+=" ORDER BY "+order;
		}
		
		try {
			con = ds.getConnection();
			pst = con.prepareStatement(ricerca);
			
			Utility.print("doRetrieveAll: "+pst.toString());
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				CarBean bean = new CarBean();
				bean.setTarga(rs.getString("targa"));
				bean.setMarca(rs.getString("marca"));
				bean.setModello(rs.getString("modello"));
				bean.setChilometraggio(rs.getInt("chilometraggio"));
				bean.setAnnoImmatricolazione(rs.getInt("anno_imm"));
				bean.setPotenza(rs.getInt("potenza"));
				bean.setAlimentazione(rs.getString("alimentazione"));
				bean.setPrezzo_gg(rs.getInt("prezzo_gg"));
				lista.add(bean);
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
		
		return lista;
	}

	@Override
	public boolean doSave(CarBean item) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("INSERT INTO veicolo(targa,marca,modello, alimentazione,potenza,anno_imm,chilometraggio,prezzo_gg) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			pst.setString(1,item.getTarga());
			pst.setString(2, item.getMarca());
			pst.setString(3, item.getModello());
			pst.setString(4, item.getAlimentazione());
			pst.setInt(5, item.getPotenza());
			pst.setInt(6, item.getAnnoImmatricolazione());
			pst.setInt(7, item.getChilometraggio());
			pst.setInt(8,item.getPrezzo_gg());
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

	@Override
	public void doUpdate(CarBean item) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("UPDATE veicolo SET marca=?, modello=?, alimentazione=?, potenza=?, anno_imm=?, chilometraggio=? prezzo_gg=? WHERE targa=?");
			pst.setString(1, item.getMarca());
			pst.setString(2, item.getModello());
			pst.setString(3, item.getAlimentazione());
			pst.setInt(4, item.getPotenza());
			pst.setInt(5, item.getAnnoImmatricolazione());
			pst.setInt(6, item.getChilometraggio());
			pst.setString(7, item.getTarga());
			pst.setInt(8,item.getPrezzo_gg());
			
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

	@Override
	public boolean doDelete(String code) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("DELETE FROM veicolo WHERE targa=?");
			pst.setString(1, code);
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
	
	public Collection<CarBean> doRetrieveAvailableInPeriod(String start, String finish) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Collection<CarBean> lista = new LinkedList<CarBean>();
		try {
			con = ds.getConnection();
			
			//Da modificare
			pst = con.prepareStatement("SELECT * FROM veicolo");
			rs = pst.executeQuery();
			while (rs.next()) {
				CarBean bean = new CarBean();
				bean.setTarga(rs.getString("targa"));
				bean.setMarca(rs.getString("marca"));
				bean.setModello(rs.getString("modello"));
				bean.setChilometraggio(rs.getInt("chilometraggio"));
				bean.setAnnoImmatricolazione(rs.getInt("anno_imm"));
				bean.setPotenza(rs.getInt("potenza"));
				bean.setAlimentazione(rs.getString("alimentazione"));
				bean.setPrezzo_gg(rs.getInt("prezzo_gg"));
				
				lista.add(bean);
				
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
		return lista;
		
	}

	public boolean plateExists (String plate) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("SELECT * FROM veicolo WHERE targa=?");
			pst.setString(1,plate);
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

	public boolean hasRents (String plate) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("SELECT * FROM veicolo v NATURAL JOIN noleggio n WHERE v.targa=?");
			pst.setString(1,plate);
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
}
