package carent.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import carent.utils.PasswordHasher;

import javax.sql.DataSource;

import carent.utils.Utility;

public class UserModelDS implements UserModel<UserBean> {
	private DataSource ds;

	public UserModelDS (DataSource datasource) {
		this.ds=datasource;
	}

	@Override
	public UserBean fetchUser(String email) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		UserBean user = null;
		Utility.print("Restituendo le credenziali dell'utente");
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("SELECT * FROM utente WHERE email=?");
			pst.setString(1,email);
			Utility.print(pst.toString());
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new UserBean();
				user.setUserCode(rs.getInt("userCode"));
				user.setEmail(rs.getString("email"));
				user.setPasswd(rs.getString("password"));
				user.setRole(rs.getString("role"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setPhone(rs.getString("phone"));
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

		return user;
	}

	@Override
	public boolean userExists(String email) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Utility.print("Verificando l'esistenza dell'utente...");
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("SELECT * FROM utente WHERE email=?");
			pst.setString(1,email);
			Utility.print(pst.toString());
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

	@Override
	public boolean wrongPassword(String email, String password) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Utility.print("Verificando la correttezza della password...");
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("SELECT * FROM utente WHERE email=? AND password=?");
			pst.setString(1, email);
			pst.setString(2, PasswordHasher.hash(password));
			Utility.print(pst.toString());
			rs = pst.executeQuery();
			if (rs.next()) {
				return false;
			} else {
				return true;
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

	public void registerUser (String email, String password, String name, String surname, String phone) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("INSERT INTO utente(email,password,name,surname,phone) VALUES (?,?,?,?,?)");
			pst.setString(1,email);
			pst.setString(2, PasswordHasher.hash(password));
			pst.setString(3, name);
			pst.setString(4, surname);
			pst.setString(5, phone);
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

	public boolean changePassword (String email, String newpassword) throws SQLException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("UPDATE utente SET password=? WHERE email=?");
			pst.setString(1, PasswordHasher.hash(newpassword));
			pst.setString(2, email);
			Utility.print(pst.toString());
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

	public boolean changeEmail (String oldemail,String newemail) throws SQLException {
		Utility.print("Provando a cambiare email...");
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("UPDATE utente SET email=? where email=?");
			pst.setString(1, newemail);
			pst.setString(2, oldemail);
			Utility.print(pst.toString());
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

	//Vanno aggiunte le funzioni per poter effettuare modifiche ai dati anagrafici

	public boolean removeUser (String email) throws SQLException {
		Utility.print("Provando a cancellare l'utente...");
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("DELETE FROM utente WHERE email=?");
			pst.setString(1, email);
			Utility.print(pst.toString());
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

	public boolean hasRents (String email) throws SQLException {
		Utility.print("Controllando vincoli sull'utente");
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ds.getConnection();
			pst = con.prepareStatement("SELECT * FROM utente u NATURAL JOIN noleggio n WHERE u.email=?");
			pst.setString(1,email);
			ResultSet rs = pst.executeQuery();
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

	public boolean isAdmin (String email) throws SQLException {
		UserBean user = this.fetchUser(email);
		if (user.getRole().equals("adminrole"))
			return true;
		else
			return false;
	}
}
