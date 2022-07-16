/*
 * 									Les interfaces de tableaux
 * 
 * Voici un autre composant assez complexe : le tableau. Celui-ci fonctionne un peu comme le 
 * JTree vu précédemment.
 * Les choses se compliquent dès que l'on doit manipuler les données à l'intérieur du 
 * tableau, car Java impose de séparer strictement l'affichage et les données dans le code.
 * 
 * I./ Premiers pas
 * 
 * Les tableaux sont des composants qui permettent d'afficher des données de façon structurée.
 * 
 * Réf Fenetre1
 * 
 * J'instancie donc l'objet JTable en lui passant un tableau bidimensionnel d'Objects (repré -
 * sentant les données du tableau) et un tableau de String (pour les titres de colonnes), comme
 * suit :
 * JTableau tableau = new JTable(data, titles);
 * 
 * Attention : les titres de mes colonnes peuvent être de type String ou de type Object, alors
 * que les données sont obligatoirement de type Object.
 * 
 * Je verrai un peu plus loin qu'il est possible de mettre plusieurs types d'éléments dans un 
 * tableau. Pour l'instant, j'essaye de comprendre comment fonctionne un tableau.
 * 
 * Attention : au moment d'ajouter le tableau au contentPane, je l'ai mis dans un scroll, sans
 * quoi les titres n'apparaîtraient pas. 
 * En effet, le scroll indique automatiquement au tableau l'endroit où il doit afficher ses
 * titres ! Sans ce scroll, je serais obligé de préciser où afficher l'en-tête du tableau,
 * comme ceci :
 *  //J'indique que l'en-tête doit se situer au NORD, donc au-dessus
 *  this.getContentPane().add(tableau.getTableHeader(), BorderLayout.NORTH);
 *  //et le corps au centre
 *  this.getContentPane().add(tableau);
 *  
 * A présent, entrons dans le vif du sujet
 * 
 * II./ Gestion de l'affichage
 * 1.) Les cellules
 * 
 * Mes tableaux sont constitués de cellules. Celles-ci sont constituées de données que j'ai
 * mises dans le tableau d'Object et de String, et peuvent être retrouvées par leur coordonnées
 * (x et y) où x correspond au numéro de la ligne et y au numéro de la colonne. Une cellule
 * est donc l'intersection d'une ligne et d'une colonne.
 * Afin de modifier une cellule, il faut récupérer la ligne et la colonne auxquelles elle
 * appartient.
 * Mais tout d'abord, commençons par changer la taille d'une colonne et d'une ligne.
 * Réf Fenetre1
 * 
 * Les indices des lignes et des colonnes d'un objet JTable commencent à 0.
 * Je constate que la ligne et la colonne concernée changent de taille lors du clic sur les
 * boutons. Je viens donc de voir comment changer la taille des cellules de façon dynamique.
 * L'instruction suivante peut être déconcertante :
 * tableau.getColumnModel().getColumn(i)
 * En fait, je dois savoir que c'est un objet qui fait le lien entre mon tableau et mes données.
 * Celui-ci est ce qu'on appelle un modèle de tableau (cela rappelle les arbres...). L'objet
 * en question s'appelle JTableModel qui par ailleurs permet de faire des choses très intéres -
 * santes. C'est lui qui stocke mes données. Toutes mes données !
 * 
 * Tous les types héritant de la classe Object sont acceptées.
 * Réf Fenetre2
 * 
 * ... mais le rendu laisse à désirer.
 * Pour être le plus flexible possible, on doit créer son propre modèle qui va stocker les 
 * données du tableau. Il suffit de créer une classe héritant de AbstractTableModel qui est
 * une classe abstraite.
 * 
 * Réf Fenetre2
 * Résultat => je ne vois plus les titres des colonnes. Cela est dû au fait que je n'ai redéfini
 * que les méthodes abstraites de la classe AbstractTableModel. Pour faire reapparaître les
 * titres, il faut redéfinir la méthode (de la classe interne ZModel) :
 * getColumnName(int col); 		==> comme ceci :
 * public String getColumnName(int col){
 * 		return this.title[col];
 * }
 * 
 * Je peux également transformer assez facilement mes boléens du tableau en cases à cocher en
 * redéfinissant une méthode dans mon modèle, et le reste étant automatique. Cette méthode
 * en question permet de retourner la classe du type de valeur d'un modèle et de transformer
 * mes boléens en cases à cocher... Au moment où mon objet crée le rendu, il invoque cette
 * méthode et s'en sert pour créer certaines choses...
 * Voici la méthode en question :
 * getColumnClass(int col); 		 => cette méthode renvoie un objet Class.
 * 
 * Réf Fenetre2
 * 
 * Note : mes cellules ne sont plus éditables ! En fait, je dois aussi informer mon modèle 
 * qu'il faut avertir l'objet JTable que certaines cellules peuvent être éditées et d'autres
 * pas (le bouton par exemple). Pour ce faire, il faut redéfinir la méthode (de ZModel) :
 * isCellEditable(int row, int col)  => celle-ci, dans la classe mère, retourne systématiquement
 * false...
 * 
 * Réf Fenetre2
 * Mes cellules sont de nouveau éditables. Cependant, je n'ai pas précisé au modèle que la
 * cellule contenant le bouton n'est pas censée être éditable... Comment régler ce problème ?
 * Grâce à la méthode getClass() de tout objet Java. 
 * JE PEUX DETERMINER DE QUELLE CLASSE EST ISSU MON OBJET GRÂCE AU MOT CLé :
 * 
 * ***********************     instanceof     ************************************************
 * 
 * Victoire! Mes cellules sont de nouveau éditables, sauf les boutons.
 * 
 * Pour faire enfin apparaître un vrai bouton (et non pas un toString() de l'objet JButton),
 * je dois utiliser un objet qui s'occupe de gérer le contenu et la façon dont celui-ci est 
 * affiché ! Et cet objet n'est rien d'autre qu'un DefaultTableCellRenderer
 * 
 * 2.) Contrôler l'affichage
 * 
 * Je dois bien distinguer un TableModel et un DefaultTableCellRenderer. Le 1er fait le lien
 * entre les données et le tableau, tandis que le second s'occupe de gérer l'affichage dans
 * les cellules.
 * 
 * Le but est de définir une nouvelle façon de dessiner les composants dans les cellules. En
 * définitive, je ne vais pas vraiment faire cela, mais dire à mon tableau que la valeur 
 * contenue dans une cellule donnée est un composant (bouton ou autre). Il suffit de créer une
 * classe héritant de DefaultTableCellRenderer et de redéfinir la méthode :
 * public Component getTableCellRendererComponent(JTable table, Object value, 
 * boolean isSelected, boolean hasFocus, int row, int column).
 * 
 * Il y en a, des paramètres ! Mais dans le cas qui m'intéresse, je n'ai besoin que d'un seul
 * d'entre eux : value. Cette méthode retourne un objet Component. Je vais seulement spécifier
 * le type d'objet dont il s'agit suivant le cas.
 * 
 * Réf TableComponent
 * 
 * Une fois cette classe créée, il suffit de signaler à mon tableau qu'il doit utiliser ce
 * rendu de cellules grâce à l'instruction suivante :
 * tableau.setDefaultRenderer(JButton.class, new TableComponent());
 * 
 * Le 1er paramètre permet de dire à mon tableau de faire attention à ce type d'objet et enfin,
 * le second lui dit d'utiliser ce modèle de cellules.
 * ==> Mes boutons apparaîssent finalement ! 
 * A présent, essayons aussi de mettre ET faire afficher des JComboBox dans notre tableau...
 * Voici la solution : dans la méthode getTableCellRendererComponent(JTable table, Object
 * value, boolean isSelected, boolean hasFocus, int row, int column), il faudra spécifier
 * de retourner une valeur castée en JComboBox en cas de rencontre avec une JComboBox (i.e.
 * instanceof JComboBox...).
 * Mais ce n'est que la 1re partie de la solution, car le tableau ne fera attention qu'aux
 * JButton si on laisse setDefaultRenderer telle quelle ==> il faut simplement changer
 * JButton en JComponent.
 * 
 * Cependant, il y a un autre problème  => MES COMPOSANTS SONT INERTES ! C'est dû au fait que
 * je vais devoir gérer moi-même la façon dont réagissent les cellules. Mais, avant
 * d'aborder ce point, je vais me pencher sur une autre façon d'afficher correctement des
 * composants dans un JTable.
 * _______________________________________________________________________________________
 * Je laisse de côté la classe servant de modèle et me concentrer sur mes composants.
 * Je reviens à un code plus sobre (une fenêtre avec seulement le JTableau)
 * Réf Fenetre3
 * De là, je vais créer une classe qui affichera un bouton dans les cellules de la seconde
 * colonne et une combo dans les cellules de la troisième colonne... Le principe est
 * simple : créer une classe qui héritera de JButton et implémentera l'interface
 * TableCellRenderer. Je vais ensuite dire à mon tableau qu'il doit utiliser ce type de
 * rendu pour la 2e colonne.
 * Réf ButtonRenderer
 * Il me suffit maintenant de mettre à jour le tableau grâce à l'instruction :
 * this.tableau.getColumn("Age").setCellRenderer(new ButtonRenderer);  => on récupère la
 * colonne grâce à son titre, puis on applique le rendu !
 * Mon bouton est de nouveau éditable, mais ce problème sera réglé par la suite... Pour le 
 * rendu de la cellule numéro 3 :
 * 
 * III./ Interaction avec l'objet JTable
 * 
 * Le composant le plus difficile à utiliser dans un tableau, entre un bouton et une combo...
 * c'est le bouton !
 * Eh oui, la combo est gérée presqu'automatiquement, alors qu'il me faudra dire aux boutons
 * ce qu'ils doivent faire... Pour arriver à cela, je vais créer une classe qui permettra à 
 * mon tableau d'effectuer des actions spécifiques grâce aux boutons.
 * 
 * Réf ButtonEditor
 * 
 * Maintenant, afin d'utiliser cet objet avec mon tableau, je vais indiquer à ce dernier 
 * l'existence de cet éditeur dans la colonne correspondante avec cette instruction :
 * this.tableau.getColumn("Age").setEditor(new ButtonEditor(new JCheckBox)); => (réf Fenetre3)
 * 
 * Là, je peux voir que lorsque je clique sur un bouton, la valeur située juste à gauche
 * est modifiée (à cause de table.setValueAt() dans le méthode actionPerformed()...).
 * L'utilisation est donc très simple. Et la gestion des combos est encore plus aisée.
 * Précédemment, j'ai développé une classe permettant d'afficher la combo. Cependant,
 * il y a un moyen bcp plus simple... Comme j'ai pu constater, la classe DefaultCellEditor
 * peut prendre un objet en paramètre : dans l'exemple du ButtonEditor, il utilisait un
 * JCheckBox. Je dois savoir que cet objet accepte d'autres types de paramètres :
 *  *	un JComboBox
 *  *	un JTextField
 * Je peux donc utiliser l'objet DefaultCellEditor directement en lui passant une combo en
 * paramètre... Je vais aussi enlever l'objet permettant d'afficher correstement la combo
 * afin de pouvoir mieux juger de l'efficacité de cette méthode.
 * 
 * En fait, sans le ComboRenderer, ma cellule se "transforme" en combo lorsque je clique
 * dessus. Lorsque le tableau sent une action sur la cellule, il utilise l'éditeur que 
 * j'ai spécifié pour celle-ci.
 * 
 * Remarque : lorsque j'utilise aussi le ComboRenderer en même temps, il n'y a pas de
 * synchronisation entre ce dernier et le DefaultCellEditor assigné, i.e. ils n'affichent pas
 * la même chose.
 * 
 * Note : le fait que les boutons aient un drôle de comportement est dû à l'affectation
 * des comportement spéciaux à mon tableau... Il faut donc définir un modèle à utiliser afin
 * de bien définir tous les points comme l'affichage, la mise à jour, etc.
 * 
 * Je vais donc utiliser un modèle de tableau personnalisé
 * 
 * Réf Fenetre4
 * 
 * En effet, j'ai construit mon tableau par le biais de mon modèle, ce qui implique que je
 * devrai également passer par le modèle pour modifier le tableau ! Conséquence directe : il
 * va falloir modifier un peu ma classe ButtonEditor
 * 
 * Réf ButtonEditor4
 * 
 * Voilà, le bug d'affichage a disparu.
 * 
 * IV./ Ajouter des lignes et des colonnes
 * 
 * Voici une autre façon d'initialiser un tableau :
 * JTable tableau = new JTable(new DefaultTableModel(data, title));
 * L'intérêt ? C'est très simple : l'ajout et suppression dynamiques d'entrées dans un tableau
 * se font via un modèle de rendu. Cependant, avec cette notation, jéconomise une ligne de 
 * code et j'ai la possibilité d'affecter diverses tâches à mon modèle de rendu, comme par
 * exemple, formater les données...
 * 
 * Dans un premier temps, ajoutons et retirons des lignes à mon tableau. Je garderai le
 * même code que précédemment avec deux ou trois ajouts :
 *  *	le bouton pour ajouter une ligne
 *  *	le bouton pour supprmer une ligne 
 *  
 *  Le modèle par défaut défini lors de la création du tableau me donne accès à deux méthodes 
 *  fort utiles :
 *  -	addRow(Object[][] lineData) : ajoute une ligne au modèle et met automatiquement à jour
 *  		le tableau
 *  -	removeRow(int row) : efface une ligne du modèle et met automatiquement à jour le
 * 		tableau
 * ==> BULLSHIT !! JE DOIS REDEFINIR CES DEUX METHODES DANS LA CLASSE PERSONNALISéE ZModel
 * 
 * Afin de pouvoir utiliser ce modèle, je vais devoir le récupérer. En fait, c'est mon tableau
 * qui va me le fournir en invoquant la méthode getModel() qui retourne un objet TableModel.
 * Attention, un cast sera nécessaire afin de pouvoir utiliser l'objet récupéré ! 
 * Par exemple :
 * ((ZModel)table.getModel()).removeRow(int row);
 * 
 * Réf Fenetre4
 * 
 */
public class Main_tableaux {

	public static void main(String[] args) {
		int b = 32;
		byte bit = 111;
		int b2 = 0b1000_000_000;
		System.out.println(b2);

	}

}
