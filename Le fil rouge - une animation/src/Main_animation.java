/*
 * 								   Le fil rouge : une animation
 * 
 * Dans ce chapitre, je verrai comment cr�er une animation simple.
 * 
 * I./ Cr�ation de l'animation
 * 
 * En utilisant les deux classes cr��es pr�c�demment (Fen�tre et Panneau), je vais pouvoir
 * cr�er un effet de d�placement. Le principe r�side dans le fait de modifier les 
 * coordonn�es de l'objet � d�placer tout en amenant l'objet Panneau � se redessiner. Tout
 * cela - dans une boucle.
 * 
 * Jusqu'� pr�sent, je n'ai utilis� que des valeures fixes pour les coordonn�es du rond,
 * mais il va falloir dynamiser tout cela. Je vais donc cr�er deux variables priv�es de
 * type int  dans la classe Panneau : appelons-les posX et posY. Dans l'animation que
 * je vais cr�er, le rond viendra de l'ext�rieur de la fen�tre 
 * ==> coordonn�es (-50, - 50,)
 * ==> diam�tre 50 pixels
 * 
 * De m�me, je cr�e les setters et les getters pour les coordonn�es posX et posY. 
 * Autre chose : j'instancie mon Panneau non pas dans le constructeur de la fen�tre, mais
 * directement dans la fen�tre, avec une port�e private.
 * D'autre part, pour faire d�placer la boule, j'ajoute une m�thode priv�e dans la Fenetre,
 * et je l'appelerai tout � la fin du constructeur. 
 * Il ya deux nouvelles instructions dans cette classe priv�e (go()) que j'ai cr��e :
 * 		-	repaint() de l'objet JPanel : demande au composant, ici un JPanel, de se
 * 			repeindre => en fait, elle appelle de nouveau la m�thode paintComponent()
 * 		-	Thread.sleep() : est un moyen de suspendre mon code. Elle met en attente mon
 * programme pendant un laps de temps d�fini dans la m�thode sleep() exprim� en 
 * millisecondes (+ le temps d'attente est court, + l'animation est rapide). Thread est
 * en fait un objet qui permet de cr�er un nouveau processus dans un prog ou de g�rer le
 * processus principal.
 * Dans tous les progs, il y a au moins un processus : celui qui est en cours d'ex�cution.
 * Je verrai plus tard qu'il est possible de diviser certaines t�ches en plusieurs 
 * processus afin de ne pas perdre du temps et des performances.
 * Note : cette instruction est dite � risque, je dois donc la mettre dans un bloc try/catch
 * 
 * A pr�sent, le rendu de ma fen�tre n'est pas vraiement ce � quoi je m'attendais
 * ==> il fo effacer les traces de l'ancien �cran en dessinant un rectangle de n'importe
 * quelle couleur occupant toute la surface disponible avant de peindre mon rond (dans
 * le Panneau)
 * 
 * II./ Am�liorations
 * 
 * Il serait bien que mon animation se poursuive tant que je ne ferme pas ma fen�tre.
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
 * Je vais donc remplacer la boucle finie du go() par une boucle infinie. R�f Fenetre.
 * Coordonn�es � r�initialiser lorsque la boule sort de la fen�tre.
 * 
 * A pr�sent, choses s�rieuses : faire rirocher ma boule sur les bords de la fen�tre !
 * 
 * ATTENTION, dans la m�thode go() de l'objet Fenetre :
 * this.getWidth() n'est pas �gal � pan.getWidth()
 * C'est pan.getWidth()/ pan.getHeight() que je dois �crire pour... d�terminer le sens
 * du d�placement de la boule.
 * 
 */
public class Main_animation {

	public static void main(String[] args) {
		Fenetre fen = new Fenetre();

	}

}
