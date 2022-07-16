import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * 								Les menus et boîtes de dialogue
 * 
 * Voici deux éléments très utiles à l'élaboration de programmes offrant plusieurs 
 * fonctionnalités. 
 * 
 * I./ Les boîtes de dialogue
 * 
 * Qu'est-ce qu'une boîte de dialogue ? C'est une petite fenêtre pouvant servir à plusieurs
 * choses :
 * 	* afficher une information
 * 	* demander une validation, une réfutation ou une annulation
 *  * demander à l'utilisateur de saisir une information dont le système a besoin
 *  * etc.
 *  
 * En effet, ces boîtes peuvent servir à bcp de choses. Il faut toutefois les utiliser
 * avec parcimonie : il est assez pénible pour l'utilisateur qu'une application ouvre une
 * boîte de dialogue à chaque notification, car toute boîte ouverte doit être fermée !
 * 
 * 1.) Les boîtes d'information
 * 
 * L'objet que je vais utiliser tout au long de ce chapitre est JOptionPane, un objet assez
 * complexe au 1er abord, mais fort pratique.
 * 
 * Ces boîtes ne sont pas destinées à participer à de quelconques opérations : elles 
 * affichent juste un message à l'attention de l'utilisateur. Voici les codes utilisées 
 * pour obtenir ces boîtes : 
 * 
 * JOptionPane jop1, jop2, jop3;
 * //Boîte du message d'information  
 * jop1 = new JOptionPane();
 * jop1.showMessageDialog(null, "Message Informatif", "Information", 
 * JOptionPane.INFORMATION_MESSAGE);
 * 
 * // Boîte du message préventif 
 * jop2 = new JOptionPane();
 * jop2.showMessageDialog(null, "Message préventif", "Attention", 
 * JOptionPane.WARNING_MESSAGE);
 * 
 * //Boîte du message d'erreur
 * jop3 = new JOptionPane();
 * jop3.showMessageDialog(null, "Message d'erreur", "Erreur", 
 * JOptionPane.ERROR_MESSAGE);
 * 
 * Ces trois boîtes ne s'affichent pas en même temps, tout simplement parce qu'en Java 
 * (mais aussi dans les autres langages), les boîtes de dialogues sont dites modales. Cela
 * signifie que lorsqu'une boîte fait son apparition, celle-ci bloque toute intéraction
 * avec un autre composant, et ce tant que l'utilisateur n'a pas mis fin au dialogue.
 * 
 * Maintenant, voyons de plus près comment construire un tel objet. Ici, j'ai utilisé la
 * méthode:
 * showMessageDialog(Component parentComponent, String message, String title,
 * int messageType);
 * 
 * Voici ses paramètres en détail:
 * 	-	Component parentComponent : correspond au composant parent; ici, il n'y en a aucun
 * 			je mets donc null
 * 	-	String message : permet de renseigner le message à afficher dans la boîte de dialogue
 * 	-	String title : permet de donner un titre à l'objet
 * 	-	int messageType : permet de savoir s'il s'agit d'un message d'information, de 
 * 			prévention ou d'erreur.
 * 
 * Il existe deux autres méthodes showMessageDialog() pour cet objet : une qui prend deux
 * paramètes en moins (le titre et le type de message), et une qui prend un paramètre en
 * plus (l'icône à utiliser).
 * Je vais me servir d'un objet ImageIcon (qui va bcp me plaire) qui lit le fichier image
 * à l'emplacement (un String) spécifié dans son constructeur, de l'icône à utiliser. 
 * Réf main.
 * 
 * Cette boîte est très utile pour signaler à l'utilisateur qu'une opération s'est 
 * terminée ou qu'une erreur est survenue. L'exemple le plus simple qui me vient en tête
 * est une division par zéro : on peut utiliser une boîte de dialogue dans le bloc catch.
 * 
 * Voici les types de boîtes que je peux afficher (ces types restent valables pour tout
 * ce qui suit) :
 * 
 *  * JOptionPane.ERROR_MESSAGE
 *  * JOptionPane.INFORMATION_MESSAGE
 *  * JOptionPane.PLAIN_MESSAGE
 *  * JOptionPane.QUESTION_MESSAGE
 *  * JOptionPane.WARNING_MESSAGE
 *  
 * Je vais maintenant poursuivre avec les boîtes de confirmation
 * 
 * 2.) Les boîtes de confirmation
 * 
 * Comme leur nom l'indique, ces boîtes permettent de valider, d'invalider ou d'annuler
 * une décision. J'utiliserai toujours l'objet JOptionPane, mais cette fois, ce sera avec
 * la méthode showConfirmDialog(), une méthode qui retourne un entier correspondant à 
 * l'option que l'on a choisie dans cette boîte :
 * 	-	Yes (ou Ok)
 * 	-	No
 * 	-	Cancel
 * 
 * Comme exemple, je vais reprendre mon Animation et ajouter une boîte de confirmation
 * lorsqu'on clique sur l'un des boutons contrôlant l'animation (Go ou Stop).
 * Réf Animation
 * J'ai donc invoqué la méthode comme ceci :
 * option = jop.showConfirmDialog(null, "Question ?", "Titre", JOptionPane.YES_NO_OPTION,
 * 				JOptionPane.QUESTION_MESSAGE);
 * Et pour le bouton stop :
 * option = jop.showConfirmDialog(null, "Question ?", "Titre", 
 * 				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
 * 	 
 * En effet, plutôt que d'afficher tout simplement la boîte, j'affecte en même temps le
 * résultat que renvoie la méthode showConfirmDialog() à une variable de type int. Et 
 * je me sers de cette variable afin de savoir quel bouton a été cliqué (oui, non, etc.).
 * Voici les constantes de l'objet JOptionPane correspondant aux différents clics sur
 * les boutons de la boîte de dialogue :
 *  * YES_OPTION, qui vaut 0 (JOptionPane.OK_OPTION a la même valeur)
 *  * NO_OPTION => 1
 *  * CANCEL_OPTION => 2
 *  * CLOSED_OPTION => -1
 * 
 * En effectuant un test sur la valeur de mon entier, on peut en déduire la valeur du
 * bouton sur lequel on a cliqué et agir en conséquence. 
 * 
 * 3.) Les boîtes de saisie
 * 
 * Dans les boîtes de saisie, on peut y saisir du texte. Et mieux encore : on peut même
 * obtenir une boîte de dialogue qui propose des choix dans une liste déroulante. 
 * En me servant toujours de l'objet JOptionPane, j'utiliserai la méthode :
 * showInputDialog(Component parent, String message, String title, int messageType);
 * ==> qui retourne une chaîne de caractères.
 * Réf main
 * Voyons maintenant comment on insère une liste dans une boîte de dialogue.
 * Réf main
 * 
 * Voici le détail des paramètres utilisés dans cette surcharge de showInputDialog():
 *  * les quatre premiers, je connais
 *  * l'icône que je souhaite passer (j'ai mis null)
 *  * un tableau de String afin de remplir la combo (l'objet JComboBox bien caché) de la
 *  		boîte
 *  * le dernier paramètre correspond à la valeur par défaut de la liste déroulante
 *  
 * Attention, cette méthode retourne un objet de type Object, comme si je récupérais la
 * valeur directement dans la combo. Du coup, il faut faire un cast.
 *  
 * Voici maintenant une variante de ce que je viens de voir : je vais utiliser ici la 
 * méthode showOptionDialog(). Celle-ci fonctionne à peu près comme la méthode précédente,
 * sauf qu'elle prend un paramètre supplémentaire et le type de retour n'est pas un objet
 * mais un entier.
 * Ce type de boîte propose un choix de boutons correspondant aux éléments passés en 
 * paramètres (tableau de String) au lieu d'une combo; elle prend aussi une valeur par
 * défaut,mais retourne l'indice de l'élément dans la liste au lieu de l'élément lui-même.
 * Réf main
 * 
 * Voilà pour les boîtes de saisie. Cependant, il serait intéressant d'ajouter des 
 * composants à ces boîtes, pour avoir plus de renseignements, ou encore, beacuoup plus
 * de renseignements...
 * 
 * 4.) Des boîtes de dialogue personnalisées
 * 
 * Les boîtes de dialogue héritent de la classe JDialog. Je vais donc créer une classe
 * dérivant de cette dernière.
 * Réf ZDialog
 * 
 * Voici les paramètres du constructeur de ZDialog :
 *  * JFrame parent correspond à l'objet parent
 *  * String title correspond au titre de ma boîte
 *  * boolean modal correspond à la modalité (true ==> boîte modale, false ==> pas modale)
 *  
 * Rien de compliqué, il est donc temps d'ajouter des composants à mon objet. Par contre,
 * si je prends la peine de construire un tel composant, autant faire quelque chose de
 * lourd, quelque chose de sérieux... comme plusieurs saisies, avec plusieurs listes en
 * même temps.
 * 
 * Donc il y a des cas où l'on doit récupérer des iformations choisies, mais ce n'est pas
 * tout le temps le cas : je vais devoir déterminer ces différents cas, ainsi que les
 * choses à faire.
 * 
 * Partons du fait que ma boîte comprendra un bouton OK et un bouton Annuler : dans le
 * cas où l'utilisateur clique sur Annuler, on ne récupère rien. Et il faudra aussi tenir
 * compte de la modalité de ma boîte : la méthode setVisible(false) met fin au dialogue !
 * Cela signifie également que le dialogue s'entame au moment où l'instruction 
 * setVisible(true) est exécutée. C'est pourquoi je vais sortir cette instruction du
 * constructeur de l'objet et la mettre à part.
 * 
 * Maintenant, il faut indiquer à ma boîte s'il faut renvoyer des informations ou pas. 
 * C'est pour cela que je vais utiliser un booléen - appelons le sendData - initialisé
 * à false. Mais qui passera à true si on clique sur OK.
 * Voici le code : 
 //Cas où notre ZDialog renverra le contenu
