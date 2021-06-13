package carent.model;

import java.sql.SQLException;
import java.util.Collection;

public interface CarModel<T> {
	public T doRetrieveByKey(String code) throws SQLException;
	
	public Collection<T> doRetrieveAll (String order) throws SQLException;
	
	public void doSave (T item) throws SQLException;
	
	public void doUpdate (T item) throws SQLException;
	
	public void doDelete (String code) throws SQLException;
}
