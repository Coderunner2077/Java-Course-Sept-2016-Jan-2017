import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class State {

	public static void main(String[] args) {
		try{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/Ecole";
			String log = "postgres";
			String pw = "svoloche";
			Connection conn = DriverManager.getConnection(url, log, pw);
			//On autorise le scroll et la mise à jour de l'affichage
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, //avec INSENSITIVE ==> OK
			ResultSet.CONCUR_READ_ONLY);
			PreparedStatement prepare = conn.prepareStatement("UPDATE professeur SET prof_prenom = ? "
					+ "WHERE prof_nom = 'MAMOU'");
			String query = "SELECT prof_nom, prof_prenom FROM professeur WHERE prof_nom = 'MAMOU'";
			ResultSet res = state.executeQuery(query);
			//je positionne au bon endroit
			res.first();
			System.out.println(res.getString("prof_nom") + " " + res.getString("prof_prenom"));
			//On paramètre la requête préparée
			prepare.setString(1, "George");
			//On exécute
			prepare.executeUpdate();
			//j'actualise ma requête
			res = state.executeQuery(query);
			res.first();
			System.out.println(res.getString("prof_nom") + " " + res.getString("prof_prenom"));
			//On paramètre la requête préparée
			prepare.setString(1, "Daniel");
			//On exécute !
			prepare.executeUpdate();
			res = state.executeQuery(query);
			res.first();
			System.out.println(res.getString("prof_nom") + " " + res.getString("prof_prenom"));
			state.executeUpdate("INSERT INTO professeur(prof_nom, prof_prenom) VALUES ('SALMONO', 'Dylan')");
			res = state.executeQuery("SELECT prof_id, prof_nom, prof_prenom FROM professeur WHERE "
					+ "prof_nom = 'SALMONO'");
			res.first();
			System.out.println(res.getInt("prof_id") + " " + res.getString("prof_nom") + " " 
			+ res.getString("prof_prenom"));
			int b = state.executeUpdate("DELETE FROM professeur WHERE prof_nom LIKE 'SALMON%'");
			System.out.println("Valeur renvoyée : " + b);
			res = state.executeQuery("SELECT prof_id, prof_nom, prof_prenom FROM professeur "
					+ "ORDER BY prof_id");
			while(res.next())
				System.out.println(res.getInt("prof_id") + " " + res.getString("prof_nom") 
						+ " " + res.getString("prof_prenom"));
			res.close();
			prepare.close();
			state.close();
			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}

	}

}
