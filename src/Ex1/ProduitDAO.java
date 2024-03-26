package Ex1;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProduitDAO extends DAO<Produit> {
	Statement stmt;
	public ProduitDAO(Connection con) {
		super(con);
		try {
			stmt = con.createStatement();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Produit> findAll() {
		 ArrayList<Produit> list = new ArrayList<Produit>();
		 String refProduit;
		 String descriptifProduit;
		 Date datePeremption;
		 int quantiteProduit;
		 double prixProduit;
		 RangementDAO refRangement;
		 IngredientDAO refIngredient;
		
		 try {
			//this.connect = DBMSConnection.getConnection();
			//Statement stmt = (Statement) this.connect.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM Produit");
			while(res.next())
			{
				refProduit=res.getString(1);
				descriptifProduit=res.getString(2);
				datePeremption=res.getDate(3);
				quantiteProduit=res.getInt(4);
				prixProduit=res.getDouble(5);
				refRangement=new RangementDAO(connect);
				refIngredient=new IngredientDAO(connect);
			    
			    Produit r = new Produit(refProduit,descriptifProduit,datePeremption,quantiteProduit,prixProduit,refRangement.find(res.getString(6)),refIngredient.find(res.getString(7)));
			    list.add(r);
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
	public Produit find(String id) {
		 Produit result = null;
		 String refProduit;
		 String descriptifProduit;
		 Date datePeremption;
		 int quantiteProduit;
		 double prixProduit;
		 RangementDAO refRangement;
		 IngredientDAO refIngredient;
		 
		 try (ResultSet res = stmt.executeQuery("SELECT * FROM Produit WHERE RefProduit = '"+id+"'");){
			 	//Connection con = DBMSConnection.getConnection();
				//PreparedStatement stmt= connect.prepareStatement("SELECT * FROM Produit WHERE RefProduit = ?");
				
				//stmt.setString(1, id);
				
				if(res.next()) {
					refProduit=res.getString(1);
					descriptifProduit=res.getString(2);
					datePeremption=res.getDate(3);
					quantiteProduit=res.getInt(4);
					prixProduit=res.getDouble(5);
					refRangement=new RangementDAO(connect);
					refIngredient=new IngredientDAO(connect);
				    
				    
				    result = new Produit(refProduit,descriptifProduit,datePeremption,quantiteProduit,prixProduit,refRangement.find(res.getString(6)),refIngredient.find(res.getString(7)));
				    //System.out.println(result); 
				}
				res.close();
		 }catch(Exception e) {
			 
		 }
	return result;
	}

	@Override
	public boolean create(Produit obj) { 
		try {
			//connect = DBMSConnection.getConnection();
			PreparedStatement stmt = connect.prepareStatement("INSERT INTO Produit (refProduit, descriptifProduit,datePeremption,quantiteProduit,prixProduit,refRangement,refIngredient) VALUES(?, ?,?,?,?,?,?) ");
			stmt.setString(1,obj.getRefProduit());
			stmt.setString(2,obj.getDescriptifProduit());
			stmt.setDate(3,obj.getDatePeremption());
			stmt.setInt(4,obj.getQuantiteProduit());
			stmt.setDouble(5,obj.getPrixProduit());
			stmt.setString(6,obj.getRefRangement().getRefRangement());
			stmt.setString(7,obj.getRefIngredient().getRefIngredient());
			return (stmt.executeUpdate()>0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Produit obj) {
	    try {
	        String req = "UPDATE Produit SET " +
	            "descriptifProduit = '" + obj.getDescriptifProduit() + "', " +
	            "datePeremption = '" + new SimpleDateFormat("dd-MM-yyyy").format(obj.getDatePeremption()) + "', " +
	            "quantiteProduit = " + obj.getQuantiteProduit() + ", " +
	            "prixProduit = " + obj.getPrixProduit() + ", " +
	            "refRangement = '" + obj.getRefRangement().getRefRangement() + "', " +
	            "refIngredient = '" + obj.getRefIngredient().getRefIngredient() + "' " +
	            "WHERE refProduit = '" + obj.getRefProduit() + "'";
	        return (stmt.executeUpdate(req) > 0);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}


	@Override
	public boolean delete(Produit obj) {
		try {
			//connect = DBMSConnection.getConnection();
			String req="DELETE FROM Produit WHERE refProduit= '"+obj.getRefProduit()+"'";
			//stmt.setString(1,obj.getRefProduit());
			//stmt.close();
			return (stmt.executeUpdate(req)>0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

}
