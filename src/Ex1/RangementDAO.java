package Ex1;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RangementDAO extends DAO<Rangement> {
	Statement stmt;
	public RangementDAO(Connection con) {
		super(con);
		try {
			stmt = con.createStatement();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public ArrayList<Rangement> findAll(){
		ArrayList<Rangement> list = new ArrayList<Rangement>();
		String refRangement;
		String nomRangement;
		try (ResultSet res = stmt.executeQuery("SELECT * FROM Rangement")){
			//connect = DBMSConnection.getConnection();
			//Statement stmt = connect.createStatement();
			
			while(res.next())
			{
				refRangement=res.getString("refRangement");
			    nomRangement=res.getString("nomRangement");
			    Rangement r = new Rangement(refRangement,nomRangement);
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
	public Rangement find(String id) {
		Rangement result = null;
        try {
            //connect = DBMSConnection.getConnection();
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Rangement WHERE RefRangement = ?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String ref = rs.getString("RefRangement");
                String nom = rs.getString("NomRangement");
                result = new Rangement(ref, nom);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public boolean create(Rangement obj) {
		try {
			connect = DBMSConnection.getConnection();
            PreparedStatement stmt = connect.prepareStatement("INSERT INTO Rangement(RefRangement, NomRangement) VALUES (?, ?)");
            stmt.setString(1, obj.getRefRangement());
            stmt.setString(2, obj.getNomRangement());
            int rows = stmt.executeUpdate();
            stmt.close();
            if(rows > 0) {
            	System.out.println("ajout avec succès");
            	return true;
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public boolean update(Rangement obj) {
		try {
			connect = DBMSConnection.getConnection();
            PreparedStatement stmt = connect.prepareStatement("UPDATE Rangement SET NomRangement = ? WHERE RefRangement = ?");
            stmt.setString(1, obj.getNomRangement());
            stmt.setString(2, obj.getRefRangement());
            int rows = stmt.executeUpdate();
            //stmt.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public boolean delete(Rangement obj) {
		try {
            PreparedStatement stmt = connect.prepareStatement("DELETE FROM Rangement WHERE RefRangement = ?");
            stmt.setString(1, obj.getRefRangement());
            
            PreparedStatement stmt2 = connect.prepareStatement("DELETE FROM Produit WHERE RefRangement = ?");
			stmt2.setString(1, obj.getRefRangement());
			stmt2.executeUpdate();
			
            int rows = stmt.executeUpdate();
            //stmt.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
