package Ex1;
import java.sql.*;

public class DBMSConnection {
	
	private static String url ="@{<ipAddress>|<hostName>}:<port>:<SID>";
	private static String driver ="oracle.jdbc.driver.OracleDriver";
	private static String user ="yourUsername";
	private static String password="yourPassword";
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
