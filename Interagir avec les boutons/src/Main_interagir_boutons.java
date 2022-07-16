/*
 * 									Interagir avec les boutons
 * 
 * Voyons d'abord comment personnaliser un bouton.
 * 
 * I./ Une classe Bouton personnalisée
 * 
 * Je crée une classe héritant de javax.swing.JButton, je l'appelle Bouton, et je 
 * redéfinie sa méthode paintComponent().
 * Réf Bouton
 * A présent, je vais changer l'aspect de mon bouton en fonction de mon intéraction vis-à-
 * vis de ce dernier. Il existe des interfaces à implémenter qui permettent de gérer 
 * toutes sortes d'événements dans mon IHM. 
 * 
 * 1.) Intéraction avec la souris : l'interface MouseListener
 * 
 * Pour détecter les événements qui surviennent sur mon composant, Java utilise ce qu'on
 * appelle le design pattern observer. 
 * Je vais devoir implémenter l'interface MouseListener dans ma classe Bouton. Je devrai
 * aussi préciser à ma classe qu'elle devra tenir quelqu'un au courant de ses chanchements
 * d'être par rapport à la souris. Ce quelqu'un n'est autre... qu'elle même. Eh oui : ma
 * classe va s'écouter, ce qui signifie que dès que mon objet observable (mon bouton) 
 * obtiendra des informations concernant les actions effectuées par la souris, il indiquera
 * à l'objet qui l'observe (ie lui même) ce qu'il doit effectuer.
 * Cela est réalisable grâce à la méthode addMouseListener(MouseListener obj) qui prend
 * un objet MouseListener en paramètre (ici, elle prendra this). => Je peux utiliser le
 * type d'une interface comme supertype : ici, ma classe implémente l'interface 
 * MouseListener, je peux donc utiliser cet objet comme référence de cette interface. 
 * Réf Bouton
 * C'est en redéfinissant les différentes méthodes présentes dans l'interface MouseListener
 * que je vais gérer les différents images à dessiner dans mon objet. Voici ces méthodes:
 -	public void mouseClicked(MouseEvent event) : méthode appelée lors du clic de la souris
 -	public void mouseEntered(MouseEvent event) : méthode appelée lors du survol de la souris
 -	public void mouseExited(MouseEvent event) ==> lorsque la souris sort de la zone du bouton
 -	public void mousePressed(MouseEvent event) ==> lorsque le bouton gauche de la souris est
 	enfoncé
 -	public void mouseReleased(MouseEvent event) : méthode appelée lorsque l'on relâche le
 	clic de la souris
 	
 * Attention, même si je n'utilise pas toutes les méthodes d'une interface, je dois malgré
 * tout insérer le squelette des méthodes non utilisées (avec les accolades), et c'est 
 * également valable pour les classes abstraites.
 * 
 * Note : dans mon cas, la méthode repaint() est appelée de façon implicite : lorsqu'un
 * événement est déclenché, mon objet se redessine automatiquement ! Comme lorsque je
 * redimensionnais ma fenêtre dans les premiers chapitres.
 * 
 * Je n'ai alors plus qu'à modifier mon image en fonction de la méthode invoquée. Mon objet
 * comportera les caractéristiques suivantes :
 * 		-	il aura une teinte jaune au survol de la souris
 * 		-	il aura une teinte bleu/cyan lorsque l'on pressera le bouton gauche de la souris
 * 		-	il aura une teinte orangée si l'on relâche le clic
 * 		-	il reviendra à la normale lorsqu'on sort de la zone du bouton
 * 
 * Réf Bouton.
 * Lorsque je relâche le clic en dehors de la zone du bouton ==> celui-ci a une teinte
 * orangée (conformément à la méthode mouseReleased()). Pour pallier ce problème, je vais
 * vérifier que lorsque le clic est relâché, la souris se trouve toujours dans la zone
 * du bouton.
 * 
 * J'ai implémenté l'interface MouseListener; il reste cependant un objet que je n'ai
 * pas encore utilisé => c'est le paramètre présent dans toutes les méthodes de cette 
 * interace : MouseEvent.
 * Cet objet me permet d'obtenir bcp d'informations sur les événements. Je ne détaillerai
 * pas tout ici, mais je verrai certains côtés pratiques. Par exemple, je peux 
 * récupérer les coordonnées x et y du curseur de la souris par rapport au Bouton grâce
 * aux méthodes getX() et getY(). Cela signifie que si l'on relâche le clic en dehors
 * de la zone du bouton, la valeur retournée par getY() sera négative. 
 * Réf Bouton:mouseReleased()
 * 
 * Note : dans les chapitres qui suivent, je verrai plusieurs interfaces pour les 
 * différentes actions possibles sur une IHM. Il existe aussi une convention pour ces 
 * interfaces : leur nom commence par le type d'action, suivi du mot Listener.
 * 
 * II./ Interagir avec son bouton
 * 1.) Déclencher une action : l'interface ActionListener
 * 
 * Afin de gérer les différentes actions à effectuer selon le bouton sur lequel on clique,
 * je vais utiliser l'interface ActionListener.
 * Je ne vais pas implémenter cette interface dans ma classe Bouton, mais dans ma classe
 * Fenetre, le but étant de faire en sorte que lorsque l'on clique sur le bouton, il se
 * passe quelque chose dans mon application : changer un état, une variable, effectuer 
 * une incrémentation... Enfin, n'importe quelle action ! 
 * Avec la méthode addActionListener(), j'informerai l'objet observé qu'un autre objet doit
 * être tenu au courant de l'événement. Ici, je veux que ce soit mon application (ma 
 * Fenetre) qui écoute mon Bouton. Le but étant de pouvoir lancer ou arrêter l'animation
 * dans le Panneau.
 * 
 * Avant d'en arriver là, je vais faire plus simple : je me pencherai dans un 1er temps
 * sur l'implémentation de l'interface MouseListener. Afin de voir toute la puissance de
 * cette interface, j'utiliserai un nouvel objet issu du javax.swing : le JLabel. 
 * Cet objet se comporte comme un libellé : il est spécialisé dans l'affichage de texte ou
 * d'image. Il est donc idéal pour mon 1er exemple.
 * On l'instancie et l'initialise de la même manière que le JButton :
 * JLabel label = new JLabel("Mon premier label");
 * Ou encore :
 * JLabel label2;
 * label2.setText("Mon deuxième label");
 * Je crée donc une variable d'instance de type JLabel avec le texte qui me plaît et
 * je l'ajoute à mon content pane en position BorderLayout.NORTH
 * Autre chose : je reprends le code avec l'animation du chapitre sur l'animation (avec le
 * ballon).
 * Résultat : le texte de cet objet est aligné par défaut en haut à gache. Il est possible
 * de modifier quelques paramètres, tels que :
 * 		-	l'alignement du texte => setHorizontalAlignment(JLable.CENTER)
 * 		-	la police du texte
 * 		-	la couleur du texte => setForeground(Color.blue)
 * 		-	d'autres paramètres.
 * Réf Fenetre.
 * A présent, je peux implémenter l'interface ActionListener. Celle-ci ne contient qu'une
 * seule méthode !
 * Attention, il faut informer l'objet Bouton que l'objet Fenetre l'écoute. 
 * ==> j'ajoute ma Fenetre à la liste des objets qui écoutent mon Bouton grâce à la 
 * méthode addActionListener(ActionListener obj) présente dans la classe JButton, donc
 * utilisable avec la varible bouton. J'ajoute donc cette instruction dans le constructeur
 * de ma Fenetre en passant this en paramètre (puisque c'est ma Fenetre qui écoute le 
 * Bouton).
 * Une fois l'opération effectuée, je peux modifier le texte du JLabel avec la méthode
 * actionPerformed(). Je vais compter le nombre de fois que l'on a cliqué sur le bouton:
 * j'ajoute donc une variable d'instance de type int (compteur) dans ma classe, puis
 * dans la méthode actionPerformed(), j'incrémente ce compteur et j'affiche son contenu
 * dans mon libellé.
 * Réf Fenetre: actionPerformed()
 * 
 * A présent, je vais ajouter un 2e bouton à ma Fenetre à côté du premier (l'affichage du
 * bouton personnalisé risque toutefois d'être décevant, pour l'instant...).
 * Attention : il fo que j'insère les 2 bouton dans un JPanel, sinon seul le dernier
 * bouton ajouté apparaîtra.
 * 
 * Maintenant, le problème est le suivant : comment effectuer deux actions différentes 
 * dans la méthode actionPerformed() ?
 * En effet, si je laisse la méthode actionPerformed telle quelle, les deux boutons 
 * exécutent la même action lorsqu'on les clique. 
 * Il existe un moyen de connaître l'élément ayant déclenché l'événement : se servir
 * de l'objet passé en paramètre dans la méthode actionPerformed(). Je peux exploiter
 * la méthode getSource() de cet objet pour connaître le nom de l'instance ayant généré
 * l'événement. 
 * Réf Fenetre :actionPerformed
 * Mon code fonctionne à merveille ! Cependant, une telle approche n'est pas très orientée
 * objet : si mon IHM contient une multitude de boutons, la méthode actionPerformed() sera
 * très chargée. Bien sûr, je pourrai créer deux objets à part, chacun écoutant un bouton,
 * dont le rôle serait de réagir de façon appropriée pour chaque bouton; mais si j'avais
 * besoin de modifier des données spécifiques à la classe contenant mes boutons, il 
 * faudrait ruser afin de parvenir à faire communiquer mes boutons... Pas terrible non 
 * plus !
 * 
 * 2.) Parler avec sa classe intérieure
 * 
 * En Java, on peut créer ce qu'on appelle des classes internes. Cela consiste à déclarer
 * une classe à l'intérieur d'une autre classe. Ca peut paraître tordu, mais c'est très
 * pratique.
 * En effet, les classes internes possèdent tous les avantanges des classes normales, de
 * l'héritage d'une superclasse à l'implémentation d'une interface. Elles bénéficient
 * donc du polymorphisme et de la covariance des variables. En outre, elles ont l'avantage
 * d'avoir accès aux attributs de la classe dans laquelle elles sont déclarées.
 * Dans le cas qui m'intéresse, cela permet de créer une implémentation de l'interface
 * ActionListener détachée de ma classe Fenetre, mais pouvant utiliser ses attributs. La
 * déclaration d'une telle classe se fait exactement de la même manière que pour une 
 * classe normale, si ce n'est qu'elle se trouve déjà dans une autre classe. On procède
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
 * Grâce à cela, je pourrai concevoir une classe spécialisée dans l'écoute des composants
 * et qui effectuera un traval bien déterminé. Dans mon exemple, je créérai deux classes
 * internes implémentant chacune l'interface ActionListener et redéfinissant la méthode
 * actionPerformed() :
 * 		-	BoutonListener : qui écoutera le premier bouton
 * 		-	Bouton2Listener : qui écoutera le second bouton
 * Une fois ces opérations effectuées, il ne me reste plus qu'à indiquer à chaque bouton
 * "qui l'écoute" grâce à la méthode addActionListener(). Et ma Fenetre n'a plus à 
 * implémenter l'interface ActionListener (elle a des classes internes pour le faire !)
 * Réf Fenetre
 * Note : mes classes internes ont même accès aux attributs déclarés private de ma classe
 * Fenetre.
 * Dorénavant, je n'ai plus à me soucier du bouton qui a déclenché l'événement, car
 * je dispose de deux classes écoutant chacune un bouton. 
 * Note : je peux aussi faire écouter mon bouton par plusieurs classes. Il me suffit 
 * d'ajouter ces classes supplémentaires à l'aide de addActionListener().
 * Pour faire le test, je crée une 3 classe Bouton3Listener.
 * 
 * Les classes internes sont vraiment des classes à part entière. Elles peuvent également
 * hériter d'une superclasse. De ce fait, c'est presque comme si on se trouvait dans le
 * cas d'un héritage multiple (ce n'en est pas un, même si ça y ressemble). 
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
 * Ce genre de classe peut s'avérer très utile.
 * Je vais maintenant étudier une autre manière d'implémenter des écouteurs et, par 
 * extension, des classes devant redéfinir les méthodes d'une classe abstraite ou d'une
 * interface
 * 
 * 3.) Les classes anonymes
 * 
 * Les classes anonymes sont le plus souvent utilisées dans la gestion d'événements
 * ponctuels, lorsque créer une classe pour un seul traitement est trop lourd. Note :
 * j'ai déjà utilisé des classes anonymes (pour définir le comportement des boutons,
 * réf CardLayout). Voici le code :
 * JButton bouton = new JButton("Contenu suivant");
 * bouton.addActionListener(new ActionListener(){
 * 		public void actionPerformed(ActionEvent arg0){
 * 			//action !
 * 		}
 * });
 * 
 * L'une des particularités de cette  méthode, c'est que l'écouteur n'écoutera que ce
 * composant. En effet, il ne s'y trouve aucune déclaration de classe et j'instancie
 * une interface par l'instruction new ActionListener(). Je dois seulement redéfinir la 
 * méthode, que je connais bien maintenant, dans un bloc d'instructions; d'où les
 * accolades après l'instanciation.
 * 
 * Pourquoi appelle-t-on cela une classe anonyme ? => procéder de cette manière revient
 * à créer une classe fille sans être obligé de créer cette classe de façon explicite.
 * L'héritage se produit automatiquement.
 * En fait, le code ci-dessus revient à effectuer ceci :
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
 * Seulement, la classe créée n'a pas de nom, l'héritage s'effectue de façon implicite.
 * On bénéficie donc de tous les avantages de la classe mère en ne redéfinissant que 
 * la méthode qui nous intéresse !
 * Les classes anonymes peuvent également être utilisées pour implémenter des classes
 * abstraites. 
 * 
 * Les classes anonymes sont soumises aux mêmes règles que les classes "normales" :
 * 		-	utilisation des méthodes non redéfinies de la classe mère
 * 		-	obligation de redéfinir toutes les méthodes d'une interface
 * 		-	obligation de redéfinir les méthodes abstraites d'une classe abstraite
 * 
 * Cependant, ces classes possèdent des restrictions à cause de leur rôle et de leur
 * raison d'être :
 * 		-	elles ne peuvent pas être déclarées abstract
 * 		-	elles ne peuvent pas non plus être déclarées static
 * 		-	elles ne peuvent pas définir de constructeur
 * 		-	elles sont automatiquement déclarées final : on ne peut dériver de cette 
 * 			classe, l'héritage est donc impossible
 * 
 * 4.) Contrôler son animation : lancement et arrêt
 * 
 * Pour parvenir à gérer le lancement et l'arrêt de mon animation, je vais devoir modifier
 * un peu le code de ma classe Fenetre. Il va falloir changer le libellé des boutons 
 * de mon IHM : le 1er affichera Go et l'autre Stop. Pour éviter d'interrompre l'animation
 * lorsqu'elle n'est pas lancée et de l'animer quand elle l'est déjà, je vais tantôt
 * activer et désactiver les boutons. C'est-à-dire :
 * 		-	au lancement, le bouton Go ne sera pas cliquable alors que le bouton Stop oui
 * 		-	si l'animation est interrompue, le bouton Stop ne sera plus cliquable, mais
 * 			le bouton Go le sera
 * Il existe une méthode toute simple gérant ces états :
 * 		-	bouton.setEnabled(false) : le bouton n'est plus cliquable
 * 		-	bouton.setEnabled(true) : le bouton est de nouveau cliquable
 * 
 * La méthode setPreferredSize(new Dimension(int width, int height)) est la méthode de
 *  gestion de dimension. Je peux m'en servir pour redimensionner mes deux boutons.
 * Afin de parvenir à contrôler mon animation, notamment en ce qui concerne son arrêt et
 * sa reprise, je dois améliorer ma méthode Go(). Je sors donc de cette méthode les deux 
 * entiers servant à recalculer les coordonnées de mon rond. Pour que la boucle infinie
 * puisse être interrompue, je dois déclarer un boléen qui changera d'état selon le 
 * bouton sur lequel on cliquera; je l'utiliserai comme paramètre de la boucle.
 * Et là, problème ! 
 * => l'animation s'arrête certes lorsque je clique sur Stop, mais elle ne se relance pas
 * quand je clique sur Go !
 * Comment est-ce possible ?
 * Un thread est lancé au démarrage de mon application : c'est le processus principal
 * du programme. Au démarrage, l'animation est donc lancée dans le même thread que mon
 * objet Fenetre. Lorsque je lui demande de s'arrêter, aucun problème : les ressources
 * mémoire sont libérées, on sort de la boucle infinie et l'application continue à 
 * fonctionner.
 * Mais lorsque je redemande à l'animation de se lancer, l'instruction se trouvant dans
 * la méthode actionPerformed() appelle la méthode go() et, étant donné que je me trouve
 * à l'intérieur d'une boucle infinie, je reste dans la méthode go() et ne sors plus de la
 * méthode actionPerformed().
 * 
 * 5.) Explication du phénomène
 * 
 * Java gère les appels aux méthodes grâce à ce qu'on appelle vulgairement la pile qui
 * définit leur ordre d'exécution.
 * C'est le fameux système LIFO (Last In First Out) => Java dépile à partir
 * de la dernière méthode invoquée, et comme la méthode go() est invoquée après 
 * actionPerformed(), celle-ci doit se dépiler après elle. Mais en entrant dans la méthode
 * go(), on entre dans une boucle infinie,  la JVM ne peut donc pas la dépiler (la méthode
 * actionPerformed() se trouve elle aussi bloquée : on n'en sort plus).
 * 
 * 
 * 
 * Petit bémol : grâce à l'objet FontMetrics, j'ai pu obtenir les dimensions de la police
 * et de la chaîne passée en paramètre comme libellé du bouton (Réf Bouton 41).
 * 
 * Spinoff : le design pattern Observer
 * 
 */
public class Main_interagir_boutons {

	public static void main(String[] args) {
		Fenetre fen = new Fenetre();

	}

}
