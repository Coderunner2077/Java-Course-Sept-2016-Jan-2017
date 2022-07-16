/*
 * 										Le Drag'n Drop
 * 
 * Le Drag'n Drop - traduit par "Glisser, D�poser" - revient � s�lectionner un �l�ment graphi -
 * que d'un clic gauche, � le d�placer gr�ce � la souris tout en maintenant le bouton enfonc�
 * et � le d�poser � l'endroit voulu en rel�chant le bouton. En Java, cette notion est arriv�e
 * avec JDK 1.2, dans le syst�me graphique awt. M�me si ce syst�me eSt fondu et simplifi� par
 * swing, je devrai utiliser l'ancienne gestion de ce comportement, version awt.
 * Je commencerai avec quelque chose de simple, en utilisant swing, pour passer ensuite � 
 * un cas plus complet en utilisant tous les rouages de ces �v�nements, car il s'agit encore
 * et toujours d'�v�nements.
 * 
 * I./ Pr�sentation
 * 
 * La 1re chose � faire en swing pour activer le drag'n drop, c'est d'activer cette fonctionna-
 * lit� dans les composants concern�s.
 * R�f Fenetre1
 * 
 * Le drag'n drop est tr�s simple � activer :
 * ==> avec la m�thode setDragEnabled(boolean b)
 * 
 * Mais le drag'n drop n'est disponible que pour certains composants. 
 * D'abord, il ne faut pas confondre l'action "drag" et l'action "drop". Certains composants
 * autorisent les deux, alors que d'autres n'autorisent que le drag. Voici un tableau r�capitu-
 * latif des actions autoris�es par composant :
 * 
 * COMPOSANT		DRAG	DROP
 * JEditorPane		 X		 X
 * JColorChooser	 X		 X
 * JFileChooser		 X		 .
 * JTextPane		 X		 X
 * JTextField		 X		 X
 * JTextArea		 X		 X
 * JFormattedTextF.	 X		 X
 * JPasswordTextF.	 .		 X
 * JLabel			 .		 .
 * JTable			 X		 .
 * JTree			 X		 .
 * JList			 X		 .
 * 
 * Certains composants de ces tableaux autorisent soit l'export des donn�es, soit l'import
 * des donn�es, soit les deux, soit aucun des deux. Certains composants n'ont aucun comporte -
 * ment lorsque j'y d�pose des donn�es... Ceci est d� � leur complexit� et � leur mode de 
 * fonctionnement. Par exemple, donner un comportement par d�faut � un JTree n'est pas une
 * mince affaire. De ce fait, le comportement est laiss� aux bons soins du d�veloppeur, en
 * l'occurence, moi. 
 * Par contre, je dois garder en t�te que "drag" inclue deux notions implicites : le 
 * "drag-d�placement" et le "drag-copie". En fait, le drag'n drop peut avoir plusieurs effets :
 * 	-	la copie
 * 	-	le d�placement
 * 
 * Par exemple, lorsque sous Windows, sans changer de disque dur, le drag consiste � d�placer
 * le contenu int�gralement (==> couper/coller), sauf si je maintiens en m�me temps la touche
 * CTRL (==> copier/coller).
 * 
 * Je peux �galement activer ces fonctionnalit�s pour des composants qui au d�part ne les au -
 * torisent pas, comme JLabel par exemple.
 * Mais pour cela, faudra aller derri�re les coulisses...
 * 
 * II./ Fonctionnement
 * 
 * 1.) Principes de base
 * 
 * Le transfert des donn�es entre deux composants  se fait gr�ce � trois composants essentielles:
 * => un composant d'origine
 * => un objet transitant entre les composants (des donn�es transf�r�es)
 * => un composant cible
 * 
 * En effet, pendant l'op�ration drag'n drop, les donn�es transitent d'un composant � un autre
 * via un objet. Dans l'API Swing, le m�canisme de drag'n drop est encapsul� dans l'objet
 * JComponent dont tous les objets graphiques h�ritent, ce qui signifie que tous les objets
 * graphiques peuvent impl�menter cette fonctionnalit�.
 * Afin d'activer le drag'n drop sur un composant qui ne le permet pas par d�faut, je dois
 * utiliser la m�thode :
 * 
 * setTransferHandler(TransferHandler newHandler) de l'objet JComponent. 
 * 
 * Cette m�thode prend un objet TransferHandler en param�tre : c'est celui-ci qui lance le 
 * m�canisme de drag'n drop.
 * 
 * Les composants du tableau r�capitulatif (hormis le JLabel) ont tous un objet TransferHandler
 * par d�faut, d'o� la possibilit� d'activer le drag'n drop avec la m�thode setDragEnabled().
 * Afin de l'activer �galement sur un JLabel, je dois lui sp�cifier un objet TransferHandler
 * r�alis� par mes soins. 
 * 
 * Attention, toutefois ! Je peux d�finir un objet TransferHandler pour un composant poss�dant
 * d�j� un comportement par d�faut, mais cette action supplantera le m�canisme par d�faut du
 * composant : il faut donc faire preuve de prudence !
 * 
 * 2.) Activer le drag'n drop du JLabel
 * 
 * Afin de lui ajouter les fonctionnalit�s voulues, je dois lui affecter un nouveau 
 * TransferHandler. Une fois ce nouvel objet lui sera assign�, je lui ajouterai un �v�nement
 * souris afin de lancer l'action de drag'n drop : l'objet TransferHandler ne permettant que 
 * le transit des donn�es (il ne g�re pas les �v�nements !). Dans mon �v�nement, je vais juste
 * r�cup�rer le composant initiateur du drag, r�cup�rer son objet TransferHandler et 
 * invoquer sa m�thode :
 * 		-	exportAsDrag(JComponent comp, InputEvent event, int action)
 * 
 * R�f Fenetre2
 * 
 * Note : l'objet de transfert n'a pas de constructeur sans argument ! Cette instruction par
 * exemple ne compilera pas : 
 * new TransferHandler();
 * Par contre, le constructeur utilis� pour le JLabel (new TransferHandler("text")) fonctionne
 * parfaitement. Pourquoi ? Tout simplement parce que la cha�ne de caract�res pass�e en 
 * param�tre correspond � une propri�t� JavaBean utilisable par l'objet.
 * 
 * Un JavaBean est un objet Java r�pondant � certains crit�res de construction :
 * 
 * 	-	la classe doit �tre Serializable pour pouvoir sauvegarder et restaurer l'�tat des 
 *			instances de cette classe
 *	-	la classe doit poss�der un constructeur sans arguments (constructeur par d�faut)
 *	-	les propri�t� priv�es de la classe (variable d'instance) doivent �tre accessibles 
 *			publiquement via des accesseurs (set et get) suivies du nom de la propri�t� avec
 *			la 1re lettre transform�e en majuscule
 *	-	la classe doit contenir des m�thodes d'interception d'�v�nements n�cessaires
 *
 * En fait, mon objet de transfert va utiliser la propri�t� "text" de mon objet JLabel, ceci
 * afin de r�cup�rer son contenu et de le faire transiter. Je verrai plus loin comment faire
 * quand je ne connais pas le nom de la propri�t�...
 * 
 * Ensuite, j'ai r�cup�r� l'objet TransferHandler (d�j� affect�) depuis mon composant : avec
 * un getter.
 * 
 * L� o� les choses deviennent int�ressantes, c'est lorsque j'invoque la m�thode :
 * handle.exportAsDrag(comp, event, TransferHandler.COPY);
 * 
 * C'est cette instruction qui amorce r�ellement le drag'n drop. Les trois param�tres servent
 * � initialiser les actions � effectuer et � d�terminer quand et sur qui les faire :
 * 		-	le 1er param�tre indique le composant contenant les donn�es � d�placer
 * 		-	le 2nd param�tre indique � mon objet l'�v�nement sur lequel il doit d�clencher le
 * 				transfert
 * 		-	le dernier indique l'action qui doit �tre effectu�e : copie, d�placement, rien...
 * 
 * Comme cela a �t� mentionn� plus haut, il existe plusieurs types d'actions qui peuvent
 * �tre effectu�es lors d'un drop, celles-ci sont param�trables via l'objet TransferHandler :
 * 
 * 	-	TransferHandler.COPY : n'autorise que la copie des donn�es vers le composant cible
 * 	-	TransferHandler.MOVE : n'autorise que le d�placement des donn�es vers le comp cible
 * 	-	TransferHandler.LINK : n'autirise que l'action lien sur les donn�es du composant cible,
 * 			cela revient � cr�er un raccourci
 * 	-	TransferHandler.COPY_OR_MOVE : autorise la copie et le d�placement
 * 	-	TransferHandler.NONE : n'autorise rien
 * 
 * Attention, l'objet TransferHandler n'accepte que les action COPY lorsqu'il est instanci�
 * avec le param�tre "text" : si je modifie la valeur ici, mon drag ne fonctionnera plus.
 * 
 * Est-ce que cela veut dire que l'option drag'n drop de mon JLabel sera restreinte ?
 * 
 * Je vais devoir encore approfondir mes connaissances...
 * 
 * III./ Cr�er son propre TransferHandler
 * 
 * Afin de personnaliser le drag'n drop pour mon composant, je vais devoir mettre les mains
 * dans le cambouis. La classe TransferHandler fait pas mal de choses dans mon dos et, tout
 * comme les mod�les de composants (cf. JTree, JTable), d�s lors que j'y mets les mains, tout
 * sera � ma charge.
 * 
 * R�f diagramme.png ==> repr�sentation simplifi�e de la classe TransferHandler.
 * 
 * Voici les m�thodes � red�finir de l'objet TransferHandler : 
 * 	-	public boolean canImport(TransferHandler.TransferSupport info) :
 * 			 m�thode permettant � l'objet de savoir si les donn�es re�ues via un drop sont 
 * 			 autoris�es � �tre import�es
 * 	-	public boolean importData(TransferHandler.TransferSupport support) :
 * 			 c'est ici que l'insertion des donn�es dans mon composant est r�alis�e
 * 	-	protected void exportDone(JComponent c, Transferable t, int action) : 
 * 			 Cette m�thode est invoqu�e � la fin de l'action DROP. Si des actions sont � faire
 * 			 ensuite, c'est ici qu'il faudra coder le comportement d�sir�
 * 	-	protected Transferable createTransferable(JComponent c) :
 * 			Dans cette m�thode, je vais cr�er l'objet utilis� par le syst�me de drag'n drop
 * 			afin de faire circuler les donn�es entre les composants. Je peux voir qu'il 
 * 			s'agit d'un objet Transferable.
 * 	-	public int getSourceActions(JComponent c) : 
 * 			cette m�thode est utilis�e afin de d�terminer le comportement du composant 
 * 			vis-�-vis du drag'n drop : je retrouverai mes variables statiques COPY, MOVE, 
 * 			LINK, COPY_OR_MOVE ou NONE
 * 
 * Commen�ons par d�finir le comportement souhait� pour mon composant : le d�placement. Cela
 * se fait via la m�thode public int getSourceActions(JComponent c). Je vais utiliser les
 * variables statiques de la classe m�re pour d�finir l'action autoris�e :
 * public int getSourceActions(JComponent c){
 * 		return MOVE;
 * }
 * 
 * R�f MyTransferHandler
 * 
 * Maintenant, je vais m'assurer qu'il sera toujours possible d'importer des donn�es d'un
 * autre composant en les d�posant dessus. Pour cela, je vais red�finir les m�thodes d'import
 * des donn�es public boolean canImport(TansferHandler.TransferSupport info) et public 
 * boolean importData(TransferHandler.TransferSupport support). 
 * Note : ce param�tre bizarre est une classe interne, TransferSupport. Cet objet a un r�le 
 * tr�s important => la communication entre les composants. C'est lui qui v�hicule l'objet
 * encapsulant mes donn�es. C'est aussi lui, pour des composants plus complexes tels qu'un
 * tableau, un arbre ou une liste, qui fournit l'emplacement o� a eu lieu l'action drop.
 * 
 * Voici ce que va contenir ma m�thode :
 * public boolean canImport(TransferHandler.TransferSupport info){
 * 		// je contr�le si les donn�es re�ues sont d'un type autoris�, ici string
 * 		if(!info.isDataFlavorSupported(DataFlavor.stringFlavor))
 * 			return false;
 * 		return true;
 * }
 * 
 * L'objet TransferSupport a une m�thode permettant de contr�ler le type de donn�es support�es
 * par mon drag'n drop. Une liste de "type MIME" (Multipurpose Internet Mail Extensions est 
 * une fa�on de typer certains fichiers commes les images, les PDF, etc.) est disponible dans 
 * l'objet DataFlavor. Ici, j'ai utilis� DataFlavor.stringFlavor, qui signifie "cha�ne de 
 * caract�res". Voici la liste des �l�ments disponibles via l'objet DataFlavor :
 * 
 * 	-	DataFlavor.javaSerializedObjectMimeType : 
 * 			autorise un objet Java s�rialis� correspondant au type MIME "application/x-java-
 * 			serialized-object"
 * 	-	DataFlavor.imageFlavor : autorise une image, soit la classe java.awt.Image correspon -
 * 			dant au type MIME "image/x-java-image"
 * 	-	DataFlavor.javaFileListFlavor : autorise un objet java.util.List contenant des objets
 * 			java.io.File
 * 	-	DataFlavor.javaJVMLocalObjectMimeType : 
 * 			autorise n'importe quel objet Java
 * 	-	DataFlavor.javaRemoteObjectMimeType :
 * 			autorise un objet distant utilisant l'interface Remote
 * 	-	DataFlavor.stringFlavor :
 * 			autorise soit une cha�ne de caract�res, soit la classe java.lang.String 
 * 
 * La seconde �tape de ma d�marche consiste � autoriser l'import de donn�es vers mon composant
 * gr�ce � la m�thode public boolean imortData(TransferHandler.TransferSupport support). 
 * R�f MyTransferHandler
 *  
 * IV./ Activer le drop sur un JTree
 * 
 * Je vais �tre confront� au probl�me de positionnement du drop sur mon composant. Cependant,
 * je dispose de l'objet dont le r�le est d'informer sur la position du drop : l'objet
 * TransferSupport.
 * Avant de poursuivre avec cette voie, il faut d�finir l'action qui doit �tre effectu�e par
 * mon composant lors du d�p�t de mes donn�es. C'est possible gr�ce � l'objet DropMode que
 * je peux utiliser via la m�thode setDropMode(DropMode dropMode). 
 * Voici la liste des modes disponibles :
 * 	-	USE_SELECTION
 * 	-	ON
 * 	-	INSERT
 * 	-	ON_OR_INSERT
 * 	-	INSERT_COLS
 * 	-	INSERT_ROWS
 * 	-	ON_OR_INSERT_COLS
 * 	-	ON_OR_INSERT_ROWS
 * 
 * Certains modes sont utilisables par des tableaux, d'autres non... C'est gr�ce � cela que 
 * je vais sp�cifier le mode de fonctionnement de mon JTree.
 * 
 * A pr�sent, il ne reste plus qu'� d�couvrir comment et surtout o� ins�rer le nouvel �l�ment.
 * C'est l� que le TransferSupport entre en jeu. Cet objet permet de r�cup�rer un objet
 * DropLocation contenant toutes les informations n�cessaires au bon positionnement des 
 * donn�es dans le composant cible.
 * En fait, par l'objet TransferSupport, je peux d�duire un objet DropLocation propre �
 * mon composant, par exemple :
 *  //pour r�cup�rer des info importantes sur un JTree
 *  JTree.DropLocation dl = (JTree.DropLocation)myTransferSupport.getDropLocation();
 *  //Pour r�cup�rer des info importantes sur un JTable
 *  JTable.DropLocation dl = (JTable.DropLocation)myTransferSupport.getDropLocation();
 *  //POur r�cup�rer des info importantes sur un JList
 *  JList.DropLocation dl = (JList.DropLocation)myTransferSupport.getDropLocation();
 *  
 * L'avantage de ces sp�cifications, c'est qu'elles permettent d'avoir acc�s � des info fort
 * utiles :
 * 
 * JList.DropLocation			JTree.DropLocation				JTable.DropLocation
 * isInsert						getChildIndex					isInsertRow
 * getIndex						getPath							isInsertColumn
 * 																getRow
 * 																getColumn
 * 
 * R�f MyTransferHandler2
 * R�f TreeTransferHandler
 * R�f TreeDragDemo (=>fenetre)
 * 
 * V./ Effet de d�placement
 * 
 * Il s'agira ici de simuler le d�placement de mes composants sur mon IHM. 
 * En fait, le principe revient � d�finir un GlassPane � ma fen�tre, composant personnalis�
 * que je vais h�riter de JPanel. C'est lui qui va se charger de dessiner les images des
 * composants sur sa surface, dont j'aurai au pr�alable d�fini la transparence. Sur chaque
 * composant, je vais devoir d�finir les actions � effectuer � chaque �v�nement souris : deux
 * classes sont cod�es � cet effet... Ensuite, il ne restera plus qu'� faire le test.
 * 
 * R�f fr.effet.dep => MyGlassPane
 * 					=> r�f MouseGlassListener
 * 					=> r�f MouseGlassMotionListener
 * 					=> r�f Fenetre
 * 
 * Attention, pour des composants comme les JTree, JTable ou autres, j'aurai certainement
 * � faire des modifications pour que �a fonctionne !
 *  
 */
public class Main_drag {

	public static void main(String[] args) {
		

	}

}
