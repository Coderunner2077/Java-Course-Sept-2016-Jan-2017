import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Modif {

	public static void main(String[] args) {
		try{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/Ecole";
			String log = "postgres";
			String psw = "svoloche";
			
			Connection conn = DriverManager.getConnection(url, log, psw);
			
			//On autorise la mise à jour des données et la mise à jour de l'affichage
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);	

			//On va charcher une ligne dans la base des données
			String query = "SELECT prof_id, prof_nom, prof_prenom FROM professeur WHERE prof_nom = 'MAMOU'";
			ResultSet res = state.executeQuery(query);
			res.first();
			//On affiche ce que l'on trouve
			
			System.out.println("Nom : " + res.getString("prof_nom") + "\tPrénom : "
					+ res.getString("prof_prenom"));
			
			
			//On met à jour les champs
			res.updateString("prof_nom", "COURTEL");
			res.updateString("prof_prenom", "Angelo");
			//on valide
			res.updateRow();
			
			System.out.println("*********************************");
		    System.out.println("APRES MODIFICATION : ");
		    System.out.println("\tNOM : " + res.getString("prof_nom") + "\tPrénom : " 
		    		 + res.getString("prof_prenom"));
		    
		    //On remet les info de départ
		    res.updateString("prof_nom", "MAMOU");
		    res.updateString("prof_prenom", "Daniel");
		    //On valide
		    res.updateRow();
		    
		    System.out.println("*********************************");
		    System.out.println("APRES REMODIFICATION : ");
		    System.out.println("\tNOM : " + res.getString("prof_nom") + "\tPrénom : " 
		    		 + res.getString("prof_prenom"));
		    
		    res.close();
		    state.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
