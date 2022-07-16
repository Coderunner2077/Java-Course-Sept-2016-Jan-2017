/*
 * 										Le Drag'n Drop
 * 
 * Le Drag'n Drop - traduit par "Glisser, Déposer" - revient à sélectionner un élément graphi -
 * que d'un clic gauche, à le déplacer grâce à la souris tout en maintenant le bouton enfoncé
 * et à le déposer à l'endroit voulu en relâchant le bouton. En Java, cette notion est arrivée
 * avec JDK 1.2, dans le système graphique awt. Même si ce système eSt fondu et simplifié par
 * swing, je devrai utiliser l'ancienne gestion de ce comportement, version awt.
 * Je commencerai avec quelque chose de simple, en utilisant swing, pour passer ensuite à 
 * un cas plus complet en utilisant tous les rouages de ces événements, car il s'agit encore
 * et toujours d'événements.
 * 
 * I./ Présentation
 * 
 * La 1re chose à faire en swing pour activer le drag'n drop, c'est d'activer cette fonctionna-
 * lité dans les composants concernés.
 * Réf Fenetre1
 * 
 * Le drag'n drop est très simple à activer :
 * ==> avec la méthode setDragEnabled(boolean b)
 * 
 * Mais le drag'n drop n'est disponible que pour certains composants. 
 * D'abord, il ne faut pas confondre l'action "drag" et l'action "drop". Certains composants
 * autorisent les deux, alors que d'autres n'autorisent que le drag. Voici un tableau récapitu-
 * latif des actions autorisées par composant :
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
 * Certains composants de ces tableaux autorisent soit l'export des données, soit l'import
 * des données, soit les deux, soit aucun des deux. Certains composants n'ont aucun comporte -
 * ment lorsque j'y dépose des données... Ceci est dû à leur complexité et à leur mode de 
 * fonctionnement. Par exemple, donner un comportement par défaut à un JTree n'est pas une
 * mince affaire. De ce fait, le comportement est laissé aux bons soins du développeur, en
 * l'occurence, moi. 
 * Par contre, je dois garder en tête que "drag" inclue deux notions implicites : le 
 * "drag-déplacement" et le "drag-copie". En fait, le drag'n drop peut avoir plusieurs effets :
 * 	-	la copie
 * 	-	le déplacement
 * 
 * Par exemple, lorsque sous Windows, sans changer de disque dur, le drag consiste à déplacer
 * le contenu intégralement (==> couper/coller), sauf si je maintiens en même temps la touche
 * CTRL (==> copier/coller).
 * 
 * Je peux également activer ces fonctionnalités pour des composants qui au départ ne les au -
 * torisent pas, comme JLabel par exemple.
 * Mais pour cela, faudra aller derrière les coulisses...
 * 
 * II./ Fonctionnement
 * 
 * 1.) Principes de base
 * 
 * Le transfert des données entre deux composants  se fait grâce à trois composants essentielles:
 * => un composant d'origine
 * => un objet transitant entre les composants (des données transférées)
 * => un composant cible
 * 
 * En effet, pendant l'opération drag'n drop, les données transitent d'un composant à un autre
 * via un objet. Dans l'API Swing, le mécanisme de drag'n drop est encapsulé dans l'objet
 * JComponent dont tous les objets graphiques héritent, ce qui signifie que tous les objets
 * graphiques peuvent implémenter cette fonctionnalité.
 * Afin d'activer le drag'n drop sur un composant qui ne le permet pas par défaut, je dois
 * utiliser la méthode :
 * 
 * setTransferHandler(TransferHandler newHandler) de l'objet JComponent. 
 * 
 * Cette méthode prend un objet TransferHandler en paramètre : c'est celui-ci qui lance le 
 * mécanisme de drag'n drop.
 * 
 * Les composants du tableau récapitulatif (hormis le JLabel) ont tous un objet TransferHandler
 * par défaut, d'où la possibilité d'activer le drag'n drop avec la méthode setDragEnabled().
 * Afin de l'activer également sur un JLabel, je dois lui spécifier un objet TransferHandler
 * réalisé par mes soins. 
 * 
 * Attention, toutefois ! Je peux définir un objet TransferHandler pour un composant possédant
 * déjà un comportement par défaut, mais cette action supplantera le mécanisme par défaut du
 * composant : il faut donc faire preuve de prudence !
 * 
 * 2.) Activer le drag'n drop du JLabel
 * 
 * Afin de lui ajouter les fonctionnalités voulues, je dois lui affecter un nouveau 
 * TransferHandler. Une fois ce nouvel objet lui sera assigné, je lui ajouterai un événement
 * souris afin de lancer l'action de drag'n drop : l'objet TransferHandler ne permettant que 
 * le transit des données (il ne gère pas les événements !). Dans mon événement, je vais juste
 * récupérer le composant initiateur du drag, récupérer son objet TransferHandler et 
 * invoquer sa méthode :
 * 		-	exportAsDrag(JComponent comp, InputEvent event, int action)
 * 
 * Réf Fenetre2
 * 
 * Note : l'objet de transfert n'a pas de constructeur sans argument ! Cette instruction par
 * exemple ne compilera pas : 
 * new TransferHandler();
 * Par contre, le constructeur utilisé pour le JLabel (new TransferHandler("text")) fonctionne
 * parfaitement. Pourquoi ? Tout simplement parce que la chaîne de caractères passée en 
 * paramètre correspond à une propriété JavaBean utilisable par l'objet.
 * 
 * Un JavaBean est un objet Java répondant à certains critères de construction :
 * 
 * 	-	la classe doit être Serializable pour pouvoir sauvegarder et restaurer l'état des 
 *			instances de cette classe
 *	-	la classe doit posséder un constructeur sans arguments (constructeur par défaut)
 *	-	les propriété privées de la classe (variable d'instance) doivent être accessibles 
 *			publiquement via des accesseurs (set et get) suivies du nom de la propriété avec
 *			la 1re lettre transformée en majuscule
 *	-	la classe doit contenir des méthodes d'interception d'événements nécessaires
 *
 * En fait, mon objet de transfert va utiliser la propriété "text" de mon objet JLabel, ceci
 * afin de récupérer son contenu et de le faire transiter. Je verrai plus loin comment faire
 * quand je ne connais pas le nom de la propriété...
 * 
 * Ensuite, j'ai récupéré l'objet TransferHandler (déjà affecté) depuis mon composant : avec
 * un getter.
 * 
 * Là où les choses deviennent intéressantes, c'est lorsque j'invoque la méthode :
 * handle.exportAsDrag(comp, event, TransferHandler.COPY);
 * 
 * C'est cette instruction qui amorce réellement le drag'n drop. Les trois paramètres servent
 * à initialiser les actions à effectuer et à déterminer quand et sur qui les faire :
 * 		-	le 1er paramètre indique le composant contenant les données à déplacer
 * 		-	le 2nd paramètre indique à mon objet l'événement sur lequel il doit déclencher le
 * 				transfert
 * 		-	le dernier indique l'action qui doit être effectuée : copie, déplacement, rien...
 * 
 * Comme cela a été mentionné plus haut, il existe plusieurs types d'actions qui peuvent
 * être effectuées lors d'un drop, celles-ci sont paramétrables via l'objet TransferHandler :
 * 
 * 	-	TransferHandler.COPY : n'autorise que la copie des données vers le composant cible
 * 	-	TransferHandler.MOVE : n'autorise que le déplacement des données vers le comp cible
 * 	-	TransferHandler.LINK : n'autirise que l'action lien sur les données du composant cible,
 * 			cela revient à créer un raccourci
 * 	-	TransferHandler.COPY_OR_MOVE : autorise la copie et le déplacement
 * 	-	TransferHandler.NONE : n'autorise rien
 * 
 * Attention, l'objet TransferHandler n'accepte que les action COPY lorsqu'il est instancié
 * avec le paramètre "text" : si je modifie la valeur ici, mon drag ne fonctionnera plus.
 * 
 * Est-ce que cela veut dire que l'option drag'n drop de mon JLabel sera restreinte ?
 * 
 * Je vais devoir encore approfondir mes connaissances...
 * 
 * III./ Créer son propre TransferHandler
 * 
 * Afin de personnaliser le drag'n drop pour mon composant, je vais devoir mettre les mains
 * dans le cambouis. La classe TransferHandler fait pas mal de choses dans mon dos et, tout
 * comme les modèles de composants (cf. JTree, JTable), dès lors que j'y mets les mains, tout
 * sera à ma charge.
 * 
 * Réf diagramme.png ==> représentation simplifiée de la classe TransferHandler.
 * 
 * Voici les méthodes à redéfinir de l'objet TransferHandler : 
 * 	-	public boolean canImport(TransferHandler.TransferSupport info) :
 * 			 méthode permettant à l'objet de savoir si les données reçues via un drop sont 
 * 			 autorisées à être importées
 * 	-	public boolean importData(TransferHandler.TransferSupport support) :
 * 			 c'est ici que l'insertion des données dans mon composant est réalisée
 * 	-	protected void exportDone(JComponent c, Transferable t, int action) : 
 * 			 Cette méthode est invoquée à la fin de l'action DROP. Si des actions sont à faire
 * 			 ensuite, c'est ici qu'il faudra coder le comportement désiré
 * 	-	protected Transferable createTransferable(JComponent c) :
 * 			Dans cette méthode, je vais créer l'objet utilisé par le système de drag'n drop
 * 			afin de faire circuler les données entre les composants. Je peux voir qu'il 
 * 			s'agit d'un objet Transferable.
 * 	-	public int getSourceActions(JComponent c) : 
 * 			cette méthode est utilisée afin de déterminer le comportement du composant 
 * 			vis-à-vis du drag'n drop : je retrouverai mes variables statiques COPY, MOVE, 
 * 			LINK, COPY_OR_MOVE ou NONE
 * 
 * Commençons par définir le comportement souhaité pour mon composant : le déplacement. Cela
 * se fait via la méthode public int getSourceActions(JComponent c). Je vais utiliser les
 * variables statiques de la classe mère pour définir l'action autorisée :
 * public int getSourceActions(JComponent c){
 * 		return MOVE;
 * }
 * 
 * Réf MyTransferHandler
 * 
 * Maintenant, je vais m'assurer qu'il sera toujours possible d'importer des données d'un
 * autre composant en les déposant dessus. Pour cela, je vais redéfinir les méthodes d'import
 * des données public boolean canImport(TansferHandler.TransferSupport info) et public 
 * boolean importData(TransferHandler.TransferSupport support). 
 * Note : ce paramètre bizarre est une classe interne, TransferSupport. Cet objet a un rôle 
 * très important => la communication entre les composants. C'est lui qui véhicule l'objet
 * encapsulant mes données. C'est aussi lui, pour des composants plus complexes tels qu'un
 * tableau, un arbre ou une liste, qui fournit l'emplacement où a eu lieu l'action drop.
 * 
 * Voici ce que va contenir ma méthode :
 * public boolean canImport(TransferHandler.TransferSupport info){
 * 		// je contrôle si les données reçues sont d'un type autorisé, ici string
 * 		if(!info.isDataFlavorSupported(DataFlavor.stringFlavor))
 * 			return false;
 * 		return true;
 * }
 * 
 * L'objet TransferSupport a une méthode permettant de contrôler le type de données supportées
 * par mon drag'n drop. Une liste de "type MIME" (Multipurpose Internet Mail Extensions est 
 * une façon de typer certains fichiers commes les images, les PDF, etc.) est disponible dans 
 * l'objet DataFlavor. Ici, j'ai utilisé DataFlavor.stringFlavor, qui signifie "chaîne de 
 * caractères". Voici la liste des éléments disponibles via l'objet DataFlavor :
 * 
 * 	-	DataFlavor.javaSerializedObjectMimeType : 
 * 			autorise un objet Java sérialisé correspondant au type MIME "application/x-java-
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
 * 			autorise soit une chaîne de caractères, soit la classe java.lang.String 
 * 
 * La seconde étape de ma démarche consiste à autoriser l'import de données vers mon composant
 * grâce à la méthode public boolean imortData(TransferHandler.TransferSupport support). 
 * Réf MyTransferHandler
 *  
 * IV./ Activer le drop sur un JTree
 * 
 * Je vais être confronté au problème de positionnement du drop sur mon composant. Cependant,
 * je dispose de l'objet dont le rôle est d'informer sur la position du drop : l'objet
 * TransferSupport.
 * Avant de poursuivre avec cette voie, il faut définir l'action qui doit être effectuée par
 * mon composant lors du dépôt de mes données. C'est possible grâce à l'objet DropMode que
 * je peux utiliser via la méthode setDropMode(DropMode dropMode). 
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
 * Certains modes sont utilisables par des tableaux, d'autres non... C'est grâce à cela que 
 * je vais spécifier le mode de fonctionnement de mon JTree.
 * 
 * A présent, il ne reste plus qu'à découvrir comment et surtout où insérer le nouvel élément.
 * C'est là que le TransferSupport entre en jeu. Cet objet permet de récupérer un objet
 * DropLocation contenant toutes les informations nécessaires au bon positionnement des 
 * données dans le composant cible.
 * En fait, par l'objet TransferSupport, je peux déduire un objet DropLocation propre à
 * mon composant, par exemple :
 *  //pour récupérer des info importantes sur un JTree
 *  JTree.DropLocation dl = (JTree.DropLocation)myTransferSupport.getDropLocation();
 *  //Pour récupérer des info importantes sur un JTable
 *  JTable.DropLocation dl = (JTable.DropLocation)myTransferSupport.getDropLocation();
 *  //POur récupérer des info importantes sur un JList
 *  JList.DropLocation dl = (JList.DropLocation)myTransferSupport.getDropLocation();
 *  
 * L'avantage de ces spécifications, c'est qu'elles permettent d'avoir accès à des info fort
 * utiles :
 * 
 * JList.DropLocation			JTree.DropLocation				JTable.DropLocation
 * isInsert						getChildIndex					isInsertRow
 * getIndex						getPath							isInsertColumn
 * 																getRow
 * 																getColumn
 * 
 * Réf MyTransferHandler2
 * Réf TreeTransferHandler
 * Réf TreeDragDemo (=>fenetre)
 * 
 * V./ Effet de déplacement
 * 
 * Il s'agira ici de simuler le déplacement de mes composants sur mon IHM. 
 * En fait, le principe revient à définir un GlassPane à ma fenêtre, composant personnalisé
 * que je vais hériter de JPanel. C'est lui qui va se charger de dessiner les images des
 * composants sur sa surface, dont j'aurai au préalable défini la transparence. Sur chaque
 * composant, je vais devoir définir les actions à effectuer à chaque événement souris : deux
 * classes sont codées à cet effet... Ensuite, il ne restera plus qu'à faire le test.
 * 
 * Réf fr.effet.dep => MyGlassPane
 * 					=> réf MouseGlassListener
 * 					=> réf MouseGlassMotionListener
 * 					=> réf Fenetre
 * 
 * Attention, pour des composants comme les JTree, JTable ou autres, j'aurai certainement
 * à faire des modifications pour que ça fonctionne !
 *  
 */
public class Main_drag {

	public static void main(String[] args) {
		

	}

}
