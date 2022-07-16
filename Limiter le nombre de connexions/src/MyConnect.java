import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnect {
	private static String url = "jdbc:postgresql://localhost:5432/Ecole";
	private static String user = "postgres";
	private static String psw = "svoloche";
	private static Connection connect;
	
	public static Connection getInstance(){
		try{
			if(connect == null)
				connect = DriverManager.getConnection(url, user, psw);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return connect;
	}
}
