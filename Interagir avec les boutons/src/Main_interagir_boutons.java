/*
 * 									Interagir avec les boutons
 * 
 * Voyons d'abord comment personnaliser un bouton.
 * 
 * I./ Une classe Bouton personnalis�e
 * 
 * Je cr�e une classe h�ritant de javax.swing.JButton, je l'appelle Bouton, et je 
 * red�finie sa m�thode paintComponent().
 * R�f Bouton
 * A pr�sent, je vais changer l'aspect de mon bouton en fonction de mon int�raction vis-�-
 * vis de ce dernier. Il existe des interfaces � impl�menter qui permettent de g�rer 
 * toutes sortes d'�v�nements dans mon IHM. 
 * 
 * 1.) Int�raction avec la souris : l'interface MouseListener
 * 
 * Pour d�tecter les �v�nements qui surviennent sur mon composant, Java utilise ce qu'on
 * appelle le design pattern observer. 
 * Je vais devoir impl�menter l'interface MouseListener dans ma classe Bouton. Je devrai
 * aussi pr�ciser � ma classe qu'elle devra tenir quelqu'un au courant de ses chanchements
 * d'�tre par rapport � la souris. Ce quelqu'un n'est autre... qu'elle m�me. Eh oui : ma
 * classe va s'�couter, ce qui signifie que d�s que mon objet observable (mon bouton) 
 * obtiendra des informations concernant les actions effectu�es par la souris, il indiquera
 * � l'objet qui l'observe (ie lui m�me) ce qu'il doit effectuer.
 * Cela est r�alisable gr�ce � la m�thode addMouseListener(MouseListener obj) qui prend
 * un objet MouseListener en param�tre (ici, elle prendra this). => Je peux utiliser le
 * type d'une interface comme supertype : ici, ma classe impl�mente l'interface 
 * MouseListener, je peux donc utiliser cet objet comme r�f�rence de cette interface. 
 * R�f Bouton
 * C'est en red�finissant les diff�rentes m�thodes pr�sentes dans l'interface MouseListener
 * que je vais g�rer les diff�rents images � dessiner dans mon objet. Voici ces m�thodes:
 -	public void mouseClicked(MouseEvent event) : m�thode appel�e lors du clic de la souris
 -	public void mouseEntered(MouseEvent event) : m�thode appel�e lors du survol de la souris
 -	public void mouseExited(MouseEvent event) ==> lorsque la souris sort de la zone du bouton
 -	public void mousePressed(MouseEvent event) ==> lorsque le bouton gauche de la souris est
 	enfonc�
 -	public void mouseReleased(MouseEvent event) : m�thode appel�e lorsque l'on rel�che le
 	clic de la souris
 	
 * Attention, m�me si je n'utilise pas toutes les m�thodes d'une interface, je dois malgr�
 * tout ins�rer le squelette des m�thodes non utilis�es (avec les accolades), et c'est 
 * �galement valable pour les classes abstraites.
 * 
 * Note : dans mon cas, la m�thode repaint() est appel�e de fa�on implicite : lorsqu'un
 * �v�nement est d�clench�, mon objet se redessine automatiquement ! Comme lorsque je
 * redimensionnais ma fen�tre dans les premiers chapitres.
 * 
 * Je n'ai alors plus qu'� modifier mon image en fonction de la m�thode invoqu�e. Mon objet
 * comportera les caract�ristiques suivantes :
 * 		-	il aura une teinte jaune au survol de la souris
 * 		-	il aura une teinte bleu/cyan lorsque l'on pressera le bouton gauche de la souris
 * 		-	il aura une teinte orang�e si l'on rel�che le clic
 * 		-	il reviendra � la normale lorsqu'on sort de la zone du bouton
 * 
 * R�f Bouton.
 * Lorsque je rel�che le clic en dehors de la zone du bouton ==> celui-ci a une teinte
 * orang�e (conform�ment � la m�thode mouseReleased()). Pour pallier ce probl�me, je vais
 * v�rifier que lorsque le clic est rel�ch�, la souris se trouve toujours dans la zone
 * du bouton.
 * 
 * J'ai impl�ment� l'interface MouseListener; il reste cependant un objet que je n'ai
 * pas encore utilis� => c'est le param�tre pr�sent dans toutes les m�thodes de cette 
 * interace : MouseEvent.
 * Cet objet me permet d'obtenir bcp d'informations sur les �v�nements. Je ne d�taillerai
 * pas tout ici, mais je verrai certains c�t�s pratiques. Par exemple, je peux 
 * r�cup�rer les coordonn�es x et y du curseur de la souris par rapport au Bouton gr�ce
 * aux m�thodes getX() et getY(). Cela signifie que si l'on rel�che le clic en dehors
 * de la zone du bouton, la valeur retourn�e par getY() sera n�gative. 
 * R�f Bouton:mouseReleased()
 * 
 * Note : dans les chapitres qui suivent, je verrai plusieurs interfaces pour les 
 * diff�rentes actions possibles sur une IHM. Il existe aussi une convention pour ces 
 * interfaces : leur nom commence par le type d'action, suivi du mot Listener.
 * 
 * II./ Interagir avec son bouton
 * 1.) D�clencher une action : l'interface ActionListener
 * 
 * Afin de g�rer les diff�rentes actions � effectuer selon le bouton sur lequel on clique,
 * je vais utiliser l'interface ActionListener.
 * Je ne vais pas impl�menter cette interface dans ma classe Bouton, mais dans ma classe
 * Fenetre, le but �tant de faire en sorte que lorsque l'on clique sur le bouton, il se
 * passe quelque chose dans mon application : changer un �tat, une variable, effectuer 
 * une incr�mentation... Enfin, n'importe quelle action ! 
 * Avec la m�thode addActionListener(), j'informerai l'objet observ� qu'un autre objet doit
 * �tre tenu au courant de l'�v�nement. Ici, je veux que ce soit mon application (ma 
 * Fenetre) qui �coute mon Bouton. Le but �tant de pouvoir lancer ou arr�ter l'animation
 * dans le Panneau.
 * 
 * Avant d'en arriver l�, je vais faire plus simple : je me pencherai dans un 1er temps
 * sur l'impl�mentation de l'interface MouseListener. Afin de voir toute la puissance de
 * cette interface, j'utiliserai un nouvel objet issu du javax.swing : le JLabel. 
 * Cet objet se comporte comme un libell� : il est sp�cialis� dans l'affichage de texte ou
 * d'image. Il est donc id�al pour mon 1er exemple.
 * On l'instancie et l'initialise de la m�me mani�re que le JButton :
 * JLabel label = new JLabel("Mon premier label");
 * Ou encore :
 * JLabel label2;
 * label2.setText("Mon deuxi�me label");
 * Je cr�e donc une variable d'instance de type JLabel avec le texte qui me pla�t et
 * je l'ajoute � mon content pane en position BorderLayout.NORTH
 * Autre chose : je reprends le code avec l'animation du chapitre sur l'animation (avec le
 * ballon).
 * R�sultat : le texte de cet objet est align� par d�faut en haut � gache. Il est possible
 * de modifier quelques param�tres, tels que :
 * 		-	l'alignement du texte => setHorizontalAlignment(JLable.CENTER)
 * 		-	la police du texte
 * 		-	la couleur du texte => setForeground(Color.blue)
 * 		-	d'autres param�tres.
 * R�f Fenetre.
 * A pr�sent, je peux impl�menter l'interface ActionListener. Celle-ci ne contient qu'une
 * seule m�thode !
 * Attention, il faut informer l'objet Bouton que l'objet Fenetre l'�coute. 
 * ==> j'ajoute ma Fenetre � la liste des objets qui �coutent mon Bouton gr�ce � la 
 * m�thode addActionListener(ActionListener obj) pr�sente dans la classe JButton, donc
 * utilisable avec la varible bouton. J'ajoute donc cette instruction dans le constructeur
 * de ma Fenetre en passant this en param�tre (puisque c'est ma Fenetre qui �coute le 
 * Bouton).
 * Une fois l'op�ration effectu�e, je peux modifier le texte du JLabel avec la m�thode
 * actionPerformed(). Je vais compter le nombre de fois que l'on a cliqu� sur le bouton:
 * j'ajoute donc une variable d'instance de type int (compteur) dans ma classe, puis
 * dans la m�thode actionPerformed(), j'incr�mente ce compteur et j'affiche son contenu
 * dans mon libell�.
 * R�f Fenetre: actionPerformed()
 * 
 * A pr�sent, je vais ajouter un 2e bouton � ma Fenetre � c�t� du premier (l'affichage du
 * bouton personnalis� risque toutefois d'�tre d�cevant, pour l'instant...).
 * Attention : il fo que j'ins�re les 2 bouton dans un JPanel, sinon seul le dernier
 * bouton ajout� appara�tra.
 * 
 * Maintenant, le probl�me est le suivant : comment effectuer deux actions diff�rentes 
 * dans la m�thode actionPerformed() ?
 * En effet, si je laisse la m�thode actionPerformed telle quelle, les deux boutons 
 * ex�cutent la m�me action lorsqu'on les clique. 
 * Il existe un moyen de conna�tre l'�l�ment ayant d�clench� l'�v�nement : se servir
 * de l'objet pass� en param�tre dans la m�thode actionPerformed(). Je peux exploiter
 * la m�thode getSource() de cet objet pour conna�tre le nom de l'instance ayant g�n�r�
 * l'�v�nement. 
 * R�f Fenetre :actionPerformed
 * Mon code fonctionne � merveille ! Cependant, une telle approche n'est pas tr�s orient�e
 * objet : si mon IHM contient une multitude de boutons, la m�thode actionPerformed() sera
 * tr�s charg�e. Bien s�r, je pourrai cr�er deux objets � part, chacun �coutant un bouton,
 * dont le r�le serait de r�agir de fa�on appropri�e pour chaque bouton; mais si j'avais
 * besoin de modifier des donn�es sp�cifiques � la classe contenant mes boutons, il 
 * faudrait ruser afin de parvenir � faire communiquer mes boutons... Pas terrible non 
 * plus !
 * 
 * 2.) Parler avec sa classe int�rieure
 * 
 * En Java, on peut cr�er ce qu'on appelle des classes internes. Cela consiste � d�clarer
 * une classe � l'int�rieur d'une autre classe. Ca peut para�tre tordu, mais c'est tr�s
 * pratique.
 * En effet, les classes internes poss�dent tous les avantanges des classes normales, de
 * l'h�ritage d'une superclasse � l'impl�mentation d'une interface. Elles b�n�ficient
 * donc du polymorphisme et de la covariance des variables. En outre, elles ont l'avantage
 * d'avoir acc�s aux attributs de la classe dans laquelle elles sont d�clar�es.
 * Dans le cas qui m'int�resse, cela permet de cr�er une impl�mentation de l'interface
 * ActionListener d�tach�e de ma classe Fenetre, mais pouvant utiliser ses attributs. La
 * d�claration d'une telle classe se fait exactement de la m�me mani�re que pour une 
 * classe normale, si ce n'est qu'elle se trouve d�j� dans une autre classe. On proc�de
 * donc comme ceci :
 * public class MaClasseExterne{
 * 		public MaClasseExterne(){
 * 			//...
 * 		}
 * 		
 * 		class MaClasseInterne{
 * 			public MaClasseInterne(){
 * 				//...
 * 			}
 * 		}
 * }
 * 
 * Gr�ce � cela, je pourrai concevoir une classe sp�cialis�e dans l'�coute des composants
 * et qui effectuera un traval bien d�termin�. Dans mon exemple, je cr��rai deux classes
 * internes impl�mentant chacune l'interface ActionListener et red�finissant la m�thode
 * actionPerformed() :
 * 		-	BoutonListener : qui �coutera le premier bouton
 * 		-	Bouton2Listener : qui �coutera le second bouton
 * Une fois ces op�rations effectu�es, il ne me reste plus qu'� indiquer � chaque bouton
 * "qui l'�coute" gr�ce � la m�thode addActionListener(). Et ma Fenetre n'a plus � 
 * impl�menter l'interface ActionListener (elle a des classes internes pour le faire !)
 * R�f Fenetre
 * Note : mes classes internes ont m�me acc�s aux attributs d�clar�s private de ma classe
 * Fenetre.
 * Dor�navant, je n'ai plus � me soucier du bouton qui a d�clench� l'�v�nement, car
 * je dispose de deux classes �coutant chacune un bouton. 
 * Note : je peux aussi faire �couter mon bouton par plusieurs classes. Il me suffit 
 * d'ajouter ces classes suppl�mentaires � l'aide de addActionListener().
 * Pour faire le test, je cr�e une 3 classe Bouton3Listener.
 * 
 * Les classes internes sont vraiment des classes � part enti�re. Elles peuvent �galement
 * h�riter d'une superclasse. De ce fait, c'est presque comme si on se trouvait dans le
 * cas d'un h�ritage multiple (ce n'en est pas un, m�me si �a y ressemble). 
 * Ce code est donc valide 
 * public class MaClasseExterne extends JFrame{
 * 		public MaClasseExterne(){
 * 			//..
 * 		}
 * 		
 * 		class MaClasseInterne extends JPanel{
 * 			public MaClasseInterne(){
 * 				//..
 * 			}
 * 		}
 * 		
 * 		class MaClasseInterne2 extends JButton{
 * 			public MaClasseInterne2(){
 * 				//..
 * 			}
 * 		}
 * }
 * 
 * Ce genre de classe peut s'av�rer tr�s utile.
 * Je vais maintenant �tudier une autre mani�re d'impl�menter des �couteurs et, par 
 * extension, des classes devant red�finir les m�thodes d'une classe abstraite ou d'une
 * interface
 * 
 * 3.) Les classes anonymes
 * 
 * Les classes anonymes sont le plus souvent utilis�es dans la gestion d'�v�nements
 * ponctuels, lorsque cr�er une classe pour un seul traitement est trop lourd. Note :
 * j'ai d�j� utilis� des classes anonymes (pour d�finir le comportement des boutons,
 * r�f CardLayout). Voici le code :
 * JButton bouton = new JButton("Contenu suivant");
 * bouton.addActionListener(new ActionListener(){
 * 		public void actionPerformed(ActionEvent arg0){
 * 			//action !
 * 		}
 * });
 * 
 * L'une des particularit�s de cette  m�thode, c'est que l'�couteur n'�coutera que ce
 * composant. En effet, il ne s'y trouve aucune d�claration de classe et j'instancie
 * une interface par l'instruction new ActionListener(). Je dois seulement red�finir la 
 * m�thode, que je connais bien maintenant, dans un bloc d'instructions; d'o� les
 * accolades apr�s l'instanciation.
 * 
 * Pourquoi appelle-t-on cela une classe anonyme ? => proc�der de cette mani�re revient
 * � cr�er une classe fille sans �tre oblig� de cr�er cette classe de fa�on explicite.
 * L'h�ritage se produit automatiquement.
 * En fait, le code ci-dessus revient � effectuer ceci :
 * class Fenetre extends JFrame{
 * 		//..
 * 		bouton.addActionListener(new ActionListenerBis());
 * 		//..
 * 		
 * 		public class ActionListenerBis implements ActionListener {
 * 			public void actionPerformed(ActionEvent event){
 * 				//action !
 * 			}
 * 		}
 * }
 * Seulement, la classe cr��e n'a pas de nom, l'h�ritage s'effectue de fa�on implicite.
 * On b�n�ficie donc de tous les avantages de la classe m�re en ne red�finissant que 
 * la m�thode qui nous int�resse !
 * Les classes anonymes peuvent �galement �tre utilis�es pour impl�menter des classes
 * abstraites. 
 * 
 * Les classes anonymes sont soumises aux m�mes r�gles que les classes "normales" :
 * 		-	utilisation des m�thodes non red�finies de la classe m�re
 * 		-	obligation de red�finir toutes les m�thodes d'une interface
 * 		-	obligation de red�finir les m�thodes abstraites d'une classe abstraite
 * 
 * Cependant, ces classes poss�dent des restrictions � cause de leur r�le et de leur
 * raison d'�tre :
 * 		-	elles ne peuvent pas �tre d�clar�es abstract
 * 		-	elles ne peuvent pas non plus �tre d�clar�es static
 * 		-	elles ne peuvent pas d�finir de constructeur
 * 		-	elles sont automatiquement d�clar�es final : on ne peut d�river de cette 
 * 			classe, l'h�ritage est donc impossible
 * 
 * 4.) Contr�ler son animation : lancement et arr�t
 * 
 * Pour parvenir � g�rer le lancement et l'arr�t de mon animation, je vais devoir modifier
 * un peu le code de ma classe Fenetre. Il va falloir changer le libell� des boutons 
 * de mon IHM : le 1er affichera Go et l'autre Stop. Pour �viter d'interrompre l'animation
 * lorsqu'elle n'est pas lanc�e et de l'animer quand elle l'est d�j�, je vais tant�t
 * activer et d�sactiver les boutons. C'est-�-dire :
 * 		-	au lancement, le bouton Go ne sera pas cliquable alors que le bouton Stop oui
 * 		-	si l'animation est interrompue, le bouton Stop ne sera plus cliquable, mais
 * 			le bouton Go le sera
 * Il existe une m�thode toute simple g�rant ces �tats :
 * 		-	bouton.setEnabled(false) : le bouton n'est plus cliquable
 * 		-	bouton.setEnabled(true) : le bouton est de nouveau cliquable
 * 
 * La m�thode setPreferredSize(new Dimension(int width, int height)) est la m�thode de
 *  gestion de dimension. Je peux m'en servir pour redimensionner mes deux boutons.
 * Afin de parvenir � contr�ler mon animation, notamment en ce qui concerne son arr�t et
 * sa reprise, je dois am�liorer ma m�thode Go(). Je sors donc de cette m�thode les deux 
 * entiers servant � recalculer les coordonn�es de mon rond. Pour que la boucle infinie
 * puisse �tre interrompue, je dois d�clarer un bol�en qui changera d'�tat selon le 
 * bouton sur lequel on cliquera; je l'utiliserai comme param�tre de la boucle.
 * Et l�, probl�me ! 
 * => l'animation s'arr�te certes lorsque je clique sur Stop, mais elle ne se relance pas
 * quand je clique sur Go !
 * Comment est-ce possible ?
 * Un thread est lanc� au d�marrage de mon application : c'est le processus principal
 * du programme. Au d�marrage, l'animation est donc lanc�e dans le m�me thread que mon
 * objet Fenetre. Lorsque je lui demande de s'arr�ter, aucun probl�me : les ressources
 * m�moire sont lib�r�es, on sort de la boucle infinie et l'application continue � 
 * fonctionner.
 * Mais lorsque je redemande � l'animation de se lancer, l'instruction se trouvant dans
 * la m�thode actionPerformed() appelle la m�thode go() et, �tant donn� que je me trouve
 * � l'int�rieur d'une boucle infinie, je reste dans la m�thode go() et ne sors plus de la
 * m�thode actionPerformed().
 * 
 * 5.) Explication du ph�nom�ne
 * 
 * Java g�re les appels aux m�thodes gr�ce � ce qu'on appelle vulgairement la pile qui
 * d�finit leur ordre d'ex�cution.
 * C'est le fameux syst�me LIFO (Last In First Out) => Java d�pile � partir
 * de la derni�re m�thode invoqu�e, et comme la m�thode go() est invoqu�e apr�s 
 * actionPerformed(), celle-ci doit se d�piler apr�s elle. Mais en entrant dans la m�thode
 * go(), on entre dans une boucle infinie,  la JVM ne peut donc pas la d�piler (la m�thode
 * actionPerformed() se trouve elle aussi bloqu�e : on n'en sort plus).
 * 
 * 
 * 
 * Petit b�mol : gr�ce � l'objet FontMetrics, j'ai pu obtenir les dimensions de la police
 * et de la cha�ne pass�e en param�tre comme libell� du bouton (R�f Bouton 41).
 * 
 * Spinoff : le design pattern Observer
 * 
 */
public class Main_interagir_boutons {

	public static void main(String[] args) {
		Fenetre fen = new Fenetre();

	}

}
