/*
 * 									 Ma première fenêtre
 * 
 * Dans cette partie, j'aborderai les interfaces graphiques (on parle aussi d'IHM pour 
 * Interface Homme Machine ou de GUI pour Graphical User Interfaces) et par extension, 
 * la programmation événementielle. C'est-à-dire que mon prog ne réagira plus à des 
 * saisies au clavier, mais à des événements provenant d'un composant graphique : un bouton,
 * une liste, un menu...
 * Le langage Java propose différentes bibliothèques pour programmer des IHM, mais ici, 
 * j'utiliserai essentiellement les packages javax.swing et java.awt présentes d'office
 * dans Java. Notamment, j'apprendrai ici à utiliser l'objet JFrame, présent dans le
 * package javax.swing.
 * Une fenêtre n'est en réalité qu'une multitude de composants posés les uns sur les 
 * autres.
 * 
 * I./ L'objet JFrame
 * 1.) Points de repères sur swing et awt
 * Tout d'abord, quelques points de repère sur javax.swing et java.awt... Dans ce cours,
 * je traiterai de ces deux derniers, mais je n'utiliserai pas de composants awt, je 
 * travaillerai uniquement avec des composants swing. Par exemple, un composant peut
 * être représenté par un bouton, une zone de texte, une case à cocher, etc.
 * Afin de mieux comprendre comment tout cela fonctionne, je dois savoir que lorsque le 
 * langage Java a vu le jour, dans sa version 1.0, seul awt était utilisable; swing
 * n'existait pas, il est apparu dans la version 1.2 de Java (aussi appelé Java 2). Les
 * composants awt sont considérés comme lourds (on dit aussi HeavyWeight) car ils sont
 * fortement liés au système d'exploitation qui les gère. Les composants swing, eux, sont
 * dessinés dans un conteneur, car ils sont légers (on dit LightWeight); ils n'ont pas
 * le même rendu à l'affichage, car ce n'est plus le système d'exploitation qui les gère.
 * Il existe également d'autres différences, comme le nombre de composants utilisables,
 * la gestion des bordures...
 * Pour toutes ces raisons, il est fortement recommandé de ne pas mélanger les composants
 * swing et awt dans une même fenêtre; cela pourrait occasionner des conflits ! Si j'associe
 * les deux, j'aurai de très grandes difficultés à développer une IHM stable et valide. En
 * effet, swing et awt ont les mêmes fondements, mais diffèrent dans leur utilisation.
 * 
 * 2.) Création d'une fenêtre
 * 
 * Pour utiliser une fenêtre de type JFrame, je dois l'instancier comme ceci :
 * JFrame fenetre = new JFrame();
 * Et ce, sans oublier d'importer javax.swing.JFrame
 * MAIS, lorsque j'exécute ce code, je n'obtiens rien, et c'est normal => par défaut,
 * JFrame n'est pas visible, je dois la rendre visible de cette manière :
 * fenetre.setVisible(true);
 * Le résultat est, pour le moins, petit : je n'obtiens qu'une toute petite fenêtre...
 * Voici les méthodes pour obtenir une fenêtre plus conséquente :
 * 		-	setTitle(String titre) : définit un titre pour la fenêtre
 * 		-	setSize(int largeur, int hauteur) : définit la taille en pixels
 * 		-	setLocationRelativeTo(null) : pour positionner la fenêtre au centre
 * 		-	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) : termine le processus lorsqu'on
 * 			clique sur la croix rouge
 * 		-	setVisible : pour rendre la fenêtre visible
 * 
 * Afin de ne pas avoir à redéfinir les attributs à chaque fois, il serait intéressant 
 * d'avoir son propre objet. 
 * Donc, au lieu d'instancier JFrame dans main, je vais créer une classe que je vais 
 * appeler Fenetre et la faire hériter de JFrame. J'écrirai toutes les méthodes
 * ci-dessus directement dans le constructeur de la classe Fenetre.
 * Après j'ai le choix, soit je conserve la classe contenant la méthode main et je crée
 * une instance de Fenetre, soit j'efface la classe contenant la méthode main et je 
 * place celle-ci directement dans ma classe Fenetre. Mais dans tous les cas, je dois 
 * créer une instance de ma Fenetre.
 * Ainsi, il est tout de même plus pratique de ne pas avoir à réécrire les mêmes 
 * instructions à chaque fois.
 * 
 * 3.) D'autres méthodes utiles de l'objet JFrame
 * a./ Positionner la fenêtre à l'écran
 * 
 * Pour positionner la fenêtre ailleurs qu'au centre, je peux utiliser la méthode 
 * setLocation(int x, int y). Grâce à cette méthode, je peux spésicier où doit se situer
 * ma fenêtre sur l'écran. Les coordonnées, exprimées en pixels, sont basées sur un 
 * repère dont l'origine est représentée par le coin supérieur gauche de l'écran.
 * Les valeurs négatives font sortir la fenêtre de l'écran, les coordonnées du 
 * point au coin supérieur gauche de l'écran étant (0, 0).
 * 
 * b./ Empêcher le redimensionnement de la fenêtre 
 * 
 * Pour cela, il suffit d'invoquer la méthode setResizable(boolean b) : false empêche
 * le redimensionnement, tandis que true l'autorise.
 * 
 * c./ Garder la fenêtre au premier plan
 * 
 * Il s'agit là encore d'une méthode qui prend un boléen en paramètre. Passer true laissera
 * la fenêtre au premier plan quoiqu'il advienne, false annulera cela. Cette méthode est:
 * setAlwaysOnTop(boolean b).
 * 
 * d./ Retirer les contours et les boutons de contrôle
 * 
 * Pour faire cela, il faut utiliser la méthode setUndecorated(boolean b)
 * 
 * 4.) La constitution d'une fenêtre Java
 * 
 * Ma fenêtre, telle qu'elle apparaît, cache quelques petites choses...
 * Je pensais, que ma fenêtre était tout simple, dépourvue de tout composant (hormis les 
 * contours). Eh bien, je me trompais ! Une JFrame est découpée en plusieurs parties
 * supérposées.
 * Une JFrame est constituée, dans l'ordre, de (Réf composants_JFrame.png) :
 * 		-	la fenêtre elle-même
 * 		-	le RootPane (en vert), le conteneur principal qui contient tous les autres
 * 			composants
 * 		-	le LayeredPane (en violet), qui forme juste un panneau composé du conteneur
 * 			global et de la barre du menu (MenuBar)
 * 		-	la MenuBar (en orange), la barre du menu, quand il y en a une
 * 		-	le content pane (en rose, situé en bas de la MenuBar), c'est dans celui-ci
 * 			que je placerai tous mes composants
 * 		-	le GlassPane (en transparence), couche utilisée pour intercepter les actions
 * 			de l'utilisateur avant qu'elles ne parviennent aux composants
 * 
 * Pas de panique, je m'en servirai uniquement du content pane. Pour le récupérer, il
 * me suffit d'utiliser la méthode getContentPane() de la classe JFrame. Cependant, je
 * vais utiliser un composant autre que le content pane : un JPanel dans lequel
 * j'insérerai mes composants.
 * 
 * Note : il existe d'autres types de fenêtre : la JWindow, une JFrame sans bordure et
 * non draggable (déplaçable), et la JDialog, une fenêtre non redimensionnable. Ignored.
 * 
 * II./ L'objet JPanel
 * 
 * Un JPanel est un composant de type conteneur dont la vocation est d'accueillir d'autres
 * objets de même type ou des objets de type composant (boutons, cases à cocher...)
 * Voici la marche à suivre :
 i.) importer la classe javax.swing.JPanel dans ma classe héritée de JFrame
 ii.) instancier un JPanel (dans le constructeur de ma Fenetre) puis lui spécifier une 
 couleur de fond pour mieux le distinguer. 
 ==> Attention, faudra importer java.awt.Color
 ==> la méthode (de l'objet JPanel) setBackground(Color.Orange) pour définir la couleur...
 iii.) Avertir ma JFrame que ce sera ma JPanel qui constituera sont content pane
 ==> avec la méthode (de l'objet Fenetre) setContentPane(JPanel pan) 
 *
 * Passons maintenant aux choses plus intéressantes...
 *
 * III./ Les objets Graphcics et Graphics2D
 * 1.) L'objet Graphics
 * 
 * Cet objet a une particularité de taille : je ne peux l'utiliser que si et seulement si
 * le système me l'a donné via la méthode getGraphics() d'un composant swing ! Pour bien
 * comprendre le fonctionnement de mes futurs conteneurs (ou composants), je vais créer 
 * une classe héritée de JPanel : disons Panneau. Réf Panneau
 * La méthode fillOval() de l'objet Graphics invoquée dans la méthode paintComponent(Graphics g)
 * de l'objet Panneau est celle que l'objet appelle pour se dessiner sur ma fenêtre; si 
 * je réduis cette dernière et que je l'affiche de nouveau, c'est encore cette méthode
 * qui est appelée pour afficher mon composant. Idem si je redimensionne ma fenêtre... De
 * plus, je n'ai même pas besoin de redéfinir un constructeur car cette méthode est 
 * appelée automatiquement !
 * C'est très pratique pour personnaliser des composants, car je n'aurai JAMAIS à
 * l'appeler moi-même : c'est automatique ! Tout ce que je peux faire, c'est forcer
 * l'objet à se repeindre; ce n'est toutefois pas cette méthode que j'invoquerai (à voir
 * plus loin, plus tard).
 * L'intérêt de disposer d'une classe héritée  d'un conteneur ou d'un composant, c'est que
 * je peux redéfinir la façon dont est peint ce composant sur la fenêtre.
 * 
 * 2.) Les méthodes de l'objet Graphics
 * a./ Dessiner un rond rempli
 * 
 * En effet, l'objet Graphics permet, entre autres, de tracer des ronds; mais il possède
 * tout un tas de méthodes plus pratiques et plus amusantes les unes que les autres...
 * Pour commencer, reprenons la méthode utilisée précédemment : g.fillOval(20, 20, 75, 75).
 * Voici son prototype : 
 * public abstract void fillOval(int x, int y, int width, int height);
 * ... le point de repère étant le coin supérieur gauche de la fenêtre créée.
 * 
 * Et pour avoir un rond centré : c'est dans ce genre de cas qu'il est intéressant 
 * d'utiliser une classe héritée. Puisque je suis dans mon objet JPanel, j'ai accès à ses
 * données lorsque je les dessine.
 * En effet, il existe des méthodes dans les objets composants qui retournent leur largeur
 * (getWidth()), ainsi que leur hauteur (getHeight()). Réf Panneau
 * 
 * b./ Dessiner un rond vide
 * 
 * C'est la méthode drawOval() qui permet de dessiner un rond vide. Elle fonctionne
 * exactement de la même manière que fillOval()
 * Réf Panneau
 * 
 * c./ Méthodes se rapportant à des rectangles
 * 
 * Pour dessiner des rectangles vides ==> drawRect()
 * Pour dessiner un rectangle rempli ==> fillRect()
 * Même fonctionnement que pour draw/fillOval
 * 
 * d./ Dessiner un rectangle aux ongles arrondis
 * 
 * ==> Les méthodes drawRoundRect() / fillRoundRect()
 * Il s'agit du même élément que précédemment, hormis le fait que le rectangle soit 
 * arrondi. L'arrondi est défini par la valeur des deux derniers paramètres supplémentaires.
 *
 * e./ Tracer des lignes droites
 * 
 * ==> drawLine(int x1, int y1, int x2, int y2) 
 * Il suffit de lui spécifier les coordonnées de départ et d'arrivée de la ligne
 * 
 * f./ Dessiner des polygones
 * 
 * ==> drawPolygon(int[] x, int[] y, int nbrePoints);
 * Grâce à cette méthode, je peux dessiner des polygones de ma composition. C'est à
 * moi de définir les coordonnées de tous les points qui la forment.
 * Le dernier paramètre est le nombre de points formant le polygone. Ainsi, je n'aurai pas
 * à indiquer deux fois le point d'origine pour boucler ma figure : Java la fermera 
 * automatiquement en reliant le dernier point de tableau au premier. Cette méthode
 * possède également son homologue pour dessiner des polygones remplis : fillPolygon()
 * 
 * Il existe aussi une méthode qui prend exactement les mêmes arguments, mais qui, elle,
 * trace plusieurs lignes : drawPolyline(). Remarque : si l'on souhaite obtenir une figure fermée,
 * faudra indiquer de nouveau le point d'origine à la fin, et par exemple, pour un triangle, le
 * nombre de points ne sera pas 3 mais 4.
 * 
 * g./ La méthode drawString()
 * 
 * Voici la méthode permettant d'écrire du texte. Elle est très simple à utiliser : il
 * suffit de lui passer en paramètre la phrase à écrire et de lui spécifier à quelles
 * coordonnées commencer. Réf Panneau
 * Je peux aussi modifier la couleur (la modification s'appliquera aussi pour les autres
 * méthodes) et la police d'écriture. Pour redéfinir la police, je dois créer un objet
 * Font (du package java.awt.Font)
 * Réf Panneau
 * 
 * 3.) La méthode drawImage() de l'objet Graphics
 * 
 * Voici à quoi elle ressemble : 
 * drawImage(Image img, int x, int y, Observer obs);
 * Je dois charger mon image grâce à trois objets :
 * 		-	un objet Image
 * 		-	un objet ImageIO
 * 		-	un objet File
 * C'est pas compliqué, il suffit de déclarer un objet de type Image et de l'initialiser
 * en utilisant une méthode statique de l'objet ImageIO qui, elle, prend un objet File 
 * en paramètre. Mon image sera stockée à la racine de mon projet, mais ce n'est pas une
 * obligation.
 * En ce qui concerne le dernier paramètre de la méthode drawImage, il s'agit de l'objet
 * qui est censé observer l'image. Ici, je vais utiliser mon objet Panneau, donc this.
 * Note : cette méthode dessinera l'image avec ses propres dimensions. Si je veux qu'elle
 * occupe l'intégralité de mon conteneur 
 * ==> drawImage(Image img, int x, int y, int width, int height, Observer obs);
 * Réf Panneau
 * 
 * 4.) L'objet Graphics2D
 * 
 * Ceci est une amélioration de l'objet Graphics. 
 * Pour utiliser cet objet, il me suffit de caster l'objet Graphics en Graphics2D 
 * (Graphics2D g2d = (Graphics2D)g, et de ne surtout pas oublier d'importer ma classe 
 * qui se trouve dans java.awt. L'une des possibilités qu'offre cet objet n'est autre
 * que celle de peindre des objets avec des dégradés de couleurs. Cette opération
 * n'est pas du tout difficile à réaliser : il suffit d'utiliser un objet GradientPaint
 * et une m de l'objet Graphics2D.
 * Voici comment je dois instancier mon objet GradientPaint (imports !):
 * GradientPaint gp = new GradientPaint(0, 0, Color.RED, 30, 30, Color.cyan, true);
 * Voici le détail du constructeur utilisé dans ce code :
 * i   - la coordonnée x où doit commencer la 1re couleur
 * ii  - la coordonnée y où doit commencer la 1re couleur
 * iii - la première couleur
 * iv  - la coordonnée x où doit commencer la 2de couleur
 * v   - la coordonnée y où doit commencer la 2de couleur
 * vi  - la seconde couleur
 * vii - le boléen indiquant si le degradé doit se répéter.
 * 
 * Ensuite, pour utiliser ce dégradé dans une forme, il faut mettre à jour mon objet 
 * Graphics2D, comme ceci :
 * g2d.setPaint(gp);
 * g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
 */
public class Main_Fenetre {

	public static void main(String[] args) {
		Fenetre fen = new Fenetre();
		//fen.setAlwaysOnTop(true);
		//fen.setUndecorated(true); 
		fen.setLocation(100, 100);// marche pas à cause du setLocationRelativeTo(null) du constructeur
		//fen.setResizable(false); 
		
		
	}

}
