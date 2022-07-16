/*
 * 									Le design pattern Observer
 * 
 * C'est le fameux m�canisme d'�coute d'�v�nements.
 * 
 * Le design pattern Observer est utilis� pour g�rer les �v�nements de mon IHM. C'est une
 * technique de programmation. La conna�tre aide � mieux comprendre le fonctionnement
 * de swing et awt. C'est par ce biais que mes composants effectueront quelque chose
 * lorsque je les cliquerai ou les survolerai.
 * 
 * 1.) Posons le probl�me
 * 
 * Je dois aider un client � r�aliser une horloge digitale en Java.
 * Et il est impossible de faire communiquer l'horloge avec la fen�tre tout en gardant
 * l'ind�pendance de chacun des deux objets.
 * Il s'agit de faire communiquer l'objet Horloge avec l'objet Fenetre sans que l'objet
 * Horloge d�pende de l'objet Fenetre => juste au cas o� l'on devrait passer d'une 
 * IHM swing � une IHM awt.
 * Il est vrai que si l'on passe l'objet d'affichage dans l'horloge, dans le cas o� l'on
 * change le type d'IHM, toutes les classes doivent �tre modifi�es; ce n'est pas g�nial.
 * En fait, lorsque je proc�de de la sorte (ie passer une instance de JLabel dans l'objet
 * Horloge), on dit que je couple des objets : je rends un ou plusieurs objets d�pendants
 * d'un ou plusieurs autres objets (et donc, je ne pourrai plus utiliser les objets
 * coupl�s ind�pendamment des objets auxquels ils sont attach�s).
 * Le couplage entre objets est l'un des probl�mes relatifs � la r�utilisation des objets.
 * Dans mon cas, si j'utilise l'objet Horloge dans une autre application, je serai
 * confront� � plusieurs probl�mes �tant donn� que cet objet ne s'affichera que dans un 
 * JLabel (en supposant que je couple les objets).
 * 
 * C'est l� que le pattern observer entre en jeu : il fait communiquer des objets entre 
 * eux sans qu'ils se connaissent r�ellement. 
 * 
 * 2.) Des objets qui parlent et qui �coutent : le pattern observer
 * 
 * Ce que je sais sur ce pattern :
 * 		-	il fait communiquer des objets entre eux
 * 		-	c'est un bon moyen d'�viter le couplage d'objets
 * 
 * Ce sont deux points cruciaux, mais un autre �l�ment va me plaire davantage :
 * TOUT SE FAIT AUTOMATIQUEMENT.
 * 
 * En fait, mon horloge doit pouvoir avertir l'objet servant � afficher l'heure lorsqu'il
 * doit rafra�chir son affichage.
 * Ce qui est merveilleux avec ce pattern, c'est que mon horloge ne se contentera pas 
 * d'avertir un seul objet que sa valeur a chang� : elle pourra en effet mettre plusieurs
 * observateurs au courant.
 * Pour faire une analogie, je peux interpr�ter la relation entre les objets impl�mentant
 * le pattern observer comme un �diteur de journal et ses clients.
 * R�f diagramme.png
 * Gr�ce � ce sch�ma, je vois que mon objet d�fini comme Observable pourra �tre surveill�
 * par plusieurs objets : il s'agit d'une relation dite de un � plusieurs vers l'objet
 * Observateur. Ce ne sont donc pas les instances d'Horloge et de JLabel que je vais 
 * utiliser, mais des impl�mentations d'interface (Observable et Observateur).
 * En effet, je sais que les classes impl�mentant une interface peuvent �tre d�finies par
 * le type de l'interface. Dans notre cas, la classe Fenetre impl�mentera l'interface 
 * Observateur : je pourrai la voir comme une classe de type Observateur. La deuxi�me
 * interface - celle d�di�e � l'objet Horloge - poss�de trois m�thodes :
	-	une permettant d'ajouter des observateurs (une collection d'observateurs � g�rer..)
	-	une permettant de retirer les observateurs
	-	enfin, une mettant � jour les observateurs
 * Gr�ce � cela, mes objets ne sont plus li�s par leurs types, mais par leurs interfaces !
 * L'interface qui apportera les m�thodes de mise � jour, d'ajout d'observateurs, etc.
 * travaillera donc des objets de type Observateur.
 * Ainsi, le couplage ne s'effectue plus directement, il s'op�re par le biais de ces
 * interfaces. Ici, il faut que mes 2 interfaces soient coupl�es pour que le syst�me
 * fonctionne. 
 * Voici comment fonctionnera l'application :
	-	j'instancierai la classe Horloge dans ma classe Fenetre
	-	cette derni�re impl�mentera l'interface Observateur (NON !)
	-	mon objet Horloge, impl�mentant l'interface Observable, pr�viendra les objets
		sp�cifi�s de ses changements
	-	j'informerai l'horloge que ma fen�tre l'observe
	-	� partir de l�, mon objet Horloge fera le reste : � chaque changement, j'appelrai
		la m�thode mettant tous les observateurs � jour
	R�f packages 
 * J'ai r�ussi � permettre � deux objets de communiquer en n'utilisant presqu'aucun
 * couplage.
 * Je peux voir que lorsque l'heure change, la m�thode updateObservateur() est invoqu�e.
 * Celle-ci parcourt la collection d'objets Observateur et appelle sa m�thode 
 * update(String hour). La m�thode update(String hour) �tant red�finie dans ma classe 
 * Fenetre afin de mettre JLabel � jour, l'heure s'affiche ! 
 * Ce pattern est utilis� dans la gestion �v�nementielle d'interfaces graphiques. J'ai 
 * en outre remarqu� que leurs syntaxes sont identiques. Mais, soit dit en passant, il
 * existe des classes Java permettant d'utiliser le pattern observer sans avoir � coder
 * ces interfaces.
 * 
 * 3.) Le pattern observer : le retour
 * 
 * En r�alit�, il existe une classe abstraite Observable et une interface Observer fournies
 * dans les classes Java.
 * Celles-ci fonctionnent de mani�re quasiment identique � la fa�on de proc�der vue 
 * pr�c�demment, seuls quelques d�tails diff�rent. Il est recommand� toutesfois d'utiliser
 * un pattern observer "fait maison". Pourquoi ?
 * ==> Tout simplement parce que l'objet que l'on souhaite observer doit h�riter de la 
 * classe Observable. Par cons�quent, il ne pourra plus h�riter d'une autre classe �tant
 * donn� que Java ne g�re pas l'h�ritage multiple.
 * R�f diagramme2.png
 * Je remarque qu'il fonctionne quasiment de la m�me mani�re que celui que j'ai d�velopp�.
 * Il y a toutefois une diff�rece dans la m�thode update(Observable obs, Object obj) :
 * sa signature a chang�. Cette m�thode prend en second param�tre un Object repr�sentant
 * une donn�e suppl�mentaire que je souhaite lui fournir.
 * 
 * Mes propres recherches � effectuer dans Java sur ce pattern : il existe des subtilit�s
 * (rien de m�chant, cela dit).
 * 
 */
public class Main_pattern_Observer {

	public static void main(String[] args) {
		

	}

}
