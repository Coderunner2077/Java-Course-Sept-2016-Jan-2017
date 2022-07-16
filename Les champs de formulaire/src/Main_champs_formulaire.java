/*
 * 									Les champs de formulaire
 * 
 * Je continue d'explorer les objets proposés par swing. Ils sont variés et s'utilisent 
 * souvent de la même manière que les boutons. En fait, maintenant que j'ai compris le
 * fonctionnement du pattern observer, je travaillerai avec des interfaces et devrai
 * implémenter pour gérer les événements avec mes composants.
 * 
 * I./ Les listes : l'objet JComboBox
 * 1.) Première utilisation
 * Réf FenetreJCB.
 * En effet, il faut avant tout spécifier une taille à l'objet JComboBox avec la méthode 
 * setPreferredSize(Dimension d). En revanche, cette liste est vide. Pour résoudre ce problème, il
 * suffit d'utiliser la méthode addItem(Object obj).
 * 
 * Note: lorsque l'objet affiche les éléments ajoutés, il appelle leur méthode toString().
 * 
 * Pour initialiser une JComboBox, je peux aussi utiliser le constructeur prenant un tableau
 * d'objets en paramètre afin de renseigner tous les éléments d'un coup. Comme ceci :
 * String[] tab = {"Option 1", "Option 2", "Option 3", "Option 4"};
 * combo = new JComboBox(tab);
 * Cela m'évite d'invoquer 4 fois la méthode addItem()...
 * 
 * Je peux assigner un choix par défaut avec la méthode setSelectedIndex(int index). J'ai
 * aussi la possibilité de changer la couleur du texte, la couleur de fond ou la police,
 * exactement comme avec un JLabel.
 * 
 * Depuis Java 7, l'objet JcomboBox peut être paramétré avec un type générique, comme
 * ceci:
 * JComboBox<String> combo : new JComboBox<String>();
 * Ce qui permet de mieux gérer le contenu de mes listes et ainsi mieux récupérer les 
 * valeurs de ces dernières.
 * 
 * Note : un autre objet que je ne traiterai pas ici accepte aussi un type paramétré, 
 * l'objet JList<E>. Celui-ci est très proche de JComboBox.
 * 
 * Il est temps d'apprendre à communiquer avec cet objet. 
 * 
 * 2.) L'interface ItemListener
 * 
 * Cette interface possède une méthode à redéfinir : itemStateChanged(ItemEvent e).
 * Celle-ci est appelée lorsqu'un élément a changé d'état. Réf FenetreJCB
 * Et pour ajouter l'implémentation de l'ItemListener à la JComboBox, j'utilise la 
 * méthode addItemListener. 
 * 
 * Note : lorsque je clique sur une autre option, mon objet commence par modifier l'état
 * de l'option précédente (l'état passe en DESELECTED) avant de changer celui de l'option
 * choisie (celle-ci passe à l'état SELECTED). Je peux donc suivre très facilement l'état
 * de mes éléments grâce à cette interface.
 * 
 * Note 2 : Il semble que la méthode itemStateChanged() ne peut pas être réglée de telle
 * façon à ce qu'elle ne soit invoquée que si l'état d'une option change en SELECTED. Aut-
 * rement dit, y aura toujours une invocation pour l'état DESELECTED qui traîne derrière.
 * 
 * Justement, pour plus de simplicité, j'utiliserai l'interface ActionListener afin de 
 * récupérer uniquement l'option choisie, en me servant de la méthode de l'objet 
 * JComboBox => getSelectedItem Réf FenetreJCB
 * Je constate qu'en utilisant l'implémentation de ActionListener, je peux récupérer 
 * l'option sur laquelle l'action a été effectuée. L'appel de la méthode getSelectedItem()
 * retourne la valeur de l'option sélectionnée; une fois récupérée, je peux travailler
 * avec ma liste !
 * 
 * A présent, je vais reprendre mon animation afin d'inclure une liste pour changer la 
 * forme de mon animation
 * 
 * 3.) Changer la forme de mon animation
 * 
 * Je vais faire en sorte que mon animation ne se contente plus d'afficher un rond : je
 * pourrais désormais choisir la forme que je veux afficher.
 * Très bien, pour réaliser cela, je vais dynamiser un peu ma classe Panneau afin qu'elle
 * peigne une forme en fonction de mon choix.
 * Pour y parvenir, je vais ajouter une variable d'instance de type String qui contiendra
 * l'intitulé de la forme que je souhaite dessiner - appelons la "forme" - ainsi qu'un
 * mutateur permettant de redéfinir cette variable.
 * Ma méthode paintComponent() doit pouvoir dessiner la forme demandée; ainsi, trois cas
 * de figure se profilent :
 * 	-	soit j'intègre les instructions if dans cette méthode et l'objet Graphics dessinera
 * 			en fonction de la variable
 *  -	soit je développe une méthode privée appelée dans la méthode paintComponent() et
 *  		qui dessinera la forme demandée
 *  -	soit j'utilise le pattern strategy afin d'encapsuler la façon dont je dessinerai
 *  		les formes de mon animation
 *  
 * Le pattern strategy est de loin la meilleure solution, mais afin de ne pas alourdir mes
 * exemples, je travaillerai "à l'ancienne".
 * Je vais donc développer une méthode privée - draw(Graphics g) - qui aura pour tâche
 * de dessiner la forme voulue. Je passerai l'objet Graphics dans la méthode 
 * paintComponent() de sorte que cette dernière puisse l'utiliser; c'est donc cette 
 * méthode que je placerai dans les conditions.
 * Voici les formes que je vais dessiner :
 * 	-	le rond, forme par défaut
 * 	-	le carré
 * 	-	le triangle
 * 	-	l'étoile
 * 
 * Cela signifie que ma liste contiendra ces quatre choix et que le rond figurera en 1er
 * lieu. Je créerai aussi une implémentation d'ActionListener dans une classe interne
 * pour gérer les actions de ma liste. Je l'ai appelée FormeListener.
 * 
 * Réf Animation
 * 
 * Les choses vont maintenant s'acclérer, car le principe est le même pour tous les
 * objets graphiques de base.
 * 
 * II./ Les cases à cocher : l'objet JCheckBox
 * 1.) Première utilisation
 * 
 * Cet objet peut être instancié avec un String en paramètre qui servira de libellé.
 * Je peux également cocher la case par défaut en appelant la méthode:
 * setSelected(Boolean bool) à laquelle je passe true. Cet objet possède, comme tous les
 * autres, une multitude de méthodes qui simplifient la vie. A fouiner...
 * Je crée directement une implémentation de l'interface ActionListener, comme d'hab.
 * Je peux contrôler si mon objet est coché avec la méthode isSelected() qui retourne un
 * boléen.
 * Je crée une FenetreJChB héritée de FRame.
 * Réf FenetreJChB
 * 
 * 2.) Un pseudomorphing pour mon animation
 * 
 * Je vais utiliser cet objet pour que mes formes changent de taille et proposent un
 * pseudo-effet de morphing.
 * Premièrement, la taille de ma forme ne sera plus fixe.
 * J'ajoute une variable de type int dans ma classe Panneau - disons drawSize - initialisée
 * à 50. Tout comme avec le déplacement, je dois savoir lorsqu'il fo augmenter ou 
 * réduire la taille de ma forme : j'utiliserai donc la même méthode que celle que j'ai
 * développé à ce moment-là.
 * Un JCheckBox sera nécessaire pour savoir si le "mode morphing" est activé.
 * En ce qui concerne la taille, si on la réduit ou l'augmente d'une unité à chaque 
 * rafraîchissement, l'effet de morphing sera ultra rapide. Donc, pour ralentir l'effet,
 * j'utiliserai une méthode retournant 1 ou 0 selon le nombre de rafraichissements. Cela
 * implique que j'aurai besoin d'une variable pour les dénombrer. J'effectuerai une
 * augmentation ou une réduction toutes les dix fois.
 * 
 * Pour bien séparer les deux cas de figure, j'insérerai une 2e méthode de dessin dans
 * la classe Panneau qui aura pour rôle de dessiner le morphing; appelons-la 
 * drawMorphing(Graphics g).
 * 
 * Lorsque je cocherai la case, le morphing s'activera, et il se désactivera une fois
 * décochée. La classe Panneau devra donc disposer d'un mutateur pour le booléen de
 * morphing.
 * 
 * Il faudra aussi gérer la collision avec les bords dans ma classe Fenetre, puisque la 
 * taille de la forme n'est plus constante en mode morphing : méthode go() à adapter en
 * conséquence. Ma classe Panneau doit posséder un accesseur permettant de retourner la
 * taille actuelle de la forme.
 * 
 * Réf Animation.
 * 
 * En effet, l'utilisation de JCheckBox est très simple, je vais étudier maintenant un
 * de ses cousins.
 * 
 * 3.) Le petit cousin : l'objet JRadioButton
 * 
 * Le principe est de proposer au moins deux choix, mais de ne permettre d'en sélectionner
 * qu'un à la fois. L'instanciation se fait de la même manière que pour un JCheckBox;
 * d'ailleurs, j'utiliserai presque la même fenêtre que pour JCheckBox, en remplaçant
 * JCheckBox par JRadioButton.
 * Réf FenetreJRB
 * 
 * Cet objet s'utilise de façon identique (trop même) que le précédent. Le problème, ici
 * c'est que je peux sélectionner les deux options (alors que c'est normalement pas
 * possible). Pour qu'un seul bouton radio soit sélectionné à la fois, je dois définir 
 * un groupe de boutons à l'aide de ButtonGroup. J'y ajouterai mes boutons radio, et seule
 * une option pourra alors être sélectionnée. Et le tour est joué.
 * 
 * III./ Les champs de texte : l'objet JTextField
 * 1.) Première utilisation
 * 
 * Je vais maintenant utiliser l'objet JTextField, qui tout comme les objets vus précédem-
 * ment, possède des méthodes de redimensionnement, de changement de couleur... 
 * Réf FenetreJTF
 * Je peux initialiser le contenu avec la méthode setText(String str) ou le récupérer
 * avec la méthode getText().
 * 
 * Il existe un objet très ressemblant à celui-ci, en un peu plus étoffé. En fait, cet
 * objet permet de créer un JTextField formaté pour recevoir un certain type de données
 * saisies (date, pourcentage, etc.).
 * 
 * 2.) Un objet plus restrictif : JFormattedTextField
 * 
 * Grâce à ce type d'objet, on pourrait éviter beaucoup de contrôles et de casts sur le
 * contenu de ses zones de texte. 
 * Un jour, inchAllah, j'aurai besoin d'une zone de texte qui n'accepte qu'un certain 
 * type de données. Avec JFormattedTextField, on s'en approche (mais il y a mieux). Cet
 * objet retourne une valeur uniquement si celle-ci correspond à ce que l'on a autorisé
 * préalablement. En d'autres mots, si l'on veut par exemple que la zone de texte ne 
 * contienne que des entiers et rien d'autre, c'est possible ! En revanche, ce contrôle
 * ne s'effectue que lorsqu'on quitte le champ en question. Je peux ainsi saisir des 
 * lettres dans un objet n'acceptant que des entiers, mais la méthode getText() ne 
 * renverra alors rien, car le contenu sera effacé, les données ne correspondant pas aux
 * attentes de l'objet.
 * Réf FenetreJFTF
 * 
 * En plus, mon objet met automatiquement la saisie en forme lorsqu'elle est valide.
 * 
 * Voici ce qu'on peut utiliser dans ce genre de champ (à mettre en paramètre lors de 
 * l'instanciation) :
 * 	==> NumberFormat avec :
 * 					-	getIntegerInstance()
 * 					-	getPercentInstance()
 * 					-	getNumberInstance()
 * 
 * ==> DateFormat avec :
 * 					-	getTimeInstance()
 * 					-	getDateInstance()
 * ==> MessageFormat
 * 
 * Sans entrer dans les détails, je peux aussi utiliser un objet MaskFormatter qui permet
 * d'attribuer un format de longueur fixe à ma zone de texte. C'est très pratique 
 * lorsque je souhaite introduire un numéro de téléphone, un numéro de Sécurité Sociale...
 * Je dois définir ce format avec un paramètre lors de l'instanciation du masque à l'aide
 * de métacaractères. Ceux-ci indiquent à mon objet MaskFormatter ce que le contenu de 
 * ma zone de texte contiendra.
 * Voici la liste de ces métacaractrères :
 * 	-	# : indique un chiffre
 * 	-	' : indique un caractère d'échappement
 * 	-	U : indique une lettre (les minuscules sont automatiquement changées en MAJ)
 * 	-	L : indique une lettre (les MAJ ==> minuscules)
 * 	-	A : indique un chiffre ou une lettre
 * 	-	? : indique une lettre
 * 	-	* : indique que tous les caractères sont acceptés
 * 	-	H : indique que tous les caractrères hexadécimaux sont acceptés (0 à 9, A à F, a à f)
 * 
 * Note: L'instanciation d'un tel objet peut lever une ParseException. Je dois donc 
 * l'entourer d'un bloc try{...}catch{...}
 * 
 * Voici à quoi ressemblerait un format téléphonique :
 * 
 * try{
 * 		MaskFormatter tel = new MaskFormatter(### ### ### ###);
 * 		//Ou encore
 * 		MaskFormatter tel2 = new MaskFormatter(###-###-###-###);
 * 		//Je peux ensuite le passer à ma zone de texte
 * 		JFormattedTextField jtf = new JFormattedTextField(tel2);
 * } catch(ParseException e){
 * 		e.printStackTrace();
 * }
 * 
 * C'est carrément autre chose, le MaskFormatter avec ses métacaractères oblige à saisir
 * uniquement ce que l'on souhaite faire saisir !
 * 
 * Jusque là, c'est pas mal. Mais me voilà confronté à un nouveau problème : l'intégrité
 * de mes données. C'est en rapport avec le fait que l'utilisateur peut quand même entrer
 * n'importe quoi (par exemple un numéro de téléphone avec que des 0, ou des 1...)
 * On peut suggérer à l'utilisateur ce qu'il doit renseigner comme données dans les
 * champs, mais on ne doit pas lui faire aveuglement confiance ! C'est simple : on part
 * du principe de ne jamais faire confiance à l'utilisateur.
 * Je suis donc obligé de faire une multitude de contrôles supplémentaires. Pour ce faire,
 * je peux :
 * 		-	tester chaque élément du numéro
 * 		-	tester le numéro en entier
 * 		-	dans le cas où je n'utilise pas de MaskFormatter, vérifier en plus que les
 *				saisies sont numériques
 *		-	utiliser une expression régulière
 *		-	empêcher la saisie d'un type de caractères
 *		-	etc.
 *
 * En gros, je dois vérifier l'intégrité de mes données (dans le cas qui m'intéresse, 
 * l'intégrité de mes chaînes de caractrès) pendant ou après la saisie. Cela prend une
 * grande part du temps lorsqu'on code des logiciels.
 * 
 * Avant de clore le chapitre, je verrai comment récupérer les éléments du clavier
 * 
 * IV./ Contrôle du clavier : l'interface KeyListener
 * 
 * Je connais déjà :
 * 	-	MouseListener qui intéragit avec la souris
 * 	-	ActionListener qui intéragit lors d'un clic sur un composant
 * 	-	l'interface ItemListener qui écoute les événements sur une liste déroulante
 * 
 * Voici à présent l'interface KeyListener. Elle permet d'intercepter les évenements
 * clavier lorsqu'on :
 * 	-	presse une touche
 * 	-	relâche une touche
 * 	-	tape sur une touche
 * 
 * Cette interface possède trois méthodes abstraites(à redéfinir dans une classe interne):
 * 		-	keyPressed(KeyEvent event) : appelée lorsqu'on presse sur une touche
 * 		-	keyReleased(KeyEvent event) : appelée lorsqu'on relâche une touche (c'est à ce
 * 				moment là que le composant se voit affecter la valeur de la touche)
 * 		-	keyTyped(KeyEvent event) : appelée entre les deux méthodes citées ci-dessus
 * 
 * L'objet KeyEvent me permettra d'obtenir des informations sur les touches qui ont été
 * utilisées. Parmi celles-ci, j'utiliserai :
 * 	-	getKeyCode() : retourne le code de la touche
 * 	-	getKeyChar() : retourne le caractère correspondant à la touche
 * 
 * Je peux aussi déterminer lorsque certaines touches de contrôle ont été utilisées(SHIFT,
 * CTRL...), connaître le composant à l'origine de l'événement, etc. Ignored. A fouiner.
 * 
 * Pour des raisons de simplicité, je n'utiliserai pas un JFormattedTextField mais un
 * JTextField sans MaskFormatter. Ainsi, je n'aurai pas à me préoccuper des tirets de
 * mon champ.
 * Pour commencer, je vais examiner l'ordre dans lequel se déroulent les événements
 * clavier; il est vrai que ceux-ci se produisent si rapidement que l'on n'a pas le temps
 * de les voir défiler. J'ai donc ajouté une pause à la fin de chaque méthode de 
 * l'implémentation afin de mieux observer l'ordre d'exécution.
 * Réf FenetreJTF.
 * Ainsi donc, l'ordre dans lequel les événements du clavier sont gérés : en 1er,
 * lorsqu'on presse sur la touche, en deuxième lieu, lorsqu'elle est tapée, et enfin, 
 * lorsqu'elle est relâchée.
 * Dans le cas qui m'intéresse, je souhaite que lorsque l'utilisateur saisit un
 * caractère interdit, celui-ci soit automatiquement retiré de la zone de texte. Pour cela,
 * je procéderai à un traitement spécifique dans la méthode keyReleased(KeyEvent event).
 * Les codes des touches correspondant aux chiffres du pavé numérique sont compris entre
 * 96 et 105.
 * A partir de là, c'est simple, il me suffit de supprimer le caractère tapé de la zone 
 * de saisie si son code n'est pas compris dans cet intervalle. Toutefois, un problème
 * se pose avec cette méthode : ceux qui possèdent un notebook sans pavé numérique ne
 * pourront rien saisir alors qu'il est possible d'obtenir des chiffres en appuyant sur
 * MAJ + &, é, ", '...
 * Ce souci m'amène à opter pour une autre solution : créer une méthode dont le type
 * de retour sera un booléen indiquant si la saisie est numérique ou non. Comment ? Tout
 * simplement en exécutant un Integer.parseInt(value), le tout enveloppé dans un 
 * try{...}catch(NumberFormatException ex){...}
 * Si j'essaye de convertir un caractère "a" en entier, l'exception sera levée et la 
 * méthode retournera alors false (true dans le cas contraire).
 * 
 * Attention : la méthode parseInt() prend un String en paramètre. La méthode getKeyChar(), 
 * elle, renvoie un char. Il faudra donc penser à faire la conversion.
 * 
 * Réf FenetreJTF.
 * Constat : les lettres simples sont interdites à la saisie => mission accomplie.
 * CEPENDANT, les caractères spéciaux comme "ô", "î", "ï", etc. ne sont pas pris en
 * charge par cette méthode. Par conséquent, leur saisie reste possible.
 */
public class Main_champs_formulaire {

	public static void main(String[] args) {
		

	}

}
