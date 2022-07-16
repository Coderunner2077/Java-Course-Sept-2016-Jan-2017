import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Prepare {

	public static void main(String[] args) {
		try{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/Ecole";
			String user = "postgres";
			String psw = "svoloche";
			
			Connection conn = DriverManager.getConnection(url, user, psw);
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE);
			//ResultSet result = state.getResultSet();
			//ResultSetMetaData resultMeta = result.getMetaData();
			//On cr�e la requ�te :
			String query = "SELECT prof_nom, prof_prenom FORM professeur ";
			//Premier trou pour le nom du professeur
			query += "WHERE prof_nom = ? ";
			//Deuxi�me trou pour l'identifiant du professeur
			query += "OR prof_id = ?";
			
			//On cr�e l'objet avec la requ�te en param�tre
			PreparedStatement prepare = conn.prepareStatement(query);
			//On remplace le premier trou par le nom du professeur
			prepare.setString(1, "MAMOU");
			//On remplace le 2nd trou par l'identifiant
			prepare.setInt(2, 2);
			// On affiche la requ�te ex�cut�e
			System.out.println(prepare.toString());
			//On modifie le premier trou
			prepare.setString(1, "TOTO");
			//On affiche de nouveau la requ�te ex�cut�e
			System.out.println(prepare.toString());
			//On modifie le 2e trou
			prepare.setInt(2, 323432);
			//On affiche de nouveau la requ�te
			System.out.println(prepare.toString());
			//On r�initialise la requ�te
			prepare.clearParameters();
			System.out.println(prepare.toString());
			
			prepare.close();
			state.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
