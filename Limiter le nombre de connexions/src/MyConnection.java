import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	private String url = "jdbc:postgresql://localhost:5432/Ecole";
	private String user = "postgres";
	private String psw = "svoloche";
	
	//Objet Connection
	private static Connection connect;
	
	//Constructeur priv�
	private MyConnection(){
		try{
			connect = DriverManager.getConnection(url, user, psw);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	//M�thode qui va retourner mon instance et la cr�er si elle n'existe pas
	public static Connection getInstance(){
		if(connect == null){
			new MyConnection();
			System.out.println("INSTANCIATION DE LA CONNEXION SQL !");
		}
		else
			System.out.println("Connexion d�j� existante ! ");
		return connect;
	}
}
