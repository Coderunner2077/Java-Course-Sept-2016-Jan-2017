/*
 * 								Conteneurs, sliders et barres de progression
 * 
 * Les nouveaux conteneurs � d�couvrir dans ce chapitre seront soit compl�mentaires � JPanel, soit �
 * tout autre type de conteneur ayant ses propres sp�cificit�s.
 * Et je n'aborderai pas ces nouveaux objets dans le d�tail ici. ==> je suis � m�me d'approfondir
 * moi-m�me mes connaissances !
 * 
 * I./ Autres conteneurs
 * 1.) L'objet JSplitPane
 * 
 * C'est un conteneur pr�vu pour contenir d'autres conteneurs dont la taille est ajustable (gr�ce �
 * une barre de s�paration)
 * R�f FenetreJSP
 * Pour s�parer deux objets JPanel de fa�on horizontale, on proc�de ainsi :
 * JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pan, pan2);
 * Et l'attribut JSplitPane.VERTICAL_SPLIT permet d'obtenir une s�paration verticale.
 * 
 * Mais les deux autres param�tres (pan et pan2) ne sont pas n�cessairement des JPanel. Il peut
 * y avoir � la place n'importe quelle autre classe d�rivant de JComponent (conteneur, bouton, case
 * � cocher, etc.).
 * L'objet JSplitPane dispose d'une m�thode permettant de rendre la barre de s�paration 
 * "intelligente"... enfin presque. Ladite m�thode ajoute deux petits boutons sur ma barre et, lors -
 * que je clique dessus, cela fera retr�cir le c�t� vers lequel pointe la fl�che dans le bouton. IL
 * suffit d'invoquer la m�thode :
 * split.setOneTouchExpandable(true);  // => split est tjs mon objet JSplitPane
 * Et voici comment d�finir une taille pour le s�parateur :
 * split.setDividerSize(int size);
 * Je peux �galement d�finir o� doit se trouver cette barre, avec les m�thodes :
 * setDividerLocation(int location) ou encore setDividerLocation(double location).
 * D'autre part, l'objet JSplitPane peut aussi contenir deux autres objets JSplitPane.
 * R�f FenetreJSP
 * 
 * Je vais � pr�sent �tudier un autre objet, qui permet d'ajouter un scroll (barre de d�filement) �
 * c�t� de mes conteneurs afin de pouvoir d�passer les limites de ceux-ci.
 * 
 * 2.) L'objet JScrollPane
 * 
 * Afin de voir dans toute son ampleur l'utilit� de cet objet, je vais utiliser un autre objet de
 * texte : le JTextArea. Cet objet est tr�s simple : c'est une forme de JTextField, mais en plus 
 * grand ! Je peux directement �crire dans ce composant, celui-ci ne retourne pas directement � la
 * ligne si j'atteins le bord droit de la fen�tre.
 * Pour v�rifier si les lettres tap�es sont bien dans mon objet, je peux r�cup�rer le texte gr�ce
 * � la m�thode getText().
 * R�f FenetreJScP
 * Je vois que si j'�cris bcp de caract�res, je peux d�passer les bords de la fen�tre, que ce soit
 * � droite ou en bas. Pour ce genre de probl�me, il existe ce qu'on appelle des "scrolls". Ce sont
 * de petits ascenseurs positionn�s sur le c�t� et / ou sur le bas de ma fen�tre et qui me permettent
 * de d�passer les limites impos�es par la fen�tre en question.
 * R�f FenetreJScP
 * L'objet utilis� afin d'avoir un ascenseur s'appelle donc JScrollPane. D�sormais, je pourrai
 * �crire aussi loin que je veux, vers le bas ou vers la droite ! Les ascenseurs apparaissent 
 * automatiquement lorsque je d�passe les limites autoris�es. De plus, je peux red�finir leur
 * comportement gr�ce aux m�thodes :
 *  * setHorizontalScrollBarPolicy(int policy) : qui permet de red�finir le comportement du scroll en
 *  	bas de ma fen�tre
 *  * setVerticalScrollBarPolicy(int policy) : qui permet de red�finir le comportement du scroll � 
 *  	droite de ma fen�tre.
 *  
 * Le param�tre de ces methodes est un entier d�fini dans la classe JScrollPane, il peut prendre
 * les valeurs suivantes :
 *  -	JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED : le scroll vertical n'est visible que s'il est 
 *  		n�cessaire, donc s'il y a d�passement de la taille en hauteur
 *  -	JScrollPane.VERTICAL_SCROLLBAR_NEVER : le scroll vertical n'est jamais visible, m�me si je
 *  		d�passe; en revanche, le conteneur s'allonge tout de m�me
 *  -	JScrollPane.VERTICAL_SCROLLBAR_ALWAYS : le scroll vertical est toujours visible, m�me si je 
 *  		ne d�passe pas
 * 
 * Les m�mes entiers existent pour le scroll horizontal, mais je dois alors remplacer VERTICAL par
 * HORIZONTAL. Je dois tout de m�me savoir que cet objet en utilise un autre : un JScrollBar. Les
 * deux barres de d�filement sont deux instances de cet objet...
 * 
 * Je vais maintenant voir comment ajouter dynamiquement des conteneurs.
 * 
 * 3.) L'objet JTabbedPane
 * 
 * Je vais apprendre � cr�er plusieurs "pages" dans mon IHM... Jusqu'� maintenant, je ne pouvais
 * pas avoir plusieurs contenus dans ma fen�tre, � moins de leur faire partager l'espace disponible.
 * Il existe une solution toute simple qui consiste � cr�er des onglets. L'objet � utiliser est un
 * JTabbedPane. Afin d'avoir un exemple plus ludique, je cr�e une classe h�rit�e de JPanel afin de
 * cr�er des onglets de couleur diff�rente...
 * R�f Panneau
 * J'ai utilis� cet objet afin de cr�er un tableau de Panneau. Chaque instance est ensuite ajout�e
 * � mon objet g�rant les onglets via la m�thode :
 *  ==> add(String title, JComponent comp)
 * R�f FenetreJTP
 * En effet, l'utilisation de cet objet est tr�s simple. D'ailleurs, je peux par exemple ajouter
 * une image en guise d'ic�ne � c�t� du titre de l'onglet, avec la m�thode :
 * ==> setIconAt(int index, ImageIcon img);
 * Ou encore :
 * ==> addTab(String title, ImageIcon img, JComponent comp);
 * 
 * J'ai �galement la possibilit� de changer l'emplacement des en-t�tes d'onglets en sp�sifiant
 * cet emplacement dans le constructeur, comme ceci :
 * JTabbedPane onglet = new JTabbedPane(JTabbedPane.BOTTOM);
 * JTabbedPane onglet2 = new JTabbedPane(JTabbedPane.LEFT);
 * JTabbedPane onglet3 = new JTabbedPane(JTabbedPane.RIGHT);
 * 
 * Je peux aussi utiliser la m�thode setTabPlacement(JTabbedPane.BOTTOM) pour sp�cifier 
 * l'emplacement de l'en-t�te d'onglet. J'ai aussi la possibilit� de retirer des onglets, avec la
 * m�thode remove(int index).
 * R�f FenetreJTP (ajout de boutons pour ajouter / retirer des onglets)
 * 
 * 4.) L'objet JDesktopPane combin� � des JInternalFrame
 * 
 * Ces deux objets sont tr�s souvent associ�s et permettent de r�aliser des applications 
 * multifen�tres. 
 * R�f Bureau (h�rit� de JFrame)
 * 
 * 5.) L'objet JWindow
 * 
 * Pour faire simple, c'est une JFrame, mais sans les contours permettant de r�duire, fermer ou
 * agrandir la fen�tre ! Il est souvent utilis� pour faire des splash screens (ce qui s'affiche au
 * lancement d'Eclipse par exemple...)
 * R�f Window
 * 
 * 6.) Le JEditorPane
 * 
 * L'objet JEditorPane permet de cr�er un �diteur HTML et d'afficher le rendu du code �crit.
 * C'est un objet sympathique, mais quelque peu limit� par la fa�on dont il g�re son contenu IHM !
 * Il permet de r�aliser des textes riches (avec une mise en page). Il y a aussi le JTextPane qui
 * permet tr�s facilement de faire un mini-�diteur de texte (enfin, tout est relatif)...
 * R�f FenetreJEP
 * 
 * Dans cet exemple, on �dite le code HTML dans l'onglet d'�dition et, au changement d'onglet,
 * on g�n�re un fichier temporaire avec l'extension ".html". Ce fichier est stock� dans un 
 * r�pertoire nomm� tmp � la racine de mon projet
 * 
 * 7.) Le JSlider
 * 
 * Ce composant me permet d'utiliser un syst�me de mesure pour une application : redimensionner une
 * image, choisir le tempo d'un morceau de musique, l'opacit� d'une couleur, etc. 
 * R�f Slide
 * 
 * 8.) La JProgressBar
 * 
 * Elle me permet de r�aliser une barre de progression pour des traiements longs. 
 * R�f Progress
 * 
 * La modification des valeurs de cet objet dois se faire dans un thread, sinon, j'aurai une barre
 * vide, un temps d'attente puis la barre remplie, mais sans que les valeurs aient d�fil� en temps
 * r�el.
 * 
 * II./ Enjoliver mes IHM
 * 
 * Je n'avais pas encore vu toute la panoplie des joyeuset�s qu'offre Java en mati�re de bordures.
 * C'est le moment ou jamais.
 * R�f BorderDemo (h�ritant de JFrame)
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
