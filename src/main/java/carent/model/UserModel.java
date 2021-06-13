package carent.model;

import java.sql.SQLException;

public interface UserModel<T> {
	public T fetchUser(String email) throws SQLException;
	public boolean userExists (String email) throws SQLException;
	public boolean wrongPassword (String email, String password) throws SQLException;
	public void registerUser (String email, String password, String name, String surname, String phone) throws SQLException;
}