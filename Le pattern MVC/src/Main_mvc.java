import com.sdz.controler.AbstractControler;
import com.sdz.controler.CalculetteControler;
import com.sdz.model.AbstractModel;
import com.sdz.model.Calculator;
import com.sdz.vue.Calculette;

/*
 * 								Mieux structurer son code : le pattern MVC
 * 
 * Dans ce chapitre est �tudi� un des design patterns les plus connus : MVC. Gr�ce � ce derner,
 * on peut d�couper le code en trois parties : mod�le, vue et contr�leur. C'est un pattern 
 * compos�, ce qui signifie qu'il est constitu� d'au moins deux patterns (voire plus). 
 * 
 * I./ Premiers pas
 * 
 * Contrairement � ce que j'ai l'habitude de faire, je proc�derai directement � la d�couverte
 * du pattern : puisque le pattern MVC est plus complexe � aborder, je vais entrer directement
 * dans le vif du sujet. Le sch�ma (mvc.png) en d�crit le principe. Avant d'expliquer ce sch�ma,
 * je vais d'abord me pencher sur ces trois entit�s (mod�le, vue, contr�leur).
 * 
 * LA VUE : 
 * 		Ce que l'on nomme la vue est une IHM. Elle repr�sente ce que l'utilisateur a sous les
 * 		yeux. La vue peut donc �tre :
 * 		=> une application graphique Swing, AWT, SWT pour Java (Form pour C#)
 * 		=> une page web
 * 		=> un terminal Linux ou une console Windows
 * 		=> etc.
 * 
 * LE MODELE :
 * 		Le mod�le peut �tre divers et vari�. C'est l� que se trouvent les donn�es. Il s'agit en
 * 		g�n�ral d'un ou plusieurs objets Java. Ces objets s'apparentent g�n�ralement � ce qu'on
 * 		appelle la "couche m�tier" de l'application et effectuent des traitement absolument
 * 		transparents pour l'utilisateur. Par exemple, on peut citer des objets dont le r�le est
 * 		de g�rer une ou plusiers tables d'une base de donn�es. En trois mots, il s'agit du coeur
 * 		du programme.
 * 
 * LE CONTR�LEUR : 
 * 		Cet objet - car il s'agit aussi d'un objet - permet de faire le lien entre la vue et
 * 		le mod�le lorsqu'une action utilisateur est intevenue sur la vue. C'est cet objet qui
 * 		aura pour r�le de contr�ler les donn�es.
 * 
 * Maintenant que toute la lumi�re est faite sur les trois composants de ce pattern, �tudions
 * la fa�on dont ce dernier travaille.
 * 
 * Note : afin de travailler sur un exemple concret, je vais reprendre ma calculatrice issue
 * d'un TP pr�c�dent.
 * 
 * Dans une application structur�e en MVC, voici ce qu'il peut se passer :
 * 
 * 	-	L'utilisateur effectue une action sur ma calculatrice (un clic sur un bouton)
 * 	-	l'action est capt�e par le contr�leur, qui va v�rifier la coh�rence des donn�es et 
 * 			�ventuellement les transformer afin que le mod�le les comprenne. Le contr�leur
 * 			peut aussi demander � la vue de changer
 * 	-	Le mod�le re�oit les donn�es et change d'�tat (une variable qui change par exemple)
 * 	-	le mod�le notifie la vue (ou les vues) qu'il faut se mettre � jour
 * 	-	l'affichage dans la vue (ou les vues) est modifi� en conq�quence en allant chercher 
 * 			l'�tat du mod�le
 * 
 * Plus haut, j'ai �crit que le pattern MVC �tait compos� : � ce stade de mon apprentissage, 
 * je peux isoler deux patterns dans cette architecture. Le pattern observer se trouve au 
 * niveau du mod�le. Ainsi, lorsque celui-ci va changer d'�tat, tous les objets qui 
 * l'observent seront mis au courant automatiquement, et ce, avec un couplage faible !
 * Le deuxi�me est plus difficile � voir mais il s'agit du pattern strategy. Ce pattern est 
 * situ� au niveau du contr�leur. On dit aussi que le contr�leur est la strategie (en r�f�rence
 * au pattern du m�me nom) de la vue. En fait, le contr�leur va transf�rer les donn�es de 
 * l'utilisateur au mod�le et il a tout � fait le droit de modifier le contenu.
 * 
 * Pour comprendre pourquoi utiliser le pattern strategy, il suffit de se rappeler la raison
 * d'�tre de ce pattern : encapsuler les morceaux de code qui changent !
 * En utilisant ce pattern, je pr�viens les risques potentiels de changement dans ma logique de
 * contr�le. Il me suffira d'utiliser une autre impl�mentation de mon contr�leur afin d'avoir
 * des contr�les diff�rents.
 * 
 * Ceci dit, il faut tout de m�me savoir que le mod�le et le contr�le sont intimement li�s :
 * un objet contr�leur pour ma calculatrice ne servira que pour ma calculatrice ! Je peux
 * donc autoriser un couplage fort entre ces deux objets.
 * 
 * Il est temps de coder.
 * 
 * II./ Le mod�le
 * 
 * Le mod�le est l'objet charg� qui sera charg� de stocker les donn�es n�cessaires � un calcul
 * (nombre et op�rateur) et d'avoir le r�sultat. Afin de pr�voir un changement �ventuel de
 * mod�le, je cr�erai le mien � partir d'un supertype de mod�le : de cette mani�re, si un
 * changement s'op�re, je pourrai utiliser les diff�rentes classes filles de fa�on polymorphe.
 * Avant de foncer t�te baiss�e, mettons au point ce que le mod�le doit �tre capable d'effec -
 * tuer. Pour r�aliser des calculs simples, il devra:
 * 	-	r�cup�rer et stocker au moins un nombre
 * 	-	stocker l'op�rateur de calcul
 * 	-	calculer le r�sultat
 * 	-	renvoyer le r�sultat
 * 	-	tout remettre � z�ro
 * 
 * Tr�s bien, voici la liste des m�thodes que je trouverai dans ma classe abstraite.
 * Je dois utiliser le pattern observer afin de faire communiquer mon mod�le avec d'autres 
 * objets. Il me faudra donc une impl�mentation de ce pattern.
 * R�f package com.sdz.observer (interfaces Observer et Observable)
 * 
 * Ma classe abstraite devra donc impl�menter ce pattern afin de centraliser les impl�menta -
 * tions. Puisque mon supertype impl�mente le pattern observer, les classes h�ritant de 
 * cette derni�re h�riteront aussi des m�thodes de ce pattern.
 * 
 * R�f com.sdz.model => AbstractModel
 * 
 * Cr�ons � pr�sent une classe concr�te h�ritant de AbstractModel
 * R�f com.sdz.model => Calculator
 * 
 * III./ Le contr�leur
 * 
 * Celui-ci sera charg� de faire le lien entre ma vue et mon mod�le. Je cr�erai aussi une 
 * classe abstraite afin de d�finir un supertype de variable pour utiliser, le cas �ch�ant,
 * des contr�leurs de fa�on polymorphe.
 * Que doit faire mon contr�leur ? C'est lui qui va intercepter les actions de l'utilisateur,
 * qui va modeler les donn�es et les envoyer au mod�le. Il devra donc :
 * 
 * 	-	agir lors d'un clic sur un chiffre
 * 	-	agir lors d'un clic sur un op�rateur
 * 	-	avertir le mod�le pour qu'il se r�initialise dans le cas d'un clic sur un bouton reset
 * 	-	contr�ler les donn�es
 * 
 * Voil� donc ma liste des m�thodes pour cet objet. Cependant, puisque mon contr�leur doit
 * interagir avec le mod�le, il faudra qu'il poss�de une instance du mod�le.
 * 
 * R�f com.sdz.controler => AbstractControler
 * 
 * J'ai d�fini les actions globales de mon objet de contr�le et je note qu'� chaque action de
 * mon contr�leur, celui-ci invoque la m�thode control(). Celle-ci va v�rifier les donn�es
 * et informer le mod�le en cons�quence.
 * 
 * Voyons maintenant ce que doit effectuer mon instance concr�te...
 * R�f com.sdz.controler => CalculetteControler
 * 
 * Ainsi, je peux voir que cette classe fille red�finit la m�thode control() et qu'elle permet 
 * d'indiquer les informations � envoyer � mon mod�le. Celui-ci mis � jour, les donn�es �
 * afficher dans la vue seront envoy�es via l'impl�mentation du pattern observer entre mon
 * mod�le et ma vue. D'ailleurs, il ne manque plus que celle-ci
 * 
 * IV./ La vue
 * 
 * La vue sera cr��e avec le package javax.swing.
 * R�f com.sdz.vue => Calculette
 * 
 * Constat : la vue contient le contr�leur (juste avant le constructeur de la classe).
 * 
 * Toutes mes classes sont � pr�sent op�rationnelles. Il ne me manque plus qu'une classe 
 * de test afin d'observer le r�sultat. Elle cr�e les trois composants qui vont dialoguer
 * entre eux : le mod�le (donn�es), la vue (fen�tre) et le contr�leur qui lie les deux.
 * 
 * R�f main
 * 
 * Le tout fonctionne tr�s bien (ou presque...).
 * 
 * Je vais maintenant d�cortiquer ce qui se passe.
 * Lorsque je clique sur un chiffre :
 * 	-	L'action est envoy�e au contr�leur
 * 	-	Celui-ci v�rifie si le chiffre est conforme
 * 	-	Il informe le mod�le
 * 	-	Ce dernier est mis � jour et informe la vue de ses changements
 * 	-	La vue raffrach�t son affichage
 * 
 * Lorsque je clique sur un op�rateur :
 * 	-	L'action est toujours envoy�e au contr�leur
 * 	-	Celui-ci v�rifie si l'op�rateur envoy� est dans sa liste
 * 	-	Le cas �ch�ant, il informe le mod�le
 * 	-	Ce dernier agit en cons�quence et informe la vue de son changement
 * 	-	La vue est mise � jour
 * 
 * Il se passera la m�me chose lorsque je cliquerai sur le bouton reset.
 * Bien s�r, j'aurai pu me passer du contr�leur, mais sans mod�le ! En effet, la raison d'�tre
 * du design pattern est de pr�venir des modifications de codes ! Avec une telle architecture, 
 * on peut bosser � trois en m�me temps sur le code : une personne sur la vue, une autre sur
 * le mod�le et une 3e sur le contr�leur.
 * 
 * Attention, on peut �mettre quelques r�serves concernant ce pattern. Bien qu'il soit tr�s
 * utile gr�ce � ses avantages � long terme, celui-ci complique grandement mon code et peut
 * le rendre tr�s difficile � comprendre pour une personne ext�rieure � l'�quipe de d�veloppe-
 * ment. M�me si le design pattern permet de r�soudre beaucoup de probl�mes, il faut faire
 * attention � la "patternite aig�e" : son usage trop fr�quent peut rendre le code
 * incompr�hensible et son entretien impossible � r�aliser.
 * 
 * REMARQUE : UNE FOIS LANC� DANS UN CALCUL, IMPOSSIBLE D'EFFECTUER UN NOUVEAU CALCUL SANS 
 * PASSER PAR RESET (am�liorations n�cessaires...)
 *  
 */
public class Main_mvc {

	public static void main(String[] args) {
		//instanciation de mon mod�le
		AbstractModel calc = new Calculator();
		//Cr�ation du contr�leur
		AbstractControler controler = new CalculetteControler(calc);
		//Cr�ation de ma fen�tre avec le contr�leur en param�tre
		Calculette calculette = new Calculette(controler);
		//ajout de la fen�tre comme observer de mon mod�le
		calc.addObserver(calculette);

	}

}
