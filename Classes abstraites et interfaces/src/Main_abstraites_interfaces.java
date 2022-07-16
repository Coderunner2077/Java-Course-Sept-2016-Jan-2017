/*
 * 											Classes abstraites et interfaces
 * Il est primordial de savoir bien structurer mes programmes (on parle d'architecture logicielle). Où faut-il 
 * ranger les comportements d'objet : dans la classe mère? dans la classe fille?
 * I'm about to discover all about it.
 * 
 * I./ Les classses absraites
 * 1.) La notion des classes abstraites
 * 
 * Une classe abstaite est quasiment identique à une classe normale. Quasiment, car elle a tout de même une 
 * particularité : je ne peux pas l'instancier. Imaginons que j'ai une classe A déclarée abstraite. Voici un code
 * qui ne fonctionnera pas :
 * public class Test {
 * 	public static class void main(String[] args{
 * 		A obj = new A(); // Erreur de compilation
 * 	}
 * }
 * La classe abstraite est comme une idée générale qui ne peut, en raison de sa généralité et de son abstraction, 
 * être réduite à un cas particulier. Par exemple, la classe Animal, déclarée abstraite, peut être dérivée
 * à autant de classes d'animaux différents, ayant chacune leur spécificité et leurs points communs, que l'on 
 * le souhaite, mais la classe Animal ne peut pas elle-même être instanciée tout comme un animal ne peut
 * exister sans devenir quelque chose de plus spécifique, comme un loup, un mouton...
 * En fait, les classes abstraites servent à définir une superclasse : elles servent essentiellement à créer un
 * nouveau type d'objets.
 * Voyons comment créer un telle classe.
 * 
 * 2.) Une classe Animal très abstraite
 * 
 * Il existe une règle pour qu'une classe soit considérée comme abstraite : elle doit être déclarée avec le mot clé
 * abstract.
 * 
 * Une telle classe peut contenir la même chose qu'une classe normale. Ses enfants pourront utiliser tous ses 
 * éléments déclarés (attributs et méthodes déclarés public ou protected). Cependant, ce type de classe permet
 * éventuellement (et non pas systèmatiquement) de définir des méthodes abstraites. Ces dernières présentent une
 * particularité : elle n'ont pas de corps (tout comme les prototypes des fonctions en C). On voit alors pourquoi 
 * on dit "méthode abstraite" : difficile de savoir ce qu'une méthode sans corps sait faire.
 * Il faut savoir qu'une méthode abstraite ne peut exister que dans une classe abstraite. Si, dans une classe, j'ai 
 * une méthode déclarée comme abstraite, je dois alors déclarer ma classe abstraite.
 * Les classes enfants hériteront aussi des méthodes abstraites, mais vu que ces dernières n'ont pas de corps, mes 
 * classes enfants seront obligés de redéfinir ces méthodes. Et ces dernières ne seront rien d'autre que des 
 * méthodes polymorphes, ce qui implique que la covariance des variables pointe de nouveau le bout de son nez :
	public class Test{
  		public static void main(String args[]){
    		Animal loup = new Loup();
    		Animal chien = new Chien();
    		loup.manger();
    		chien.crier(); 
  		}
	}
 * Attention, il est primordial de comprendre que là, je n'ai pas instancié ma classe abstraite. J'ai instancié
 * un objet Loup que j'ai mis dans un objet de type Animal (il en va de même pour l'instanciation de la classe Chien). 
 * Je dois me rappeler que l'instance se crée avec le mot clé new. En aucun cas, le fait de déclarer une variable
 * d'un type de classe donné - ici, Animal - n'est une instanciation. Ici, j'instancie un Loup et un Chien.
 * Je peux aussi utiliser une variable de type Object comme référence à un objet Loup, à un objet Chien, etc.
 * Mais je ne peux pas mettre directement une référence de type Object dans une référence de type Loup. Réf.
 * Pour avertir la JVM que la référence que je veux affecter à mon objet de type Loup est un Loup, je dois utiliser
 * le transtypage. Réf main 219-221
 * 
 * 3.) Cahier de charge de l'exemple Animal
 * 
 * Ce que je fais pour l'exemple:
 * 		-	Mes objets seront probablement tous de couleur et de poids différents. Mes classes auront donc le droit
 * 		de modifier ceux-ci.
 * 		-	Ici, je pars du principe que tous mes animaux sont carnivores. La méthode manger() sera donc définie
 * 		dans la classe Animal
 * 		-	Idem pour la méthode boire()
 * 		-	Ils ne crieront pas et ne se déplaceront pas de la même manière ==> méthodes polymorphes & méthodes
 * 		abstraites dans la classe Animal
 * 
 * Mais je peux encore améliorer l'architecture, en créant deux sous-classes : Canin et Felin, qui hériteront 
 * d'Animal, et dont mes objets eux-même hériteront.
 * Je vais redéfinir la méthode deplacement() dans les deux sous-classes, car je vais partir du principe que les 
 * canins se déplacent d'une certaine manière et les félins d'une toute autre. (Avec un tel exemple, je réviserai
 * le polymorphisme)
 * Les classes Canin et Felin sont deux beaux exemples de classes abstraites sans aucune méthode abstraite.
 * Note: getClass() se trouve dans la classe Object et retourne "class <nom de la classe>".
 * Il existe un moyen d'améliorer l'architecture logicielle, afin de la rendre plus portable d'una application à
 * une toute autre, en recourant aux ...interfaces.
 * 
 * II./ Les interfaces
 * En fait, les interfaces permettent de créer un nouveau supertype, on peut même ajouter autant que l'on veut
 * dans une seule classe ! Quant à l'utilisation de mes objets, la convention est toute trouvée. Pourquoi?
 * Parce qu'une interface n'est rien d'autre qu'une classe 100 % abstraite.
 * 
 * 1.) Ma première interface
 * Pour déclarer une interface ("I"), j'écris :
 * public interface I { }
 * Vu qu'une interface est 100 % abstraite, il ne me reste qu'à ajouter des méthodes abstraites, mais sans le mot
 * clé  abstract. En voici des exemples :
 * public interface I{
 * 		public void A();
 * 		public String B();
 * }
 * public interface I2{
 * 		public void C();
 * 		public String D();
 * }
 * Et pour faire en sorte qu'une classe utilise une interface, il suffit d'utiliser le mot clé implements. Ce qui
 * me donnerait :
 * public class X implements I{
 * public void A(){
 // ...
  }
  public String B(){
  // ...
   }
   }
 * On dit que la classe X implémente l'interface I. ET JE PEUX MEME IMPLEMENTER PLUSIEURS INTERFACES, comme ceci:
 	public class X implements I, I2{
 		public void A(){
 		//...
 	 	}
 	 	public String B(){
 	 	//...
 	  	}
 	 	public void C(){
 	 	//...
 	  	}
 			public String D(){
 		//...
 		}
 	}
 Par contre, lorsque j'implémente une interface, je dois absolument redéfinir les méthodes de l'interface.
 ATTENTION, l'ordre des déclarations est primordial : je dois mettre l'expression de l'héritage avant celle de 
 l'implémentation, sinon mon code ne compilera pas.
 Utilisation du polymorphisme de l'implémentation Rintintin : réf main 245-249
 Objectif atteint : je suis parvenu à définir deux superclasses afin de les utiliser comme supertypes et de jouir
 pleinement du polymorphisme.
 
 Par ailleurs, il existe une autre façon très intéressante d'utiliser les interfaces grâce à une technique de 
 programmation appelée "pattern strategy". Grâce à cette dernière, on peut faire évoluer au mieux un programme
 Java.
 
  III./ Le pattern strategy
  
  1.) Les limites de l'héritage
  En dépit de la puissance de l'héritage, celui-ci atteint ses limites lorsque je suis amené à modifier la 
  hiérarchie de mes classes afin de répondre à une demande (du client, etc.).
  Le fait de toucher à ma hiérarchie peut amener des erreurs indésirables, voire des absurdités : tout cela parce
  que je vais changer une structure qui fonctionne à cause des contraintes que l'on m'impose. 
  Si l'on ne s'en tient qu'à l'héritage pour développer un programme ou un jeu en essayant continuellement 
  d'apporter des améliorations et donc des modifications, on va être confrontés à trois problèmes majeures :
  		-	du code dupliqué s'insinuera dans mon code
  		-	à chaque modification du comportement de mes personnages, je serai obligé de retoucher le code source
  		de la (ou des) classe(s) concernée(s)
  		-	mon code perdra en réutilisabilité et du coup, il ne sera pas extensible du tout.
  
  2.) Une solution simple et robuste : le pattern strategy
  
  Pour remédier aux limites de l'héritage, il existe un concept simple (que je connais déjà) : l'encapsulation.
  C'est sur ce concept que se base le pattern strategy pour répondre aux limites de l'héritage. 
  Le pattern strategy ("modèle de conception" en français) consiste à créer des objets avec des données, des
  méthodes (voire les deux). Le principe de base de ce pattern est le suivant : 
  "isoler ce qui varie dans le programme et l'encapsuler".
  Le pattern strategy offre la possibilité de ne modifier que les comportements et non les objets qui ont ces 
  comportements.
  Dans mon exemple, les classes héritant du Personnage  héritent aussi de ses comportements, et, par conséquent,
  on peut dire que mes classes filles sont aussi des Personnage.
  Les éléments qui ne cessent de varier dans mon programme sont les suivants:
  	-	la méthode combattre();
  	-	la méthode seDeplacer();
  	-	la méthode soigner();
  Quand certains comportements de la classe mère semblent ne pas être au bon endroit dans la hiérarchie à cause
  de la nécesssité de les faire varier fréquemment, on doit recourir au pattern strategy.
  ==> Il suffit de faire sortir les comportements qui varient de la classe mère, de créer une classe abstraite ou 
  une interface symbolisant ces comportements et d'ordonner à ma classe Personnage d'avoir ces comportements. 
  
  Il faut se rendre compte qu'utiliser les interfaces de cette manière revient à créer un supertype de variable;
  du coup, on peut utiliser les classes héritant de ces interfaces de façon polymorphe sans se soucier de la classe
  dont sont issus nos objets. Dans mon cas, la classe Personnage comprendra des objets de type EspritCombatif, 
  Soin et Deplacement.
  Afin de gagner en clarté, il sera préférable de gérer mes différentes classes avec différents packages.
  En effet, les comportements de mes personnages sont trop épars pour être définis dans ma superclasse Personnage.
  Et le but est de pouvoir modifier uniquement les comportements et non les classes héritant de ma superclasse.
  Les interfaces servent à créer un supertype d'objet; grâce à elles, j'utiliserai des objets de type :
  		-	EspritCombatif qui présentent une méthode combat();
  		-	Soin qui présentent une méthode soigne();
  		-	Deplacement qui présentent une méthode deplace();
  Dans ma classe Personnage, j'ai ajouté une instance de chaque type de comportement. Je vais développer un 
  comportement par défaut pour chacun d'entre eux et affecter cet objet dans ma superclasse. Les classes filles, 
  elles, comprendront des instances différents selon leurs besoins.
  Du coup, que faire des méthodes de la superclasse Personnage ?
  Je les garde, mais plutôt que de redéfinir ces dernières, la superclasse va invoquer la méthode de comportement
   de chaque objet. Ainsi, je n'ai plus à redéfinir ou à modifier mes classes. La seule chose qu'il reste à faire, 
   c'est d'affecter une instance de comportement à mes objets. Voir les packages personnage et comportement.
   
   Maintenat que j'ai défini des objets de comportements, je vais pouvoir remanier ma classe Personnage. J'ajoute
   les variables d'instance, les mutateurs et les constructeurs permettant d'initialiser mes objets.
   
   Conclusion : Que de changements avec le pattern strategy ! Grâce à ce dernier, je n'utilise plus de méthodes
   définies dans ma hiérarchie de classes, mais des implémentations concrètes d'interfaces. Les méthodes que 
   mes classes appellent utilisent chacune un objet de comportement. Je peux donc définir des guerriers, des civils,
   des médecins... tous personnalisables, puisqu'il suffit de modifier l'instance de leur comportement pour que
   ceux-ci changent instantanément. De plus, je peux changer le comportement des instances avec le mutateur présent
   dans la superclasse en lui passant une implémentation correspondante.
   Le plus beau, c'est qu'on peut instancier des objets Guerriers avec des comportements différents.
   Donc, le pattern strategy permet de rendre une hiérarchie de classes plus souple. On a vu qu'il valait mieux
   encapsuler des comportements plutôt que de les mettre d'office dans l'objet concerné.
  
  
  
 
 
 	
 * 
 * 
 * 
 */
