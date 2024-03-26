package Ex1;
import java.sql.*;
import java.util.ArrayList;

public abstract class DAO<T> {
	protected Connection connect= null;
	public DAO(Connection con){
		this.connect=con;
	}
	public abstract ArrayList<T> findAll();
	public abstract T find(String id);
	public abstract boolean create (T obj);
	public abstract boolean update (T obj);
	public abstract boolean delete (T obj);
}
