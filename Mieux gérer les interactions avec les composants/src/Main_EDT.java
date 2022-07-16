/*
 * 							Mieux gérer les intéractions avec les composants
 * 
 * L'EDT, pour "Event Dispatch Thread", permet d'améliorer la performance et la réactivité
 * de mes programmes Java. Comme son nom l'indique, il s'agit d'un thread, d'une pile d'appel.
 * Cependant, celui-ci a une particularité, il s'occupe de gérer toutes les modifications 
 * portant sur un composant graphique :
 * 	-	le redimensionnement
 * 	-	le changement de couleur
 * 	-	le changement de valeur
 * 	-	etc.
 * 
 * Mes applications graphiques seront plus sûres et plus performantes lorsque j'utiliserai ce 
 * thread pour effectuer tous les changements qui pourraient intervenir sur mon IHM.
 * 
 * I./ Présentation des protagonistes
 * 
 * Je sais déjà que lorsque je lance mon prog Java en mode console, un thread principal est
 * démarré pour empiler les instructions de mon prog jusqu'à la fin. Ce que j'ignore, c'est
 * qu'un autre thread est lancé : celui qui s'occupe de toutes les tâches de fond (lancement
 * de nouveaux threads...).
 * Or depuis un certain temps, on travaille le plus souvent en mode graphique. Et, un troisième
 * thread est lancé qui se nomme EDT (Event Dispatch Tread). C'est dans celui-ci que tous les
 * changements pourtant sur des composants sont executés. 
 * Réf schéma.png
 * La philosophie de Java est que toute modification apportée à un composant se fait obliga -
 * toirement dans l'EDT : lorsque j'utilise une méthode actionPerformed, celle-ci, son contenu
 * compris, est executée dans l'EDT (c'est aussi le cas pour les autres intercepteurs d'événe -
 * ments). La politique de Java est simple : toute action modifiant l'état d'un composant 
 * graphique doit se faire dans un seul et unique thread, l'EDT. Pourquoi ? C'est simple, les
 * composants graphiques ne sont pas "thread-safe" : ils ne peuvent pas être utilisés par
 * plusieurs threads simultanément et assurer un fonctionnement sans errer ! Alors, pour 
 * s'assurer que les composants sont utilisés au bon endroit, on doit placer toutes les inté -
 * ractions dans l'EDT.
 * 
 * Par contre, cela signifie que si dans une méthode actionPerformed on a un traitement assez
 * long, c'est toute l'interface graphique qui sera figée ! 
 * 
 * La toute 1re fois que j'ai tenté de contrôler mon animation... quand je cliquais sur le 
 * bouton pour la lancer, mon interface était bloquée étant donné que la méthode contenant une
 * boucle infinie n'était pas dépilée du thread dans lequel elle était lancée. D'ailleurs, le
 * bouton s'affichait comme si on n'avait pas relâché le clic; c'était dû au fait que l'exécu -
 * tion de ma méthode se faisait dans l'EDT, bloquant ainsi toutes les actions sur mes compo -
 * sants.
 * 
 * Réf schéma2.png => la création et la mise à jour des composants ne peuvent s'effectuaient 
 * dans l'EDT car j'ai cliqué sur un bouton engendrant un long, très long traitement dans 
 * l'EDT : du coup, toute mon IHM est figée. Non pas parce que Java est lent, mais parce que
 * j'ai exécuté un traitement au mauvais endroit. Il existe toutefois quelques méthodes
 * thread-safe :
 * 	- 	paint() et repaint()
 * 	-	validate(), invalidate() et revalidate()
 * 
 *  Celles-ci peuvent être appelées depuis n'importe quel thread. 
 *  
 *  A ce stade, une question se pose : comment exécuter une action dans l'EDT ?
 *  Let's see it !
 *  
 *  II./ Utiliser l'EDT
 *  
 *  Java me fournit la classe SwingUtilities qui offre plusieurs méthodes statiques permettant
 *  d'insérer du code dans l'EDT :
 *  	-	invokeLater(Runnable doRun) : exécute le thread en paramètre dans l'EDT et rend
 *  			immédiatement la main au thread principal
 *  	-	invokeAndWait(Runnable doRun) : exécute le thread en paramètre dans l'EDT et attend
 *  			la fin de celui-ci pour rendre la main au thread principal
 *  	-	isEventDispatchThread() : retourne vrai si le thread dans lequel se trouve 
 *  			l'instruction est dans l'EDT
 *  
 * Réf Test1 
 * 
 * Au lancement de ce test, je constate que le thread principal ne reprend la main qu'après
 * la fin de la méthode updateBouton().
 * Attention : mes observations montrent que c'est le thread principal qui a empilé et 
 * dépilé la méthode updateBouton()
 * 
 * La solution pour rendre la main au thread principal avant la fin de la méthode est de créer
 * un nouveau thread, mais cette fois je vais également exécuter la mise à jour du bouton
 * dans l'EDT.
 * 
 * Réf Test2
 *  
 * Remarques : Avec la méthode invokeLater, le thread en paramètre est exécuté de façon 
 * asynchrone, ie plus tard, au moment où l'autre thread est endormi (sleep). Alors que la
 * méthode invokeAndWait exécute le thread en paramètre de façon synchronisée, d'emblée, 
 * directement. C-à-d que la méthode invokeAndWait, invoque d'emblée le thread en 
 * paramètre et ensuite, rien ne se passe jusqu'à la fin de l'instruction Thread.sleep(1000)).
 * Attention, la méthode invokeAndWait lance deux exceptions : InterruptedException ainsi que
 * InvocationTargetException.
 * 
 * Sinon, les deux méthodes lancent l'action dans le thread EDT (Thread[AWT-EventQueue...]), et 
 * dans les deux cas le thread principal reprend la main avant la fin de la méthode.
 * 
 * Autre chose, en règle générale, on passe également la création de la fenêtre dans l'EDT.
 * 
 * Depuis la version 6 de Java, une classe est mise à disposition pour effectuer des 
 * traitements lourds et interagir avec l'EDT.
 * 
 * III./ La classe SwingWorker<T,V>
 * 
 * Cette dernière est une classe abstraite permettant de réaliser des traitements en tâche
 * de fond tout en dialoguant avec les composants graphiques via l'EDT, aussi bien en cours
 * de traitement qu'en fin de traitement. Dès que j'aurai un traitement prenant pas mal de
 * temps et devant interagir avec mon IHM, il faut penser au SwingWorker.
 * 
 * Vu que cette classe est abstraite, je vais devoir redéfinir une méthode : doInBackground().
 * Elle permet de redéfinir ce que doit faire l'objet en tâche de fond. Une fois cette tâche
 * effectuée, la méthode doInBackground() prend fin. J'ai la possibilité de redéfinir la 
 * méthode done() invoquée à la fin de la méthode doInBackground() et qui a pour rôle 
 * d'interagir avec mon IHM en s'assurant que ce sera fait dans l'EDT. Implémenter la
 * méthode done() est optionnel, je ne suis nullement tenu de le faire.
 * 
 * Réf Test3
 * 
 * Le traitement se fait en tâche de fond (Thread[SwingWorker-pool-1-thread..]), et mon
 * composant est mis à jour dans l'EDT.
 * Pour interagir avec l'EDT pendant le traitement, il suffit d'utiliser la méthode 
 * setProgress(int progress) combinée avec l'événement PropertyChangeListener, qui sera
 * informé du changement d'état de la propriété progress.
 * 
 * Réf Test4
 * 
 * Les méthodes que j'ai vue jusqu'ici sont issues de la classe SwingWorker, qui implémente
 * l'interface java.util.concurrent.Future, offrant les méthodes suivantes :
 * 	-	get() : permet à la méthode doInBackground de renvoyer son résultat à d'autres
 * 			threads
 * 	-	cancel() : essaye d'interrompre la tâche de doInBackground() en cours
 * 	-	isCancelled() : retourne vrai si l'action a été interrompue
 * 	-	isDone() : retourne vrai si l'action est terminée
 * 
 * On peut donc utiliser ces méthodes dans l'objet SwingWorker afin de récupérer le résultat
 * d'un traitement. Pour le moment, je n'ai pas utilisé la généricité de cette classe. Or,
 * comme indique le titre de cette section, SwingWorker peut prendre deux types génériques.
 * Le 1er correspond au type de renvoi de la méthode doInBackground() et, par extension, au
 * type de renvoi de la méthode get(). Le deuxième est utilisé comme type de retour inter -
 * médiaire pendant l'exécution de la méthode doInBackground().
 * 
 * Afin de gérer les résultats intermédiaires, je peux utiliser les méthodes suivantes :
 * 
 * publish(V value) : publie le résultat intermédiaire pour la méthode process(List<V> list)
 * 
 * process(List<V> list) : permet d'utiliser le résultat intermédiaire pour un traitement
 * 		spécifique
 * 
 * Réf Test5
 * 
 * Remarques : la méthode publish(V value) définit les résultats intermédiaires. 
 * Et la méthode process(List<V> list) se fait dans l'EDT.
 * 
 * La classe SwingWorker<T,V> offre la possibilité de lancer des traitements dans un thread
 * tout en s'assurant que les mises à jour des composants se feront dans l'EDT.
 *  
 */
public class Main_EDT {

	public static void main(String[] args) {
		

	}

}
