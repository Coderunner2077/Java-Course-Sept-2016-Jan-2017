/*							
 * 										Fouiller dans sa base de données
 * 
 * Je continue l'initiation au pays du JDBC en abordant la manière d'interroger ma BDD. Eh oui, une base
 * de donnéeS n'est utile que si l'on peut consulter, ajouter, modifier et supprimer les données qu'elle 
 * contient. Pour y parvenir, il était impératif de se connecter au préalble. Maintenant que c'est chose
 * faite, voyons comment fouiner dans une BDD.
 * 
 * I./ Le couple Statement - ResultSet
 * 
 * 1.) Passer des requêtes SQL à travers Java
 * Ce sont les deux objets qui permettent de récupérer des données de la BDD et de travailler avec celles-ci.
 * 
 * Réf Connect->main
 * 
 * Les métadatas (ou plus communément, les métadonnées), constituent en réalité un ensemble de données
 * servant à décrire une structure. Dans mon cas, elles permettent de connaître le nom des tables, des 
 * champs, leur type...
 * 
 * J'ai simplement executé une requête SQL et récupéré les lignes retournées. Mais détaillons un peu
 * plus ce qu'il s'est passé. Déjà, j'ai spécifié l'URL complète pour la connexion, pour préciser à quelle
 * BDD se connecter.
 * 
 * Ce dernier point mis à part, les choses se sont déroulées en quatre étapes distinctes :
 * 
 * 		-	Création d'un objet Statement
 * 		-	Exécution de la réquête SQL
 * 		-	Récupération et affichage des données via l'objet ResultSet
 * 		-	Fermeture des objets utilisés (bien que non obligatoire, c'est recommandé)
 * 
 * Les résultats d'une requête sont stockées dans l'objet ResultSet, grâce auquel on peut parcourir
 * les lignes de résultats et les afficher.
 * 
 * L'objet ResultSetMetaData permet de récupérer les métadonnées de la requête, i.e. ses informations 
 * globales. J'ai ensuite utilisé cet objet afin de récupérer le nombre de colonnes renvoyée par ma requête
 * SQL ainsi que leur nom. Cet objet de métadonnées permet de récupérer des info très utiles telles que :
 * 
 * 		-	le nombre de colonnes d'un résultat
 * 		-	le nom des colonnes d'un résultat
 * 		-	le type de données stockées dans chaque colonne
 * 		-	le nom de la table à laquelle appartient la colonne (dans le cas d'une jointure de tables);
 * 
 * Note : il existe aussi un objet DataBaseMetaData qui fournit des info sur la base de données.
 * 
 * Attention : contrairement aux indices des tableaux, les indices de colonnes SQL commencent à 1.
 * 
 * J'utilise une première boucle me permettant de parcourir chaque ligne via la boucle for tant que 
 * l'objet ResultSet retourne des lignes de résultats. La méthode next() permet de positionner l'objet sur
 * la ligne suivante de la liste des résultats. Au 1er tour de boucle, cette méthode place l'objet sur la
 * 1re ligne. Si je n'ai pas positionné l'objet ResultSet et que je tente de lire des données, une 
 * exception est levée. 
 * 
 * Je suis parti du principe que les données de mes colonnes m'étaient inconnues, mais étant donnée que je les 
 * connais, le code suivant aurait tout aussi bien fonctionné :
 * 
 * while(result.next()){
 * 		System.out.print("\t"+ result.getInt("cls_id")+"\t |");
 * 		System.out.print("\t" + result.getString("cls_nom") + "\t |");
 * 		System.out.println("\n\t---------------------------------------");
 * }
 * 
 * Je connais désormais le nom des colonnes retournées par la requête SQL. Je connais également leur type,
 * il me suffit donc d'invoquer la méthode adéquate de l'objet ResultSet en utilisant le nom de la 
 * colonne à récupérer (au lieu d'un indice). En revanche, si je tente de récupérer le contenu de la
 * colonne cls_nom avec la méthode getInt("cls_nom"), j'aurai une exception !
 * 
 * Il existe une méthode getXXX() par type primitif ainsi que quelques autres correspondant au type 
 * SQL :
 * 
 * 	-	getArray(int columnIndex);
 * 	-	getAscii(int columnIndex);
 * 	-	getBigDecimal(int columnIndex);
 * 	-	getBinary(int columnIndex);
 * 	-	getBlob(int columnIndex);
 * 	-	getBoolean(int columnIndex);
 * 	-	getBytes(int columnIndex);
 * 	-	getCharacter(int columnIndex);
 * 	-	getDate(int columnIndex);
 * 	-	getDouble(int columnIndex);
 * 	-	getFloat(int columnIndex);
 * 	-	getInt(int columnIndex);
 * 	-	getLong(int columnIndex);
 * 	-	getObject(int columnIndex);
 * 	-	getString(int columnIndex);
 * 
 * Pour finir, je n'ai plus qu'à fermer mes objets à l'aide des instructions result.close() et
 * state.close().
 * 
 * Place aux exercices ==> Réf main
 * 
 * 2.) Statement : approfondissement
 * 
 * Pour l'instant, j'ai obtenu l'objet Statement sans aucun paramètres...
 * Voyons maintenant comment spécifier des paramètres pour la création de l'objet Statement. Ces paramètres
 * permettent différentes actions lors du parcours des résultats via l'objet ResultSet.
 * 
 * Le 1er paramètre est utile pour la lecture du jeu d'enregistrements : 
 * 
 * 	-	TYPE_FORWARD_ONLY : le résultat n'est consultable qu'en avançant dans les données renvoyées, il
 * 							est donc impossible de revenir en arrière lors de la lecture.
 * 	-	TYPE_SCROLL_SENSITIVE : le parcours peut se faire en avant ou vers l'arrière et le curseur peut se
 * 							se positionner n'importe où, et si des changements surviennent dans la base
 * 							pendant la lecture, ils seront directement visibles lors du parcours des résultats
 * 	-	TYPE_SCROLL_INSENSITIVE : à la différence du précédent, les changements ne seront pas visibles.
 * 
 * Le second concerne la possibilité de mis à jour du jeu d'enregistrements :
 * 
 * 	-	CONCUR_READ_ONLY : les données sont consultables en lecture seule, c'est-à-dire que l'on ne peut
 * 						  modifier des valeurs pour mettre la base à jour
 * 	-	CONCUR_UPDATABLE : les données sont modifiables; lors d'une modification, la base est mise à jour.
 * 
 * Note : Par défaut, les ResultSet issus d'un Statement sont de type TYPE_FORWARD_ONLY pour le parcours, 
 * et CONCUR_READ_ONLY pour les actions réalisables. 
 * 
 * Ces paramètres sont des variables statiques de la classe ResultSet. Voici comment créer un Statement 
 * permettant à l'objet ResultSet de pouvoir être lu d'avant en arrière avec possibilité de modification :
 * 
 * Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
 * 
 * Il existe aussi un autre type de Statement...
 * 
 * II./ Les requêtes préparées
 * 
 * 1.) PreparedStatement : avant-goût...
 * De tels objets sont créés de la même façon que des Statement classiques, sauf qu'au lieu de cette 
 * insctruciton :
 * Statement state = conn.createStatement();
 * 
 * ... je dois écrire ceci :
 * 
 * PreparedStatement stm = conn.prepareStatement("SELECT * FROM classe");
 * 
 * Jusqu'ici, rien de spécial. Cependant, une différence est déjà effective à ce state : la requête SQL est
 * précompilée. Cela a pour effet de réduire le temps d'exécution dans le moteur SQL de la BDD. C'est normal,
 * étant donné qu'il n'aura pas à compiler la requête. En règle générale, on utilise ce genre d'ojbet pour
 * des requêtes contenant beaucoup de paramètres ou pouvant être exécutées plusieurs fois. Il existe une 
 * autre différence de taille entre les objets Statement et PreparedStatement : dans le second, on peut
 * utiliser des paramètres à trous !
 * 
 * En fait, on peut insérer un caractère spécial dans mes requêtes et remplacer ce caractère grâce à des
 * méthodes de l'objet PreparedStatement en spécifiant sa place et sa valeur (son type étant défini par 
 * la méthode utilisée).
 * 
 * Réf Prepare
 * 
 * Mais quelles méthodes d'affectation existe-t-il ?
 * ==> C'est simple ! Rappelons-nous de la liste des méthode de l'objet ResulSet récupérant des données.
 * Ici, elle identique, sauf que l'on trouve setXXX() à la place des getXXX().
 * Tout comme son homologue sans trou, cet objet peut prendre les mêmes types de paramètres pour la lecture
 * et pour la modification des données lues :
 * PreparedStatement prepare = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
 * ResultSet.CONCUR_READ_ONLY);
 * 
 * Il existe également une méthode retournant un objet ResultSetMetaData : getMetaData().
 * 
 * Pour en terminer avec les méthodes de l'objet PreparedStatement que je presente ici (il en existe 
 * d'autres), prepare.clearParametres() permet de réinitialiser la requête préparée afin de retirer toutes
 * les valeurs renseignées. 
 * 
 * 2.) ResultSet : le retour
 * 
 * Je vais maintenant apprendre à me promener dans mes objets ResultSet. En fait, l'objet ResultSet offre
 * beaucoup de méthodes permettant d'explorer les résultats, à condition que j'aie bien préparé l'objet
 * Statement.
 * 
 * J'ai la possibilité de :
 * 
 * 	-	me positionner avant la 1re ligne de mon résultat ==> res.beforeFirst();
 *  -	savoir si je me trouve avant la 1re ligne ==> res.isBeforeFirst();
 *  -	me placer sur la 1re ligne de mon résultat ==> res.first();
 *  -	savoir si je me trouve sur la 1re ligne ==> res.isFirst();
 *  -	me retrouver sur la dernière ligne ==> res.last();
 *  -	savoir si je me trouve sur la dernière ligne ==> res.isLast();
 *  -	me positionnner après la dernière ligne du résultat ==> res.afterLast();
 *  -	savoir si je me trouve après la dernière ligne du résultat ==> res.isAfterLast();
 *  -	aller de la 1re ligne à la dernière ligne ==> res.next();
 *  -	aller de la dernière ligne à la première ==> res.previous();
 *  -	me positionner sur une ligne précise de mon résultat ==> res.absolute(5)
 *  -	me positionner sur une ligne par rapport à ma position actuelle ==> res.relative(-3);
 *  
 *  Réf RetourSet
 *  
 * Il est important de noter l'endroit où je me situe dans la parcours de la requête !
 * 
 * Attention, il existe des emplacements particuliers. Par exemple, si je ne suis pas encore positionné
 * sur le 1er élément et que je procède à un result.relative(1), je me retrouverai sur le 1er élément. De
 * même, un result.absolute(0) correspond à un result.beforeFirst().
 * 
 * Cela signifie que si je souhaite placer le curseur sur la 1re ligne, je dois utiliser absolute(1) quelque
 * soit l'endroit où je me situe ! En revanche, cela nécessite que le ResultSet soit de type 
 * TYPE_SCROLL_SENSITIVE ou TYPE_SCROLL_INSENSITIVE, sans quoi j'aurai une exception.
 * 
 * III./ Modifier des données
 * 
 * Pendant la lecture, je peux utiliser des méthodes qui ressemblent beaucoup à celles que j'ai utilisées
 * lors du parcours d'un résultat (getString(), getAschii(), getBytes()...). 
 * Ici, je dois remplacer getXXX() par updateXXX(). Ces méthodes de mis à jour des données prennent 2
 * paramètres : 
 * 		-	le nom de la colonne(String)
 * 		-	la valeur à attribuer à la place de la valeur existante (cela dépend de la méthode utilisée).
 * 
 * Exemple de ces méthodes de l'objet ResultSet:
 * 	-	updateFloat(String nomCln, float value) ==> prend un float en 2nd paramètre
 * 	-	updateString(String nomCln, String value) ==> prend une chaîne de caractères en paramètre.
 * 	-	etc.
 * 
 * Changer la valeur d'un champ est donc très facile. Cependant, il faut, en plus de changer les valeurs,
 * valider ces changements pour qu'ils soient effectifs : cela se fait par la méthode updateRow(). De la
 * même manière, je peux annuler les changements grâce à la méthode cancelRowUpdate(). Et si je veux
 * annuler des modifications, je dois le faire avant la méthode de validation, sinon l'annulation sera
 * ignorée.
 * 
 * Réf Modif
 * 
 * En quelques clics, les données ont été modifiées dans la base de données !
 * 
 * Je vais maintenant voir comment exécuter les autres types de requêtes avec Java.
 * 
 * IV./ Statement, toujours plus fort
 * 
 * Réf State
 * 
 * Ici, j'ai utilisé un PreparedStatement. Mais j'aurais tout aussi bien pu utiliser un simple
 * Statement et invoquer la méthode executeUpdate(String query). 
 * Pour la mise à jour, la création ou la suppression des données, je peux donc utiliser la méthode
 * executeUpdate(String query). En fait, celles-ci retourne un booléan (non, plutôt un int) indiquant si
 * le traitement a réussi ou échoué.
 * Voici quelques exemples :
 * state.executeUpdate("INSERT INTO professeur (prof_nom, prof_prenom) VALUES ('SALMON', 'Dylan')");
 * stata.executeUpdate("DELETE FROM professeur WHERE prof_nom = 'MAMOU');
 * 
 * Note : Contrairement à ce que je pensais, pour effectuer de telles mises à jour avec un statement (pré -
 * paré ou pas d'ailleurs), je n'ai pas besoin de le paramétrer à CONCUR_UPDATABLE. Les arguments de type
 * CONCUR_UPDATABLE ont effet uniquement sur le ResultSet auquel le statement fait passer une requête.
 * 
 * V./ Gérer les transactions manuellement
 * 
 * Certains moteurs SQL comme PostgresSQL proposent de gérer les requêtes SQL grâce à ce que l'on appelle
 * des transactions.
 * 
 * Par où commencer ? Lorsque j'insère, modifie ou supprime des données dans PostgreSQL, il se produit un
 * événement automatique : la validation des modifications par le SQL. C'est aussi simple que ça... 
 * Lorsque j'exécute une requête de type INSERT, CREATE, UPDATE ou DELETE, le type de cette requête
 * modifie les données présentes dans la base. Une fois qu'elle est exécutée, le moteur SQL valide
 * directement ces modifications !
 * 
 * Cependant, je peux avoir la main sur ce point. En effet, j'ai la possibilité d'avoir le contrôle sur
 * mes données afin de maîtriser l'intégrité de mes données. Imaginons que je dois exécuter deux requêtes,
 * une modification et une insertion, et que je pars du principe que l'insertion dépend de la mise à jour...
 * Comment ferais-je si de mauvaises données étaient mises à jour ? L'insertion qui en découle serait
 * mauvaise. Cela, bien sur, si le moteur SQL valide automatiquement les requêtes exécutées.
 * 
 * Pour gérer manuellement les transactions, on spécifie au moteur SQL de ne pas valider automatiquement
 * les requêtes SQL grâce à une méthode (qui ne concernera toutefois pas l'objet Statement, mais l'objet
 * Connection) prenant un booléen en paramètre : setAutoCommit(boolean b)
 * Réf Transact.
 * 
 * Lorsque je souhaite que mes requêtes soient prises en compte, je dois les valider en utilisant la
 * méthode conn.commit();
 * 
 * Attention : en mode setAutoCommit(false), si je ne valide pas mes requêtes, elles ne seront pas prises
 * en compte.
 * 
 * Je peux revenir à tout moment au mode de validation automatique grâce à setAutoCommit(true).
 * 
 * Réf Transact
 * 
 * Je vois que malgré sa présence, la requête de mise à jour est inopérante. Je peux voir les modifications
 * lors de l'exécution du script, mais étant donné que je ne les ai pas validées, elles sont annulées à la
 * fin du code. Pour que la mise à jour soit effective, il faudrait effectuer un conn.commit() avant la 
 * fin du script.
 * Donc, en utilisant les transactions manuelles, toute instruction non validée par la méthode commit()
 * de l'objet Connection est annulée.
 * 
 * 
 * 
 * 
 * 
 *  
 *  
 * 
 * 
 * 
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Connect {

	public static void main(String[] args) {
		try{
			
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver O.K.");

			String url = "jdbc:postgresql://localhost:5432/Ecole";
			String user = "postgres";
	      	String passwd = "svoloche";

	      	Connection conn = DriverManager.getConnection(url, user, passwd);
	      	System.out.println("Connexion effective !");  
	      	
	      	//Création d'un objet Statement
	      	Statement state = conn.createStatement();
	      	
	      	/*
	      	String query = "SELECT prof_nom, prof_prenom, mat_nom FROM professeur ";
	      	query += "INNER JOIN j_mat_prof ON prof_id = jmp_prof_k ";
	      	query += "INNER JOIN matiere ON jmp_mat_k = mat_id ";
	      	query += "ORDER BY prof_nom";
	      	*/
	      	
	      	String query = "SELECT cls_nom, prof_nom, prof_prenom, mat_nom FROM classe ";
	      	query += "INNER JOIN j_cls_jmp ON cls_id = jcm_cls_k AND cls_id IN(1,7) ";
	      	query += "INNER JOIN j_mat_prof ON jcm_jmp_k = jmp_id ";
	      	query += "INNER JOIN professeur ON jmp_prof_k = prof_id ";
	      	query += "INNER JOIN matiere ON jmp_mat_k = mat_id ";
	      	query += "ORDER BY classe DESC, prof_nom";
	      	
	      	
	      	//L'objet ResultSet contient le résultat de la requête SQL :
	      	ResultSet result = state.executeQuery(query);
	      	
	      	//On récupère les MetaData
	      	ResultSetMetaData resultMeta = result.getMetaData();
	      	/*
	      	System.out.println("\n\t*****************************");
	      	//On affiche le nom des colonnes :
	      	for(int i = 1; i <= resultMeta.getColumnCount(); ++i)
	      		System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t*");
	      	System.out.println("\n\t*****************************");
	      	
	      	while(result.next()){
	      		for(int i = 1; i <= resultMeta.getColumnCount(); ++i)
	      			System.out.print("\t" + result.getObject(i).toString() + "\t|");
	      		System.out.println("\n\t-------------------------------");
	      	}
	      	*/
	      	
	      	/*
	      	while(result.next()){
	      		System.out.print("\t"+result.getInt("cls_id") + "\t |");
	      		System.out.print("\t" + result.getString("cls_nom") + "\t |");
	      		System.out.println("\n\t----------------------------------------");
	      	}
	      	*/
	      	/*
	      	System.out.println("Il y a " + resultMeta.getColumnCount() + " colonnes dans cette table");
	      	//On affiche le nom des colonnes :
	      	for(int i = 1; i <= resultMeta.getColumnCount(); ++i)
	      		System.out.print("\t*" + resultMeta.getColumnName(i).toUpperCase() + "\n");
	      	System.out.println("Voici les nom et prénoms : \n");
	      	while(result.next()){
	      		System.out.print("\t" + result.getString("prof_nom") + "\t|");
	      		System.out.print("\t" + result.getString("prof_prenom") + "\t|");
	      		System.out.println("\n--------------------------------------");
	      	}
	      	*/
	      	System.out.println("Il y a " + resultMeta.getColumnCount() + " colonnes dans cette table");
	      	//On affiche le nom des colonnes :
	      	for(int i = 1; i <= resultMeta.getColumnCount(); ++i)
	      		System.out.print("\t*" + resultMeta.getColumnName(i).toUpperCase() + "\n");
	      	System.out.println("Voici les nom et prénoms : \n");
	      	String nom = "", classe = "";
	      	while(result.next()){
	      		if(!classe.equals(result.getString("cls_nom"))){
	      			classe = result.getString("cls_nom");
	      			System.out.println("\nClasse de " + classe + " :");
	      		}
	      		if(!nom.equals(result.getString("prof_nom"))){
		      		System.out.print("\n\t* " + result.getString("prof_nom"));
			      	System.out.print(" " + result.getString("prof_prenom") + " enseigne : ");
			      	System.out.print("\n\t\t\t\t- " + result.getString("mat_nom"));
			      	nom = result.getString("prof_nom");
		      	}
		      	else
		      		System.out.print("\n\t\t\t\t- " + result.getString("mat_nom")); 			
	      		
	      	}
	      	result.close();
	      	state.close();
	         
	    } catch (Exception e) {
	      e.printStackTrace();
	    }      

	}

}
