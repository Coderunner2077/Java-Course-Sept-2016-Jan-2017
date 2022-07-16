/*
 * 									Le design pattern Observer
 * 
 * C'est le fameux mécanisme d'écoute d'événements.
 * 
 * Le design pattern Observer est utilisé pour gérer les événements de mon IHM. C'est une
 * technique de programmation. La connaître aide à mieux comprendre le fonctionnement
 * de swing et awt. C'est par ce biais que mes composants effectueront quelque chose
 * lorsque je les cliquerai ou les survolerai.
 * 
 * 1.) Posons le problème
 * 
 * Je dois aider un client à réaliser une horloge digitale en Java.
 * Et il est impossible de faire communiquer l'horloge avec la fenêtre tout en gardant
 * l'indépendance de chacun des deux objets.
 * Il s'agit de faire communiquer l'objet Horloge avec l'objet Fenetre sans que l'objet
 * Horloge dépende de l'objet Fenetre => juste au cas où l'on devrait passer d'une 
 * IHM swing à une IHM awt.
 * Il est vrai que si l'on passe l'objet d'affichage dans l'horloge, dans le cas où l'on
 * change le type d'IHM, toutes les classes doivent être modifiées; ce n'est pas génial.
 * En fait, lorsque je procéde de la sorte (ie passer une instance de JLabel dans l'objet
 * Horloge), on dit que je couple des objets : je rends un ou plusieurs objets dépendants
 * d'un ou plusieurs autres objets (et donc, je ne pourrai plus utiliser les objets
 * couplés indépendamment des objets auxquels ils sont attachés).
 * Le couplage entre objets est l'un des problèmes relatifs à la réutilisation des objets.
 * Dans mon cas, si j'utilise l'objet Horloge dans une autre application, je serai
 * confronté à plusieurs problèmes étant donné que cet objet ne s'affichera que dans un 
 * JLabel (en supposant que je couple les objets).
 * 
 * C'est là que le pattern observer entre en jeu : il fait communiquer des objets entre 
 * eux sans qu'ils se connaissent réellement. 
 * 
 * 2.) Des objets qui parlent et qui écoutent : le pattern observer
 * 
 * Ce que je sais sur ce pattern :
 * 		-	il fait communiquer des objets entre eux
 * 		-	c'est un bon moyen d'éviter le couplage d'objets
 * 
 * Ce sont deux points cruciaux, mais un autre élément va me plaire davantage :
 * TOUT SE FAIT AUTOMATIQUEMENT.
 * 
 * En fait, mon horloge doit pouvoir avertir l'objet servant à afficher l'heure lorsqu'il
 * doit rafraîchir son affichage.
 * Ce qui est merveilleux avec ce pattern, c'est que mon horloge ne se contentera pas 
 * d'avertir un seul objet que sa valeur a changé : elle pourra en effet mettre plusieurs
 * observateurs au courant.
 * Pour faire une analogie, je peux interpréter la relation entre les objets implémentant
 * le pattern observer comme un éditeur de journal et ses clients.
 * Réf diagramme.png
 * Grâce à ce schéma, je vois que mon objet défini comme Observable pourra être surveillé
 * par plusieurs objets : il s'agit d'une relation dite de un à plusieurs vers l'objet
 * Observateur. Ce ne sont donc pas les instances d'Horloge et de JLabel que je vais 
 * utiliser, mais des implémentations d'interface (Observable et Observateur).
 * En effet, je sais que les classes implémentant une interface peuvent être définies par
 * le type de l'interface. Dans notre cas, la classe Fenetre implémentera l'interface 
 * Observateur : je pourrai la voir comme une classe de type Observateur. La deuxième
 * interface - celle dédiée à l'objet Horloge - possède trois méthodes :
	-	une permettant d'ajouter des observateurs (une collection d'observateurs à gérer..)
	-	une permettant de retirer les observateurs
	-	enfin, une mettant à jour les observateurs
 * Grâce à cela, mes objets ne sont plus liés par leurs types, mais par leurs interfaces !
 * L'interface qui apportera les méthodes de mise à jour, d'ajout d'observateurs, etc.
 * travaillera donc des objets de type Observateur.
 * Ainsi, le couplage ne s'effectue plus directement, il s'opère par le biais de ces
 * interfaces. Ici, il faut que mes 2 interfaces soient couplées pour que le système
 * fonctionne. 
 * Voici comment fonctionnera l'application :
	-	j'instancierai la classe Horloge dans ma classe Fenetre
	-	cette dernière implémentera l'interface Observateur (NON !)
	-	mon objet Horloge, implémentant l'interface Observable, préviendra les objets
		spécifiés de ses changements
	-	j'informerai l'horloge que ma fenêtre l'observe
	-	à partir de là, mon objet Horloge fera le reste : à chaque changement, j'appelrai
		la méthode mettant tous les observateurs à jour
	Réf packages 
 * J'ai réussi à permettre à deux objets de communiquer en n'utilisant presqu'aucun
 * couplage.
 * Je peux voir que lorsque l'heure change, la méthode updateObservateur() est invoquée.
 * Celle-ci parcourt la collection d'objets Observateur et appelle sa méthode 
 * update(String hour). La méthode update(String hour) étant redéfinie dans ma classe 
 * Fenetre afin de mettre JLabel à jour, l'heure s'affiche ! 
 * Ce pattern est utilisé dans la gestion événementielle d'interfaces graphiques. J'ai 
 * en outre remarqué que leurs syntaxes sont identiques. Mais, soit dit en passant, il
 * existe des classes Java permettant d'utiliser le pattern observer sans avoir à coder
 * ces interfaces.
 * 
 * 3.) Le pattern observer : le retour
 * 
 * En réalité, il existe une classe abstraite Observable et une interface Observer fournies
 * dans les classes Java.
 * Celles-ci fonctionnent de manière quasiment identique à la façon de procéder vue 
 * précédemment, seuls quelques détails diffèrent. Il est recommandé toutesfois d'utiliser
 * un pattern observer "fait maison". Pourquoi ?
 * ==> Tout simplement parce que l'objet que l'on souhaite observer doit hériter de la 
 * classe Observable. Par conséquent, il ne pourra plus hériter d'une autre classe étant
 * donné que Java ne gère pas l'héritage multiple.
 * Réf diagramme2.png
 * Je remarque qu'il fonctionne quasiment de la même manière que celui que j'ai développé.
 * Il y a toutefois une différece dans la méthode update(Observable obs, Object obj) :
 * sa signature a changé. Cette méthode prend en second paramètre un Object représentant
 * une donnée supplémentaire que je souhaite lui fournir.
 * 
 * Mes propres recherches à effectuer dans Java sur ce pattern : il existe des subtilités
 * (rien de méchant, cela dit).
 * 
 */
public class Main_pattern_Observer {

	public static void main(String[] args) {
		

	}

}
