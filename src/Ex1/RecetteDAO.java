package Ex1;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecetteDAO extends DAO<Recette> {
	Statement stmt;
	public RecetteDAO(Connection con) {
		super(con);
		try {
			stmt = con.createStatement();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Recette> findAll() {
		ArrayList<Recette> list = new ArrayList<Recette>();
		String refRecette;
		String nomRecette;
		String descriptifRecette;
		int caloriesRecette;
		String difficulte;
		int tempsPreparation;
		int tempsCuisson;
		int nbPersonnes;
		try(ResultSet res = stmt.executeQuery("SELECT * FROM Recette")) {
			//Statement stmt = (Statement) this.connect.createStatement();
			
			while(res.next())
			{
				refRecette=res.getString("refRecette");
			    nomRecette=res.getString("nomRecette");
			    descriptifRecette=res.getString("descriptifRecette");
			    caloriesRecette=res.getInt("caloriesRecette");
			    difficulte=res.getString("difficulte");
			    tempsPreparation=res.getInt("tempsPreparation");
			    tempsCuisson=res.getInt("tempsCuisson");
			    nbPersonnes=res.getInt("nbPersonnes");
			    
			    Recette r = new Recette(refRecette,nomRecette,descriptifRecette,caloriesRecette,difficulte,tempsPreparation,tempsCuisson,nbPersonnes);
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
	public Recette find(String id) {
		Recette result = null;
		String refRecette;
		String nomRecette;
		String descriptifRecette;
		int caloriesRecette;
		String difficulte;
		int tempsPreparation;
		int tempsCuisson;
		int nbPersonnes;
        try {
            //connect = DBMSConnection.getConnection();
            //PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Recette WHERE RefRecette = ?");
            //stmt.setString(1, id);
        	String req = "SELECT * FROM Recette WHERE RefRecette='" + id + "'";
            ResultSet res = stmt.executeQuery(req);
            if (res.next()) {
            	refRecette=res.getString("refRecette");
			    nomRecette=res.getString("nomRecette");
			    descriptifRecette=res.getString("descriptifRecette");
			    caloriesRecette=res.getInt("caloriesRecette");
			    difficulte=res.getString("difficulte");
			    tempsPreparation=res.getInt("tempsPreparation");
			    tempsCuisson=res.getInt("tempsCuisson");
			    nbPersonnes=res.getInt("nbPersonnes");
			    
			    result = new Recette(refRecette,nomRecette,descriptifRecette,caloriesRecette,difficulte,tempsPreparation,tempsCuisson,nbPersonnes);
            }
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public boolean create(Recette obj) {
	    try {
	    	//(refRecette, nomRecette, descriptifRecette, caloriesRecette, difficulte, tempsPreparation, tempsCuisson, nbPersonnes)
	    	String sql = "INSERT INTO Recette(RefRecette, NomRecette, DescriptifRecette, CaloriesRecette, Difficulte, TempsPreparation, TempsCuisson, NbPersonnes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement stmt = connect.prepareStatement(sql);
	        stmt.setString(1, obj.getRefRecette());
	        stmt.setString(2, obj.getNomRecette());
	        stmt.setString(3, obj.getDescriptifRecette());
	        stmt.setInt(4, obj.getCaloriesRecette());
	        stmt.setString(5, obj.getDifficulte());
	        stmt.setInt(6, obj.getTempsPreparation());
	        stmt.setInt(7, obj.getTempsCuisson());
	        stmt.setInt(8, obj.getNbPersonnes());
	        int rows = stmt.executeUpdate();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	
	@Override
	public boolean update(Recette obj) {
		 try {
		        //connect = DBMSConnection.getConnection();
		        //PreparedStatement stmt = connect.prepareStatement("UPDATE Recette SET nomRecette=?, descriptifRecette=?, caloriesRecette=?, difficulte=?, tempsPreparation=?, tempsCuisson=?, nbPersonnes=? WHERE refRecette=?");
		        String req="UPDATE Recette SET nomRecette='"+obj.getNomRecette()+"', descriptifRecette='"+obj.getDescriptifRecette()+"', caloriesRecette="+obj.getCaloriesRecette()+", difficulte='"+obj.getDifficulte()+"', tempsPreparation="+obj.getTempsPreparation()+", tempsCuisson="+obj.getTempsCuisson()+", nbPersonnes="+obj.getNbPersonnes()+"WHERE refRecette='"+obj.getRefRecette()+"'";
		        /*stmt.setString(1, obj.getNomRecette());
		        stmt.setString(2, obj.getDescriptifRecette());
		        stmt.setInt(3, obj.getCaloriesRecette());
		        stmt.setString(4, obj.getDifficulte());
		        stmt.setInt(5, obj.getTempsPreparation());
		        stmt.setInt(6, obj.getTempsCuisson());
		        stmt.setInt(7, obj.getNbPersonnes());
		        stmt.setString(8, obj.getRefRecette());*/
		        int rows = stmt.executeUpdate(req);
		        if (rows > 0) {
		            return true;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		return false;
	}

	@Override
	public boolean delete(Recette obj) {
	    try {
	    	String req = "DELETE FROM Recette WHERE RefRecette='" + obj.getRefRecette() + "'";
            //ResultSet res = stmt.executeQuery(req);
	        int rows = stmt.executeUpdate(req);
	        
	        PreparedStatement stmt2 = connect.prepareStatement("DELETE FROM Composition WHERE RefRecette=?");
			stmt2.setString(1,obj.getRefRecette());
			stmt2.executeUpdate();
	        
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


}
