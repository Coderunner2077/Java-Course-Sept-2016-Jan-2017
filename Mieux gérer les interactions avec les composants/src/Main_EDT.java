/*
 * 							Mieux g�rer les int�ractions avec les composants
 * 
 * L'EDT, pour "Event Dispatch Thread", permet d'am�liorer la performance et la r�activit�
 * de mes programmes Java. Comme son nom l'indique, il s'agit d'un thread, d'une pile d'appel.
 * Cependant, celui-ci a une particularit�, il s'occupe de g�rer toutes les modifications 
 * portant sur un composant graphique :
 * 	-	le redimensionnement
 * 	-	le changement de couleur
 * 	-	le changement de valeur
 * 	-	etc.
 * 
 * Mes applications graphiques seront plus s�res et plus performantes lorsque j'utiliserai ce 
 * thread pour effectuer tous les changements qui pourraient intervenir sur mon IHM.
 * 
 * I./ Pr�sentation des protagonistes
 * 
 * Je sais d�j� que lorsque je lance mon prog Java en mode console, un thread principal est
 * d�marr� pour empiler les instructions de mon prog jusqu'� la fin. Ce que j'ignore, c'est
 * qu'un autre thread est lanc� : celui qui s'occupe de toutes les t�ches de fond (lancement
 * de nouveaux threads...).
 * Or depuis un certain temps, on travaille le plus souvent en mode graphique. Et, un troisi�me
 * thread est lanc� qui se nomme EDT (Event Dispatch Tread). C'est dans celui-ci que tous les
 * changements pourtant sur des composants sont execut�s. 
 * R�f sch�ma.png
 * La philosophie de Java est que toute modification apport�e � un composant se fait obliga -
 * toirement dans l'EDT : lorsque j'utilise une m�thode actionPerformed, celle-ci, son contenu
 * compris, est execut�e dans l'EDT (c'est aussi le cas pour les autres intercepteurs d'�v�ne -
 * ments). La politique de Java est simple : toute action modifiant l'�tat d'un composant 
 * graphique doit se faire dans un seul et unique thread, l'EDT. Pourquoi ? C'est simple, les
 * composants graphiques ne sont pas "thread-safe" : ils ne peuvent pas �tre utilis�s par
 * plusieurs threads simultan�ment et assurer un fonctionnement sans errer ! Alors, pour 
 * s'assurer que les composants sont utilis�s au bon endroit, on doit placer toutes les int� -
 * ractions dans l'EDT.
 * 
 * Par contre, cela signifie que si dans une m�thode actionPerformed on a un traitement assez
 * long, c'est toute l'interface graphique qui sera fig�e ! 
 * 
 * La toute 1re fois que j'ai tent� de contr�ler mon animation... quand je cliquais sur le 
 * bouton pour la lancer, mon interface �tait bloqu�e �tant donn� que la m�thode contenant une
 * boucle infinie n'�tait pas d�pil�e du thread dans lequel elle �tait lanc�e. D'ailleurs, le
 * bouton s'affichait comme si on n'avait pas rel�ch� le clic; c'�tait d� au fait que l'ex�cu -
 * tion de ma m�thode se faisait dans l'EDT, bloquant ainsi toutes les actions sur mes compo -
 * sants.
 * 
 * R�f sch�ma2.png => la cr�ation et la mise � jour des composants ne peuvent s'effectuaient 
 * dans l'EDT car j'ai cliqu� sur un bouton engendrant un long, tr�s long traitement dans 
 * l'EDT : du coup, toute mon IHM est fig�e. Non pas parce que Java est lent, mais parce que
 * j'ai ex�cut� un traitement au mauvais endroit. Il existe toutefois quelques m�thodes
 * thread-safe :
 * 	- 	paint() et repaint()
 * 	-	validate(), invalidate() et revalidate()
 * 
 *  Celles-ci peuvent �tre appel�es depuis n'importe quel thread. 
 *  
 *  A ce stade, une question se pose : comment ex�cuter une action dans l'EDT ?
 *  Let's see it !
 *  
 *  II./ Utiliser l'EDT
 *  
 *  Java me fournit la classe SwingUtilities qui offre plusieurs m�thodes statiques permettant
 *  d'ins�rer du code dans l'EDT :
 *  	-	invokeLater(Runnable doRun) : ex�cute le thread en param�tre dans l'EDT et rend
 *  			imm�diatement la main au thread principal
 *  	-	invokeAndWait(Runnable doRun) : ex�cute le thread en param�tre dans l'EDT et attend
 *  			la fin de celui-ci pour rendre la main au thread principal
 *  	-	isEventDispatchThread() : retourne vrai si le thread dans lequel se trouve 
 *  			l'instruction est dans l'EDT
 *  
 * R�f Test1 
 * 
 * Au lancement de ce test, je constate que le thread principal ne reprend la main qu'apr�s
 * la fin de la m�thode updateBouton().
 * Attention : mes observations montrent que c'est le thread principal qui a empil� et 
 * d�pil� la m�thode updateBouton()
 * 
 * La solution pour rendre la main au thread principal avant la fin de la m�thode est de cr�er
 * un nouveau thread, mais cette fois je vais �galement ex�cuter la mise � jour du bouton
 * dans l'EDT.
 * 
 * R�f Test2
 *  
 * Remarques : Avec la m�thode invokeLater, le thread en param�tre est ex�cut� de fa�on 
 * asynchrone, ie plus tard, au moment o� l'autre thread est endormi (sleep). Alors que la
 * m�thode invokeAndWait ex�cute le thread en param�tre de fa�on synchronis�e, d'embl�e, 
 * directement. C-�-d que la m�thode invokeAndWait, invoque d'embl�e le thread en 
 * param�tre et ensuite, rien ne se passe jusqu'� la fin de l'instruction Thread.sleep(1000)).
 * Attention, la m�thode invokeAndWait lance deux exceptions : InterruptedException ainsi que
 * InvocationTargetException.
 * 
 * Sinon, les deux m�thodes lancent l'action dans le thread EDT (Thread[AWT-EventQueue...]), et 
 * dans les deux cas le thread principal reprend la main avant la fin de la m�thode.
 * 
 * Autre chose, en r�gle g�n�rale, on passe �galement la cr�ation de la fen�tre dans l'EDT.
 * 
 * Depuis la version 6 de Java, une classe est mise � disposition pour effectuer des 
 * traitements lourds et interagir avec l'EDT.
 * 
 * III./ La classe SwingWorker<T,V>
 * 
 * Cette derni�re est une classe abstraite permettant de r�aliser des traitements en t�che
 * de fond tout en dialoguant avec les composants graphiques via l'EDT, aussi bien en cours
 * de traitement qu'en fin de traitement. D�s que j'aurai un traitement prenant pas mal de
 * temps et devant interagir avec mon IHM, il faut penser au SwingWorker.
 * 
 * Vu que cette classe est abstraite, je vais devoir red�finir une m�thode : doInBackground().
 * Elle permet de red�finir ce que doit faire l'objet en t�che de fond. Une fois cette t�che
 * effectu�e, la m�thode doInBackground() prend fin. J'ai la possibilit� de red�finir la 
 * m�thode done() invoqu�e � la fin de la m�thode doInBackground() et qui a pour r�le 
 * d'interagir avec mon IHM en s'assurant que ce sera fait dans l'EDT. Impl�menter la
 * m�thode done() est optionnel, je ne suis nullement tenu de le faire.
 * 
 * R�f Test3
 * 
 * Le traitement se fait en t�che de fond (Thread[SwingWorker-pool-1-thread..]), et mon
 * composant est mis � jour dans l'EDT.
 * Pour interagir avec l'EDT pendant le traitement, il suffit d'utiliser la m�thode 
 * setProgress(int progress) combin�e avec l'�v�nement PropertyChangeListener, qui sera
 * inform� du changement d'�tat de la propri�t� progress.
 * 
 * R�f Test4
 * 
 * Les m�thodes que j'ai vue jusqu'ici sont issues de la classe SwingWorker, qui impl�mente
 * l'interface java.util.concurrent.Future, offrant les m�thodes suivantes :
 * 	-	get() : permet � la m�thode doInBackground de renvoyer son r�sultat � d'autres
 * 			threads
 * 	-	cancel() : essaye d'interrompre la t�che de doInBackground() en cours
 * 	-	isCancelled() : retourne vrai si l'action a �t� interrompue
 * 	-	isDone() : retourne vrai si l'action est termin�e
 * 
 * On peut donc utiliser ces m�thodes dans l'objet SwingWorker afin de r�cup�rer le r�sultat
 * d'un traitement. Pour le moment, je n'ai pas utilis� la g�n�ricit� de cette classe. Or,
 * comme indique le titre de cette section, SwingWorker peut prendre deux types g�n�riques.
 * Le 1er correspond au type de renvoi de la m�thode doInBackground() et, par extension, au
 * type de renvoi de la m�thode get(). Le deuxi�me est utilis� comme type de retour inter -
 * m�diaire pendant l'ex�cution de la m�thode doInBackground().
 * 
 * Afin de g�rer les r�sultats interm�diaires, je peux utiliser les m�thodes suivantes :
 * 
 * publish(V value) : publie le r�sultat interm�diaire pour la m�thode process(List<V> list)
 * 
 * process(List<V> list) : permet d'utiliser le r�sultat interm�diaire pour un traitement
 * 		sp�cifique
 * 
 * R�f Test5
 * 
 * Remarques : la m�thode publish(V value) d�finit les r�sultats interm�diaires. 
 * Et la m�thode process(List<V> list) se fait dans l'EDT.
 * 
 * La classe SwingWorker<T,V> offre la possibilit� de lancer des traitements dans un thread
 * tout en s'assurant que les mises � jour des composants se feront dans l'EDT.
 *  
 */
public class Main_EDT {

	public static void main(String[] args) {
		

	}

}
