/*
 * 									 Ma premi�re fen�tre
 * 
 * Dans cette partie, j'aborderai les interfaces graphiques (on parle aussi d'IHM pour 
 * Interface Homme Machine ou de GUI pour Graphical User Interfaces) et par extension, 
 * la programmation �v�nementielle. C'est-�-dire que mon prog ne r�agira plus � des 
 * saisies au clavier, mais � des �v�nements provenant d'un composant graphique : un bouton,
 * une liste, un menu...
 * Le langage Java propose diff�rentes biblioth�ques pour programmer des IHM, mais ici, 
 * j'utiliserai essentiellement les packages javax.swing et java.awt pr�sentes d'office
 * dans Java. Notamment, j'apprendrai ici � utiliser l'objet JFrame, pr�sent dans le
 * package javax.swing.
 * Une fen�tre n'est en r�alit� qu'une multitude de composants pos�s les uns sur les 
 * autres.
 * 
 * I./ L'objet JFrame
 * 1.) Points de rep�res sur swing et awt
 * Tout d'abord, quelques points de rep�re sur javax.swing et java.awt... Dans ce cours,
 * je traiterai de ces deux derniers, mais je n'utiliserai pas de composants awt, je 
 * travaillerai uniquement avec des composants swing. Par exemple, un composant peut
 * �tre repr�sent� par un bouton, une zone de texte, une case � cocher, etc.
 * Afin de mieux comprendre comment tout cela fonctionne, je dois savoir que lorsque le 
 * langage Java a vu le jour, dans sa version 1.0, seul awt �tait utilisable; swing
 * n'existait pas, il est apparu dans la version 1.2 de Java (aussi appel� Java 2). Les
 * composants awt sont consid�r�s comme lourds (on dit aussi HeavyWeight) car ils sont
 * fortement li�s au syst�me d'exploitation qui les g�re. Les composants swing, eux, sont
 * dessin�s dans un conteneur, car ils sont l�gers (on dit LightWeight); ils n'ont pas
 * le m�me rendu � l'affichage, car ce n'est plus le syst�me d'exploitation qui les g�re.
 * Il existe �galement d'autres diff�rences, comme le nombre de composants utilisables,
 * la gestion des bordures...
 * Pour toutes ces raisons, il est fortement recommand� de ne pas m�langer les composants
 * swing et awt dans une m�me fen�tre; cela pourrait occasionner des conflits ! Si j'associe
 * les deux, j'aurai de tr�s grandes difficult�s � d�velopper une IHM stable et valide. En
 * effet, swing et awt ont les m�mes fondements, mais diff�rent dans leur utilisation.
 * 
 * 2.) Cr�ation d'une fen�tre
 * 
 * Pour utiliser une fen�tre de type JFrame, je dois l'instancier comme ceci :
 * JFrame fenetre = new JFrame();
 * Et ce, sans oublier d'importer javax.swing.JFrame
 * MAIS, lorsque j'ex�cute ce code, je n'obtiens rien, et c'est normal => par d�faut,
 * JFrame n'est pas visible, je dois la rendre visible de cette mani�re :
 * fenetre.setVisible(true);
 * Le r�sultat est, pour le moins, petit : je n'obtiens qu'une toute petite fen�tre...
 * Voici les m�thodes pour obtenir une fen�tre plus cons�quente :
 * 		-	setTitle(String titre) : d�finit un titre pour la fen�tre
 * 		-	setSize(int largeur, int hauteur) : d�finit la taille en pixels
 * 		-	setLocationRelativeTo(null) : pour positionner la fen�tre au centre
 * 		-	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) : termine le processus lorsqu'on
 * 			clique sur la croix rouge
 * 		-	setVisible : pour rendre la fen�tre visible
 * 
 * Afin de ne pas avoir � red�finir les attributs � chaque fois, il serait int�ressant 
 * d'avoir son propre objet. 
 * Donc, au lieu d'instancier JFrame dans main, je vais cr�er une classe que je vais 
 * appeler Fenetre et la faire h�riter de JFrame. J'�crirai toutes les m�thodes
 * ci-dessus directement dans le constructeur de la classe Fenetre.
 * Apr�s j'ai le choix, soit je conserve la classe contenant la m�thode main et je cr�e
 * une instance de Fenetre, soit j'efface la classe contenant la m�thode main et je 
 * place celle-ci directement dans ma classe Fenetre. Mais dans tous les cas, je dois 
 * cr�er une instance de ma Fenetre.
 * Ainsi, il est tout de m�me plus pratique de ne pas avoir � r��crire les m�mes 
 * instructions � chaque fois.
 * 
 * 3.) D'autres m�thodes utiles de l'objet JFrame
 * a./ Positionner la fen�tre � l'�cran
 * 
 * Pour positionner la fen�tre ailleurs qu'au centre, je peux utiliser la m�thode 
 * setLocation(int x, int y). Gr�ce � cette m�thode, je peux sp�sicier o� doit se situer
 * ma fen�tre sur l'�cran. Les coordonn�es, exprim�es en pixels, sont bas�es sur un 
 * rep�re dont l'origine est repr�sent�e par le coin sup�rieur gauche de l'�cran.
 * Les valeurs n�gatives font sortir la fen�tre de l'�cran, les coordonn�es du 
 * point au coin sup�rieur gauche de l'�cran �tant (0, 0).
 * 
 * b./ Emp�cher le redimensionnement de la fen�tre 
 * 
 * Pour cela, il suffit d'invoquer la m�thode setResizable(boolean b) : false emp�che
 * le redimensionnement, tandis que true l'autorise.
 * 
 * c./ Garder la fen�tre au premier plan
 * 
 * Il s'agit l� encore d'une m�thode qui prend un bol�en en param�tre. Passer true laissera
 * la fen�tre au premier plan quoiqu'il advienne, false annulera cela. Cette m�thode est:
 * setAlwaysOnTop(boolean b).
 * 
 * d./ Retirer les contours et les boutons de contr�le
 * 
 * Pour faire cela, il faut utiliser la m�thode setUndecorated(boolean b)
 * 
 * 4.) La constitution d'une fen�tre Java
 * 
 * Ma fen�tre, telle qu'elle appara�t, cache quelques petites choses...
 * Je pensais, que ma fen�tre �tait tout simple, d�pourvue de tout composant (hormis les 
 * contours). Eh bien, je me trompais ! Une JFrame est d�coup�e en plusieurs parties
 * sup�rpos�es.
 * Une JFrame est constitu�e, dans l'ordre, de (R�f composants_JFrame.png) :
 * 		-	la fen�tre elle-m�me
 * 		-	le RootPane (en vert), le conteneur principal qui contient tous les autres
 * 			composants
 * 		-	le LayeredPane (en violet), qui forme juste un panneau compos� du conteneur
 * 			global et de la barre du menu (MenuBar)
 * 		-	la MenuBar (en orange), la barre du menu, quand il y en a une
 * 		-	le content pane (en rose, situ� en bas de la MenuBar), c'est dans celui-ci
 * 			que je placerai tous mes composants
 * 		-	le GlassPane (en transparence), couche utilis�e pour intercepter les actions
 * 			de l'utilisateur avant qu'elles ne parviennent aux composants
 * 
 * Pas de panique, je m'en servirai uniquement du content pane. Pour le r�cup�rer, il
 * me suffit d'utiliser la m�thode getContentPane() de la classe JFrame. Cependant, je
 * vais utiliser un composant autre que le content pane : un JPanel dans lequel
 * j'ins�rerai mes composants.
 * 
 * Note : il existe d'autres types de fen�tre : la JWindow, une JFrame sans bordure et
 * non draggable (d�pla�able), et la JDialog, une fen�tre non redimensionnable. Ignored.
 * 
 * II./ L'objet JPanel
 * 
 * Un JPanel est un composant de type conteneur dont la vocation est d'accueillir d'autres
 * objets de m�me type ou des objets de type composant (boutons, cases � cocher...)
 * Voici la marche � suivre :
 i.) importer la classe javax.swing.JPanel dans ma classe h�rit�e de JFrame
 ii.) instancier un JPanel (dans le constructeur de ma Fenetre) puis lui sp�cifier une 
 couleur de fond pour mieux le distinguer. 
 ==> Attention, faudra importer java.awt.Color
 ==> la m�thode (de l'objet JPanel) setBackground(Color.Orange) pour d�finir la couleur...
 iii.) Avertir ma JFrame que ce sera ma JPanel qui constituera sont content pane
 ==> avec la m�thode (de l'objet Fenetre) setContentPane(JPanel pan) 
 *
 * Passons maintenant aux choses plus int�ressantes...
 *
 * III./ Les objets Graphcics et Graphics2D
 * 1.) L'objet Graphics
 * 
 * Cet objet a une particularit� de taille : je ne peux l'utiliser que si et seulement si
 * le syst�me me l'a donn� via la m�thode getGraphics() d'un composant swing ! Pour bien
 * comprendre le fonctionnement de mes futurs conteneurs (ou composants), je vais cr�er 
 * une classe h�rit�e de JPanel : disons Panneau. R�f Panneau
 * La m�thode fillOval() de l'objet Graphics invoqu�e dans la m�thode paintComponent(Graphics g)
 * de l'objet Panneau est celle que l'objet appelle pour se dessiner sur ma fen�tre; si 
 * je r�duis cette derni�re et que je l'affiche de nouveau, c'est encore cette m�thode
 * qui est appel�e pour afficher mon composant. Idem si je redimensionne ma fen�tre... De
 * plus, je n'ai m�me pas besoin de red�finir un constructeur car cette m�thode est 
 * appel�e automatiquement !
 * C'est tr�s pratique pour personnaliser des composants, car je n'aurai JAMAIS �
 * l'appeler moi-m�me : c'est automatique ! Tout ce que je peux faire, c'est forcer
 * l'objet � se repeindre; ce n'est toutefois pas cette m�thode que j'invoquerai (� voir
 * plus loin, plus tard).
 * L'int�r�t de disposer d'une classe h�rit�e  d'un conteneur ou d'un composant, c'est que
 * je peux red�finir la fa�on dont est peint ce composant sur la fen�tre.
 * 
 * 2.) Les m�thodes de l'objet Graphics
 * a./ Dessiner un rond rempli
 * 
 * En effet, l'objet Graphics permet, entre autres, de tracer des ronds; mais il poss�de
 * tout un tas de m�thodes plus pratiques et plus amusantes les unes que les autres...
 * Pour commencer, reprenons la m�thode utilis�e pr�c�demment : g.fillOval(20, 20, 75, 75).
 * Voici son prototype : 
 * public abstract void fillOval(int x, int y, int width, int height);
 * ... le point de rep�re �tant le coin sup�rieur gauche de la fen�tre cr��e.
 * 
 * Et pour avoir un rond centr� : c'est dans ce genre de cas qu'il est int�ressant 
 * d'utiliser une classe h�rit�e. Puisque je suis dans mon objet JPanel, j'ai acc�s � ses
 * donn�es lorsque je les dessine.
 * En effet, il existe des m�thodes dans les objets composants qui retournent leur largeur
 * (getWidth()), ainsi que leur hauteur (getHeight()). R�f Panneau
 * 
 * b./ Dessiner un rond vide
 * 
 * C'est la m�thode drawOval() qui permet de dessiner un rond vide. Elle fonctionne
 * exactement de la m�me mani�re que fillOval()
 * R�f Panneau
 * 
 * c./ M�thodes se rapportant � des rectangles
 * 
 * Pour dessiner des rectangles vides ==> drawRect()
 * Pour dessiner un rectangle rempli ==> fillRect()
 * M�me fonctionnement que pour draw/fillOval
 * 
 * d./ Dessiner un rectangle aux ongles arrondis
 * 
 * ==> Les m�thodes drawRoundRect() / fillRoundRect()
 * Il s'agit du m�me �l�ment que pr�c�demment, hormis le fait que le rectangle soit 
 * arrondi. L'arrondi est d�fini par la valeur des deux derniers param�tres suppl�mentaires.
 *
 * e./ Tracer des lignes droites
 * 
 * ==> drawLine(int x1, int y1, int x2, int y2) 
 * Il suffit de lui sp�cifier les coordonn�es de d�part et d'arriv�e de la ligne
 * 
 * f./ Dessiner des polygones
 * 
 * ==> drawPolygon(int[] x, int[] y, int nbrePoints);
 * Gr�ce � cette m�thode, je peux dessiner des polygones de ma composition. C'est �
 * moi de d�finir les coordonn�es de tous les points qui la forment.
 * Le dernier param�tre est le nombre de points formant le polygone. Ainsi, je n'aurai pas
 * � indiquer deux fois le point d'origine pour boucler ma figure : Java la fermera 
 * automatiquement en reliant le dernier point de tableau au premier. Cette m�thode
 * poss�de �galement son homologue pour dessiner des polygones remplis : fillPolygon()
 * 
 * Il existe aussi une m�thode qui prend exactement les m�mes arguments, mais qui, elle,
 * trace plusieurs lignes : drawPolyline(). Remarque : si l'on souhaite obtenir une figure ferm�e,
 * faudra indiquer de nouveau le point d'origine � la fin, et par exemple, pour un triangle, le
 * nombre de points ne sera pas 3 mais 4.
 * 
 * g./ La m�thode drawString()
 * 
 * Voici la m�thode permettant d'�crire du texte. Elle est tr�s simple � utiliser : il
 * suffit de lui passer en param�tre la phrase � �crire et de lui sp�cifier � quelles
 * coordonn�es commencer. R�f Panneau
 * Je peux aussi modifier la couleur (la modification s'appliquera aussi pour les autres
 * m�thodes) et la police d'�criture. Pour red�finir la police, je dois cr�er un objet
 * Font (du package java.awt.Font)
 * R�f Panneau
 * 
 * 3.) La m�thode drawImage() de l'objet Graphics
 * 
 * Voici � quoi elle ressemble : 
 * drawImage(Image img, int x, int y, Observer obs);
 * Je dois charger mon image gr�ce � trois objets :
 * 		-	un objet Image
 * 		-	un objet ImageIO
 * 		-	un objet File
 * C'est pas compliqu�, il suffit de d�clarer un objet de type Image et de l'initialiser
 * en utilisant une m�thode statique de l'objet ImageIO qui, elle, prend un objet File 
 * en param�tre. Mon image sera stock�e � la racine de mon projet, mais ce n'est pas une
 * obligation.
 * En ce qui concerne le dernier param�tre de la m�thode drawImage, il s'agit de l'objet
 * qui est cens� observer l'image. Ici, je vais utiliser mon objet Panneau, donc this.
 * Note : cette m�thode dessinera l'image avec ses propres dimensions. Si je veux qu'elle
 * occupe l'int�gralit� de mon conteneur 
 * ==> drawImage(Image img, int x, int y, int width, int height, Observer obs);
 * R�f Panneau
 * 
 * 4.) L'objet Graphics2D
 * 
 * Ceci est une am�lioration de l'objet Graphics. 
 * Pour utiliser cet objet, il me suffit de caster l'objet Graphics en Graphics2D 
 * (Graphics2D g2d = (Graphics2D)g, et de ne surtout pas oublier d'importer ma classe 
 * qui se trouve dans java.awt. L'une des possibilit�s qu'offre cet objet n'est autre
 * que celle de peindre des objets avec des d�grad�s de couleurs. Cette op�ration
 * n'est pas du tout difficile � r�aliser : il suffit d'utiliser un objet GradientPaint
 * et une m de l'objet Graphics2D.
 * Voici comment je dois instancier mon objet GradientPaint (imports !):
 * GradientPaint gp = new GradientPaint(0, 0, Color.RED, 30, 30, Color.cyan, true);
 * Voici le d�tail du constructeur utilis� dans ce code :
 * i   - la coordonn�e x o� doit commencer la 1re couleur
 * ii  - la coordonn�e y o� doit commencer la 1re couleur
 * iii - la premi�re couleur
 * iv  - la coordonn�e x o� doit commencer la 2de couleur
 * v   - la coordonn�e y o� doit commencer la 2de couleur
 * vi  - la seconde couleur
 * vii - le bol�en indiquant si le degrad� doit se r�p�ter.
 * 
 * Ensuite, pour utiliser ce d�grad� dans une forme, il faut mettre � jour mon objet 
 * Graphics2D, comme ceci :
 * g2d.setPaint(gp);
 * g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
 */
public class Main_Fenetre {

	public static void main(String[] args) {
		Fenetre fen = new Fenetre();
		//fen.setAlwaysOnTop(true);
		//fen.setUndecorated(true); 
		fen.setLocation(100, 100);// marche pas � cause du setLocationRelativeTo(null) du constructeur
		//fen.setResizable(false); 
		
		
	}

}
