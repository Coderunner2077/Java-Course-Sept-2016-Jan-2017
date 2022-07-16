/*
 * 									Les interfaces de tableaux
 * 
 * Voici un autre composant assez complexe : le tableau. Celui-ci fonctionne un peu comme le 
 * JTree vu pr�c�demment.
 * Les choses se compliquent d�s que l'on doit manipuler les donn�es � l'int�rieur du 
 * tableau, car Java impose de s�parer strictement l'affichage et les donn�es dans le code.
 * 
 * I./ Premiers pas
 * 
 * Les tableaux sont des composants qui permettent d'afficher des donn�es de fa�on structur�e.
 * 
 * R�f Fenetre1
 * 
 * J'instancie donc l'objet JTable en lui passant un tableau bidimensionnel d'Objects (repr� -
 * sentant les donn�es du tableau) et un tableau de String (pour les titres de colonnes), comme
 * suit :
 * JTableau tableau = new JTable(data, titles);
 * 
 * Attention : les titres de mes colonnes peuvent �tre de type String ou de type Object, alors
 * que les donn�es sont obligatoirement de type Object.
 * 
 * Je verrai un peu plus loin qu'il est possible de mettre plusieurs types d'�l�ments dans un 
 * tableau. Pour l'instant, j'essaye de comprendre comment fonctionne un tableau.
 * 
 * Attention : au moment d'ajouter le tableau au contentPane, je l'ai mis dans un scroll, sans
 * quoi les titres n'appara�traient pas. 
 * En effet, le scroll indique automatiquement au tableau l'endroit o� il doit afficher ses
 * titres ! Sans ce scroll, je serais oblig� de pr�ciser o� afficher l'en-t�te du tableau,
 * comme ceci :
 *  //J'indique que l'en-t�te doit se situer au NORD, donc au-dessus
 *  this.getContentPane().add(tableau.getTableHeader(), BorderLayout.NORTH);
 *  //et le corps au centre
 *  this.getContentPane().add(tableau);
 *  
 * A pr�sent, entrons dans le vif du sujet
 * 
 * II./ Gestion de l'affichage
 * 1.) Les cellules
 * 
 * Mes tableaux sont constitu�s de cellules. Celles-ci sont constitu�es de donn�es que j'ai
 * mises dans le tableau d'Object et de String, et peuvent �tre retrouv�es par leur coordonn�es
 * (x et y) o� x correspond au num�ro de la ligne et y au num�ro de la colonne. Une cellule
 * est donc l'intersection d'une ligne et d'une colonne.
 * Afin de modifier une cellule, il faut r�cup�rer la ligne et la colonne auxquelles elle
 * appartient.
 * Mais tout d'abord, commen�ons par changer la taille d'une colonne et d'une ligne.
 * R�f Fenetre1
 * 
 * Les indices des lignes et des colonnes d'un objet JTable commencent � 0.
 * Je constate que la ligne et la colonne concern�e changent de taille lors du clic sur les
 * boutons. Je viens donc de voir comment changer la taille des cellules de fa�on dynamique.
 * L'instruction suivante peut �tre d�concertante :
 * tableau.getColumnModel().getColumn(i)
 * En fait, je dois savoir que c'est un objet qui fait le lien entre mon tableau et mes donn�es.
 * Celui-ci est ce qu'on appelle un mod�le de tableau (cela rappelle les arbres...). L'objet
 * en question s'appelle JTableModel qui par ailleurs permet de faire des choses tr�s int�res -
 * santes. C'est lui qui stocke mes donn�es. Toutes mes donn�es !
 * 
 * Tous les types h�ritant de la classe Object sont accept�es.
 * R�f Fenetre2
 * 
 * ... mais le rendu laisse � d�sirer.
 * Pour �tre le plus flexible possible, on doit cr�er son propre mod�le qui va stocker les 
 * donn�es du tableau. Il suffit de cr�er une classe h�ritant de AbstractTableModel qui est
 * une classe abstraite.
 * 
 * R�f Fenetre2
 * R�sultat => je ne vois plus les titres des colonnes. Cela est d� au fait que je n'ai red�fini
 * que les m�thodes abstraites de la classe AbstractTableModel. Pour faire reappara�tre les
 * titres, il faut red�finir la m�thode (de la classe interne ZModel) :
 * getColumnName(int col); 		==> comme ceci :
 * public String getColumnName(int col){
 * 		return this.title[col];
 * }
 * 
 * Je peux �galement transformer assez facilement mes bol�ens du tableau en cases � cocher en
 * red�finissant une m�thode dans mon mod�le, et le reste �tant automatique. Cette m�thode
 * en question permet de retourner la classe du type de valeur d'un mod�le et de transformer
 * mes bol�ens en cases � cocher... Au moment o� mon objet cr�e le rendu, il invoque cette
 * m�thode et s'en sert pour cr�er certaines choses...
 * Voici la m�thode en question :
 * getColumnClass(int col); 		 => cette m�thode renvoie un objet Class.
 * 
 * R�f Fenetre2
 * 
 * Note : mes cellules ne sont plus �ditables ! En fait, je dois aussi informer mon mod�le 
 * qu'il faut avertir l'objet JTable que certaines cellules peuvent �tre �dit�es et d'autres
 * pas (le bouton par exemple). Pour ce faire, il faut red�finir la m�thode (de ZModel) :
 * isCellEditable(int row, int col)  => celle-ci, dans la classe m�re, retourne syst�matiquement
 * false...
 * 
 * R�f Fenetre2
 * Mes cellules sont de nouveau �ditables. Cependant, je n'ai pas pr�cis� au mod�le que la
 * cellule contenant le bouton n'est pas cens�e �tre �ditable... Comment r�gler ce probl�me ?
 * Gr�ce � la m�thode getClass() de tout objet Java. 
 * JE PEUX DETERMINER DE QUELLE CLASSE EST ISSU MON OBJET GR�CE AU MOT CL� :
 * 
 * ***********************     instanceof     ************************************************
 * 
 * Victoire! Mes cellules sont de nouveau �ditables, sauf les boutons.
 * 
 * Pour faire enfin appara�tre un vrai bouton (et non pas un toString() de l'objet JButton),
 * je dois utiliser un objet qui s'occupe de g�rer le contenu et la fa�on dont celui-ci est 
 * affich� ! Et cet objet n'est rien d'autre qu'un DefaultTableCellRenderer
 * 
 * 2.) Contr�ler l'affichage
 * 
 * Je dois bien distinguer un TableModel et un DefaultTableCellRenderer. Le 1er fait le lien
 * entre les donn�es et le tableau, tandis que le second s'occupe de g�rer l'affichage dans
 * les cellules.
 * 
 * Le but est de d�finir une nouvelle fa�on de dessiner les composants dans les cellules. En
 * d�finitive, je ne vais pas vraiment faire cela, mais dire � mon tableau que la valeur 
 * contenue dans une cellule donn�e est un composant (bouton ou autre). Il suffit de cr�er une
 * classe h�ritant de DefaultTableCellRenderer et de red�finir la m�thode :
 * public Component getTableCellRendererComponent(JTable table, Object value, 
 * boolean isSelected, boolean hasFocus, int row, int column).
 * 
 * Il y en a, des param�tres ! Mais dans le cas qui m'int�resse, je n'ai besoin que d'un seul
 * d'entre eux : value. Cette m�thode retourne un objet Component. Je vais seulement sp�cifier
 * le type d'objet dont il s'agit suivant le cas.
 * 
 * R�f TableComponent
 * 
 * Une fois cette classe cr��e, il suffit de signaler � mon tableau qu'il doit utiliser ce
 * rendu de cellules gr�ce � l'instruction suivante :
 * tableau.setDefaultRenderer(JButton.class, new TableComponent());
 * 
 * Le 1er param�tre permet de dire � mon tableau de faire attention � ce type d'objet et enfin,
 * le second lui dit d'utiliser ce mod�le de cellules.
 * ==> Mes boutons appara�ssent finalement ! 
 * A pr�sent, essayons aussi de mettre ET faire afficher des JComboBox dans notre tableau...
 * Voici la solution : dans la m�thode getTableCellRendererComponent(JTable table, Object
 * value, boolean isSelected, boolean hasFocus, int row, int column), il faudra sp�cifier
 * de retourner une valeur cast�e en JComboBox en cas de rencontre avec une JComboBox (i.e.
 * instanceof JComboBox...).
 * Mais ce n'est que la 1re partie de la solution, car le tableau ne fera attention qu'aux
 * JButton si on laisse setDefaultRenderer telle quelle ==> il faut simplement changer
 * JButton en JComponent.
 * 
 * Cependant, il y a un autre probl�me  => MES COMPOSANTS SONT INERTES ! C'est d� au fait que
 * je vais devoir g�rer moi-m�me la fa�on dont r�agissent les cellules. Mais, avant
 * d'aborder ce point, je vais me pencher sur une autre fa�on d'afficher correctement des
 * composants dans un JTable.
 * _______________________________________________________________________________________
 * Je laisse de c�t� la classe servant de mod�le et me concentrer sur mes composants.
 * Je reviens � un code plus sobre (une fen�tre avec seulement le JTableau)
 * R�f Fenetre3
 * De l�, je vais cr�er une classe qui affichera un bouton dans les cellules de la seconde
 * colonne et une combo dans les cellules de la troisi�me colonne... Le principe est
 * simple : cr�er une classe qui h�ritera de JButton et impl�mentera l'interface
 * TableCellRenderer. Je vais ensuite dire � mon tableau qu'il doit utiliser ce type de
 * rendu pour la 2e colonne.
 * R�f ButtonRenderer
 * Il me suffit maintenant de mettre � jour le tableau gr�ce � l'instruction :
 * this.tableau.getColumn("Age").setCellRenderer(new ButtonRenderer);  => on r�cup�re la
 * colonne gr�ce � son titre, puis on applique le rendu !
 * Mon bouton est de nouveau �ditable, mais ce probl�me sera r�gl� par la suite... Pour le 
 * rendu de la cellule num�ro 3 :
 * 
 * III./ Interaction avec l'objet JTable
 * 
 * Le composant le plus difficile � utiliser dans un tableau, entre un bouton et une combo...
 * c'est le bouton !
 * Eh oui, la combo est g�r�e presqu'automatiquement, alors qu'il me faudra dire aux boutons
 * ce qu'ils doivent faire... Pour arriver � cela, je vais cr�er une classe qui permettra � 
 * mon tableau d'effectuer des actions sp�cifiques gr�ce aux boutons.
 * 
 * R�f ButtonEditor
 * 
 * Maintenant, afin d'utiliser cet objet avec mon tableau, je vais indiquer � ce dernier 
 * l'existence de cet �diteur dans la colonne correspondante avec cette instruction :
 * this.tableau.getColumn("Age").setEditor(new ButtonEditor(new JCheckBox)); => (r�f Fenetre3)
 * 
 * L�, je peux voir que lorsque je clique sur un bouton, la valeur situ�e juste � gauche
 * est modifi�e (� cause de table.setValueAt() dans le m�thode actionPerformed()...).
 * L'utilisation est donc tr�s simple. Et la gestion des combos est encore plus ais�e.
 * Pr�c�demment, j'ai d�velopp� une classe permettant d'afficher la combo. Cependant,
 * il y a un moyen bcp plus simple... Comme j'ai pu constater, la classe DefaultCellEditor
 * peut prendre un objet en param�tre : dans l'exemple du ButtonEditor, il utilisait un
 * JCheckBox. Je dois savoir que cet objet accepte d'autres types de param�tres :
 *  *	un JComboBox
 *  *	un JTextField
 * Je peux donc utiliser l'objet DefaultCellEditor directement en lui passant une combo en
 * param�tre... Je vais aussi enlever l'objet permettant d'afficher correstement la combo
 * afin de pouvoir mieux juger de l'efficacit� de cette m�thode.
 * 
 * En fait, sans le ComboRenderer, ma cellule se "transforme" en combo lorsque je clique
 * dessus. Lorsque le tableau sent une action sur la cellule, il utilise l'�diteur que 
 * j'ai sp�cifi� pour celle-ci.
 * 
 * Remarque : lorsque j'utilise aussi le ComboRenderer en m�me temps, il n'y a pas de
 * synchronisation entre ce dernier et le DefaultCellEditor assign�, i.e. ils n'affichent pas
 * la m�me chose.
 * 
 * Note : le fait que les boutons aient un dr�le de comportement est d� � l'affectation
 * des comportement sp�ciaux � mon tableau... Il faut donc d�finir un mod�le � utiliser afin
 * de bien d�finir tous les points comme l'affichage, la mise � jour, etc.
 * 
 * Je vais donc utiliser un mod�le de tableau personnalis�
 * 
 * R�f Fenetre4
 * 
 * En effet, j'ai construit mon tableau par le biais de mon mod�le, ce qui implique que je
 * devrai �galement passer par le mod�le pour modifier le tableau ! Cons�quence directe : il
 * va falloir modifier un peu ma classe ButtonEditor
 * 
 * R�f ButtonEditor4
 * 
 * Voil�, le bug d'affichage a disparu.
 * 
 * IV./ Ajouter des lignes et des colonnes
 * 
 * Voici une autre fa�on d'initialiser un tableau :
 * JTable tableau = new JTable(new DefaultTableModel(data, title));
 * L'int�r�t ? C'est tr�s simple : l'ajout et suppression dynamiques d'entr�es dans un tableau
 * se font via un mod�le de rendu. Cependant, avec cette notation, j�conomise une ligne de 
 * code et j'ai la possibilit� d'affecter diverses t�ches � mon mod�le de rendu, comme par
 * exemple, formater les donn�es...
 * 
 * Dans un premier temps, ajoutons et retirons des lignes � mon tableau. Je garderai le
 * m�me code que pr�c�demment avec deux ou trois ajouts :
 *  *	le bouton pour ajouter une ligne
 *  *	le bouton pour supprmer une ligne 
 *  
 *  Le mod�le par d�faut d�fini lors de la cr�ation du tableau me donne acc�s � deux m�thodes 
 *  fort utiles :
 *  -	addRow(Object[][] lineData) : ajoute une ligne au mod�le et met automatiquement � jour
 *  		le tableau
 *  -	removeRow(int row) : efface une ligne du mod�le et met automatiquement � jour le
 * 		tableau
 * ==> BULLSHIT !! JE DOIS REDEFINIR CES DEUX METHODES DANS LA CLASSE PERSONNALIS�E ZModel
 * 
 * Afin de pouvoir utiliser ce mod�le, je vais devoir le r�cup�rer. En fait, c'est mon tableau
 * qui va me le fournir en invoquant la m�thode getModel() qui retourne un objet TableModel.
 * Attention, un cast sera n�cessaire afin de pouvoir utiliser l'objet r�cup�r� ! 
 * Par exemple :
 * ((ZModel)table.getModel()).removeRow(int row);
 * 
 * R�f Fenetre4
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
