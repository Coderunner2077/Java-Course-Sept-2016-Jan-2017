/*
 * 								Conteneurs, sliders et barres de progression
 * 
 * Les nouveaux conteneurs à découvrir dans ce chapitre seront soit complémentaires à JPanel, soit à
 * tout autre type de conteneur ayant ses propres spécificités.
 * Et je n'aborderai pas ces nouveaux objets dans le détail ici. ==> je suis à même d'approfondir
 * moi-même mes connaissances !
 * 
 * I./ Autres conteneurs
 * 1.) L'objet JSplitPane
 * 
 * C'est un conteneur prévu pour contenir d'autres conteneurs dont la taille est ajustable (grâce à
 * une barre de séparation)
 * Réf FenetreJSP
 * Pour séparer deux objets JPanel de façon horizontale, on procède ainsi :
 * JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pan, pan2);
 * Et l'attribut JSplitPane.VERTICAL_SPLIT permet d'obtenir une séparation verticale.
 * 
 * Mais les deux autres paramètres (pan et pan2) ne sont pas nécessairement des JPanel. Il peut
 * y avoir à la place n'importe quelle autre classe dérivant de JComponent (conteneur, bouton, case
 * à cocher, etc.).
 * L'objet JSplitPane dispose d'une méthode permettant de rendre la barre de séparation 
 * "intelligente"... enfin presque. Ladite méthode ajoute deux petits boutons sur ma barre et, lors -
 * que je clique dessus, cela fera retrécir le côté vers lequel pointe la flèche dans le bouton. IL
 * suffit d'invoquer la méthode :
 * split.setOneTouchExpandable(true);  // => split est tjs mon objet JSplitPane
 * Et voici comment définir une taille pour le séparateur :
 * split.setDividerSize(int size);
 * Je peux également définir où doit se trouver cette barre, avec les méthodes :
 * setDividerLocation(int location) ou encore setDividerLocation(double location).
 * D'autre part, l'objet JSplitPane peut aussi contenir deux autres objets JSplitPane.
 * Réf FenetreJSP
 * 
 * Je vais à présent étudier un autre objet, qui permet d'ajouter un scroll (barre de défilement) à
 * côté de mes conteneurs afin de pouvoir dépasser les limites de ceux-ci.
 * 
 * 2.) L'objet JScrollPane
 * 
 * Afin de voir dans toute son ampleur l'utilité de cet objet, je vais utiliser un autre objet de
 * texte : le JTextArea. Cet objet est très simple : c'est une forme de JTextField, mais en plus 
 * grand ! Je peux directement écrire dans ce composant, celui-ci ne retourne pas directement à la
 * ligne si j'atteins le bord droit de la fenêtre.
 * Pour vérifier si les lettres tapées sont bien dans mon objet, je peux récupérer le texte grâce
 * à la méthode getText().
 * Réf FenetreJScP
 * Je vois que si j'écris bcp de caractères, je peux dépasser les bords de la fenêtre, que ce soit
 * à droite ou en bas. Pour ce genre de problème, il existe ce qu'on appelle des "scrolls". Ce sont
 * de petits ascenseurs positionnés sur le côté et / ou sur le bas de ma fenêtre et qui me permettent
 * de dépasser les limites imposées par la fenêtre en question.
 * Réf FenetreJScP
 * L'objet utilisé afin d'avoir un ascenseur s'appelle donc JScrollPane. Désormais, je pourrai
 * écrire aussi loin que je veux, vers le bas ou vers la droite ! Les ascenseurs apparaissent 
 * automatiquement lorsque je dépasse les limites autorisées. De plus, je peux redéfinir leur
 * comportement grâce aux méthodes :
 *  * setHorizontalScrollBarPolicy(int policy) : qui permet de redéfinir le comportement du scroll en
 *  	bas de ma fenêtre
 *  * setVerticalScrollBarPolicy(int policy) : qui permet de redéfinir le comportement du scroll à 
 *  	droite de ma fenêtre.
 *  
 * Le paramètre de ces methodes est un entier défini dans la classe JScrollPane, il peut prendre
 * les valeurs suivantes :
 *  -	JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED : le scroll vertical n'est visible que s'il est 
 *  		nécessaire, donc s'il y a dépassement de la taille en hauteur
 *  -	JScrollPane.VERTICAL_SCROLLBAR_NEVER : le scroll vertical n'est jamais visible, même si je
 *  		dépasse; en revanche, le conteneur s'allonge tout de même
 *  -	JScrollPane.VERTICAL_SCROLLBAR_ALWAYS : le scroll vertical est toujours visible, même si je 
 *  		ne dépasse pas
 * 
 * Les mêmes entiers existent pour le scroll horizontal, mais je dois alors remplacer VERTICAL par
 * HORIZONTAL. Je dois tout de même savoir que cet objet en utilise un autre : un JScrollBar. Les
 * deux barres de défilement sont deux instances de cet objet...
 * 
 * Je vais maintenant voir comment ajouter dynamiquement des conteneurs.
 * 
 * 3.) L'objet JTabbedPane
 * 
 * Je vais apprendre à créer plusieurs "pages" dans mon IHM... Jusqu'à maintenant, je ne pouvais
 * pas avoir plusieurs contenus dans ma fenêtre, à moins de leur faire partager l'espace disponible.
 * Il existe une solution toute simple qui consiste à créer des onglets. L'objet à utiliser est un
 * JTabbedPane. Afin d'avoir un exemple plus ludique, je crée une classe héritée de JPanel afin de
 * créer des onglets de couleur différente...
 * Réf Panneau
 * J'ai utilisé cet objet afin de créer un tableau de Panneau. Chaque instance est ensuite ajoutée
 * à mon objet gérant les onglets via la méthode :
 *  ==> add(String title, JComponent comp)
 * Réf FenetreJTP
 * En effet, l'utilisation de cet objet est très simple. D'ailleurs, je peux par exemple ajouter
 * une image en guise d'icône à côté du titre de l'onglet, avec la méthode :
 * ==> setIconAt(int index, ImageIcon img);
 * Ou encore :
 * ==> addTab(String title, ImageIcon img, JComponent comp);
 * 
 * J'ai également la possibilité de changer l'emplacement des en-têtes d'onglets en spésifiant
 * cet emplacement dans le constructeur, comme ceci :
 * JTabbedPane onglet = new JTabbedPane(JTabbedPane.BOTTOM);
 * JTabbedPane onglet2 = new JTabbedPane(JTabbedPane.LEFT);
 * JTabbedPane onglet3 = new JTabbedPane(JTabbedPane.RIGHT);
 * 
 * Je peux aussi utiliser la méthode setTabPlacement(JTabbedPane.BOTTOM) pour spécifier 
 * l'emplacement de l'en-tête d'onglet. J'ai aussi la possibilité de retirer des onglets, avec la
 * méthode remove(int index).
 * Réf FenetreJTP (ajout de boutons pour ajouter / retirer des onglets)
 * 
 * 4.) L'objet JDesktopPane combiné à des JInternalFrame
 * 
 * Ces deux objets sont très souvent associés et permettent de réaliser des applications 
 * multifenêtres. 
 * Réf Bureau (hérité de JFrame)
 * 
 * 5.) L'objet JWindow
 * 
 * Pour faire simple, c'est une JFrame, mais sans les contours permettant de réduire, fermer ou
 * agrandir la fenêtre ! Il est souvent utilisé pour faire des splash screens (ce qui s'affiche au
 * lancement d'Eclipse par exemple...)
 * Réf Window
 * 
 * 6.) Le JEditorPane
 * 
 * L'objet JEditorPane permet de créer un éditeur HTML et d'afficher le rendu du code écrit.
 * C'est un objet sympathique, mais quelque peu limité par la façon dont il gère son contenu IHM !
 * Il permet de réaliser des textes riches (avec une mise en page). Il y a aussi le JTextPane qui
 * permet très facilement de faire un mini-éditeur de texte (enfin, tout est relatif)...
 * Réf FenetreJEP
 * 
 * Dans cet exemple, on édite le code HTML dans l'onglet d'édition et, au changement d'onglet,
 * on génère un fichier temporaire avec l'extension ".html". Ce fichier est stocké dans un 
 * répertoire nommé tmp à la racine de mon projet
 * 
 * 7.) Le JSlider
 * 
 * Ce composant me permet d'utiliser un système de mesure pour une application : redimensionner une
 * image, choisir le tempo d'un morceau de musique, l'opacité d'une couleur, etc. 
 * Réf Slide
 * 
 * 8.) La JProgressBar
 * 
 * Elle me permet de réaliser une barre de progression pour des traiements longs. 
 * Réf Progress
 * 
 * La modification des valeurs de cet objet dois se faire dans un thread, sinon, j'aurai une barre
 * vide, un temps d'attente puis la barre remplie, mais sans que les valeurs aient défilé en temps
 * réel.
 * 
 * II./ Enjoliver mes IHM
 * 
 * Je n'avais pas encore vu toute la panoplie des joyeusetés qu'offre Java en matière de bordures.
 * C'est le moment ou jamais.
 * Réf BorderDemo (héritant de JFrame)
 * 
 * 
 * 
 *  
 * 
 * 
 */
public class Main_conteneurs {

	public static void main(String[] args) {

	}

}
