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
			//On crée la requête :
			String query = "SELECT prof_nom, prof_prenom FORM professeur ";
			//Premier trou pour le nom du professeur
			query += "WHERE prof_nom = ? ";
			//Deuxième trou pour l'identifiant du professeur
			query += "OR prof_id = ?";
			
			//On crée l'objet avec la requête en paramètre
			PreparedStatement prepare = conn.prepareStatement(query);
			//On remplace le premier trou par le nom du professeur
			prepare.setString(1, "MAMOU");
			//On remplace le 2nd trou par l'identifiant
			prepare.setInt(2, 2);
			// On affiche la requête exécutée
			System.out.println(prepare.toString());
			//On modifie le premier trou
			prepare.setString(1, "TOTO");
			//On affiche de nouveau la requête exécutée
			System.out.println(prepare.toString());
			//On modifie le 2e trou
			prepare.setInt(2, 323432);
			//On affiche de nouveau la requête
			System.out.println(prepare.toString());
			//On réinitialise la requête
			prepare.clearParameters();
			System.out.println(prepare.toString());
			
			prepare.close();
			state.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
