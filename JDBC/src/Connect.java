/*							
 * 										Fouiller dans sa base de donn�es
 * 
 * Je continue l'initiation au pays du JDBC en abordant la mani�re d'interroger ma BDD. Eh oui, une base
 * de donn�eS n'est utile que si l'on peut consulter, ajouter, modifier et supprimer les donn�es qu'elle 
 * contient. Pour y parvenir, il �tait imp�ratif de se connecter au pr�alble. Maintenant que c'est chose
 * faite, voyons comment fouiner dans une BDD.
 * 
 * I./ Le couple Statement - ResultSet
 * 
 * 1.) Passer des requ�tes SQL � travers Java
 * Ce sont les deux objets qui permettent de r�cup�rer des donn�es de la BDD et de travailler avec celles-ci.
 * 
 * R�f Connect->main
 * 
 * Les m�tadatas (ou plus commun�ment, les m�tadonn�es), constituent en r�alit� un ensemble de donn�es
 * servant � d�crire une structure. Dans mon cas, elles permettent de conna�tre le nom des tables, des 
 * champs, leur type...
 * 
 * J'ai simplement execut� une requ�te SQL et r�cup�r� les lignes retourn�es. Mais d�taillons un peu
 * plus ce qu'il s'est pass�. D�j�, j'ai sp�cifi� l'URL compl�te pour la connexion, pour pr�ciser � quelle
 * BDD se connecter.
 * 
 * Ce dernier point mis � part, les choses se sont d�roul�es en quatre �tapes distinctes :
 * 
 * 		-	Cr�ation d'un objet Statement
 * 		-	Ex�cution de la r�qu�te SQL
 * 		-	R�cup�ration et affichage des donn�es via l'objet ResultSet
 * 		-	Fermeture des objets utilis�s (bien que non obligatoire, c'est recommand�)
 * 
 * Les r�sultats d'une requ�te sont stock�es dans l'objet ResultSet, gr�ce auquel on peut parcourir
 * les lignes de r�sultats et les afficher.
 * 
 * L'objet ResultSetMetaData permet de r�cup�rer les m�tadonn�es de la requ�te, i.e. ses informations 
 * globales. J'ai ensuite utilis� cet objet afin de r�cup�rer le nombre de colonnes renvoy�e par ma requ�te
 * SQL ainsi que leur nom. Cet objet de m�tadonn�es permet de r�cup�rer des info tr�s utiles telles que :
 * 
 * 		-	le nombre de colonnes d'un r�sultat
 * 		-	le nom des colonnes d'un r�sultat
 * 		-	le type de donn�es stock�es dans chaque colonne
 * 		-	le nom de la table � laquelle appartient la colonne (dans le cas d'une jointure de tables);
 * 
 * Note : il existe aussi un objet DataBaseMetaData qui fournit des info sur la base de donn�es.
 * 
 * Attention : contrairement aux indices des tableaux, les indices de colonnes SQL commencent � 1.
 * 
 * J'utilise une premi�re boucle me permettant de parcourir chaque ligne via la boucle for tant que 
 * l'objet ResultSet retourne des lignes de r�sultats. La m�thode next() permet de positionner l'objet sur
 * la ligne suivante de la liste des r�sultats. Au 1er tour de boucle, cette m�thode place l'objet sur la
 * 1re ligne. Si je n'ai pas positionn� l'objet ResultSet et que je tente de lire des donn�es, une 
 * exception est lev�e. 
 * 
 * Je suis parti du principe que les donn�es de mes colonnes m'�taient inconnues, mais �tant donn�e que je les 
 * connais, le code suivant aurait tout aussi bien fonctionn� :
 * 
 * while(result.next()){
 * 		System.out.print("\t"+ result.getInt("cls_id")+"\t |");
 * 		System.out.print("\t" + result.getString("cls_nom") + "\t |");
 * 		System.out.println("\n\t---------------------------------------");
 * }
 * 
 * Je connais d�sormais le nom des colonnes retourn�es par la requ�te SQL. Je connais �galement leur type,
 * il me suffit donc d'invoquer la m�thode ad�quate de l'objet ResultSet en utilisant le nom de la 
 * colonne � r�cup�rer (au lieu d'un indice). En revanche, si je tente de r�cup�rer le contenu de la
 * colonne cls_nom avec la m�thode getInt("cls_nom"), j'aurai une exception !
 * 
 * Il existe une m�thode getXXX() par type primitif ainsi que quelques autres correspondant au type 
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
 * Pour finir, je n'ai plus qu'� fermer mes objets � l'aide des instructions result.close() et
 * state.close().
 * 
 * Place aux exercices ==> R�f main
 * 
 * 2.) Statement : approfondissement
 * 
 * Pour l'instant, j'ai obtenu l'objet Statement sans aucun param�tres...
 * Voyons maintenant comment sp�cifier des param�tres pour la cr�ation de l'objet Statement. Ces param�tres
 * permettent diff�rentes actions lors du parcours des r�sultats via l'objet ResultSet.
 * 
 * Le 1er param�tre est utile pour la lecture du jeu d'enregistrements : 
 * 
 * 	-	TYPE_FORWARD_ONLY : le r�sultat n'est consultable qu'en avan�ant dans les donn�es renvoy�es, il
 * 							est donc impossible de revenir en arri�re lors de la lecture.
 * 	-	TYPE_SCROLL_SENSITIVE : le parcours peut se faire en avant ou vers l'arri�re et le curseur peut se
 * 							se positionner n'importe o�, et si des changements surviennent dans la base
 * 							pendant la lecture, ils seront directement visibles lors du parcours des r�sultats
 * 	-	TYPE_SCROLL_INSENSITIVE : � la diff�rence du pr�c�dent, les changements ne seront pas visibles.
 * 
 * Le second concerne la possibilit� de mis � jour du jeu d'enregistrements :
 * 
 * 	-	CONCUR_READ_ONLY : les donn�es sont consultables en lecture seule, c'est-�-dire que l'on ne peut
 * 						  modifier des valeurs pour mettre la base � jour
 * 	-	CONCUR_UPDATABLE : les donn�es sont modifiables; lors d'une modification, la base est mise � jour.
 * 
 * Note : Par d�faut, les ResultSet issus d'un Statement sont de type TYPE_FORWARD_ONLY pour le parcours, 
 * et CONCUR_READ_ONLY pour les actions r�alisables. 
 * 
 * Ces param�tres sont des variables statiques de la classe ResultSet. Voici comment cr�er un Statement 
 * permettant � l'objet ResultSet de pouvoir �tre lu d'avant en arri�re avec possibilit� de modification :
 * 
 * Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
 * 
 * Il existe aussi un autre type de Statement...
 * 
 * II./ Les requ�tes pr�par�es
 * 
 * 1.) PreparedStatement : avant-go�t...
 * De tels objets sont cr��s de la m�me fa�on que des Statement classiques, sauf qu'au lieu de cette 
 * insctruciton :
 * Statement state = conn.createStatement();
 * 
 * ... je dois �crire ceci :
 * 
 * PreparedStatement stm = conn.prepareStatement("SELECT * FROM classe");
 * 
 * Jusqu'ici, rien de sp�cial. Cependant, une diff�rence est d�j� effective � ce state : la requ�te SQL est
 * pr�compil�e. Cela a pour effet de r�duire le temps d'ex�cution dans le moteur SQL de la BDD. C'est normal,
 * �tant donn� qu'il n'aura pas � compiler la requ�te. En r�gle g�n�rale, on utilise ce genre d'ojbet pour
 * des requ�tes contenant beaucoup de param�tres ou pouvant �tre ex�cut�es plusieurs fois. Il existe une 
 * autre diff�rence de taille entre les objets Statement et PreparedStatement : dans le second, on peut
 * utiliser des param�tres � trous !
 * 
 * En fait, on peut ins�rer un caract�re sp�cial dans mes requ�tes et remplacer ce caract�re gr�ce � des
 * m�thodes de l'objet PreparedStatement en sp�cifiant sa place et sa valeur (son type �tant d�fini par 
 * la m�thode utilis�e).
 * 
 * R�f Prepare
 * 
 * Mais quelles m�thodes d'affectation existe-t-il ?
 * ==> C'est simple ! Rappelons-nous de la liste des m�thode de l'objet ResulSet r�cup�rant des donn�es.
 * Ici, elle identique, sauf que l'on trouve setXXX() � la place des getXXX().
 * Tout comme son homologue sans trou, cet objet peut prendre les m�mes types de param�tres pour la lecture
 * et pour la modification des donn�es lues :
 * PreparedStatement prepare = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
 * ResultSet.CONCUR_READ_ONLY);
 * 
 * Il existe �galement une m�thode retournant un objet ResultSetMetaData : getMetaData().
 * 
 * Pour en terminer avec les m�thodes de l'objet PreparedStatement que je presente ici (il en existe 
 * d'autres), prepare.clearParametres() permet de r�initialiser la requ�te pr�par�e afin de retirer toutes
 * les valeurs renseign�es. 
 * 
 * 2.) ResultSet : le retour
 * 
 * Je vais maintenant apprendre � me promener dans mes objets ResultSet. En fait, l'objet ResultSet offre
 * beaucoup de m�thodes permettant d'explorer les r�sultats, � condition que j'aie bien pr�par� l'objet
 * Statement.
 * 
 * J'ai la possibilit� de :
 * 
 * 	-	me positionner avant la 1re ligne de mon r�sultat ==> res.beforeFirst();
 *  -	savoir si je me trouve avant la 1re ligne ==> res.isBeforeFirst();
 *  -	me placer sur la 1re ligne de mon r�sultat ==> res.first();
 *  -	savoir si je me trouve sur la 1re ligne ==> res.isFirst();
 *  -	me retrouver sur la derni�re ligne ==> res.last();
 *  -	savoir si je me trouve sur la derni�re ligne ==> res.isLast();
 *  -	me positionnner apr�s la derni�re ligne du r�sultat ==> res.afterLast();
 *  -	savoir si je me trouve apr�s la derni�re ligne du r�sultat ==> res.isAfterLast();
 *  -	aller de la 1re ligne � la derni�re ligne ==> res.next();
 *  -	aller de la derni�re ligne � la premi�re ==> res.previous();
 *  -	me positionner sur une ligne pr�cise de mon r�sultat ==> res.absolute(5)
 *  -	me positionner sur une ligne par rapport � ma position actuelle ==> res.relative(-3);
 *  
 *  R�f RetourSet
 *  
 * Il est important de noter l'endroit o� je me situe dans la parcours de la requ�te !
 * 
 * Attention, il existe des emplacements particuliers. Par exemple, si je ne suis pas encore positionn�
 * sur le 1er �l�ment et que je proc�de � un result.relative(1), je me retrouverai sur le 1er �l�ment. De
 * m�me, un result.absolute(0) correspond � un result.beforeFirst().
 * 
 * Cela signifie que si je souhaite placer le curseur sur la 1re ligne, je dois utiliser absolute(1) quelque
 * soit l'endroit o� je me situe ! En revanche, cela n�cessite que le ResultSet soit de type 
 * TYPE_SCROLL_SENSITIVE ou TYPE_SCROLL_INSENSITIVE, sans quoi j'aurai une exception.
 * 
 * III./ Modifier des donn�es
 * 
 * Pendant la lecture, je peux utiliser des m�thodes qui ressemblent beaucoup � celles que j'ai utilis�es
 * lors du parcours d'un r�sultat (getString(), getAschii(), getBytes()...). 
 * Ici, je dois remplacer getXXX() par updateXXX(). Ces m�thodes de mis � jour des donn�es prennent 2
 * param�tres : 
 * 		-	le nom de la colonne(String)
 * 		-	la valeur � attribuer � la place de la valeur existante (cela d�pend de la m�thode utilis�e).
 * 
 * Exemple de ces m�thodes de l'objet ResultSet:
 * 	-	updateFloat(String nomCln, float value) ==> prend un float en 2nd param�tre
 * 	-	updateString(String nomCln, String value) ==> prend une cha�ne de caract�res en param�tre.
 * 	-	etc.
 * 
 * Changer la valeur d'un champ est donc tr�s facile. Cependant, il faut, en plus de changer les valeurs,
 * valider ces changements pour qu'ils soient effectifs : cela se fait par la m�thode updateRow(). De la
 * m�me mani�re, je peux annuler les changements gr�ce � la m�thode cancelRowUpdate(). Et si je veux
 * annuler des modifications, je dois le faire avant la m�thode de validation, sinon l'annulation sera
 * ignor�e.
 * 
 * R�f Modif
 * 
 * En quelques clics, les donn�es ont �t� modifi�es dans la base de donn�es !
 * 
 * Je vais maintenant voir comment ex�cuter les autres types de requ�tes avec Java.
 * 
 * IV./ Statement, toujours plus fort
 * 
 * R�f State
 * 
 * Ici, j'ai utilis� un PreparedStatement. Mais j'aurais tout aussi bien pu utiliser un simple
 * Statement et invoquer la m�thode executeUpdate(String query). 
 * Pour la mise � jour, la cr�ation ou la suppression des donn�es, je peux donc utiliser la m�thode
 * executeUpdate(String query). En fait, celles-ci retourne un bool�an (non, plut�t un int) indiquant si
 * le traitement a r�ussi ou �chou�.
 * Voici quelques exemples :
 * state.executeUpdate("INSERT INTO professeur (prof_nom, prof_prenom) VALUES ('SALMON', 'Dylan')");
 * stata.executeUpdate("DELETE FROM professeur WHERE prof_nom = 'MAMOU');
 * 
 * Note : Contrairement � ce que je pensais, pour effectuer de telles mises � jour avec un statement (pr� -
 * par� ou pas d'ailleurs), je n'ai pas besoin de le param�trer � CONCUR_UPDATABLE. Les arguments de type
 * CONCUR_UPDATABLE ont effet uniquement sur le ResultSet auquel le statement fait passer une requ�te.
 * 
 * V./ G�rer les transactions manuellement
 * 
 * Certains moteurs SQL comme PostgresSQL proposent de g�rer les requ�tes SQL gr�ce � ce que l'on appelle
 * des transactions.
 * 
 * Par o� commencer ? Lorsque j'ins�re, modifie ou supprime des donn�es dans PostgreSQL, il se produit un
 * �v�nement automatique : la validation des modifications par le SQL. C'est aussi simple que �a... 
 * Lorsque j'ex�cute une requ�te de type INSERT, CREATE, UPDATE ou DELETE, le type de cette requ�te
 * modifie les donn�es pr�sentes dans la base. Une fois qu'elle est ex�cut�e, le moteur SQL valide
 * directement ces modifications !
 * 
 * Cependant, je peux avoir la main sur ce point. En effet, j'ai la possibilit� d'avoir le contr�le sur
 * mes donn�es afin de ma�triser l'int�grit� de mes donn�es. Imaginons que je dois ex�cuter deux requ�tes,
 * une modification et une insertion, et que je pars du principe que l'insertion d�pend de la mise � jour...
 * Comment ferais-je si de mauvaises donn�es �taient mises � jour ? L'insertion qui en d�coule serait
 * mauvaise. Cela, bien sur, si le moteur SQL valide automatiquement les requ�tes ex�cut�es.
 * 
 * Pour g�rer manuellement les transactions, on sp�cifie au moteur SQL de ne pas valider automatiquement
 * les requ�tes SQL gr�ce � une m�thode (qui ne concernera toutefois pas l'objet Statement, mais l'objet
 * Connection) prenant un bool�en en param�tre : setAutoCommit(boolean b)
 * R�f Transact.
 * 
 * Lorsque je souhaite que mes requ�tes soient prises en compte, je dois les valider en utilisant la
 * m�thode conn.commit();
 * 
 * Attention : en mode setAutoCommit(false), si je ne valide pas mes requ�tes, elles ne seront pas prises
 * en compte.
 * 
 * Je peux revenir � tout moment au mode de validation automatique gr�ce � setAutoCommit(true).
 * 
 * R�f Transact
 * 
 * Je vois que malgr� sa pr�sence, la requ�te de mise � jour est inop�rante. Je peux voir les modifications
 * lors de l'ex�cution du script, mais �tant donn� que je ne les ai pas valid�es, elles sont annul�es � la
 * fin du code. Pour que la mise � jour soit effective, il faudrait effectuer un conn.commit() avant la 
 * fin du script.
 * Donc, en utilisant les transactions manuelles, toute instruction non valid�e par la m�thode commit()
 * de l'objet Connection est annul�e.
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
	      	
	      	//Cr�ation d'un objet Statement
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
	      	
	      	
	      	//L'objet ResultSet contient le r�sultat de la requ�te SQL :
	      	ResultSet result = state.executeQuery(query);
	      	
	      	//On r�cup�re les MetaData
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
	      	System.out.println("Voici les nom et pr�noms : \n");
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
	      	System.out.println("Voici les nom et pr�noms : \n");
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
