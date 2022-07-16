/*
 * 									Les champs de formulaire
 * 
 * Je continue d'explorer les objets propos�s par swing. Ils sont vari�s et s'utilisent 
 * souvent de la m�me mani�re que les boutons. En fait, maintenant que j'ai compris le
 * fonctionnement du pattern observer, je travaillerai avec des interfaces et devrai
 * impl�menter pour g�rer les �v�nements avec mes composants.
 * 
 * I./ Les listes : l'objet JComboBox
 * 1.) Premi�re utilisation
 * R�f FenetreJCB.
 * En effet, il faut avant tout sp�cifier une taille � l'objet JComboBox avec la m�thode 
 * setPreferredSize(Dimension d). En revanche, cette liste est vide. Pour r�soudre ce probl�me, il
 * suffit d'utiliser la m�thode addItem(Object obj).
 * 
 * Note: lorsque l'objet affiche les �l�ments ajout�s, il appelle leur m�thode toString().
 * 
 * Pour initialiser une JComboBox, je peux aussi utiliser le constructeur prenant un tableau
 * d'objets en param�tre afin de renseigner tous les �l�ments d'un coup. Comme ceci :
 * String[] tab = {"Option 1", "Option 2", "Option 3", "Option 4"};
 * combo = new JComboBox(tab);
 * Cela m'�vite d'invoquer 4 fois la m�thode addItem()...
 * 
 * Je peux assigner un choix par d�faut avec la m�thode setSelectedIndex(int index). J'ai
 * aussi la possibilit� de changer la couleur du texte, la couleur de fond ou la police,
 * exactement comme avec un JLabel.
 * 
 * Depuis Java 7, l'objet JcomboBox peut �tre param�tr� avec un type g�n�rique, comme
 * ceci:
 * JComboBox<String> combo : new JComboBox<String>();
 * Ce qui permet de mieux g�rer le contenu de mes listes et ainsi mieux r�cup�rer les 
 * valeurs de ces derni�res.
 * 
 * Note : un autre objet que je ne traiterai pas ici accepte aussi un type param�tr�, 
 * l'objet JList<E>. Celui-ci est tr�s proche de JComboBox.
 * 
 * Il est temps d'apprendre � communiquer avec cet objet. 
 * 
 * 2.) L'interface ItemListener
 * 
 * Cette interface poss�de une m�thode � red�finir : itemStateChanged(ItemEvent e).
 * Celle-ci est appel�e lorsqu'un �l�ment a chang� d'�tat. R�f FenetreJCB
 * Et pour ajouter l'impl�mentation de l'ItemListener � la JComboBox, j'utilise la 
 * m�thode addItemListener. 
 * 
 * Note : lorsque je clique sur une autre option, mon objet commence par modifier l'�tat
 * de l'option pr�c�dente (l'�tat passe en DESELECTED) avant de changer celui de l'option
 * choisie (celle-ci passe � l'�tat SELECTED). Je peux donc suivre tr�s facilement l'�tat
 * de mes �l�ments gr�ce � cette interface.
 * 
 * Note 2 : Il semble que la m�thode itemStateChanged() ne peut pas �tre r�gl�e de telle
 * fa�on � ce qu'elle ne soit invoqu�e que si l'�tat d'une option change en SELECTED. Aut-
 * rement dit, y aura toujours une invocation pour l'�tat DESELECTED qui tra�ne derri�re.
 * 
 * Justement, pour plus de simplicit�, j'utiliserai l'interface ActionListener afin de 
 * r�cup�rer uniquement l'option choisie, en me servant de la m�thode de l'objet 
 * JComboBox => getSelectedItem R�f FenetreJCB
 * Je constate qu'en utilisant l'impl�mentation de ActionListener, je peux r�cup�rer 
 * l'option sur laquelle l'action a �t� effectu�e. L'appel de la m�thode getSelectedItem()
 * retourne la valeur de l'option s�lectionn�e; une fois r�cup�r�e, je peux travailler
 * avec ma liste !
 * 
 * A pr�sent, je vais reprendre mon animation afin d'inclure une liste pour changer la 
 * forme de mon animation
 * 
 * 3.) Changer la forme de mon animation
 * 
 * Je vais faire en sorte que mon animation ne se contente plus d'afficher un rond : je
 * pourrais d�sormais choisir la forme que je veux afficher.
 * Tr�s bien, pour r�aliser cela, je vais dynamiser un peu ma classe Panneau afin qu'elle
 * peigne une forme en fonction de mon choix.
 * Pour y parvenir, je vais ajouter une variable d'instance de type String qui contiendra
 * l'intitul� de la forme que je souhaite dessiner - appelons la "forme" - ainsi qu'un
 * mutateur permettant de red�finir cette variable.
 * Ma m�thode paintComponent() doit pouvoir dessiner la forme demand�e; ainsi, trois cas
 * de figure se profilent :
 * 	-	soit j'int�gre les instructions if dans cette m�thode et l'objet Graphics dessinera
 * 			en fonction de la variable
 *  -	soit je d�veloppe une m�thode priv�e appel�e dans la m�thode paintComponent() et
 *  		qui dessinera la forme demand�e
 *  -	soit j'utilise le pattern strategy afin d'encapsuler la fa�on dont je dessinerai
 *  		les formes de mon animation
 *  
 * Le pattern strategy est de loin la meilleure solution, mais afin de ne pas alourdir mes
 * exemples, je travaillerai "� l'ancienne".
 * Je vais donc d�velopper une m�thode priv�e - draw(Graphics g) - qui aura pour t�che
 * de dessiner la forme voulue. Je passerai l'objet Graphics dans la m�thode 
 * paintComponent() de sorte que cette derni�re puisse l'utiliser; c'est donc cette 
 * m�thode que je placerai dans les conditions.
 * Voici les formes que je vais dessiner :
 * 	-	le rond, forme par d�faut
 * 	-	le carr�
 * 	-	le triangle
 * 	-	l'�toile
 * 
 * Cela signifie que ma liste contiendra ces quatre choix et que le rond figurera en 1er
 * lieu. Je cr�erai aussi une impl�mentation d'ActionListener dans une classe interne
 * pour g�rer les actions de ma liste. Je l'ai appel�e FormeListener.
 * 
 * R�f Animation
 * 
 * Les choses vont maintenant s'accl�rer, car le principe est le m�me pour tous les
 * objets graphiques de base.
 * 
 * II./ Les cases � cocher : l'objet JCheckBox
 * 1.) Premi�re utilisation
 * 
 * Cet objet peut �tre instanci� avec un String en param�tre qui servira de libell�.
 * Je peux �galement cocher la case par d�faut en appelant la m�thode:
 * setSelected(Boolean bool) � laquelle je passe true. Cet objet poss�de, comme tous les
 * autres, une multitude de m�thodes qui simplifient la vie. A fouiner...
 * Je cr�e directement une impl�mentation de l'interface ActionListener, comme d'hab.
 * Je peux contr�ler si mon objet est coch� avec la m�thode isSelected() qui retourne un
 * bol�en.
 * Je cr�e une FenetreJChB h�rit�e de FRame.
 * R�f FenetreJChB
 * 
 * 2.) Un pseudomorphing pour mon animation
 * 
 * Je vais utiliser cet objet pour que mes formes changent de taille et proposent un
 * pseudo-effet de morphing.
 * Premi�rement, la taille de ma forme ne sera plus fixe.
 * J'ajoute une variable de type int dans ma classe Panneau - disons drawSize - initialis�e
 * � 50. Tout comme avec le d�placement, je dois savoir lorsqu'il fo augmenter ou 
 * r�duire la taille de ma forme : j'utiliserai donc la m�me m�thode que celle que j'ai
 * d�velopp� � ce moment-l�.
 * Un JCheckBox sera n�cessaire pour savoir si le "mode morphing" est activ�.
 * En ce qui concerne la taille, si on la r�duit ou l'augmente d'une unit� � chaque 
 * rafra�chissement, l'effet de morphing sera ultra rapide. Donc, pour ralentir l'effet,
 * j'utiliserai une m�thode retournant 1 ou 0 selon le nombre de rafraichissements. Cela
 * implique que j'aurai besoin d'une variable pour les d�nombrer. J'effectuerai une
 * augmentation ou une r�duction toutes les dix fois.
 * 
 * Pour bien s�parer les deux cas de figure, j'ins�rerai une 2e m�thode de dessin dans
 * la classe Panneau qui aura pour r�le de dessiner le morphing; appelons-la 
 * drawMorphing(Graphics g).
 * 
 * Lorsque je cocherai la case, le morphing s'activera, et il se d�sactivera une fois
 * d�coch�e. La classe Panneau devra donc disposer d'un mutateur pour le bool�en de
 * morphing.
 * 
 * Il faudra aussi g�rer la collision avec les bords dans ma classe Fenetre, puisque la 
 * taille de la forme n'est plus constante en mode morphing : m�thode go() � adapter en
 * cons�quence. Ma classe Panneau doit poss�der un accesseur permettant de retourner la
 * taille actuelle de la forme.
 * 
 * R�f Animation.
 * 
 * En effet, l'utilisation de JCheckBox est tr�s simple, je vais �tudier maintenant un
 * de ses cousins.
 * 
 * 3.) Le petit cousin : l'objet JRadioButton
 * 
 * Le principe est de proposer au moins deux choix, mais de ne permettre d'en s�lectionner
 * qu'un � la fois. L'instanciation se fait de la m�me mani�re que pour un JCheckBox;
 * d'ailleurs, j'utiliserai presque la m�me fen�tre que pour JCheckBox, en rempla�ant
 * JCheckBox par JRadioButton.
 * R�f FenetreJRB
 * 
 * Cet objet s'utilise de fa�on identique (trop m�me) que le pr�c�dent. Le probl�me, ici
 * c'est que je peux s�lectionner les deux options (alors que c'est normalement pas
 * possible). Pour qu'un seul bouton radio soit s�lectionn� � la fois, je dois d�finir 
 * un groupe de boutons � l'aide de ButtonGroup. J'y ajouterai mes boutons radio, et seule
 * une option pourra alors �tre s�lectionn�e. Et le tour est jou�.
 * 
 * III./ Les champs de texte : l'objet JTextField
 * 1.) Premi�re utilisation
 * 
 * Je vais maintenant utiliser l'objet JTextField, qui tout comme les objets vus pr�c�dem-
 * ment, poss�de des m�thodes de redimensionnement, de changement de couleur... 
 * R�f FenetreJTF
 * Je peux initialiser le contenu avec la m�thode setText(String str) ou le r�cup�rer
 * avec la m�thode getText().
 * 
 * Il existe un objet tr�s ressemblant � celui-ci, en un peu plus �toff�. En fait, cet
 * objet permet de cr�er un JTextField format� pour recevoir un certain type de donn�es
 * saisies (date, pourcentage, etc.).
 * 
 * 2.) Un objet plus restrictif : JFormattedTextField
 * 
 * Gr�ce � ce type d'objet, on pourrait �viter beaucoup de contr�les et de casts sur le
 * contenu de ses zones de texte. 
 * Un jour, inchAllah, j'aurai besoin d'une zone de texte qui n'accepte qu'un certain 
 * type de donn�es. Avec JFormattedTextField, on s'en approche (mais il y a mieux). Cet
 * objet retourne une valeur uniquement si celle-ci correspond � ce que l'on a autoris�
 * pr�alablement. En d'autres mots, si l'on veut par exemple que la zone de texte ne 
 * contienne que des entiers et rien d'autre, c'est possible ! En revanche, ce contr�le
 * ne s'effectue que lorsqu'on quitte le champ en question. Je peux ainsi saisir des 
 * lettres dans un objet n'acceptant que des entiers, mais la m�thode getText() ne 
 * renverra alors rien, car le contenu sera effac�, les donn�es ne correspondant pas aux
 * attentes de l'objet.
 * R�f FenetreJFTF
 * 
 * En plus, mon objet met automatiquement la saisie en forme lorsqu'elle est valide.
 * 
 * Voici ce qu'on peut utiliser dans ce genre de champ (� mettre en param�tre lors de 
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
 * Sans entrer dans les d�tails, je peux aussi utiliser un objet MaskFormatter qui permet
 * d'attribuer un format de longueur fixe � ma zone de texte. C'est tr�s pratique 
 * lorsque je souhaite introduire un num�ro de t�l�phone, un num�ro de S�curit� Sociale...
 * Je dois d�finir ce format avec un param�tre lors de l'instanciation du masque � l'aide
 * de m�tacaract�res. Ceux-ci indiquent � mon objet MaskFormatter ce que le contenu de 
 * ma zone de texte contiendra.
 * Voici la liste de ces m�tacaractr�res :
 * 	-	# : indique un chiffre
 * 	-	' : indique un caract�re d'�chappement
 * 	-	U : indique une lettre (les minuscules sont automatiquement chang�es en MAJ)
 * 	-	L : indique une lettre (les MAJ ==> minuscules)
 * 	-	A : indique un chiffre ou une lettre
 * 	-	? : indique une lettre
 * 	-	* : indique que tous les caract�res sont accept�s
 * 	-	H : indique que tous les caractr�res hexad�cimaux sont accept�s (0 � 9, A � F, a � f)
 * 
 * Note: L'instanciation d'un tel objet peut lever une ParseException. Je dois donc 
 * l'entourer d'un bloc try{...}catch{...}
 * 
 * Voici � quoi ressemblerait un format t�l�phonique :
 * 
 * try{
 * 		MaskFormatter tel = new MaskFormatter(### ### ### ###);
 * 		//Ou encore
 * 		MaskFormatter tel2 = new MaskFormatter(###-###-###-###);
 * 		//Je peux ensuite le passer � ma zone de texte
 * 		JFormattedTextField jtf = new JFormattedTextField(tel2);
 * } catch(ParseException e){
 * 		e.printStackTrace();
 * }
 * 
 * C'est carr�ment autre chose, le MaskFormatter avec ses m�tacaract�res oblige � saisir
 * uniquement ce que l'on souhaite faire saisir !
 * 
 * Jusque l�, c'est pas mal. Mais me voil� confront� � un nouveau probl�me : l'int�grit�
 * de mes donn�es. C'est en rapport avec le fait que l'utilisateur peut quand m�me entrer
 * n'importe quoi (par exemple un num�ro de t�l�phone avec que des 0, ou des 1...)
 * On peut sugg�rer � l'utilisateur ce qu'il doit renseigner comme donn�es dans les
 * champs, mais on ne doit pas lui faire aveuglement confiance ! C'est simple : on part
 * du principe de ne jamais faire confiance � l'utilisateur.
 * Je suis donc oblig� de faire une multitude de contr�les suppl�mentaires. Pour ce faire,
 * je peux :
 * 		-	tester chaque �l�ment du num�ro
 * 		-	tester le num�ro en entier
 * 		-	dans le cas o� je n'utilise pas de MaskFormatter, v�rifier en plus que les
 *				saisies sont num�riques
 *		-	utiliser une expression r�guli�re
 *		-	emp�cher la saisie d'un type de caract�res
 *		-	etc.
 *
 * En gros, je dois v�rifier l'int�grit� de mes donn�es (dans le cas qui m'int�resse, 
 * l'int�grit� de mes cha�nes de caractr�s) pendant ou apr�s la saisie. Cela prend une
 * grande part du temps lorsqu'on code des logiciels.
 * 
 * Avant de clore le chapitre, je verrai comment r�cup�rer les �l�ments du clavier
 * 
 * IV./ Contr�le du clavier : l'interface KeyListener
 * 
 * Je connais d�j� :
 * 	-	MouseListener qui int�ragit avec la souris
 * 	-	ActionListener qui int�ragit lors d'un clic sur un composant
 * 	-	l'interface ItemListener qui �coute les �v�nements sur une liste d�roulante
 * 
 * Voici � pr�sent l'interface KeyListener. Elle permet d'intercepter les �venements
 * clavier lorsqu'on :
 * 	-	presse une touche
 * 	-	rel�che une touche
 * 	-	tape sur une touche
 * 
 * Cette interface poss�de trois m�thodes abstraites(� red�finir dans une classe interne):
 * 		-	keyPressed(KeyEvent event) : appel�e lorsqu'on presse sur une touche
 * 		-	keyReleased(KeyEvent event) : appel�e lorsqu'on rel�che une touche (c'est � ce
 * 				moment l� que le composant se voit affecter la valeur de la touche)
 * 		-	keyTyped(KeyEvent event) : appel�e entre les deux m�thodes cit�es ci-dessus
 * 
 * L'objet KeyEvent me permettra d'obtenir des informations sur les touches qui ont �t�
 * utilis�es. Parmi celles-ci, j'utiliserai :
 * 	-	getKeyCode() : retourne le code de la touche
 * 	-	getKeyChar() : retourne le caract�re correspondant � la touche
 * 
 * Je peux aussi d�terminer lorsque certaines touches de contr�le ont �t� utilis�es(SHIFT,
 * CTRL...), conna�tre le composant � l'origine de l'�v�nement, etc. Ignored. A fouiner.
 * 
 * Pour des raisons de simplicit�, je n'utiliserai pas un JFormattedTextField mais un
 * JTextField sans MaskFormatter. Ainsi, je n'aurai pas � me pr�occuper des tirets de
 * mon champ.
 * Pour commencer, je vais examiner l'ordre dans lequel se d�roulent les �v�nements
 * clavier; il est vrai que ceux-ci se produisent si rapidement que l'on n'a pas le temps
 * de les voir d�filer. J'ai donc ajout� une pause � la fin de chaque m�thode de 
 * l'impl�mentation afin de mieux observer l'ordre d'ex�cution.
 * R�f FenetreJTF.
 * Ainsi donc, l'ordre dans lequel les �v�nements du clavier sont g�r�s : en 1er,
 * lorsqu'on presse sur la touche, en deuxi�me lieu, lorsqu'elle est tap�e, et enfin, 
 * lorsqu'elle est rel�ch�e.
 * Dans le cas qui m'int�resse, je souhaite que lorsque l'utilisateur saisit un
 * caract�re interdit, celui-ci soit automatiquement retir� de la zone de texte. Pour cela,
 * je proc�derai � un traitement sp�cifique dans la m�thode keyReleased(KeyEvent event).
 * Les codes des touches correspondant aux chiffres du pav� num�rique sont compris entre
 * 96 et 105.
 * A partir de l�, c'est simple, il me suffit de supprimer le caract�re tap� de la zone 
 * de saisie si son code n'est pas compris dans cet intervalle. Toutefois, un probl�me
 * se pose avec cette m�thode : ceux qui poss�dent un notebook sans pav� num�rique ne
 * pourront rien saisir alors qu'il est possible d'obtenir des chiffres en appuyant sur
 * MAJ + &, �, ", '...
 * Ce souci m'am�ne � opter pour une autre solution : cr�er une m�thode dont le type
 * de retour sera un bool�en indiquant si la saisie est num�rique ou non. Comment ? Tout
 * simplement en ex�cutant un Integer.parseInt(value), le tout envelopp� dans un 
 * try{...}catch(NumberFormatException ex){...}
 * Si j'essaye de convertir un caract�re "a" en entier, l'exception sera lev�e et la 
 * m�thode retournera alors false (true dans le cas contraire).
 * 
 * Attention : la m�thode parseInt() prend un String en param�tre. La m�thode getKeyChar(), 
 * elle, renvoie un char. Il faudra donc penser � faire la conversion.
 * 
 * R�f FenetreJTF.
 * Constat : les lettres simples sont interdites � la saisie => mission accomplie.
 * CEPENDANT, les caract�res sp�ciaux comme "�", "�", "�", etc. ne sont pas pris en
 * charge par cette m�thode. Par cons�quent, leur saisie reste possible.
 */
public class Main_champs_formulaire {

	public static void main(String[] args) {
		

	}

}
