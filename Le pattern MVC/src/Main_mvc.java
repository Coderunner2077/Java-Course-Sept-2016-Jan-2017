import com.sdz.controler.AbstractControler;
import com.sdz.controler.CalculetteControler;
import com.sdz.model.AbstractModel;
import com.sdz.model.Calculator;
import com.sdz.vue.Calculette;

/*
 * 								Mieux structurer son code : le pattern MVC
 * 
 * Dans ce chapitre est étudié un des design patterns les plus connus : MVC. Grâce à ce derner,
 * on peut découper le code en trois parties : modèle, vue et contrôleur. C'est un pattern 
 * composé, ce qui signifie qu'il est constitué d'au moins deux patterns (voire plus). 
 * 
 * I./ Premiers pas
 * 
 * Contrairement à ce que j'ai l'habitude de faire, je procéderai directement à la découverte
 * du pattern : puisque le pattern MVC est plus complexe à aborder, je vais entrer directement
 * dans le vif du sujet. Le schéma (mvc.png) en décrit le principe. Avant d'expliquer ce schéma,
 * je vais d'abord me pencher sur ces trois entités (modèle, vue, contrôleur).
 * 
 * LA VUE : 
 * 		Ce que l'on nomme la vue est une IHM. Elle représente ce que l'utilisateur a sous les
 * 		yeux. La vue peut donc être :
 * 		=> une application graphique Swing, AWT, SWT pour Java (Form pour C#)
 * 		=> une page web
 * 		=> un terminal Linux ou une console Windows
 * 		=> etc.
 * 
 * LE MODELE :
 * 		Le modèle peut être divers et varié. C'est là que se trouvent les données. Il s'agit en
 * 		général d'un ou plusieurs objets Java. Ces objets s'apparentent généralement à ce qu'on
 * 		appelle la "couche métier" de l'application et effectuent des traitement absolument
 * 		transparents pour l'utilisateur. Par exemple, on peut citer des objets dont le rôle est
 * 		de gérer une ou plusiers tables d'une base de données. En trois mots, il s'agit du coeur
 * 		du programme.
 * 
 * LE CONTRÔLEUR : 
 * 		Cet objet - car il s'agit aussi d'un objet - permet de faire le lien entre la vue et
 * 		le modèle lorsqu'une action utilisateur est intevenue sur la vue. C'est cet objet qui
 * 		aura pour rôle de contrôler les données.
 * 
 * Maintenant que toute la lumière est faite sur les trois composants de ce pattern, étudions
 * la façon dont ce dernier travaille.
 * 
 * Note : afin de travailler sur un exemple concret, je vais reprendre ma calculatrice issue
 * d'un TP précédent.
 * 
 * Dans une application structurée en MVC, voici ce qu'il peut se passer :
 * 
 * 	-	L'utilisateur effectue une action sur ma calculatrice (un clic sur un bouton)
 * 	-	l'action est captée par le contrôleur, qui va vérifier la cohérence des données et 
 * 			éventuellement les transformer afin que le modèle les comprenne. Le contrôleur
 * 			peut aussi demander à la vue de changer
 * 	-	Le modèle reçoit les données et change d'état (une variable qui change par exemple)
 * 	-	le modèle notifie la vue (ou les vues) qu'il faut se mettre à jour
 * 	-	l'affichage dans la vue (ou les vues) est modifié en conqéquence en allant chercher 
 * 			l'état du modèle
 * 
 * Plus haut, j'ai écrit que le pattern MVC était composé : à ce stade de mon apprentissage, 
 * je peux isoler deux patterns dans cette architecture. Le pattern observer se trouve au 
 * niveau du modèle. Ainsi, lorsque celui-ci va changer d'état, tous les objets qui 
 * l'observent seront mis au courant automatiquement, et ce, avec un couplage faible !
 * Le deuxième est plus difficile à voir mais il s'agit du pattern strategy. Ce pattern est 
 * situé au niveau du contrôleur. On dit aussi que le contrôleur est la strategie (en référence
 * au pattern du même nom) de la vue. En fait, le contrôleur va transférer les données de 
 * l'utilisateur au modèle et il a tout à fait le droit de modifier le contenu.
 * 
 * Pour comprendre pourquoi utiliser le pattern strategy, il suffit de se rappeler la raison
 * d'être de ce pattern : encapsuler les morceaux de code qui changent !
 * En utilisant ce pattern, je préviens les risques potentiels de changement dans ma logique de
 * contrôle. Il me suffira d'utiliser une autre implémentation de mon contrôleur afin d'avoir
 * des contrôles différents.
 * 
 * Ceci dit, il faut tout de même savoir que le modèle et le contrôle sont intimement liés :
 * un objet contrôleur pour ma calculatrice ne servira que pour ma calculatrice ! Je peux
 * donc autoriser un couplage fort entre ces deux objets.
 * 
 * Il est temps de coder.
 * 
 * II./ Le modèle
 * 
 * Le modèle est l'objet chargé qui sera chargé de stocker les données nécessaires à un calcul
 * (nombre et opérateur) et d'avoir le résultat. Afin de prévoir un changement éventuel de
 * modèle, je créerai le mien à partir d'un supertype de modèle : de cette manière, si un
 * changement s'opère, je pourrai utiliser les différentes classes filles de façon polymorphe.
 * Avant de foncer tête baissée, mettons au point ce que le modèle doit être capable d'effec -
 * tuer. Pour réaliser des calculs simples, il devra:
 * 	-	récupérer et stocker au moins un nombre
 * 	-	stocker l'opérateur de calcul
 * 	-	calculer le résultat
 * 	-	renvoyer le résultat
 * 	-	tout remettre à zéro
 * 
 * Très bien, voici la liste des méthodes que je trouverai dans ma classe abstraite.
 * Je dois utiliser le pattern observer afin de faire communiquer mon modèle avec d'autres 
 * objets. Il me faudra donc une implémentation de ce pattern.
 * Réf package com.sdz.observer (interfaces Observer et Observable)
 * 
 * Ma classe abstraite devra donc implémenter ce pattern afin de centraliser les implémenta -
 * tions. Puisque mon supertype implémente le pattern observer, les classes héritant de 
 * cette dernière hériteront aussi des méthodes de ce pattern.
 * 
 * Réf com.sdz.model => AbstractModel
 * 
 * Créons à présent une classe concrète héritant de AbstractModel
 * Réf com.sdz.model => Calculator
 * 
 * III./ Le contrôleur
 * 
 * Celui-ci sera chargé de faire le lien entre ma vue et mon modèle. Je créerai aussi une 
 * classe abstraite afin de définir un supertype de variable pour utiliser, le cas échéant,
 * des contrôleurs de façon polymorphe.
 * Que doit faire mon contrôleur ? C'est lui qui va intercepter les actions de l'utilisateur,
 * qui va modeler les données et les envoyer au modèle. Il devra donc :
 * 
 * 	-	agir lors d'un clic sur un chiffre
 * 	-	agir lors d'un clic sur un opérateur
 * 	-	avertir le modèle pour qu'il se réinitialise dans le cas d'un clic sur un bouton reset
 * 	-	contrôler les données
 * 
 * Voilà donc ma liste des méthodes pour cet objet. Cependant, puisque mon contrôleur doit
 * interagir avec le modèle, il faudra qu'il possède une instance du modèle.
 * 
 * Réf com.sdz.controler => AbstractControler
 * 
 * J'ai défini les actions globales de mon objet de contrôle et je note qu'à chaque action de
 * mon contrôleur, celui-ci invoque la méthode control(). Celle-ci va vérifier les données
 * et informer le modèle en conséquence.
 * 
 * Voyons maintenant ce que doit effectuer mon instance concrète...
 * Réf com.sdz.controler => CalculetteControler
 * 
 * Ainsi, je peux voir que cette classe fille redéfinit la méthode control() et qu'elle permet 
 * d'indiquer les informations à envoyer à mon modèle. Celui-ci mis à jour, les données à
 * afficher dans la vue seront envoyées via l'implémentation du pattern observer entre mon
 * modèle et ma vue. D'ailleurs, il ne manque plus que celle-ci
 * 
 * IV./ La vue
 * 
 * La vue sera créée avec le package javax.swing.
 * Réf com.sdz.vue => Calculette
 * 
 * Constat : la vue contient le contrôleur (juste avant le constructeur de la classe).
 * 
 * Toutes mes classes sont à présent opérationnelles. Il ne me manque plus qu'une classe 
 * de test afin d'observer le résultat. Elle crée les trois composants qui vont dialoguer
 * entre eux : le modèle (données), la vue (fenêtre) et le contrôleur qui lie les deux.
 * 
 * Réf main
 * 
 * Le tout fonctionne très bien (ou presque...).
 * 
 * Je vais maintenant décortiquer ce qui se passe.
 * Lorsque je clique sur un chiffre :
 * 	-	L'action est envoyée au contrôleur
 * 	-	Celui-ci vérifie si le chiffre est conforme
 * 	-	Il informe le modèle
 * 	-	Ce dernier est mis à jour et informe la vue de ses changements
 * 	-	La vue raffrachît son affichage
 * 
 * Lorsque je clique sur un opérateur :
 * 	-	L'action est toujours envoyée au contrôleur
 * 	-	Celui-ci vérifie si l'opérateur envoyé est dans sa liste
 * 	-	Le cas échéant, il informe le modèle
 * 	-	Ce dernier agit en conséquence et informe la vue de son changement
 * 	-	La vue est mise à jour
 * 
 * Il se passera la même chose lorsque je cliquerai sur le bouton reset.
 * Bien sûr, j'aurai pu me passer du contrôleur, mais sans modèle ! En effet, la raison d'être
 * du design pattern est de prévenir des modifications de codes ! Avec une telle architecture, 
 * on peut bosser à trois en même temps sur le code : une personne sur la vue, une autre sur
 * le modèle et une 3e sur le contrôleur.
 * 
 * Attention, on peut émettre quelques réserves concernant ce pattern. Bien qu'il soit très
 * utile grâce à ses avantages à long terme, celui-ci complique grandement mon code et peut
 * le rendre très difficile à comprendre pour une personne extérieure à l'équipe de développe-
 * ment. Même si le design pattern permet de résoudre beaucoup de problèmes, il faut faire
 * attention à la "patternite aigüe" : son usage trop fréquent peut rendre le code
 * incompréhensible et son entretien impossible à réaliser.
 * 
 * REMARQUE : UNE FOIS LANCé DANS UN CALCUL, IMPOSSIBLE D'EFFECTUER UN NOUVEAU CALCUL SANS 
 * PASSER PAR RESET (améliorations nécessaires...)
 *  
 */
public class Main_mvc {

	public static void main(String[] args) {
		//instanciation de mon modèle
		AbstractModel calc = new Calculator();
		//Création du contrôleur
		AbstractControler controler = new CalculetteControler(calc);
		//Création de ma fenêtre avec le contrôleur en paramètre
		Calculette calculette = new Calculette(controler);
		//ajout de la fenêtre comme observer de mon modèle
		calc.addObserver(calculette);

	}

}
