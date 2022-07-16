import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RetourSet {

	public static void main(String[] args) {
		try{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/Ecole";
			String login = "postgres";
			String pw = "svoloche";
			
			Connection conn = DriverManager.getConnection(url, login, pw);
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			
			String query = "SELECT prof_nom, prof_prenom FROM professeur";
			ResultSet result = state.executeQuery(query);
			
			int i = 1;
			System.out.println("\n\t-------------------------------");
			System.out.println("\tLecture standard");
			System.out.println("\t-------------------------------");
			
			while(result.next()){
				System.out.println("\tNom : " + result.getString("prof_nom") + "\tPrenom : " 
						+ result.getString("prof_prenom"));
				//on regarde si on se trouve sur la dernière ligne du résultat 
				if(result.isLast())
					System.out.println("\t * DERNIER RESULTAT * ");
				++i;
			}
			//Une fois la lecture terminée, on contrôle si on se trouve bien à l'extérieur des lignes du
			//résultat
			if(result.isAfterLast())
				System.out.println("Terminado");
			
			System.out.println("\n\t-------------------------------");
			System.out.println("\tLecture en sens contraire");
			System.out.println("\t-------------------------------");
			
			while(result.previous()){
				System.out.println("\tNom : " + result.getString("prof_nom") + "\tPrénom : "
						+ result.getString("prof_prenom"));
				if(result.isFirst())
					System.out.println("\t * RETOUR VERS LE FUTUR * ");
			}
			
			if(result.isBeforeFirst())
				System.out.println("TERMINADO");
			
			System.out.println("\n\t-------------------------------");
			System.out.println("\tPositionnement absolu à la place N° " + i/2 + ".");
			System.out.println("\t-------------------------------");
			result.absolute(i/2);
			while(result.next())
				System.out.println("\tNom : " + result.getString("prof_nom") + "\tPrénom : "
						+ result.getString("prof_prenom"));
			
			System.out.println("\n\t-------------------------------");
			System.out.println("\tPositionnement relatif du curseur à la place N° " + (i -(i-2)) + ".");
			System.out.println("\t-------------------------------");
			
			result.relative(-(i-2));
			
			while(result.next())
				System.out.println("\tNom : " + result.getString("prof_nom") + "\tPrénom : " 
						+ result.getString("prof_prenom"));
			
			result.close();
			state.close();			
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
