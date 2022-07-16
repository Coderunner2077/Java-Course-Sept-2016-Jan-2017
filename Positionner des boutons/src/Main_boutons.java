/*
 * 									Positionner des boutons
 * 
 * Avant toute chose, il faut apprendre à positionner des composants dans une fenêtre. Il
 * me faut en effet gérer la façon dont le contenu est affiché dans une fenêtre.
 * 
 * I./ Utiliser la classe JButton
 * 
 * Je vais utiliser la classe JButton du package javax.swing. 
 * Je crée une classe Fenetre héritée de JFrame, sans la méthode go(), et j'y ajoute
 * une variable d'instance JPanel ainsi qu'une autre de type JButton. Je fais de JPanel
 * mon content pane, puis je définis le libellé (on parle aussi d'étiquette) de mon
 * bouton et je le mets sur ce qui me sert de content pane (en l'occurence JPanel).
 * Il existe deux possibilités pour attribuer un libellé à un bouton :
 * Possibilité 1 : instanciation avec le libellé =>
 * JButton bouton = new JButton("Mon bouton");
 * Possibilité 2 : instanciation puis définition du libellé =>
 * JButton bouton2 = new JButton();
 * bouton2.setText("Mon autre bouton");
 * 
 * Il ne me reste plus qu'à ajouter ce bouton sur mon content pane grâce à la méthode
 * add() de l'objet JPanel (avant de définir celui-ci comme mon content pane).
 * Le bouton est centré sur mon conteneur, et c'est normal, car par défaut, JPanel gère la
 * mise en page. En fait, il existe en Java des objets qui servent à agencer mes composants,
 * et JPanel en instancie un par défaut.
 * Maintenant, pour voir la diffèrence, je vais travailler directement sur le content
 * pane de mon JFrame, en appelant la méthode getContentPane(), couplée de add().
 * Et le résultat n'est pas du tout conséquent, car le bouton occupe toute la fenêtre !
 * Et c'est lié au mode de fonctionnement de l'objet d'agencement par défaut de mon objet
 * JFrame.
 * Faisons un petit tour d'horizon de ces objets, et voyons comment ils fonctionnent...
 * 
 * II./ Positionner son composant : les layout managers
 * 
 * Il existe plusieurs sortes de layout managers, plus ou moins simples à utiliser, dont
 * le rôle est de gèrer la position des éléments sur la fenêtre. Tous ces layout 
 * managers se trouvent dans java.awt
 * 
 * 1.) L'objet BorderLayout
 * 
 * Il s'agit du layout manager par défaut du content pane du JFrame. Il est très pratique
 * si je souhaite placer des objets par rapport à une position cardinale de mon conteneur.
 * En effet, je dois utiliser les valeurs NORTH, SOUTH, WEST, EAST ou encore CENTER.
 * Par exemple :
 * this.getContentPane().add(new JButton("bouton"), BorderLayout.EAST);
 * Réf Fenetre.
 * En règle générale, il faut d'abord définir le layout à utiliser avec la méthode 
 * setLayout() de l'objet JFrame. Ensuite, pour chaque composant que je souhaite positionner
 * avec add(), j'utilise en 2e paramètre un attribut static de la classe BorderLayout (dont
 * la liste est citée plus haut).
 * Utiliser l'objet BorderLayout soumet mes composants à certaines contraintes. Pour une
 * position NORTH ou SOUTH, la hauteur de mon composant sera proportionnelle à ma fenêtre,
 * mais il occupera toute la largeur; tandis qu'avec WEST et EAST, ce sera la largeur qui
 * sera proportionnelle alors que toute la hauteur sera occupée. Et bien entendu, CENTER
 * utilise tout l'espace restant. 
 * Note : CENTER est le layout par défaut du content pane de la fenêtre,
 * d'où la taille du bouton lorsque je l'ai ajouté au tout début.
 * 
 * 2.) L'objet GridLayout
 * 
 * Celui-ci permet d'ajouter des composants suivant une grille définie par un nombre de 
 * lignes et de colonnes. Les éléments sont situés à partir de la case en haut à gauche. 
 * Dès qu'une ligne est remplie, on passe à la suivante.
 * Réf Fenetre
 * En effet, pour définir une grille de trois lignes et 2 colonnes, je procède ainsi :
 * this.setLayout(new GridLayout(3, 2));
 * Et j'ajoute les boutons sans même avoir à définir le positionnement (contrairement à
 * BorderLayout).
 * Je peux également définir le nombre de lignes et de colonnes en utilisant ces 
 * méthodes :
 * GridLayout gl = new GridLayout();
 * gl.setColumns(2);
 * gl.setRows(3);
 * this.setLayout(gl);
 * 
 * Je peux aussi ajouter de l'espace entre les colonnes et les lignes :
 * GridLayout gl = new GridLayout(3, 2);
 * gl.setHgap(5); --> 5 pixels d'espace entre les colonnes (H pour Horizontal)
 * gl.setVgap(5); --> 5 pixels d'espace entre les lignes (V pour Vertical)
 * Ou en abrégé :
 * GridLayout gl = new GridLayout(3, 2, 5, 5);
 * 
 * 3.) L'objet BoxLayout
 * 
 * Grâce à lui, je peux ranger mes composants à la suite, soit sur une ligne, soit sur
 * une colonne. Réf Fenetre.
 * Ce code est simple : on crée 3 JPanel contenant chacun un certain nombre de JButton
 * rangés en ligne grâce à l'attribut LINE_AXIS. Ces 3 conteneurs créés, je les range dans
 * un 4e où, cette fois, je les agence dans une colonne grâce à l'attribut PAGE_AXIS. 
 * 
 * Rien de bien compliqué, mais je dois savoir qu'il existe un moyen encore plus simple 
 * d'utiliser ce layout : via l'objet Box. Ce dernier n'est rien d'autre qu'un conteneur
 * paramétré avec un BoxLayout. Réf Fenetre.
 * 
 * 4.) L'objet CardLayout
 * 
 * Je vais à présent pouvoir gérer mes conteneurs comme un tas de cartes (les uns sur les 
 * autres), et basculer d'un contenu à l'autre en deux temps, trois clics. Le principe est
 * d'assigner des conteneurs au layout en leur donnant un nom afin de les retrouver plus 
 * facilement, permettant de passer de l'un à l'autre sans effort.
 * Réf Fenetre
 * Note : on a utilisé des boutons pour passer d'un conteneur à un autre.
 * 
 * 5.) L'objet GridBagLayout
 * 
 * Cet objet est le + difficile.
 * Pour faire simple, ce layout se présente sous la forme d'une grille à la façon d'un
 * tableau excel (ou un tableau bidimensionnel) : je dois positionner mes composants en
 * me servant des coordonnées des cellules (qui sont définies lorsque je specifie leur 
 * nombre). Je dois aussi définir les marges et la façon dont mes composants se répliquent
 * dans les cellules... C'est plutôt dense comme gestion du positionnement. On rentrera 
 * pas dans les détails ici.
 * Je vais faire en sorte d'obtenir un tableau à quatre colonnes sur trois lignes.
 * Réf Fenetre
 * J'ai positionné 4 éléments sur la 1re ligne, spécifié que le 4e élément terminait 
 * celle-ci, puis j'ai placé un autre composant au début de la 2e ligne d'une hauteur de 
 * 2 cases, en informant le gestionnaire que celui-ci suivait directement la fin de la
 * 1re ligne. J'ai ajouté un composant de trois cases de long terminant la deuxième ligne,
 * pour passer ensuite à un composant de deux cases de long puis à un dernier achevant la
 * ligne.
 * Lorsque des composants se trouvent sur plusieurs cases, je dois spécifier la façon
 * dont ils s'étalent : horizontalement ou verticalement.
 * Je peux me rendre compte que c'est au niveau du GridBagConstraints (l'objet servant
 * à positionner les composants) que tout ce joue. Je peux utiliser ses différentes 
 * méthodes afin de positionner mes composants. En voici une liste :
 	-	gridx : position en x dans la grille
 	-	gridy : position en y dans la grille
 	-	gridwidth : nombre de colonnes occupées
 	-	gridheight : nombre de lignes occupées
 	-	weightx : si la grille est plus large que l'espace demandé, l'espace est 
 		redistribué proportionnellemnt aux valeurs de weightx des différentes colonnes.
 	-	weightY : si la grille est plus haute que l'espace demandé, l'espace est
 		redistribué proportionnellement aux valeurs de weigthy des différents lignes
 	-	anchor : ancrage du composant dans la cellule, ie son alignement dans la cellule
 		(en bas à droite, en haut à gauche...) Voici les différents valeurs utilisables:
 		=> FIRST_LINE_START : en haut à gauche
 		=> PAGE_START : en haut au centre
 		=> FIRST_LINE_END : en haut à droite
 		=> LINE_START : au milieu  à gauche
 		=> CENTER : au milieu et centré
 		=> LINE_END : au milieu à droite
 		=> LAST_LINE_START : en bas à gauche
 		=> PAGE_END : en bas au centre
 		=> LAST_LINE_END : en bas à droite
 		
 	-	fill : remplissage si la cellule est plus grande que le composant. Valeurs 
 		possibles : none, HORIZONTAL, VERTICAL, BOTH
 	-	insets : l'espace autour du composant. S'ajoute aux espacement définis par les 
 		propriétés ipadx et ipady ci-dessous
 	-	ipadx : espacement à gauche et à droite du composant
 	-	ipady : espacement au-dessus et au-dessous du composant
 		
 * Consulter le site d'Oracle s'il faut.
 * 
 * 6.) L'objet FlowLayout
 * 
 * C'est le plus facile : il se contente de centrer les composants dans le conteneur. 
 * En fait, c'est le layout défini par défaut dans les objets JPanel. Lorsque j'insère
 * plusieurs composants dans ce gestionnaire, il passe à la ligne suivante dès que la
 * place est trop étroite.
 * Réf Fenetre
 * 
 * 
 * III./ Insérer un bouton dans l'animation du chapitre précédent.
 * 
 * Les IHM ne sont en fait qu'une imbrication de composants positionnés grâce à des 
 * layout managers. 
 * Pour illustrer cela, je vais reprendre l'animation avec le ballon et y insérer un 
 * bouton en bas.
 * Réf Fenetre du "Le fil rouge - une animation".
 * 
 * 
 * Attention, la liste des layout managers étudiées ici n'est pas exhaustive.
 */
public class Main_boutons {

	public static void main(String[] args) {
		Fenetre fen = new Fenetre();

	}

}