//D'un JTextField nommé jtf
  public String showZDialog(){
  		this.sendData = false; //?????????????
  		//Début du dialogue
  		this.setVisible(true);
  		//Le dialogue prend fin
  		//Si on a cliqué sur OK, on envoie, sinon on envoie une chaîne vide !
 		return (this.sendData)? jtf.getText() : "";
  }
 * Il me reste un dernier point à gérer.
 * ==> Comment récupérer les info saisies dans ma boîte depuis ma fenêtre, vu que je
 * veux obtenir plusieurs informations ?
 * 
 * C'est vrai qu'on ne peut retourner qu'une seule valeur à la fois. Mais il peut y avoir
 * plusieurs solutions à ce problème :
 *  * Dans le cas où je n'ai qu'un composant, je peux adapter la méthode showZDialog() au
 *  	type de retour du composant utilisé
 *  * Dans mon cas, je veux plusieurs composants, donc plusieurs valeurs. Je peux :
 *  	==> retourner une collection de valeurs (ArrayList)
 *  	==> faire des accesseurs dans mon ZDialog
 *  	==> créer un objet dont le rôle est de collecter les informations dans ma boîte
 *  		et de retourner cet objet
 *  	==> etc.
 *  
 *  Je vais opter pour un objet qui collectera les informations et que je retournerai à la
 *  fin de la méthode showZDialog(). Avant de ma lancer dans sa création, je dois savoir
 *  ce que je vais mettre dans ma boîte... Je vais programmer une boîte permettant de 
 *  spécifier les caractéristiques d'un personnage :
 *   - son nom (un champ de saisie)
 *   - son genre (une combo)
 *   - sa taille (un champ de saisie)
 *   - sa couleur de cheveux (une combo)
 *   - sa tranche d'âge (des boutons radios)
 *   
 * Note : pour ce qui est du placement des composants, l'objet JDialog se comporte 
 * exactement comme un JFrame (BorderLayout par défaut, ajout d'un composant au 
 * conteneur...).
 * Je vais donc créer cet objet => Réf ZDialogInfo
 * L'avantage avec cette méthode, c'est que j'ai pas à me soucier d'une éventuelle
 * annulation de la saisie : l'objet d'information renverra toujours qqch.
 * Réf ZDialog
 * 
 * II./ Les menus
 * 
 * 1.) Faire son premier menu
 * 
 * MenuBar fait partie de la composition de l'objet JFrame. Le moment est venu pour
 * utiliser un composant de ce genre. Néanmoins, celui-ci appartient au package java.awt.
 * Dans ce chapitre, j'utiliserai son homologue, l'objet JMenuBar, issu du package
 * javax.swing. Pour travailler avec des menus, j'aurai besoin :
 *  * de l'objet JMenu, le titre principal d'un point de menu (Fichier, Edition...)
 *  * d'objets JMenuItem, les éléments composants mes menus
 *  
 * Afin de permettre des intéractions avec mes futurs menus, je vais devoir implémenter
 * l'interface ActionListener. Ces implémentations serviront à écouter les objets
 * JMenuItem : ce sont ces objets qui déclencheront l'une ou l'autre opération. Les JMenu,
 * eux, se comportent automatiquement : si on clique sur un titre de menu, celui-ci se 
 * déroule tout seul et, dans le cas où j'ai un tel objet présent dans un autre JMenu,
 * une autre liste se déroulera toute seule. 
 * 
 * Je vais transformer mon Animation en une Animation 1.2 avec un vrai menu à la place
 * des boutons.
 * Mais d'abord, je vais me familiariser un peu avec le JMenuBar
 * Réf ZFenetre
 * 
 * A présent, je vais créer un menu pour mon animation. D'abord sans la gestion des
 * événements. Pour le moment, je vais avoir besoin :
 *  * d'un menu Animation pour lancer, interrompre (par défaut à setEnabled(false) ou
 *  	quitter l'animation
 *  * d'un menu Forme afin de sélectionner le type de forme à utiliser (sous-menu + radio
 *  	par forme)
 *  * d'un menu A propos avec un joli "?" qui va ouvrir une boîte de dialogue
 *  
 * Il ne faut surtout pas effacer les implémentations pour les événements : je retire 
 * seulement les composants qui les utilisent. Ensuite, je crée mon menu !
 * Réf Animation 1.2
 * 
 * Pour faire communiquer les menus et mon animaton, il suffit d'indiquer à mes MenuItem
 * qu'on les écoute. En fait, cela revient à faire comme si je cliquais sur mes boutons
 * (à l'exception des cases à cocher et des radios, où, là, je peux utiliser une
 * implémentation d'ActionListener ou d'ItemListener).
 * Et dans le Panneau :
 * J'ai ajouté :  || this.forme.equals("CARRÉ") :
 *	if(this.forme.equals("CARRE") || this.forme.equals("CARRÉ")){
 * g.fillRect(posX, posY, 50, 50);
 * }
 * Réf Animation 1.2
 * 
 * Il est temps d'ajouter des raccourcis clavier à mon application.
 * 
 * 2.) Les raccourcis clavier
 * 
 * Il est très simple d'insérer des raccourcis clavier. Pour ajouter un "accélérateur" 
 * (=> raccourcis clavier des éléments de menu) sur un JMenu, j'appelerai la méthode
 * setAccelerator(). Et pour ajouter un mnémonique (=> raccourcis permettant de simuler
 * un clic sur un point de menu) sur un JMenuItem, je me servirai de la méthode:
 * setMnemonic().
 * Attribuons le mnémonique "A" au menu Animation, le mnénomique "F" pour le menu
 * Forme et enfin "P" pour A propos. Il me suffit d'invoquer setMnemonic(char mnemonic) 
 * sur le JMenu que je désire.
 * Réf Animation 1.2
 * A présent, j'ai les lettres correspondant au mnémonique soulignées dans mes menus. Et
 * il y a mieux : si je tap ALT + <la lettre>, le menu correspondant se déroule ! 
 * Je peux aussi mettre des mnémoniques sur des objets JMenuItem. Il existe une autre 
 * façon d'ajouter un mnémonique sur un JMenu (c'est uniquement valable pour un JMenu !) :
 * en passant le mnémonique en 2e paramètre du constructeur de l'objet, comme ceci :
 * JMenu menu = new JMenu("Fichier", 'F'); ==> ce menu aura le mnémonique 'F'
 * 
 * Pour ajouter des accélérateurs, c'est quasiment pareil, si ce n'est que je devrai 
 * utiliser un nouvel objet : KeyStroke. Cet objet permet de déterminer la touche utilisée
 * ou à utiliser. C'est grâce à cet objet que je vais pouvoir construire des combinaisons
 * de touches pour mes accélérateurs. Je vais commencer par attribuer un simple caractère
 * comme accélérateur à mon JMenuItem Lancer en utilisant la méthode:
 * getKeyStroke(char caracter) de l'objet KeyStroke.
 * Je vais ajouter la ligne qui suit au début de ma méthode initMenu() de l'Animation 1.2:
 * lancement.setAccelerator(KeyStroke.getKeyStroke('c'));
 * En important bien sûr javax.swing.KeyStroke et java.awt.event.ActionEvent au préalable.
 * Réf Animation 1.2 => un petit "c" est apparu à côté du Lancement. Et je n'ai qu'à 
 * appuyer juste sur 'c' pour effectuer le lancement (==> même effet qu'un clic sur le
 * menu Lancement).
 * Attention, un accélérateur est sensible à la casse contrairement au mnémonique ("c" 
 * n'est pas la même chose que "C").
 * Appuyer sur "c" lancera systématiquement l'animation. C'est l'une des raison pour 
 * laquelle les accélérateurs sont, en général, des combinaisons du genre CTRL + c,
 * ou encore CTRL + SHIFT + S.
 * 
 * Pour cela, je vais utiliser une méthode getKeyStroke() un peu différente : elle ne
 * prendra pas le caractère de ma touche en argument, mais son code ainsi qu'une ou
 * plusieurs touche(s) composant la combinaison. Pour obtenir le code d'une touche, 
 * j'utiliserai l'objet KeyEvent, un objet qui stocke tous les codes des touches.
 * Dans le code qui suit, je crée un accélérateur CTRL + L pour le menu Lancement et
 * un accélérateur CTRL + SHIFT + A pour le menu Arrêt :
 * lancement.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK);
 *  //...
 * arret.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK + 
 * 			KeyEvent.SHIFT_DOWN_MASK);
 * 
 * En fait, la classe KeyEvent répertorie tous les codes de toutes les touches du clavier.
 * Une grande majorité d'entre eux sont sous la forme : VK_<le caractère ou le nom de la
 * touche en majuscules>. VK => Value of Key.
 * A part certaines touches de contrôle comme CTRL, SHIFT, ALT, etc. je peux facilement
 * retrouver le code d'une touche grâce à cet objet.
 * 
 * Note : Eclipse me dit que la version CTRL_DOWN_MASK est la plus récente  et qu'il est
 * vivement conseillé de l'utiliser.
 * 
 * Il est temps de voir à présent comment créer un menu contextuel.
 * 
 * 3.) Faire un menu contextuel
 * 
 * Je vais utiliser un autre objet JPopupMenu, dans lequel je mettrai mes JMenuItem ou/et
 * JMenu. Il faudra juste indiquer à mon JPopupMenu comment et où s'afficher. 
 * 
 * Voici les points importants de mon menu contextuel :
 * 	* Dans le cas d'opérations identiques à celles accessibles par le menu, je devrai
 * 		créer des objets qui s'étendent à ces deux menus.
 * 	* Le menu contextuel ne doit s'afficher que dans la zone où l'animation s'exécute,
 * 		pas dans le menu !
 *  * Il ne doit s'afficher que lors d'un clic droit
 * 
 * Je vais mettre dans mon menu contextuel les action "Lancer l'animation" et "Arrêter
 * l'animation", ainsi que deux nouveautés :
 * 		-	changer la couleur de fond de mon animation
 * 		-	changer la couleur de ma forme
 * 
 * Avant d'implémenter les deux nouveautés, je vais travailler sur les deux premières.
 * Lorsque je lancerai l'animation, je devrai mettre les deux menus Lancer l'animation
 * dans l'état sesEnabled(false) et les deux menus Arrêter l'animation dans l'état
 * setEnabled(true) (et pour l'arrêter, il faudra faire l'inverse).
 * Je vais utiliser le même objet qui écoute  pour les deux menus. Il me faudra créer une
 * véritable instance de ces objets et signaler à l'application que ces objets écoutent 
 * non seulement le menu d'en haut, mais aussi le menu contextuel.
 * J'ai parfaitement le droit de le faire : plusieurs objets peuvent écouter un même 
 * composant et plusieurs composants peuvent avoir le même objet qui les écoute. 
 * A ce stade, je dois juste tirer au clair ceci :
 *  * comment indiquer à mon panneau quand et où afficher le menu contextuel
 *  * comment lui spécifier qu'il doit le faire uniquement suite à un clic droit
 *  
 * Le déclenchement de l'affichage du pop-up doit se faire lors d'un clic de la souris.
 * Je connais une interface qui gère ce type d'événement : l'interface MouseListener. Je
 * vais donc indiquer à mon panneau qu'un objet du type de cette interface va l'écouter.
 * 
 * Note : tout comme dans le chapitre sur les zones de saisie, il existe une classe qui
 * contient toutes les méthodes de ladite interface : la classe MouseAdapter. Je peux
 * implémenter celle-ci afin de ne redéfinir que la méthode dont j'ai besoin. C'est
 * cette solution que je vais utiliser.
 * 
 * Il vaut mieux utiliser l'événement mouseReleased() plutôt que mouseClicked() pour une
 * raison évidente : si ces deux événements sont quasiment identiques, dans un certain 
 * cas, seul l'événement mouseClicked() sera appelé. Il s'agit du cas où je clique sur
 * une zone, déplace ma souris en dehors de la zone tout en maintenant le clic et relâche
 * le bouton de la souris. C'est pour cette raison que je vais préférer utiliser la 
 * méthode mouseReleased(). Ensuite, pour préciser où il faut afficher le menu contextuel,
 * je vais utiliser la méthode show(Component invoker, int x, int y) de la classe 
 * JPopupMenu :
 *  * Component invoker : désigne l'objet invoquant le menu contextuel, dans mon cas, 
 *  		l'instance de Panneau
 *  * int x : coordonnée x du menu
 *  * int y : coordonnée y du menu
 *  
 * Rappel : je peux me servir de l'objet MouseEvent pour déterminer les coordonnées de la
 * souris.
 * 
 * Pour détecter le clic droit : c'est encore l'objet MouseEvent qui va me sauver la mise.
 * En effet, il possède une méthode isPopupTrigger() qui renvoie vrai s'il s'agit d'un
 * clic droit.
 * Réf Animation 1.2
 * 
 * Les menus et les menus contextuels peuvent s'avérer vraiment utiles et ergonomiques !
 * En plus, ils sont relativement simples à implémenter (et à utiliser). Cependant, je
 * constate qu'il y a bcp de clics superflus, que ce soit pour utiliser un menu ou menu
 * contextuel : il faut au moins deux clics pour afficher leur contenu (sauf dans le cas
 * d'un accélérateur).
 * Pour contrer ce genre de choses, il existe un concept très puissant : la barre 
 * d'outils.
 * 
 * 4.) La barre d'outils
 * 
 * La barre d'outils sert à effectuer des actions qui se trouvent dans le menu, mais sans
 * devoir fouiller dans celui-ci ou mémoriser le raccourci clavier (accélérateur) qui y
 * est lié. Elle permet donc des actions rapides.
 * Elle est généralement composée d'une multitude de boutons, une image apposée sur 
 * chacun d'eux symbolisant l'action qu'il doit effectuer.
 * Pour créer et utiliser une barre d'outils, je vais utiliser l'objet JToolBar. Cet
 * objet fonctionne comme un menu classique, à une différence près : il prend des boutons
 * (JButton) en arguments, et il n'y a pas d'endroit spécifique où incorporer ma barre
 * d'outils (il faudra l'expliciter lors de sa création).
 * 
 * Tout d'abord, il faut des images à mettre sur mes boutons...
 * 
 * Au niveau des actions à gérer, pour le lancement de l'animation et l'arrêt, il faudra
 * penser à éditer le comportement des boutons de la barre d'outils, comme je l'ai fait
 * pour les deux actions du menu contextuel. Concernant les boutons pour les formes, c'est
 * un peu plus délicat. Les autres composants qui éditaient la forme de mon animation
 * étaient des boutons radios. Or, ici, j'ai des boutons standard. Outre le fait qu'il va
 * falloir une instance précise de FormeListener, j'aurai à modifier un peu son comporte -
 * ment...
 * 
 * Il me faut savoir si l'action vient d'un bouton radio du menu ou d'un bouton de la
 * barre d'outils : c'est l'objet ActionEvent qui me permettra d'accéder à cette info.
 * Je ne vais pas tester tous les boutons radio un par un, pour ces composants, le
 * système utilisé jusque là était très bien. Non, je vais tout simplement vérifier si
 * celui qui a déclenché l'action est un JRadioButtonMenuItem, et si c'est le cas, je 
 * testerai les boutons.
 * 
 * Rappel : chapitre sur la réflexivité ! La méthode getSource() me retourne un objet, il
 * est donc possible de conaître la classe de celui-ci avec la méthode getClass() et par
 * conséquent d'en obtenir le nom grâce à getName().
 * 
 * Il va falloir qu'on pense à mettre à jour le bouton radio sélectionné dans le menu. Et
 * là, pour mon plus grand bonheur, il y a une astuce qui marche pas mal du tout : lors
 * du clic sur un bouton de la barre d'outils, il suffit de déclencher l'événement sur
 * le bouton radio correspondant ! Dans la classe AbstractButton, dont héritent tous les
 * boutons, il y a la méthode doClick(). Cette méthode déclenche un événement identique
 * à un vrai clic de souris sur le composant. Ainsi, plutôt que de gérer la même façon
 * de faire à deux endroits, je vais rediriger l'action effectuée sur un composant vers
 * un autre.
 * Réf Animation 1.2
 * 
 * 5.) Utiliser les actions abstraites
 * 
 * J'ai vu précédemment comment centraliser des actions sur différents composants. Il 
 * existe une classe abstraite qui permet de gérer ce genre de choses, car elle peut
 * s'adapter à beaucoup de composants (en général, ceux qui ne font qu'une action, comme
 * un bouton, une case à cocher, mais pas une liste).
 * Le rôle de cette classe est d'attribuer automatiquement une action à un ou plusieurs
 * composants. Le principal avantage de ce procédé est que plusieurs composants travaillent
 * avec une implémentation de l'interface AbstractAction, mais son gros inconvénient
 * réside dans le fait que je dois dois programmer une implémentation par action :
 * 		-	une action pour la couleur de la forme en rouge
 * 		-	une action pour la couleur de la forme en bleu
 * 		-	une action pour la couleur de la forme en vert
 * 		-	une action pour la couleur de fond en rouge
 * 		-	une action pour la couleur de fond en bleu
 * 		-	une action pour la couleur de fond en vert
 * 		-	etc.
 * 
 * Cela peut être lourd à coder, il faut réfléchir et vérifier s'il est pertinent 
 * d'utiliser cette méthode.
 * 
 * Voici comment on l'utilise :
 * 
 * public class Fenetre extends JFrame{
  //Nous pouvons utiliser les actions abstraites directement dans un JButton
  private JButton bouton1 = new JButton(new RougeAction("ActionRouge", new ImageIcon("images/rouge.jpg"));

  //Ou créer une instance concrète
  private RougeAction rAct = new RougeAction("ActionRouge", new ImageIcon("images/rouge.jpg"));
  private JToolBar toolBar = new JToolBar();

  //…

  //Utiliser une action directement dans une barre d'outils
  private void initToolBar(){
    toolBar.add(rAct);
  }

  //…

  class RougeAction extends AbstractAction{
    //Constructeur avec le nom uniquement
    public RougeAction(String name){super(name);}

    //Le constructeur prend le nom et une icône en paramètre
    public RougeAction(String name, ImageIcon){super(name, img);}

    public void actionPerformed(ActionEvent){
      //Vous connaissez la marche à suivre
    }
  }
}
 *
 *Je peux voir que cela peut être très pratique. Désormais, je peux ajouter une action 
 * sur une barre d'outils, celle-ci crée automatiquement un bouton correspondant ! 
 * Utiliser les actions abstraites plutôt que des implémentations de telle ou telle 
 * interface est un choix qui me revient. Je peux d'ailleurs très bien appliquer ce
 * principe au code de mon animation, mais je constaterai qu'il s'alourdira. J'éviterai
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
		 //Boîte du message d'information  
		 jop1 = new JOptionPane();
		 ImageIcon img = new ImageIcon("information.png");
		 jop1.showMessageDialog(null, "Message Informatif", "Information", 
		 JOptionPane.INFORMATION_MESSAGE, img);
		 
		 // Boîte du message préventif 
		 jop2 = new JOptionPane();
		 img = new ImageIcon("warning.png");
		 jop2.showMessageDialog(null, "Message préventif", "Attention", 
		 JOptionPane.WARNING_MESSAGE, img);
		 
		 //Boîte du message d'erreur
		 jop3 = new JOptionPane();
		 img = new ImageIcon("error.png");
		 jop3.showMessageDialog(null, "Message d'erreur", "Erreur", 
		 JOptionPane.ERROR_MESSAGE, img);
		 
		 //Boîte de saisie
		 JOptionPane jop4 = new JOptionPane();
		 String nom = "";
		 nom = jop4.showInputDialog(null, "Veuillez décliner votre identité !", "Gendarmerie"
		 		+ " nationale", JOptionPane.QUESTION_MESSAGE);
		 jop4.showMessageDialog(null, "Vous vous appelez " + nom, "Information",
				 JOptionPane.INFORMATION_MESSAGE);
		 
		 //Insérer une liste dans une boîte de saisie 
		 JOptionPane jop5 = new JOptionPane();
		 String[] genre = {"Masculin", "Féminin", "Indéterminé"};
		 String gender;
		 gender = (String)jop5.showInputDialog(null, "Quel est votre genre ?", "Le genre", 
				 JOptionPane.QUESTION_MESSAGE, null, genre, genre[2]);
		 jop5.showMessageDialog(null, "Vous êtes plutôt "+ gender, "Etat civil",
				 JOptionPane.INFORMATION_MESSAGE);
		 
		 //Variante => retourne un int au lieu d'un objet
		 JOptionPane jop6 = new JOptionPane();
		 int indice;
		 indice = jop6.showOptionDialog(null, "Quel est votre genre ?", "Le gender", 
				 	JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				 	genre, genre[2]);
		 jop6.showMessageDialog(null, "Vous êtes donc plutôt " + genre[indice], 
				 "Etat civil", JOptionPane.INFORMATION_MESSAGE);
		 */
		Fenetre fen = new Fenetre();
	}

}
