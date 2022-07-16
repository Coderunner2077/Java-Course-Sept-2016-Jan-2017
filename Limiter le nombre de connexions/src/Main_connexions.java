import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 										Limiter le nombre de connexions
 * 
 * Je sais d�sormais comment me connecter � une BDD depuis Java. Il s'agira ici d'une approche un peu plus
 * orient�e objet. Il est vrai qu'�tablir sans arr�t la connexion � la BDD commence � �tre fastidieux. On
 * peut y rem�dier avec ce que l'on appelle le pattern singleton.
 * 
 * I./ Pourquoi ne se connecter qu'une seule fois?
 * 
 * Pourquoi vouloir avoir une seule instance de l'objet Connection ?
 * ==> Parce que cela ne sert pas � grande chose de r�initialiser la connexion � chaque fois. Rappelons-nous
 * que la connexion sert � �tablir un pont entre ma base et mon application. Mon application et mon BDD
 * peuvent discuter.
 * Comment faire pour garantir qu'une seule instance de Connection existe dans l'application ?
 * ==> C'est ici que le pattern singleton entre en jeu ! Ce pattern est peut-�tre l'un des plus simples �
 * comprendre m�me. Son principe de base consiste � interdire l'instanciation d'une classe gr�ce � un 
 * constructeur d�clar� private. 
 * 
 * II./ Le pattern singleton
 * 
 * Je veux qu'il soit impossible de cr�er plus d'un objet de connexion. Voici une classe qui permet
 * de s'assurer que c'est le cas : R�f MyConnection
 * Je viens de cr�er une classe avec un constructeur priv� : du coup, impossible d'instancier cet objet et
 * d'acc�der � ses attributs, puisqu'ils sont d�clar�s private ! Mon objet Connection est instanci� dans
 * le constructeur priv� et la seule m�thode accessible depuis l'ext�rieur est getInstance(). C'est donc cette
 * m�thode qui a pour r�le de cr�er une connexion si elle n'existe pas, et seulement dans ce cas. Pour en
 * avoir le coeur net, on va modifier un peu la m�thode getInstance() afin qu'elle signale l'existance
 * d'une connexion le cas �ch�ant. Cela montre bien quand la connexion est r�ellement cr��e.
 * R�f main
 * 
 * Ainsi, la connexion � la BDD ne se fait qu'une seule et unique fois pour mon application.
 * La classe MyConnection peut �tre simplifi�e. R�f MyConnect 
 * 
 * Attention toutefois, je dois rajouter la d�claration static � mes param�tres de connexion aussi cette 
 * fois-ci (car ils sont utilis�s dans une m�thode static). J'avais commenc� par ins�r� un constructeur
 * priv� car je devais savor que cela existait. Mais bon, on peut tr�s bien s'en passer.
 * 
 * Par contre, dans une application multithreads, pour �tre s�r d'�viter les conflits, il me suffit de 
 * synchroniser la m�thode getInstance() et le tour est jou�. Mais - parce qu'il y a un mais - cette
 * m�thode ne r�gle le probl�me qu'avant l'instanciation de la connexion. Autrement dit, une fois 
 * l'instanciation cr��e, la synchronisation ne sert plus � rien.
 * 
 * Le probl�me du multithreading ne se pose pas vraiment pour une connexion � une BDD puisque ce singleton
 * sert surtout de passerelle entre ma BDD et mon application. Cependant, il peut exister d'autres
 * objets que des connexions SQL qui ne doivent �tre instanci�s qu'une fois; tous ne sont pas aussi 
 * laxistes pour le multithreading.
 * 
 * Voyons comment parfaire ce pattern avec un exemple autre qu'une connexion SQL.
 * 
 * III./ Le singleton dans tous ses �tats
 * 
 * R�f MySingleton
 * R�f main (pour le test)
 * 
 * La politique du singleton est toujours bonne. Mais posons-nous la question : quand est-ce que la cr�ation
 * de l'instance est la plus judicieuse ? Ici, j'ai ex�cut� mon code et l'instance a �t� cr��e lorsqu'on
 * l'a demand� la premi�re fois ! C'est le principal probl�me que posent le singleton et le multithreading :
 * la premi�re instance... Une fois celle-ci cr��e, les probl�mes se font rares.
 * 
 * Pour limiter les ennuis, je vais donc laisser cette lourde t�che � la JVM, d�s le chargement de la classe,
 * en instanciant mon singleton lors de sa cr�ation. R�f MySinglton2
 * 
 * Ainsi, c'est la machine virtuelle qui s'occupe de charger l'instance du singleton, bien avant  que 
 * n'importe quel thread vienne taquiner la m�thode getInstance()...
 * 
 * Il existe une autre m�thode permettant de faire cela, mais elle ne fonctionne parfaitement que depuis
 * le JDK 1.5. On appelle cette m�thode "le verouillage � double v�rification". Elle consiste � utiliser
 * le mot cl� volatile combin� au mot cl� synchronized.
 * 
 * D�clarer une variable volatile permet d'assurer un acc�s ordonn� des threads � une variable (plusieurs
 * threads peuvent acc�der � cette variable), marquant ainsi le premier point de verouillage. Ensuite,
 * la double v�rification s'effectue dans la m�thode getInstace() : on effectue la synchronisation 
 * uniquement lorsque le singleton n'est pas cr��.
 * 
 * Voici ce que cela donne : R�f FinalSingleton
 * 
 * Conclusion : Pour �conomiser les ressources, je ne devrais cr�er qu'un seul objet de connexion.
 * Le pattern singleton permet de disposer d'une instance unique d'un objet.
 * Ce pattern repose sur un constructeur priv� associ� � une m�thode retournant l'instance cr��e dans la
 * classe elle-m�me. 
 * Afin de pallier au probl�me du multithreading, il me suffit d'utiliser le mot cl� synchronized dans
 * la d�claration de ma m�thode de r�cup�ration de l'instance, mais cette synchronisation n'est utile 
 * qu'une seule fois. A la place je peux instancier l'objet au chargement de la classe par la JVM, avant
 * tout appel � celle-ci.
 */
public class Main_connexions {

	public static void main(String[] args) {
		try{
			//Je vais appeler quatre fois la m�thode getInstance()
			PreparedStatement prepare = MyConnection.getInstance().prepareStatement("SELECT * FROM classe "
					+ "WHERE cls_nom = ?");
			Statement state = MyConnection.getInstance().createStatement();
			MyConnection.getInstance().setAutoCommit(false);
			DatabaseMetaData dataMeta = MyConnection.getInstance().getMetaData();
			
			//Creation d'une (autre) nouvelle connection
			Statement state2 = MyConnect.getInstance().createStatement();
			MyConnect.getInstance(); //already connected
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		for(int i = 0; i < 4; ++i)
			System.out.println("Appel N� " + i + " : " + MySingleton.getInstance().getName());
		for(int i = 0; i < 4; ++i)
			System.out.println("Appel N� " + i + " : " + FinalSingleton.getInstance().getName());
	}

}
