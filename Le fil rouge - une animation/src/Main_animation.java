/*
 * 								   Le fil rouge : une animation
 * 
 * Dans ce chapitre, je verrai comment créer une animation simple.
 * 
 * I./ Création de l'animation
 * 
 * En utilisant les deux classes créées précédemment (Fenêtre et Panneau), je vais pouvoir
 * créer un effet de déplacement. Le principe réside dans le fait de modifier les 
 * coordonnées de l'objet à déplacer tout en amenant l'objet Panneau à se redessiner. Tout
 * cela - dans une boucle.
 * 
 * Jusqu'à présent, je n'ai utilisé que des valeures fixes pour les coordonnées du rond,
 * mais il va falloir dynamiser tout cela. Je vais donc créer deux variables privées de
 * type int  dans la classe Panneau : appelons-les posX et posY. Dans l'animation que
 * je vais créer, le rond viendra de l'extérieur de la fenêtre 
 * ==> coordonnées (-50, - 50,)
 * ==> diamètre 50 pixels
 * 
 * De même, je crée les setters et les getters pour les coordonnées posX et posY. 
 * Autre chose : j'instancie mon Panneau non pas dans le constructeur de la fenêtre, mais
 * directement dans la fenêtre, avec une portée private.
 * D'autre part, pour faire déplacer la boule, j'ajoute une méthode privée dans la Fenetre,
 * et je l'appelerai tout à la fin du constructeur. 
 * Il ya deux nouvelles instructions dans cette classe privée (go()) que j'ai créée :
 * 		-	repaint() de l'objet JPanel : demande au composant, ici un JPanel, de se
 * 			repeindre => en fait, elle appelle de nouveau la méthode paintComponent()
 * 		-	Thread.sleep() : est un moyen de suspendre mon code. Elle met en attente mon
 * programme pendant un laps de temps défini dans la méthode sleep() exprimé en 
 * millisecondes (+ le temps d'attente est court, + l'animation est rapide). Thread est
 * en fait un objet qui permet de créer un nouveau processus dans un prog ou de gèrer le
 * processus principal.
 * Dans tous les progs, il y a au moins un processus : celui qui est en cours d'exécution.
 * Je verrai plus tard qu'il est possible de diviser certaines tâches en plusieurs 
 * processus afin de ne pas perdre du temps et des performances.
 * Note : cette instruction est dite à risque, je dois donc la mettre dans un bloc try/catch
 * 
 * A présent, le rendu de ma fenêtre n'est pas vraiement ce à quoi je m'attendais
 * ==> il fo effacer les traces de l'ancien écran en dessinant un rectangle de n'importe
 * quelle couleur occupant toute la surface disponible avant de peindre mon rond (dans
 * le Panneau)
 * 
 * II./ Améliorations
 * 
 * Il serait bien que mon animation se poursuive tant que je ne ferme pas ma fenêtre.
 * Pour faire cela, je dois recourir aux boucles infinies.
 * J'ai le choix, entre :
 * while(true){
 * ...
 * }
 * for(;;){
 * ...
 * }
 * do{
 * ...
 * }while(true);
 * 
 * Je vais donc remplacer la boucle finie du go() par une boucle infinie. Réf Fenetre.
 * Coordonnées à réinitialiser lorsque la boule sort de la fenêtre.
 * 
 * A présent, choses sérieuses : faire rirocher ma boule sur les bords de la fenêtre !
 * 
 * ATTENTION, dans la méthode go() de l'objet Fenetre :
 * this.getWidth() n'est pas égal à pan.getWidth()
 * C'est pan.getWidth()/ pan.getHeight() que je dois écrire pour... déterminer le sens
 * du déplacement de la boule.
 * 
 */
public class Main_animation {

	public static void main(String[] args) {
		Fenetre fen = new Fenetre();

	}

}
