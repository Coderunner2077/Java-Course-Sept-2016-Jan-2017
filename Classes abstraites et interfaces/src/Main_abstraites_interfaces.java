/*
 * 											Classes abstraites et interfaces
 * Il est primordial de savoir bien structurer mes programmes (on parle d'architecture logicielle). O� faut-il 
 * ranger les comportements d'objet : dans la classe m�re? dans la classe fille?
 * I'm about to discover all about it.
 * 
 * I./ Les classses absraites
 * 1.) La notion des classes abstraites
 * 
 * Une classe abstaite est quasiment identique � une classe normale. Quasiment, car elle a tout de m�me une 
 * particularit� : je ne peux pas l'instancier. Imaginons que j'ai une classe A d�clar�e abstraite. Voici un code
 * qui ne fonctionnera pas :
 * public class Test {
 * 	public static class void main(String[] args{
 * 		A obj = new A(); // Erreur de compilation
 * 	}
 * }
 * La classe abstraite est comme une id�e g�n�rale qui ne peut, en raison de sa g�n�ralit� et de son abstraction, 
 * �tre r�duite � un cas particulier. Par exemple, la classe Animal, d�clar�e abstraite, peut �tre d�riv�e
 * � autant de classes d'animaux diff�rents, ayant chacune leur sp�cificit� et leurs points communs, que l'on 
 * le souhaite, mais la classe Animal ne peut pas elle-m�me �tre instanci�e tout comme un animal ne peut
 * exister sans devenir quelque chose de plus sp�cifique, comme un loup, un mouton...
 * En fait, les classes abstraites servent � d�finir une superclasse : elles servent essentiellement � cr�er un
 * nouveau type d'objets.
 * Voyons comment cr�er un telle classe.
 * 
 * 2.) Une classe Animal tr�s abstraite
 * 
 * Il existe une r�gle pour qu'une classe soit consid�r�e comme abstraite : elle doit �tre d�clar�e avec le mot cl�
 * abstract.
 * 
 * Une telle classe peut contenir la m�me chose qu'une classe normale. Ses enfants pourront utiliser tous ses 
 * �l�ments d�clar�s (attributs et m�thodes d�clar�s public ou protected). Cependant, ce type de classe permet
 * �ventuellement (et non pas syst�matiquement) de d�finir des m�thodes abstraites. Ces derni�res pr�sentent une
 * particularit� : elle n'ont pas de corps (tout comme les prototypes des fonctions en C). On voit alors pourquoi 
 * on dit "m�thode abstraite" : difficile de savoir ce qu'une m�thode sans corps sait faire.
 * Il faut savoir qu'une m�thode abstraite ne peut exister que dans une classe abstraite. Si, dans une classe, j'ai 
 * une m�thode d�clar�e comme abstraite, je dois alors d�clarer ma classe abstraite.
 * Les classes enfants h�riteront aussi des m�thodes abstraites, mais vu que ces derni�res n'ont pas de corps, mes 
 * classes enfants seront oblig�s de red�finir ces m�thodes. Et ces derni�res ne seront rien d'autre que des 
 * m�thodes polymorphes, ce qui implique que la covariance des variables pointe de nouveau le bout de son nez :
	public class Test{
  		public static void main(String args[]){
    		Animal loup = new Loup();
    		Animal chien = new Chien();
    		loup.manger();
    		chien.crier(); 
  		}
	}
 * Attention, il est primordial de comprendre que l�, je n'ai pas instanci� ma classe abstraite. J'ai instanci�
 * un objet Loup que j'ai mis dans un objet de type Animal (il en va de m�me pour l'instanciation de la classe Chien). 
 * Je dois me rappeler que l'instance se cr�e avec le mot cl� new. En aucun cas, le fait de d�clarer une variable
 * d'un type de classe donn� - ici, Animal - n'est une instanciation. Ici, j'instancie un Loup et un Chien.
 * Je peux aussi utiliser une variable de type Object comme r�f�rence � un objet Loup, � un objet Chien, etc.
 * Mais je ne peux pas mettre directement une r�f�rence de type Object dans une r�f�rence de type Loup. R�f.
 * Pour avertir la JVM que la r�f�rence que je veux affecter � mon objet de type Loup est un Loup, je dois utiliser
 * le transtypage. R�f main 219-221
 * 
 * 3.) Cahier de charge de l'exemple Animal
 * 
 * Ce que je fais pour l'exemple:
 * 		-	Mes objets seront probablement tous de couleur et de poids diff�rents. Mes classes auront donc le droit
 * 		de modifier ceux-ci.
 * 		-	Ici, je pars du principe que tous mes animaux sont carnivores. La m�thode manger() sera donc d�finie
 * 		dans la classe Animal
 * 		-	Idem pour la m�thode boire()
 * 		-	Ils ne crieront pas et ne se d�placeront pas de la m�me mani�re ==> m�thodes polymorphes & m�thodes
 * 		abstraites dans la classe Animal
 * 
 * Mais je peux encore am�liorer l'architecture, en cr�ant deux sous-classes : Canin et Felin, qui h�riteront 
 * d'Animal, et dont mes objets eux-m�me h�riteront.
 * Je vais red�finir la m�thode deplacement() dans les deux sous-classes, car je vais partir du principe que les 
 * canins se d�placent d'une certaine mani�re et les f�lins d'une toute autre. (Avec un tel exemple, je r�viserai
 * le polymorphisme)
 * Les classes Canin et Felin sont deux beaux exemples de classes abstraites sans aucune m�thode abstraite.
 * Note: getClass() se trouve dans la classe Object et retourne "class <nom de la classe>".
 * Il existe un moyen d'am�liorer l'architecture logicielle, afin de la rendre plus portable d'una application �
 * une toute autre, en recourant aux ...interfaces.
 * 
 * II./ Les interfaces
 * En fait, les interfaces permettent de cr�er un nouveau supertype, on peut m�me ajouter autant que l'on veut
 * dans une seule classe ! Quant � l'utilisation de mes objets, la convention est toute trouv�e. Pourquoi?
 * Parce qu'une interface n'est rien d'autre qu'une classe 100 % abstraite.
 * 
 * 1.) Ma premi�re interface
 * Pour d�clarer une interface ("I"), j'�cris :
 * public interface I { }
 * Vu qu'une interface est 100 % abstraite, il ne me reste qu'� ajouter des m�thodes abstraites, mais sans le mot
 * cl�  abstract. En voici des exemples :
 * public interface I{
 * 		public void A();
 * 		public String B();
 * }
 * public interface I2{
 * 		public void C();
 * 		public String D();
 * }
 * Et pour faire en sorte qu'une classe utilise une interface, il suffit d'utiliser le mot cl� implements. Ce qui
 * me donnerait :
 * public class X implements I{
 * public void A(){
 // ...
  }
  public String B(){
  // ...
   }
   }
 * On dit que la classe X impl�mente l'interface I. ET JE PEUX MEME IMPLEMENTER PLUSIEURS INTERFACES, comme ceci:
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
 Par contre, lorsque j'impl�mente une interface, je dois absolument red�finir les m�thodes de l'interface.
 ATTENTION, l'ordre des d�clarations est primordial : je dois mettre l'expression de l'h�ritage avant celle de 
 l'impl�mentation, sinon mon code ne compilera pas.
 Utilisation du polymorphisme de l'impl�mentation Rintintin : r�f main 245-249
 Objectif atteint : je suis parvenu � d�finir deux superclasses afin de les utiliser comme supertypes et de jouir
 pleinement du polymorphisme.
 
 Par ailleurs, il existe une autre fa�on tr�s int�ressante d'utiliser les interfaces gr�ce � une technique de 
 programmation appel�e "pattern strategy". Gr�ce � cette derni�re, on peut faire �voluer au mieux un programme
 Java.
 
  III./ Le pattern strategy
  
  1.) Les limites de l'h�ritage
  En d�pit de la puissance de l'h�ritage, celui-ci atteint ses limites lorsque je suis amen� � modifier la 
  hi�rarchie de mes classes afin de r�pondre � une demande (du client, etc.).
  Le fait de toucher � ma hi�rarchie peut amener des erreurs ind�sirables, voire des absurdit�s : tout cela parce
  que je vais changer une structure qui fonctionne � cause des contraintes que l'on m'impose. 
  Si l'on ne s'en tient qu'� l'h�ritage pour d�velopper un programme ou un jeu en essayant continuellement 
  d'apporter des am�liorations et donc des modifications, on va �tre confront�s � trois probl�mes majeures :
  		-	du code dupliqu� s'insinuera dans mon code
  		-	� chaque modification du comportement de mes personnages, je serai oblig� de retoucher le code source
  		de la (ou des) classe(s) concern�e(s)
  		-	mon code perdra en r�utilisabilit� et du coup, il ne sera pas extensible du tout.
  
  2.) Une solution simple et robuste : le pattern strategy
  
  Pour rem�dier aux limites de l'h�ritage, il existe un concept simple (que je connais d�j�) : l'encapsulation.
  C'est sur ce concept que se base le pattern strategy pour r�pondre aux limites de l'h�ritage. 
  Le pattern strategy ("mod�le de conception" en fran�ais) consiste � cr�er des objets avec des donn�es, des
  m�thodes (voire les deux). Le principe de base de ce pattern est le suivant : 
  "isoler ce qui varie dans le programme et l'encapsuler".
  Le pattern strategy offre la possibilit� de ne modifier que les comportements et non les objets qui ont ces 
  comportements.
  Dans mon exemple, les classes h�ritant du Personnage  h�ritent aussi de ses comportements, et, par cons�quent,
  on peut dire que mes classes filles sont aussi des Personnage.
  Les �l�ments qui ne cessent de varier dans mon programme sont les suivants:
  	-	la m�thode combattre();
  	-	la m�thode seDeplacer();
  	-	la m�thode soigner();
  Quand certains comportements de la classe m�re semblent ne pas �tre au bon endroit dans la hi�rarchie � cause
  de la n�cesssit� de les faire varier fr�quemment, on doit recourir au pattern strategy.
  ==> Il suffit de faire sortir les comportements qui varient de la classe m�re, de cr�er une classe abstraite ou 
  une interface symbolisant ces comportements et d'ordonner � ma classe Personnage d'avoir ces comportements. 
  
  Il faut se rendre compte qu'utiliser les interfaces de cette mani�re revient � cr�er un supertype de variable;
  du coup, on peut utiliser les classes h�ritant de ces interfaces de fa�on polymorphe sans se soucier de la classe
  dont sont issus nos objets. Dans mon cas, la classe Personnage comprendra des objets de type EspritCombatif, 
  Soin et Deplacement.
  Afin de gagner en clart�, il sera pr�f�rable de g�rer mes diff�rentes classes avec diff�rents packages.
  En effet, les comportements de mes personnages sont trop �pars pour �tre d�finis dans ma superclasse Personnage.
  Et le but est de pouvoir modifier uniquement les comportements et non les classes h�ritant de ma superclasse.
  Les interfaces servent � cr�er un supertype d'objet; gr�ce � elles, j'utiliserai des objets de type :
  		-	EspritCombatif qui pr�sentent une m�thode combat();
  		-	Soin qui pr�sentent une m�thode soigne();
  		-	Deplacement qui pr�sentent une m�thode deplace();
  Dans ma classe Personnage, j'ai ajout� une instance de chaque type de comportement. Je vais d�velopper un 
  comportement par d�faut pour chacun d'entre eux et affecter cet objet dans ma superclasse. Les classes filles, 
  elles, comprendront des instances diff�rents selon leurs besoins.
  Du coup, que faire des m�thodes de la superclasse Personnage ?
  Je les garde, mais plut�t que de red�finir ces derni�res, la superclasse va invoquer la m�thode de comportement
   de chaque objet. Ainsi, je n'ai plus � red�finir ou � modifier mes classes. La seule chose qu'il reste � faire, 
   c'est d'affecter une instance de comportement � mes objets. Voir les packages personnage et comportement.
   
   Maintenat que j'ai d�fini des objets de comportements, je vais pouvoir remanier ma classe Personnage. J'ajoute
   les variables d'instance, les mutateurs et les constructeurs permettant d'initialiser mes objets.
   
   Conclusion : Que de changements avec le pattern strategy ! Gr�ce � ce dernier, je n'utilise plus de m�thodes
   d�finies dans ma hi�rarchie de classes, mais des impl�mentations concr�tes d'interfaces. Les m�thodes que 
   mes classes appellent utilisent chacune un objet de comportement. Je peux donc d�finir des guerriers, des civils,
   des m�decins... tous personnalisables, puisqu'il suffit de modifier l'instance de leur comportement pour que
   ceux-ci changent instantan�ment. De plus, je peux changer le comportement des instances avec le mutateur pr�sent
   dans la superclasse en lui passant une impl�mentation correspondante.
   Le plus beau, c'est qu'on peut instancier des objets Guerriers avec des comportements diff�rents.
   Donc, le pattern strategy permet de rendre une hi�rarchie de classes plus souple. On a vu qu'il valait mieux
   encapsuler des comportements plut�t que de les mettre d'office dans l'objet concern�.
  
  
  
 
 
 	
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
		//Loup l = obj; // Probl�me de r�f�rence
		Loup l = (Loup)obj; // transtypage
		Canin wolf = new Loup("gris", 70);
		wolf.boire(); //m�thode de la classe Animal
		wolf.deplacement(); // m�thode de la classe Canin
		wolf.manger(); // m�thode de la classe Animal
		wolf.crier(); // m�thode de la classe Loup
		System.out.println(wolf.toString());
		System.out.println("--------------------------------------------");
		
		Chien chien = new Chien("Gris bleut�", 45);
		//les m�thodes du Chien
		chien.boire();
		chien.manger();
		chien.deplacement();
		chien.crier();
		System.out.println(chien);
		System.out.println("--------------------------------------------");
		
		//les m�thodes de l'interface
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
