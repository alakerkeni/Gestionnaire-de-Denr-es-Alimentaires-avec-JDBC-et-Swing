package Ex1;
import java.sql.*;
import java.util.ArrayList;


public class Test {

	/*private static String url ="jdbc:oracle:thin:@localhost:1521:xe";
	private static String driver ="oracle.jdbc.driver.OracleDriver";
	private static String user ="system";
	private static String password="Ala_Oracle2023";
	private static Connection con ;*/
	
	public static void main(String[] args) {
	        /*try {
	            Class.forName(driver);
	            Connection con = DriverManager.getConnection(url, user, password);
	            System.out.println("Connection successful.");
	            }

	            con.close();
	            System.out.println("Connection closed.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }*/
		try {
			Connection con= DBMSConnection.getConnection();
			/*******testing rangement********/
			RangementDAO daoRang= new RangementDAO(con);
			//ArrayList<Rangement> list= new ArrayList<Rangement>();
			//System.out.println(list=daoRang.findAll());
			/*******testing recette*********/
			RecetteDAO daoR= new RecetteDAO(con);
			//Recette r1= new Recette("t0002", "test2","descriptifRecette", 750, "facile" , 50, 30, 50);
			//System.out.println(daoR.create(r1));
			//ArrayList<Recette> list= new ArrayList<Recette>();
			//System.out.println(list=daoR.findAll());
			//Recette r1= daoR.find("r0001");
			//System.out.println("daoR.delete(r1)"+" : "+daoR.find("r0002"));
			/*******testing TypeIngredient*********/
			//TypeIngredient i1 = new TypeIngredient("a123", "lemon");
			TypeIngredientDAO daot = new TypeIngredientDAO(con);
			//ArrayList<TypeIngredient> list= new ArrayList<TypeIngredient>();
			//System.out.println(daot.update(i1));
			//TypeIngredient t1;
			//System.out.println(t1=daot.find("t0021"));
			/*******testing Ingredient*********/
			//ArrayList<Ingredient> list= new ArrayList<Ingredient>();
			IngredientDAO daoI = new IngredientDAO(con);		
			//System.out.println(list=daoI.findAll());
			//System.out.println(daoI.create(new Ingredient("test1","test1",t1)));
			//Ingredient t2=new Ingredient("test1","testUpdate",t1);
			//System.out.println(daoI.delete(dao.find("test1")));
			//System.out.println(list=daoI.findAll());
			/*******testing composition*********/
			//ArrayList<Composition> list= new ArrayList<Composition>();
			CompositionDAO daoC = new CompositionDAO(con);
			//System.out.println(daoC.create(new Composition (1777,77.14,daoR.find("r0003"),daoI.find("i0010"))));
			//System.out.println(daoC.delete(daoC.find("5")));
			//System.out.println(list=daoC.findAll());
			/*******testing Produit*********/
			//ArrayList<Produit> list= new ArrayList<Produit>();
			ProduitDAO daoP = new ProduitDAO(con);
			//System.out.println(daoP.find("P00001 "));
			//Produit produit = new Produit("REF001", "Farine de blé",new Date(System.currentTimeMillis()), 10, 2.99, daoRang.find("R0009"), daoI.find("i0017"));
			//System.out.println(daoP.create(produit));
			//System.out.println(daoP.delete(new Produit("REF001", "Farinesèche",new Date(System.currentTimeMillis()), 10, 2.99, daoRang.find("R0009"), daoI.find("i0017"))));
			//System.out.println(list=daoP.findAll());
			MaFenetre fen = new MaFenetre(con) ;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}