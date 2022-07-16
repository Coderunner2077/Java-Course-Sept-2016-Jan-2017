import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Transact {

	public static void main(String[] args) {
		try{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/Ecole";
			String log = "postgres";
			String psw = "svoloche";
			Connection conn = DriverManager.getConnection(url, log, psw);
			conn.setAutoCommit(false);
			int proc = Runtime.getRuntime().availableProcessors();
			System.out.println(proc);
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM professeur WHERE prof_nom = 'MAMOU'";
			ResultSet res = state.executeQuery(query);
			res.first();
			System.out.println("Nom : " + res.getString("prof_nom") + "\tPrénom : "
					+ res.getString("prof_prenom"));
			//Mise à jour inopérante
			if(!res.getString("prof_prenom").equals("Daniel"))
				state.executeUpdate("UPDATE professeur SET prof_prenom = 'Daniel' WHERE prof_nom = 'MAMOU'");
			//Validation manuelle des mises à jour
			conn.commit();
			res = state.executeQuery(query);
			res.first();
			System.out.println("Nom : " + res.getString("prof_nom") + "\tPrénom : "
					+ res.getString("prof_prenom"));
			res.close();
			state.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}

	}

}
