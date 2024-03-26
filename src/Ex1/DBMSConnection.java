package Ex1;
import java.sql.*;

public class DBMSConnection {
	
	private static String url ="jdbc:oracle:thin:@localhost:1521:xe";
	private static String driver ="oracle.jdbc.driver.OracleDriver";
	private static String user ="system";
	private static String password="system";
	private static Connection con;
	
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			System.out.println(con.isClosed());
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}


}
