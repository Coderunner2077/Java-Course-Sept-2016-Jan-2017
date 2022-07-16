import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * 								Les menus et bo�tes de dialogue
 * 
 * Voici deux �l�ments tr�s utiles � l'�laboration de programmes offrant plusieurs 
 * fonctionnalit�s. 
 * 
 * I./ Les bo�tes de dialogue
 * 
 * Qu'est-ce qu'une bo�te de dialogue ? C'est une petite fen�tre pouvant servir � plusieurs
 * choses :
 * 	* afficher une information
 * 	* demander une validation, une r�futation ou une annulation
 *  * demander � l'utilisateur de saisir une information dont le syst�me a besoin
 *  * etc.
 *  
 * En effet, ces bo�tes peuvent servir � bcp de choses. Il faut toutefois les utiliser
 * avec parcimonie : il est assez p�nible pour l'utilisateur qu'une application ouvre une
 * bo�te de dialogue � chaque notification, car toute bo�te ouverte doit �tre ferm�e !
 * 
 * 1.) Les bo�tes d'information
 * 
 * L'objet que je vais utiliser tout au long de ce chapitre est JOptionPane, un objet assez
 * complexe au 1er abord, mais fort pratique.
 * 
 * Ces bo�tes ne sont pas destin�es � participer � de quelconques op�rations : elles 
 * affichent juste un message � l'attention de l'utilisateur. Voici les codes utilis�es 
 * pour obtenir ces bo�tes : 
 * 
 * JOptionPane jop1, jop2, jop3;
 * //Bo�te du message d'information  
 * jop1 = new JOptionPane();
 * jop1.showMessageDialog(null, "Message Informatif", "Information", 
 * JOptionPane.INFORMATION_MESSAGE);
 * 
 * // Bo�te du message pr�ventif 
 * jop2 = new JOptionPane();
 * jop2.showMessageDialog(null, "Message pr�ventif", "Attention", 
 * JOptionPane.WARNING_MESSAGE);
 * 
 * //Bo�te du message d'erreur
 * jop3 = new JOptionPane();
 * jop3.showMessageDialog(null, "Message d'erreur", "Erreur", 
 * JOptionPane.ERROR_MESSAGE);
 * 
 * Ces trois bo�tes ne s'affichent pas en m�me temps, tout simplement parce qu'en Java 
 * (mais aussi dans les autres langages), les bo�tes de dialogues sont dites modales. Cela
 * signifie que lorsqu'une bo�te fait son apparition, celle-ci bloque toute int�raction
 * avec un autre composant, et ce tant que l'utilisateur n'a pas mis fin au dialogue.
 * 
 * Maintenant, voyons de plus pr�s comment construire un tel objet. Ici, j'ai utilis� la
 * m�thode:
 * showMessageDialog(Component parentComponent, String message, String title,
 * int messageType);
 * 
 * Voici ses param�tres en d�tail:
 * 	-	Component parentComponent : correspond au composant parent; ici, il n'y en a aucun
 * 			je mets donc null
 * 	-	String message : permet de renseigner le message � afficher dans la bo�te de dialogue
 * 	-	String title : permet de donner un titre � l'objet
 * 	-	int messageType : permet de savoir s'il s'agit d'un message d'information, de 
 * 			pr�vention ou d'erreur.
 * 
 * Il existe deux autres m�thodes showMessageDialog() pour cet objet : une qui prend deux
 * param�tes en moins (le titre et le type de message), et une qui prend un param�tre en
 * plus (l'ic�ne � utiliser).
 * Je vais me servir d'un objet ImageIcon (qui va bcp me plaire) qui lit le fichier image
 * � l'emplacement (un String) sp�cifi� dans son constructeur, de l'ic�ne � utiliser. 
 * R�f main.
 * 
 * Cette bo�te est tr�s utile pour signaler � l'utilisateur qu'une op�ration s'est 
 * termin�e ou qu'une erreur est survenue. L'exemple le plus simple qui me vient en t�te
 * est une division par z�ro : on peut utiliser une bo�te de dialogue dans le bloc catch.
 * 
 * Voici les types de bo�tes que je peux afficher (ces types restent valables pour tout
 * ce qui suit) :
 * 
 *  * JOptionPane.ERROR_MESSAGE
 *  * JOptionPane.INFORMATION_MESSAGE
 *  * JOptionPane.PLAIN_MESSAGE
 *  * JOptionPane.QUESTION_MESSAGE
 *  * JOptionPane.WARNING_MESSAGE
 *  
 * Je vais maintenant poursuivre avec les bo�tes de confirmation
 * 
 * 2.) Les bo�tes de confirmation
 * 
 * Comme leur nom l'indique, ces bo�tes permettent de valider, d'invalider ou d'annuler
 * une d�cision. J'utiliserai toujours l'objet JOptionPane, mais cette fois, ce sera avec
 * la m�thode showConfirmDialog(), une m�thode qui retourne un entier correspondant � 
 * l'option que l'on a choisie dans cette bo�te :
 * 	-	Yes (ou Ok)
 * 	-	No
 * 	-	Cancel
 * 
 * Comme exemple, je vais reprendre mon Animation et ajouter une bo�te de confirmation
 * lorsqu'on clique sur l'un des boutons contr�lant l'animation (Go ou Stop).
 * R�f Animation
 * J'ai donc invoqu� la m�thode comme ceci :
 * option = jop.showConfirmDialog(null, "Question ?", "Titre", JOptionPane.YES_NO_OPTION,
 * 				JOptionPane.QUESTION_MESSAGE);
 * Et pour le bouton stop :
 * option = jop.showConfirmDialog(null, "Question ?", "Titre", 
 * 				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
 * 	 
 * En effet, plut�t que d'afficher tout simplement la bo�te, j'affecte en m�me temps le
 * r�sultat que renvoie la m�thode showConfirmDialog() � une variable de type int. Et 
 * je me sers de cette variable afin de savoir quel bouton a �t� cliqu� (oui, non, etc.).
 * Voici les constantes de l'objet JOptionPane correspondant aux diff�rents clics sur
 * les boutons de la bo�te de dialogue :
 *  * YES_OPTION, qui vaut 0 (JOptionPane.OK_OPTION a la m�me valeur)
 *  * NO_OPTION => 1
 *  * CANCEL_OPTION => 2
 *  * CLOSED_OPTION => -1
 * 
 * En effectuant un test sur la valeur de mon entier, on peut en d�duire la valeur du
 * bouton sur lequel on a cliqu� et agir en cons�quence. 
 * 
 * 3.) Les bo�tes de saisie
 * 
 * Dans les bo�tes de saisie, on peut y saisir du texte. Et mieux encore : on peut m�me
 * obtenir une bo�te de dialogue qui propose des choix dans une liste d�roulante. 
 * En me servant toujours de l'objet JOptionPane, j'utiliserai la m�thode :
 * showInputDialog(Component parent, String message, String title, int messageType);
 * ==> qui retourne une cha�ne de caract�res.
 * R�f main
 * Voyons maintenant comment on ins�re une liste dans une bo�te de dialogue.
 * R�f main
 * 
 * Voici le d�tail des param�tres utilis�s dans cette surcharge de showInputDialog():
 *  * les quatre premiers, je connais
 *  * l'ic�ne que je souhaite passer (j'ai mis null)
 *  * un tableau de String afin de remplir la combo (l'objet JComboBox bien cach�) de la
 *  		bo�te
 *  * le dernier param�tre correspond � la valeur par d�faut de la liste d�roulante
 *  
 * Attention, cette m�thode retourne un objet de type Object, comme si je r�cup�rais la
 * valeur directement dans la combo. Du coup, il faut faire un cast.
 *  
 * Voici maintenant une variante de ce que je viens de voir : je vais utiliser ici la 
 * m�thode showOptionDialog(). Celle-ci fonctionne � peu pr�s comme la m�thode pr�c�dente,
 * sauf qu'elle prend un param�tre suppl�mentaire et le type de retour n'est pas un objet
 * mais un entier.
 * Ce type de bo�te propose un choix de boutons correspondant aux �l�ments pass�s en 
 * param�tres (tableau de String) au lieu d'une combo; elle prend aussi une valeur par
 * d�faut,mais retourne l'indice de l'�l�ment dans la liste au lieu de l'�l�ment lui-m�me.
 * R�f main
 * 
 * Voil� pour les bo�tes de saisie. Cependant, il serait int�ressant d'ajouter des 
 * composants � ces bo�tes, pour avoir plus de renseignements, ou encore, beacuoup plus
 * de renseignements...
 * 
 * 4.) Des bo�tes de dialogue personnalis�es
 * 
 * Les bo�tes de dialogue h�ritent de la classe JDialog. Je vais donc cr�er une classe
 * d�rivant de cette derni�re.
 * R�f ZDialog
 * 
 * Voici les param�tres du constructeur de ZDialog :
 *  * JFrame parent correspond � l'objet parent
 *  * String title correspond au titre de ma bo�te
 *  * boolean modal correspond � la modalit� (true ==> bo�te modale, false ==> pas modale)
 *  
 * Rien de compliqu�, il est donc temps d'ajouter des composants � mon objet. Par contre,
 * si je prends la peine de construire un tel composant, autant faire quelque chose de
 * lourd, quelque chose de s�rieux... comme plusieurs saisies, avec plusieurs listes en
 * m�me temps.
 * 
 * Donc il y a des cas o� l'on doit r�cup�rer des iformations choisies, mais ce n'est pas
 * tout le temps le cas : je vais devoir d�terminer ces diff�rents cas, ainsi que les
 * choses � faire.
 * 
 * Partons du fait que ma bo�te comprendra un bouton OK et un bouton Annuler : dans le
 * cas o� l'utilisateur clique sur Annuler, on ne r�cup�re rien. Et il faudra aussi tenir
 * compte de la modalit� de ma bo�te : la m�thode setVisible(false) met fin au dialogue !
 * Cela signifie �galement que le dialogue s'entame au moment o� l'instruction 
 * setVisible(true) est ex�cut�e. C'est pourquoi je vais sortir cette instruction du
 * constructeur de l'objet et la mettre � part.
 * 
 * Maintenant, il faut indiquer � ma bo�te s'il faut renvoyer des informations ou pas. 
 * C'est pour cela que je vais utiliser un bool�en - appelons le sendData - initialis�
 * � false. Mais qui passera � true si on clique sur OK.
 * Voici le code : 
 //Cas o� notre ZDialog renverra le contenu
