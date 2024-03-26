package Ex1;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompositionDAO extends DAO<Composition> {
	Statement stmt;
	public CompositionDAO(Connection con) {
		super(con);
		try {
			stmt = con.createStatement();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Composition> findAll() {
		ArrayList<Composition> list = new ArrayList<Composition>();
		//int refComposition;
	    //double quantiteComposition;
	    RecetteDAO refRecette;
	    IngredientDAO refIngredient;

		try (ResultSet res = stmt.executeQuery("SELECT * FROM Composition")){
			
			while(res.next())
			{
				//refComposition=res.getInt("refComposition");
				//quantiteComposition=res.getDouble("quantiteComposition");
				refRecette=new RecetteDAO(connect);
				refIngredient=new IngredientDAO(connect);
			    
				Composition r = new Composition(res.getInt(1),res.getDouble(2),refRecette.find(res.getString(3)),refIngredient.find(res.getString(4)));
			    list.add(r);
			}
			res.close();
		}
		catch(Exception e)
		{
			
		}
		return list;
	}

	@Override
	public Composition find(String id) {
		Composition result=null;
		try {
		PreparedStatement stmt= connect.prepareStatement("SELECT * FROM Composition WHERE RefComposition = ?");
		stmt.setString(1, id);
		ResultSet res = stmt.executeQuery();
		if(res.next()) {
			RecetteDAO refRecette=new RecetteDAO(connect);
			IngredientDAO refIngredient =new IngredientDAO(connect);
			result = new Composition(res.getInt(1),res.getDouble(2),refRecette.find(res.getString(3)),refIngredient.find(res.getString(4)));
		}
		res.close();
		}catch(Exception e) {
			
		}
	return result;
	}

	@Override
	public boolean create(Composition obj) {
		try {
			connect = DBMSConnection.getConnection();
			PreparedStatement stmt = connect.prepareStatement("INSERT INTO Composition (refComposition, quantiteComposition, refRecette, refIngredient) VALUES (?, ?, ?, ?)");
			stmt.setInt(1, obj.getRefComposition());
			stmt.setDouble(2, obj.getQuantiteComposition());
			stmt.setString(3, obj.getRefRecette().getRefRecette());
			stmt.setString(4, obj.getRefIngredient().getRefIngredient());
			int rows = stmt.executeUpdate();
	        if (rows > 0) {
	            return true;
	        }
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		return false;
	}

	@Override
	public boolean update(Composition obj) {
		try {
			//connect = DBMSConnection.getConnection();
			//PreparedStatement stmt = connect.prepareStatement("UPDATE Composition SET quantiteComposition = ?, refRecette = ?, refIngredient = ? WHERE refComposition = ?");
			String req ="UPDATE Composition SET quantiteComposition = "+obj.getQuantiteComposition()+", refRecette = '"+obj.getRefRecette().getRefRecette()+"', refIngredient = '"+obj.getRefIngredient().getRefIngredient()+"' WHERE refComposition = '"+obj.getRefComposition()+"'";
			//stmt.setDouble(1, obj.getQuantiteComposition());
			//stmt.setString(2, obj.getRefRecette());
			//stmt.setString(3, obj.getRefIngredient());
			//stmt.setInt(4, obj.getRefComposition());
			//stmt.executeUpdate();
			int rows = stmt.executeUpdate(req);
	        if (rows > 0) {
	            return true;
	        }
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Composition obj) {
		try {
			//connect = DBMSConnection.getConnection();
			PreparedStatement stmt = connect.prepareStatement("DELETE FROM Composition WHERE refComposition = ?");
			stmt.setInt(1, obj.getRefComposition());
			stmt.executeUpdate();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
