import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 										Limiter le nombre de connexions
 * 
 * Je sais désormais comment me connecter à une BDD depuis Java. Il s'agira ici d'une approche un peu plus
 * orientée objet. Il est vrai qu'établir sans arrêt la connexion à la BDD commence à être fastidieux. On
 * peut y remédier avec ce que l'on appelle le pattern singleton.
 * 
 * I./ Pourquoi ne se connecter qu'une seule fois?
 * 
 * Pourquoi vouloir avoir une seule instance de l'objet Connection ?
 * ==> Parce que cela ne sert pas à grande chose de réinitialiser la connexion à chaque fois. Rappelons-nous
 * que la connexion sert à établir un pont entre ma base et mon application. Mon application et mon BDD
 * peuvent discuter.
 * Comment faire pour garantir qu'une seule instance de Connection existe dans l'application ?
 * ==> C'est ici que le pattern singleton entre en jeu ! Ce pattern est peut-être l'un des plus simples à
 * comprendre même. Son principe de base consiste à interdire l'instanciation d'une classe grâce à un 
 * constructeur déclaré private. 
 * 
 * II./ Le pattern singleton
 * 
 * Je veux qu'il soit impossible de créer plus d'un objet de connexion. Voici une classe qui permet
 * de s'assurer que c'est le cas : Réf MyConnection
 * Je viens de créer une classe avec un constructeur privé : du coup, impossible d'instancier cet objet et
 * d'accéder à ses attributs, puisqu'ils sont déclarés private ! Mon objet Connection est instancié dans
 * le constructeur privé et la seule méthode accessible depuis l'extérieur est getInstance(). C'est donc cette
 * méthode qui a pour rôle de créer une connexion si elle n'existe pas, et seulement dans ce cas. Pour en
 * avoir le coeur net, on va modifier un peu la méthode getInstance() afin qu'elle signale l'existance
 * d'une connexion le cas échéant. Cela montre bien quand la connexion est réellement créée.
 * Réf main
 * 
 * Ainsi, la connexion à la BDD ne se fait qu'une seule et unique fois pour mon application.
 * La classe MyConnection peut être simplifiée. Réf MyConnect 
 * 
 * Attention toutefois, je dois rajouter la déclaration static à mes paramètres de connexion aussi cette 
 * fois-ci (car ils sont utilisés dans une méthode static). J'avais commencé par inséré un constructeur
 * privé car je devais savor que cela existait. Mais bon, on peut très bien s'en passer.
 * 
 * Par contre, dans une application multithreads, pour être sûr d'éviter les conflits, il me suffit de 
 * synchroniser la méthode getInstance() et le tour est joué. Mais - parce qu'il y a un mais - cette
 * méthode ne règle le problème qu'avant l'instanciation de la connexion. Autrement dit, une fois 
 * l'instanciation créée, la synchronisation ne sert plus à rien.
 * 
 * Le problème du multithreading ne se pose pas vraiment pour une connexion à une BDD puisque ce singleton
 * sert surtout de passerelle entre ma BDD et mon application. Cependant, il peut exister d'autres
 * objets que des connexions SQL qui ne doivent être instanciés qu'une fois; tous ne sont pas aussi 
 * laxistes pour le multithreading.
 * 
 * Voyons comment parfaire ce pattern avec un exemple autre qu'une connexion SQL.
 * 
 * III./ Le singleton dans tous ses états
 * 
 * Réf MySingleton
 * Réf main (pour le test)
 * 
 * La politique du singleton est toujours bonne. Mais posons-nous la question : quand est-ce que la création
 * de l'instance est la plus judicieuse ? Ici, j'ai exécuté mon code et l'instance a été créée lorsqu'on
 * l'a demandé la première fois ! C'est le principal problème que posent le singleton et le multithreading :
 * la première instance... Une fois celle-ci créée, les problèmes se font rares.
 * 
 * Pour limiter les ennuis, je vais donc laisser cette lourde tâche à la JVM, dès le chargement de la classe,
 * en instanciant mon singleton lors de sa création. Réf MySinglton2
 * 
 * Ainsi, c'est la machine virtuelle qui s'occupe de charger l'instance du singleton, bien avant  que 
 * n'importe quel thread vienne taquiner la méthode getInstance()...
 * 
 * Il existe une autre méthode permettant de faire cela, mais elle ne fonctionne parfaitement que depuis
 * le JDK 1.5. On appelle cette méthode "le verouillage à double vérification". Elle consiste à utiliser
 * le mot clé volatile combiné au mot clé synchronized.
 * 
 * Déclarer une variable volatile permet d'assurer un accès ordonné des threads à une variable (plusieurs
 * threads peuvent accéder à cette variable), marquant ainsi le premier point de verouillage. Ensuite,
 * la double vérification s'effectue dans la méthode getInstace() : on effectue la synchronisation 
 * uniquement lorsque le singleton n'est pas créé.
 * 
 * Voici ce que cela donne : Réf FinalSingleton
 * 
 * Conclusion : Pour économiser les ressources, je ne devrais créer qu'un seul objet de connexion.
 * Le pattern singleton permet de disposer d'une instance unique d'un objet.
 * Ce pattern repose sur un constructeur privé associé à une méthode retournant l'instance créée dans la
 * classe elle-même. 
 * Afin de pallier au problème du multithreading, il me suffit d'utiliser le mot clé synchronized dans
 * la déclaration de ma méthode de récupération de l'instance, mais cette synchronisation n'est utile 
 * qu'une seule fois. A la place je peux instancier l'objet au chargement de la classe par la JVM, avant
 * tout appel à celle-ci.
 */
public class Main_connexions {

	public static void main(String[] args) {
		try{
			//Je vais appeler quatre fois la méthode getInstance()
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
			System.out.println("Appel N° " + i + " : " + MySingleton.getInstance().getName());
		for(int i = 0; i < 4; ++i)
			System.out.println("Appel N° " + i + " : " + FinalSingleton.getInstance().getName());
	}

}