import fr.groupe.personnage.*;
import fr.comportement.*;

public class Main_abstraites_interfaces {

	public static void main(String[] args) {
		Animal loup = new Loup();
		loup.crier();
		loup.manger();
		Object obj = new Loup();
		((Loup)obj).manger();
		//Loup l = obj; // Problème de référence
		Loup l = (Loup)obj; // transtypage
		Canin wolf = new Loup("gris", 70);
		wolf.boire(); //méthode de la classe Animal
		wolf.deplacement(); // méthode de la classe Canin
		wolf.manger(); // méthode de la classe Animal
		wolf.crier(); // méthode de la classe Loup
		System.out.println(wolf.toString());
		System.out.println("--------------------------------------------");
		
		Chien chien = new Chien("Gris bleuté", 45);
		//les méthodes du Chien
		chien.boire();
		chien.manger();
		chien.deplacement();
		chien.crier();
		System.out.println(chien);
		System.out.println("--------------------------------------------");
		
		//les méthodes de l'interface
		chien.faireLeBeau();
		chien.faireLechouille();
		chien.faireCalin();
		
		System.out.println("--------------------------------------------");
		//J'utilise le polymorphisme de mon interface
		Rintintin r = new Chien();
		r.faireLeBeau();
		r.faireLechouille();
		r.faireCalin();
		
		System.out.println("==============================================");
		Personnage[] tPers = {new Guerrier(), new Sniper(), new Medecin()};
		for(int i = 0; i < tPers.length; i++){
			System.out.println("Instance de "+tPers[i].getClass().getName());
			System.out.println("*********************************************");
			tPers[i].combattre();
			tPers[i].soigner();
			tPers[i].seDeplacer();
			System.out.println(tPers[i]);
		}
		// JE VAIS MAINTENANT CHANGER DYNAMIQUEMENT LE COMPORTEMENT D'UNE INSTANCE
		Personnage civil = new Civil();
		civil.combattre();
		civil.setEspritCombatif(new CombatCouteau());
		civil.combattre();
		Personnage pers = new Guerrier();
		pers.soigner();
		pers.setSoin(new PremierSoin());
		pers.soigner();
		Personnage guerrier = new Guerrier();
		guerrier.soigner();
		Personnage soldat = new Guerrier(new CombatPistolet(), new PremierSoin(), new Courir());
		soldat.combattre();
		soldat.seDeplacer();
		soldat.soigner();
		System.out.println(soldat);
	}

}
