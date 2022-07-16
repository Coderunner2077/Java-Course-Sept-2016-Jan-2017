/*
 * 									Positionner des boutons
 * 
 * Avant toute chose, il faut apprendre � positionner des composants dans une fen�tre. Il
 * me faut en effet g�rer la fa�on dont le contenu est affich� dans une fen�tre.
 * 
 * I./ Utiliser la classe JButton
 * 
 * Je vais utiliser la classe JButton du package javax.swing. 
 * Je cr�e une classe Fenetre h�rit�e de JFrame, sans la m�thode go(), et j'y ajoute
 * une variable d'instance JPanel ainsi qu'une autre de type JButton. Je fais de JPanel
 * mon content pane, puis je d�finis le libell� (on parle aussi d'�tiquette) de mon
 * bouton et je le mets sur ce qui me sert de content pane (en l'occurence JPanel).
 * Il existe deux possibilit�s pour attribuer un libell� � un bouton :
 * Possibilit� 1 : instanciation avec le libell� =>
 * JButton bouton = new JButton("Mon bouton");
 * Possibilit� 2 : instanciation puis d�finition du libell� =>
 * JButton bouton2 = new JButton();
 * bouton2.setText("Mon autre bouton");
 * 
 * Il ne me reste plus qu'� ajouter ce bouton sur mon content pane gr�ce � la m�thode
 * add() de l'objet JPanel (avant de d�finir celui-ci comme mon content pane).
 * Le bouton est centr� sur mon conteneur, et c'est normal, car par d�faut, JPanel g�re la
 * mise en page. En fait, il existe en Java des objets qui servent � agencer mes composants,
 * et JPanel en instancie un par d�faut.
 * Maintenant, pour voir la diff�rence, je vais travailler directement sur le content
 * pane de mon JFrame, en appelant la m�thode getContentPane(), coupl�e de add().
 * Et le r�sultat n'est pas du tout cons�quent, car le bouton occupe toute la fen�tre !
 * Et c'est li� au mode de fonctionnement de l'objet d'agencement par d�faut de mon objet
 * JFrame.
 * Faisons un petit tour d'horizon de ces objets, et voyons comment ils fonctionnent...
 * 
 * II./ Positionner son composant : les layout managers
 * 
 * Il existe plusieurs sortes de layout managers, plus ou moins simples � utiliser, dont
 * le r�le est de g�rer la position des �l�ments sur la fen�tre. Tous ces layout 
 * managers se trouvent dans java.awt
 * 
 * 1.) L'objet BorderLayout
 * 
 * Il s'agit du layout manager par d�faut du content pane du JFrame. Il est tr�s pratique
 * si je souhaite placer des objets par rapport � une position cardinale de mon conteneur.
 * En effet, je dois utiliser les valeurs NORTH, SOUTH, WEST, EAST ou encore CENTER.
 * Par exemple :
 * this.getContentPane().add(new JButton("bouton"), BorderLayout.EAST);
 * R�f Fenetre.
 * En r�gle g�n�rale, il faut d'abord d�finir le layout � utiliser avec la m�thode 
 * setLayout() de l'objet JFrame. Ensuite, pour chaque composant que je souhaite positionner
 * avec add(), j'utilise en 2e param�tre un attribut static de la classe BorderLayout (dont
 * la liste est cit�e plus haut).
 * Utiliser l'objet BorderLayout soumet mes composants � certaines contraintes. Pour une
 * position NORTH ou SOUTH, la hauteur de mon composant sera proportionnelle � ma fen�tre,
 * mais il occupera toute la largeur; tandis qu'avec WEST et EAST, ce sera la largeur qui
 * sera proportionnelle alors que toute la hauteur sera occup�e. Et bien entendu, CENTER
 * utilise tout l'espace restant. 
 * Note : CENTER est le layout par d�faut du content pane de la fen�tre,
 * d'o� la taille du bouton lorsque je l'ai ajout� au tout d�but.
 * 
 * 2.) L'objet GridLayout
 * 
 * Celui-ci permet d'ajouter des composants suivant une grille d�finie par un nombre de 
 * lignes et de colonnes. Les �l�ments sont situ�s � partir de la case en haut � gauche. 
 * D�s qu'une ligne est remplie, on passe � la suivante.
 * R�f Fenetre
 * En effet, pour d�finir une grille de trois lignes et 2 colonnes, je proc�de ainsi :
 * this.setLayout(new GridLayout(3, 2));
 * Et j'ajoute les boutons sans m�me avoir � d�finir le positionnement (contrairement �
 * BorderLayout).
 * Je peux �galement d�finir le nombre de lignes et de colonnes en utilisant ces 
 * m�thodes :
 * GridLayout gl = new GridLayout();
 * gl.setColumns(2);
 * gl.setRows(3);
 * this.setLayout(gl);
 * 
 * Je peux aussi ajouter de l'espace entre les colonnes et les lignes :
 * GridLayout gl = new GridLayout(3, 2);
 * gl.setHgap(5); --> 5 pixels d'espace entre les colonnes (H pour Horizontal)
 * gl.setVgap(5); --> 5 pixels d'espace entre les lignes (V pour Vertical)
 * Ou en abr�g� :
 * GridLayout gl = new GridLayout(3, 2, 5, 5);
 * 
 * 3.) L'objet BoxLayout
 * 
 * Gr�ce � lui, je peux ranger mes composants � la suite, soit sur une ligne, soit sur
 * une colonne. R�f Fenetre.
 * Ce code est simple : on cr�e 3 JPanel contenant chacun un certain nombre de JButton
 * rang�s en ligne gr�ce � l'attribut LINE_AXIS. Ces 3 conteneurs cr��s, je les range dans
 * un 4e o�, cette fois, je les agence dans une colonne gr�ce � l'attribut PAGE_AXIS. 
 * 
 * Rien de bien compliqu�, mais je dois savoir qu'il existe un moyen encore plus simple 
 * d'utiliser ce layout : via l'objet Box. Ce dernier n'est rien d'autre qu'un conteneur
 * param�tr� avec un BoxLayout. R�f Fenetre.
 * 
 * 4.) L'objet CardLayout
 * 
 * Je vais � pr�sent pouvoir g�rer mes conteneurs comme un tas de cartes (les uns sur les 
 * autres), et basculer d'un contenu � l'autre en deux temps, trois clics. Le principe est
 * d'assigner des conteneurs au layout en leur donnant un nom afin de les retrouver plus 
 * facilement, permettant de passer de l'un � l'autre sans effort.
 * R�f Fenetre
 * Note : on a utilis� des boutons pour passer d'un conteneur � un autre.
 * 
 * 5.) L'objet GridBagLayout
 * 
 * Cet objet est le + difficile.
 * Pour faire simple, ce layout se pr�sente sous la forme d'une grille � la fa�on d'un
 * tableau excel (ou un tableau bidimensionnel) : je dois positionner mes composants en
 * me servant des coordonn�es des cellules (qui sont d�finies lorsque je specifie leur 
 * nombre). Je dois aussi d�finir les marges et la fa�on dont mes composants se r�pliquent
 * dans les cellules... C'est plut�t dense comme gestion du positionnement. On rentrera 
 * pas dans les d�tails ici.
 * Je vais faire en sorte d'obtenir un tableau � quatre colonnes sur trois lignes.
 * R�f Fenetre
 * J'ai positionn� 4 �l�ments sur la 1re ligne, sp�cifi� que le 4e �l�ment terminait 
 * celle-ci, puis j'ai plac� un autre composant au d�but de la 2e ligne d'une hauteur de 
 * 2 cases, en informant le gestionnaire que celui-ci suivait directement la fin de la
 * 1re ligne. J'ai ajout� un composant de trois cases de long terminant la deuxi�me ligne,
 * pour passer ensuite � un composant de deux cases de long puis � un dernier achevant la
 * ligne.
 * Lorsque des composants se trouvent sur plusieurs cases, je dois sp�cifier la fa�on
 * dont ils s'�talent : horizontalement ou verticalement.
 * Je peux me rendre compte que c'est au niveau du GridBagConstraints (l'objet servant
 * � positionner les composants) que tout ce joue. Je peux utiliser ses diff�rentes 
 * m�thodes afin de positionner mes composants. En voici une liste :
 	-	gridx : position en x dans la grille
 	-	gridy : position en y dans la grille
 	-	gridwidth : nombre de colonnes occup�es
 	-	gridheight : nombre de lignes occup�es
 	-	weightx : si la grille est plus large que l'espace demand�, l'espace est 
 		redistribu� proportionnellemnt aux valeurs de weightx des diff�rentes colonnes.
 	-	weightY : si la grille est plus haute que l'espace demand�, l'espace est
 		redistribu� proportionnellement aux valeurs de weigthy des diff�rents lignes
 	-	anchor : ancrage du composant dans la cellule, ie son alignement dans la cellule
 		(en bas � droite, en haut � gauche...) Voici les diff�rents valeurs utilisables:
 		=> FIRST_LINE_START : en haut � gauche
 		=> PAGE_START : en haut au centre
 		=> FIRST_LINE_END : en haut � droite
 		=> LINE_START : au milieu  � gauche
 		=> CENTER : au milieu et centr�
 		=> LINE_END : au milieu � droite
 		=> LAST_LINE_START : en bas � gauche
 		=> PAGE_END : en bas au centre
 		=> LAST_LINE_END : en bas � droite
 		
 	-	fill : remplissage si la cellule est plus grande que le composant. Valeurs 
 		possibles : none, HORIZONTAL, VERTICAL, BOTH
 	-	insets : l'espace autour du composant. S'ajoute aux espacement d�finis par les 
 		propri�t�s ipadx et ipady ci-dessous
 	-	ipadx : espacement � gauche et � droite du composant
 	-	ipady : espacement au-dessus et au-dessous du composant
 		
 * Consulter le site d'Oracle s'il faut.
 * 
 * 6.) L'objet FlowLayout
 * 
 * C'est le plus facile : il se contente de centrer les composants dans le conteneur. 
 * En fait, c'est le layout d�fini par d�faut dans les objets JPanel. Lorsque j'ins�re
 * plusieurs composants dans ce gestionnaire, il passe � la ligne suivante d�s que la
 * place est trop �troite.
 * R�f Fenetre
 * 
 * 
 * III./ Ins�rer un bouton dans l'animation du chapitre pr�c�dent.
 * 
 * Les IHM ne sont en fait qu'une imbrication de composants positionn�s gr�ce � des 
 * layout managers. 
 * Pour illustrer cela, je vais reprendre l'animation avec le ballon et y ins�rer un 
 * bouton en bas.
 * R�f Fenetre du "Le fil rouge - une animation".
 * 
 * 
 * Attention, la liste des layout managers �tudi�es ici n'est pas exhaustive.
 */
public class Main_boutons {

	public static void main(String[] args) {
		Fenetre fen = new Fenetre();

	}

}
