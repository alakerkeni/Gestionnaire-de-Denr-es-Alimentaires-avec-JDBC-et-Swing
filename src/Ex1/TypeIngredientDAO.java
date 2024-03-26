package Ex1;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeIngredientDAO extends DAO<TypeIngredient> {
	Statement stmt;
	public TypeIngredientDAO(Connection con) {
		super(con);
		try {
			stmt = con.createStatement();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<TypeIngredient> findAll() {
		ArrayList<TypeIngredient> list = new ArrayList<TypeIngredient>();
		  String refType;
		  String nomType;
		  try(ResultSet res = stmt.executeQuery("SELECT * FROM TypeIngredient")) {
			  
			  while(res.next())
			{
			  refType = res.getString("RefType");
			  nomType= res.getString("NomType");
			  list.add(new TypeIngredient(refType,nomType));
			}
			  res.close();
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
		return list;
	}

	@Override
	public TypeIngredient find(String id) {
		TypeIngredient result = null;
		String refType;
		String nomType;
		try {
			String req= "SELECT * FROM TypeIngredient WHERE RefType='"+id+"'";
			ResultSet res = stmt.executeQuery(req);
			if (res.next()) {
				refType = res.getString(1);
				nomType= res.getString(2);
				result = new TypeIngredient(refType,nomType);
			}
			res.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean create(TypeIngredient obj) {
		try {
		 //connect= DBMSConnection.getConnection();
		 PreparedStatement stmt= connect.prepareStatement("INSERT INTO TypeIngredient (refType,nomType) VALUES(?,?)");
		 stmt.setString(1, obj.getRefType());
		 stmt.setString(2, obj.getNomType());
		 int rows =stmt.executeUpdate();
		 if (rows>0) return true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(TypeIngredient obj) {
		try {
			connect= DBMSConnection.getConnection();
			String req="UPDATE TypeIngredient SET  nomType='"+obj.getNomType()+"' WHERE refType='"+obj.getRefType()+"'";
			//PreparedStatement stmt = connect.prepareStatement("UPDATE TypeIngredient SET  nomType=? WHERE refType=?");
			//stmt.setString(1,obj.getNomType());
			//stmt.setString(2,obj.getRefType());
			int rows = stmt.executeUpdate(req);
			return(rows>0);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(TypeIngredient obj) {
		try {
			connect= DBMSConnection.getConnection();
			PreparedStatement stmt = connect.prepareStatement("DELETE FROM TypeIngredient WHERE refType=?");
			stmt.setString(1,obj.getRefType());
			
			
			PreparedStatement stmt3 = connect.prepareStatement("DELETE FROM Produit WHERE refIngredient IN (select refIngredient FROM Ingredient inner join TypeIngredient "
																	+ "ON Ingredient.refType = TypeIngredient.refType "
																	+ " WHERE TypeIngredient.refType=? )");
			stmt3.setString(1,obj.getRefType());
			stmt3.executeUpdate();
			
			PreparedStatement stmt2 = connect.prepareStatement("DELETE FROM Ingredient WHERE refType=?");
			stmt2.setString(1,obj.getRefType());
			stmt2.executeUpdate();
			
			int rows = stmt.executeUpdate();
			stmt.close();
			return(rows>0);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
