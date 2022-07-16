/*
 * 										Les arbres et leur structure
 * 
 * Il ne s'agira pas ici des arbres du monde v�g�tal.
 * 
 * I./ La composition des arbres
 * 
 * L'objet JTree est un arbre. Et la chose bien pratique avec cet objet, c'est que, m�me s'il
 * ne ressemble pas � un ch�ne ou � tout autre arbre, il est compos� de la m�me fa�on. Tout 
 * comme un arbre, il est constitu� d'�l�ments similaires � :
 * 	-	des racines
 * 	-	un tronc
 * 	-	des branches
 * 	-	des feuilles
 * 
 * L'objet JTree se base sur la m�me architecture. J'aurai donc :
 * 	-	une racine : le r�pertoire le plus haut de la hi�rarchie
 * 	-	des troncs : dossiers de la racine (Noeud n�1, 2,...)
 * 	-	une ou plusieurs branches : un ou plusieurs sous-r�pertoires (Fichier enfant n�1, ...)
 * 	-	une ou plusieurs feuilles : �l�ments se trouvant en bas de la hi�rarchie (Sous-fichier 
 * 			enfant n�1,...)
 * 
 * R�f Fenetre1
 * 
 * En effet, avec tous ces objets (notammet DefaultMutableTreeNode), on construit une v�ritable 
 * hi�rarchie avant de cr�er et d'afficher l'arbre. Ce type d'objets est tout indiqu� pour 
 * lister des fichiers ou des r�pertoires. C'est avec un arbre que je vais afficher mon 
 * arborescence de fichiers.
 * 
 * R�f Fenetre2
 * 
 * Pas mal du tout, mais du coup, le dossier "Racine" ne correspond � rien (il contient tout
 * le reste, mais bon...) ! Heureusement, il existe une m�thode dans l'objet
 * JTree qui permet de ne pas afficher la racine d'une arborescence :
 * setRootVisible(boolean ok);  
 * Il suffit donc de rajouter l'instruction setRootVisible(false) � la fin de la m�thode 
 * listRoot() (m�thode faite maison) de l'objet Tree(), juste avant d'ajouter mon arbre au
 * content pane.
 * 
 * Je sais cr�er et afficher un arbre, voyons maintenant comment interagir avec.
 * 
 * II./ Des arbres qui me parlent
 * 
 * Je vais impl�menter l'interface TreeSelectionListener, qui ne contient qu'une m�thode
 * � red�finir : 
 * public void valueChanged(TreeSelectionEvent event);
 * 
 * R�f Fenetre2
 * 
 * Mon arbre est maintenant r�actif : lorsque je s�lectionne un fichier ou un dossier, le
 * nom de ce dernier s'affiche. Cela se fait gr�ce � la m�thode (de JTree) :
 * getLastSelectedPathComponent() : qui retourne un Object correspondant au dernier point
 * de l'arbre qui a �t� cliqu�. Il ne reste plus qu'� utiliser la m�thode toString() afin de
 * retourner son libell�.
 * 
 * Il serait plus int�ressant de conna�tre plut�t le chemin d'acc�s (absolu) du noeud cliqu� 
 * dans l'arbre.
 * Je peux obtenir des informations compl�mentaires sur une feuille ou une branche en recourant
 * � l'objet File, par exemple. L'objet TreeSelectionEvent pass� en param�tre de la m�thode
 * de l'interface apporte de pr�cieux renseignements, dont la m�thode getPath() qui me 
 * retourne un objet TreePath. Ce dernier contient les objets correspondant aux noeuds du
 * chemin d'acc�s � un point dans l'abre.
 * R�f Fenetre2
 * Je n'ai modifi� que la classe anonyme qui g�re l'�v�nement d�clench� sur l'abre.
 * Je peux maintenant avoir le chemin complet d'un point de l'arbre (un fichier ou un dossier)
 * gr�ce � un simple clic. 
 * Et vu que j'interagis avec les fichiers de mon syst�me ==> je peux en savoir plus. Je vais
 * ajouter un "coin information" � droite de mon arbre, dans un conteneur � part.
 * 
 * R�f Fenetre3
 * 
 * Voyons maintenant comment personnaliser l'affichage de l'arbre.
 * 
 * III./ D�corer les arbres
 * 
 * J'ai la possibilit� de changer les ic�nes des r�pertoires et des fichiers, tout comme celle
 * d'ouverture et de fermeture. Cette op�ration est simple � r�aliser : il suffit d'utiliser un
 * objet DefaultTreeCellRenderer (qui est une sorte de mod�le). Et voici les m�thodes de cet
 * objet pour d�finir les ic�nes :
 * 	-	setClosedIcon(ImageIcon img) : pour l'ic�ne "ferm�"
 * 	-	setOpenIcon(ImageIcon img) : pour l'ic�ne "ouvert"
 * 	-	setLeafIcon(ImageIcon img) : pour l'ic�ne "feuille"
 * 
 * Et ensuite, une fois que l'on a instanci� le JTree, il suffit de sp�cifier � celui-ci qu'il
 * lui faut utiliser ce mod�le en utilisant la m�thode :
 * setCellRenderer(DefaultTreeCellRenderer cellRenderer);
 * R�f Fenetre4
 * 
 * Il existe une autre fa�on de changer l'affichage (le design) de l'application. Chaque
 * syst�me d'exploitation poss�de son propre "design". D'ailleurs, mes applications Java ne
 * ressemblent pas du tout � ce que mon OS propose d'habitude. Les couleurs, mais aussi la 
 * fa�on dont elles sont dessin�es... Mais il y a un moyen de pallier ce probl�me : utiliser le
 * "look and feel" de mon OS.
 * 
 * J'ai rajout� ces lignes de code dans le constructeur de mon objet, avant l'instruction
 * setVisible(true) :
 * try{
 * 		//On force � utiliser le "look and feel" du syst�me
 * 		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName();
 * 		//Ici, on force tous les composants de ma fen�tre (this) � se redessiner avec le 
 * 		//"look and feel" du syst�me
 * 		SwingUtilities.updateComponentTreeUI(this);
 * } 
 * catch(InstantiationException e) {}
 * catch(ClassNotFoundException e) {}
 * catch(UnsupportedLookAndFeelException e) {}
 * catch(IllegalAccessException e) {}
 * 
 * Bien s�r, je peux utiliser d'autres "look and feel" que ceux de mon syst�me et de Java. 
 * R�f Fenetre2
 * 
 * IV./ Modifier le contenu de mes arbres
 * 1.) Modifier le nom d'un noeud
 * 
 * C'est maintenant que les choses se compliquent. Il va falloir faire la lumi�re sur 
 * certaines choses... 
 * Mon JTree est en fait compos� de plusieurs objets. Voici une liste des objets que je serai
 * amen� � utiliser avec ce composant (il y a cinq interfaces et une classe concr�te ) :
 * 	-	TreeModel : c'est lui qui contient les noeuds de mon arbre
 * 	-	TreeNode : noeuds et structure de mon arbre
 * 	-	TreeSelectionModel : mod�le de s�lection de tous mes noeuds
 * 	-	TreePath : objet qui permet de conna�tre le chemin d'un noeud dans l'arbre.
 * 	-	TreeCellRenderer : interface permettant de modifier l'apparence d'un noeud
 * 	-	TreeCellEditor : �diteur utilis� lorsqu'un noeud est �ditable
 * 
 * Je vais commencer par quelque chose d'assez simple : modifier le libell� d'un noeud !
 * Il faudra commencer par le rendre �ditable, via la m�thode setEditable(Boolean bok) de mon
 * JTree. 
 * Attention, je serai peut-�tre amen� � sauvegarder le nouveau nom de mon noeud. Il faudra
 * donc d�clencher un traitement lors de la modification d'un noeud. Pour faire cela, je vais
 * utiliser l'objet TreeModel et l'�couter afin de d�terminer ce qui se passe avec mon arbre.
 * Je vais donc utiliser un DefaultTreeModel (classe impl�mentant l'interface TreeModel) ainsi
 * qu'une impl�mentation de l'interface TreeModelListener afin d'�couter cet objet.
 * 
 * R�f Fenetre5
 * 
 * En effet, lorsque je change le nom d'un noeud, la m�thode treeNodesChanged (de TreeModel-
 * Listener) est invoqu�e !
 * Note : afin de pouvoir changer le nom d'un noeud, je dois double-cliquer dessus avec un
 * intervalle d'une demi-seconde entre chaque clique... Si je double-clique trop vite, je
 * d�plie le noeud.
 * 
 * 2.) Ajouter et supprimer des noeuds
 * 
 * Examinons maintenant la mani�re d'ajouter des noeuds � mon arbre. Pour ce faire, je vais
 * utiliser un bouton qui va me demander de sp�cifier le nom du nouveau noeud, via un
 * JOptionPane
 * 
 * L� non plus, rien de compliqu�, except� peut-�tre cette portion de code (dans la m�thode
 * actionPerformed() du bouton "Ajouter") :
 * parentNode = (DefaultMutableTreeNode)arbre.getLastSelectedPathComponent();
 * childNode = new DefaultMutableTreeNode(nodeName);
 * parentNode.add(childNode);
 * model.insertNodeInto(childNode, parentNode, parentNode.getChildCount() - 1);
 * model.nodeChanged(parentNode);
 * 
 * => Explication :
 * 	-	Tout d'abord, je r�cup�re le dernier noeud s�lectionn� (ligne 1)
 * 	-	Ensuite, je cr�e un nouveau noeud (ligne 2) et l'ajoute dans le noeud parent (ligne 3)
 * 	-	Cependant, je dois sp�cifier � mon mod�le qu'il contient un nouveau noeud et donc
 * 			qu'il a chang� (ligne 4 et 5)
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
