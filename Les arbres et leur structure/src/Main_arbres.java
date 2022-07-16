/*
 * 										Les arbres et leur structure
 * 
 * Il ne s'agira pas ici des arbres du monde végétal.
 * 
 * I./ La composition des arbres
 * 
 * L'objet JTree est un arbre. Et la chose bien pratique avec cet objet, c'est que, même s'il
 * ne ressemble pas à un chêne ou à tout autre arbre, il est composé de la même façon. Tout 
 * comme un arbre, il est constitué d'éléments similaires à :
 * 	-	des racines
 * 	-	un tronc
 * 	-	des branches
 * 	-	des feuilles
 * 
 * L'objet JTree se base sur la même architecture. J'aurai donc :
 * 	-	une racine : le répertoire le plus haut de la hiérarchie
 * 	-	des troncs : dossiers de la racine (Noeud n°1, 2,...)
 * 	-	une ou plusieurs branches : un ou plusieurs sous-répertoires (Fichier enfant n°1, ...)
 * 	-	une ou plusieurs feuilles : éléments se trouvant en bas de la hiérarchie (Sous-fichier 
 * 			enfant n°1,...)
 * 
 * Réf Fenetre1
 * 
 * En effet, avec tous ces objets (notammet DefaultMutableTreeNode), on construit une véritable 
 * hiérarchie avant de créer et d'afficher l'arbre. Ce type d'objets est tout indiqué pour 
 * lister des fichiers ou des répertoires. C'est avec un arbre que je vais afficher mon 
 * arborescence de fichiers.
 * 
 * Réf Fenetre2
 * 
 * Pas mal du tout, mais du coup, le dossier "Racine" ne correspond à rien (il contient tout
 * le reste, mais bon...) ! Heureusement, il existe une méthode dans l'objet
 * JTree qui permet de ne pas afficher la racine d'une arborescence :
 * setRootVisible(boolean ok);  
 * Il suffit donc de rajouter l'instruction setRootVisible(false) à la fin de la méthode 
 * listRoot() (méthode faite maison) de l'objet Tree(), juste avant d'ajouter mon arbre au
 * content pane.
 * 
 * Je sais créer et afficher un arbre, voyons maintenant comment interagir avec.
 * 
 * II./ Des arbres qui me parlent
 * 
 * Je vais implémenter l'interface TreeSelectionListener, qui ne contient qu'une méthode
 * à redéfinir : 
 * public void valueChanged(TreeSelectionEvent event);
 * 
 * Réf Fenetre2
 * 
 * Mon arbre est maintenant réactif : lorsque je sélectionne un fichier ou un dossier, le
 * nom de ce dernier s'affiche. Cela se fait grâce à la méthode (de JTree) :
 * getLastSelectedPathComponent() : qui retourne un Object correspondant au dernier point
 * de l'arbre qui a été cliqué. Il ne reste plus qu'à utiliser la méthode toString() afin de
 * retourner son libellé.
 * 
 * Il serait plus intéressant de connaître plutôt le chemin d'accès (absolu) du noeud cliqué 
 * dans l'arbre.
 * Je peux obtenir des informations complémentaires sur une feuille ou une branche en recourant
 * à l'objet File, par exemple. L'objet TreeSelectionEvent passé en paramètre de la méthode
 * de l'interface apporte de précieux renseignements, dont la méthode getPath() qui me 
 * retourne un objet TreePath. Ce dernier contient les objets correspondant aux noeuds du
 * chemin d'accès à un point dans l'abre.
 * Réf Fenetre2
 * Je n'ai modifié que la classe anonyme qui gère l'événement déclenché sur l'abre.
 * Je peux maintenant avoir le chemin complet d'un point de l'arbre (un fichier ou un dossier)
 * grâce à un simple clic. 
 * Et vu que j'interagis avec les fichiers de mon système ==> je peux en savoir plus. Je vais
 * ajouter un "coin information" à droite de mon arbre, dans un conteneur à part.
 * 
 * Réf Fenetre3
 * 
 * Voyons maintenant comment personnaliser l'affichage de l'arbre.
 * 
 * III./ Décorer les arbres
 * 
 * J'ai la possibilité de changer les icônes des répertoires et des fichiers, tout comme celle
 * d'ouverture et de fermeture. Cette opération est simple à réaliser : il suffit d'utiliser un
 * objet DefaultTreeCellRenderer (qui est une sorte de modèle). Et voici les méthodes de cet
 * objet pour définir les icônes :
 * 	-	setClosedIcon(ImageIcon img) : pour l'icône "fermé"
 * 	-	setOpenIcon(ImageIcon img) : pour l'icône "ouvert"
 * 	-	setLeafIcon(ImageIcon img) : pour l'icône "feuille"
 * 
 * Et ensuite, une fois que l'on a instancié le JTree, il suffit de spécifier à celui-ci qu'il
 * lui faut utiliser ce modèle en utilisant la méthode :
 * setCellRenderer(DefaultTreeCellRenderer cellRenderer);
 * Réf Fenetre4
 * 
 * Il existe une autre façon de changer l'affichage (le design) de l'application. Chaque
 * système d'exploitation possède son propre "design". D'ailleurs, mes applications Java ne
 * ressemblent pas du tout à ce que mon OS propose d'habitude. Les couleurs, mais aussi la 
 * façon dont elles sont dessinées... Mais il y a un moyen de pallier ce problème : utiliser le
 * "look and feel" de mon OS.
 * 
 * J'ai rajouté ces lignes de code dans le constructeur de mon objet, avant l'instruction
 * setVisible(true) :
 * try{
 * 		//On force à utiliser le "look and feel" du système
 * 		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName();
 * 		//Ici, on force tous les composants de ma fenêtre (this) à se redessiner avec le 
 * 		//"look and feel" du système
 * 		SwingUtilities.updateComponentTreeUI(this);
 * } 
 * catch(InstantiationException e) {}
 * catch(ClassNotFoundException e) {}
 * catch(UnsupportedLookAndFeelException e) {}
 * catch(IllegalAccessException e) {}
 * 
 * Bien sûr, je peux utiliser d'autres "look and feel" que ceux de mon système et de Java. 
 * Réf Fenetre2
 * 
 * IV./ Modifier le contenu de mes arbres
 * 1.) Modifier le nom d'un noeud
 * 
 * C'est maintenant que les choses se compliquent. Il va falloir faire la lumière sur 
 * certaines choses... 
 * Mon JTree est en fait composé de plusieurs objets. Voici une liste des objets que je serai
 * amené à utiliser avec ce composant (il y a cinq interfaces et une classe concrète ) :
 * 	-	TreeModel : c'est lui qui contient les noeuds de mon arbre
 * 	-	TreeNode : noeuds et structure de mon arbre
 * 	-	TreeSelectionModel : modèle de sélection de tous mes noeuds
 * 	-	TreePath : objet qui permet de connaître le chemin d'un noeud dans l'arbre.
 * 	-	TreeCellRenderer : interface permettant de modifier l'apparence d'un noeud
 * 	-	TreeCellEditor : éditeur utilisé lorsqu'un noeud est éditable
 * 
 * Je vais commencer par quelque chose d'assez simple : modifier le libellé d'un noeud !
 * Il faudra commencer par le rendre éditable, via la méthode setEditable(Boolean bok) de mon
 * JTree. 
 * Attention, je serai peut-être amené à sauvegarder le nouveau nom de mon noeud. Il faudra
 * donc déclencher un traitement lors de la modification d'un noeud. Pour faire cela, je vais
 * utiliser l'objet TreeModel et l'écouter afin de déterminer ce qui se passe avec mon arbre.
 * Je vais donc utiliser un DefaultTreeModel (classe implémentant l'interface TreeModel) ainsi
 * qu'une implémentation de l'interface TreeModelListener afin d'écouter cet objet.
 * 
 * Réf Fenetre5
 * 
 * En effet, lorsque je change le nom d'un noeud, la méthode treeNodesChanged (de TreeModel-
 * Listener) est invoquée !
 * Note : afin de pouvoir changer le nom d'un noeud, je dois double-cliquer dessus avec un
 * intervalle d'une demi-seconde entre chaque clique... Si je double-clique trop vite, je
 * déplie le noeud.
 * 
 * 2.) Ajouter et supprimer des noeuds
 * 
 * Examinons maintenant la manière d'ajouter des noeuds à mon arbre. Pour ce faire, je vais
 * utiliser un bouton qui va me demander de spécifier le nom du nouveau noeud, via un
 * JOptionPane
 * 
 * Là non plus, rien de compliqué, excepté peut-être cette portion de code (dans la méthode
 * actionPerformed() du bouton "Ajouter") :
 * parentNode = (DefaultMutableTreeNode)arbre.getLastSelectedPathComponent();
 * childNode = new DefaultMutableTreeNode(nodeName);
 * parentNode.add(childNode);
 * model.insertNodeInto(childNode, parentNode, parentNode.getChildCount() - 1);
 * model.nodeChanged(parentNode);
 * 
 * => Explication :
 * 	-	Tout d'abord, je récupère le dernier noeud sélectionné (ligne 1)
 * 	-	Ensuite, je crée un nouveau noeud (ligne 2) et l'ajoute dans le noeud parent (ligne 3)
 * 	-	Cependant, je dois spécifier à mon modèle qu'il contient un nouveau noeud et donc
 * 			qu'il a changé (ligne 4 et 5)
 * 
 * Et pour supprimer un noeud, il suffirait d'appeler :
 *  model.removeNodeFromParent(DefaultMutableTreeNode nodeASupprimer);
 *  
 * Un jeu d'enfant
 *
 */
public class Main_arbres {

	public static void main(String[] args) {

	}

}