//D'un JTextField nomm� jtf
  public String showZDialog(){
  		this.sendData = false; //?????????????
  		//D�but du dialogue
  		this.setVisible(true);
  		//Le dialogue prend fin
  		//Si on a cliqu� sur OK, on envoie, sinon on envoie une cha�ne vide !
 		return (this.sendData)? jtf.getText() : "";
  }
 * Il me reste un dernier point � g�rer.
 * ==> Comment r�cup�rer les info saisies dans ma bo�te depuis ma fen�tre, vu que je
 * veux obtenir plusieurs informations ?
 * 
 * C'est vrai qu'on ne peut retourner qu'une seule valeur � la fois. Mais il peut y avoir
 * plusieurs solutions � ce probl�me :
 *  * Dans le cas o� je n'ai qu'un composant, je peux adapter la m�thode showZDialog() au
 *  	type de retour du composant utilis�
 *  * Dans mon cas, je veux plusieurs composants, donc plusieurs valeurs. Je peux :
 *  	==> retourner une collection de valeurs (ArrayList)
 *  	==> faire des accesseurs dans mon ZDialog
 *  	==> cr�er un objet dont le r�le est de collecter les informations dans ma bo�te
 *  		et de retourner cet objet
 *  	==> etc.
 *  
 *  Je vais opter pour un objet qui collectera les informations et que je retournerai � la
 *  fin de la m�thode showZDialog(). Avant de ma lancer dans sa cr�ation, je dois savoir
 *  ce que je vais mettre dans ma bo�te... Je vais programmer une bo�te permettant de 
 *  sp�cifier les caract�ristiques d'un personnage :
 *   - son nom (un champ de saisie)
 *   - son genre (une combo)
 *   - sa taille (un champ de saisie)
 *   - sa couleur de cheveux (une combo)
 *   - sa tranche d'�ge (des boutons radios)
 *   
 * Note : pour ce qui est du placement des composants, l'objet JDialog se comporte 
 * exactement comme un JFrame (BorderLayout par d�faut, ajout d'un composant au 
 * conteneur...).
 * Je vais donc cr�er cet objet => R�f ZDialogInfo
 * L'avantage avec cette m�thode, c'est que j'ai pas � me soucier d'une �ventuelle
 * annulation de la saisie : l'objet d'information renverra toujours qqch.
 * R�f ZDialog
 * 
 * II./ Les menus
 * 
 * 1.) Faire son premier menu
 * 
 * MenuBar fait partie de la composition de l'objet JFrame. Le moment est venu pour
 * utiliser un composant de ce genre. N�anmoins, celui-ci appartient au package java.awt.
 * Dans ce chapitre, j'utiliserai son homologue, l'objet JMenuBar, issu du package
 * javax.swing. Pour travailler avec des menus, j'aurai besoin :
 *  * de l'objet JMenu, le titre principal d'un point de menu (Fichier, Edition...)
 *  * d'objets JMenuItem, les �l�ments composants mes menus
 *  
 * Afin de permettre des int�ractions avec mes futurs menus, je vais devoir impl�menter
 * l'interface ActionListener. Ces impl�mentations serviront � �couter les objets
 * JMenuItem : ce sont ces objets qui d�clencheront l'une ou l'autre op�ration. Les JMenu,
 * eux, se comportent automatiquement : si on clique sur un titre de menu, celui-ci se 
 * d�roule tout seul et, dans le cas o� j'ai un tel objet pr�sent dans un autre JMenu,
 * une autre liste se d�roulera toute seule. 
 * 
 * Je vais transformer mon Animation en une Animation 1.2 avec un vrai menu � la place
 * des boutons.
 * Mais d'abord, je vais me familiariser un peu avec le JMenuBar
 * R�f ZFenetre
 * 
 * A pr�sent, je vais cr�er un menu pour mon animation. D'abord sans la gestion des
 * �v�nements. Pour le moment, je vais avoir besoin :
 *  * d'un menu Animation pour lancer, interrompre (par d�faut � setEnabled(false) ou
 *  	quitter l'animation
 *  * d'un menu Forme afin de s�lectionner le type de forme � utiliser (sous-menu + radio
 *  	par forme)
 *  * d'un menu A propos avec un joli "?" qui va ouvrir une bo�te de dialogue
 *  
 * Il ne faut surtout pas effacer les impl�mentations pour les �v�nements : je retire 
 * seulement les composants qui les utilisent. Ensuite, je cr�e mon menu !
 * R�f Animation 1.2
 * 
 * Pour faire communiquer les menus et mon animaton, il suffit d'indiquer � mes MenuItem
 * qu'on les �coute. En fait, cela revient � faire comme si je cliquais sur mes boutons
 * (� l'exception des cases � cocher et des radios, o�, l�, je peux utiliser une
 * impl�mentation d'ActionListener ou d'ItemListener).
 * Et dans le Panneau :
 * J'ai ajout� :  || this.forme.equals("CARR�") :
 *	if(this.forme.equals("CARRE") || this.forme.equals("CARR�")){
 * g.fillRect(posX, posY, 50, 50);
 * }
 * R�f Animation 1.2
 * 
 * Il est temps d'ajouter des raccourcis clavier � mon application.
 * 
 * 2.) Les raccourcis clavier
 * 
 * Il est tr�s simple d'ins�rer des raccourcis clavier. Pour ajouter un "acc�l�rateur" 
 * (=> raccourcis clavier des �l�ments de menu) sur un JMenu, j'appelerai la m�thode
 * setAccelerator(). Et pour ajouter un mn�monique (=> raccourcis permettant de simuler
 * un clic sur un point de menu) sur un JMenuItem, je me servirai de la m�thode:
 * setMnemonic().
 * Attribuons le mn�monique "A" au menu Animation, le mn�nomique "F" pour le menu
 * Forme et enfin "P" pour A propos. Il me suffit d'invoquer setMnemonic(char mnemonic) 
 * sur le JMenu que je d�sire.
 * R�f Animation 1.2
 * A pr�sent, j'ai les lettres correspondant au mn�monique soulign�es dans mes menus. Et
 * il y a mieux : si je tap ALT + <la lettre>, le menu correspondant se d�roule ! 
 * Je peux aussi mettre des mn�moniques sur des objets JMenuItem. Il existe une autre 
 * fa�on d'ajouter un mn�monique sur un JMenu (c'est uniquement valable pour un JMenu !) :
 * en passant le mn�monique en 2e param�tre du constructeur de l'objet, comme ceci :
 * JMenu menu = new JMenu("Fichier", 'F'); ==> ce menu aura le mn�monique 'F'
 * 
 * Pour ajouter des acc�l�rateurs, c'est quasiment pareil, si ce n'est que je devrai 
 * utiliser un nouvel objet : KeyStroke. Cet objet permet de d�terminer la touche utilis�e
 * ou � utiliser. C'est gr�ce � cet objet que je vais pouvoir construire des combinaisons
 * de touches pour mes acc�l�rateurs. Je vais commencer par attribuer un simple caract�re
 * comme acc�l�rateur � mon JMenuItem Lancer en utilisant la m�thode:
 * getKeyStroke(char caracter) de l'objet KeyStroke.
 * Je vais ajouter la ligne qui suit au d�but de ma m�thode initMenu() de l'Animation 1.2:
 * lancement.setAccelerator(KeyStroke.getKeyStroke('c'));
 * En important bien s�r javax.swing.KeyStroke et java.awt.event.ActionEvent au pr�alable.
 * R�f Animation 1.2 => un petit "c" est apparu � c�t� du Lancement. Et je n'ai qu'� 
 * appuyer juste sur 'c' pour effectuer le lancement (==> m�me effet qu'un clic sur le
 * menu Lancement).
 * Attention, un acc�l�rateur est sensible � la casse contrairement au mn�monique ("c" 
 * n'est pas la m�me chose que "C").
 * Appuyer sur "c" lancera syst�matiquement l'animation. C'est l'une des raison pour 
 * laquelle les acc�l�rateurs sont, en g�n�ral, des combinaisons du genre CTRL + c,
 * ou encore CTRL + SHIFT + S.
 * 
 * Pour cela, je vais utiliser une m�thode getKeyStroke() un peu diff�rente : elle ne
 * prendra pas le caract�re de ma touche en argument, mais son code ainsi qu'une ou
 * plusieurs touche(s) composant la combinaison. Pour obtenir le code d'une touche, 
 * j'utiliserai l'objet KeyEvent, un objet qui stocke tous les codes des touches.
 * Dans le code qui suit, je cr�e un acc�l�rateur CTRL + L pour le menu Lancement et
 * un acc�l�rateur CTRL + SHIFT + A pour le menu Arr�t :
 * lancement.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK);
 *  //...
 * arret.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK + 
 * 			KeyEvent.SHIFT_DOWN_MASK);
 * 
 * En fait, la classe KeyEvent r�pertorie tous les codes de toutes les touches du clavier.
 * Une grande majorit� d'entre eux sont sous la forme : VK_<le caract�re ou le nom de la
 * touche en majuscules>. VK => Value of Key.
 * A part certaines touches de contr�le comme CTRL, SHIFT, ALT, etc. je peux facilement
 * retrouver le code d'une touche gr�ce � cet objet.
 * 
 * Note : Eclipse me dit que la version CTRL_DOWN_MASK est la plus r�cente  et qu'il est
 * vivement conseill� de l'utiliser.
 * 
 * Il est temps de voir � pr�sent comment cr�er un menu contextuel.
 * 
 * 3.) Faire un menu contextuel
 * 
 * Je vais utiliser un autre objet JPopupMenu, dans lequel je mettrai mes JMenuItem ou/et
 * JMenu. Il faudra juste indiquer � mon JPopupMenu comment et o� s'afficher. 
 * 
 * Voici les points importants de mon menu contextuel :
 * 	* Dans le cas d'op�rations identiques � celles accessibles par le menu, je devrai
 * 		cr�er des objets qui s'�tendent � ces deux menus.
 * 	* Le menu contextuel ne doit s'afficher que dans la zone o� l'animation s'ex�cute,
 * 		pas dans le menu !
 *  * Il ne doit s'afficher que lors d'un clic droit
 * 
 * Je vais mettre dans mon menu contextuel les action "Lancer l'animation" et "Arr�ter
 * l'animation", ainsi que deux nouveaut�s :
 * 		-	changer la couleur de fond de mon animation
 * 		-	changer la couleur de ma forme
 * 
 * Avant d'impl�menter les deux nouveaut�s, je vais travailler sur les deux premi�res.
 * Lorsque je lancerai l'animation, je devrai mettre les deux menus Lancer l'animation
 * dans l'�tat sesEnabled(false) et les deux menus Arr�ter l'animation dans l'�tat
 * setEnabled(true) (et pour l'arr�ter, il faudra faire l'inverse).
 * Je vais utiliser le m�me objet qui �coute  pour les deux menus. Il me faudra cr�er une
 * v�ritable instance de ces objets et signaler � l'application que ces objets �coutent 
 * non seulement le menu d'en haut, mais aussi le menu contextuel.
 * J'ai parfaitement le droit de le faire : plusieurs objets peuvent �couter un m�me 
 * composant et plusieurs composants peuvent avoir le m�me objet qui les �coute. 
 * A ce stade, je dois juste tirer au clair ceci :
 *  * comment indiquer � mon panneau quand et o� afficher le menu contextuel
 *  * comment lui sp�cifier qu'il doit le faire uniquement suite � un clic droit
 *  
 * Le d�clenchement de l'affichage du pop-up doit se faire lors d'un clic de la souris.
 * Je connais une interface qui g�re ce type d'�v�nement : l'interface MouseListener. Je
 * vais donc indiquer � mon panneau qu'un objet du type de cette interface va l'�couter.
 * 
 * Note : tout comme dans le chapitre sur les zones de saisie, il existe une classe qui
 * contient toutes les m�thodes de ladite interface : la classe MouseAdapter. Je peux
 * impl�menter celle-ci afin de ne red�finir que la m�thode dont j'ai besoin. C'est
 * cette solution que je vais utiliser.
 * 
 * Il vaut mieux utiliser l'�v�nement mouseReleased() plut�t que mouseClicked() pour une
 * raison �vidente : si ces deux �v�nements sont quasiment identiques, dans un certain 
 * cas, seul l'�v�nement mouseClicked() sera appel�. Il s'agit du cas o� je clique sur
 * une zone, d�place ma souris en dehors de la zone tout en maintenant le clic et rel�che
 * le bouton de la souris. C'est pour cette raison que je vais pr�f�rer utiliser la 
 * m�thode mouseReleased(). Ensuite, pour pr�ciser o� il faut afficher le menu contextuel,
 * je vais utiliser la m�thode show(Component invoker, int x, int y) de la classe 
 * JPopupMenu :
 *  * Component invoker : d�signe l'objet invoquant le menu contextuel, dans mon cas, 
 *  		l'instance de Panneau
 *  * int x : coordonn�e x du menu
 *  * int y : coordonn�e y du menu
 *  
 * Rappel : je peux me servir de l'objet MouseEvent pour d�terminer les coordonn�es de la
 * souris.
 * 
 * Pour d�tecter le clic droit : c'est encore l'objet MouseEvent qui va me sauver la mise.
 * En effet, il poss�de une m�thode isPopupTrigger() qui renvoie vrai s'il s'agit d'un
 * clic droit.
 * R�f Animation 1.2
 * 
 * Les menus et les menus contextuels peuvent s'av�rer vraiment utiles et ergonomiques !
 * En plus, ils sont relativement simples � impl�menter (et � utiliser). Cependant, je
 * constate qu'il y a bcp de clics superflus, que ce soit pour utiliser un menu ou menu
 * contextuel : il faut au moins deux clics pour afficher leur contenu (sauf dans le cas
 * d'un acc�l�rateur).
 * Pour contrer ce genre de choses, il existe un concept tr�s puissant : la barre 
 * d'outils.
 * 
 * 4.) La barre d'outils
 * 
 * La barre d'outils sert � effectuer des actions qui se trouvent dans le menu, mais sans
 * devoir fouiller dans celui-ci ou m�moriser le raccourci clavier (acc�l�rateur) qui y
 * est li�. Elle permet donc des actions rapides.
 * Elle est g�n�ralement compos�e d'une multitude de boutons, une image appos�e sur 
 * chacun d'eux symbolisant l'action qu'il doit effectuer.
 * Pour cr�er et utiliser une barre d'outils, je vais utiliser l'objet JToolBar. Cet
 * objet fonctionne comme un menu classique, � une diff�rence pr�s : il prend des boutons
 * (JButton) en arguments, et il n'y a pas d'endroit sp�cifique o� incorporer ma barre
 * d'outils (il faudra l'expliciter lors de sa cr�ation).
 * 
 * Tout d'abord, il faut des images � mettre sur mes boutons...
 * 
 * Au niveau des actions � g�rer, pour le lancement de l'animation et l'arr�t, il faudra
 * penser � �diter le comportement des boutons de la barre d'outils, comme je l'ai fait
 * pour les deux actions du menu contextuel. Concernant les boutons pour les formes, c'est
 * un peu plus d�licat. Les autres composants qui �ditaient la forme de mon animation
 * �taient des boutons radios. Or, ici, j'ai des boutons standard. Outre le fait qu'il va
 * falloir une instance pr�cise de FormeListener, j'aurai � modifier un peu son comporte -
 * ment...
 * 
 * Il me faut savoir si l'action vient d'un bouton radio du menu ou d'un bouton de la
 * barre d'outils : c'est l'objet ActionEvent qui me permettra d'acc�der � cette info.
 * Je ne vais pas tester tous les boutons radio un par un, pour ces composants, le
 * syst�me utilis� jusque l� �tait tr�s bien. Non, je vais tout simplement v�rifier si
 * celui qui a d�clench� l'action est un JRadioButtonMenuItem, et si c'est le cas, je 
 * testerai les boutons.
 * 
 * Rappel : chapitre sur la r�flexivit� ! La m�thode getSource() me retourne un objet, il
 * est donc possible de cona�tre la classe de celui-ci avec la m�thode getClass() et par
 * cons�quent d'en obtenir le nom gr�ce � getName().
 * 
 * Il va falloir qu'on pense � mettre � jour le bouton radio s�lectionn� dans le menu. Et
 * l�, pour mon plus grand bonheur, il y a une astuce qui marche pas mal du tout : lors
 * du clic sur un bouton de la barre d'outils, il suffit de d�clencher l'�v�nement sur
 * le bouton radio correspondant ! Dans la classe AbstractButton, dont h�ritent tous les
 * boutons, il y a la m�thode doClick(). Cette m�thode d�clenche un �v�nement identique
 * � un vrai clic de souris sur le composant. Ainsi, plut�t que de g�rer la m�me fa�on
 * de faire � deux endroits, je vais rediriger l'action effectu�e sur un composant vers
 * un autre.
 * R�f Animation 1.2
 * 
 * 5.) Utiliser les actions abstraites
 * 
 * J'ai vu pr�c�demment comment centraliser des actions sur diff�rents composants. Il 
 * existe une classe abstraite qui permet de g�rer ce genre de choses, car elle peut
 * s'adapter � beaucoup de composants (en g�n�ral, ceux qui ne font qu'une action, comme
 * un bouton, une case � cocher, mais pas une liste).
 * Le r�le de cette classe est d'attribuer automatiquement une action � un ou plusieurs
 * composants. Le principal avantage de ce proc�d� est que plusieurs composants travaillent
 * avec une impl�mentation de l'interface AbstractAction, mais son gros inconv�nient
 * r�side dans le fait que je dois dois programmer une impl�mentation par action :
 * 		-	une action pour la couleur de la forme en rouge
 * 		-	une action pour la couleur de la forme en bleu
 * 		-	une action pour la couleur de la forme en vert
 * 		-	une action pour la couleur de fond en rouge
 * 		-	une action pour la couleur de fond en bleu
 * 		-	une action pour la couleur de fond en vert
 * 		-	etc.
 * 
 * Cela peut �tre lourd � coder, il faut r�fl�chir et v�rifier s'il est pertinent 
 * d'utiliser cette m�thode.
 * 
 * Voici comment on l'utilise :
 * 
 * public class Fenetre extends JFrame{
  //Nous pouvons utiliser les actions abstraites directement dans un JButton
  private JButton bouton1 = new JButton(new RougeAction("ActionRouge", new ImageIcon("images/rouge.jpg"));

  //Ou cr�er une instance concr�te
  private RougeAction rAct = new RougeAction("ActionRouge", new ImageIcon("images/rouge.jpg"));
  private JToolBar toolBar = new JToolBar();

  //�

  //Utiliser une action directement dans une barre d'outils
  private void initToolBar(){
    toolBar.add(rAct);
  }

  //�

  class RougeAction extends AbstractAction{
    //Constructeur avec le nom uniquement
    public RougeAction(String name){super(name);}

    //Le constructeur prend le nom et une ic�ne en param�tre
    public RougeAction(String name, ImageIcon){super(name, img);}

    public void actionPerformed(ActionEvent){
      //Vous connaissez la marche � suivre
    }
  }
}
 *
 *Je peux voir que cela peut �tre tr�s pratique. D�sormais, je peux ajouter une action 
 * sur une barre d'outils, celle-ci cr�e automatiquement un bouton correspondant ! 
 * Utiliser les actions abstraites plut�t que des impl�mentations de telle ou telle 
 * interface est un choix qui me revient. Je peux d'ailleurs tr�s bien appliquer ce
 * principe au code de mon animation, mais je constaterai qu'il s'alourdira. J'�viterai
 * donc de le faire. C'est une question de choix et de conception.
 * 
 *  
 * 
 *
 * 
 * 
 */
public class Main_menu_boites {

	public static void main(String[] args) {
		/*
		JOptionPane jop1, jop2, jop3;
		 //Bo�te du message d'information  
		 jop1 = new JOptionPane();
		 ImageIcon img = new ImageIcon("information.png");
		 jop1.showMessageDialog(null, "Message Informatif", "Information", 
		 JOptionPane.INFORMATION_MESSAGE, img);
		 
		 // Bo�te du message pr�ventif 
		 jop2 = new JOptionPane();
		 img = new ImageIcon("warning.png");
		 jop2.showMessageDialog(null, "Message pr�ventif", "Attention", 
		 JOptionPane.WARNING_MESSAGE, img);
		 
		 //Bo�te du message d'erreur
		 jop3 = new JOptionPane();
		 img = new ImageIcon("error.png");
		 jop3.showMessageDialog(null, "Message d'erreur", "Erreur", 
		 JOptionPane.ERROR_MESSAGE, img);
		 
		 //Bo�te de saisie
		 JOptionPane jop4 = new JOptionPane();
		 String nom = "";
		 nom = jop4.showInputDialog(null, "Veuillez d�cliner votre identit� !", "Gendarmerie"
		 		+ " nationale", JOptionPane.QUESTION_MESSAGE);
		 jop4.showMessageDialog(null, "Vous vous appelez " + nom, "Information",
				 JOptionPane.INFORMATION_MESSAGE);
		 
		 //Ins�rer une liste dans une bo�te de saisie 
		 JOptionPane jop5 = new JOptionPane();
		 String[] genre = {"Masculin", "F�minin", "Ind�termin�"};
		 String gender;
		 gender = (String)jop5.showInputDialog(null, "Quel est votre genre ?", "Le genre", 
				 JOptionPane.QUESTION_MESSAGE, null, genre, genre[2]);
		 jop5.showMessageDialog(null, "Vous �tes plut�t "+ gender, "Etat civil",
				 JOptionPane.INFORMATION_MESSAGE);
		 
		 //Variante => retourne un int au lieu d'un objet
		 JOptionPane jop6 = new JOptionPane();
		 int indice;
		 indice = jop6.showOptionDialog(null, "Quel est votre genre ?", "Le gender", 
				 	JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				 	genre, genre[2]);
		 jop6.showMessageDialog(null, "Vous �tes donc plut�t " + genre[indice], 
				 "Etat civil", JOptionPane.INFORMATION_MESSAGE);
		 */
		Fenetre fen = new Fenetre();
	}

}
