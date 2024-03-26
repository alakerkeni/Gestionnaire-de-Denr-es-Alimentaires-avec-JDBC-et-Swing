package Ex1;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientDAO extends DAO<Ingredient> {
	Statement stmt;
	public IngredientDAO(Connection con) {
		super(con);
		try {
			stmt = con.createStatement();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Ingredient> findAll() {
		ArrayList<Ingredient> list = new ArrayList<Ingredient>();
		try(ResultSet res = stmt.executeQuery("SELECT * FROM Ingredient");) {
			
			while(res.next())
			{
				TypeIngredientDAO type = new TypeIngredientDAO(connect);
				list.add(new Ingredient(res.getString(1), res.getString(2), type.find(res.getString(3))));
			}
			res.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Ingredient find(String id) {
		//String RefIngredient,NomIngredient,RefType;
		Ingredient result=null;
		try {
			ResultSet res = stmt.executeQuery("SELECT * FROM Ingredient WHERE RefIngredient='"+ id +"'");
			while(res.next())
			{
				//RefIngredient=res.getString(1);
				//NomIngredient=res.getString(2);
				//RefType=res.getString(3);
				TypeIngredientDAO type = new TypeIngredientDAO(connect);
				result=new Ingredient(res.getString(1), res.getString(2), type.find(res.getString(3)));
			}
			res.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean create(Ingredient obj) {
		try {
			//connect= DBMSConnection.getConnection();
			PreparedStatement stmt = connect.prepareStatement("INSERT INTO Ingredient (refIngredient, nomIngredient, refType) VALUES (?, ?, ?)");
			stmt.setString(1, obj.getRefIngredient());
			stmt.setString(2, obj.getNomIngredient());
			stmt.setString(3, obj.getRefType().getRefType());
			int rows = stmt.executeUpdate();
			if( rows > 0) return true;
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean update(Ingredient obj) {
		try {
			String req="UPDATE Ingredient SET nomIngredient = '"+obj.getNomIngredient()+"', refType = '"+obj.getRefType().getRefType()+"' WHERE refIngredient = '"+obj.getRefIngredient()+"'";
			int rows = stmt.executeUpdate(req);
			//stmt.close();
			if(rows > 0) return true;
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean delete(Ingredient obj) {
		try {
			//connect= DBMSConnection.getConnection();
			PreparedStatement stmt = connect.prepareStatement("DELETE FROM Ingredient WHERE refIngredient = ?");
			stmt.setString(1, obj.getRefIngredient());
			
			PreparedStatement stmt2 = connect.prepareStatement("DELETE FROM Composition WHERE refIngredient = ?");
			stmt2.setString(1, obj.getRefIngredient());
			stmt2.executeUpdate();
			
			PreparedStatement stmt3 = connect.prepareStatement("DELETE FROM Produit WHERE refIngredient = ?");
			stmt3.setString(1, obj.getRefIngredient());
			stmt3.executeUpdate();
			
			int rows = stmt.executeUpdate();
			//stmt.close();
			if( rows > 0) return true;
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}
		return false;
	}

}
